#!/bin/sh

sdir=$(pwd)
MY_DIR=$(dirname $0)
cd ${MY_DIR}
MY_DIR=$(pwd)
cd $sdir

SQL_DIR=${MY_DIR}/sql
NAWK=/usr/bin/awk
created_by="Admin"
created_datetime=$(date '+%m-%d-%Y %H:%M:%S')
dbuser=root
dbpassword=admin
dbname=curabled
mysql=$(which mysql 2>/dev/null)
TEMP=/tmp/$(basename $0).$$

SM_CSV=${MY_DIR}/StateMaster.csv
DM_CSV=${MY_DIR}/DistrictMaster.csv
TM_CSV=${MY_DIR}/TaluqMaster.csv
PM_CSV=${MY_DIR}/PanchayatMaster.csv

if [ $? -ne 0 ]; then
	echo "Mysql binary not found. Please set the location in PATH and rerun."
	exit 1
fi

write_state_master_sql() {
	table_name=StateMaster
	$NAWK -F"," -v table_name=${table_name} -v c_dt="${created_datetime}" -v cb="${created_by}" '
	{
		if ( NR == 1 ) {
			sql_file_name=table_name ".sql"
			format="%m-%d-%Y %H:%i:%s";	
			printf("")>sql_file_name;
		} else {
			printf("Generataing SQL for %s\n", table_name);
			printf ( "insert into %s(createdBy, createdDate, code, name) values (\"%s\", STR_TO_DATE(\"%s\", \"%s\"), \"%s\", \"%s\");\n", table_name, cb, c_dt, format, $1, $2) >> sql_file_name;
		}
	}
' $SM_CSV
	printf "Moving %s to $SQL_DIR\n" ${table_name}.sql;
	mv -f ${table_name}.sql ${SQL_DIR}
}

write_district_master_sql() {
	table_name=DistrictMaster
	$NAWK -F"," -v table_name=${table_name} -v c_dt="${created_datetime}" -v cb="${created_by}" '
	{
		if ( NR == 1 ) {
			sql_file_name=table_name ".sql"
			format="%m-%d-%Y %H:%i:%s";	
			printf("")>sql_file_name;
		} 
		else {
			printf("Generataing SQL for table %s  state id : %d\n", table_name, $3);
			printf ( "insert into %s(createdBy, createdDate, code, name, stateMasterId) values (\"%s\", STR_TO_DATE(\"%s\", \"%s\"), \"%s\", \"%s\", %d);\n", table_name, cb, c_dt, format, $1, $2, $3) >> sql_file_name;
		
		}
	}
' $1
	printf "Moving %s to $SQL_DIR\n" ${table_name}.sql;
	mv -f ${table_name}.sql ${SQL_DIR}
}

write_taluk_master_sql() {
	table_name=TaluqMaster
	$NAWK -F"," -v table_name=${table_name} -v c_dt="${created_datetime}" -v cb="${created_by}" '
	{
		if ( NR == 1 ) {
			sql_file_name=table_name ".sql"
			format="%m-%d-%Y %H:%i:%s";	
			printf("")>sql_file_name;
		} 
		else {
			printf("Generataing SQL for table %s  district id : %d\n", table_name, $3);
			printf ( "insert into %s(createdBy, createdDate, code, name, districtMasterId) values (\"%s\", STR_TO_DATE(\"%s\", \"%s\"), \"%s\", \"%s\", %d);\n", table_name, cb, c_dt, format, $1, $2, $3) >> sql_file_name;
		
		}
	}
' $1
	printf "Moving %s to $SQL_DIR\n" ${table_name}.sql;
	mv -f ${table_name}.sql ${SQL_DIR}
}


write_panchayat_master_sql() {
	table_name=PanchayatMaster
	$NAWK -F"," -v table_name=${table_name} -v c_dt="${created_datetime}" -v cb="${created_by}" '
	{
		if ( NR == 1 ) {
			sql_file_name=table_name ".sql"
			format="%m-%d-%Y %H:%i:%s";	
			printf("")>sql_file_name;
		} 
		else {
			printf("Generataing SQL for table %s  taluq id : %d\n", table_name, $3);
			printf ( "insert into %s(createdBy, createdDate, code, name, taluqMasterId) values (\"%s\", STR_TO_DATE(\"%s\", \"%s\"), \"%s\", \"%s\", %d);\n", table_name, cb, c_dt, format, $1, $2, $3) >> sql_file_name;
		
		}
	}
' $1
	printf "Moving %s to $SQL_DIR\n" ${table_name}.sql;
	mv -f ${table_name}.sql ${SQL_DIR}
}

get_state_id() {
	state_id=$(mysql -u${dbuser} -p${dbpassword} $dbname -e "select id from statemaster where name=\"$1\";" -B --skip-column-names 2>/dev/null)
}

get_district_id() {
	district_id=$(mysql -u${dbuser} -p${dbpassword} $dbname -e "select id from districtmaster where name=\"$1\";" -B --skip-column-names 2>/dev/null)
}

get_taluq_id() {
	taluq_id=$(mysql -u${dbuser} -p${dbpassword} $dbname -e "select id from taluqmaster where name=\"$1\";" -B --skip-column-names 2>/dev/null)
}

# Write State Master
write_state_master_sql;
mysql -u${dbuser} -p${dbpassword} $dbname < ${SQL_DIR}/${table_name}.sql

if [ $? -ne 0 ]; then
	echo ${SQL_DIR}/${table_name}.sql failed
	exit 1;
fi
# Write District Master
cp -f ${DM_CSV} ${DM_CSV}.bak

# Get all states in file
$(awk -F, '{print $3}' ${DM_CSV}.bak | sort -u | grep -v ^State > ${TEMP})

# Substitute states with ids
OIFS=${IFS}
IFS=$'\n'       # make newlines the only separator

echo -n "Getting State IDs"
for j in $(cat ${TEMP})    
do
	echo -n ".";
	get_state_id "$j";
	sed "s/,$j\$/,${state_id}/g" ${DM_CSV}.bak > /tmp/save.$$
	mv /tmp/save.$$ ${DM_CSV}.bak 
done
echo "";
IFS=${OIFS}

# Write the District Master SQL file
write_district_master_sql ${DM_CSV}.bak;
rm -f ${DM_CSV}.bak ${TEMP}
mysql -u${dbuser} -p${dbpassword} $dbname < ${SQL_DIR}/${table_name}.sql

if [ $? -ne 0 ]; then
	echo ${SQL_DIR}/${table_name}.sql failed
	exit 1;
fi

# Write Taluq Master
cp -f ${TM_CSV} ${TM_CSV}.bak

# Get all districts in file
$(awk -F, '{print $3}' ${TM_CSV}.bak | sort -u | grep -v ^District > ${TEMP})

# Substitute districts with ids
OIFS=${IFS}
IFS=$'\n'       # make newlines the only separator
echo -n "Getting District IDs"
for j in $(cat ${TEMP})    
do
	echo -n ".";
	get_district_id "$j";
	sed "s/,$j\$/,${district_id}/g" ${TM_CSV}.bak > /tmp/save.$$
	mv /tmp/save.$$ ${TM_CSV}.bak 
done
echo "";
IFS=${OIFS}

# Write the Taluq Master SQL file
write_taluk_master_sql ${TM_CSV}.bak;
rm -f ${TM_CSV}.bak ${TEMP} 
mysql -u${dbuser} -p${dbpassword} $dbname < ${SQL_DIR}/${table_name}.sql

if [ $? -ne 0 ]; then
	echo ${SQL_DIR}/${table_name}.sql failed
	exit 1;
fi

# Write Panchayat Master
cp -f ${PM_CSV} ${PM_CSV}.bak

# Get all districts in file
$(awk -F, '{print $3}' ${PM_CSV}.bak | sort -u | grep -v ^Taluq > ${TEMP})

# Substitute taluqs with ids
OIFS=${IFS}
IFS=$'\n'       # make newlines the only separator
echo -n "Getting Taluq IDs"
for j in $(cat ${TEMP})    
do
	echo -n ".";
	get_taluq_id "$j";
	sed "s/,$j\$/,${taluq_id}/g" ${PM_CSV}.bak > /tmp/save.$$
	mv /tmp/save.$$ ${PM_CSV}.bak 
done
echo "";
IFS=${OIFS}

# Write the Taluq Master SQL file
write_panchayat_master_sql ${PM_CSV}.bak;
rm -f ${PM_CSV}.bak ${TEMP} 
mysql -u${dbuser} -p${dbpassword} $dbname < ${SQL_DIR}/${table_name}.sql

if [ $? -ne 0 ]; then
	echo ${SQL_DIR}/${table_name}.sql failed
	exit 1;
fi


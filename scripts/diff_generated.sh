#!/bin/sh

DESTDIR="${DESTDIR:-/d/Codebase/Codebase/sboot/codebase/CurableD/project}"

if [ ! -d "${DESTDIR}" ]; then
        echo "${DESTDIR} : No such directory"
        echo "Please set environment variable DESTDIR"
        exit 1
fi
sdir=$(dirname $0)
cd $sdir
sdir="${PWD}"

rest_files=$(echo *Controller.java)
service_files=$(echo *Service.java)
rep_files=$(echo *Repository.java)
dto_files=$(echo *DTO.java)
mapper_files=$(echo *Mapper.java)
domain_files=$(ls -1 *.java 2>/dev/null | grep -v Controller.java | grep -v Repository.java | grep -v Service.java | grep -v DTO.java | grep -v Mapper.java)

cd ${DESTDIR}/src/main/java

dest="com/curable/domain"
for f in ${domain_files}; do
	if [ -f "$sdir/$f" ]; then
		if [ ${dest}/$f ]; then
			tmp=$(diff --ignore-space-change $sdir/$f $dest/$f)
			if [ -n "${tmp}" ]; then
				echo "Differences between $sdir/$f and old copy."
				echo "****************** Start of Difference ************"
				diff --ignore-space-change $sdir/$f ${dest}/$f
				echo "****************** End of Difference ************"
				echo -n "Hit Enter to continue :"
				read dummy
			else
				echo "No difference between $sdir/$f and $dest/$f"
			fi
		else
			echo "File $sdir/$f is new - no previous copy found in ${dest}."
		fi
	fi
done 

dest="com/curable/repository"
for f in ${rep_files}; do
	if [ -f "$sdir/$f" ]; then
		if [ ${dest}/$f ]; then
			tmp=$(diff --ignore-space-change $sdir/$f $dest/$f)
			if [ -n "${tmp}" ]; then
				echo "Differences between $sdir/$f and old copy."
				echo "****************** Start of Difference ************"
				diff --ignore-space-change $sdir/$f ${dest}/$f
				echo "****************** End of Difference ************"
				echo -n "Hit Enter to continue :"
				read dummy
			else
				echo "No difference between $sdir/$f and $dest/$f"
			fi
		else
			echo "File $sdir/$f is new - no previous copy found in ${dest}."
		fi
	fi
done 

dest="com/curable/service"
for f in ${service_files}; do
	if [ -f "$sdir/$f" ]; then
		if [ ${dest}/$f ]; then
			tmp=$(diff --ignore-space-change $sdir/$f $dest/$f)
			if [ -n "${tmp}" ]; then
				echo "Differences between $sdir/$f and old copy."
				echo "****************** Start of Difference ************"
				diff --ignore-space-change $sdir/$f ${dest}/$f
				echo "****************** End of Difference ************"
				echo -n "Hit Enter to continue :"
				read dummy
			else
				echo "No difference between $sdir/$f and $dest/$f"
			fi
		else
			echo "File $sdir/$f is new - no previous copy found in ${dest}."
		fi
	fi
done 

dest="com/curable/rest"
for f in ${rest_files}; do
	if [ -f "$sdir/$f" ]; then
		if [ ${dest}/$f ]; then
			tmp=$(diff --ignore-space-change $sdir/$f $dest/$f)
			if [ -n "${tmp}" ]; then
				echo "Differences between $sdir/$f and old copy."
				echo "****************** Start of Difference ************"
				diff --ignore-space-change $sdir/$f ${dest}/$f
				echo "****************** End of Difference ************"
				echo -n "Hit Enter to continue :"
				read dummy
			else
				echo "No difference between $sdir/$f and $dest/$f"
			fi
		else
			echo "File $sdir/$f is new - no previous copy found in ${dest}."
		fi
	fi
done 

dest="com/curable/service/dto"
for f in ${dto_files}; do
	if [ -f "$sdir/$f" ]; then
		if [ ${dest}/$f ]; then
			tmp=$(diff --ignore-space-change $sdir/$f $dest/$f)
			if [ -n "${tmp}" ]; then
				echo "Differences between $sdir/$f and old copy."
				echo "****************** Start of Difference ************"
				diff --ignore-space-change $sdir/$f ${dest}/$f
				echo "****************** End of Difference ************"
				echo -n "Hit Enter to continue :"
				read dummy
			else
				echo "No difference between $sdir/$f and $dest/$f"
			fi
		else
			echo "File $sdir/$f is new - no previous copy found in ${dest}."
		fi
	fi
done 

dest="com/curable/service/mapper"
for f in ${mapper_files}; do
	if [ -f "$sdir/$f" ]; then
		if [ ${dest}/$f ]; then
			tmp=$(diff --ignore-space-change $sdir/$f $dest/$f)
			if [ -n "${tmp}" ]; then
				echo "Differences between $sdir/$f and old copy."
				echo "****************** Start of Difference ************"
				diff --ignore-space-change $sdir/$f ${dest}/$f
				echo "****************** End of Difference ************"
				echo -n "Hit Enter to continue :"
				read dummy
			else
				echo "No difference between $sdir/$f and $dest/$f"
			fi
		else
			echo "File $sdir/$f is new - no previous copy found in ${dest}."
		fi
	fi
done 


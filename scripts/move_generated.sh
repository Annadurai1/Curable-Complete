#!/bin/sh

DESTDIR="${DESTDIR:-/d/Codebase/Codebase/sboot/codebase/CurableD/project}"
if [ ! -d "${DESTDIR}" ]; then
	echo "${DESTDIR} : No such directory"
	echo "Please set environment variable DESTDIR"
	exit 1
fi
git=0;
if [ $# -gt 0 ]; then
	if [ "$1" == "--git" ]; then
		git=1
	else
		echo "$0 <--git>";
		exit 1
	fi	
fi
sdir=$(dirname $0)
cd $sdir
sdir="${PWD}"

rest_files=$(echo *Controller.java)
service_files=$(echo *Service.java)
rep_files=$(echo *Repository.java)
dto_files=$(echo *DTO.java)
mapper_files=$(echo *Mapper.java)
domain_files=$(ls -1 *.java 2>/dev/null| grep -v Controller.java | grep -v Repository.java | grep -v Service.java | grep -v DTO.java | grep -v Mapper.java)

cd ${DESTDIR}/src/main/java
dest="com/curable/domain"
for f in ${domain_files}; do
	if [ -f "${sdir}/$f" ]; then
		if [ -f $dest/$f ]; then
			manual_edit_found=$(grep "MANUAL EDIT" $dest/$f)
			if [ -n "${manual_edit_found}" ]; then
				echo "Not Moving $sdir/$f. Manual Edits done"
			else
				echo "Moving $sdir/$f to $dest/$f"
				mv $sdir/$f $dest/$f
				if [ $git -eq 1 ]; then
					git add $dest/$f
				fi	
			fi
		else #First Time
			echo "Moving $sdir/$f to $dest/$f"
			mv $sdir/$f $dest/$f
		fi
	fi
done 

dest="com/curable/repository"
for f in ${rep_files}; do
	if [ -f "${sdir}/$f" ]; then
		if [ -f $dest/$f ]; then
			manual_edit_found=$(grep "MANUAL EDIT" $dest/$f)
			if [ -n "${manual_edit_found}" ]; then
				echo "Not Moving $sdir/$f. Manual Edits done"
			else
				echo "Moving $sdir/$f to $dest/$f"
				mv $sdir/$f $dest/$f
				if [ $git -eq 1 ]; then
					git add $dest/$f
				fi	
			fi
		else #First Time
			echo "Moving $sdir/$f to $dest/$f"
			mv $sdir/$f $dest/$f
		fi
	fi
done 

dest="com/curable/service"
for f in ${service_files}; do
	if [ -f "${sdir}/$f" ]; then
		if [ -f $dest/$f ]; then
			manual_edit_found=$(grep "MANUAL EDIT" $dest/$f)
			if [ -n "${manual_edit_found}" ]; then
				echo "Not Moving $sdir/$f. Manual Edits done"
			else
				echo "Moving $sdir/$f to $dest/$f"
				mv $sdir/$f $dest/$f
				if [ $git -eq 1 ]; then
					git add $dest/$f
				fi	
			fi
		else #First Time
			echo "Moving $sdir/$f to $dest/$f"
			mv $sdir/$f $dest/$f
		fi
	fi
done 

dest="com/curable/rest"
for f in ${rest_files}; do
	if [ -f "${sdir}/$f" ]; then
		if [ -f $dest/$f ]; then
			manual_edit_found=$(grep "MANUAL EDIT" $dest/$f)
			if [ -n "${manual_edit_found}" ]; then
				echo "Not Moving $sdir/$f. Manual Edits done"
			else
				echo "Moving $sdir/$f to $dest/$f"
				mv $sdir/$f $dest/$f
				if [ $git -eq 1 ]; then
					git add $dest/$f
				fi	
			fi
		else #First Time
			echo "Moving $sdir/$f to $dest/$f"
			mv $sdir/$f $dest/$f
		fi
	fi
done 

dest="com/curable/service/dto"
for f in ${dto_files}; do
	if [ -f "${sdir}/$f" ]; then
		if [ -f $dest/$f ]; then
			manual_edit_found=$(grep "MANUAL EDIT" $dest/$f)
			if [ -n "${manual_edit_found}" ]; then
				echo "Not Moving $sdir/$f. Manual Edits done"
			else
				echo "Moving $sdir/$f to $dest/$f"
				mv $sdir/$f $dest/$f
				if [ $git -eq 1 ]; then
					git add $dest/$f
				fi	
			fi
		else #First Time
			echo "Moving $sdir/$f to $dest/$f"
			mv $sdir/$f $dest/$f
		fi
	fi
done 

dest="com/curable/service/mapper"
for f in ${mapper_files}; do
        if [ -f "${sdir}/$f" ]; then
                if [ -f $dest/$f ]; then
                        manual_edit_found=$(grep "MANUAL EDIT" $dest/$f)
                        if [ -n "${manual_edit_found}" ]; then
                                echo "Not Moving $sdir/$f. Manual Edits done"
                        else
                                echo "Moving $sdir/$f to $dest/$f"
                                mv $sdir/$f $dest/$f
				if [ $git -eq 1 ]; then
					git add $dest/$f
				fi	
                        fi
                else #First Time
                        echo "Moving $sdir/$f to $dest/$f"
                        mv $sdir/$f $dest/$f
                fi
        fi
done


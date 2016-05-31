#!/bin/bash
#########################################################################
# filename:		care_301_generate-example-data.sh						#
# author:		team white												#
# required:		sqlite3													#
# description:	inserts given data into database "care"					#
#########################################################################

# define some vars
position=("assistant" "receptionist" "doctor" "health visitor" "manager" "psychiatrist")
department=("home care" "clinic treatment" "administration" "accident and emergency unit")
incident_type=("vulgarity", "violence", "drop" "drunkenness" "drug consumption" "drug abuse" "sexual abuse" "suicide")
tmpfile=mktemp

# fill table employee
while read line
do
	firstname=`echo $line | cut -d"," -f1`
	surname=`echo $line | cut -d"," -f2`
	pos=`echo $line | cut -d"," -f3`
	dep=`echo $line | cut -d"," -f4`

	echo "INSERT INTO Employee (employeeFirstName, employeeSurName, departmentID, positionID) values(\"$firstname\", \"$surname\", (SELECT departmentID FROM Department WHERE departmentName = \"$dep\"),(SELECT positionID FROM Position WHERE positionName = \"$pos\"));" >> $tmpfile
done < ./care_302_employee-list.csv

sqlite3 ~/.sne/databases/care.db < $tmpfile

rm $tmpfile

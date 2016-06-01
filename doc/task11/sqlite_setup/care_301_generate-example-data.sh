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


# fill table employee
tmpfile=`mktemp`

while read line
do
	firstname=`echo $line | cut -d"," -f1`
	surname=`echo $line | cut -d"," -f2`
	pos=`echo $line | cut -d"," -f3`
	dep=`echo $line | cut -d"," -f4`

	echo "INSERT INTO Employee (employeeFirstName, employeeSurName, departmentID, positionID) values(\"$firstname\", \"$surname\", (SELECT departmentID FROM Department WHERE departmentName = \"$dep\"),(SELECT positionID FROM Position WHERE positionName = \"$pos\"));" >> $tmpfile
done < ./care_302_employee-list.csv

sqlite3 ~/.sne/databases/care.db < $tmpfile
echo "data for table Employee generated"

rm $tmpfile



# fill table patient
tmpfile2=`mktemp`

while read line
do
	firstname=`echo $line | cut -d"," -f1`
	surname=`echo $line | cut -d"," -f2`

	echo "INSERT INTO Patient (patientFirstName, patientSurName) values(\"$firstname\", \"$surname\");" >> $tmpfile2
done < ./care_303_patient-list.csv

sqlite3 ~/.sne/databases/care.db < $tmpfile2
echo "data for table Patient generated"

rm $tmpfile2



# fill table treatment
tmpfile3=`mktemp`
echo "INSERT INTO Treatment (patientID, employeeID, treatmentDate, duration) VALUES (30, 170, \"2014-01-01\", 35)" >> $tmpfile3

# loop for year 2013, 2014, 2015
for y in {2013..2015}
do
	# loop over all month
	for i in {1..12}
	do
		# loop over all days
		for j in {1..31}
		do
			# do not creat dates that don't exist
			if (( "$i" == "2" )) && (( "$j" > "28" ))
			then
				continue
			elif (( "$i" == "4" )) && (( "$i" == "6" )) && (( "$i" == "9" ))  && (( "$i" == "11" )) && (( "$j" > "30" ))
			then
				continue
			else
				pat=$(( ($RANDOM%1100)+1 ))
				emp=$(( ($RANDOM%300)+1 ))
				dur=$(( ($RANDOM%90)+6 ))
				
				# format month properly
				if (( "$i" <= 9 ))
				then
					mon="0$i"
				else
					mon=$i
				fi

				# format day properly
				if (( "$j" <= 9 ))
				then
					day="0$j"
				else
					day=$j
				fi
				
				echo ",($pat, $emp, \"$y-$mon-$day\", $dur)" >> $tmpfile3
			fi
		done
	done
done

echo ";" >> $tmpfile3
sqlite3 ~/.sne/databases/care.db < $tmpfile3
echo "data for table Treatment generated"

rm $tmpfile3





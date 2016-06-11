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
absence_reason=("Illness", "Vacation", "Overslept", "Compensation", "Appointment", "Education", "Pregnancy")

### fill table employee
tmpfile=`mktemp`

while read line
do
	firstname=`echo $line | cut -d"," -f1`
	surname=`echo $line | cut -d"," -f2`
	pos=`echo $line | cut -d"," -f3`
	dep=`echo $line | cut -d"," -f4`

	echo "INSERT INTO Employee (employeeFirstName, employeeSurName, departmentID, positionID) values(\"$firstname\", \"$surname\", (SELECT departmentID FROM Department WHERE departmentName = \"$dep\"),(SELECT positionID FROM Position WHERE positionName = \"$pos\"));" >> $tmpfile
done < ./care_302_employee-list.csv

sqlite3 ~/.sne/databases/dwh.db < $tmpfile
echo "data for table Employee generated"

rm $tmpfile



### fill table patient
tmpfile2=`mktemp`

while read line
do
	firstname=`echo $line | cut -d"," -f1`
	surname=`echo $line | cut -d"," -f2`

	echo "INSERT INTO Patient (patientFirstName, patientSurName) values(\"$firstname\", \"$surname\");" >> $tmpfile2
done < ./care_303_patient-list.csv

sqlite3 ~/.sne/databases/dwh.db < $tmpfile2
echo "data for table Patient generated"

rm $tmpfile2



### fill table treatment
tmpfile3=`mktemp`
tmpfile4=`mktemp`
tmpfile5=`mktemp`
treatmentid=0
absenceid=0

echo "INSERT INTO Treatment (patientID, employeeID, treatmentDate, duration) VALUES (30, 170, \"2014-01-01\", 35)" >> $tmpfile3
echo "INSERT INTO Incident (treatmentID, typeID) VALUES (1, 1)" >> $tmpfile4
echo "INSERT INTO Absence (employeeID, absenceDate, absenceReason) VALUES (01, \"2014-01-01\", \"Employee is sick\")" >> $tmpfile5

function is_absent() {
	if (( ($RANDOM%356) > 260 ))
	then
		return 0
	else
		return 1
	fi
}

function is_incident() {
	if (( ($RANDOM%100) >= 98 ))
	then
		return 0
	else
		return 1
	fi
}

function get_incident_type() {
	if (( ($RANDOM%1000) >= 999 ))
	then
		incident_type=8
	elif (( ($RANDOM%1000) >= 990 ))
	then
		incident_type=7
	else
		incident_type=$(( ($RANDOM%6)+1 ))
	fi
}

function get_absence_type() {
	if (( ($RANDOM%100) >= 98 ))
	then
		absenceid=7			# pregnancy
	elif (( ($RANDOM%100) >= 95 ))
	then
		absenceid=8			# other
	elif (( ($RANDOM%100) >= 90 ))
	then
		absenceid=6			# education
	else
		absenceid=$(( ($RANDOM%5)+1 ))
	fi
}


echo "Start generating Data for Treatment and Incident"
# loop for year 2013, 2014, 2015, 2016
for y in {2013..2016}
do
	# loop over all month
	for i in {1..12}
	do
		# loop over all days
		for j in {1..31}
		do
			# do not creat dates that don't exist
			if (( (( (( "$i" == "2" )) && (( "$j" > "28" )) && (( "$y" != "2016")) )) || (( (( "$i" == "2" )) && (( "$j" > "29" )) )) ))
			then
				continue
			elif (( (( "$i" == "4" )) || (( "$i" == "6" )) || (( "$i" == "9" ))  || (( "$i" == "11" )) )) && (( "$j" > "30" ))
			then
				continue
			else
				# loop over all employees
				for e in {1..300}
				do
					if is_absent ;
					then
						emp=$e
						get_absence_type

						echo ",($emp, \"$y-$mon-$day\", $absenceid)" >> $tmpfile5
						
					else
						pat=$(( ($RANDOM%1100)+1 ))
						emp=$e
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
						
						((treatmentid++))

						if is_incident ;
						then
							get_incident_type
							type=$incident_type
							echo ",($treatmentid,$type)" >> $tmpfile4
						fi
					fi
				done
			fi
		done
		echo "  -> generate data until $y-$mon-$day"
	done
done

echo ";" >> $tmpfile3
echo ";" >> $tmpfile4
echo ";" >> $tmpfile5
sqlite3 ~/.sne/databases/dwh.db < $tmpfile3
sqlite3 ~/.sne/databases/dwh.db < $tmpfile4
sqlite3 ~/.sne/databases/dwh.db < $tmpfile5
echo "data for table Treatment and Incident generated"

rm $tmpfile3
rm $tmpfile4
rm $tmpfile5



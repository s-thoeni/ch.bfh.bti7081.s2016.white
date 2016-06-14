#!/bin/bash
#########################################################################
# filename:		accounting_301_generate-example-data.sh					#
# author:		team white												#
# required:		sqlite3													#
# description:	inserts given data into database "dwh"					#
#########################################################################


### fill table treatment
# create temporary files for the insert query
tmpfile=`mktemp`

# prepare insert querie. it's faster to have one query for all inserts
echo "INSERT INTO Journal (journalDate, effort, return, cashflow) VALUES (\"2012-12-31\", 104340, 112080, -4880)" >> $tmpfile


# loop for year 2013, 2014, 2015, 2016
for y in {2013..2016}
do
	# generate dummy write-off and calculate it on daily basis
	## bash itself can't calc floats, so let's use 'bc' instead
	write_off=`echo 'scale=2;'$((950000 + (5*$RANDOM)+1))'/356'|bc`

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
				# generate dummy data
				effort=`echo 'scale=2;100000+('$((($RANDOM%2000)+1))'*5.1)'|bc`
				return=`echo 'scale=2;100000+('$((($RANDOM%2000)+1))'*5.1)'|bc`
				cashflow=`echo 'scale=2;'$return'-'$effort'+'$write_off|bc`
	
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

				# add line to query
				echo ",(\"$y-$mon-$day\", $effort, $return, $cashflow)" >> $tmpfile
	
			fi
		done
	done
done

# finnish each insert query with a semicolon.
echo ";" >> $tmpfile

# insert data in db. we love input redirection
sqlite3 ~/.sne/databases/dwh.db < $tmpfile

# remove temp files
rm $tmpfile



#!/bin/bash

#########################################################################
# filename:		accounting_001_create-database.sh						#
# author:		team white												#
# required:		sqlite3													#
# description:	creates the database accounting on your linux machine	#
#########################################################################

# create file structure in home dir for databases
mkdir /home/`whoami`/.sne
mkdir /home/`whoami`/.sne/databases

# create database and import data
sqlite3 /home/`whoami`/.sne/databases/accounting.db < ./accounting_101_create-tables.sql



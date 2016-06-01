----------------------------------------------------------------------
-- filename:	conf_101_create-tables.sql							--
-- author:		team white											--
-- description:	setup tables for configuration database				--
-- use:			run on command line:								--
--				sqlite3 conf.db < conf_101_create-tables.sql		--
----------------------------------------------------------------------

CREATE TABLE User (
	userID		INTEGER PRIMARY KEY AUTOINCREMENT,
	firstName	NVARCHAR(128) NOT NULL,
	surName		NVARCHAR(128) NOT NULL
);

CREATE TABLE Configuration (
	confID			INTEGER PRIMARY KEY AUTOINCREMENT,
	tileNumber		INTEGER NULL,
	reportType		NVARCHAR(128) NOT NULL,
	reportTimeFrame	NVARCHAR(128) NOT NULL,
	userID			INTEGER NOT NULL,
	FOREIGN KEY(userID) REFERENCES User(userID)
);

CREATE TABLE Alarm (
	alarmID			INTEGER PRIMARY KEY AUTOINCREMENT,
	reportType		NVARCHAR(128) NOT NULL,
	alarmTimeFrame	NVARCHAR(128) NOT NULL,
	comperator		NVARCHAR(128) NOT NULL,
	errorValue		INTEGER NOT NULL,
	warnValue		INTEGER NOT NULL,
	userID			INTEGER NOT NULL,
	FOREIGN KEY(userID) REFERENCES User(userID)
);
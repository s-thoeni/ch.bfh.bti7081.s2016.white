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
	confID		INTEGER PRIMARY KEY AUTOINCREMENT,
	tileNumer	INTEGER NOT NULL,
	tileValue	NVARCHAR(128) NOT NULL,
	userID		INTEGER NOT NULL,
	FOREIGN KEY(userID) REFERENCES User(userID)
);


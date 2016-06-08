----------------------------------------------------------------------
-- filename:	accounting_101_create-tables.sql					--
-- author:		team white											--
-- description:	setup tables for configuration database				--
-- use:			run on command line:								--
--				sqlite3 conf.db < accounting_101_create-tables.sql	--
----------------------------------------------------------------------


CREATE TABLE Journal (
	journalID				INTEGER PRIMARY KEY AUTOINCREMENT,
	journalDate				DATE NOT NULL,
	effort					FLOAT NOT NULL,
	return					FLOAT NOT NULL,
	cashFlow				FLOAT NOT NULL
);


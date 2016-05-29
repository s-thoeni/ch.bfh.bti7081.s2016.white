----------------------------------------------------------------------
-- filename:	accounting_101_create-tables.sql					--
-- author:		team white											--
-- description:	setup tables for configuration database				--
-- use:			run on command line:								--
--				sqlite3 conf.db < accounting_101_create-tables.sql	--
----------------------------------------------------------------------


CREATE TABLE AccountType (
	typeID		INTEGER PRIMARY KEY AUTOINCREMENT,
	typeName	NVARCHAR(128) NOT NULL
);

CREATE TABLE Acocunt (
	accountID				INTEGER PRIMARY KEY AUTOINCREMENT,
	typeID					INTEGER NOT NULL,
	accountName				NVARCHAR(128) NOT NULL,
	FOREIGN KEY(typeID) REFERENCES AccountType(typeID)
);

CREATE TABLE Journal (
	entryID					INTEGER PRIMARY KEY AUTOINCREMENT,
	debitAccountID			INTEGER NOT NULL,
	creditAccountID			INTEGER NOT NULL,
	amount					DECIMAL(18,2) NOT NULL,
	transactoinDate			DATE NOT NULL,
	liquidityRelated		BOOLEAN NOT NULL,
	incomeStatementRelated	BOOLEAN NOT NULL,
	FOREIGN KEY(debitAccountID) REFERENCES Account(accountID),
	FOREIGN KEY(creditAccountID) REFERENCES Account(accountID)
);


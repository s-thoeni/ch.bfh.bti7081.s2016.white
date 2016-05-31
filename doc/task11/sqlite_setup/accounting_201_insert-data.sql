----------------------------------------------------------------------
-- filename:	accounting_201_insert-data.sql						--
-- author:		team white											--
-- description:	setup tables for configuration database				--
-- use:			run on command line:								--
--				sqlite3 conf.db < accounting_201_insert-data.sql	--
----------------------------------------------------------------------

-- TODO
INSERT INTO AccountType
	(typeName)
VALUES
	('assets'),
	('liabilities'),
	('effort'),
	('earnings');


INSERT INTO Account
	(accountName, accountType)
VALUES
	('HR effort', (SELECT typeID FROM AccountType WHERE typeName = 'debit account')),
	('', (SELECT typeID FROM AccountType WHERE typeName = 'credit account')),
	('', (SELECT typeID FROM AccountType WHERE typeName = 'credit account'));

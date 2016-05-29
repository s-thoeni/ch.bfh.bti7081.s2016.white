----------------------------------------------------------------------
-- filename:	conf_201_insert-data.sql							--
-- author:		team white											--
-- description:	setup tables for configuration database				--
-- use:			run on command line:								--
--				sqlite3 conf.db < conf_201_insert-data.sql			--
----------------------------------------------------------------------

INSERT INTO User
	(firstName, surName)
VALUES
	('Lucas', 'Wirtz'),
	('Kathrin','Krüger'),
	('Alice','Lang'),
	('Carolin','Gruber'),
	('Konrad','Beck'),
	('Simone','Fisher'),
	('Kevin','Meier');


--INSERT INTO Configuration
--	(tileNumber, tileValue, userID)



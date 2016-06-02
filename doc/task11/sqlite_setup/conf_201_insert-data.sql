----------------------------------------------------------------------
-- filename:	conf_201_insert-data.sql							--
-- author:		team white											--
-- description:	setup tables for configuration database				--
-- use:			run on command line:								--
--				sqlite3 conf.db < conf_201_insert-data.sql			--
----------------------------------------------------------------------

INSERT INTO User
	(firstName, surName, userName)
VALUES
	('Lucas', 'Wirtz', 'lucas.wirtz'),
	('Kathrin','Krüger', 'kathrin.krueger'),
	('Alice','Lang', 'alice.lang'),
	('Carolin','Gruber', 'carolin.gruber'),
	('Konrad','Beck', 'konrad.beck'),
	('Simone','Fisher', 'simone.fisher'),
	('Kevin','Meier', 'kevin.meier');


--INSERT INTO Configuration
--	(tileNumber, tileValue, userID)



----------------------------------------------------------------------
-- filename:	conf_201_insert-data.sql							--
-- author:		team white											--
-- description:	setup tables for configuration database				--
-- use:			run on command line:								--
--				sqlite3 conf.db < conf_201_insert-data.sql			--
----------------------------------------------------------------------

INSERT INTO User
	(firstName, surName, userName, userPassword)
VALUES
	('Lucas', 'Wirtz', 'lucas.wirtz', '181210f8f9c779c26da1d9b2075bde0127302ee0e3fca38c9a83f5b1dd8e5d3b'),
	('Kathrin','Krüger', 'kathrin.krueger', '181210f8f9c779c26da1d9b2075bde0127302ee0e3fca38c9a83f5b1dd8e5d3b'),
	('Alice','Lang', 'alice.lang', '181210f8f9c779c26da1d9b2075bde0127302ee0e3fca38c9a83f5b1dd8e5d3b'),
	('Carolin','Gruber', 'carolin.gruber', '181210f8f9c779c26da1d9b2075bde0127302ee0e3fca38c9a83f5b1dd8e5d3b'),
	('Konrad','Beck', 'konrad.beck', '181210f8f9c779c26da1d9b2075bde0127302ee0e3fca38c9a83f5b1dd8e5d3b'),
	('Simone','Fisher', 'simone.fisher', '181210f8f9c779c26da1d9b2075bde0127302ee0e3fca38c9a83f5b1dd8e5d3b'),
	('Kevin','Meier', 'kevin.meier', '181210f8f9c779c26da1d9b2075bde0127302ee0e3fca38c9a83f5b1dd8e5d3b');


--INSERT INTO Configuration
--	(tileNumber, tileValue, userID)



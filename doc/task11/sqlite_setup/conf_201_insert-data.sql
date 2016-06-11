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
	('Lucas', 'Wirtz', 'lucas.wirtz', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),
	('Kathrin','Krüger', 'kathrin.krueger', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),
	('Alice','Lang', 'alice.lang', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),
	('Carolin','Gruber', 'carolin.gruber', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),
	('Konrad','Beck', 'konrad.beck', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),
	('Simone','Fisher', 'simone.fisher', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),
	('Kevin','Meier', 'kevin.meier', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3');


INSERT INTO Configuration
	(confID, reportType, reportTimeFrame, userID)
VALUES
	(125, 'AVAILABLE_EMPLOYEES', 'LAST_WEEK', 1),
	(127, 'EFFORT', 'CURRENT_WEEK', 1);


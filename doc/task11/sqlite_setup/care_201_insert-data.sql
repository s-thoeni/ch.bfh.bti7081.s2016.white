----------------------------------------------------------------------
-- filename:	conf_201_insert-data.sql							--
-- author:		team white											--
-- description:	setup tables for configuration database				--
-- use:			run on command line:								--
--				sqlite3 conf.db < conf_201_insert-data.sql			--
----------------------------------------------------------------------

INSERT INTO Position
	(positionName)
VALUES
	('nurse'),
	('receptionist'),
	('doctor'),
	('health visior'),
	('manager');

	
INSERT INTO Department
	(departmentName)
VALUES
	('home care'),
	('clinic treatment'),
	('administration');


INSERT INTO IncidentType
	(typeName)
VALUES
	('vulgarity'),
	('violence'),
	('drop'),
	('drunkenness'),
	('drug consumption'),	-- drogenkonsum
	('drug abuse'),			-- medikamentenmissbrauch
	('sexual abuse'),
	('suicide');





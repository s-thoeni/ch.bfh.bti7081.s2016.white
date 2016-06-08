----------------------------------------------------------------------
-- filename:	care_101_create-tables.sql							--
-- author:		team white											--
-- description:	setup tables for configuration database				--
-- use:			run on command line:								--
--				sqlite3 conf.db < care_101_create-tables.sql		--
----------------------------------------------------------------------

CREATE TABLE Position (
	positionID		INTEGER PRIMARY KEY AUTOINCREMENT,
	positionName	NVARCHAR(128) NOT NULL
);

CREATE TABLE Department (
	departmentID		INTEGER PRIMARY KEY AUTOINCREMENT,
	departmentName		NVARCHAR(128) NOT NULL
);

CREATE TABLE Employee (
	employeeID			INTEGER PRIMARY KEY AUTOINCREMENT,
	employeeFirstName	NVARCHAR(128) NOT NULL,
	employeeSurName		NVARCHAR(128) NOT NULL,
	departmentID		INTEGER NOT NULL,
	positionID			INTEGER NOT NULL,
	FOREIGN KEY(positionID) REFERENCES Position(positionID)
	FOREIGN KEY(departmentID) REFERENCES Department(departmentID)
);

ALTER TABLE Department
	ADD COLUMN departmentHead
	REFERENCES Employee(userID);

CREATE TABLE Patient (
	patientID			INTEGER PRIMARY KEY AUTOINCREMENT,
	patientFirstName	NVARCHAR(128) NOT NULL,
	patientSurName		NVARCHAR(128) NOT NULL
);

CREATE TABLE Treatment (
	treatmentID			INTEGER PRIMARY KEY AUTOINCREMENT,
	patientID			INTEGER NOT NULL,
	employeeID			INTEGER NOT NULL,
	treatmentDate		DATE NOT NULL,
	duration			INTEGER NOT NULL,	-- duration in minutes
	FOREIGN KEY(patientID) REFERENCES Patient(patientID),
	FOREIGN KEY(employeeID) REFERENCES Employee(employeeID)
);

CREATE TABLE IncidentType (
	typeID				INTEGER PRIMARY KEY AUTOINCREMENT,
	typeName			NVARCHAR(128) NOT NULL
);

CREATE TABLE Incident (
	incidentID			INTEGER PRIMARY KEY AUTOINCREMENT,
	treatmentID			INTEGER NOT NULL,
	typeID				INTEGER NOT NULL,
	description			NVARCHAR(2048) NULL,
	FOREIGN KEY(treatmentID) REFERENCES Treatment(treatmentID),
	FOREIGN KEY(typeID) REFERENCES IncidentType(typeID)
);


CREATE TABLE Absence (
	absenceID			INTEGER PRIMARY KEY AUTOINCREMENT,
	employeeID			INTEGER NOT NULL,
	absenceDate			DATE NOT NULL,
	absenceReason		INTEGER NOT NULL,
	FOREIGN KEY(absenceReason) REFERENCES AbsenceReason(reasonID)
);

CREATE TABLE AbsenceReason (
	reasonID			INTEGER PRIMARY KEY AUTOINCREMENT,
	reasonDescription	NVARCHAR(32) NULL
);

-- Optional Tables Invoicing for future enhancements of reporting features
CREATE TABLE Invoice (
	invoiceID			INTEGER PRIMARY KEY AUTOINCREMENT,
	invoiceDate			DATE NOT NULL
);

CREATE TABLE Invoice_Treatment (
	invoiceID			INTEGER NOT NULL,
	treatmentID			INTEGER NOT NULL,
	PRIMARY KEY(invoiceID, treatmentID) ON CONFLICT ROLLBACK,
	FOREIGN KEY(invoiceID) REFERENCES Invoice(invoiceID),
	FOREIGN KEY(treatmentID) REFERENCES Treatment(treatmentID)
);




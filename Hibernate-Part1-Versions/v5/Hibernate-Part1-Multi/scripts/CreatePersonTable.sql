CREATE TABLE IF NOT EXISTS PERSON (
PersonId INT NOT NULL auto_increment,
LastName VARCHAR(30) default NULL,
FirstName VARCHAR(30) default NULL,
MiddleName VARCHAR(30) default NULL,
Suffix VARCHAR(20) default NULL,
Title VARCHAR(20) default NULL,
StreetNo INT default NULL,
Barangay VARCHAR(30) default NULL,
City VARCHAR(30) default NULL,
ZipCode INT default NULL,
Birthday DATE default NULL,
GWA DOUBLE default NULL,
DateHired VARCHAR(20) default NULL,
CurrentlyEmployed CHAR(1) DEFAULT NULL,
PRIMARY KEY(PersonId)
);

CREATE TABLE IF NOT EXISTS PERSON (
Person_Id INT NOT NULL auto_increment,
Last_Name VARCHAR(30) default NULL,
First_Name VARCHAR(30) default NULL,
Middle_Name VARCHAR(30) default NULL,
Suffix VARCHAR(20) default NULL,
Title VARCHAR(20) default NULL,
Street_No INT default NULL,
Barangay VARCHAR(30) default NULL,
City VARCHAR(30) default NULL,
Zip_Code INT default NULL,
Birthday DATE default NULL,
GWA DOUBLE default NULL,
Date_Hired VARCHAR(20) default NULL,
Currently_Employed CHAR(1) DEFAULT NULL,
PRIMARY KEY(Person_Id)
);

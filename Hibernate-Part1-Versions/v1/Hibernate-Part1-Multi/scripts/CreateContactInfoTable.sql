create table CONTACT_INFO (
Contact_Id INT NOT NULL auto_increment,
idx INT DEFAULT 0, 
Land_Line VARCHAR(20) DEFAULT NULL,
Mobile_Number VARCHAR(20) DEFAULT NULL,
Email VARCHAR(20) DEFAULT NULL,
Person_Id INT default NULL,
PRIMARY KEY(Contact_Id)
);

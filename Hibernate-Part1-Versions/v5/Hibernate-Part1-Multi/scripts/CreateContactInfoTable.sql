CREATE TABLE IF NOT EXISTS CONTACT_INFO (
ContactId INT NOT NULL auto_increment,
idx INT DEFAULT 0, 
LandLine VARCHAR(20) DEFAULT NULL,
MobileNumber VARCHAR(20) DEFAULT NULL,
Email VARCHAR(30) DEFAULT NULL,
PersonId INT default NULL,
PRIMARY KEY(ContactId)
);

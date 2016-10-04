CREATE TABLE IF NOT EXISTS CONTACT_INFO (
contact_id INT NOT NULL auto_increment,
idx INT DEFAULT 0, 
type VARCHAR(20) DEFAULT NULL,
information VARCHAR(20) DEFAULT NULL,
person_id INT default NULL,
PRIMARY KEY(contact_id)
);

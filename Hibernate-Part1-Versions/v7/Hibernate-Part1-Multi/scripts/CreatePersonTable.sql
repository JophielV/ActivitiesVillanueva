CREATE TABLE IF NOT EXISTS PERSON (
person_id INT NOT NULL auto_increment,
last_name VARCHAR(30) default NULL,
first_name VARCHAR(30) default NULL,
middle_name VARCHAR(30) default NULL,
suffix VARCHAR(20) default NULL,
title VARCHAR(20) default NULL,
street_no INT default NULL,
barangay VARCHAR(30) default NULL,
city VARCHAR(30) default NULL,
zip_code INT default NULL,
birthday DATE default NULL,
gwa DOUBLE default NULL,
date_hired VARCHAR(20) default NULL,
currently_employed CHAR(1) DEFAULT NULL,
PRIMARY KEY(person_id)
);

CREATE TABLE IF NOT EXISTS PERSON_ROLES (
person_id INT DEFAULT NULL,
role_id INT DEFAULT NULL,
idx INT DEFAULT 0, 
PRIMARY KEY(person_id, role_id)
);

CREATE TABLE IF NOT EXISTS PERSON_ROLES (
Person_Id INT DEFAULT NULL,
Role_Id INT DEFAULT NULL,
idx INT DEFAULT 0, 
PRIMARY KEY(Person_Id, Role_Id)
);
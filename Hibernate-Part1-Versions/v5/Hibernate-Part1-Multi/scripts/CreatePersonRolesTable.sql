CREATE TABLE IF NOT EXISTS PERSON_ROLES (
PersonId INT DEFAULT NULL,
RoleId INT DEFAULT NULL,
idx INT DEFAULT 0, 
PRIMARY KEY(PersonId, RoleId)
);

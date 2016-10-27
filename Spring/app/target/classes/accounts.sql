INSERT INTO USERS(username,password,enabled)
VALUES ('admin','123456', true);
INSERT INTO USERS(username,password,enabled)
VALUES ('user','123456', true);

INSERT INTO USER_ROLES (username, role)
VALUES ('admin', 'ROLE_USER');
INSERT INTO USER_ROLES (username, role)
VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO USER_ROLES (username, role)
VALUES ('user', 'ROLE_USER');

--Any initail sql data must be set here (usually insert statments)

INSERT INTO users(username,password,enabled) VALUES ('manager1','$2a$10$g4eOBgsOTOIIRvPBgDnEy.qzoiMygNdIw.CmletGIXLs19J2noJli', 1);
INSERT INTO users(username,password,enabled) VALUES ('waiter1','$2a$10$2L.9niCO09qso2ZOpnIefOwDgAH5KZkB3PyXXowXvfYSQ0vdF7DWK', 1);

INSERT INTO user_roles (username, role) VALUES ('manager1', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role) VALUES ('waiter1', 'ROLE_USER');
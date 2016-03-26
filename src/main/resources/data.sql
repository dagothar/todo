INSERT INTO Tasks(authorId, status, todo, date) VALUES (1, TRUE, 'make tasks database', DATE '2016-03-24');
INSERT INTO Tasks(authorId, status, todo, date) VALUES (1, TRUE, 'add tasks', DATE '2016-03-24');
INSERT INTO Tasks(authorId, status, todo, date) VALUES (1, FALSE, 'calendar page', DATE '2016-03-24');
INSERT INTO Tasks(authorId, status, todo, date) VALUES (1, TRUE, 'other stuff', DATE '2016-03-25');
INSERT INTO Tasks(authorId, status, todo, date) VALUES (1, FALSE, 'other day', DATE '2016-03-25');

INSERT INTO Users(username, passwordHash, role) VALUES ('dagothar', '$2a$04$TV2.N1J7veWPouHA5PVan.7oPw9M1yO4bBpdRZ9B2Pc61/UWiVITe', 'USER');
INSERT INTO Users(username, passwordHash, role) VALUES ('u1', '$2a$04$BdkOAmQBi1tDLvf1jiFLP./OMm3dq7XRuZrbb5L1OwuRmtypUys1.', 'USER');

CREATE TABLE Tasks(id SERIAL, authorId INT NOT NULL, status BOOLEAN DEFAULT FALSE, todo VARCHAR(255) NOT NULL, date DATE DEFAULT NOW());

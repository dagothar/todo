-- CREATE TABLE IF NOT EXISTS Tasks(
--     id SERIAL,
--     author INT NOT NULL,
--     status BOOLEAN DEFAULT FALSE,
--     todo VARCHAR(255) NOT NULL,
--     date DATE DEFAULT NOW()
-- );

CREATE Table IF NOT EXISTS Users(id SERIAL,
    username VARCHAR(255) NOT NULL, 
    passwordHash VARCHAR(255) NOT NULL, 
    role VARCHAR(255) NOT NULL DEFAULT 'USER',
    UNIQUE KEY (username)
);
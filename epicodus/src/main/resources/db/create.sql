SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS students (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    age INTEGER,
    lastJob VARCHAR,
    gender VARCHAR,
    zipcode VARCHAR,
    currentTrack VARCHAR,
    graduated BOOLEAN,
    currentJob VARCHAR,
    daysToJob INTEGER,
);
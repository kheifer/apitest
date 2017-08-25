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
CREATE TABLE IF NOT EXISTS teachers(
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    hireDate VARCHAR,
    gender VARCHAR,
    zipcode VARCHAR,
    classesTaught INTEGER,
);
CREATE TABLE IF NOT EXISTS cohorts (
  cohortId int PRIMARY KEY auto_increment,
  cohortName VARCHAR,
  location VARCHAR
);
CREATE TABLE IF NOT EXISTS cohorts_teachers (
  id int PRIMARY KEY auto_increment,
  cohortName VARCHAR,
  teacherId INTEGER
);
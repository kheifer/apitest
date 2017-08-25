SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS students (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    age INTEGER,
    lastJob VARCHAR,
    gender VARCHAR,
    zipcode VARCHAR,
    trackId VARCHAR,
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
CREATE TABLE IF NOT EXISTS tracks (
  trackId int PRIMARY KEY auto_increment,
  description VARCHAR,
  duration VARCHAR,
  focus VARCHAR
);
CREATE TABLE IF NOT EXISTS cohorts_tracks (
  id int PRIMARY KEY auto_increment,
  cohortId VARCHAR,
  teacherId INTEGER
);
CREATE TABLE IF NOT EXISTS teachers_students (
  id int PRIMARY KEY auto_increment,
   VARCHAR,
  teacherId INTEGER
);
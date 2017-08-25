SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS cohorts (
  cohortId int PRIMARY KEY auto_increment,
  cohortName VARCHAR,
  location VARCHAR,
  dateStarted VARCHAR,
);
CREATE TABLE IF NOT EXISTS tracks (
  trackId int PRIMARY KEY auto_increment,
  description VARCHAR,
  duration VARCHAR,
  focus VARCHAR,
);
CREATE TABLE IF NOT EXISTS cohorts_tracks (
  id int PRIMARY KEY auto_increment,
  cohortId INTEGER,
  trackId INTEGER,
);
CREATE TABLE User (
	user_id INTEGER PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL

);
CREATE TABLE Country (
	country_id INTEGER PRIMARY KEY,
	name VARCHAR(255) NOT NULL
);
CREATE TABLE Entry (
	entry_id INTEGER PRIMARY KEY,
	song VARCHAR(255),
	user_id INTEGER,
	season_id INTEGER,
	liveshow_id INTEGER,
	FOREIGN KEY (user_id) REFERENCES User(user_id),
	FOREIGN KEY (season_id) REFERENCES Season(season_id),
	FOREIGN KEY (liveshow_id) REFERENCES LiveShow(liveshow_id)
);
CREATE TABLE LiveShow (


);
CREATE TABLE Semifinal (

);
CREATE TABLE Final (

);
CREATE TABLE Season (

);
CREATE TABLE Vote (
 
);
CREATE TABLE VotingSystem (

);
CREATE TABLE VotedCountry (
	
);
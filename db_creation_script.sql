CREATE TABLE User_ (
	user_id SERIAL PRIMARY KEY,
	name VARCHAR(255) UNIQUE NOT NULL,
	email VARCHAR(255) UNIQUE NOT NULL

);
CREATE TABLE Country (
	country_id SERIAL PRIMARY KEY,
	name VARCHAR(255) UNIQUE NOT NULL
);
CREATE TABLE VotingSystem (
	votingsystem_id SERIAL PRIMARY KEY,
	name VARCHAR(255) UNIQUE NOT NULL,
	points INTEGER[] NOT NULL
);

CREATE TYPE SeasonStatus AS ENUM ('SIGNUPS', 'IN_PROGRESS', 'FINISHED');
CREATE TABLE Season (
	season_id SERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	votingsystem_semi_id INTEGER,
	votingsystem_final_id INTEGER,
	autoqualifiers INTEGER,
	qualifiers INTEGER,
	currentshow INTEGER,
	seasonstatus SeasonStatus NOT NULL
);
CREATE TABLE LiveShow (
	liveshow_id SERIAL PRIMARY KEY,
	name VARCHAR(255),
	season_id INTEGER,
	votingsystem_id INTEGER,
	FOREIGN KEY (season_id) REFERENCES Season(season_id),
	FOREIGN KEY (votingsystem_id) REFERENCES VotingSystem(votingsystem_id)
);
CREATE TABLE Semifinal (
	semi_id INTEGER PRIMARY KEY,
	qualifiers INTEGER,
	FOREIGN KEY (semi_id) REFERENCES LiveShow(liveshow_id)
);

CREATE TABLE Final_ (
	final_id INTEGER PRIMARY KEY,
-- WIP
	FOREIGN KEY (final_id) REFERENCES LiveShow(liveshow_id)
);
CREATE TABLE Entry (
	entry_id SERIAL PRIMARY KEY,
	song VARCHAR(255) UNIQUE,
	user_id INTEGER,
	country_id INTEGER,
	season_id INTEGER,
	liveshow_id INTEGER,
	FOREIGN KEY (user_id) REFERENCES User_(user_id),
	FOREIGN KEY (country_id) REFERENCES Country(country_id),
	FOREIGN KEY (season_id) REFERENCES Season(season_id),
	FOREIGN KEY (liveshow_id) REFERENCES LiveShow(liveshow_id)
);
CREATE TABLE LiveShowEntry (
	liveshowentry_id SERIAL PRIMARY KEY,
	liveshow_id INTEGER,
    entry_id INTEGER,
    score INTEGER NOT NULL,
    FOREIGN KEY (liveshow_id) REFERENCES LiveShow(liveshow_id),
    FOREIGN KEY (entry_id) REFERENCES Entry(entry_id)
);
CREATE TABLE Vote (
	vote_id SERIAL PRIMARY KEY,
	user_id INTEGER,
	liveshow_id INTEGER,
	FOREIGN KEY (user_id) REFERENCES User_(user_id),
	FOREIGN KEY (liveshow_id) REFERENCES LiveShow(liveshow_id)
);
CREATE TABLE VotedCountry (
	vote_country_id SERIAL PRIMARY KEY,
	vote_id INTEGER,
	country_id INTEGER,
	FOREIGN KEY (vote_id) REFERENCES Vote(vote_id),
	FOREIGN KEY (country_id) REFERENCES Country(country_id)
);

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO paul;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO paul;
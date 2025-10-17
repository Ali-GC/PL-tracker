DROP TABLE IF EXISTS matches;

CREATE TABLE matches (
    match_id        INTEGER PRIMARY KEY,
    season          VARCHAR(9) NOT NULL,
    match_week      INTEGER,
    match_date      DATE NOT NULL,
    kickoff_time    VARCHAR(10),
    home_team       VARCHAR(100) NOT NULL,
    away_team       VARCHAR(100) NOT NULL,
    home_score      INTEGER,
    away_score      INTEGER
);

ALTER TABLE matches
ADD CONSTRAINT home_team_cannot_equal_away_team CHECK (home_team <> away_team);

DROP TABLE IF EXISTS teams;

CREATE TABLE teams (
    team_id         INT GENERATED ALWAYS AS IDENTITY,
    team_name       VARCHAR(50) NOT NULL,
    venue           VARCHAR(100) NOT NULL,
    owner_name      VARCHAR(100) NOT NULL,
    logo_url        VARCHAR(200)
);


INSERT INTO matches (match_id, season, match_week, match_date, kickoff_time, home_team, away_team, home_score, away_score)
SELECT match_id, season, matchweek, match_date, kickoff_time, home_team, away_team, home_score, away_score
FROM pl_matches;

INSERT INTO teams (team_name, venue, owner_name, logo_url) VALUES
('Liverpool', 'Anfield', 'Fenway Sports Group', 'https://resources.premierleague.com/premierleague25/badges-alt/14.svg'),
('Manchester United', 'Old Trafford', 'The Glazer family', 'https://resources.premierleague.com/premierleague25/badges-alt/1.svg'), 
('Manchester City', 'Etihad Stadium', 'Sheikh Mansour','https://resources.premierleague.com/premierleague25/badges-alt/43.svg'),
('West Ham United', 'London Stadium', 'David Sullivan & Daniel Kretinsky','https://resources.premierleague.com/premierleague25/badges-alt/21.svg'),
('Arsenal', 'Emirates Stadium', 'Stan Kroenke','https://resources.premierleague.com/premierleague25/badges-alt/3.svg'),
('Tottenham Hotspur', 'Tottenham Hotspur Stadium', 'Joe Lewis','https://resources.premierleague.com/premierleague25/badges-alt/6.svg'),
('Chelsea', 'Stamford Bridge', 'Todd Boehly & Clearlake Capital','https://resources.premierleague.com/premierleague25/badges-alt/8.svg'),
('Brentford', 'Gtech Community Stadium', 'Matthew Benham','https://resources.premierleague.com/premierleague25/badges-alt/94.svg'),
('Burnley', 'Turf Moor', 'Alan Pace','https://resources.premierleague.com/premierleague25/badges-alt/90.svg'),
('Everton', 'Goodison Oark', 'Dan Friedkin','https://resources.premierleague.com/premierleague25/badges-alt/11.svg'),
('Sunderland', 'Stadium of Light', 'Kyril Louis-Dreyfus','https://resources.premierleague.com/premierleague25/badges-alt/56.svg'),
('Crystal Palace', 'Selhurst Park', 'John Textor; David S. Blitzer; Josh Harris; Steve Parish','https://resources.premierleague.com/premierleague25/badges-alt/31.svg'),
('Newcastle United', 'St James Park', 'Saudi Public Investment Fund','https://resources.premierleague.com/premierleague25/badges-alt/4.svg'),
('Nottingham Forest', 'City Ground', 'Evangelos Marinakis','https://resources.premierleague.com/premierleague25/badges-alt/17.svg'),
('Bournemouth', 'Vitality Stadium', 'Maxim Demin','https://resources.premierleague.com/premierleague25/badges-alt/91.svg'),
('Fulham', 'Craven Cottage', 'Shahid Khan','https://resources.premierleague.com/premierleague25/badges-alt/54.svg'),
('Brighton & Hove Albion', 'American Express Community Stadium', 'Tony Bloom','https://resources.premierleague.com/premierleague25/badges-alt/36.svg'),
('Wolverhampton Wanderers', 'Molineux Stadium', 'Guo Guangchang, Liang Xinjun, Wang Qunbin','https://resources.premierleague.com/premierleague25/badges-alt/39.svg'),
('Aston Villa', 'Villa Park', 'Nassef Sawiris & Wes Edens','https://resources.premierleague.com/premierleague25/badges-alt/7.svg'),
('Leeds United', 'Elland Road', '49ers Enterprises Global Football Group LLC','https://resources.premierleague.com/premierleague25/badges-alt/2.svg');
DROP TABLE IF EXISTS pl_matches;

CREATE TABLE pl_matches (
    match_id        INTEGER PRIMARY KEY,
    season          VARCHAR(9) NOT NULL,
    matchweek       INTEGER,
    match_date      DATE NOT NULL,
    kickoff_time    VARCHAR(10),
    home_team       VARCHAR(100) NOT NULL,
    away_team       VARCHAR(100) NOT NULL,
    home_score      INTEGER,
    away_score      INTEGER,
    venue           VARCHAR(150),
    source_url      TEXT,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_pl_matches_date ON pl_matches(match_date);
CREATE INDEX IF NOT EXISTS idx_pl_matches_teams ON pl_matches(home_team, away_team);

-- Note: source_url points to the fixture/results page used to construct these rows.

-- INSERT rows (match_id sequential)
INSERT INTO pl_matches(match_id, season, matchweek, match_date, kickoff_time, home_team, away_team, home_score, away_score, venue, source_url) VALUES
(1, '2025/26', 1, '2025-08-15', '20:00', 'Liverpool', 'Bournemouth', 4, 2, 'Anfield', 'https://fixturedownload.com/results/epl-2025'),
(2, '2025/26', 1, '2025-08-16', '12:30', 'Aston Villa', 'Newcastle United', 0, 0, 'Villa Park', 'https://fixturedownload.com/results/epl-2025'),
(3, '2025/26', 1, '2025-08-16', '15:00', 'Brighton & Hove Albion', 'Fulham', 1, 1, 'American Express Stadium', 'https://fixturedownload.com/results/epl-2025'),
(4, '2025/26', 1, '2025-08-16', '15:00', 'Sunderland', 'West Ham United', 3, 0, 'Stadium of Light', 'https://fixturedownload.com/results/epl-2025'),
(5, '2025/26', 1, '2025-08-16', '15:00', 'Tottenham Hotspur', 'Burnley', 3, 0, 'Tottenham Hotspur Stadium', 'https://fixturedownload.com/results/epl-2025'),
(6, '2025/26', 1, '2025-08-16', '15:00', 'Wolverhampton Wanderers', 'Manchester City', 0, 4, 'Molineux Stadium', 'https://fixturedownload.com/results/epl-2025'),
(7, '2025/26', 1, '2025-08-17', '14:00', 'Nottingham Forest', 'Brentford', 3, 1, 'The City Ground', 'https://fixturedownload.com/results/epl-2025'),
(8, '2025/26', 1, '2025-08-17', '14:00', 'Chelsea', 'Crystal Palace', 0, 0, 'Stamford Bridge', 'https://fixturedownload.com/results/epl-2025'),
(9, '2025/26', 1, '2025-08-17', '16:30', 'Manchester United', 'Arsenal', 0, 1, 'Old Trafford', 'https://fixturedownload.com/results/epl-2025'),
(10, '2025/26', 1, '2025-08-18', '20:00', 'Leeds United', 'Everton', 1, 0, 'Elland Road', 'https://fixturedownload.com/results/epl-2025'),

(11, '2025/26', 2, '2025-08-22', '20:00', 'West Ham United', 'Chelsea', 1, 5, 'London Stadium', 'https://fixturedownload.com/results/epl-2025'),
(12, '2025/26', 2, '2025-08-23', '12:30', 'Manchester City', 'Tottenham Hotspur', 0, 2, 'Etihad Stadium', 'https://fixturedownload.com/results/epl-2025'),
(13, '2025/26', 2, '2025-08-23', '15:00', 'Bournemouth', 'Wolverhampton Wanderers', 1, 0, 'Vitality Stadium', 'https://fixturedownload.com/results/epl-2025'),
(14, '2025/26', 2, '2025-08-23', '15:00', 'Brentford', 'Aston Villa', 1, 0, 'Gtech Community Stadium', 'https://fixturedownload.com/results/epl-2025'),
(15, '2025/26', 2, '2025-08-23', '15:00', 'Burnley', 'Sunderland', 2, 0, 'Turf Moor', 'https://fixturedownload.com/results/epl-2025'),
(16, '2025/26', 2, '2025-08-23', '17:30', 'Arsenal', 'Leeds United', 5, 0, 'Emirates Stadium', 'https://fixturedownload.com/results/epl-2025'),
(17, '2025/26', 2, '2025-08-24', '14:00', 'Crystal Palace', 'Nottingham Forest', 1, 1, 'Selhurst Park', 'https://fixturedownload.com/results/epl-2025'),
(18, '2025/26', 2, '2025-08-24', '14:00', 'Everton', 'Brighton & Hove Albion', 2, 0, 'Hill Dickinson Stadium', 'https://fixturedownload.com/results/epl-2025'),
(19, '2025/26', 2, '2025-08-24', '16:30', 'Fulham', 'Manchester United', 1, 1, 'Craven Cottage', 'https://fixturedownload.com/results/epl-2025'),
(20, '2025/26', 2, '2025-08-25', '20:00', 'Newcastle United', 'Liverpool', 2, 3, 'St. James'' Park', 'https://fixturedownload.com/results/epl-2025'),

(21, '2025/26', 3, '2025-08-30', '12:30', 'Chelsea', 'Fulham', 2, 0, 'Stamford Bridge', 'https://fixturedownload.com/results/epl-2025'),
(22, '2025/26', 3, '2025-08-30', '15:00', 'Manchester United', 'Burnley', 3, 2, 'Old Trafford', 'https://fixturedownload.com/results/epl-2025'),
(23, '2025/26', 3, '2025-08-30', '15:00', 'Sunderland', 'Brentford', 2, 1, 'Stadium of Light', 'https://fixturedownload.com/results/epl-2025'),
(24, '2025/26', 3, '2025-08-30', '15:00', 'Tottenham Hotspur', 'Bournemouth', 0, 1, 'Tottenham Hotspur Stadium', 'https://fixturedownload.com/results/epl-2025'),
(25, '2025/26', 3, '2025-08-30', '15:00', 'Wolverhampton Wanderers', 'Everton', 2, 3, 'Molineux Stadium', 'https://fixturedownload.com/results/epl-2025'),
(26, '2025/26', 3, '2025-08-30', '17:30', 'Leeds United', 'Newcastle United', 0, 0, 'Elland Road', 'https://fixturedownload.com/results/epl-2025'),
(27, '2025/26', 3, '2025-08-31', '14:00', 'Brighton & Hove Albion', 'Manchester City', 2, 1, 'American Express Stadium', 'https://fixturedownload.com/results/epl-2025'),
(28, '2025/26', 3, '2025-08-31', '14:00', 'Nottingham Forest', 'West Ham United', 0, 3, 'The City Ground', 'https://fixturedownload.com/results/epl-2025'),
(29, '2025/26', 3, '2025-08-31', '16:30', 'Liverpool', 'Arsenal', 1, 0, 'Anfield', 'https://fixturedownload.com/results/epl-2025'),
(30, '2025/26', 3, '2025-08-31', '19:00', 'Aston Villa', 'Crystal Palace', 0, 3, 'Villa Park', 'https://fixturedownload.com/results/epl-2025'),

(31, '2025/26', 4, '2025-09-13', '12:30', 'Arsenal', 'Nottingham Forest', 3, 0, 'Emirates Stadium', 'https://fixturedownload.com/results/epl-2025'),
(32, '2025/26', 4, '2025-09-13', '15:00', 'Bournemouth', 'Brighton & Hove Albion', 2, 1, 'Vitality Stadium', 'https://fixturedownload.com/results/epl-2025'),
(33, '2025/26', 4, '2025-09-13', '15:00', 'Crystal Palace', 'Sunderland', 0, 0, 'Selhurst Park', 'https://fixturedownload.com/results/epl-2025'),
(34, '2025/26', 4, '2025-09-13', '15:00', 'Everton', 'Aston Villa', 0, 0, 'Hill Dickinson Stadium', 'https://fixturedownload.com/results/epl-2025'),
(35, '2025/26', 4, '2025-09-13', '15:00', 'Fulham', 'Leeds United', 1, 0, 'Craven Cottage', 'https://fixturedownload.com/results/epl-2025'),
(36, '2025/26', 4, '2025-09-13', '15:00', 'Newcastle United', 'Wolverhampton Wanderers', 1, 0, 'St. James'' Park', 'https://fixturedownload.com/results/epl-2025'),
(37, '2025/26', 4, '2025-09-13', '17:30', 'West Ham United', 'Tottenham Hotspur', 0, 3, 'London Stadium', 'https://fixturedownload.com/results/epl-2025'),
(38, '2025/26', 4, '2025-09-13', '20:00', 'Brentford', 'Chelsea', 2, 2, 'Gtech Community Stadium', 'https://fixturedownload.com/results/epl-2025'),
(39, '2025/26', 4, '2025-09-14', '14:00', 'Burnley', 'Liverpool', 0, 1, 'Turf Moor', 'https://fixturedownload.com/results/epl-2025'),
(40, '2025/26', 4, '2025-09-14', '16:30', 'Manchester City', 'Manchester United', 3, 0, 'Etihad Stadium', 'https://fixturedownload.com/results/epl-2025'),

(41, '2025/26', 5, '2025-09-20', '12:30', 'Liverpool', 'Everton', 2, 1, 'Anfield', 'https://fixturedownload.com/results/epl-2025'),
(42, '2025/26', 5, '2025-09-20', '15:00', 'Brighton & Hove Albion', 'Tottenham Hotspur', 2, 2, 'American Express Stadium', 'https://fixturedownload.com/results/epl-2025'),
(43, '2025/26', 5, '2025-09-20', '15:00', 'Burnley', 'Nottingham Forest', 1, 1, 'Turf Moor', 'https://fixturedownload.com/results/epl-2025'),
(44, '2025/26', 5, '2025-09-20', '15:00', 'West Ham United', 'Crystal Palace', 1, 2, 'London Stadium', 'https://fixturedownload.com/results/epl-2025'),
(45, '2025/26', 5, '2025-09-20', '15:00', 'Wolverhampton Wanderers', 'Leeds United', 1, 3, 'Molineux Stadium', 'https://fixturedownload.com/results/epl-2025'),
(46, '2025/26', 5, '2025-09-20', '17:30', 'Manchester United', 'Chelsea', 2, 1, 'Old Trafford', 'https://fixturedownload.com/results/epl-2025'),
(47, '2025/26', 5, '2025-09-20', '20:00', 'Fulham', 'Brentford', 3, 1, 'Craven Cottage', 'https://fixturedownload.com/results/epl-2025'),
(48, '2025/26', 5, '2025-09-21', '14:00', 'Bournemouth', 'Newcastle United', 0, 0, 'Vitality Stadium', 'https://fixturedownload.com/results/epl-2025'),
(49, '2025/26', 5, '2025-09-21', '14:00', 'Sunderland', 'Aston Villa', 1, 1, 'Stadium of Light', 'https://fixturedownload.com/results/epl-2025'),
(50, '2025/26', 5, '2025-09-21', '16:30', 'Arsenal', 'Manchester City', 1, 1, 'Emirates Stadium', 'https://fixturedownload.com/results/epl-2025'),

(51, '2025/26', 6, '2025-09-27', '12:30', 'Brentford', 'Manchester United', 3, 1, 'Gtech Community Stadium', 'https://fixturedownload.com/results/epl-2025'),
(52, '2025/26', 6, '2025-09-27', '15:00', 'Chelsea', 'Brighton & Hove Albion', 1, 3, 'Stamford Bridge', 'https://fixturedownload.com/results/epl-2025'),
(53, '2025/26', 6, '2025-09-27', '15:00', 'Crystal Palace', 'Liverpool', 2, 1, 'Selhurst Park', 'https://fixturedownload.com/results/epl-2025'),
(54, '2025/26', 6, '2025-09-27', '15:00', 'Leeds United', 'Bournemouth', 2, 2, 'Elland Road', 'https://fixturedownload.com/results/epl-2025'),
(55, '2025/26', 6, '2025-09-27', '15:00', 'Manchester City', 'Burnley', 5, 1, 'Etihad Stadium', 'https://fixturedownload.com/results/epl-2025'),
(56, '2025/26', 6, '2025-09-27', '17:30', 'Nottingham Forest', 'Sunderland', 0, 1, 'The City Ground', 'https://fixturedownload.com/results/epl-2025'),
(57, '2025/26', 6, '2025-09-27', '20:00', 'Tottenham Hotspur', 'Wolverhampton Wanderers', 1, 1, 'Tottenham Hotspur Stadium', 'https://fixturedownload.com/results/epl-2025'),
(58, '2025/26', 6, '2025-09-28', '14:00', 'Aston Villa', 'Fulham', 3, 1, 'Villa Park', 'https://fixturedownload.com/results/epl-2025'),
(59, '2025/26', 6, '2025-09-28', '16:30', 'Newcastle United', 'Arsenal', 1, 2, 'St. James'' Park', 'https://fixturedownload.com/results/epl-2025'),
(60, '2025/26', 6, '2025-09-29', '20:00', 'Everton', 'West Ham United', 1, 1, 'Hill Dickinson Stadium', 'https://fixturedownload.com/results/epl-2025'),

(61, '2025/26', 7, '2025-10-03', '20:00', 'Bournemouth', 'Fulham', 3, 1, 'Vitality Stadium', 'https://fixturedownload.com/results/epl-2025'),
(62, '2025/26', 7, '2025-10-04', '12:30', 'Leeds United', 'Tottenham Hotspur', 1, 2, 'Elland Road', 'https://fixturedownload.com/results/epl-2025'),
(63, '2025/26', 7, '2025-10-04', '15:00', 'Arsenal', 'West Ham United', 2, 0, 'Emirates Stadium', 'https://fixturedownload.com/results/epl-2025'),
(64, '2025/26', 7, '2025-10-04', '15:00', 'Manchester United', 'Sunderland', 2, 0, 'Old Trafford', 'https://fixturedownload.com/results/epl-2025'),
(65, '2025/26', 7, '2025-10-04', '17:30', 'Chelsea', 'Liverpool', 2, 1, 'Stamford Bridge', 'https://fixturedownload.com/results/epl-2025'),
(66, '2025/26', 7, '2025-10-05', '14:00', 'Aston Villa', 'Burnley', 2, 1, 'Villa Park', 'https://fixturedownload.com/results/epl-2025'),
(67, '2025/26', 7, '2025-10-05', '14:00', 'Everton', 'Crystal Palace', 2, 1, 'Hill Dickinson Stadium', 'https://fixturedownload.com/results/epl-2025'),
(68, '2025/26', 7, '2025-10-05', '14:00', 'Newcastle United', 'Nottingham Forest', 2, 0, 'St. James'' Park', 'https://fixturedownload.com/results/epl-2025'),
(69, '2025/26', 7, '2025-10-05', '14:00', 'Wolverhampton Wanderers', 'Brighton & Hove Albion', 1, 1, 'Molineux Stadium', 'https://fixturedownload.com/results/epl-2025'),
(70, '2025/26', 7, '2025-10-05', '16:30', 'Brentford', 'Manchester City', 0, 1, 'Gtech Community Stadium', 'https://fixturedownload.com/results/epl-2025')
;
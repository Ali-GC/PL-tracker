import requests
# SOAP request URL

venue_dictionary = {
  "Liverpool": "Anfield",
  "Manchester United": "Old Trafford",
  "Manchester City": "Etihad Stadium",
  "West Ham United": "London Stadium",
  "Arsenal": "Emirates Stadium",
  "Tottenham Hotspur": "Tottenham Hotspur Stadium",
  "Chelsea": "Stamford Bridge",
  "Brentford": "Gtech Community Stadium",
  "Burnley": "Turf Moor",
  "Everton": "Goodison Oark",
  "Sunderland": "Stadium of Light",
  "Crystal Palace": "Selhurst Park",
  "Newcastle United": "St James' Park",
  "Nottingham Forest": "City Ground",
  "Bournemouth": "Vitality Stadium",
  "Fulham": "Craven Cottage",
  "Brighton & Hove Albion": "American Express Community Stadium",
  "Wolverhampton Wanderers": "Molineux Stadium",
  "Aston Villa": "Villa Park",
  "Leeds United": "Elland Road"
}

id_dictionary = {
  "Liverpool": 1,
  "Manchester United": 2,
  "Manchester City": 3,
  "West Ham United": 4,
  "Arsenal": 5,
  "Tottenham Hotspur": 6,
  "Chelsea": 7,
  "Brentford": 8,
  "Burnley": 9,
  "Everton": 10,
  "Sunderland": 11,
  "Crystal Palace": 12,
  "Newcastle United": 13,
  "Nottingham Forest": 14,
  "Bournemouth": 15,
  "Fulham": 16,
  "Brighton & Hove Albion": 17,
  "Wolverhampton Wanderers": 18,
  "Aston Villa": 19,
  "Leeds United": 20
}

url = "http://localhost:8080/ws"

match_date = input("please enter the date: ")
season = "2025/26"
kickoff_date = match_date
match_week = input("please enter the matchweek: ")
home_team = input("please enter the home_team: ")
away_team = input("please enter the away team: ")
home_score = input("please enter the home_score: ")
away_score = input("please enter the away_score: ")
venue = venue_dictionary[home_team]
source_url = "https://www.premierleague.com/en/"
match_id = f"2526{match_week}999{id_dictionary[home_team]}"
# structured XML
payload = f"""<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
				  xmlns:gs="match.pltracker.com">
                    <soapenv:Header/>
                    <soapenv:Body>
                        <gs:createMatchRequest>
                            <gs:matchDetails>
                                    <gs:match_id>{match_id}</gs:match_id>
                                    <gs:season>{season}</gs:season>
                                    <gs:matchweek>{match_week}</gs:matchweek>
                                    <gs:match_date>{match_date}</gs:match_date>
                                    <gs:kickoff_date>{kickoff_date}</gs:kickoff_date>
                                    <gs:homeTeam>{home_team}</gs:homeTeam>
                                    <gs:awayTeam>{away_team}</gs:awayTeam>
                                    <gs:home_score>{home_score}</gs:home_score>
                                    <gs:away_score>{away_score}</gs:away_score>
                                    <gs:venue>{venue}</gs:venue>
                                    <gs:source_url>{source_url}</gs:source_url>
                                </gs:matchDetails>
                        </gs:createMatchRequest>
                    </soapenv:Body>
            </soapenv:Envelope>"""
# headers
headers = {
    'Content-Type': 'text/xml; charset=utf-8'
}
# POST request
response = requests.request("POST", url, headers=headers, data=payload)

# prints the response
print(response.text)
print(response)
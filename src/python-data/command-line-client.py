import sys
import requests
# SOAP request URL
def add_match():
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
  kickoff_time = input("please enter the kickoff time: ")
  match_week = input("please enter the match week: ")
  home_team = input("please enter the home team: ")
  away_team = input("please enter the away team: ")
  home_score = input("please enter the home_score: ")
  away_score = input("please enter the away_score: ")
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
                                      <gs:match_week>{match_week}</gs:match_week>
                                      <gs:match_date>{match_date}</gs:match_date>
                                      <gs:kickoff_time>{kickoff_time}</gs:kickoff_time>
                                      <gs:homeTeam>{home_team}</gs:homeTeam>
                                      <gs:awayTeam>{away_team}</gs:awayTeam>
                                      <gs:home_score>{home_score}</gs:home_score>
                                      <gs:away_score>{away_score}</gs:away_score>
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

def change_owner():
  team_name = input("enter the teams whose owner needs to be changed: ")
  owner_name = input("enter the name of the new owner: ")
  # structured XML
  url = "http://localhost:8080/ws"
  payload = f"""<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
            xmlns:gs="match.pltracker.com">
                      <soapenv:Header/>
                      <soapenv:Body>
                          <gs:updateOwnerRequest>
                            <gs:teamName>{team_name}</gs:teamName>
                            <gs:ownerName>{owner_name}</gs:ownerName>
                          </gs:updateOwnerRequest>
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

if len(sys.argv) == 2:
  if sys.argv[1] == "update-owner":
    change_owner()
else:
  add_match()
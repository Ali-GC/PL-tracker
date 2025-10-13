package com.alicode.pltracker.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.alicode.pltracker.models.Match;
import com.alicode.pltracker.service.MatchSoapService;
import com.pltracker.match.GetMatchRequest;
import com.pltracker.match.GetMatchResponse;
import com.pltracker.match.MatchDetails;

@Endpoint
public class MatchEndpoint {

    @Autowired
    MatchSoapService service;
    
    @PayloadRoot(namespace="match.pltracker.com", localPart="getMatchRequest")
    @ResponsePayload
    public GetMatchResponse getMatchRequest(@RequestPayload GetMatchRequest request){
        String team = request.getHomeTeam();
        List<Match> matches = service.getMatchesByTeam(team, team);
        return mapMatches(matches);
    }

    private GetMatchResponse mapMatches(List<Match> matches) {
        GetMatchResponse response = new GetMatchResponse();
        for(Match match: matches){
            MatchDetails matchDetails = new MatchDetails();
            matchDetails.setMatchId(match.getMatchId());
            matchDetails.setSeason(match.getSeason());
            matchDetails.setMatchweek(match.getMatchweek());
            matchDetails.setMatchDate(match.getMatchDate());
            matchDetails.setKickoffDate(match.getKickoffDate());
            matchDetails.setHomeTeam(match.getHomeTeam());
            matchDetails.setAwayTeam(match.getAwayTeam());
            matchDetails.setHomeScore(match.getHomeScore());
            matchDetails.setAwayScore(match.getAwayScore());
            matchDetails.setVenue(match.getVenue());
            matchDetails.setSourceUrl(match.getSourceUrl());
            response.getMatchDetails().add(matchDetails);
        }
        return response;
    }
}



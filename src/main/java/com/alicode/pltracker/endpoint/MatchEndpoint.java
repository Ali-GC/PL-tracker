package com.alicode.pltracker.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.alicode.pltracker.models.Match;
import com.alicode.pltracker.service.MatchService;
import com.pltracker.match.CreateMatchRequest;
import com.pltracker.match.CreateMatchResponse;
import com.pltracker.match.GetMatchRequest;
import com.pltracker.match.GetMatchResponse;
import com.pltracker.match.MatchDetails;

@Endpoint
public class MatchEndpoint {

    @Autowired
    MatchService service;
    
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

    @PayloadRoot(namespace="match.pltracker.com", localPart="createMatchRequest")
    @ResponsePayload
    public CreateMatchResponse createMatchRequest(@RequestPayload CreateMatchRequest request){
        MatchDetails matchDetails = request.getMatchDetails();
        Match match = mapMatchDetails(matchDetails);
        Match newMatch = service.create(match);
        return new CreateMatchResponse();
    }

    private Match mapMatchDetails(MatchDetails matchDetails) {
        Match match = new Match();
        match.setMatchId(matchDetails.getMatchId());
        match.setSeason(matchDetails.getSeason());
        match.setMatchweek(matchDetails.getMatchweek());
        match.setMatchDate(matchDetails.getMatchDate());
        match.setKickoffDate(matchDetails.getKickoffDate());
        match.setHomeTeam(matchDetails.getHomeTeam());
        match.setAwayTeam(matchDetails.getAwayTeam());
        match.setHomeScore(matchDetails.getHomeScore());
        match.setAwayScore(matchDetails.getAwayScore());
        match.setVenue(matchDetails.getVenue());
        match.setSourceUrl(matchDetails.getSourceUrl());
        return match;
    }
}




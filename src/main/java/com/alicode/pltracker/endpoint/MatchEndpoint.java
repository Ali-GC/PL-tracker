package com.alicode.pltracker.endpoint;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.alicode.exceptions.InvalidRequestException;
import com.alicode.pltracker.models.Match;
import com.alicode.pltracker.service.MatchService;
import com.pltracker.match.CreateMatchRequest;
import com.pltracker.match.CreateMatchResponse;
import com.pltracker.match.GetAllMatchesRequest;
import com.pltracker.match.GetAllMatchesResponse;
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
    
    @PayloadRoot(namespace="match.pltracker.com", localPart="getAllMatchesRequest")
    @ResponsePayload
    public GetAllMatchesResponse getAllMatchesRequest(@RequestPayload GetAllMatchesRequest request){
        List<Match> matches = service.getMatches();
        return mapAllMatches(matches);
    }

    private GetMatchResponse mapMatches(List<Match> matches) {
        GetMatchResponse response = new GetMatchResponse();
        for(Match match: matches){
            MatchDetails matchDetails = new MatchDetails();
            matchDetails.setMatchId(match.getMatchId());
            matchDetails.setSeason(match.getSeason());
            matchDetails.setMatchWeek(match.getMatchWeek());
            matchDetails.setMatchDate(toXMLGregorianCalendar(match.getMatchDate()));
            matchDetails.setKickoffTime(match.getKickoffTime());
            matchDetails.setHomeTeam(match.getHomeTeam());
            matchDetails.setAwayTeam(match.getAwayTeam());
            matchDetails.setHomeScore(match.getHomeScore());
            matchDetails.setAwayScore(match.getAwayScore());
            response.getMatchDetails().add(matchDetails);
        }
        return response;
    }

    private GetAllMatchesResponse mapAllMatches(List<Match> matches) {
        GetAllMatchesResponse response = new GetAllMatchesResponse();
        for(Match match: matches){
            MatchDetails matchDetails = new MatchDetails();
            matchDetails.setMatchId(match.getMatchId());
            matchDetails.setSeason(match.getSeason());
            matchDetails.setMatchWeek(match.getMatchWeek());
            matchDetails.setMatchDate(toXMLGregorianCalendar(match.getMatchDate()));
            matchDetails.setKickoffTime(match.getKickoffTime());
            matchDetails.setHomeTeam(match.getHomeTeam());
            matchDetails.setAwayTeam(match.getAwayTeam());
            matchDetails.setHomeScore(match.getHomeScore());
            matchDetails.setAwayScore(match.getAwayScore());
            response.getMatchDetails().add(matchDetails);
        }
        return response;
    }

    @PayloadRoot(namespace="match.pltracker.com", localPart="createMatchRequest")
    @ResponsePayload
    public CreateMatchResponse createMatchRequest(@RequestPayload CreateMatchRequest request){
        MatchDetails matchDetails = request.getMatchDetails();
        matchDetailValidation(matchDetails);
        Match match = mapMatchDetails(matchDetails);
        Match newMatch = service.create(match);
        return new CreateMatchResponse();
    }

    private Match mapMatchDetails(MatchDetails matchDetails) {
        Match match = new Match();
        match.setMatchId(matchDetails.getMatchId());
        match.setSeason(matchDetails.getSeason());
        match.setMatchWeek(matchDetails.getMatchWeek());
        match.setMatchDate(matchDetails.getMatchDate().toGregorianCalendar().toZonedDateTime().toLocalDate());
        match.setKickoffTime(matchDetails.getKickoffTime());
        match.setHomeTeam(matchDetails.getHomeTeam());
        match.setAwayTeam(matchDetails.getAwayTeam());
        match.setHomeScore(matchDetails.getHomeScore());
        match.setAwayScore(matchDetails.getAwayScore());
        return match;
    }

    private void matchDetailValidation(MatchDetails match) {
        // Basic field validation
        if (match.getHomeTeam() == null || match.getHomeTeam().trim().isEmpty()) {
            throw new InvalidRequestException("Home team cannot be null or empty");
        }

        if (match.getAwayTeam() == null || match.getAwayTeam().trim().isEmpty()) {
            throw new InvalidRequestException("Away team cannot be null or empty");
        }

        if (match.getHomeTeam().equals(match.getAwayTeam())) {
            throw new InvalidRequestException("Home team and away team cannot be the same");
        }

        if (match.getHomeScore() == null) {
            throw new InvalidRequestException("home score is required and must be an integer");
        }
        
        if (match.getAwayScore() == null) {
            throw new InvalidRequestException("away score is required and must be an integer");
        }

        if (match.getMatchDate() == null) {
            throw new InvalidRequestException("match_date must be a valid date (YYYY-MM-DD)");
        }
    }

    private static XMLGregorianCalendar toXMLGregorianCalendar(LocalDate localDate) {
        if (localDate == null) return null;

        try{ 
            GregorianCalendar gcal = GregorianCalendar.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            XMLGregorianCalendar xcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
            return xcal;
        }
        catch (DatatypeConfigurationException e) {
            throw new RuntimeException("Error creating XMLGregorianCalendar", e);
        }
    }
}






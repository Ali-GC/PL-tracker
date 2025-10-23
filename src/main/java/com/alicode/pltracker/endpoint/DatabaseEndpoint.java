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

import com.alicode.pltracker.models.DatabaseResponse;
import com.alicode.pltracker.models.Match;
import com.alicode.pltracker.models.Team;
import com.alicode.pltracker.service.DatabaseService;
import com.pltracker.match.DatabaseResponseType;
import com.pltracker.match.GetAllDataRequest;
import com.pltracker.match.GetAllDataResponse;
import com.pltracker.match.MatchDetails;
import com.pltracker.match.MatchesType;
import com.pltracker.match.TeamDetails;
import com.pltracker.match.TeamsType;

@Endpoint
public class DatabaseEndpoint {
    @Autowired
    DatabaseService service;
    
    @PayloadRoot(namespace="match.pltracker.com", localPart="getAllDataRequest")
    @ResponsePayload
    public GetAllDataResponse getAllDataRequest(@RequestPayload GetAllDataRequest request){
        DatabaseResponse response = service.fetchAllData();
        return mapAllData(response);
    }
    
    private GetAllDataResponse mapAllData(DatabaseResponse data) {
        GetAllDataResponse response = new GetAllDataResponse();
        DatabaseResponseType dataResponse = new DatabaseResponseType();
        dataResponse.setMatches(mapMatches(data.getMatches()));
        dataResponse.setTeam(mapTeams(data.getTeams()));
        response.setDatabaseResponseType(dataResponse);
        return response;
    }

    private MatchesType mapMatches(List<Match> matches) {
        MatchesType response = new MatchesType();
        for(Match match: matches){
            MatchDetails matchDetails = new MatchDetails();
            matchDetails.setMatchId(match.getMatchId());
            matchDetails.setSeason(match.getSeason());
            matchDetails.setMatchWeek(match.getMatchWeek());
            matchDetails.setMatchDate(match.getMatchDate());
            matchDetails.setKickoffTime(match.getKickoffTime());
            matchDetails.setHomeTeam(match.getHomeTeam());
            matchDetails.setAwayTeam(match.getAwayTeam());
            matchDetails.setHomeScore(match.getHomeScore());
            matchDetails.setAwayScore(match.getAwayScore());
            response.getMatch().add(matchDetails);
        }
        return response;
    }

    private TeamsType mapTeams(List<Team> teams) {
        TeamsType response = new TeamsType();
        for(Team team: teams){
            TeamDetails teamDetails = new TeamDetails();
            teamDetails.setTeamId(team.getTeamId());
            teamDetails.setTeamName(team.getTeamName());
            teamDetails.setVenue(team.getVenue());
            teamDetails.setOwnerName(team.getOwnerName());
            teamDetails.setLogoUrl(team.getLogoUrl());
            response.getTeam().add(teamDetails);
        }
        return response;
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
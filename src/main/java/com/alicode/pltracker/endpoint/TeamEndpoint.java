package com.alicode.pltracker.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.alicode.pltracker.models.Team;
import com.alicode.pltracker.service.TeamService;
import com.pltracker.match.GetTeamRequest;
import com.pltracker.match.GetTeamResponse;
import com.pltracker.match.TeamDetails;
import com.pltracker.match.UpdateOwnerRequest;
import com.pltracker.match.UpdateOwnerResponse;

@Endpoint
public class TeamEndpoint {

    @Autowired
    TeamService service;
    
    @PayloadRoot(namespace="match.pltracker.com", localPart="getTeamRequest")
    @ResponsePayload
    public GetTeamResponse getTeamRequest(@RequestPayload GetTeamRequest request){
        String team_name = request.getTeamName();
        Team team = service.getTeamByTeamName(team_name);
        return mapTeam(team);
    }

    @PayloadRoot(namespace="match.pltracker.com", localPart="updateOwnerRequest")
    @ResponsePayload
    public UpdateOwnerResponse updateOwnerRequest(@RequestPayload UpdateOwnerRequest request){
        String team_name = request.getTeamName();
        String owner_name = request.getOwnerName();
        int status = service.updateTeamOwner(owner_name, team_name);
        UpdateOwnerResponse response = new UpdateOwnerResponse();
        response.setStatus(status);
        return response;
    }

    private GetTeamResponse mapTeam(Team team) {
        GetTeamResponse response = new GetTeamResponse();
        TeamDetails teamDetails = new TeamDetails();
        teamDetails.setTeamId(team.getTeamId());
        teamDetails.setTeamName(team.getTeamName());
        teamDetails.setVenue(team.getVenue());
        teamDetails.setOwnerName(team.getOwnerName());
        teamDetails.setLogoUrl(team.getLogoUrl());
        response.setTeamDetails(teamDetails);
        return response;
    }

    // private Team mapTeamDetails(TeamDetails TeamDetails) {
    //     Team Team = new Team();
    //     Team.setTeamId(TeamDetails.getTeamId());
    //     Team.setSeason(TeamDetails.getSeason());
    //     Team.setTeamWeek(TeamDetails.getTeamWeek());
    //     Team.setTeamDate(TeamDetails.getTeamDate());
    //     Team.setKickoffDate(TeamDetails.getKickoffTime());
    //     Team.setHomeTeam(TeamDetails.getHomeTeam());
    //     Team.setAwayTeam(TeamDetails.getAwayTeam());
    //     Team.setHomeScore(TeamDetails.getHomeScore());
    //     Team.setAwayScore(TeamDetails.getAwayScore());
    //     return Team;
    // }
}




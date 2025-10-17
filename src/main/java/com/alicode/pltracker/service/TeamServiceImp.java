package com.alicode.pltracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alicode.pltracker.models.Team;
import com.alicode.pltracker.repository.TeamRepository;

@Component
public class TeamServiceImp implements TeamService {
    private TeamRepository repo;

    @Autowired
    public TeamServiceImp(TeamRepository repo) {
        this.repo = repo;
    }

    @Override
    public Team getTeamByTeamName(String team_name){
        return repo.findByTeamName(team_name);
    }

    @Override
    public int updateTeamOwner(String owner_name, String team_name){
        return repo.updateOwnerByTeamName(owner_name, team_name);
    }
}

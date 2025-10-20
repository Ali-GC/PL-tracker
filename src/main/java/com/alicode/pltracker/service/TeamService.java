package com.alicode.pltracker.service;

import org.springframework.stereotype.Service;

import com.alicode.pltracker.models.Team;

@Service
public interface TeamService {
    Team getTeamByTeamName(String team_name);
    int updateTeamOwner(String owner_name, String team_name);
}

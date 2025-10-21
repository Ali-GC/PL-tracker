package com.alicode.pltracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alicode.pltracker.models.DatabaseResponse;
import com.alicode.pltracker.models.Match;
import com.alicode.pltracker.models.Team;
import com.alicode.pltracker.repository.MatchRepository;
import com.alicode.pltracker.repository.TeamRepository;

@Component
public class DatabaseServiceImp implements DatabaseService {
    private MatchRepository matchRepo;
    private TeamRepository teamRepo;

    @Autowired
    public DatabaseServiceImp(MatchRepository matchRepo,TeamRepository teamRepo) {
        this.matchRepo = matchRepo;
        this.teamRepo = teamRepo;
    }

    @Override
    public DatabaseResponse fetchAllData() {
        List<Match> matches = matchRepo.findAll();
        List<Team> teams = teamRepo.findAll();

        return new DatabaseResponse(matches, teams);
    }
}
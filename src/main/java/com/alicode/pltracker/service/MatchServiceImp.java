package com.alicode.pltracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alicode.pltracker.models.Match;
import com.alicode.pltracker.repository.MatchRepository;

@Component
public class MatchServiceImp implements MatchService {
    private MatchRepository repo;

    @Autowired
    public MatchServiceImp(MatchRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Match> getMatches(){
        return repo.findAll();
    }

    @Override
    public List<Match> getMatchesByTeam(String team_name) {
        return repo.findByhomeTeam(team_name);
    }
}

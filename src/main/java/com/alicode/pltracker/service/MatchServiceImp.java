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
    public List<Match> getMatchesByTeam(String team_name, String away_name) {
        return repo.findByhomeTeamOrAwayTeam(team_name, away_name);
    }

    @Override
    // public Match create(Long match_id, String season, int matchweek, String match_date, String kickoff_date, String homeTeam, String awayTeam, int home_score, int away_score, String venue, String source_url) {
    //     return repo.save(match_id, season, matchweek, match_date, kickoff_date, homeTeam, awayTeam, home_score, away_score, venue, source_url);
    // }
    public Match create(Match match){
        return repo.save(match);
    }
}

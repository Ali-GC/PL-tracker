package com.alicode.pltracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alicode.pltracker.models.Match;

@Service
public interface MatchService {
    List<Match> getMatches();
    List<Match> getMatchesByTeam(String team_name, String away_name);
    // Match create(Long match_id, String season, int matchweek, String match_date, String kickoff_date, String homeTeam, String awayTeam, int home_score, int away_score, String venue, String source_url);
    Match create(Match match);
}

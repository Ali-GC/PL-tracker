package com.alicode.pltracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alicode.pltracker.models.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByhomeTeamOrAwayTeam(String home_team, String away_team);
}
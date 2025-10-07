package com.alicode.pltracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alicode.pltracker.models.Match;

import java.util.Optional;


public interface MatchRepository extends JpaRepository<Match, Long> {
    Optional<Match> findByHome_Team(String home_team);
}
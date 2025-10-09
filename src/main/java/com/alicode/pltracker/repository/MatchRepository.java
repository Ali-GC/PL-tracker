package com.alicode.pltracker.repository;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alicode.pltracker.models.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    //Optional<Match> findByhomeTeam(String home_team);
}
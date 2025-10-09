package com.alicode.pltracker.repository;

import java.util.List;
//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alicode.pltracker.models.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByhomeTeam(@Param("home_team")String home_team);
}
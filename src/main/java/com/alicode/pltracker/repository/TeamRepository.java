package com.alicode.pltracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alicode.pltracker.models.Team;

import jakarta.transaction.Transactional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByTeamName(String teamName);

    @Modifying
    @Transactional 
    @Query("update Team t set t.ownerName = ?1 where t.teamName = ?2")
    int updateOwnerByTeamName(String owner_name, String team_name);
}

package com.alicode.pltracker.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "matches")
public class Match {
    @Id
    private Long match_id;
    private String season;
    private Integer match_week;
    private String match_date;
    private String kickoff_time;
    private String homeTeam;
    private String awayTeam;
    private Integer home_score;
    private Integer away_score;

    public Long getMatchId(){
        return match_id;
    }

    public void setMatchId(long match_id){
        this.match_id = match_id;
    }

    public String getMatchDate(){
        return match_date;
    }

    public void setMatchDate(String match_date){
        this.match_date = match_date;
    }

    public Integer getMatchWeek(){
        return match_week;
    }

    public void setMatchWeek(Integer match_week){
        this.match_week = match_week;
    }

    public String getKickoffTime(){
        return kickoff_time;
    }

    public void setKickoffTime(String kickoff_time){
        this.kickoff_time = kickoff_time;
    }

    public Integer getHomeScore(){
        return home_score;
    }

    public void setHomeScore(Integer home_score){
        this.home_score = home_score;
    }
    
    public Integer getAwayScore(){
        return away_score;
    }

    public void setAwayScore(Integer away_score){
        this.away_score = away_score;
    }
}

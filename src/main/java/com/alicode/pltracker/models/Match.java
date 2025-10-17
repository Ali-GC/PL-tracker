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
    private int match_week;
    private String match_date;
    private String kickoff_time;
    private String homeTeam;
    private String awayTeam;
    private int home_score;
    private int away_score;

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

    public int getMatchWeek(){
        return match_week;
    }

    public void setMatchWeek(int match_week){
        this.match_week = match_week;
    }

    public String getKickoffDate(){
        return match_date;
    }

    public void setKickoffDate(String match_date){
        this.match_date = match_date;
    }

    public int getHomeScore(){
        return home_score;
    }

    public void setHomeScore(int home_score){
        this.home_score = home_score;
    }
    
    public int getAwayScore(){
        return away_score;
    }

    public void setAwayScore(int away_score){
        this.away_score = away_score;
    }
}

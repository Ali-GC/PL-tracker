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
@Table(name = "pl_matches")
public class Match {
    @Id
    private Long match_id;
    private String season;
    private int matchweek;
    private String match_date;
    private String kickoff_date;
    private String homeTeam;
    private String awayTeam;
    private int home_score;
    private int away_score;
    private String venue;
    private String source_url;

    public Long getMatchId(){
        return match_id;
    }

    public void setMatchId(Long match_id){
        this.match_id = match_id;
    }

    public String getMatchDate(){
        return match_date;
    }

    public void setMatchDate(String match_date){
        this.match_date = match_date;
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

    public String getSourceUrl(){
        return source_url;
    }

    public void setSourceUrl(String source_url){
        this.source_url = source_url;
    }
}

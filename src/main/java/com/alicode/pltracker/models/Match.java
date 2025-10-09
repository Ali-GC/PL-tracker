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
    private String home_team;
    private String away_team;
    private int home_score;
    private int away_score;
    private String venue;
    private String source_url;
}

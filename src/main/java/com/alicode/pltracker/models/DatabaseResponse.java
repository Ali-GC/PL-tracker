package com.alicode.pltracker.models;

import java.util.List;

public class DatabaseResponse {
    private List<Match> matches;
    private List<Team> teams;

    public DatabaseResponse(List<Match> matches, List<Team> teams) {
        this.matches = matches;
        this.teams = teams;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public List<Team> getTeams() {
        return teams;
    }
}
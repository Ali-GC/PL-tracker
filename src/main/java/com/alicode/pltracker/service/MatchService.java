package com.alicode.pltracker.service;

import java.util.List;

import com.alicode.pltracker.models.Match;

public interface MatchService {
    List<Match> findAllMatches();
}

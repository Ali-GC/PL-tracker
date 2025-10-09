package com.alicode.pltracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alicode.pltracker.models.Match;

@Service
public interface MatchService {
    List<Match> getMatches();
}

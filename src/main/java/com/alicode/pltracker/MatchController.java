package com.alicode.pltracker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alicode.pltracker.models.Match;
import com.alicode.pltracker.service.MatchService;


@RestController
public class MatchController{
    
    private MatchService service;

    @Autowired
    public MatchController(MatchService service){
        this.service = service;
    }

    @GetMapping("/matches")
    public List<Match> getMatches(){
        return service.getMatches();
    }
    
}
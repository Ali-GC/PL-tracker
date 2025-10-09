package com.alicode.pltracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alicode.pltracker.repository.MatchRepository;
//import com.alicode.pltracker.service.MatchService;


@RestController
public class PltrackerController {

    @Autowired
    MatchRepository repo;

    public PltrackerController(MatchRepository repo){
        this.repo= repo;
    }
    @RequestMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    // @RequestMapping("/matches")
    // public Optional<Match> getMatches(@RequestBody Match match) {
    //     return ""
    // }
    
    
}

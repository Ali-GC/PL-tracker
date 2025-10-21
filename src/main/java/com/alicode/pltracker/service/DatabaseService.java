package com.alicode.pltracker.service; 

import com.alicode.pltracker.models.DatabaseResponse;

import org.springframework.stereotype.Service;

@Service
public interface DatabaseService {

    public DatabaseResponse fetchAllData();
}
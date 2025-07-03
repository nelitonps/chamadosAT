package com.nelitonps.chamadosAT.config;

import com.nelitonps.chamadosAT.services.DBService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @PostConstruct
    public void instanciaDB(){
        this.dbService.instanciaDB();
    }
}

package com.nelitonps.chamadosAT.config;

import com.nelitonps.chamadosAT.services.DBService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String value;

    @PostConstruct
    public boolean instanciaDB(){
        if (value.equals("create")){
            this.dbService.instanciaDB();
        }
        return false;
    }
}

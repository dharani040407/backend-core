package com.codewith.secondapi.service;

//import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.codewith.secondapi.Entities.Authens;
import com.codewith.secondapi.repository.AuthenRepository;

//import io.micrometer.common.lang.NonNull;

@Service
public class AuthenticService {
    @Autowired
    AuthenRepository authu;
  public   Authens hello(@NonNull   Authens athu)
    {
        if(authu.existsByEmail(athu.getEmail()))
        {
            throw  new RuntimeException("email_add already exists");
        }
        
        return authu.save(athu);
    
}
public Authens verfication(Authens athu)
{
    if(authu.existsByEmail(athu.getEmail()))
    {
        Authens A3 = authu.findByEmail(athu.getEmail());

        if(!A3.getPin().equals(athu.getPin()))
        {
            throw new RuntimeException("Wrong pin");
        }

        return A3;
    }

    throw new RuntimeException("Email not found");
}
}


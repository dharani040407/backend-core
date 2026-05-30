package com.codewith.secondapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.lang.NonNull;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewith.secondapi.Entities.Authens;
import com.codewith.secondapi.service.AuthenticService;

@RestController
@RequestMapping("/api/login")
public class AuthenController {
    @Autowired
    AuthenticService authen;
@PostMapping("/signup")
public ResponseEntity<Authens> hello(@RequestBody @NonNull  Authens authi){
return new ResponseEntity<>( authen.hello(authi),HttpStatus.OK);
}
@PostMapping("/verfiy")
public ResponseEntity<Authens> Authentication(@RequestBody Authens Authen)
{
    Authens A2=authen.verfication(Authen);
    return new ResponseEntity<>(A2,HttpStatus.OK);
}
// Add this to AuthenController.java
@ExceptionHandler(RuntimeException.class)
public ResponseEntity<String> handleError(RuntimeException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
}
}

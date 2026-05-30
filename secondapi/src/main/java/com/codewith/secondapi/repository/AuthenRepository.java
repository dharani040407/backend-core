package com.codewith.secondapi.repository;

//import java.util.Optional;

//import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codewith.secondapi.Entities.Authens;
@Repository
public interface AuthenRepository extends JpaRepository<Authens,String>{
    boolean existsByEmail( String email);
    boolean existsBypin(String pin);
    Authens findByEmail(String email);
    Authens findByEmailAndPin(String email,String pin);
}

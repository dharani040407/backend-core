package com.codewith.secondapi.Entities;

import jakarta.persistence.*;
//import lombok.AllArgsConstructor;

@Entity
@Table(name="user_cred")

public class Authens{
   @Id
   @Column(name="email_add")
 public  String email; 
@Column(name="pin")
public String pin;
//getters
public Authens(String email_add,String pin)
{
   this.email=email_add;
   this.pin=pin;

}
public Authens(){}
public void setemail(String email){this.email=email;}
public void setpin(String pin){this.pin=pin;}
public String getEmail(){return email;}
public String  getPin(){return pin;}
}

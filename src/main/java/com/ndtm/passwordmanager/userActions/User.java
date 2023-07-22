package com.ndtm.passwordmanager.userActions;

//import jakarta.persistence.*;

import java.time.LocalDateTime;

// сделать Entity, Table, все дела
//@Entity
//@Table(name = "users")
public class User {

   // @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //@Column(nullable = false)
    private String firstName;

    //@Column(nullable = false)
    private String lastName;

    //@Column(nullable = false, unique = true)
    private byte[] login;

   // @Column(nullable = false)
    private byte[] password;

    //@Column(nullable = false)
    private LocalDateTime registerDate;

    public User(String firstName, String lastName, byte[] login, byte[] password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        registerDate = LocalDateTime.now();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public byte[] getLogin() {
        return login;
    }

    public void setLogin(byte[] login) {
        this.login = login;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }
}

package com.ndtm.passwordmanager.userActions;

import javax.persistence.*;

/** TODO:
 */

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false )
    private String lastName;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private byte[] email;


    public User(String firstName, String lastName, String login, String password, byte[] email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getEmail() {
        return email;
    }

    public void setEmail(byte[] email) {
        this.email = email;
    }
}

package com.ndtm.passwordmanager.manage;

import jakarta.persistence.*;

/** TODO:
 * 1. измнеить название класса, сейчас оно не отражает намерения класса
 * 2. мб пассворд будет стрингом. Когда напишу метод хеширования - возможно изменю
 */
@Entity
@Table(name = "passwords")
public class WebSite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private byte[] login;

    @Column(nullable = false)
    private byte[] password;

    public WebSite(String title, String url, byte[] login, byte[] password) {
        this.title = title;
        this.url = url;
        this.login = login;
        this.password = password;
    }
}

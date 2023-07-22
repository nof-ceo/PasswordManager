package com.ndtm.passwordmanager.manage;

// измнеить название, сейчас оно не отражает намерения класса

//import jakarta.persistence.*;

//@Entity
//@Table(name = "passwords")
public class WebSite {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

   // @Column(nullable = false)
    private String title;

    //@Column(nullable = false)
    private String url;

   // @Column(nullable = false)
    private byte[] login;

    //@Column(nullable = false)
    private byte[] password;

    // мб пассворд будет стрингом. Когда напишу метод хеширования - возможно изменю
    public WebSite(String title, String url, byte[] password) {
        this.title = title;
        this.url = url;
        this.password = password;
    }
}

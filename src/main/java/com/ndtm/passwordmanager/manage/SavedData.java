package com.ndtm.passwordmanager.manage;

import jakarta.persistence.*;

/** TODO:
 * 1. измнеить название класса, сейчас оно не отражает намерения класса
 * 2. мб пассворд будет стрингом. Когда напишу метод хеширования - возможно изменю
 * 3. creditCard, cvv, expiredDate, phoneNumber - будут в хешированном виде
 */
@Entity
@Table(name = "savedData")
public class SavedData {

    @Column(name = "user_id")
    private long userId;

    @Column(name = "site_title", nullable = false)
    private String siteTitle;

    @Column(name = "site_url", nullable = false)
    private String siteUrl;

    @Column(nullable = false)
    private byte[] login;

    @Column(nullable = false)
    private byte[] password;

    @Column(name = "credit_card", nullable = true)
    private byte[] creditCard;

    @Column(name = "expired_date", nullable = true)
    private byte[] expiredDate;

    @Column(name = "cvv", nullable = true)
    private byte[] cvv;

    @Column(name = "phone_number", nullable = true)
    private byte[] phoneNumber;

    public SavedData(String siteTitle, String siteUrl, byte[] login, byte[] password, byte[] creditCard, byte[] expiredDate, byte[] cvv, byte[] phoneNumber) {
        this.siteTitle = siteTitle;
        this.siteUrl = siteUrl;
        this.login = login;
        this.password = password;
        this.creditCard = creditCard;
        this.expiredDate = expiredDate;
        this.cvv = cvv;
        this.password = phoneNumber;
    }
}

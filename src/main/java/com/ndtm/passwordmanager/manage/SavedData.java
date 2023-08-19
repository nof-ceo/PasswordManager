package com.ndtm.passwordmanager.manage;

import javax.persistence.*;

/** TODO:
 * 1. измнеить название класса, сейчас оно не отражает намерения класса
 * 2. мб пассворд будет стрингом. Когда напишу метод хеширования - возможно изменю
 * 3. creditCard, cvv, expiredDate, phoneNumber - будут в хешированном виде
 */
@Entity
@Table(schema = "savedData")
public class SavedData {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
    private int id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "site_title", nullable = false)
    private String siteTitle;

    @Column(name = "site_url", nullable = false)
    private String siteUrl;

    @Column(nullable = false)
    private String login;

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

    public SavedData(String siteTitle, String siteUrl, String login, byte[] password, byte[] creditCard, byte[] expiredDate, byte[] cvv, byte[] phoneNumber) {
        this.siteTitle = siteTitle;
        this.siteUrl = siteUrl;
        this.login = login;
        this.password = password;
        this.creditCard = creditCard;
        this.expiredDate = expiredDate;
        this.cvv = cvv;
        this.phoneNumber = phoneNumber;
    }

    public SavedData() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSiteTitle() {
        return siteTitle;
    }

    public void setSiteTitle(String siteTitle) {
        this.siteTitle = siteTitle;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public byte[] getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(byte[] creditCard) {
        this.creditCard = creditCard;
    }

    public byte[] getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(byte[] expiredDate) {
        this.expiredDate = expiredDate;
    }

    public byte[] getCvv() {
        return cvv;
    }

    public void setCvv(byte[] cvv) {
        this.cvv = cvv;
    }

    public byte[] getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(byte[] phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

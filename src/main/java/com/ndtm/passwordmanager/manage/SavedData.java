package com.ndtm.passwordmanager.manage;

import com.ndtm.passwordmanager.userActions.UserService;

import javax.persistence.*;

/** TODO:
 * 1. creditCard, cvv, expiredDate, phoneNumber - будут в хешированном виде
 * 2. password должен быть закодирован, а не захеширован
 */
@Entity
@Table(name = "saved_data")
public class SavedData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "saved_data_id")
    private int id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "site_title", nullable = false)
    private String siteTitle;

    @Column(name = "site_url", nullable = true)
    private String siteUrl;

    @Column(nullable = true)
    private String login;

    @Column(nullable = true)
    private String password;

    @Column(name = "credit_card", nullable = true)
    private String  creditCard;

    @Column(name = "expired_date", nullable = true)
    private String expiredDate;

    @Column(name = "cvv", nullable = true)
    private String cvv;

    @Column(name = "phone_number", nullable = true)
    private String phoneNumber;

    // изменить на false
    @Column(name = "key_encryption", nullable = false)
    private String key;

    @Column(name = "init_vector", nullable = false)
    private byte[] initVector;

    public SavedData(String siteTitle, String siteUrl, String login, String password, String creditCard, String expiredDate, String cvv, String phoneNumber) {
        this.siteTitle = siteTitle;
        this.siteUrl = siteUrl;
        this.login = login;
        this.password = password;
        this.creditCard = creditCard;
        this.expiredDate = expiredDate;
        this.cvv = cvv;
        this.phoneNumber = phoneNumber;
        this.userId = UserService.currentActiveUser.getId();
    }

    public SavedData() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public byte[] getInitVector() {
        return initVector;
    }

    public void setInitVector(byte[] initVector) {
        this.initVector = initVector;
    }

    @Override
    public boolean equals(Object savedData) {
        if(savedData.getClass() != this.getClass())
            return false;
        else {
            SavedData data = (SavedData) savedData;

            return ((this.userId == data.userId) && (this.siteTitle.equals(data.siteTitle)) &&
                    (this.siteUrl.equals(data.siteUrl)) && (this.login.equals(data.login)) &&
                    (this.password.equals(data.password)) && (this.creditCard == data.creditCard) &&
                    (this.expiredDate == data.expiredDate) && (this.cvv == data.cvv) &&
                    (this.phoneNumber == data.phoneNumber));
        }
    }

    @Override
    public int hashCode() {
        return siteTitle.hashCode() + siteUrl.hashCode() + userId;
    }

}

package com.gym.gymclient.Entity;

public class GymClientEntity {
    private Long clientID;
    private String clientName;
    private String mail;
    private String phone;
    private String subscriptionType;
    
    public GymClientEntity() {}

    public GymClientEntity(String clientName, String mail, String phone, String subscriptionType) {
        this.clientName = clientName;
        this.mail = mail;
        this.phone = phone;
        this.subscriptionType = subscriptionType;
    }
        public GymClientEntity(Long clientID, String clientName, String mail, String phone, String subscriptionType) {
        this.clientID = clientID;
        this.clientName = clientName;
        this.mail = mail;
        this.phone = phone;
        this.subscriptionType = subscriptionType;
    }
    
    public Long getClientID() { return clientID; }
    public void setClientID(Long clientID) { this.clientID = clientID; }
    
    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getSubscriptionType() { return subscriptionType; }
    public void setSubscriptionType(String subscriptionType) { this.subscriptionType = subscriptionType; }
}

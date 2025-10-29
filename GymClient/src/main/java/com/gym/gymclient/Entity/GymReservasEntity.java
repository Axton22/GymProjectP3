package com.gym.gymclient.Entity;

public class GymReservasEntity {
    private Long reservaID;
    private String clientName;
    private String mail;
    private String phone;
    private String reservaType;
    
    public GymReservasEntity() {}

    public GymReservasEntity(String clientName, String mail, String phone, String reservaType) {
        this.clientName = clientName;
        this.mail = mail;
        this.phone = phone;
        this.reservaType = reservaType;
    }

    public GymReservasEntity(Long reservaID, String clientName, String mail, String phone, String reservaType) {
        this.reservaID = reservaID;
        this.clientName = clientName;
        this.mail = mail;
        this.phone = phone;
        this.reservaType = reservaType;
    }
    
    public Long getReservaID() { return reservaID; }
    public void setReservaID(Long reservaID) { this.reservaID = reservaID; }
    
    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getReservaType() { return reservaType; }
    public void setReservaType(String reservaType) { this.reservaType = reservaType; }
}


package com.gym.gymclient.Entity;


public class GymRutinaEntity {
    private Long rutinaid;
    private String clientName;
    private String mail;
    private String phone;
    private String rutinatype;
    
    public GymRutinaEntity() {}

    public GymRutinaEntity(String clientName, String mail, String phone, String rutinatype) {
        this.clientName = clientName;
        this.mail = mail;
        this.phone = phone;
        this.rutinatype = rutinatype;
    }
        public GymRutinaEntity(Long rutinaid, String clientName, String mail, String phone, String rutinatype) {
        this.rutinaid = rutinaid;
        this.clientName = clientName;
        this.mail = mail;
        this.phone = phone;
        this.rutinatype = rutinatype;
    }
    
    public Long getClientID() { return rutinaid; }
    public void setClientID(Long clientID) { this.rutinaid = clientID; }
    
    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getRutinaType() { return rutinatype; }
    public void setgetRutinaType(String subscriptionType) { this.rutinatype = rutinatype; }
}

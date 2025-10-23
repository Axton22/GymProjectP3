/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GymServer.API.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author Axton Urbina
 */
@Entity
@Table(name = "gymcliententity")
@NamedQueries({
    @NamedQuery(name = "GymClientEntity.findAll", query = "SELECT g FROM GymClientEntity g"),
    @NamedQuery(name = "GymClientEntity.findByClientID", query = "SELECT g FROM GymClientEntity g WHERE g.clientID = :clientID"),
    @NamedQuery(name = "GymClientEntity.findByClientName", query = "SELECT g FROM GymClientEntity g WHERE g.clientName = :clientName"),
    @NamedQuery(name = "GymClientEntity.findByMail", query = "SELECT g FROM GymClientEntity g WHERE g.mail = :mail"),
    @NamedQuery(name = "GymClientEntity.findByPhone", query = "SELECT g FROM GymClientEntity g WHERE g.phone = :phone"),
    @NamedQuery(name = "GymClientEntity.findBySubscriptionType", query = "SELECT g FROM GymClientEntity g WHERE g.subscriptionType = :subscriptionType")})
public class GymClientEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "clientID")
    private Integer clientID;
    @Column(name = "clientName")
    private String clientName;
    @Column(name = "mail")
    private String mail;
    @Column(name = "phone")
    private String phone;
    @Column(name = "SubscriptionType")
    private String subscriptionType;

    public GymClientEntity() {
    }

    public GymClientEntity(Integer clientID) {
        this.clientID = clientID;
    }

    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clientID != null ? clientID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GymClientEntity)) {
            return false;
        }
        GymClientEntity other = (GymClientEntity) object;
        if ((this.clientID == null && other.clientID != null) || (this.clientID != null && !this.clientID.equals(other.clientID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.GymServer.API.entities.GymClientEntity[ clientID=" + clientID + " ]";
    }
    
}

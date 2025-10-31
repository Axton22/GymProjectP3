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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Axton Urbina
 */
@Entity
@Table(name = "reservationentity")
@NamedQueries({
    @NamedQuery(name = "ReservationEntity.findAll", query = "SELECT r FROM ReservationEntity r"),
    @NamedQuery(name = "ReservationEntity.findByReservationID", query = "SELECT r FROM ReservationEntity r WHERE r.reservationID = :reservationID"),
    @NamedQuery(name = "ReservationEntity.findByClientID", query = "SELECT r FROM ReservationEntity r WHERE r.clientID = :clientID"),
    @NamedQuery(name = "ReservationEntity.findByRoutineID", query = "SELECT r FROM ReservationEntity r WHERE r.routineID = :routineID"),
    @NamedQuery(name = "ReservationEntity.findByReservationDate", query = "SELECT r FROM ReservationEntity r WHERE r.reservationDate = :reservationDate"),
    @NamedQuery(name = "ReservationEntity.findByReservationHour", query = "SELECT r FROM ReservationEntity r WHERE r.reservationHour = :reservationHour"),
    @NamedQuery(name = "ReservationEntity.findByState", query = "SELECT r FROM ReservationEntity r WHERE r.state = :state")})
public class ReservationEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "reservationID")
    private Integer reservationID;
    @Column(name = "clntID")
    private Integer clientID;
    @Column(name = "routID")
    private Integer routineID;
    @Column(name = "reservationDate")
    @Temporal(TemporalType.DATE)
    private Date reservationDate;
    @Column(name = "reservationHour")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reservationHour;
    @Column(name = "state")
    private Boolean state;

    public ReservationEntity() {
    }

    public ReservationEntity(Integer reservationID) {
        this.reservationID = reservationID;
    }

    public Integer getReservationID() {
        return reservationID;
    }

    public void setReservationID(Integer reservationID) {
        this.reservationID = reservationID;
    }

    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public Integer getRoutineID() {
        return routineID;
    }

    public void setRoutineID(Integer routineID) {
        this.routineID = routineID;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Date getReservationHour() {
        return reservationHour;
    }

    public void setReservationHour(Date reservationHour) {
        this.reservationHour = reservationHour;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservationID != null ? reservationID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservationEntity)) {
            return false;
        }
        ReservationEntity other = (ReservationEntity) object;
        if ((this.reservationID == null && other.reservationID != null) || (this.reservationID != null && !this.reservationID.equals(other.reservationID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.GymServer.API.entities.Reservation[ reservationID=" + reservationID + " ]";
    }
    
}

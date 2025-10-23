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
@Table(name = "gymroutineentity")
@NamedQueries({
    @NamedQuery(name = "GymRoutineEntity.findAll", query = "SELECT g FROM GymRoutineEntity g"),
    @NamedQuery(name = "GymRoutineEntity.findByRoutineID", query = "SELECT g FROM GymRoutineEntity g WHERE g.routineID = :routineID"),
    @NamedQuery(name = "GymRoutineEntity.findByRoutineName", query = "SELECT g FROM GymRoutineEntity g WHERE g.routineName = :routineName"),
    @NamedQuery(name = "GymRoutineEntity.findByDayWeek", query = "SELECT g FROM GymRoutineEntity g WHERE g.dayWeek = :dayWeek"),
    @NamedQuery(name = "GymRoutineEntity.findByStarHour", query = "SELECT g FROM GymRoutineEntity g WHERE g.starHour = :starHour"),
    @NamedQuery(name = "GymRoutineEntity.findByEndHour", query = "SELECT g FROM GymRoutineEntity g WHERE g.endHour = :endHour")})
public class GymRoutineEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "routineID")
    private Integer routineID;
    @Column(name = "routineName")
    private String routineName;
    @Column(name = "dayWeek")
    private String dayWeek;
    @Column(name = "starHour")
    private String starHour;
    @Column(name = "endHour")
    private String endHour;

    public GymRoutineEntity() {
    }

    public GymRoutineEntity(Integer routineID) {
        this.routineID = routineID;
    }

    public Integer getRoutineID() {
        return routineID;
    }

    public void setRoutineID(Integer routineID) {
        this.routineID = routineID;
    }

    public String getRoutineName() {
        return routineName;
    }

    public void setRoutineName(String routineName) {
        this.routineName = routineName;
    }

    public String getDayWeek() {
        return dayWeek;
    }

    public void setDayWeek(String dayWeek) {
        this.dayWeek = dayWeek;
    }

    public String getStarHour() {
        return starHour;
    }

    public void setStarHour(String starHour) {
        this.starHour = starHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (routineID != null ? routineID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GymRoutineEntity)) {
            return false;
        }
        GymRoutineEntity other = (GymRoutineEntity) object;
        if ((this.routineID == null && other.routineID != null) || (this.routineID != null && !this.routineID.equals(other.routineID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.GymServer.API.entities.Gymroutine[ routineID=" + routineID + " ]";
    }
    
}

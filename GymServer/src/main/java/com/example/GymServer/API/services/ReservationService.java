/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GymServer.API.services;

import com.example.GymServer.API.entities.ReservationEntity;
import com.example.GymServer.API.repositories.ReservationRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Axton Urbina
 */
@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;
    
    public ArrayList<ReservationEntity> getReservaitons() {
        return (ArrayList<ReservationEntity>) reservationRepository.findAll();
    }
    
    public ReservationEntity saveReservation(ReservationEntity reservation) {
        return reservationRepository.save(reservation);
    }
    
    public Optional<ReservationEntity> findByID(Integer id) {
        return reservationRepository.findById(id);
    }
    
    public boolean deleteReservation(Integer id) {
        try {
            reservationRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

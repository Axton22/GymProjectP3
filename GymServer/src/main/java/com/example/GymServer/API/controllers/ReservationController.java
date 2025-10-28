/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GymServer.API.controllers;

import com.example.GymServer.API.entities.ReservationEntity;
import com.example.GymServer.API.services.ReservationService;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Axton Urbina
 */
@RestController
@RequestMapping("/reservas")
public class ReservationController {
    @Autowired
    ReservationService reservationService;
    
    @GetMapping()
    public ArrayList<ReservationEntity> getReservations() {
        return reservationService.getReservaitons();
    }
    
    @PostMapping()
    public ReservationEntity saveReservation(@RequestBody ReservationEntity reservation) {
        return this.reservationService.saveReservation(reservation);
    }
    
    @GetMapping( path = "/{id}")
    public Optional<ReservationEntity> findById(@PathVariable("id") Integer id) {
        return this.reservationService.findByID(id);
    }
    
    @DeleteMapping( path = "/{id}")
    public String deleteById(@PathVariable("id") Integer id) {
        boolean ok = this.reservationService.deleteReservation(id);
        if (ok) {
            return "Se eliminó la reservación exitosamente" + "\nID:" + id;
        } else {
            return "No se pudo eliminar la reservación" + "\nID:" + id;
        }
    }
}

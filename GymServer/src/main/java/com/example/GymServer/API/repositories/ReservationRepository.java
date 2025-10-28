/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GymServer.API.repositories;

import com.example.GymServer.API.entities.ReservationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Axton Urbina
 */
@Repository
public interface ReservationRepository extends CrudRepository<ReservationEntity, Integer> {
    
}

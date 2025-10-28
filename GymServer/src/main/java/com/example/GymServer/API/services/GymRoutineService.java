/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GymServer.API.services;

import com.example.GymServer.API.entities.GymRoutineEntity;
import com.example.GymServer.API.repositories.GymRoutineRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Axton Urbina
 */
@Service
public class GymRoutineService {
    @Autowired
    GymRoutineRepository gymRoutineRepository;
    
    public ArrayList<GymRoutineEntity> getRoutines() {
        return (ArrayList<GymRoutineEntity>) gymRoutineRepository.findAll();
    }
    
    public GymRoutineEntity saveRoutine(GymRoutineEntity routine) {
        return gymRoutineRepository.save(routine);
    }
    
    public Optional<GymRoutineEntity> findByID(Integer id) {
        return gymRoutineRepository.findById(id);
    }
    
    public boolean deleteRoutine(Integer id) {
        try {
            gymRoutineRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

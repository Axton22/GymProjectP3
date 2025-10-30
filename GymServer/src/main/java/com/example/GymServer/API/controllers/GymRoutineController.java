/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GymServer.API.controllers;

import com.example.GymServer.API.entities.GymRoutineEntity;
import com.example.GymServer.API.services.GymRoutineService;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Axton Urbina
 */
@RestController
@RequestMapping("/rutinas")
public class GymRoutineController {
    @Autowired
    GymRoutineService gymRoutineService;
    
    @GetMapping()
    public ArrayList<GymRoutineEntity> getRoutines() {
        return gymRoutineService.getRoutines();
    }
    
    @PostMapping()
    public GymRoutineEntity saveRoutine(@RequestBody GymRoutineEntity routine) {
        return this.gymRoutineService.saveRoutine(routine);
    }
    
    @PutMapping("/{id}")
    public GymRoutineEntity updateRoutine(@PathVariable("id") Integer id, @RequestBody GymRoutineEntity routine) {
        routine.setRoutineID(id);
        return this.gymRoutineService.saveRoutine(routine);
    }
    
    @GetMapping( path = "/{id}")
    public Optional<GymRoutineEntity> findById(@PathVariable("id") Integer id) {
        return this.gymRoutineService.findByID(id);
    }
    
    @DeleteMapping( path = "/{id}")
    public String deleteById(@PathVariable("id") Integer id) {
        boolean ok = this.gymRoutineService.deleteRoutine(id);
        if (ok) {
            return "Se eliminó la rutina exitosamente" + "\nID:" + id;
        } else {
            return "No se pudo eliminar la rutina" + "\nID:" + id;
        }
    }
}

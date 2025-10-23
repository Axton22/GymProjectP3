/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GymServer.API.controllers;

import com.example.GymServer.API.entities.GymClientEntity;
import com.example.GymServer.API.services.GymClientService;
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
@RequestMapping("/clientes")
public class GymClientController {
    @Autowired
    GymClientService gymClientService;
    
    @GetMapping()
    public ArrayList<GymClientEntity> getClients() {
        return gymClientService.getClients();
    }
    
    @PostMapping()
    public GymClientEntity saveClient(@RequestBody GymClientEntity client) {
        return this.gymClientService.saveClient(client);
    }
    
    @GetMapping( path = "/{id}")
    public Optional<GymClientEntity> findById(@PathVariable("id") Integer id) {
        return this.gymClientService.findByID(id);
    }
    
    @DeleteMapping( path = "/{id}")
    public String deleteById(@PathVariable("id") Integer id) {
        boolean ok = this.gymClientService.deleteClient(id);
        if (ok) {
            return "Se eliminó el cliente exitosamente" + "\nID:" + id;
        } else {
            return "No se pudo eliminar el cliente" + "\nID:" + id;
        }
    }
}

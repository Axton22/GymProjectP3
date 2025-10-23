/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.GymServer.API.services;

import com.example.GymServer.API.entities.GymClientEntity;
import com.example.GymServer.API.repositories.GymClientRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Axton Urbina
 */
@Service
public class GymClientService {
    @Autowired
    GymClientRepository gymClientRepository;
    
    public ArrayList<GymClientEntity> getClients() {
        return (ArrayList<GymClientEntity>) gymClientRepository.findAll();
    }
    
    public GymClientEntity saveClient(GymClientEntity client) {
        return gymClientRepository.save(client);
    }
    
    public Optional<GymClientEntity> findByID(Integer id) {
        return gymClientRepository.findById(id);
    }
    
    public boolean deleteClient(Integer id) {
        try {
            gymClientRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
} 

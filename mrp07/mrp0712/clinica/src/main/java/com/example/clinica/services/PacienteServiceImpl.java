package com.example.clinica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clinica.config.Tarifas;
import com.example.clinica.domain.Paciente;
import com.example.clinica.domain.PacienteConsulta;
import com.example.clinica.domain.PacienteDTO;
import com.example.clinica.domain.PacienteRecetas;
import com.example.clinica.domain.PacienteRevision;
import com.example.clinica.repositories.PacienteRepository;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    Tarifas tarifas;

    public void add(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    public void deleteFirst() {
        if (pacienteRepository.findAll().size() > 0) {
            pacienteRepository.delete(pacienteRepository.getFirst());
        }
    }

    public Paciente getFirst() {
        if (pacienteRepository.findAll().size() > 0) {
            return pacienteRepository.getFirst();
        }
        return null;
    }

    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    public Double facturar (Paciente paciente) {
        if (paciente != null) {
            return paciente.facturar(tarifas);
        } else {
            return 0d;
        }
        
    }

    public Paciente buildPacienteFromDTO (PacienteDTO pacienteDTO){
        Paciente paciente;
        switch (pacienteDTO.getTipoPaciente()) {
            case 1 -> {
                paciente = new PacienteConsulta();
                ((PacienteConsulta)paciente).setMotivoConsulta(pacienteDTO.getMotivoConsulta());
            }
            case 2 -> {
                paciente = new PacienteRecetas();
                String [] med = pacienteDTO.getListaMedicamentos().split(",");
                ((PacienteRecetas)paciente).setListaMedicamentos(med.length);
            }
            case 3 -> {
                paciente = new PacienteRevision();
                ((PacienteRevision)paciente).setFechaUltimaRevision(pacienteDTO.getFechaUltimaRevision());
            }
            default -> {
                return null;
            }
        }
        paciente.setDni(pacienteDTO.getDni());
        paciente.setFechaNacimiento(pacienteDTO.getFechaNacimiento());
        paciente.setNombre(pacienteDTO.getNombre());
        return paciente;
    }
    
}

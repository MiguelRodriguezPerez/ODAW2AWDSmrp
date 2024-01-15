package com.example.clinica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.clinica.domain.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Query("select p from Paciente p where p.id=(select min(p2.id) from Paciente p2)")
    Paciente getFirst();

}

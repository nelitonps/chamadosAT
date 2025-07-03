package com.nelitonps.chamadosAT.repositories;

import com.nelitonps.chamadosAT.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}

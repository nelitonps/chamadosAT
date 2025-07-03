package com.nelitonps.chamadosAT.repositories;

import com.nelitonps.chamadosAT.domain.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
}

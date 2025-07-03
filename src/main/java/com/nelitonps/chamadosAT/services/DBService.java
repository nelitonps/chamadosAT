package com.nelitonps.chamadosAT.services;

import com.nelitonps.chamadosAT.domain.Chamado;
import com.nelitonps.chamadosAT.domain.Cliente;
import com.nelitonps.chamadosAT.domain.Tecnico;
import com.nelitonps.chamadosAT.domain.enums.Perfil;
import com.nelitonps.chamadosAT.domain.enums.Prioridade;
import com.nelitonps.chamadosAT.domain.enums.Status;
import com.nelitonps.chamadosAT.repositories.ChamadoRepository;
import com.nelitonps.chamadosAT.repositories.ClienteRepository;
import com.nelitonps.chamadosAT.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;

    public void instanciaDB(){

        Tecnico tec1 = new Tecnico(null, "Flavia Silva", "376.720.710-92", "flavia@mail.com", "123");
        tec1.addPerfil(Perfil.ADMIN);
        Cliente cli1 = new Cliente(null, "Silvio Gomes", "172.117.670-57", "silvio@mail.com", "123");
        Chamado chamado1 = new Chamado(null, Prioridade.BAIXA, Status.ABERTO, "Chamado 1", "Primeiro chamado", cli1, tec1);

        tecnicoRepository.saveAll(Arrays.asList(tec1));
        clienteRepository.saveAll(Arrays.asList(cli1));
        chamadoRepository.saveAll(Arrays.asList(chamado1));
    }
}

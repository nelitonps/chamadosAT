package com.nelitonps.chamadosAT.controllers;

import com.nelitonps.chamadosAT.domain.Chamado;
import com.nelitonps.chamadosAT.domain.Cliente;
import com.nelitonps.chamadosAT.domain.Tecnico;
import com.nelitonps.chamadosAT.domain.dtos.ChamadoDTO;
import com.nelitonps.chamadosAT.domain.dtos.ClienteDTO;
import com.nelitonps.chamadosAT.domain.dtos.TecnicoDTO;
import com.nelitonps.chamadosAT.domain.enums.Prioridade;
import com.nelitonps.chamadosAT.domain.enums.Status;
import com.nelitonps.chamadosAT.services.ChamadoService;
import com.nelitonps.chamadosAT.services.ClienteService;
import com.nelitonps.chamadosAT.services.TecnicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/chamados")
public class ChamadoController {

    @Autowired
    private ChamadoService chamadoService;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "index-dark";
    }

    @GetMapping("/login")
    public String login() {
        return "auth-cover-login";
    }

    @GetMapping("/relatorios")
    public String relatorios() {
        return "relatorios";
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> finById(@PathVariable Integer id){
        Chamado obj = chamadoService.findById(id);
        return ResponseEntity.ok().body(new ChamadoDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll(){
        List<Chamado> list = chamadoService.findAll();
        List<ChamadoDTO> listDTO = list.stream().map(x -> new ChamadoDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO objDTO){
        Chamado newObj = chamadoService.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> update(@PathVariable Integer id, @Valid @RequestBody ChamadoDTO objDTO){
        Chamado obj = chamadoService.update(id, objDTO);
        return ResponseEntity.ok().body(new ChamadoDTO(obj));
    }

}

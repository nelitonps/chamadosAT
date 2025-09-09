package com.nelitonps.chamadosAT.controllers;

import com.nelitonps.chamadosAT.domain.Tecnico;
import com.nelitonps.chamadosAT.domain.dtos.TecnicoDTO;
import com.nelitonps.chamadosAT.domain.enums.Perfil;
import com.nelitonps.chamadosAT.domain.enums.Prioridade;
import com.nelitonps.chamadosAT.domain.enums.Status;
import com.nelitonps.chamadosAT.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/chamados/tecnicos")
public class TecnicoViewController {

    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping
    public String listarTecnicos(Model model) {
        List<Tecnico> tecnicos = tecnicoService.findAll();
        model.addAttribute("tecnicos", tecnicos);
        return "tecnicos"; // nome do arquivo HTML Thymeleaf
    }

    @GetMapping("/novo-tecnico")
    public String novoTecnicoForm(Model model) {
        model.addAttribute("tecnico", new Tecnico()); // Isso é essencial para o th:object funcionar
        model.addAttribute("perfilList", Perfil.values()); // ou sua lógica para buscar da API
        return "novo-tecnico";
    }

    @GetMapping("/editar-tecnico/{id}")
    public String editarTecnicoForm(@PathVariable Integer id, Model model) {
        Tecnico tecnico = tecnicoService.findById(id);
        TecnicoDTO dto = new TecnicoDTO(tecnico); // converte para DTO
        model.addAttribute("tecnico", dto);
        model.addAttribute("perfilList", Perfil.values());
        return "editar-tecnico";
    }

    @GetMapping("/deletar-tecnico/{id}")
    public String deletarTecnicoForm(@PathVariable Integer id, Model model) {
        Tecnico tecnico = tecnicoService.findById(id);
        TecnicoDTO dto = new TecnicoDTO(tecnico); // converte para DTO
        model.addAttribute("tecnico", dto);
        model.addAttribute("perfilList", Perfil.values());
        return "deletar-tecnico";
    }




}

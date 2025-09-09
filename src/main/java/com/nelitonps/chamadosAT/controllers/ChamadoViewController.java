package com.nelitonps.chamadosAT.controllers;

import com.nelitonps.chamadosAT.domain.Chamado;
import com.nelitonps.chamadosAT.domain.Tecnico;
import com.nelitonps.chamadosAT.domain.dtos.ChamadoDTO;
import com.nelitonps.chamadosAT.domain.dtos.TecnicoDTO;
import com.nelitonps.chamadosAT.domain.enums.Perfil;
import com.nelitonps.chamadosAT.domain.enums.Prioridade;
import com.nelitonps.chamadosAT.domain.enums.Status;
import com.nelitonps.chamadosAT.services.ChamadoService;
import com.nelitonps.chamadosAT.services.ClienteService;
import com.nelitonps.chamadosAT.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/chamados/helpdesk")
public class ChamadoViewController {

    @Autowired
    private ChamadoService chamadoService;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String listarChamados(Model model) {
        List<Chamado> chamados = chamadoService.findAll();
        model.addAttribute("chamados", chamados);
        return "helpdesk"; // nome do arquivo HTML Thymeleaf
    }

    @GetMapping("/novo-chamado")
    public String novoChamado(Model model) {
        model.addAttribute("statusList", Status.values()); // se for enum
        model.addAttribute("prioridadeList", Prioridade.values()); // se for enum
        model.addAttribute("tecnicos", tecnicoService.findAll()); // lista de técnicos
        model.addAttribute("clientes", clienteService.findAll()); // lista de clientes
        return "novo-chamado"; // caminho do HTML
    }


    @GetMapping("/editar-chamado/{id}")
    public String editarChamadoForm(@PathVariable Integer id, Model model) {
            Chamado chamado = chamadoService.findById(id);
            ChamadoDTO dto = new ChamadoDTO(chamado);
            model.addAttribute("chamado", dto);
            model.addAttribute("statusList", Status.values());
            model.addAttribute("prioridadeList", Prioridade.values());
            model.addAttribute("tecnicos", tecnicoService.findAll());
            model.addAttribute("clientes", clienteService.findAll());
            return "editar-chamado";
    }



    @GetMapping("/ver-chamado/{id}")
    public String verChamadoForm(@PathVariable Integer id, Model model) {
        Chamado chamado = chamadoService.findById(id);
        ChamadoDTO dto = new ChamadoDTO(chamado);
        model.addAttribute("chamado", dto);
        model.addAttribute("statusList", Status.values());
        model.addAttribute("prioridadeList", Prioridade.values());
        model.addAttribute("tecnicos", tecnicoService.findAll());
        model.addAttribute("clientes", clienteService.findAll());
        return "ver-chamado";
    }

}

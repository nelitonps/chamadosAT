package com.nelitonps.chamadosAT.controllers;

import com.nelitonps.chamadosAT.domain.Cliente;
import com.nelitonps.chamadosAT.domain.Tecnico;
import com.nelitonps.chamadosAT.domain.dtos.ClienteDTO;
import com.nelitonps.chamadosAT.domain.dtos.TecnicoDTO;
import com.nelitonps.chamadosAT.domain.enums.Perfil;
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
@RequestMapping("/chamados/clientes")
public class ClienteViewController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String listarClientes(Model model) {
        List<Cliente> clientes = clienteService.findAll();
        model.addAttribute("clientes", clientes);
        return "clientes"; // nome do arquivo HTML Thymeleaf
    }

    @GetMapping("/novo-cliente")
    public String novoClienteForm(Model model) {
        model.addAttribute("cliente", new Cliente()); // Isso é essencial para o th:object funcionar
        model.addAttribute("perfilList", Perfil.values()); // ou sua lógica para buscar da API
        return "novo-cliente";
    }

    @GetMapping("/editar-cliente/{id}")
    public String editarClienteForm(@PathVariable Integer id, Model model) {
        Cliente cliente = clienteService.findById(id);
        ClienteDTO dto = new ClienteDTO(cliente); // converte para DTO
        model.addAttribute("cliente", dto);
        model.addAttribute("perfilList", Perfil.values());
        return "editar-cliente";
    }

    @GetMapping("/deletar-cliente/{id}")
    public String deletarClienteForm(@PathVariable Integer id, Model model) {
        Cliente cliente = clienteService.findById(id);
        ClienteDTO dto = new ClienteDTO(cliente); // converte para DTO
        model.addAttribute("cliente", dto);
        model.addAttribute("perfilList", Perfil.values());
        return "deletar-cliente";
    }




}

package com.nelitonps.chamadosAT.services;

import com.nelitonps.chamadosAT.domain.Cliente;
import com.nelitonps.chamadosAT.domain.Pessoa;
import com.nelitonps.chamadosAT.domain.Tecnico;
import com.nelitonps.chamadosAT.domain.dtos.ClienteDTO;
import com.nelitonps.chamadosAT.repositories.ClienteRepository;
import com.nelitonps.chamadosAT.repositories.PessoaRepository;
import com.nelitonps.chamadosAT.services.exception.DataIntegrityViolationException;
import com.nelitonps.chamadosAT.services.exception.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente create(ClienteDTO objDTO) {
        objDTO.setId(null);
        Cliente obj = new Cliente(objDTO);
        validaPorCpfEEmail(objDTO);
        return clienteRepository.save(obj);
    }

    public Cliente update(Integer id, ClienteDTO objDTO) {
        objDTO.setId(id);
        Cliente oldObj = findById(id);
        validaPorCpfEEmail(objDTO);
        oldObj = new Cliente(objDTO);
        return clienteRepository.save(oldObj);
    }

    public void delete(Integer id) {
        Cliente obj = findById(id);
        if (obj.getChamados().size() > 0){
            throw new DataIntegrityViolationException("Cliente possui ordens de serviçõ e não pode ser deletado!");
        }
        clienteRepository.deleteById(id);
    }

    private void validaPorCpfEEmail(ClienteDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }
        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("E-MAIL já cadastrado no sistema!");
        }
    }
}

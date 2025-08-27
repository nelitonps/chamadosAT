package com.nelitonps.chamadosAT.services;

import com.nelitonps.chamadosAT.domain.Pessoa;
import com.nelitonps.chamadosAT.domain.Tecnico;
import com.nelitonps.chamadosAT.domain.dtos.TecnicoDTO;
import com.nelitonps.chamadosAT.repositories.PessoaRepository;
import com.nelitonps.chamadosAT.repositories.TecnicoRepository;
import com.nelitonps.chamadosAT.services.exception.DataIntegrityViolationException;
import com.nelitonps.chamadosAT.services.exception.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO) {
        objDTO.setId(null);
        objDTO.setSenha(encoder.encode(objDTO.getSenha())); //Parte de autenticação
        validaPorCpfEEmail(objDTO);
        Tecnico obj = new Tecnico(objDTO);
        return tecnicoRepository.save(obj);
    }

    public Tecnico update(Integer id, TecnicoDTO objDTO) {
        objDTO.setId(id);
        Tecnico oldObj = findById(id);
        validaPorCpfEEmail(objDTO);
        oldObj = new Tecnico(objDTO);
        return tecnicoRepository.save(oldObj);
    }

    private void validaPorCpfEEmail(TecnicoDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }
        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("EMAIL já cadastrado no sistema!");
        }
    }


    public void delete(Integer id) {
        Tecnico obj = findById(id);
        if (obj.getChamados().size() > 0){
            throw new DataIntegrityViolationException("Tecnico possui ordens de serviço e não pode ser deletado!");
        }
        tecnicoRepository.deleteById(id);
    }
}

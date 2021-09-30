package com.ifpb.pdist.bancoweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifpb.pdist.bancoweb.model.Correntista;
import com.ifpb.pdist.bancoweb.model.dtos.EmailDto;
import com.ifpb.pdist.bancoweb.repository.CorrentistaRepository;
import com.ifpb.pdist.bancoweb.util.PasswordUtil;

@Service
public class CorrentistaService {

    @Autowired
    private CorrentistaRepository correntistaRepository;

    @Transactional
    public void insertBD() {
		Correntista darwin = new Correntista();
		darwin.setEmail("helenabarros245@gmail.com");
		darwin.setNome("Charles Robert Darwin");
		darwin.setSenha(PasswordUtil.hashPassword("evolucao"));
		EmailDto emailDto = new EmailDto();
		emailDto.setEmailTo(darwin.getEmail());
		
//    	correntista.setSenha(PasswordUtil.hashPassword(correntista.getSenha()));
        correntistaRepository.save(darwin);
        
        

		Correntista admin = new Correntista();
		admin.setEmail("admin@spring-banco.com.br");
		admin.setNome("Administrador do Sistema");
		admin.setSenha(PasswordUtil.hashPassword("root123"));
		admin.setAdmin(true);
        correntistaRepository.save(admin);

    }

    public List<Correntista> findAll() {
        return correntistaRepository.findAll();
    }

    public Correntista findById(Integer correntistaId) {
        Optional<Correntista> c =  correntistaRepository.findById(correntistaId);
        return c.isPresent() ? c.get() : null;
    }


}
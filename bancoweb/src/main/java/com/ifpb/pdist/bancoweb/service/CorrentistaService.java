package com.ifpb.pdist.bancoweb.service;

import com.ifpb.pdist.bancoweb.model.Correntista;
import com.ifpb.pdist.bancoweb.repository.CorrentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CorrentistaService {

    @Autowired
    private CorrentistaRepository correntistaRepository;

    @Transactional
    public void insertBD(Correntista correntista) {
        correntistaRepository.save(correntista);

    }

    public List<Correntista> findAll() {
        return correntistaRepository.findAll();
    }

    public Correntista findById(Integer correntistaId) {
        Optional<Correntista> c =  correntistaRepository.findById(correntistaId);
        return c.isPresent() ? c.get() : null;
    }


}

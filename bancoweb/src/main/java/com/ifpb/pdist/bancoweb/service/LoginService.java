package com.ifpb.pdist.bancoweb.service;

import com.ifpb.pdist.bancoweb.model.Correntista;
import com.ifpb.pdist.bancoweb.repository.CorrentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private CorrentistaRepository correntistaRepository;

    public Correntista isValido(Correntista correntista) {
        Correntista correntistaBD = correntistaRepository.findByEmail(correntista.getEmail());
        boolean valido = false;
        if (correntistaBD != null) {
            valido = true;
        }
        return valido ? correntistaBD : null;
    }


}

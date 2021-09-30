package com.ifpb.pdist.bancoweb.service;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifpb.pdist.bancoweb.exception.BancoException;
import com.ifpb.pdist.bancoweb.model.Conta;
import com.ifpb.pdist.bancoweb.model.Correntista;
import com.ifpb.pdist.bancoweb.model.Transacao;
import com.ifpb.pdist.bancoweb.repository.ContaRepository;


@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;

	@Transactional
	public void saveConta(Conta conta) {
		contaRepository.save(conta);
	}

	public Conta findById(Integer id) {
		Optional<Conta> conta = contaRepository.findById(id);
		return conta.isPresent() ? conta.get() : null;
	}

	public Conta findByCorrentista(Correntista correntista) {
		return contaRepository.findByCorrentista(correntista);
	}

	public Conta findByNumeroWithTransacoes(String numero) {
		return contaRepository.findByNumeroWithTransacoes(numero);
	}

	public Conta findByIdWithTransacoes(Integer id) {
		return contaRepository.findByIdWithTransacoes(id);
	}

	public List<Conta> findAll() throws BancoException {
		return contaRepository.findAll();
	}

	@Transactional
	public void deleteById(Integer id) {
		contaRepository.deleteById(id);
	}

	@Transactional
	public void addTransacao(Conta conta, Transacao transacao) {
		conta.addTransacao(transacao);
		contaRepository.save(conta);

	}

}

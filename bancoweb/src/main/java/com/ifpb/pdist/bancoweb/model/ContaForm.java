package com.ifpb.pdist.bancoweb.model;

public class ContaForm {
	private Conta conta;
	private Integer correntistaId;

	public ContaForm() {
		super();
	}

	public ContaForm(Conta conta, Integer correntistaId) {
		super();
		this.conta = conta;
		this.correntistaId = correntistaId;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Integer getCorrentistaId() {
		return correntistaId;
	}

	public void setCorrentistaId(Integer correntistaId) {
		this.correntistaId = correntistaId;
	}

}

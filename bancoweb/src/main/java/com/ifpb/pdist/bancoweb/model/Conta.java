package com.ifpb.pdist.bancoweb.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Conta implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String numero;
	
	@Column(name="data_criacao")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@OneToMany (mappedBy = "conta", cascade = {CascadeType.ALL})
	private Set<Transacao> transacoes = new HashSet<Transacao>();
	
	@OneToOne
	@JoinColumn(name="id_correntista", foreignKey = @ForeignKey(name="FK_CORRENTISTA"))
	private Correntista correntista;
	
	public BigDecimal getSaldo() {
		BigDecimal total = BigDecimal.ZERO;
		for (Transacao t : this.transacoes) {
			total = total.add(t.getValor());
		}
		return total;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Set<Transacao> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(Set<Transacao> transacoes) {
		this.transacoes = transacoes;
	}

	public void addTransacao(Transacao transacao) {
		this.transacoes.add(transacao);
		transacao.setConta(this);
		
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Correntista getCorrentista() {
		return correntista;
	}

	public void setCorrentista(Correntista correntista) {
		this.correntista = correntista;
	}
	
}

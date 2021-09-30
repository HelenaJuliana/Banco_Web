package com.ifpb.pdist.bancoweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ifpb.pdist.bancoweb.model.Conta;
import com.ifpb.pdist.bancoweb.model.Correntista;

public interface ContaRepository extends JpaRepository<Conta, Integer> {
	
	public Conta findByCorrentista(Correntista correntista);
	
	@Query("from Conta c left join fetch c.transacoes t where c.numero = :numero")
	public Conta findByNumeroWithTransacoes(@Param("numero") String numero);
	
	@Query("select distinct c from Conta c left join fetch c.transacoes t where c.id = :id")
	public Conta findByIdWithTransacoes(@Param("id") Integer id);

}

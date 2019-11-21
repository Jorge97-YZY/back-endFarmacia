package com.jorge.manuel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jorge.manuel.model.Fornecedor;
/**
 * 
 *O JpaRepository é onde esta integrado os metodos Salvar, Listar, Apagar, Editar...
 *
 */

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
	

	public Fornecedor findByNumforn(String num_form);
	

}

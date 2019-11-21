package com.jorge.manuel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jorge.manuel.model.Produto;
/**
 * 
 *O JpaRepository é onde esta integrado os metodos Salvar, Listar, Apagar, Editar...
 *
 */

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	

}

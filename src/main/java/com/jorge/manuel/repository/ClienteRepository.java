package com.jorge.manuel.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.jorge.manuel.model.Cliente;
/**
 * 
 *O JpaRepository é onde esta integrado os metodos Salvar, Listar, Apagar, Editar...
 *
 */

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	public Cliente findByEmail(String email);
	public Cliente findByBi(String bi);

}

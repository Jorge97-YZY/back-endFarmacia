package com.jorge.manuel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jorge.manuel.model.Funcionario;
/**
 * 
 *O JpaRepository é onde esta integrado os metodos Salvar, Listar, Apagar, Editar...
 *
 */

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	
	public Funcionario findByBi(String bi);
}

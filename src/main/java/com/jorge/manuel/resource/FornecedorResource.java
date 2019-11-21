package com.jorge.manuel.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jorge.manuel.model.Fornecedor;
import com.jorge.manuel.model.Retorno;
import com.jorge.manuel.repository.FornecedorRepository;
import com.jorge.manuel.viewModel.ViwModelFornecedor;

@Controller
@RestController

@CrossOrigin("${permitir}")
public class FornecedorResource {

	@Autowired
	private FornecedorRepository fornecedorRepository;

	@RequestMapping(value = "/fornecedores/listar/todos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public List<Fornecedor> listar() {
		return fornecedorRepository.findAll();
	}

	@PostMapping("/fornecedores/salvar")
	public Fornecedor salvar(@RequestBody @Valid Fornecedor fornecedor) {
		return fornecedorRepository.save(fornecedor);
	}

	@RequestMapping(value = "/fornecedores/listar/{id}", method = RequestMethod.GET, produces = "application/json")
	@CrossOrigin(origins = "*")
	public Optional<Fornecedor> findById(@PathVariable Long id) {

		return fornecedorRepository.findById(id);
	}

	@RequestMapping(value = "/fornecedores/editar", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<Retorno> atualizar(@RequestBody ViwModelFornecedor viwModelFornecedor) {
		Retorno c = new Retorno();

		Fornecedor fornecedor = this.fornecedorRepository.findByNumforn(viwModelFornecedor.getNum_forn());

		fornecedor.setNome(viwModelFornecedor.getNome());
		fornecedor.setTelefone(viwModelFornecedor.getTelefone());

		this.fornecedorRepository.save(fornecedor);

		c.setCodigo(200);
		c.setMensagem("Fornecedor alterado com sucesso!");
		c.setResultado(fornecedor);

		return ResponseEntity.ok(c);

	}

	@DeleteMapping("/fornecedores/apagar/{id}")
	public void delete(@PathVariable long id) {
		 fornecedorRepository.deleteById(id);
	}
}

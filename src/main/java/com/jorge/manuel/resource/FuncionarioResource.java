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

import com.jorge.manuel.model.Funcionario;
import com.jorge.manuel.model.Retorno;
import com.jorge.manuel.repository.FuncionarioRepository;
import com.jorge.manuel.viewModel.ViwModelFuncionario;

@Controller
@RestController

@CrossOrigin("${permitir}")
public class FuncionarioResource {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@RequestMapping(value = "/funcionarios/listar/todos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public List<Funcionario> listar() {
		return funcionarioRepository.findAll();
	}

	@PostMapping("/funcionarios/salvar")
	public Funcionario salvar(@RequestBody @Valid Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}

	@RequestMapping(value = "/funcinarios/listar/{id}", method = RequestMethod.GET, produces = "application/json")
	@CrossOrigin(origins = "*")
	public Optional<Funcionario> findById(@PathVariable Long id) {

		return funcionarioRepository.findById(id);
	}

	@RequestMapping(value = "/funcionarios/editar", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<Retorno> atualizar(@RequestBody ViwModelFuncionario viwModelFuncionario) {
		Retorno c = new Retorno();

		Funcionario funcionario = this.funcionarioRepository.findByBi(viwModelFuncionario.getBi());

		funcionario.setNome(viwModelFuncionario.getNome());
		funcionario.setTelefone(viwModelFuncionario.getTelefone());

		this.funcionarioRepository.save(funcionario);

		c.setCodigo(200);
		c.setMensagem("Funcionario alterado com sucesso!");
		c.setResultado(funcionario);

		return ResponseEntity.ok(c);

	}

	@DeleteMapping("/funcionarios/apagar/{id}")
	public void delete(@PathVariable long id) {
		 funcionarioRepository.deleteById(id);
	}
}

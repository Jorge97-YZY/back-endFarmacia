package com.jorge.manuel.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jorge.manuel.model.Cliente;
import com.jorge.manuel.model.Retorno;
import com.jorge.manuel.repository.ClienteRepository;
import com.jorge.manuel.viewModel.ViwModelCliente;

@RestController

@CrossOrigin("${permitir}")
public class ClienteResource {

	@Autowired
	private ClienteRepository clienteRepository;

	@RequestMapping(value = "/clientes/listar/todos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}

	@PostMapping("/clientes/salvar")
	public Cliente salvar(@RequestBody @Valid Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@RequestMapping(value = "/clientes/listar/{id}", method = RequestMethod.GET, produces = "application/json")
	@CrossOrigin(origins = "*")
	public Optional<Cliente> findById(@PathVariable Long id) {

		return clienteRepository.findById(id);
	}

	@RequestMapping(value = "/clientes/editar", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<Retorno> atualizar(@RequestBody ViwModelCliente viwModel) {
		Retorno c = new Retorno();

		Cliente cliente = this.clienteRepository.findByBi(viwModel.getBi());

		cliente.setNome(viwModel.getNome());
		cliente.setEmail(viwModel.getEmail());
		cliente.setTelefone(viwModel.getTelefone());

		this.clienteRepository.save(cliente);

		c.setCodigo(200);
		c.setMensagem("Cliente alterado com sucesso!");
		c.setResultado(cliente);

		return ResponseEntity.ok(c);
		
	}

	@DeleteMapping("/clientes/apagar/{id}")
	public void delete(@PathVariable long id) {
		clienteRepository.deleteById(id);
	}
}

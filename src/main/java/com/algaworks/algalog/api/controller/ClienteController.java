package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.service.ClienteServiceCRUD;

import lombok.AllArgsConstructor;

@AllArgsConstructor	//Gera o construtor com os atributos
@RestController		//Diz para o Spring que é um componente e que pode tratar requisiçoes e devolver uma resposta
@RequestMapping("/clientes")	//Essa annotation evita de ficar repetindo o caminho nas "GetMapping"
public class ClienteController {
	/*
	@PersistenceContext
	private EntityManager manager; //Interface do Jakatapersistence responsavel por fazer as operacoes CRUD no banco de dados
	*/
	
	//Poderia usar a annotation "@Autowired" mas como ja usamos "@AllArgsConstructor" para gerar o construtor, ficou desnecessario
	ClienteRepository clienteRepository;
	ClienteServiceCRUD clienteService;
	
	
	@GetMapping		//Quando for chamado/requisitado, chamara este metodo
	public List<Cliente> listar() {
		/*return manager.createQuery("from Cliente", Cliente.class)
				.getResultList();
		*/
		return clienteRepository.findAll();
//		return clienteRepository.findByNome("Jorge Rufino");	Busca exata
//		return clienteRepository.findByNomeContaining("ino");	Busca que contem o string
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {	//Permite controlar melhor a resposta como o codigo que sera devolvivo em caso de nao encontrar cliente
		return clienteRepository.findById(clienteId)
//				.map(cliente -> ResponseEntity.ok(cliente))
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
		
//		Faz o mesmo que o return acima		
//		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
//		
//		if (cliente.isPresent()) {
//			return ResponseEntity.ok(cliente.get());
//		}
//		
//		return ResponseEntity.notFound().build();	//Se nao encontrar o cliente, retorna o erro 404
	}
	
	//essa annotation diz que os parametros do corpo da requisicao, serao usados para criar o obejto cliente
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)	//Faz com que o codigo de reposta seja o  "201 - Created" em vez do "200 - Ok"
	public Cliente adicionar (@Valid @RequestBody Cliente cliente) {	//@Valid faz a validação logo na chamada do metodo, antes de ir pro banco
//		return clienteRepository.save(cliente);  //já salva e retonar o cliente
		return clienteService.salvar(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> alterar(@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente) {	
		
		if(!clienteRepository.existsById(clienteId)){
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(clienteId);	//Está setando o cliente para ser alterado, se nao ele criaria um novo cliente
//		cliente = clienteRepository.save(cliente);
		cliente = clienteService.salvar(cliente);
		
		return ResponseEntity.ok(cliente); 
	}
	
	//Void porque o corpo da resposta nao vai existir
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> deletar(@PathVariable Long clienteId){
		if(!clienteRepository.existsById(clienteId)){
			return ResponseEntity.notFound().build();
		}
		
//		clienteRepository.deleteById(clienteId);
		clienteService.deletar(clienteId);
		
		return ResponseEntity.noContent().build();	//Retorna codigo 204 (Sucesso sem corpo na resposta)
	}
}


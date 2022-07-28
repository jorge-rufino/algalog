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

import com.algaworks.algalog.api.assembler.ClienteAssembler;
import com.algaworks.algalog.api.model.ClienteModel;
import com.algaworks.algalog.api.model.input.ClienteInputModel;
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
	ClienteAssembler clienteAssembler;
	
	
	@GetMapping		//Quando for chamado/requisitado um GET na requisição, chamara este metodo
	public List<ClienteModel> listar() {
		/*return manager.createQuery("from Cliente", Cliente.class)
				.getResultList();
		*/
		return clienteAssembler.toCollectionModel(clienteRepository.findAll());
		
//		return clienteRepository.findAll();
//		return clienteRepository.findByNome("Jorge Rufino");	Busca exata
//		return clienteRepository.findByNomeContaining("ino");	Busca que contem o string
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<ClienteModel> buscar(@PathVariable Long clienteId) {	//Permite controlar melhor a resposta como o codigo que sera devolvivo em caso de nao encontrar cliente
		return clienteRepository.findById(clienteId)
//				.map(cliente -> ResponseEntity.ok(cliente))
//				.map(ResponseEntity::ok)										Faz o mesmo que a linha acima
				.map(cliente -> ResponseEntity.ok(clienteAssembler.toModel(cliente)))
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
	public ClienteModel adicionar (@Valid @RequestBody ClienteInputModel clienteInputModel) {	//@Valid faz a validação logo na chamada do metodo, antes de ir pro banco
//		return clienteRepository.save(cliente);  //já salva e retonar o cliente
		Cliente clienteCovertido = clienteAssembler.toEntity(clienteInputModel);
		Cliente novoCliente = clienteService.salvar(clienteCovertido);
		return clienteAssembler.toModel(novoCliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<ClienteModel> alterar(@Valid @PathVariable Long clienteId, @Valid @RequestBody ClienteInputModel clienteInputModel) {	
		
		if(!clienteRepository.existsById(clienteId)){
			return ResponseEntity.notFound().build();
		}
		
		clienteInputModel.setId(clienteId);	//Está setando o cliente para ser alterado, se nao ele criaria um novo cliente
//		cliente = clienteRepository.save(cliente);
		Cliente clienteConvertido = clienteAssembler.toEntity(clienteInputModel);
		Cliente clienteAlterado = clienteService.salvar(clienteConvertido);
		
		return  ResponseEntity.ok(clienteAssembler.toModel(clienteAlterado)); 
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


package com.algaworks.algalog.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;

@RestController		//Diz para o Spring que é um componente e que pode tratar requisiçoes e devolver uma resposta
public class ClienteController {
	
	@GetMapping("/clientes")		//Quando for chamado/requisitado, chamara este metodo
	public List<Cliente> listar() {
		var cliente1 = new Cliente(); 	//Nova forma de declarar variaves
		cliente1.setId(1L);
		cliente1.setNome("João");
		cliente1.setTelefone("91 98569-7143");
		cliente1.setEmail("joao@gmail.com");
		
		var cliente2 = new Cliente(); 	//Nova forma de declarar variaves
		cliente2.setId(2L);
		cliente2.setNome("Maria");
		cliente2.setTelefone("91 99999-2222");
		cliente2.setEmail("maria@gmail.com");
				
		/*List<Cliente> lista = new ArrayList<>();	Poderia ser assim tb mas para economizar codigo foi feito apenas o "return" direto	
		lista.add(cliente1);
		lista.add(cliente2);
		
		return lista;*/
		
		return Arrays.asList(cliente1, cliente2);	//Retorna a lista
		
	}
}


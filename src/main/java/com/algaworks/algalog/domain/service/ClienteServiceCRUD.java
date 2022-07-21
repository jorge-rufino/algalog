package com.algaworks.algalog.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service	//Representa um componente de serviços, de regras de negocios
public class ClienteServiceCRUD {
	
	private ClienteRepository clienteRepository;
	
	@Transactional		//Declara que o metodo deve ser executado dentro de uma transacao. Se ele der erro todas as transacoes dependentes dele sao canceladas
	public Cliente salvar (Cliente cliente) {
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
					.stream()
//Verifica se o cliente que foi encontrado pelo email é igual ao novo cliente, se forem diferentes, vai disparar a exceçao
//se forem iguais ele vai atualizar, pois o metodo "alterar()" tambem utiliza este aqui
					.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		
		if(emailEmUso) {
			throw new NegocioException("Já existe um cliente cadastrado com este email!");
		}
		
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void deletar(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
	
	public Cliente buscar(Long clienteId) {
//		como "findById" retorna um Optional, precisamos usar o "orElseThrow" onde caso ele encontre um cliente,
//		retorna ele, se nao dispara a exceção, por isso a lambda é vazia "() ->"
		return clienteRepository.findById(clienteId)
				.	orElseThrow(() -> new NegocioException("Cliente não encontrado!"));
	}
}

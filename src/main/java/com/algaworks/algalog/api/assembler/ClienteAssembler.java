package com.algaworks.algalog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.algalog.api.model.ClienteModel;
import com.algaworks.algalog.api.model.input.ClienteInputModel;
import com.algaworks.algalog.domain.model.Cliente;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ClienteAssembler {

	private ModelMapper modelMapper;
	
	public ClienteModel toModel(Cliente cliente) {
		return modelMapper.map(cliente, ClienteModel.class);
	}
	
	public List<ClienteModel> toCollectionModel(List<Cliente> clientes){
		return clientes.stream()
				.map(cliente -> toModel(cliente))
				.collect(Collectors.toList());
	}
	
	public Cliente toEntity(ClienteInputModel clienteInputModel) {
		return modelMapper.map(clienteInputModel, Cliente.class);
	}
}

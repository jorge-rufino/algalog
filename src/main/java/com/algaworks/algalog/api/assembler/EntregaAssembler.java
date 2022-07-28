package com.algaworks.algalog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.algalog.api.model.ClienteModel;
import com.algaworks.algalog.api.model.EntregaModel;
import com.algaworks.algalog.api.model.input.EntregaInputModel;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.model.Entrega;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EntregaAssembler {
	//Classe responsavel pelas conversoes das entidades do Representation Model e Domain Model
	
	private ModelMapper modelMapper;
	
//	Converte a Entrega do Domain Model para uma Entrega do Representation Model
	public EntregaModel toModel(Entrega entrega) {
		return modelMapper.map(entrega, EntregaModel.class);
	}
	
	public ClienteModel toModel(Cliente cliente) {
		return modelMapper.map(cliente, ClienteModel.class);
	}
	
//	Converte uma Lista de Entregas do Domain Model para uma lista de Entregas do Representation Model
	public List<EntregaModel> toCollectionModel(List<Entrega> entregas){
		return entregas.stream()
				.map(this::toModel)	//Aplica uma funcao aos elementos da stream no caso converter a stream de EntregaModel
				.collect(Collectors.toList());	//transforma a stream para lista
	}
		
//	Converte uma Entrega do Representation Model para uma Entrega do Domain Model 
//	Método usado no Input
	public Entrega toEntity(EntregaInputModel entregaModel) {
		return modelMapper.map(entregaModel, Entrega.class);
	}
}

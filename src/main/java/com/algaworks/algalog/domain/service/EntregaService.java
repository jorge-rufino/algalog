package com.algaworks.algalog.domain.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.StatusEntrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EntregaService {
	
	ClienteServiceCRUD clienteService;
	EntregaRepository entregaRepository;
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {

		Cliente cliente = clienteService.buscar(entrega.getCliente().getId());
		
		entrega.setCliente(cliente);	
		entrega.setStatus(StatusEntrega.PENDENTE);	//Toda entrega comeca pendente
		entrega.setDataPedido(OffsetDateTime.now());			//Pega a hora no momento do pedido
		
		return entregaRepository.save(entrega);
	}
}

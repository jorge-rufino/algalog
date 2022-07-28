package com.algaworks.algalog.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.algaworks.algalog.domain.model.StatusEntrega;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaModel {
//	Classe de Representacao (Represetation Model) do Modelo de Dominio
	//ModelMapper consegue mapear desde que as variaveis tenham essa sintaxe:
//	"nome_atributo + Nome_classe"
	private Long id;
	private ClienteResumoModel cliente;
	private String nomeDestinatario;
	private String nomeCliente;
	private DestinatarioModel destinatario;
	
	private BigDecimal taxa;
	private StatusEntrega status;
	private OffsetDateTime dataPedido;
	private OffsetDateTime dataFinalizacao;
	
}

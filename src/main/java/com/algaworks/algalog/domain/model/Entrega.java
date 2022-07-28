package com.algaworks.algalog.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.algaworks.algalog.domain.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Valid		//Esta anotação valida TODOS os atributos do Cliente, porém como so queremos validar o ID, usaremos o "ValidationGroups" para especificar somente ele
	@ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)	//Quando for validar o Cliente, usará o VadidationGroups no lugar do Default para não validar todos os atributos
	@NotNull	//Ele verifica somente se existe ou nao um "objeto Cliente", ele não faz a validação dos dados do cliente
	@ManyToOne
	private Cliente cliente;
	
	@Valid
	@NotNull
	@Embedded	//Destinatario nao sera uma tabela a parte, ele sera parte da tabela Entrega, por isso esta annotation
	private Destinatario destinatario;	
	
	@NotNull
	private BigDecimal taxa;
	
	@JsonProperty(access = Access.READ_ONLY)	//Faz com que não aceite esses dados pela requisiçao
	private OffsetDateTime dataPedido;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataFinalizacao;
	
	@JsonProperty(access = Access.READ_ONLY)
	@Enumerated(EnumType.STRING)	//Pega a String/Nome das opçoes da classe Enum em vez de pegar o indice/posicao
	private StatusEntrega status;
	
}
 
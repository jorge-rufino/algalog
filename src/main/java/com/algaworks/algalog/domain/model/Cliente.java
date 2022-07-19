package com.algaworks.algalog.domain.model;

import lombok.Getter;
import lombok.Setter;

//Lombok - Cria os metodos set e get para os atributos da classe
@Getter
@Setter
public class Cliente {
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	
}

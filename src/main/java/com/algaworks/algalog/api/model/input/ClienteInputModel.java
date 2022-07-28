package com.algaworks.algalog.api.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteInputModel {
	
	//Apesar do Id não ser informado na requisiçao, preciso dele aqui para poder alterar o cliente usando o "setId"
	private Long id;
	
	@NotBlank				//Evita que o nome seja "null" ou esteja vazio ""	
	private String nome;
	
	@NotBlank
	@Email					//Verifica se a syntaxe do email está correta
	private String email;
	
	@NotBlank
	private String telefone;
}

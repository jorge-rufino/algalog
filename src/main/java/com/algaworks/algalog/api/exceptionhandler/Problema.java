package com.algaworks.algalog.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)		//So vai incluir propriedades se nao forem nulas
@Getter
@Setter
public class Problema {
	
	private Integer status;				//Numero de erro da requisição
	private OffsetDateTime dataHora;		//data e hora do erro
	private String titulo;				//Título geral do erro
	
	private List<Campo> campos;			//Lista com o erros, contendo o nome e a mensagem de erro
 	
	@Getter
	@AllArgsConstructor
	public static class Campo{
		
		private String nome;
		private String mensagem;
	}
}

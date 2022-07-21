package com.algaworks.algalog.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Problema {
	
	private Integer status;				//Numero de erro da requisição
	private LocalDateTime dataHora;		//data e hora do erro
	private String titulo;				//Título geral do erro
	
	private List<Campo> campos;			//Lista com o erros, contendo o nome e a mensagem de erro
 	
	@Getter
	@AllArgsConstructor
	public static class Campo{
		
		private String nome;
		private String mensagem;
	}
}

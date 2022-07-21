package com.algaworks.algalog.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

//Lombok - Cria os metodos set e get para os atributos da classe, e hashCode e Equals
@EqualsAndHashCode(onlyExplicitlyIncluded = true)  //por padrao ele faz o hashcode e equals para todos os atributos 
@Getter												//porem vamos indicar quais o atributos que queremos com outra annotation
@Setter
@Entity   
@Table(name = "cliente") // como a tabela tem o mesmo nome da entidade, nao precisa dessa annotation mas coloquei para conhecimento
public class Cliente {
	//Essas annotations indicam quem é o ID e que ele é autoincremental com base na regra do banco de dados
	@EqualsAndHashCode.Include	//Indica que essa variavel deve ser usado para criar o hashCode e Equals
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank				//Evita que o nome seja "null" ou esteja vazio ""
	@Size(max=90)			//Define tamanho maximo (Tem que ser condizente com o banco)
	private String nome;
	
	@NotBlank				
	@Size(max=255)
	@Email					//Verifica se a syntaxe do email está correta
	private String email;
	
	@NotBlank
	@Column(name = "fone")	//Como mudamos o nome da coluna telefone para "fone",  precisamos dessa annotation
	@Size(max=20)
	private String telefone;
	
}


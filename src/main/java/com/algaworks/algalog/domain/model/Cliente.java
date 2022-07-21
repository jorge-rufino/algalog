package com.algaworks.algalog.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	private String nome;
	private String email;
	
	@Column(name = "fone")	//Como mudamos o nome da coluna telefone para "fone",  precisamos dessa annotation
	private String telefone;
	
}


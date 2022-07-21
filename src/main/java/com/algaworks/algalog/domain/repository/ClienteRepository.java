package com.algaworks.algalog.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algalog.domain.model.Cliente;
//É responsavel por acessar o Banco de Dados
//Somente com isso o Spring já cria um componente para fazer o CRUD no banco

//Os parametros a serem passados para o JPA são: a entidade/classe (Cliente) e o tipo do Id (Long)
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	//Metodo especifico padrao spring data - Faz uma busca exata pelo nome
	List<Cliente> findByNome(String nome);	//Tem que seguir um padrao de nomenclatura desses metodos
				//como iremos fazer uma busca, devemos iniciar com "findBy" mais o nome do atributo que queremobs
				//no caso iremos buscar por nome entao "findByNome"
				
	//Este metodo traz resultados que contenham em qualquer lugar a string.... o metodo de cima faz uma busca exata
	List<Cliente> findByNomeContaining(String nome);	
}

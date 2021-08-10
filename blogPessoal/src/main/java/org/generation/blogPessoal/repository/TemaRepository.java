package org.generation.blogPessoal.repository;

import java.util.List;

import org.generation.blogPessoal.model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemaRepository extends JpaRepository<Tema, Long>{
// consultas personalizadas, para procurar pelo tema
	
	public List<Tema> findAllByDescricaoContainingIgnoreCase(String descricao); //é a descrição que montamos em 'tema'
	// select * from blogPessoal + where titulo + like "%titulo%"
	
	
}


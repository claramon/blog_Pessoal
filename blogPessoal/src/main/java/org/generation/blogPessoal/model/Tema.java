package org.generation.blogPessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity // evidencia que os dados precisam ser guardados em banco de dados
@Table(name = "tb_tema")// cerate table postagem 
public class Tema {

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment + not null
	private long id; // bigint
	
	@NotNull
	private String descricao;
	
	@OneToMany(mappedBy = "tema", cascade = CascadeType.ALL)// integridade da tabela, não permite que delete temas que estejam sendo usados
	// assim, o cascade serve para, caso haja alteração em um tema, todas as postagens que o contenha sofrerão alteração
	@JsonIgnoreProperties("tema")
	private List <Postagem> postagem; // qdo chegar em 'postagem'(azul), pára de apresentar informação 

	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}
	
	
}

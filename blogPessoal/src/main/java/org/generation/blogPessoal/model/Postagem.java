package org.generation.blogPessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


//anotações: parâmetros para as classes

@Entity // para que o o hibernate entenda essa classe como entidade
@Table(name = "postagem") //criando a tabela no database
public class Postagem {

	@Id // configura que o id declarado abaixo é o ID da postagem
	@GeneratedValue(strategy = GenerationType.IDENTITY) // ID virará primary key no database
	private long id;
	
	@NotNull
	@Size(min = 5, max = 100)
	private String titulo;
	
	@NotNull
	@Size(min=10, max=500)
	private String texto;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date = new java.sql.Date(System.currentTimeMillis()); //já captura a data e hr que a postagem foi feita

	@ManyToOne //inner join entre 'postagem' e 'tema'
	@JsonIgnoreProperties("postagem") // propriedade a ser ignorada dentro de 'tema' => postagem
	private Tema tema;
	
	
	public long getId() { //getters and setters
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Tema getTema() {
		return tema;
	}
	public void setTema(Tema tema) {
		this.tema = tema;
	}
	
}

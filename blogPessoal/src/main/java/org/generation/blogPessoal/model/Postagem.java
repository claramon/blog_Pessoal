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

	@Id // ID virará primary key no database
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment + not null
	private long id; // bigint
	
	@NotNull
	@Size(min = 5, max = 500)
	private String titulo; // varchar (100)
	
	@NotNull
	@Size(min=10, max=500)
	private String texto; //varchar (500)
	
	@Temporal(TemporalType.TIMESTAMP) // datatime
	private Date date = new java.sql.Date(System.currentTimeMillis()); //já captura a data e hr que a postagem foi feita

	@ManyToOne //inner join entre 'postagem' e 'tema'
	@JsonIgnoreProperties("postagem") // propriedade a ser ignorada dentro de 'tema' => postagem
	private Tema tema;
	
	@ManyToOne
	@JsonIgnoreProperties ("postagem")
	private Usuario usuario;
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
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

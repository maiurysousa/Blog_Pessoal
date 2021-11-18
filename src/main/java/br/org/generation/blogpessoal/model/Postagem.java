package br.org.generation.blogpessoal.model;

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

@Entity // indica que a classe será uma entidade do jpa hibernate e será mapeada como
		// tabela
@Table(name = "tb_postagens")
public class Postagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	/* Primary key que tem como finalidade incrementar
	 *um valor a cada nova inserção / faz relação entre a culuna id  do mySQL e do STS*/
	
	private long id;

	@NotNull(message = "O atributo título é obrigatório!")
	@Size(min = 5, max = 100, message = "O título deve conter no mínimo 5 e no máximo 100 caracteres.")
	private String titulo;

	@NotNull(message = "O atributo título é obrigatório!")
	@Size(min = 10, max = 1000, message = "O texto deve conter no mínimo 10 e no máximo 1000 caracteres.")
	private String texto;

	@Temporal(TemporalType.TIMESTAMP) // indica que será trabalhado com tempo
	private Date data = new java.sql.Date(System.currentTimeMillis());
	 /* Contador retorna um número (milisegundos) a
	  * partir da hora e local da máquina*/

	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;

	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;

	public long getId() {
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}

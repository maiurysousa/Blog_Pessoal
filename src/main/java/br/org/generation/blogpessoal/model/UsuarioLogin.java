package br.org.generation.blogpessoal.model;

public class UsuarioLogin {
	
	/* Essa Classe não terá nenhuma annotation porque não irá gerar 
	 * uma tabela no Banco de Dados.
	 * Sua principal função é servir de apoio ao processo de login na api.
	 */

	private long id;

	private String nome;

	private String usuario;

	private String senha;

	private String token;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
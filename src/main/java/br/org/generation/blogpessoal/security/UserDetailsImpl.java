package br.org.generation.blogpessoal.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.org.generation.blogpessoal.model.Usuario;

public class UserDetailsImpl implements UserDetails {
	/* A Classe UserDetailsImpl implementa a interface UserDetails, que descreve o usuário para 
	 * o Spring Security,ou seja, detalha as caracteríticas do usuário.
	 * 
	 * O sufixo Impl para indicar que se trata de uma implementação.
	*/
	private static final long serialVersionUID = 1L;

	private String userName; //Credencial do usuário
	private String password; //Credencial do usuário
	private List<GrantedAuthority> authorities; //o que ele pode e não pode fazer
	//Autorição feita através da Collection authorities do tipo GrantedAuthority
	

	public UserDetailsImpl(Usuario usuario) { //Método construtor com parâmetros 
		/* Este método Construtor recebe um objeto Usuario e
		 * recupera os dados necessários através dos respectivos métodos Get*/
		this.userName = usuario.getUsuario();
		this.password = usuario.getSenha();
	}

	public UserDetailsImpl() {	} //Método construtor sem parâmetros

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		/*  Sobrecarrega (@Override) o método que retorna as Autorizações
		 *  da conta do usuário. Nesta implementação, não há nenhuma autorização
		 *  negada*/
		
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {

		return userName;
	}

	@Override
	public boolean isAccountNonExpired() { //Método que Indica se a conta do usuário 
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { //Método que Indica se o usuário está bloqueado ou desbloqueado.
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {//Método indica se as  credenciais do usuário (senha) expiraram. 
		return true;
	}

	@Override
	public boolean isEnabled() {
		/*Método que indica se o usuário 
	 *  está habilitado ou desabilitado.
	 *  Se mudar para false nenhum usuário conseguirá logar.*/
		return true;
	}
}

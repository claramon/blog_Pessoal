package org.generation.blogPessoal.seguranca;

import java.util.Collection;

import org.generation.blogPessoal.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails{ // implements = aplicar uma regra de negócio que já existe da interface UserDetails

	private static final long serialVersionUID = 1L; // classe para controle interno
	
	private String userName;
	private String password;
	
	public UserDetailsImpl(Usuario user) { //populariza o método user com login e senha
		this.userName = user.getUsuario();
		this.password = user.getSenha();
	}
	
	public UserDetailsImpl() {} //PORQUE?
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password; // vem "null", colocamos o que queremos que retorne
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName; // vem "null", colocamos o que queremos que retorne
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true; // não utilizamos, mas colocamos como 'true' (boas práticas) para que retorne o resultado esperado
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true; // não utilizamos, mas colocamos como 'true' (boas práticas) para que retorne o resultado esperado
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true; // não utilizamos, mas colocamos como 'true' (boas práticas) para que retorne o resultado esperado
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true; // não utilizamos, mas colocamos como 'true' (boas práticas) para que retorne o resultado esperado
	}

}

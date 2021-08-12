package org.generation.blogPessoal.seguranca;

import java.util.Collection;
import java.util.List;

import org.generation.blogPessoal.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails{ // implements = aplicar uma regra de negócio que já existe da interface UserDetails

	private static final long serialVersionUID = 1L; // classe para controle interno
	
	private String userName;
	private String password;
	private List<GrantedAuthority> authorities;
	
	public UserDetailsImpl(Usuario user) { // popula o método user com login e senha, obrigada o usuário a por email e senha
		this.userName = user.getUsuario();
		this.password = user.getSenha();
	}
	
	public UserDetailsImpl() {} // para instanciar algo sem precisar passar o método, poed puxar outras coisas do usuário, não só email e senha, mas o nome tmb, por exemplo
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
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

package org.generation.blogPessoal.seguranca;

import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // trata-se de um serviço
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioRepository userRepository; // repositório para manipulação de dados dos usuários
	
	@Override // sobrescrita de metodo
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{ // throw = tratativa de erro, joga isso se der erro 
		Optional<Usuario> user = userRepository.findByUsuario(userName); // objeto optional com nome user, que vai receber o repositório com filtro + username
		user.orElseThrow(() -> new UsernameNotFoundException(userName + "not found. ")); // chama o user com método orelsethrow, caso de erro chama o not found
		
		return user.map(UserDetailsImpl::new).get(); // entregará um novo userdetails, como é um objeto option, usamos get para extrair o que tem dentro do objeto
	}
}


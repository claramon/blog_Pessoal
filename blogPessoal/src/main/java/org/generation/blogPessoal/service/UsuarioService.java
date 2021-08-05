package org.generation.blogPessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service// é um serviço 
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public Usuario CadastrarUsuario(Usuario usuario) {// regra de negócio - cadastrar usuário
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();// é o encoder - colocamos esse BCryptPasswordEncoder na classe de configuração
	
		String senhaEncoder = encoder.encode(usuario.getSenha()); // recebe o objeto encoder e pede uma senha, que receberá do usuario
		usuario.setSenha(senhaEncoder); // acessar e modificar o atributo senha, e passa a senha encriptada (acima)
		
		return repository.save(usuario); // vamos salvar o usuario com a senha encriptada
	}
	
	public Optional<UserLogin> Logar(Optional<UserLogin> user){// regra de negócio - login => não usa mais o tipo usuário, mas user login, pq eu quero devolver pro usuário das informções do userlogin (nome, usuario, senha e token)
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // instancia o encoder novamente
		Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario()); // cria obejto do tipo optional, que recebe usuario, usa o metodo que criamos no repository (findbyusuario), fazendo a pesquisa pelo nome do usuario que o cliente digitou
		
		if(usuario.isPresent()) { // se o obj usuario tiver algo dentro...
			if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {//... vou comparar a senha encriptada com a que o usuario digitou
				
				String auth = user.get().getUsuario() + ":" + user.get().getSenha();// devolver a senha encriptada, concatena duas infos: usuario + senha dentro da string
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));// rede byte: pega o encode com base 64 e recebe a string acima, com o formato de byte que quero "us-ascii"
				String authHeader = "Basic " + new String(encodedAuth); // converte a rede byte em string
				
				user.get().setToken(authHeader); //preenchi o token
				user.get().setNome(usuario.get().getNome()); // acessa o user com o metodo set e coloca o que veio no username (usuario.get().getNome())
				
				return user;
			}
		}
		return null;
	}
	
}

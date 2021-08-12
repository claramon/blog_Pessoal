package org.generation.blogPessoal.controller;

//import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.model.Usuario;
//import org.generation.blogPessoal.repository.UsuarioRepository;
import org.generation.blogPessoal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// passa pelo filtro de segurança, mas é liberado

@RestController
@RequestMapping("/usuarios") //lá do basicsecurityconfig permitimos dois parametros: usuarios/logar e usuarios/cadastrar
@CrossOrigin(origins = "*", allowedHeaders = "*") // qualquer origem, qualquer informação
public class UsuarioController {

	@Autowired // injetar a classe de serviços, ao invés do repository
	private UsuarioService usuarioService; //cadastrar e logar são POST, o usuário não tem acesso como aconteceria se fosse um GET (iria pelo url), pela body como acontece com POST é mais seguro
	
	//@Autowired
	//private UsuarioRepository usuarioRepository;
	
	
	//@GetMapping("/all")
	//public ResponseEntity<List<Usuario>> GetAll() {
	//	return ResponseEntity.ok(usuarioRepository.findAll());
	//}

	//@GetMapping("/{id}")
	//public ResponseEntity<Usuario> GetById(@PathVariable long id){
	//	return usuarioRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
	//			.orElse(ResponseEntity.notFound().build());
	//}
	
	@PostMapping("/logar")
	public ResponseEntity<UserLogin> Autentication(@RequestBody Optional<UserLogin> user){ // optional - pode vir algo ou não
		return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp)) //puxa o metodo de logar que criamos, se vier algo, reponde ok na body
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()); // se o login não for feito ou der erro, tem que retornar que não tem autorização. O build monta a body
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Optional<Usuario>> Post(@RequestBody Usuario usuario){ 
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(usuarioService.cadastrarUsuario(usuario));// se vai ter que cadastrar, vai ter que passar por uma regra de negócio
	}
	
	
	//@PutMapping("/alterar")
	//public ResponseEntity<Usuario> Put(@RequestBody Usuario usuario){
	//	Optional<Usuario> usuarioUpdate = usuarioService.atualizarUsuario(usuario);
	//	try {
	//		return ResponseEntity.ok(usuarioUpdate.get());
	//	} catch (Exception e) {
	//		return ResponseEntity.badRequest().build();
	//	}
	
}





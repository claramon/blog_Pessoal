package org.generation.blogPessoal.controller;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//configuração do controler para receber requisições

@RestController
@RequestMapping("/postagens")
@CrossOrigin("*") //"*" = aceitar requisições de qualquer origem
public class PostagemController {

	@Autowired
	private PostagemRepository repository; //não dá para instanciar uma interface. Ao chamar o autowired, o spring faz sozinho
	
	@GetMapping //para esse método funcionar, precisa dessa anotação. Quando vir uma requisição externa através da URI "postagens", se o método for GET, vai disparar essa resposta (return)
	public ResponseEntity<List<Postagem>> GetAll(){ //primeiro método 
		return ResponseEntity.ok(repository.findAll()); // retorna um objeto (ResponseEntity) com resposta http "ok" e dentro da body, a requisição de todas as postagens 
	}
	
	@GetMapping("/{id}")// qdo for feito um método get em postagem e passar um atributo id: 
	public ResponseEntity<Postagem> GetById(@PathVariable long id){ //uma variável pelo caminho da URI
		return repository.findById(id) // retorna a interface que criamos (repository), que vai chamar o método findbyid...
				.map(resp -> ResponseEntity.ok(resp))// que pode tanto devolver um objeto do tipo postagem (ok)...
				.orElse(ResponseEntity.notFound().build());// ou devolver um erro, caso o objeto não exista ou se ocorrer erro na requisição
	}
	
	@GetMapping("/titulo/{titulo}") // caminho titulo + atributo titulo
	// o que vem depois da barra, o back entende como sendo um atributo (pode ter qualquer nome),
	//se deixasse só com um "titulo" com barra e chaves, daria duplicidade
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo) {// path variable = estou pegando um caminho dentro da URI
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping//inserção
	public ResponseEntity<Postagem> post (@RequestBody Postagem postagem){ //qdo um objeto é grande, seguro, o usuário não vê, portanto passa pelo corpo da requisição
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));// endpoit de postagem
		//ao testar no postman, não passamos o ID
	}
	
	@PutMapping//atualização
	public ResponseEntity<Postagem> put (@RequestBody Postagem postagem){ //qdo um objeto é grande, seguro, o usuário não vê, portanto passa pelo corpo da requisição
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
		//ao testar no postman, passamos o ID que queremos alterar
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) { //só retorna o status 'ok' de 'deletado', a própria API devolve para o cliente, não precisamos fazer um return
		repository.deleteById(id);
	}	
}







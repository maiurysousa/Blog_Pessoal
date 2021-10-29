package br.org.generation.blogpessoal.controller;

import java.util.List;

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

import br.org.generation.blogpessoal.model.Postagem;
import br.org.generation.blogpessoal.repository.PostagemRepository;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll(){
		return ResponseEntity.ok(postagemRepository.findAll());
		// select * from tb_postagens;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable long id){
		return postagemRepository.findById(id) //método de busca
				.map(resposta -> ResponseEntity.ok(resposta)) // mostra e dá um status de ok//ou vai receber o dado ou um objto nulo
				.orElse(ResponseEntity.notFound().build());
		// select * from tb_postagens where id = 1;
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity <List <Postagem>> getById(@PathVariable String titulo){
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo)); //método de busca
	
	}
	
	@PostMapping
	public ResponseEntity<Postagem> postPostagem(@RequestBody Postagem postagem){ //parâmetro do tipo postagem que vou chamar depostagem
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
	}
	
	@PutMapping
	public ResponseEntity<Postagem> putPostagem(@RequestBody Postagem postagem){ //parâmetro do tipo postagem que vou chamar depostagem
		return ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem));
	}
	
	@DeleteMapping("/{id}")
	public void deletePostagem (@PathVariable long id) {
		postagemRepository.deleteById(id);
	}
	
}

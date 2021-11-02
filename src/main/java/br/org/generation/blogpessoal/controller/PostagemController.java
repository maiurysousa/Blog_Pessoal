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
	
	@GetMapping //retorna a lista com todos os recursos que estão no endereço postagens
	public ResponseEntity<List<Postagem>> getAll(){
		return ResponseEntity.ok(postagemRepository.findAll());
		// select * from tb_postagens;
	}
	
	@GetMapping("/{id}") //retorna um recurso específico indentificado pelo id
	public ResponseEntity<Postagem> getById(@PathVariable long id){ // tipo long = bigint
		return postagemRepository.findById(id) // método de busca - Encontre pelo id 
				.map(resposta -> ResponseEntity.ok(resposta)) // Um papa que vai chegar a informação e trazer ela de volta com um status de ok ou receber o dado/objeto nulo
				.orElse(ResponseEntity.notFound().build()); // if else / notfound - mensagem de  erro 404 - não encontrado / build - uma forma de compactar informaçãoes e mandar de volta
		// select * from tb_postagens where id = 1;
	}
	
	@GetMapping("/titulo/{titulo}")  //retorna todos os recursos que contem um ou mais chars informados pelo cliente
	public ResponseEntity <List <Postagem>> getById(@PathVariable String titulo){
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo)); //método de busca
	}
	
	@PostMapping    //insere um novo recurso
	public ResponseEntity<Postagem> postPostagem(@RequestBody Postagem postagem){ //parâmetro do tipo postagem que será chamado de postagem
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
	}
	
	@PutMapping    //atualiza um recurso existente, caso o recurso não exista retorna um notFound
	public ResponseEntity<Postagem> putPostagem(@RequestBody Postagem postagem){ 
		//return ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem));
		return postagemRepository.findById(postagem.getId())
				.map(resposta -> ResponseEntity.ok(postagemRepository.save(postagem)))
				.orElse(ResponseEntity.notFound().build());				
	}
	
	@DeleteMapping("/{id}")    //deleta um recurso existente, caso o recurso não exista retorna um notFound
	public ResponseEntity<?> deletePostagem (@PathVariable long id) {
		//public void deletePostagem (@PathVariable long id) {
		//postagemRepository.deleteById(id);
		return postagemRepository.findById(id)
				.map(checagem -> {postagemRepository.deleteById(id);
				return ResponseEntity.ok().build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
}

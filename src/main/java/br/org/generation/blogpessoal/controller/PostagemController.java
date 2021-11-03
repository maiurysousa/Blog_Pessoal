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

@RestController //informa p/ o Spring q a classe é um controlador
@RequestMapping("/postagens") // URI pela qual a classe será acessada
@CrossOrigin(origins = "*", allowedHeaders = "*") // a classe vai aceitar requisições de qlqr origem
public class PostagemController {
	
	@Autowired // garante que os serviçoes da interface seja acessado a patir do controller
	private PostagemRepository postagemRepository;
	
	@GetMapping //retorna a lista com todos os recursos que estão no endereço postagens
	public ResponseEntity<List<Postagem>> getAll(){
		return ResponseEntity.ok(postagemRepository.findAll());
		// select * from tb_postagens;
	}
	
	@GetMapping("/{id}") //retorna um recurso específico indentificado pelo id
	public ResponseEntity<Postagem> getById(@PathVariable long id){ // tipo do id = bigint
		return postagemRepository.findById(id) // método de busca - Encontre pelo id 
				.map(resposta -> ResponseEntity.ok(resposta)) // mapeia a informação, checa ela e traz de volta com um status de ok ou recebe o dado/objeto nulo
				.orElse(ResponseEntity.notFound().build()); // se não, devolve uma mensagem de erro 404 (não encontrado) / build - uma forma de compactar informaçãoes e mandar de volta
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
	
	@PutMapping    //atualiza um recurso existente e caso não exista retorna um notFound
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
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
}

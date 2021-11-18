package br.org.generation.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.blogpessoal.model.Postagem;

@Repository // indica que a classe se trata de um repositório
public interface PostagemRepository extends JpaRepository<Postagem, Long>{
	public List <Postagem> findAllByTituloContainingIgnoreCase(String titulo); 
	// buscar tudo que contem dentro do título sem levar em consideração maiúscula ou minuscula
}

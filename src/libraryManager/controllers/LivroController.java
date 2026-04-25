package libraryManager.controllers;

import java.util.List;

import libraryManager.entities.Livro;
import libraryManager.services.LivroService;

public class LivroController {

	private LivroService livroService;
	// injecao dependencia por construtor
	public LivroController(LivroService livroService) {
		this.livroService = livroService;
	}
	
	// .......................................................................
	public Livro findById(Long id) {
		if (id == null) {
			throw new RuntimeException("Campo Obrigatório!");
		}
		return livroService.findById(id);
	}
	
	// .......................................................................
	public List<Livro> findAll() {
		return livroService.findAll();
	}
	
	// .......................................................................
	public void insert(Livro livro) {
		if (livro.getTitulo() == null || livro.getTitulo().trim().isBlank()) {
			throw new RuntimeException("Campo Obrigatório!");
		}
		if (livro.getAutor() == null) {
			throw new RuntimeException("Campo Obrigatório!");
		}
		livroService.insert(livro);
	}
	
	// .......................................................................
	public void update(Long id, Livro livro) {
		livroService.update(id, livro);
	}
	
	// .......................................................................
	public void delete(Long id) {
		livroService.delete(id);
	}
}

package libraryManager.controllers;

import java.util.List;

import libraryManager.entities.Autor;
import libraryManager.services.AutorService;

public class AutorController {

	private AutorService autorService;

	public AutorController(AutorService autorService) {
		this.autorService = autorService;
	}
//.......................................................................
	public void insert(Autor autor) {
		if(autor.getNome() == null || autor.getNome().trim().isBlank()) {
			System.out.println("Campo obrigatório!");
            return;
		}
		if(autor.getNacionalidade() == null || autor.getNacionalidade().trim().isBlank()) {
			System.out.println("Campo obrigatório!");
            return;
		}
		autorService.insert(autor);
	}
//.......................................................................
	public Autor findById(Long id) {
		if(id == null) {
			throw new RuntimeException("Campo Obrigatório!");
		}
		return autorService.findById(id);
	}
//.......................................................................
	public List<Autor> findAll() {

		return autorService.findAll();
	}
//.......................................................................
	public void update(Long id, Autor autor) {
		
		autorService.update(id, autor);
	}
//.......................................................................
	public void remove(int id) {

	}

}

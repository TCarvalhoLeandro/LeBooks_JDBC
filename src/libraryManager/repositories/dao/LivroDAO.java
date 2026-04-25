package libraryManager.repositories.dao;

import java.util.List;

import libraryManager.entities.Livro;

public interface LivroDAO {

	Livro findById(Long id);
	Livro findByName(String titulo);
	List<Livro> findAll();
	void insert(Livro livro);
	void update(Long id, Livro livro);
	void delete(Long id);
}

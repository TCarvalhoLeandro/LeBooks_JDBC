package libraryManager.repositories.dao;

import java.util.List;

import libraryManager.entities.Autor;

public interface AutorDAO {

	void insert(Autor autor);
	Autor findByName(String email);
	Autor findById(Long id);
	List<Autor> findAll();
	void update(Long id, Autor autor);
	void remove(int id);
}

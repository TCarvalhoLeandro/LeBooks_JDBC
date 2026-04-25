package libraryManager.repositories.dao;

import java.util.List;

import libraryManager.entities.Leitor;

public interface LeitorDAO {
	
	Leitor findById(Long id);
	Leitor findByName(String nome);
	List<Leitor> findAll();
	void insert(Leitor leitor);
	void update(Long id, Leitor leitor);
	void delete(Long id);
}

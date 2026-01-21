package biblioteca.model.dao;

import java.util.List;

import biblioteca.entities.Livro;

public interface LivroDao {

	void insert(Livro livro);
	void update(Livro livro);
	void deleteById(Integer id);
	Livro findById(Integer id);
	List<Livro> findAll();
}

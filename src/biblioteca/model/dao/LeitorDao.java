package biblioteca.model.dao;

import java.util.List;

import biblioteca.entities.Leitor;

public interface LeitorDao {
	
	void insert(Leitor leitor);
	void update(Leitor leitor);
	void deleteById(Integer id);
	Leitor findById(Integer id);
	List<Leitor> findAll();

}

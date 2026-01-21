package biblioteca.model.dao;

import java.util.List;

import biblioteca.entities.Emprestimo;

public interface EmprestimoDao {
	
	void insert(Emprestimo emp);
	void update(Emprestimo emp);
	void deleteById(Integer id);
	Emprestimo findById(Integer id);
	List<Emprestimo> findAll();

}

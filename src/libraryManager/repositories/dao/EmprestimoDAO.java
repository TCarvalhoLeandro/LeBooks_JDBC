package libraryManager.repositories.dao;

import java.time.LocalDate;
import java.util.List;

import libraryManager.entities.Emprestimo;

public interface EmprestimoDAO {

	Emprestimo findById(Long id);
	Emprestimo findByData(LocalDate data);
	List<Emprestimo> findAll();
	void insert(Emprestimo emprestimo);
	void returnBook(Long id, Emprestimo emp);
	
}

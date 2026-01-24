package biblioteca.model.dao;

import java.util.List;

import biblioteca.entities.Emprestimo;

public interface EmprestimoDao {
	
	void insert(Emprestimo emp);
	void registerReturn(Emprestimo emp);
	List<Emprestimo> findAtivos();// Metodo especifico para devolucao
	Emprestimo findById(Integer id);//Apenas se o emprestimo for feito por erro (cancelar)
	List<Emprestimo> findAll();

}

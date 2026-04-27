package libraryManager.controllers;

import java.util.List;

import libraryManager.entities.Emprestimo;
import libraryManager.services.EmprestimoService;

public class EmprestimoController {

	private EmprestimoService emprestimoService;

	public EmprestimoController(EmprestimoService emprestimoService) {
		this.emprestimoService = emprestimoService;
	}

	// .......................................................................
	public Emprestimo findById(Long id) {
		if (id == null) {
			throw new RuntimeException("Campo Obrigatório!");
		}
		return emprestimoService.findById(id);
	}

	// .......................................................................
	public List<Emprestimo> findAll() {
		return emprestimoService.findAll();
	}

	// .......................................................................
	public void insert(Long leitor_id, Long livro_id) {
		if (leitor_id == null || livro_id == null) {
			throw new RuntimeException("Campo Obrigatório!");
		}
		
		emprestimoService.insert(leitor_id, livro_id);
	}

	// .......................................................................
	public void returnBook(Long id) {
		if (id == null) {
			throw new RuntimeException("Campo Obrigatório!");
		}
		emprestimoService.returnBook(id);
	}

}

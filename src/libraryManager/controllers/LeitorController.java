package libraryManager.controllers;

import java.util.List;

import libraryManager.entities.Leitor;
import libraryManager.services.LeitorService;

public class LeitorController {

	private LeitorService leitorService;

	public LeitorController(LeitorService leitorService) {
		this.leitorService = leitorService;
	}

	// .......................................................................
	public Leitor findById(Long id) {
		if (id == null) {
			throw new RuntimeException("Campo Obrigatório!");
		}
		return leitorService.findById(id);
	}

	// .......................................................................
	public List<Leitor> findAll() {
		return leitorService.findAll();
	}

	// .......................................................................
	public void insert(Leitor leitor) {
		if (leitor.getNome() == null || leitor.getNome().trim().isBlank()) {
			throw new RuntimeException("Campo Obrigatório!");
		}
		leitorService.insert(leitor);
	}

	// .......................................................................
	public void update(Long id, Leitor leitor) {
		leitorService.update(id, leitor);
	}

	// .......................................................................
	public void delete(Long id) {
		leitorService.delete(id);
	}

}

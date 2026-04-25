package libraryManager.services;

import java.util.List;

import libraryManager.entities.Leitor;
import libraryManager.repositories.dao.LeitorDAO;
import libraryManager.services.exceptions.RegraNegocioException;

public class LeitorService {

	private LeitorDAO leitorDao;

	public LeitorService(LeitorDAO leitorDao) {
		this.leitorDao = leitorDao;
	}

	// .......................................................................
	public Leitor findById(Long id) {
		Leitor leitor = leitorDao.findById(id);
		if (leitor != null) {
			return leitor;
		} else {
			throw new RuntimeException("Livro inexistente");
		}
	}

	// .......................................................................
	public List<Leitor> findAll() {
		return leitorDao.findAll();
	}

	// .......................................................................
	public void insert(Leitor leitor) {
		Leitor leitorExistente = leitorDao.findByName(leitor.getNome());
		// regra de negócio
		if (leitorExistente == null) {
			// Pode mandar o DAO salvar no banco.
			leitorDao.insert(leitor);
		} else {
			// Barrar o fluxo.
			throw new RegraNegocioException("Leitor já existe!");
		}
	}

	// .......................................................................
	public void update(Long id, Leitor leitor) {
		leitorDao.update(id, leitor);
	}

	// .......................................................................
	public void delete(Long id) {
		Leitor leitor = findById(id);
		if (leitor != null) {
			leitorDao.delete(id);
		} else {
			throw new RuntimeException("Livro inexistente");
		}
	}
}

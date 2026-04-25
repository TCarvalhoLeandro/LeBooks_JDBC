package libraryManager.services;

import java.util.List;

import libraryManager.entities.Livro;
import libraryManager.repositories.dao.LivroDAO;
import libraryManager.services.exceptions.RegraNegocioException;

public class LivroService {

	private LivroDAO livroDao;

	public LivroService(LivroDAO livroDao) {
		this.livroDao = livroDao;
	}

	// .......................................................................
	public Livro findById(Long id) {
		Livro livro = livroDao.findById(id);
		if (livro != null) {
			return livro;
		} else {
			throw new RuntimeException("Livro inexistente");
		}
	}

	// .......................................................................
	public List<Livro> findAll() {
		return livroDao.findAll();
	}

	// .......................................................................
	public void insert(Livro livro) {
		Livro autorExistente = livroDao.findByName(livro.getTitulo());
		// regra de negócio
		if (autorExistente == null) {
			// Pode mandar o DAO salvar no banco.
			livroDao.insert(livro);
		} else {
			// Barrar o fluxo.
			throw new RegraNegocioException("Livro já existe!");
		}
	}

	// .......................................................................
	public void update(Long id, Livro livro) {
		livroDao.update(id, livro);
	}

	// .......................................................................
	public void delete(Long id) {
		Livro livro = findById(id);
		if (livro != null) {
			livroDao.delete(id);
		} else {
			throw new RuntimeException("Livro inexistente");
		}
	}
}

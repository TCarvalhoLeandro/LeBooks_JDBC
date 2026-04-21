package libraryManager.services;

import java.util.List;

import libraryManager.entities.Autor;
import libraryManager.repositories.dao.AutorDAO;
import libraryManager.services.exceptions.RegraNegocioException;

public class AutorService {

	private AutorDAO autorDao;

	public AutorService(AutorDAO autorDao) {
		this.autorDao = autorDao;
	}
//.......................................................................
	public void insert(Autor autor) {
		Autor autorExistente = autorDao.findByName(autor.getNome());
		// regra de negócio
        if (autorExistente == null) {
            // Pode mandar o DAO salvar no banco.
            autorDao.insert(autor);
        } else {
            // Barrar o fluxo.
            throw new RegraNegocioException("Autor já existe!");
        }
	}
//.......................................................................	
	public Autor findById(Long id) {
		Autor autor = autorDao.findById(id);
		if(autor != null) {
			return autor;
		}
		else {
			throw new RuntimeException("Autor inexistente");
		}
	}
//.......................................................................	
	public List<Autor> findAll() {
		return autorDao.findAll();
	}
//.......................................................................	
	public void update(Long id, Autor autor) {
		autorDao.update(id, autor);
		
	}
//.......................................................................	
	public void remove(int id) {
		
	}
	
}

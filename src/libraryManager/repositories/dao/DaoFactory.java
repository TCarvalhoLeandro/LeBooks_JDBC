package libraryManager.repositories.dao;

import libraryManager.db.DB;
import libraryManager.repositories.dao.impl.AutorDaoImpl;
import libraryManager.repositories.dao.impl.LivroDaoImpl;

public class DaoFactory {

	public static AutorDAO createAutorDao() {
		return new AutorDaoImpl(DB.getConnection());
	}
	
	public static LivroDAO createLivroDao() {
		return new LivroDaoImpl(DB.getConnection(), createAutorDao());
	}
}

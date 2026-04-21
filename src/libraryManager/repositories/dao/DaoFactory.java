package libraryManager.repositories.dao;

import libraryManager.db.DB;
import libraryManager.repositories.dao.impl.AutorDaoImpl;

public class DaoFactory {

	public static AutorDAO createAutorDao() {
		return new AutorDaoImpl(DB.getConnection());
	}
}

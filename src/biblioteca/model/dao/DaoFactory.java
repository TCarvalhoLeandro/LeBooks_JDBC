package biblioteca.model.dao;

import biblioteca.db.DB;
import biblioteca.model.dao.impl.LivroDaoJDBC;

public class DaoFactory {
	
	public static LivroDao createLivroDao() {
		return new LivroDaoJDBC(DB.getConnection());
	}

	
}

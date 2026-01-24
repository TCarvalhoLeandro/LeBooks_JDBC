package biblioteca.model.dao;

import biblioteca.db.DB;
import biblioteca.model.dao.impl.EmprestimoDaoJDBC;
import biblioteca.model.dao.impl.LeitorDaoJDBC;
import biblioteca.model.dao.impl.LivroDaoJDBC;

public class DaoFactory {
	
	public static LivroDao createLivroDao() {
		return new LivroDaoJDBC(DB.getConnection());
	}
	
	
	public static LeitorDao createLeitorDao() {
		return new LeitorDaoJDBC(DB.getConnection());
	}
	
	
	public static EmprestimoDao createEmprestimoDao() {
		return new EmprestimoDaoJDBC(DB.getConnection());
	}
	
}

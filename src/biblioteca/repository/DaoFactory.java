package biblioteca.repository;

import biblioteca.db.DB;
import biblioteca.repository.impl.EmprestimoDaoJDBC;
import biblioteca.repository.impl.LeitorDaoJDBC;
import biblioteca.repository.impl.LivroDaoJDBC;

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

package biblioteca.model.dao;

public class DaoFactory {
	
	public static LivroDao createLivroDao() {
		return new LivroDaoJDBC()
	}

}

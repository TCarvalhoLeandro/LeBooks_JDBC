import biblioteca.entities.Livro;
import biblioteca.model.dao.DaoFactory;
import biblioteca.model.dao.LivroDao;

public class TesteConexao {

	public static void main(String[] args) {
		/*// Substitua pelos seus dados reais
		String url = "jdbc:mysql://root@127.0.0.1:3306/LeandroDataBase";
		String user = "Leandro_DataBase"; // ou "root"
		String password = "1234567";

		System.out.println("Conectando ao banco...");

		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("SUCESSO! Conexão realizada com o MySQL.");
			conn.close();
		} catch (SQLException e) {
			System.err.println("ERRO ao conectar: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Erro de Driver não encontrado? " + e.getMessage());
		}
		*/
		
		LivroDao livroDao = DaoFactory.createLivroDao();
		
		System.out.println("\nTESTE 04: Seller insert: ");
		
		Livro livro = new Livro(null, "Memórias Póstumas de Brás Cubas", "Macahado de Assis", 1881, true); 
		livroDao.insert(livro);
		System.out.println("Insert new seller id: " + livro.getId());
	}
}



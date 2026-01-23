import java.util.ArrayList;
import java.util.List;

import biblioteca.entities.Leitor;
import biblioteca.model.dao.DaoFactory;
import biblioteca.model.dao.LeitorDao;
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
		
		/*
		System.out.println("\nTESTE 01: Livro insert: ");	
		Livro livro = new Livro(null, "A Menina que Roubava Livros", "Markus Zusak", 2005, true); 
		livroDao.insert(livro);
		System.out.println("Insert new seller id: " + livro.getId());
		
		
		System.out.println("TESTE 02: Livro findById: ");
		Livro livro = livroDao.findById(1);
		System.out.println(livro);
		
		
		System.out.println("\nTESTE 03: Livro update: ");
		Livro livro = livroDao.findById(1);
		livro.setTitulo("Quincas Borba");
		livroDao.update(livro);
		System.out.println("Update!!");
		
		
		System.out.println("\nTESTE 04: Livro deleteById: ");
		livroDao.deleteById(2);
		
		
		List<Livro> list = new ArrayList<Livro>();
		list = livroDao.findAll();
		
		for(Livro obj: list) {
			System.out.println(obj);
		}
		*/
		
		LeitorDao leitorDao = DaoFactory.createLeitorDao();
		/*
		System.out.println("\nTESTE 01: Leitor insert: ");	
		Leitor leitor = new Leitor(null, "Alicia Violet", "12345678902", "alicia@gmail.com"); 
		leitorDao.insert(leitor);
		System.out.println("Insert new leitor id: " + leitor.getId());
		
		
		System.out.println("\nTESTE 02: Leitor findById: ");
		Leitor leitorFindById = leitorDao.findById(1);
		System.out.println(leitorFindById);
		
		
		System.out.println("\nTESTE 03: Leitor update: ");
		Leitor leitorUpdate = leitorDao.findById(1);
		leitorUpdate.setEmail("julia@gmail.com");
		leitorDao.update(leitorUpdate);
		System.out.println("Update!!!!!!!!");
		*/
		
		System.out.println("\nTESTE 04: Leitor deleteById");
		leitorDao.deleteById(3);
		System.out.println("Delete!!!!!!");
		
		
		/*
		System.out.println("\nTESTE 05: Leitor findAll ");
		List<Leitor> list = new ArrayList<Leitor>();
		list = leitorDao.findAll();
		
		for(Leitor obj: list) {
			System.out.println(obj);
		}
		*/
		
		
	}
}



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import biblioteca.entities.Emprestimo;
import biblioteca.entities.Leitor;
import biblioteca.entities.Livro;
import biblioteca.repository.DaoFactory;
import biblioteca.repository.EmprestimoDao;
import biblioteca.repository.LeitorDao;
import biblioteca.repository.LivroDao;
import biblioteca.repository.impl.EmprestimoDaoJDBC;


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
		
		
		System.out.println("\nTESTE 04: Leitor deleteById");
		leitorDao.deleteById(3);
		System.out.println("Delete!!!!!!");
		
		
		
		System.out.println("\nTESTE 05: Leitor findAll ");
		List<Leitor> list = new ArrayList<Leitor>();
		list = leitorDao.findAll();
		
		for(Leitor obj: list) {
			System.out.println(obj);
		}
		*/
		
		EmprestimoDao empDao = DaoFactory.createEmprestimoDao();
		
		/*
		System.out.println("\nTESTE 01: Emprestimo insert: ");	
		Livro livroEmp = livroDao.findById(7);// busco um livro no banco
		Leitor leitorEmp = leitorDao.findById(5);
		Emprestimo emprestimo = new Emprestimo(null, leitorEmp, livroEmp, LocalDate.now());
		emprestimo.setDevolvido(false);
		emprestimo.setDataDevolucao(LocalDate.now().plusDays(5));
		empDao.insert(emprestimo);
		System.out.println("Insert new leitor id: " + emprestimo.getId());
		
		
		System.out.println("\nTESTE 02: Emprestimo findById");
		Emprestimo emp = empDao.findById(3);
		System.out.println(emp);
		
		
		
		System.out.println("\nTESTE 03: Emprestimo registerReturn");
		Emprestimo emp = empDao.findById(4);
		empDao.registerReturn(emp);
		
		
		System.out.println("\nTESTE 04: Emprestimo findAll");
		List<Emprestimo> list = new ArrayList<Emprestimo>();
		list = empDao.findAll();
		
		for(Emprestimo obj: list) {
			System.out.println(obj);
		}
		
		*/
		System.out.println("\nTESTE 05: Emprestimo findAtivos");
		List<Emprestimo> listAtivo = new ArrayList<Emprestimo>();
		listAtivo = empDao.findAtivos();
		
		for(Emprestimo obj: listAtivo) {
			System.out.println(obj);
		}
		
	}
}



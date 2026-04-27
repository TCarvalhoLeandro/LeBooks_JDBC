package libraryManager.repositories.dao;

import java.sql.Connection;

import libraryManager.db.DB;
import libraryManager.repositories.dao.impl.AutorDaoImpl;
import libraryManager.repositories.dao.impl.EmprestimoDaoImpl;
import libraryManager.repositories.dao.impl.LeitorDaoImpl;
import libraryManager.repositories.dao.impl.LivroDaoImpl;

public class DaoFactory {

	/**
	 * Cria um AutorDAO com uma NOVA conexão independente.
	 * Uso: Operações simples no banco que não exigem controle de transação manual.
	 */
	public static AutorDAO createAutorDao() {
		return new AutorDaoImpl(DB.getConnection());
	}
	
	/**
	 * Cria um AutorDAO usando uma conexão COMPARTILHADA (recebida por parâmetro).
	 * Uso: Operações que fazem parte de uma transação maior (Commit/Rollback).
	 */
	public static AutorDAO createAutorDao(Connection conn) {
		return new AutorDaoImpl(conn);
	}
	
	/**
	 * Cria um LivroDAO com uma NOVA conexão e injeta um AutorDAO independente.
	 * Uso: Consultas isoladas de livros e seus autores (Auto-commit ativado).
	 */
	public static LivroDAO createLivroDao() {
		return new LivroDaoImpl(DB.getConnection(), createAutorDao());
	}
	
	/**
	 * Cria um LivroDAO usando uma conexão COMPARTILHADA e garante que o 
	 * AutorDAO injetado utilize a exata MESMA conexão.
	 * Uso: Transações seguras (ACID) envolvendo livros e autores simultaneamente.
	 */
	public static LivroDAO createLivroDao(Connection conn) {
		return new LivroDaoImpl(conn, createAutorDao(conn));
	}
	
	/**
	 * Cria um LeitorDAO com uma NOVA conexão independente.
	 */
	public static LeitorDAO createLeitorDao() {
		return new LeitorDaoImpl(DB.getConnection());
	}
	
	public static LeitorDAO createLeitorDao(Connection conn) {
		return new LeitorDaoImpl(conn);
	}
		
	/**
	 * Cria um EmprestimoDAO com uma NOVA conexão independente.
	 * Uso: Buscas isoladas e relatórios de empréstimos.
	 */
	public static EmprestimoDAO createEmprestimoDao() {
		return new EmprestimoDaoImpl(DB.getConnection());
	}
	
	/**
	 * Cria um EmprestimoDAO usando uma conexão COMPARTILHADA.
	 * Uso: Operações complexas como criar ou devolver um empréstimo, onde 
	 * outras tabelas (como Livro) também precisam ser atualizadas na mesma transação.
	 */
	public static EmprestimoDAO createEmprestimoDao(Connection conn) {
		return new EmprestimoDaoImpl(conn);
	}
}

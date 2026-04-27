package libraryManager.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import libraryManager.db.DB;
import libraryManager.domainException.DadosException;
import libraryManager.entities.Emprestimo;
import libraryManager.entities.Leitor;
import libraryManager.entities.Livro;
import libraryManager.repositories.dao.DaoFactory;
import libraryManager.repositories.dao.EmprestimoDAO;
import libraryManager.repositories.dao.LeitorDAO;
import libraryManager.repositories.dao.LivroDAO;

public class EmprestimoService {

	private EmprestimoDAO emprestimoDao;

	public EmprestimoService(EmprestimoDAO emprestimoDao) {
		this.emprestimoDao = emprestimoDao;

	}

	// .......................................................................
	public Emprestimo findById(Long id) {
		Emprestimo emp = emprestimoDao.findById(id);
		if (emp != null) {
			return emp;
		} else {
			throw new RuntimeException("Emprestimo inexistente");
		}
	}

	// .......................................................................
	public List<Emprestimo> findAll() {
		return emprestimoDao.findAll();
	}

	// .......................................................................
	public void insert(Long leitor_id, Long livro_id) {

		Connection conn = DB.getConnection();
		try {
			emprestimoDao = DaoFactory.createEmprestimoDao(conn);
			LivroDAO livroDao = DaoFactory.createLivroDao(conn);
			LeitorDAO leitorDao = DaoFactory.createLeitorDao(conn);
			conn.setAutoCommit(false);

			Livro livro = livroDao.findById(livro_id);
			livro.setDisponivel(false);
			livroDao.update(livro.getId(), livro);
			Leitor leitor = leitorDao.findById(leitor_id);
			
			Emprestimo emp = new Emprestimo();
			emp.setDataEmprestimo(LocalDate.now());
			emp.setDataDevolucao(LocalDate.now().plusDays(7));
			emp.setLeitor(leitor);
			emp.setLivro(livro);
			
			emprestimoDao.insert(emp);

			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.print("Erro rollback: ");
				e1.printStackTrace();
			}
			throw new DadosException("Erro: " + e.getMessage());
		} finally {
			DB.closseConnection();
		}
	}

	// .......................................................................
	public void returnBook(Long id) {

		// 1. PREPARAÇÃO: Obtemos a conexão que será a "maestrina" de toda a operação.
		Connection conn = DB.getConnection();
		try {
			// 2. COMPARTILHAMENTO: Entregamos a MESMA conexão para os DAOs.
			// Isso garante que eles trabalhem juntos na mesma transação.
			emprestimoDao = DaoFactory.createEmprestimoDao(conn);
			LivroDAO livroDao = DaoFactory.createLivroDao(conn);

			// 3. REGRA DE NEGÓCIO EM MEMÓRIA: Buscamos o empréstimo pelo ID e
			// atualizamos os estados do empréstimo (data) e do livro (disponibilidade).
			Emprestimo emp = emprestimoDao.findById(id);
			emp.setDataDevolucaoEfetiva(LocalDate.now());
			Livro livro = emp.getLivro();
			livro.setDisponivel(true);

			// 4. INÍCIO DA TRANSAÇÃO (ACID): Desligamos o salvamento automático.
			// A partir daqui, o banco só salva se dermos a ordem explícita.
			// Dica de Arquitetura: É muito comum colocar essa linha logo no início do bloco
			// 'try'.
			conn.setAutoCommit(false);

			// 5. OPERAÇÕES NO BANCO: Disparamos os updates para as duas tabelas.
			livroDao.update(livro.getId(), livro);
			emprestimoDao.returnBook(id, emp);

			// 6. SUCESSO (COMMIT): Se chegou até aqui sem erros, confirmamos as
			// alterações no banco de dados. Operação Atômica concluída!
			conn.commit();
		} catch (SQLException e) {
			// 7. FALHA (ROLLBACK): Se qualquer erro SQL acontecer (ex: queda de rede),
			// desfazemos TUDO o que tentou ser feito para manter o banco consistente.
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// Repassamos o erro empacotado para a camada superior (ex: Controller) tratar.
				System.out.print("Erro rollback: ");
				e1.printStackTrace();
			}
			throw new DadosException("Erro na operção: " + e.getMessage());
		} finally {
			// 8. LIMPEZA: Independentemente de sucesso ou falha, SEMPRE fechamos a conexão
			// para não esgotar os recursos do banco de dados.
			DB.closseConnection();
		}
	}
}

package libraryManager.repositories.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import libraryManager.db.DbException;
import libraryManager.entities.Autor;
import libraryManager.entities.Emprestimo;
import libraryManager.entities.Leitor;
import libraryManager.entities.Livro;
import libraryManager.repositories.dao.EmprestimoDAO;

public class EmprestimoDaoImpl implements EmprestimoDAO {

	private Connection conn;

	// injecao dependencia por construtor
	public EmprestimoDaoImpl(Connection conn) {
		this.conn = conn;
	}

	// .......................................................................
	@Override
	public Emprestimo findById(Long id) {

		String sql = """
					SELECT
					tb_emprestimo.id AS emp_id,
					tb_emprestimo.dataEmprestimo,
					tb_emprestimo.dataDevolucao,
					tb_emprestimo.dataDevolucaoEfetiva,
					tb_leitor.id AS leitor_id,
					tb_leitor.nome,
					tb_leitor.cpf,
					tb_livro.id AS livro_id,
					tb_livro.titulo,
					tb_livro.ano,
					tb_livro.autor_id
				FROM
					tb_emprestimo
				INNER JOIN tb_leitor ON tb_emprestimo.leitor_id = tb_leitor.id
				INNER JOIN tb_livro ON tb_emprestimo.livro_id = tb_livro.id
				WHERE tb_emprestimo.id = ?
				""";

		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.setLong(1, id);
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					Emprestimo emprestimo = new Emprestimo();

					emprestimo.setId(rs.getLong("emp_id"));

					Leitor leitor = new Leitor();
					leitor.setId(rs.getLong("leitor_id"));
					leitor.setNome(rs.getString("nome"));
					leitor.setCpf(rs.getString("cpf"));
					emprestimo.setLeitor(leitor);

					Livro livro = new Livro();
					livro.setId(rs.getLong("livro_id"));
					livro.setTitulo(rs.getString("titulo"));
					Autor autor = new Autor();
					autor.setId(rs.getLong("autor_id"));
					livro.setAutor(autor);
					livro.setAno(rs.getInt("ano"));
					emprestimo.setLivro(livro);
					
					emprestimo.setDataEmprestimo(rs.getDate("dataEmprestimo").toLocalDate());
					emprestimo.setDataDevolucao(rs.getDate("dataDevolucao").toLocalDate());
					java.sql.Date dataSql = rs.getDate("dataDevolucaoEfetiva");
					if (dataSql != null) {
						emprestimo.setDataDevolucaoEfetiva(dataSql.toLocalDate());
					}
					return emprestimo;
				}
				return null;
			}
		} catch (SQLException e) {
			throw new DbException("Erro ao buscar por Id! " + e.getMessage());
		}
	}

	@Override
	public Emprestimo findByData(LocalDate data) {
		// TODO Auto-generated method stub
		return null;
	}

	// .......................................................................
	@Override
	public List<Emprestimo> findAll() {

		String sql = """
				SELECT
					tb_emprestimo.id AS emp_id,
					tb_emprestimo.dataEmprestimo,
					tb_emprestimo.dataDevolucao,
					tb_emprestimo.dataDevolucaoEfetiva,
					tb_leitor.id AS leitor_id,
					tb_leitor.nome,
					tb_leitor.cpf,
					tb_livro.id AS livro_id,
					tb_livro.titulo
				FROM
					tb_emprestimo
				INNER JOIN tb_leitor ON tb_emprestimo.leitor_id = tb_leitor.id
				INNER JOIN tb_livro ON tb_emprestimo.livro_id = tb_livro.id
				""";

		List<Emprestimo> list = new ArrayList<Emprestimo>();
		try (PreparedStatement st = conn.prepareStatement(sql)) {

			try (ResultSet rs = st.executeQuery()) {

				while (rs.next()) {
					Emprestimo emprestimo = new Emprestimo();

					emprestimo.setId(rs.getLong("emp_id"));

					Leitor leitor = new Leitor();
					leitor.setId(rs.getLong("leitor_id"));
					leitor.setNome(rs.getString("nome"));
					leitor.setCpf(rs.getString("cpf"));
					emprestimo.setLeitor(leitor);

					Livro livro = new Livro();
					livro.setId(rs.getLong("livro_id"));
					livro.setTitulo(rs.getString("titulo"));
					emprestimo.setLivro(livro);
					emprestimo.setDataEmprestimo(rs.getDate("dataEmprestimo").toLocalDate());
					emprestimo.setDataDevolucao(rs.getDate("dataDevolucao").toLocalDate());
					java.sql.Date dataSql = rs.getDate("dataDevolucaoEfetiva");
					if (dataSql != null) {
						emprestimo.setDataDevolucaoEfetiva(dataSql.toLocalDate());
					}
					list.add(emprestimo);
				}
			}
			return list;
		} catch (SQLException e) {
			throw new DbException("Erro ao buscar lista! " + e.getMessage());
		}
	}

	// .......................................................................
	@Override
	public void insert(Emprestimo emp) {
		String sql = "INSERT INTO tb_emprestimo (dataEmprestimo, dataDevolucao,"
				+ " dataDevolucaoEfetiva, leitor_id, livro_id) VALUES (?, ?, ?, ?, ?)";

		// O try-with-resources fecha o PreparedStatement automaticamente
		try (PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			st.setDate(1, java.sql.Date.valueOf(emp.getDataEmprestimo()));
			st.setDate(2, java.sql.Date.valueOf(emp.getDataDevolucao()));
			if (emp.getDataDevolucaoEfetiva() == null) {
				st.setNull(3, java.sql.Types.DATE);
			} else {
				st.setDate(3, java.sql.Date.valueOf(emp.getDataDevolucaoEfetiva()));
			}
			st.setLong(4, emp.getLeitor().getId());
			st.setLong(5, emp.getLivro().getId());

			int linhasAfetadas = st.executeUpdate();

			if (linhasAfetadas > 0) {
				// Outro try-with-resources para o ResultSet
				try (ResultSet rs = st.getGeneratedKeys()) {
					if (rs.next()) {
						Long id = rs.getLong(1);
						emp.setId(id);
					}
				}
			} else {
				throw new DbException("Erro inexperado!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	// .......................................................................
	@Override
	public void returnBook(Long id, Emprestimo emp) {
		String sql = """
					UPDATE
						tb_emprestimo
					SET
						dataDevolucaoEfetiva = ?
					WHERE
						Id = ?
				""";

		try (PreparedStatement st = conn.prepareStatement(sql)) {

			// Usando setObject para passar o LocalDate moderno diretamente
			st.setObject(1, emp.getDataDevolucaoEfetiva());
			st.setLong(2, emp.getId());

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException("Erro ao atualizar! " + e.getMessage());
		}

	}
}

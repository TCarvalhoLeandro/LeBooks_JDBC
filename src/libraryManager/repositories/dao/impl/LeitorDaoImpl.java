package libraryManager.repositories.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import libraryManager.db.DbException;
import libraryManager.entities.Leitor;
import libraryManager.repositories.dao.LeitorDAO;

public class LeitorDaoImpl implements LeitorDAO {

	private Connection conn;

	public LeitorDaoImpl(Connection conn) {
		this.conn = conn;
	}

	// .......................................................................
	@Override
	public Leitor findById(Long id) {

		String sql = "SELECT * FROM tb_leitor WHERE Id = ?";

		try (PreparedStatement st = conn.prepareStatement(sql)) {

			st.setLong(1, id);

			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					Leitor leitor = new Leitor();
					leitor.setId(rs.getLong("id"));
					leitor.setNome(rs.getString("nome"));
					leitor.setCpf(rs.getString("cpf"));
					leitor.setEmail(rs.getString("email"));
					leitor.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());

					return leitor;
				}
				return null;
			}
		} catch (SQLException e) {
			throw new DbException("Erro ao buscar por Id! " + e.getMessage());
		}
	}

	// .......................................................................
	@Override
	public Leitor findByName(String nome) {
		// comando sql
		String sql = "SELECT * FROM tb_leitor WHERE nome = ?";

		// O try-with-resources fecha o PreparedStatement automaticamente
		try (PreparedStatement st = conn.prepareStatement(sql)) {

			// pega o comando e seta o nome com o nome passado no parametro
			st.setString(1, nome);

			// Usamos executeQuery() para SELECT, pois ele devolve dados (ResultSet)
			try (ResultSet rs = st.executeQuery()) {
				// Se rs.next() for verdadeiro, achamos o autor
				if (rs.next()) {
					Leitor leitor = new Leitor();
					leitor.setId(rs.getLong("id"));
					leitor.setNome(rs.getString("nome"));
					leitor.setCpf(rs.getString("cpf"));
					leitor.setEmail(rs.getString("email"));
					leitor.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());

					return leitor;
				}
				return null;
			}
		} catch (SQLException e) {
			throw new DbException("Erro ao buscar por nome! " + e.getMessage());
		}
	}

	// .......................................................................
	@Override
	public List<Leitor> findAll() {

		String sql = "SELECT * FROM tb_leitor";
		List<Leitor> list = new ArrayList<Leitor>();
		try (PreparedStatement st = conn.prepareStatement(sql)) {
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					Leitor leitor = new Leitor();
					leitor.setId(rs.getLong("id"));
					leitor.setNome(rs.getString("nome"));
					leitor.setCpf(rs.getString("cpf"));
					leitor.setEmail(rs.getString("email"));
					leitor.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());

					list.add(leitor);
				}
			}
			return list;
		} catch (SQLException e) {
			throw new DbException("Erro ao buscar lista! " + e.getMessage());
		}
	}

	// .......................................................................
	@Override
	public void insert(Leitor leitor) {
		String sql = "INSERT INTO tb_leitor (nome, cpf, email, data_nascimento) VALUES (?, ?, ?, ?)";

		// O try-with-resources fecha o PreparedStatement automaticamente
		try (PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			st.setString(1, leitor.getNome());
			st.setString(2, leitor.getCpf());
			st.setString(3, leitor.getEmail());
			st.setDate(4, java.sql.Date.valueOf(leitor.getDataNascimento()));// LocalDate do java para Date do SQL

			int linhasAfetadas = st.executeUpdate();

			if (linhasAfetadas > 0) {
				// Outro try-with-resources para o ResultSet
				try (ResultSet rs = st.getGeneratedKeys()) {
					if (rs.next()) {
						Long id = rs.getLong(1);
						leitor.setId(id);
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
	public void update(Long id, Leitor leitor) {

		String sql = "UPDATE tb_leitor SET Nome = ?, Cpf = ?, Email = ?, Data_nascimento = ? WHERE Id = ?";

		try (PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, leitor.getNome());
			st.setString(2, leitor.getCpf());
			st.setString(3, leitor.getEmail());
			st.setDate(4, java.sql.Date.valueOf(leitor.getDataNascimento()));// LocalDate do java para Date do SQL

			st.setLong(5, leitor.getId());

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException("Erro ao atualizar! " + e.getMessage());
		}

	}

	// .......................................................................
	@Override
	public void delete(Long id) {

		String sql = "DELETE FROM tb_leitor WHERE Id = ?";

		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.setLong(1, id);

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

}

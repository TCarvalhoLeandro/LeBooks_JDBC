package libraryManager.repositories.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import libraryManager.db.DbException;
import libraryManager.entities.Autor;
import libraryManager.entities.Livro;
import libraryManager.repositories.dao.AutorDAO;
import libraryManager.repositories.dao.LivroDAO;

public class LivroDaoImpl implements LivroDAO {

	private Connection conn;
	private AutorDAO autorDao;
	// injecao dependencia por construtor
	public LivroDaoImpl(Connection conn, AutorDAO autorDao) {
		this.conn = conn;
		this.autorDao = autorDao;
	}

	// .......................................................................
	@Override
	public Livro findById(Long id) {

		String sql = "SELECT * FROM tb_livro WHERE Id = ?";

		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.setLong(1, id);
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					Livro livro = new Livro();
					livro.setId(rs.getLong("id"));
					livro.setTitulo(rs.getString("titulo"));
					
					Long autor_id = rs.getLong("autor_id");
					Autor autor = autorDao.findById(autor_id);
					livro.setAutor(autor);
					
					livro.setAno(rs.getInt("ano"));
					livro.setDisponivel(rs.getBoolean("disponivel"));
					return livro;
				}
				return null;
			}
		} catch (SQLException e) {
			throw new DbException("Erro ao buscar por Id! " + e.getMessage());
		}
	}

	// .......................................................................
	@Override
	public Livro findByName(String titulo) {
		// comando sql
		String sql = "SELECT * FROM tb_livro WHERE Titulo = ?";

		// O try-with-resources fecha o PreparedStatement automaticamente
		try (PreparedStatement st = conn.prepareStatement(sql)) {

			// pega o comando e seta o nome com o nome passado no parametro
			st.setString(1, titulo);

			// Usamos executeQuery() para SELECT, pois ele devolve dados (ResultSet)
			try (ResultSet rs = st.executeQuery()) {
				// Se rs.next() for verdadeiro, achamos o autor
				if (rs.next()) {
					Livro livro = new Livro();
					livro.setId(rs.getLong("id"));
					livro.setTitulo(rs.getString("titulo"));
					
					Long autor_id = rs.getLong("autor_id");
					Autor autor = autorDao.findById(autor_id);
					livro.setAutor(autor);
					
					livro.setAno(rs.getInt("ano"));
					livro.setDisponivel(rs.getBoolean("disponivel"));
					return livro;
				}
				return null;
			}
		} catch (SQLException e) {
			throw new DbException("Erro ao buscar por nome! " + e.getMessage());
		}
	}

	// .......................................................................
	@Override
	public List<Livro> findAll() {
		String sql = "SELECT tb_livro.*,"
										+ " tb_autor.id AS id_do_autor, tb_autor.nome, tb_autor.nacionalidade"
										+ " FROM tb_livro INNER JOIN tb_autor"
										+ " ON tb_livro.autor_id = tb_autor.id";
		List<Livro> list = new ArrayList<Livro>();
		try (PreparedStatement st = conn.prepareStatement(sql)) {
			
			try (ResultSet rs = st.executeQuery()) {
				
				while (rs.next()) {
					Livro livro = new Livro();
					livro.setId(rs.getLong("id"));
					livro.setTitulo(rs.getString("titulo"));
					
					Autor autor = new Autor(rs.getLong("id_do_autor"), rs.getString("nome"), rs.getString("nacionalidade"));
					livro.setAutor(autor);
							
					livro.setAno(rs.getInt("ano"));
					livro.setDisponivel(rs.getBoolean("disponivel"));
					list.add(livro);
				}
			}
			return list;
		} catch (SQLException e) {
			throw new DbException("Erro ao buscar lista! " + e.getMessage());
		}
	}

	// .......................................................................
	@Override
	public void insert(Livro livro) {
		String sql = "INSERT INTO tb_livro (titulo, autor_id, ano, disponivel) VALUES (?, ?, ?, ?)";

		// O try-with-resources fecha o PreparedStatement automaticamente
		try (PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			st.setString(1, livro.getTitulo());
			st.setLong(2, livro.getAutor().getId());
			st.setInt(3, livro.getAno());
			st.setBoolean(4, livro.isDisponivel());

			int linhasAfetadas = st.executeUpdate();

			if (linhasAfetadas > 0) {
				// Outro try-with-resources para o ResultSet
				try (ResultSet rs = st.getGeneratedKeys()) {
					if (rs.next()) {
						Long id = rs.getLong(1);
						livro.setId(id);
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
	public void update(Long id, Livro livro) {
		
		String sql = "UPDATE tb_livro SET Titulo = ?, Autor_id = ?, Ano = ?, Disponivel = ? WHERE Id = ?";
				
		try (PreparedStatement st = conn.prepareStatement(sql)) {
				
			st.setString(1, livro.getTitulo());
			st.setLong(2, livro.getAutor().getId());
			st.setInt(3, livro.getAno());
			st.setBoolean(4, livro.isDisponivel());
			st.setLong(5, livro.getId());
	
			st.executeUpdate();
		}
		catch(SQLException e) {
			throw new DbException("Erro ao atualizar! " + e.getMessage());
		}

	}
	// .......................................................................
	@Override
	public void delete(Long id) {
		
		String sql = "DELETE FROM tb_livro WHERE Id = ?";
		
		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.setLong(1, id);
			
			st.executeUpdate();
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
}

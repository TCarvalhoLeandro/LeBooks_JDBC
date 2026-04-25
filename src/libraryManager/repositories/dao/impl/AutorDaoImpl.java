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
import libraryManager.repositories.dao.AutorDAO;

public class AutorDaoImpl implements AutorDAO {
	
	private Connection conn;
	
	public AutorDaoImpl(Connection conn) {
		this.conn = conn;
	}
//.......................................................................
	@Override
	public void insert(Autor autor) {
		String sql = "INSERT INTO tb_autor (nome, nacionalidade) VALUES (?, ?)";

		// O try-with-resources fecha o PreparedStatement automaticamente
		try (PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			st.setString(1, autor.getNome());
			st.setString(2, autor.getNacionalidade());

			int linhasAfetadas = st.executeUpdate();

			if (linhasAfetadas > 0) {
				// Outro try-with-resources para o ResultSet
				try (ResultSet rs = st.getGeneratedKeys()) {
					if (rs.next()) {
						Long id = rs.getLong(1);
						autor.setId(id);
					}
				}
			} else {
				throw new DbException("Erro inexperado!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
//.......................................................................
	@Override
	public Autor findByName(String email) {
		// comando sql
		String sql = "SELECT * FROM tb_autor WHERE nome = ?";
		
		// O try-with-resources fecha o PreparedStatement automaticamente
		try (PreparedStatement st = conn.prepareStatement(sql)) {
			
			// pega o comando e seta o nome com o nome passado no parametro
			st.setString(1, email);
			
			// Usamos executeQuery() para SELECT, pois ele devolve dados (ResultSet)
			try(ResultSet rs = st.executeQuery()){
				// Se rs.next() for verdadeiro, achamos o autor
	            if (rs.next()) {
	                Autor autor = new Autor();
	                autor.setId(rs.getLong("id"));
	                autor.setNome(rs.getString("nome"));
	                autor.setNacionalidade(rs.getString("nacionalidade"));
	               
	                return autor;
	            }
	            return null;    
			}
		}
		catch(SQLException e) {
			throw new DbException("Erro ao buscar por nome! " + e.getMessage());
		}
	}
//.......................................................................
	@Override
	public Autor findById(Long id) {
		
		String sql = "SELECT * FROM tb_autor WHERE Id = ?";
	
		try (PreparedStatement st = conn.prepareStatement(sql)) {
		
			st.setLong(1, id);
			
			try(ResultSet rs = st.executeQuery()){
				if (rs.next()) {
	                Autor autor = new Autor();
	                autor.setId(rs.getLong("id"));
	                autor.setNome(rs.getString("nome"));
	                autor.setNacionalidade(rs.getString("nacionalidade"));
	               
	                return autor;
	            }
	            return null;  
			}
		}
		catch(SQLException e) {
			throw new DbException("Erro ao buscar por Id! " + e.getMessage());
		}
	}
//.......................................................................
	@Override
	public List<Autor> findAll() {

		String sql = "SELECT * FROM tb_autor";
		List<Autor> list = new ArrayList<Autor>();
		try (PreparedStatement st = conn.prepareStatement(sql)) {
			try(ResultSet rs = st.executeQuery()){
				while(rs.next()) {
					Autor autor = new Autor();
					autor.setId(rs.getLong("id"));
					autor.setNome(rs.getString("nome"));
					autor.setNacionalidade(rs.getString("nacionalidade"));
					list.add(autor);
				}
			}
			return list;
		}
		catch(SQLException e) {
			throw new DbException("Erro ao buscar lista! " + e.getMessage());
		}
	}
//.......................................................................
	@Override
	public void update(Long id, Autor autor) {
		
		String sql = "UPDATE tb_autor SET Nome = ?, Nacionalidade = ? WHERE Id = ?";
				
		try (PreparedStatement st = conn.prepareStatement(sql)) {
				
			st.setString(1, autor.getNome());
			st.setString(2, autor.getNacionalidade());
			st.setLong(3, autor.getId());
	
			st.executeUpdate();
		}
		catch(SQLException e) {
			throw new DbException("Erro ao atualizar! " + e.getMessage());
		}

	}
//.......................................................................
	@Override
	public void remove(int id) {
		// TODO Auto-generated method stub

	}

}

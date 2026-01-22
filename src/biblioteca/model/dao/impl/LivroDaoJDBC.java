package biblioteca.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import biblioteca.db.DB;
import biblioteca.db.DbException;
import biblioteca.entities.Livro;
import biblioteca.model.dao.LivroDao;

public class LivroDaoJDBC implements LivroDao{

	/*
	 * LivroDaoJDBC vai ter uma dependencia com a conexao Que é forçadda atraves do
	 * construtor do Connection
	 */
	private Connection conn;

	public LivroDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	
	
	@Override
	public void insert(Livro livro) {
		
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
									    "INSERT INTO livro"
									  + "(titulo, autor, ano, disponivel) "
									  + "VALUES "
									  + "(?, ?, ?, ?)", 
									  Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, livro.getTitulo());
			st.setString(2, livro.getAutor());
			st.setInt(3, livro.getAno());
			st.setBoolean(4, livro.isDisponivel());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				
				ResultSet rs = st.getGeneratedKeys();
				
				if(rs.next()) {
					int id = rs.getInt(1);
					livro.setId(id);
				}
				DB.closeResultSet(rs);
			}
		}
		catch(SQLException e) {
			throw new DbException("Unexpected error" + e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
		
	}

	@Override
	public void update(Livro livro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Livro findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Livro> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
}

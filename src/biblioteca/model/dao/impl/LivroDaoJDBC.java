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

	
	//METODO PARA INSERIR DADOS
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
	
	
	//METODO PARA BUSCAR POR ID
	@Override
	public Livro findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("SELECT * FROM livro WHERE id = ?");
			
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				Livro livro = instanciaLivro(rs);
				return livro;
			}
			return null;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	
	//METODO PARA ATUALIZAR DADOS
	@Override
	public void update(Livro livro) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement(
			      "UPDATE livro "
				+ "SET titulo = ?, autor = ?, ano = ?, disponivel = ? " 
				+ "WHERE id = ?");
			
			st.setString(1, livro.getTitulo());
			st.setString(2, livro.getAutor());
			st.setInt(3, livro.getAno());
			st.setBoolean(4, livro.isDisponivel());
			st.setInt(5, livro.getId());
			
			st.executeUpdate();
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	
	//METODO PARA DELETAR PO ID
	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	
	//METODO PARA BUSCAR TODOS OS DADOS
	@Override
	public List<Livro> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//METODO PARA INSTANCIAR LIVRO A PARTIR DE UM RESULTSET
	public static Livro instanciaLivro(ResultSet rs) throws SQLException {
		
		Livro livro = new Livro();
		
		livro.setId(rs.getInt("id"));
		livro.setTitulo(rs.getString("titulo"));
		livro.setAutor(rs.getString("autor"));
		livro.setAno(rs.getInt("ano"));
		livro.setDisponivel(rs.getBoolean("disponivel"));
		
		return livro;
				
	}

}

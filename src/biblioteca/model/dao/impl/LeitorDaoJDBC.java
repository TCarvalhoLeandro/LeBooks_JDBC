package biblioteca.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import biblioteca.db.DB;
import biblioteca.db.DbException;
import biblioteca.db.DbIntegrityException;
import biblioteca.entities.Leitor;
import biblioteca.model.dao.LeitorDao;

public class LeitorDaoJDBC implements LeitorDao{
	
	private Connection conn;

	public LeitorDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	// METODO PARA INSERIR LEITOR NO BANCO DE DADOS
	@Override
	public void insert(Leitor leitor) {
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement(
					            "INSERT INTO leitor"
							  + "(nome, cpf, email) "
							  + "VALUES "
							  + "(?, ?, ?)", 
							  Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, leitor.getNome());
			st.setString(2, leitor.getCpf());
			st.setString(3, leitor.getEmail());
			
			int rowAffected = st.executeUpdate();
			
			if(rowAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				
				if(rs.next()) {
					int id = rs.getInt(1);
					leitor.setId(id);
				}
				DB.closeResultSet(rs);
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	
	//METODO PARA ATUALIZAR LEITOR NO BANCO DE DADOS 
	@Override
	public void update(Leitor leitor) {
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement(
					 			  "UPDATE leitor "
								+ "SET nome = ?, cpf = ?, email = ? "
								+ "WHERE id = ?");
			
			st.setString(1, leitor.getNome());
			st.setString(2, leitor.getCpf());
			st.setString(3, leitor.getEmail());
			st.setInt(4, leitor.getId());
			
			st.executeUpdate();
			
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	// METODO PARA DELETAR LEITOR NO BANCO DE DADOS (ID)
	@Override
	public void deleteById(Integer id) {
		

		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("DELETE FROM leitor WHERE id = ?");
			
			st.setInt(1, id);
			
			st.executeUpdate();
		}
		catch(SQLException e) {
			// Verifica se o erro é de integridade referencial
	        if (e.getErrorCode() == 1451) {
	            throw new DbIntegrityException("Não é possível excluir o leitor: ele possui empréstimos registrados.");
	        }
			throw new DbException(e.getMessage());
			
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	
	// METODO PARA BUSCAR LEITOR NO BANCO DE DADOS (ID)
	@Override
	public Leitor findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("SELECT * FROM leitor WHERE id = ?");
			
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				Leitor leitor = instanciaLeitor(rs);
				return leitor;
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

	 // METODO PARA BUSCAR TODOS OS LEITORES DO BANCO DE DADOS
	@Override
	public List<Leitor> findAll() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM leitor ORDER BY id");
			
			rs = st.executeQuery();	
			List<Leitor> list = new ArrayList<Leitor>();
			
			while(rs.next()) {
				Leitor leitor = instanciaLeitor(rs);
				list.add(leitor);
			}
			return list;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}
	
	
	// METODO PARA INSTANCIAR UM LEITOR A PARTIR DO BANCO DE DADOS
	public static Leitor instanciaLeitor(ResultSet rs) throws SQLException {
		
		Leitor leitor = new Leitor();
		
		leitor.setId(rs.getInt("id"));
		leitor.setNome(rs.getString("nome"));
		leitor.setCpf(rs.getString("cpf"));
		leitor.setEmail(rs.getString("email"));
		
		return leitor;
	}
	
	

}

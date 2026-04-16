package biblioteca.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import biblioteca.db.DB;
import biblioteca.db.DbException;
import biblioteca.entities.Emprestimo;
import biblioteca.entities.Leitor;
import biblioteca.entities.Livro;
import biblioteca.repository.EmprestimoDao;


public class EmprestimoDaoJDBC implements EmprestimoDao{
	
	private Connection conn;

	public EmprestimoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	
	// METODO PARA INSERIR UM EMPRESTIMO NO BANCO DE DADOS
	@Override
	public void insert(Emprestimo emp) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
								"INSERT INTO emprestimo "
								+ "(data_emprestimo, data_devolucao, devolvido, id_leitor, id_livro) "
								+ "VALUES (?, ?, ?, ?, ?)", 
								Statement.RETURN_GENERATED_KEYS);
			
			/*Um problema muito comum quando fazemos a transição (que muitas vezes usa java.util.Date) 
			 * para as APIs mais modernas do Java 8+, como o LocalDate.

			O JDBC tradicional espera o tipo java.sql.Date, mas o LocalDate não pode ser convertido 
			diretamente com um simples "cast". Você precisa usar os métodos de conversão da própria 
			classe ou aproveitar que os drivers de banco de dados modernos (como o que você usa no 
			Ubuntu para o MySQL) já aceitam o LocalDate via setObject.

			Pode ser resolvido  usando setObject (Mais moderna e recomendada)
			Os drivers JDBC atuais conseguem mapear o LocalDate automaticamente se você usar o método
			 setObject.*/
			st.setObject(1, emp.getDataEmprestimo());
			st.setObject(2, emp.getDataDevolucao());
			st.setBoolean(3, false);
			st.setInt(4, emp.getLeitor().getId());
			st.setInt(5, emp.getLivro().getId());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					emp.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpectde error!!");
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
		
	}

	
	// METODO PARA  REGISTRAR A DATA DE DEVOLUCAO DE UM LIVRO NO BANCO DE DADOS
	@Override
	public void registerReturn(Emprestimo emp) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
							"UPDATE emprestimo "
							+ "SET devolvido = true, data_devolucao = ? "
							+ "WHERE id = ? AND devolvido = false");
			
			st.setObject(1, LocalDate.now());// Usando a data de hoje para a devolução real
			st.setInt(2, emp.getId());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected == 0) {
				System.out.println("O empréstimo id: " + emp.getId() +" já foi devolvido ou não existe.");
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	
	//METODO PARA BUSCAR EMPRESTIMOS QUE ESTAO ATIVOS (NAO DEVOLVIDOS)
	@Override
	public List<Emprestimo> findAtivos() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
							// SQL focado apenas nos registros onde devolvido é false
							"SELECT emprestimo.*, " +
						    "leitor.id AS leitor_id_col, leitor.nome AS leitor_nome_col, " + // Apelidos para Leitor
						    "livro.id AS livro_id_col, livro.titulo AS livro_titulo_col " + // Apelidos para Livro
						    "FROM emprestimo " +
						    "INNER JOIN leitor ON emprestimo.id_leitor = leitor.id " +
						    "INNER JOIN livro ON emprestimo.id_livro = livro.id " +
				            "WHERE emprestimo.devolvido = false " + // Filtro de ativos
				            "ORDER BY emprestimo.data_devolucao ASC"); // Ordena pelos que vencem primeiro
			
			rs = st.executeQuery();
			
			List<Emprestimo> list = new ArrayList<Emprestimo>();
			
			// Maps para eveitar repetição de instancia na memoria
			Map<Integer, Leitor> leitorMap = new HashMap<Integer, Leitor>();
			Map<Integer, Livro> livroMap = new HashMap<Integer, Livro>();
			
			while(rs.next()) {
				
				Leitor leitor = leitorMap.get(rs.getInt("id_leitor"));
				if(leitor == null) {
					leitor = new Leitor();
					leitor.setId(rs.getInt("leitor_id_col"));
					leitor.setNome(rs.getString("leitor_nome_col"));
					leitorMap.put(rs.getInt("id_leitor"), leitor);
				}
				
				Livro livro = livroMap.get(rs.getInt("id_livro"));
				if(livro == null) {
					livro = new Livro();
					livro.setId(rs.getInt("livro_id_col")); // Usa o apelido do SQL
			        livro.setTitulo(rs.getString("livro_titulo_col"));
					livroMap.put(rs.getInt("id_livro"), livro);
				}
				
				// instancia Emprestimo com os objetos associados
				Emprestimo emp = instanciaEmprestimo(rs, leitor, livro);
				list.add(emp);
				
				
			}
			return list;
			
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	
	// METODO PARA BUSCAR UM EMPRESTIMO POR ID NO BANCO DE DADOS
	@Override
	public Emprestimo findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs =null;
		try {
			// Usamos aliases (e.*, lt.*, lv.*) para garantir que todas as colunas venham no ResultSet
			st = conn.prepareStatement(
							"SELECT emprestimo.*, " +
						    "leitor.id AS leitor_id_col, leitor.nome AS leitor_nome_col, " + // Apelidos para Leitor
						    "livro.id AS livro_id_col, livro.titulo AS livro_titulo_col " + // Apelidos para Livro
						    "FROM emprestimo " +
						    "INNER JOIN leitor ON emprestimo.id_leitor = leitor.id " +
						    "INNER JOIN livro ON emprestimo.id_livro = livro.id " +
		            		"WHERE emprestimo.id = ?");
			
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				
				// RETORNAR AQUI=============================================
				Leitor leitor = new Leitor();
				leitor.setId(rs.getInt("leitor_id_col"));
				leitor.setNome(rs.getString("leitor_nome_col"));
				
				Livro livro = new Livro();
				livro.setId(rs.getInt("livro_id_col")); // Usa o apelido do SQL
		        livro.setTitulo(rs.getString("livro_titulo_col"));
				
				// Monta o objeto Emprestimo associando os dois objetos acima
				Emprestimo emp = instanciaEmprestimo(rs, leitor, livro);
				
				return emp;
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			
		}
		
		return null;
	}

	
	// METODO PARA BUSCAR TODOS OS EMPRESTIMOS NO BANCO DE DADOS
	@Override
	public List<Emprestimo> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			// SQL com join para trazer os dados completos de Leitor e Livro
			st = conn.prepareStatement(
							"SELECT emprestimo.*, " +
						    "leitor.id AS leitor_id_col, leitor.nome AS leitor_nome_col, " + // Apelidos para Leitor
						    "livro.id AS livro_id_col, livro.titulo AS livro_titulo_col " + // Apelidos para Livro
						    "FROM emprestimo " +
						    "INNER JOIN leitor ON emprestimo.id_leitor = leitor.id " +
						    "INNER JOIN livro ON emprestimo.id_livro = livro.id " +
						    "ORDER BY data_emprestimo DESC"); // Mais recentes primeiro
					
			rs = st.executeQuery();
			
			List<Emprestimo> list = new ArrayList<Emprestimo>();
			
			//Maps pra controlar a repetição de instancias e ecomizar memoria
			Map<Integer, Leitor> leitorMap = new HashMap<Integer, Leitor>();
			Map<Integer, Livro> livroMap = new HashMap<Integer, Livro>();
			
			while(rs.next()) {
				
				// 1 - verificamos se o Leitor já foi instanciado nesta consulta
				Leitor leitor = leitorMap.get(rs.getInt("id_leitor"));
				
				if(leitor == null) {
					// Se não existe no Map, usamos o seu método estático pra criar e guardar
					leitor = new Leitor();
					leitor.setId(rs.getInt("leitor_id_col"));
					leitor.setNome(rs.getString("leitor_nome_col"));
					leitorMap.put(rs.getInt("id_leitor"), leitor);// adiciona na tabela hash com o id como chave
					
				}
				
				// 2 - verificamos se o Livro já foi instanciado nesta consulta
				Livro livro = livroMap.get(rs.getInt("id_livro"));
				
				if(livro == null) {
					livro = new Livro();
					livro.setId(rs.getInt("livro_id_col")); // Usa o apelido do SQL
			        livro.setTitulo(rs.getString("livro_titulo_col"));
					livroMap.put(rs.getInt("id_livro"), livro);
				}
				
				// 3 - Criamos o Emprestimo usando os objetos unicos recuperados acima
				Emprestimo emp = instanciaEmprestimo(rs, leitor, livro);
				list.add(emp);
			}
			return list;
			
					
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}
	
	
	
	// METODO PARA INSTANCIAR UM EMPRESTIMO
	public Emprestimo instanciaEmprestimo(ResultSet rs, Leitor leitor, Livro livro) throws SQLException{
		Emprestimo emp = new Emprestimo();

		emp.setId(rs.getInt("id"));
	
		/*rs.getDate() do JDBC retorna um tipo java.sql.Date, mas o seu objeto
		Emprestimo está esperando um java.time.LocalDate. Eles são tipos 
		incompatíveis para uma atribuição direta.
		Ao passar LocalDate.class, o driver do MySQL (ou o banco que você estiver 
		usando no Ubuntu) já tenta converter o valor do banco diretamente para o
		 tipo que você usa na sua entidade.*/
		emp.setDataEmprestimo(rs.getObject("data_emprestimo", LocalDate.class));
		emp.setDataDevolucao(rs.getObject("data_devolucao", LocalDate.class));
		emp.setDevolvido(rs.getBoolean("devolvido"));
		emp.setLeitor(leitor);
		emp.setLivro(livro);
		return emp;
	}
	
}

package libraryManager.testConexao;

import java.sql.Connection;
import java.sql.SQLException;

import libraryManager.db.DB;

public class TesteConexao {
	public static void main(String[] args) {
		try(Connection conn = DB.getConnection()){
			System.out.println("Conexão estabelecida...");
		}
		catch(SQLException e) {
			System.out.println("Erro na conexão!");
		}
		System.out.println("Conexão fechada!");
	}
}



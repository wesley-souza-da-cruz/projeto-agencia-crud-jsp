// Driver de conexão com o banco de dados - aula Gabriel Sales - Gratissimo! - agencia
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
	
	private static final String url = "jdbc:mysql://localhost:3306/java_crud";
	private static final String user = "root";
	private static final String password = "root";
	
	public static Connection createConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver encontrado");
			
		} catch(ClassNotFoundException e) {
			System.out.println("Driver nao encontrado. " + e.getMessage());
		}
		
		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			System.out.println("Conectado com banco de dados");
			return connection;
			
		} catch(SQLException e) {
			System.out.println("Nao conectado com o banco de dados." + e.getMessage());
			return null;
		}
			
	}	

}

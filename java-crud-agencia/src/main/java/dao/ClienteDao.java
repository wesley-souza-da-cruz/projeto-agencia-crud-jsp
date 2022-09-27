package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.MySqlConnection;
import model.Cliente;

public class ClienteDao implements CRUD {
	
	private static Connection connection = MySqlConnection.createConnection();
	private static String sql;
	
	public static void create(Cliente cliente) {
		 sql = "INSERT INTO clientes VALUES (null, ?, ?, ?, ?)";
		 
		 try {
			 PreparedStatement preparedStatement = connection.prepareStatement(sql);
			 
			 preparedStatement.setString(1, cliente.getNome());
			 preparedStatement.setString(2, cliente.getCpf());
			 preparedStatement.setString(3, cliente.getNascimento());
			 preparedStatement.setString(4, cliente.getSituacao());
			 preparedStatement.setInt(5, cliente.getId());
			 
			 preparedStatement.executeUpdate();
			 
			 System.out.println("Inserido com sucesso");
			 
		 } catch(SQLException e) {
			 System.out.println("Nao inserido, tente novamente. " + e.getMessage());
		 }
	}
	
	public static void delete(int clienteId) {
		sql = "DELETE FROM clientes WHERE id = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, clienteId);
			preparedStatement.executeUpdate();
			
			System.out.println("Deletado com sucesso");
			
		} catch (SQLException e) {
			System.out.println("Erro ao deletar. " + e.getMessage());
		}
	}
	
	public static List<Cliente> find(String pesquisa){
		
		sql = String.format("SELECT * FROM clientes WHERE nome like '%s%%' OR cpf LIKE '%s%%' ", pesquisa, pesquisa);
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				
				Cliente cliente = new Cliente();
				
				cliente.setId(resultSet.getInt("id"));
				cliente.setNome(resultSet.getString("nome"));
				cliente.setCpf(resultSet.getString("cpf"));
				cliente.setNascimento(resultSet.getString("nascimento"));
				cliente.setSituacao(resultSet.getString("situacao"));
				
				clientes.add(cliente);
				
			}
			
			System.out.println("Encontrado com sucesso");
			return clientes;
			
		} catch(SQLException e) {
			System.out.println("Erro ao encontrar. " + e.getMessage());
			return null;
		}
		
		
	}
	
	public static Cliente findByPk(int clienteId) {
		sql = String.format("SELECT * FROM clientes WHERE id = %d ", clienteId);
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			Cliente cliente = new Cliente();
			
			while (resultSet.next()) {
				cliente.setId(resultSet.getInt("id"));
				cliente.setNome(resultSet.getString("nome"));
				cliente.setCpf(resultSet.getString("cpf"));
				cliente.setNascimento(resultSet.getString("nascimento"));
				cliente.setSituacao(resultSet.getString("situacao"));
			}
			
			System.out.println("Encontrado corretamente por pk clientes");
			return cliente;
			
	} catch(SQLException e) {
		
			System.out.println("Nao encontrado corretamente pro  pk clientes. " + e.getMessage());
			return null;
		}
	}
	
	public static void update(Cliente cliente) {
		sql = "UPDATE clientes SET nome=?, cpf=?, nascimento=?, situacao=? WHERE id=?";
		 
		 try {
			 PreparedStatement preparedStatement = connection.prepareStatement(sql);
			 
			 preparedStatement.setString(1, cliente.getNome());
			 preparedStatement.setString(2, cliente.getCpf());
			 preparedStatement.setString(3, cliente.getNascimento());
			 preparedStatement.setString(4, cliente.getSituacao());
			 preparedStatement.setInt(5, cliente.getId());
			 
			 preparedStatement.executeUpdate();
			 
			 System.out.println("Atualizado com sucesso");
			 
		 } catch(SQLException e) {
			 System.out.println("Erro ao atualizar. " + e.getMessage());
		 }
	}
}
package es.JoseGalanMaqueda.modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloUsuarios
{

	// ================================ BASE DATOS ===============================================
	BaseDatos bd = null;
	String sentencia = "";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	
	public int comprobacionUsuario(String nombreUsuario, String password) 
	{
		
		int tipo = -1;
		BaseDatos bd = new BaseDatos();
		connection = bd.conectar();
		try 
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			sentencia = "SELECT * from usuarios where nombreUsuario='"+nombreUsuario+"' AND claveUsuario = sha2('"+password+"',256);";
			rs = statement.executeQuery(sentencia);
			if (rs.next()) 
			{
				tipo = rs.getInt("tipoUsuario");
			}
		}
		catch (SQLException e2) 
		{
			tipo = -1;
			System.out.println("Error 1.- " + e2.getMessage());
		}
		finally 
		{
			bd.desconectar(connection);
		}
		return tipo;
	}
	

}

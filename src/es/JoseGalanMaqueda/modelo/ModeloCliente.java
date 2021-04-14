package es.JoseGalanMaqueda.modelo;

import java.awt.TextField;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloCliente
{
	// ================================ BASE DATOS ===============================================
	BaseDatos bd = null;
	String sentencia = "";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	public boolean comprobacionDatosIntroducidos(TextField nombre, TextField apellidos, TextField dni, TextField direccion, String eleccion) 
	{
		boolean booleano = false;
		if( (nombre.getText().length()!=0) && (apellidos.getText().length() != 0) && (dni.getText().length()!=0) && (direccion.getText().length()!=0) && (eleccion.length()!=0) ) {
			if (dni.getText().length() == 9)
			{
				booleano = true;
			}
		}
		return booleano;
	}

	public boolean insertarDatosClientes(TextField nombre, TextField apellidos, TextField dni, TextField direccion, String eleccion) {
		bd = new BaseDatos();
		connection = bd.conectar();
		boolean insertado = true;
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			sentencia = "INSERT INTO clientes VALUES (null, '"+ nombre.getText()+ "', '" +apellidos.getText() +
					"', '"+ dni.getText() +"', '" + direccion.getText() + "', '" + eleccion +"');";
			statement.executeUpdate(sentencia);

		} catch (SQLException e1)
		{
			insertado = false;
		}finally {
			bd.desconectar(connection);
		}
		return insertado;
	}
}

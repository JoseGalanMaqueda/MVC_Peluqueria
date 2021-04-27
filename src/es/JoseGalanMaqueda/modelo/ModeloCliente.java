package es.JoseGalanMaqueda.modelo;

import java.awt.Choice;
import java.awt.TextArea;
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
			sentencia = "INSERT INTO clientes VALUES (null, '"+ nombre.getText()+ "', '" +apellidos.getText() + "', '"+ dni.getText() +"', '" + direccion.getText() + "', '" + eleccion +"');";
			statement.executeUpdate(sentencia);

		} catch (SQLException e1)
		{
			insertado = false;
		}finally {
			bd.desconectar(connection);
		}
		return insertado;
	}
	
	public void cargarListadoClientes(Choice cholistaClientes, String clienteBuscado) 
	{
		bd = new BaseDatos();
		connection = bd.conectar();
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			sentencia = "SELECT * FROM clientes WHERE nombreCliente LIKE '"+clienteBuscado+"%';";
			rs = statement.executeQuery(sentencia);
			cholistaClientes.removeAll();
			cholistaClientes.add("Selecciona un Cliente..");
			while (rs.next()) {
				cholistaClientes.add(rs.getInt("idCliente")+"-"+rs.getString("nombreCliente")+"-"+rs.getString("apellidosCliente")+"-"+rs.getString("dniCliente"));
			}
		} catch (SQLException e) {
			cholistaClientes.removeAll();
			cholistaClientes.add("Problema al cargar los datos");
		}finally {
			bd.desconectar(connection);
		}
	}
	
	
	public void cargarListadoClientes(Choice cholistaClientes) 
	{
		bd = new BaseDatos();
		connection = bd.conectar();
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			sentencia = "SELECT * FROM clientes;";
			rs = statement.executeQuery(sentencia);
			cholistaClientes.removeAll();
			cholistaClientes.add("Selecciona un Cliente..");
			while (rs.next()) {
				cholistaClientes.add(rs.getInt("idCliente")+"-"+rs.getString("nombreCliente")+"-"+rs.getString("apellidosCliente")+"-"+rs.getString("dniCliente"));
			}
		} catch (SQLException e) {
			cholistaClientes.removeAll();
			cholistaClientes.add("Problema al cargar los datos");
		}finally {
			bd.desconectar(connection);
		}
	}
	
	public boolean eliminarClientes(Choice choListaCliente) {
		bd = new BaseDatos();
		connection = bd.conectar();
		boolean eliminado = true;
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			sentencia = "DELETE FROM clientes WHERE idCliente = "+choListaCliente.getSelectedItem().split("-")[0]+";";
			statement.executeUpdate(sentencia);
		} catch (SQLException e1)
		{
			eliminado = false;
		}finally {
			bd.desconectar(connection);
		}
		return eliminado;
	}
	
	public void consultaClientes(TextArea txaConsultaClientes) {
		bd= new BaseDatos();
		connection = bd.conectar();
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			sentencia = "SELECT * FROM clientes;";
			rs = statement.executeQuery(sentencia);
			txaConsultaClientes.selectAll();
			txaConsultaClientes.setText("");
			txaConsultaClientes.append("IdCliente\tNombre\tApellidos\tDNI\tDireccion\tSexo\n");
			txaConsultaClientes.append("====================================================\n");
			while (rs.next()) {
				txaConsultaClientes.append(rs.getInt("idCliente")+"\t"+rs.getString("nombreCliente")+"\t"+rs.getString("apellidosCliente")+"\t"+
						rs.getString("dniCliente")+"\t"+rs.getString("direccionCliente")+"\t"+rs.getString("sexoCliente")+"\n");
			}
		} catch (SQLException e) {
			txaConsultaClientes.selectAll();
			txaConsultaClientes.setText("");
			txaConsultaClientes.append("Error al cargar los datos");	
		}finally {
			bd.desconectar(connection);
		}
	}
	

	
	
}

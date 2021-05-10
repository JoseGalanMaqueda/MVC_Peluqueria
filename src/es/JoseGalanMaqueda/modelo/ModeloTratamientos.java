package es.JoseGalanMaqueda.modelo;

import java.awt.Choice;
import java.awt.TextArea;
import java.awt.TextField;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import es.JoseGalanMaqueda.Controladores.ControladorLogin;

public class ModeloTratamientos
{
	// ================================ BASE DATOS ===============================================
	BaseDatos bd = null;
	String sentencia = "";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	public boolean comprobacionDatos(TextField nombre) 
	{
		boolean booleano = false;
		if (nombre.getText().length()!=0) {
			booleano = true;
		}
		return booleano;
	}
	
	public boolean insertarDatosTratamientos(TextField nombre, Choice precio, TextArea descripcion) {
		bd = new BaseDatos();
		connection = bd.conectar();
		boolean insertado = true;
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			sentencia = "INSERT INTO tratamientos VALUES (null, '"+ nombre.getText()+ "', '" +descripcion.getText()+ "', "+ precio.getSelectedItem() +");";
			statement.executeUpdate(sentencia);

		} catch (SQLException e1)
		{
			insertado = false;
		}finally {
			bd.desconectar(connection);
		}
		return insertado;
	}
	
	// ======================== ELIMINAR TRATAMIENTO =========================================
	public void cargarListadoTratamientos(Choice choListaTratamientos) 
	{
		bd = new BaseDatos();
		connection = bd.conectar();
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			sentencia = "SELECT * FROM tratamientos;";
			rs = statement.executeQuery(sentencia);
			choListaTratamientos.removeAll();
			choListaTratamientos.add("Selecciona un Tratamiento..");
			while (rs.next()) {
				choListaTratamientos.add(rs.getInt("idTratamiento")+"-"+rs.getString("nombreTratamiento"));
			}
		} catch (SQLException e) {
			choListaTratamientos.removeAll();
			choListaTratamientos.add("Problema al cargar los datos");
		}finally {
			bd.desconectar(connection);
		}
	}
	
	public void cargarListadoTratamientos(Choice choListaTratamientos, String TratamientoBuscado) 
	{
		bd = new BaseDatos();
		connection = bd.conectar();
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			sentencia = "SELECT * FROM tratamientos WHERE nombreTratamiento LIKE '"+TratamientoBuscado+"%';";
			rs = statement.executeQuery(sentencia);
			choListaTratamientos.removeAll();
			choListaTratamientos.add("Selecciona un Tratamiento..");
			while (rs.next()) {
				choListaTratamientos.add(rs.getInt("idTratamiento")+"-"+rs.getString("nombreTratamiento"));
			}
		} catch (SQLException e) {
			choListaTratamientos.removeAll();
			choListaTratamientos.add("Problema al cargar los datos");
		}finally {
			bd.desconectar(connection);
		}
	}
	
	public boolean eliminarTratamiento(Choice choListaTratamientos) {
		bd = new BaseDatos();
		connection = bd.conectar();
		boolean eliminado = true;
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			sentencia = "DELETE FROM tratamientos WHERE idTratamiento = "+choListaTratamientos.getSelectedItem().split("-")[0]+";";
			FicheroLog.guardar(ControladorLogin.nombreUsuario, sentencia);
			statement.executeUpdate(sentencia);
		} catch (SQLException e1)
		{
			eliminado = false;
		}finally {
			bd.desconectar(connection);
		}
		return eliminado;
	}
	
	// =========================== CONSULTA TRATAMIENTOS ==========================
	
	public void consultaTratamientos(TextArea txaConsultaTratamientos) 
	{
		bd= new BaseDatos();
		connection = bd.conectar();
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			sentencia = "SELECT * FROM tratamientos;";
			FicheroLog.guardar(ControladorLogin.nombreUsuario, sentencia);
			rs = statement.executeQuery(sentencia);
			txaConsultaTratamientos.selectAll();
			txaConsultaTratamientos.setText("");
			txaConsultaTratamientos.append("IdTratamiento\tNombre\tDescripcion\tPrecio\n");
			txaConsultaTratamientos.append("=======================================================================\n");
			while (rs.next()) {
				txaConsultaTratamientos.append(rs.getInt("idTratamiento")+"\t"+rs.getString("nombreTratamiento")+"\t"+rs.getString("descripcionTratamiento")+"\t"+
						rs.getDouble("precioTratamiento")+"0\n");
			}
		} catch (SQLException e) {
			txaConsultaTratamientos.selectAll();
			txaConsultaTratamientos.setText("");
			txaConsultaTratamientos.append("Error al cargar los datos");
			bd.desconectar(connection);
		}
	}
}














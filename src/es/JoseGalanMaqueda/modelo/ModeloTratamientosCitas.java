package es.JoseGalanMaqueda.modelo;

import java.awt.Choice;
import java.awt.TextArea;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import es.JoseGalanMaqueda.Controladores.ControladorLogin;

public class ModeloTratamientosCitas
{
	// ================================ BASE DATOS ===============================================
	BaseDatos bd = null;
	String sentencia = "";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	
	public void rellenarTextArea(String citaSeleccionada, TextArea listadoTratamientos) 
	{
		bd= new BaseDatos();
		connection = bd.conectar();
		try 
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			sentencia = "select tratamientos.nombreTratamiento from tratamientos, tratamiento_citas where tratamientos.idTratamiento = tratamiento_citas.idTratamientoFK AND idCitaFK = "+citaSeleccionada+" ;";
			rs = statement.executeQuery(sentencia);
			listadoTratamientos.selectAll();
			listadoTratamientos.setText("");
			listadoTratamientos.append("Tratamientos\n");
			listadoTratamientos.append("======================\n");
			while (rs.next()) 
			{
				listadoTratamientos.append(rs.getString("tratamientos.nombreTratamiento")+"\n");
			}
		} 
		catch (SQLException e) 
		{
			listadoTratamientos.selectAll();
			listadoTratamientos.setText("");
			listadoTratamientos.append("Error al cargar los datos");	
		}
		finally 
		{
			bd.desconectar(connection);
		}
	}
	
	public boolean insertarTratamientoCita(String datoCita, Choice listaTratamiento) 
	{
		bd = new BaseDatos();
		connection = bd.conectar();
		boolean insertado = true;
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			sentencia = "INSERT INTO tratamiento_citas VALUES(null, "+datoCita+", "+listaTratamiento.getSelectedItem().split("-")[0]+");";
			FicheroLog.guardar(ControladorLogin.nombreUsuario, sentencia);
			statement.executeUpdate(sentencia);
		}
		catch (SQLException e1)
		{
			insertado = false; 
		}
		finally 
		{
			bd.desconectar(connection);
		}
		return insertado;
	}
	
	
	public void consultarAsignaciones(TextArea consultaAsignaciones) 
	{
		bd= new BaseDatos();
		connection = bd.conectar();
		try 
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			sentencia = "SELECT * FROM tratamiento_citas;";
			FicheroLog.guardar(ControladorLogin.nombreUsuario, sentencia);
			rs = statement.executeQuery(sentencia);
			consultaAsignaciones.selectAll();
			consultaAsignaciones.setText("");
			consultaAsignaciones.append("idTratamiento_Cita\tidCita\tidTratamiento\n");
			consultaAsignaciones.append("======================================\n");
			while (rs.next()) 
			{
				consultaAsignaciones.append(rs.getInt("idTratamiento_Cita")+"\t\t"+rs.getInt("idCitaFK")+"\t\t"+rs.getInt("idTratamientoFK")+"\n");
			}
		}
		catch (SQLException e) 
		{
			consultaAsignaciones.selectAll();
			consultaAsignaciones.setText("");
			consultaAsignaciones.append("Error al cargar los datos");	
		}
		finally 
		{
			bd.desconectar(connection);
		}
	}
}

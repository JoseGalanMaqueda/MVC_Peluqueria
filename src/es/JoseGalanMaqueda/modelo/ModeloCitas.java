package es.JoseGalanMaqueda.modelo;

import java.awt.Choice;
import java.awt.TextArea;
import java.awt.TextField;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import es.JoseGalanMaqueda.Controladores.ControladorLogin;

public class ModeloCitas
{
	
	// ====================================== BASE DATOS ==================================================================
	BaseDatos bd = null;
	String sentencia = "";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	public boolean insertarCita(TextField fecha, Choice listahoras, Choice listaClientes) 
	{
		bd = new BaseDatos();
		connection = bd.conectar();
		boolean insertado = true;
		try
		{
			String[] fechaEuropea = fecha.getText().split("/");
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			sentencia = "INSERT INTO citas VALUES (null, '"+ fechaEuropea[2]+"-"+fechaEuropea[1]+"-"+fechaEuropea[0]+ "', '" + listahoras.getSelectedItem() +
					"', "+ listaClientes.getSelectedItem().split("-")[0] +");";
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
	
	public String idUltimaCita() 
	{
		bd = new BaseDatos();
		connection = bd.conectar();
		String idCita = "";
		try 
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			sentencia = "SELECT max(idCita) FROM citas;";
			rs = statement.executeQuery(sentencia);
			while (rs.next()) 
			{
				idCita = rs.getString("max(idCita)");
			}
		} 
		catch (SQLException e) 
		{
			idCita = "";
		}
		finally 
		{
			bd.desconectar(connection);
		}
		return idCita;
	}
	
	public boolean comprobacionCitas(TextField fecha, Choice listaClientes) 
	{
		boolean correcto = true;
		if ((fecha.getText().length() == 0) || (listaClientes.getSelectedItem().equals("Selecciona un Cliente.."))) 
		{
			correcto = false;
		}
		return correcto;
		
	}
	
	public void ConsultaCitas(TextArea consulta) 
	{
		bd= new BaseDatos();
		connection = bd.conectar();
		try 
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			sentencia = "SELECT * FROM citas;";
			FicheroLog.guardar(ControladorLogin.nombreUsuario, sentencia);
			rs = statement.executeQuery(sentencia);
			consulta.selectAll();
			consulta.setText("");
			consulta.append("IdCita\tFecha\tHora\tidCliente\n");
			consulta.append("====================================================\n");
			while (rs.next()) 
			{
				String fecha = rs.getDate("fechaCita")+"";
				String [] fechaEuropea = fecha.split("-");
				String[] quitarSegundos = rs.getString("horaCita").split(":");
				consulta.append(rs.getInt("idCita")+"\t"+fechaEuropea[2]+"/"+fechaEuropea[1]+"/"+fechaEuropea[0]+"\t"+quitarSegundos[0]+":"+quitarSegundos[1]+"\t"+
						rs.getInt("idClienteFK")+"\n");
			}
		}
		catch (SQLException e) 
		{
			consulta.selectAll();
			consulta.setText("");
			consulta.append("Error al cargar los datos");	
		}
		finally 
		{
			bd.desconectar(connection);
		}
	}
	
}






























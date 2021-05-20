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
			sentencia = "select citas.idCita, date_format(citas.fechaCita, '%d-%m-%Y') as 'Fecha',  citas.horaCita as 'Hora', concat(concat(clientes.nombreCliente, ' '), clientes.apellidosCliente) as 'Nombre Clientes'\n"
					+ "from citas \n"
					+ "	join clientes on citas.idClienteFK = clientes.idCliente\n"
					+ "order by DATE_FORMAT(citas.fechaCita, '%Y'), DATE_FORMAT(citas.fechaCita, '%m'), DATE_FORMAT(citas.fechaCita, '%d');";
			FicheroLog.guardar(ControladorLogin.nombreUsuario, sentencia);
			rs = statement.executeQuery(sentencia);
			consulta.selectAll();
			consulta.setText("");
			consulta.append("IdCita\tFecha\tHora\tNombre Clientes\n");
			consulta.append("====================================================\n");
			while (rs.next()) 
			{
				String[] quitarSegundos = rs.getString("Hora").split(":");
				consulta.append(rs.getInt("idCita")+"\t"+rs.getString("Fecha")+"\t"+quitarSegundos[0]+":"+quitarSegundos[1]+"\t"+
						rs.getString("Nombre Clientes")+"\n");
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






























package es.JoseGalanMaqueda.modelo;

import java.awt.Choice;
import java.awt.Desktop;
import java.awt.TextArea;
import java.awt.TextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

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

	public ArrayList<String> obtenerDatosParaExportar()
	{
		bd = new BaseDatos();
		connection = bd.conectar();
		ArrayList<String> datos = new ArrayList<>();

		try 
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			sentencia = "select citas.idCita as id, date_format(citas.fechaCita, '%d-%m-%Y') as 'Fecha',  citas.horaCita as 'Hora', concat(concat(clientes.nombreCliente, ' '), clientes.apellidosCliente) as 'Nombre Clientes'\n"
					+ "from citas \n"
					+ "	join clientes on citas.idClienteFK = clientes.idCliente\n"
					+ "order by DATE_FORMAT(citas.fechaCita, '%Y'), DATE_FORMAT(citas.fechaCita, '%m'), DATE_FORMAT(citas.fechaCita, '%d'), id;";
			FicheroLog.guardar(ControladorLogin.nombreUsuario, sentencia);
			rs = statement.executeQuery(sentencia);
			while (rs.next()) 
			{
				datos.add(rs.getString("id"));
				datos.add(rs.getString("Fecha"));
				datos.add(rs.getString("Hora"));
				datos.add(rs.getString("Nombre Clientes"));
			}

		}
		catch (SQLException e) 
		{
			e.getMessage();
		}
		bd.desconectar(connection);
		return datos;
	}

	public void exportarAPDF(ArrayList<String> datos) 
	{
		Document documento = new Document();
		Paragraph parrafo = new Paragraph("Listado Citas", FontFactory.getFont("arial", 22, Font.BOLD));
		try
		{
			FileOutputStream ficheroPDF = new FileOutputStream("citas.pdf");
			PdfWriter.getInstance(documento, ficheroPDF).setInitialLeading(20);
			documento.open();
			PdfPTable tabla = new PdfPTable(4);
			tabla.addCell("id");
			tabla.addCell("Fecha");
			tabla.addCell("Hora");
			tabla.addCell("Nombre Clientes");

			for (int i =0; i<datos.size(); i++) 
			{
				tabla.addCell(datos.get(i));
			}
			parrafo.setAlignment(Element.ALIGN_CENTER);
			documento.add(parrafo);
			documento.add(new Paragraph("\n"));
			documento.add(tabla);
			documento.close();

			FicheroLog.guardar(ControladorLogin.nombreUsuario, "Generacion PDF citas");

			File path = new File("citas.pdf");
			Desktop.getDesktop().open(path);
		}
		catch (FileNotFoundException e) 
		{
			e.getMessage();
		}
		catch (DocumentException e) 
		{
			e.getMessage();
		}
		catch (IOException e) 
		{
			e.getMessage();
		}

	}

	// =============================== CARGAR DATOS EN LISTADOS ================================================================
	public void cargarListadoClientes(Choice choListaCitas, String citaBuscada) 
	{
		bd = new BaseDatos();
		connection = bd.conectar();
		String[] citaTransformada = citaBuscada.split("/");
		try 
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			sentencia = "SELECT * FROM citas join clientes where citas.idClienteFK = clientes.idCliente AND fechaCita LIKE '"+citaTransformada[2]+"-"+citaTransformada[1]+"-"+citaTransformada[0]+"';";	
			System.out.println(sentencia);
			rs = statement.executeQuery(sentencia);
			choListaCitas.removeAll();
			choListaCitas.add("Selecciona una Cita..");
			while (rs.next()) 
			{
				String[] quitarSegundos = rs.getString("horaCita").split(":");
				choListaCitas.add(rs.getInt("idCita")+"-"+rs.getString("fechaCita")+"-"+quitarSegundos[0]+":"+quitarSegundos[1]+"-"+rs.getString("nombreCliente")+" "+rs.getString("apellidosCliente"));
			}
		}
		catch (SQLException e) 
		{
			choListaCitas.removeAll();
			choListaCitas.add("Problema al cargar los datos");
		}
		finally 
		{
			bd.desconectar(connection);
		}
	}


	public void cargarListadoClientes(Choice choListaCitas) 
	{
		bd = new BaseDatos();
		connection = bd.conectar();
		try 
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			sentencia = "select citas.idCita as id, date_format(citas.fechaCita, '%d-%m-%Y') as 'Fecha',  citas.horaCita as 'Hora', concat(concat(clientes.nombreCliente, ' '), clientes.apellidosCliente) as 'Nombre Clientes'\n"
					+ "from citas\n"
					+ "	join clientes on citas.idClienteFK = clientes.idCliente\n"
					+ "order by DATE_FORMAT(citas.fechaCita, '%Y'), DATE_FORMAT(citas.fechaCita, '%m'), DATE_FORMAT(citas.fechaCita, '%d'), id;";
			rs = statement.executeQuery(sentencia);
			choListaCitas.removeAll();
			choListaCitas.add("Selecciona una Cita..");
			while (rs.next()) 
			{
				String[] quitarSegundos = rs.getString("Hora").split(":");
				choListaCitas.add(rs.getInt("id")+"-"+rs.getString("Fecha")+"-"+quitarSegundos[0]+":"+quitarSegundos[1]+"-"+rs.getString("Nombre Clientes"));
			}
		}
		catch (SQLException e) 
		{
			choListaCitas.removeAll();
			choListaCitas.add("Problema al cargar los datos");
		}
		finally 
		{
			bd.desconectar(connection);
		}
	}

	// ===================================== ELIMINAR CLIENTES ========================================================
	public boolean eliminarClientes(Choice choListaCita) 
	{
		bd = new BaseDatos();
		connection = bd.conectar();
		boolean eliminado = true;
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			sentencia = "DELETE FROM citas WHERE idCita = "+choListaCita.getSelectedItem().split("-")[0]+";";
			FicheroLog.guardar(ControladorLogin.nombreUsuario, sentencia);
			statement.executeUpdate(sentencia);
		}
		catch (SQLException e1)
		{
			eliminado = false;
		}
		finally 
		{
			bd.desconectar(connection);
		}
		return eliminado;
	}
	
	
	public void consultaPrincipal(TextArea textArea) 
	{
		bd= new BaseDatos();
		connection = bd.conectar();
		String linea = "";
		try 
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			sentencia = "select idCita as \"Id\", date_format(fechaCita, \"%d/%m/%Y\") as \"Fecha\", horaCita as \"Hora\", concat(clientes.nombreCliente, concat(\" \", clientes.apellidosCliente)) as \"Nombre Cliente\" \n"
					+ "from citas join clientes on citas.idClienteFK = clientes.idCliente;";
			FicheroLog.guardar(ControladorLogin.nombreUsuario, sentencia);
			rs = statement.executeQuery(sentencia);
			ResultSet rs2;
			Statement statement2 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			textArea.selectAll();
			textArea.setText("");
			textArea.append("IdCita\tFecha\tHora\tNombre Clientes\tTratamientos\n");
			textArea.append("====================================================\n");
			while (rs.next()) 
			{
				String[] quitarSegundos = rs.getString("Hora").split(":");
				int idCita = rs.getInt("Id");
				String sentencia2 = "select nombreTratamiento from tratamiento_citas\n"
						+ "join tratamientos on tratamientos.idTratamiento = tratamiento_citas.idTratamientoFK\n"
						+ "where idCitaFk = "+idCita+";";
				rs2 = statement2.executeQuery(sentencia2);
				linea = rs.getInt("Id")+"\t"+rs.getString("Fecha")+"\t"+quitarSegundos[0]+":"+quitarSegundos[1]+"\t"+ rs.getString("Nombre Cliente") + "\t";
				while(rs2.next()) 
				{
					
					linea = linea + rs2.getString("NombreTratamiento") + ", ";
					
				}
				textArea.append(linea + "\n");
			}
		}
		catch (SQLException e) 
		{
			textArea.selectAll();
			textArea.setText("");
			textArea.append("Error al cargar los datos" + e.getMessage());	
		}
		finally 
		{
			bd.desconectar(connection);
		}
	}

}






























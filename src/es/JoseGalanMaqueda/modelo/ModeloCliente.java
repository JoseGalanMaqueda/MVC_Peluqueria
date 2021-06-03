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

public class ModeloCliente
{
	// ================================ BASE DATOS ===============================================
	BaseDatos bd = null;
	String sentencia = "";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;


	// ================================ COMPROBACION DATOS EN ALTA CLIENTES ===================================================
	public boolean comprobacionDatosIntroducidos(TextField nombre, TextField apellidos, TextField dni, TextField direccion, String eleccion) 
	{
		boolean booleano = false;
		if( (nombre.getText().length()!=0) && (apellidos.getText().length() != 0) && (dni.getText().length()!=0) && (direccion.getText().length()!=0) && (eleccion.length()!=0) ) 
		{
			if (dni.getText().length() == 9)
			{
				booleano = true;
			}
		}
		return booleano;
	}

	//================================ INSERTA DATOS EN TABLA CLIENTES ======================================================================
	public boolean insertarDatosClientes(TextField nombre, TextField apellidos, TextField dni, TextField direccion, String eleccion) 
	{
		bd = new BaseDatos();
		connection = bd.conectar();
		boolean insertado = true;
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			sentencia = "INSERT INTO clientes VALUES (null, '"+ nombre.getText()+ "', '" +apellidos.getText() + "', '"+ dni.getText() +"', '" + direccion.getText() + "', '" + eleccion +"');";
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

	// =============================== CARGAR DATOS EN LISTADOS CON BUSQUEDA DE CLIENTE ================================================================
	public void cargarListadoClientes(Choice cholistaClientes, String clienteBuscado) 
	{
		bd = new BaseDatos();
		connection = bd.conectar();
		try 
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			sentencia = "SELECT * FROM clientes WHERE nombreCliente LIKE '"+clienteBuscado+"%';";
			rs = statement.executeQuery(sentencia);
			cholistaClientes.removeAll();
			cholistaClientes.add("Selecciona un Cliente..");
			while (rs.next()) 
			{
				cholistaClientes.add(rs.getInt("idCliente")+"-"+rs.getString("nombreCliente")+"-"+rs.getString("apellidosCliente")+"-"+rs.getString("dniCliente"));
			}
		}
		catch (SQLException e) 
		{
			cholistaClientes.removeAll();
			cholistaClientes.add("Problema al cargar los datos");
		}
		finally 
		{
			bd.desconectar(connection);
		}
	}
	
	// =================================== DATOS CLIENTE PARA SELECCIONAR EN LISTADO ====================================================================
	public String cargarDatoCliente(String clienteBuscado) 
	{
		bd = new BaseDatos();
		connection = bd.conectar();
		String datosCliente= "";
		try 
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			sentencia = "SELECT * FROM clientes WHERE idCliente LIKE "+clienteBuscado+";";
			rs = statement.executeQuery(sentencia);
			while (rs.next()) 
			{
				datosCliente = rs.getInt("idCliente")+"-"+rs.getString("nombreCliente")+"-"+rs.getString("apellidosCliente")+"-"+rs.getString("dniCliente");
			}
		}
		catch (SQLException e) 
		{
		}
		finally 
		{
			bd.desconectar(connection);
		}
		
		return datosCliente;
	}

	
	// ==================================== CARGAR DATOS LISTADOS DE MANERA GENERICA ==========================================================================
	public void cargarListadoClientes(Choice cholistaClientes) 
	{
		bd = new BaseDatos();
		connection = bd.conectar();
		try 
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			sentencia = "SELECT * FROM clientes;";
			rs = statement.executeQuery(sentencia);
			cholistaClientes.removeAll();
			cholistaClientes.add("Selecciona un Cliente..");
			while (rs.next()) 
			{
				cholistaClientes.add(rs.getInt("idCliente")+"-"+rs.getString("nombreCliente")+"-"+rs.getString("apellidosCliente")+"-"+rs.getString("dniCliente"));
			}
		}
		catch (SQLException e) 
		{
			cholistaClientes.removeAll();
			cholistaClientes.add("Problema al cargar los datos");
		}
		finally 
		{
			bd.desconectar(connection);
		}
	}

	// ===================================== ELIMINAR CLIENTES ========================================================
	public boolean eliminarClientes(Choice choListaCliente) 
	{
		bd = new BaseDatos();
		connection = bd.conectar();
		boolean eliminado = true;
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			sentencia = "DELETE FROM clientes WHERE idCliente = "+choListaCliente.getSelectedItem().split("-")[0]+";";
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


	// ====================================== METODOS ACTUALIZAR CLIENTES =========================================
	public boolean actualizarCliente(TextField id,TextField nombre, TextField apellidos, TextField dni, TextField direccion, String eleccion) 
	{
		bd = new BaseDatos();
		connection = bd.conectar();
		boolean actualizado = true;
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			sentencia = "UPDATE clientes SET nombreCliente = '"+nombre.getText()+"',"
					+ "apellidosCliente = '"+apellidos.getText()+"',"
					+ " dniCliente = '"+dni.getText()+"', "
					+ "direccionCliente = '"+direccion.getText()+"', "
					+ "sexoCliente = '"+eleccion+"' "
					+ "WHERE idCliente = "+id.getText()+"";
			FicheroLog.guardar(ControladorLogin.nombreUsuario, sentencia);
			statement.executeUpdate(sentencia);
		}
		catch (SQLException e1)
		{
			actualizado = false;
		}
		finally 
		{
			bd.desconectar(connection);
		}
		return actualizado;
	}

	// ============================================= CARGA DATOS EN LA VENTADA DE MODIFICICION DE CLIENTES =============================
	public String cargarDatosCliente(String id) 
	{
		bd= new BaseDatos();
		connection = bd.conectar();
		String valores = "";
		try 
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			sentencia = "SELECT * FROM clientes WHERE idCliente = "+id+";";
			rs = statement.executeQuery(sentencia);
			while (rs.next()) 
			{
				valores = rs.getInt("idCliente")+"-"+rs.getString("nombreCliente")+"-"+rs.getString("apellidosCliente")+"-"+
						rs.getString("dniCliente")+"-"+rs.getString("direccionCliente")+"-"+rs.getString("sexoCliente");
			}
		}
		catch (SQLException e) 
		{
			valores = "";	
		}
		finally 
		{
			bd.desconectar(connection);
		}
		return valores;
	}



	// ===================================== CONSULTAR CLIENTES ======================================================
	public void consultaClientes(TextArea txaConsultaClientes) 
	{
		bd= new BaseDatos();
		connection = bd.conectar();
		try 
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			sentencia = "SELECT * FROM clientes;";
			FicheroLog.guardar(ControladorLogin.nombreUsuario, sentencia);
			rs = statement.executeQuery(sentencia);
			txaConsultaClientes.selectAll();
			txaConsultaClientes.setText("");
			txaConsultaClientes.append("Id\tNombre\tApellidos\tDNI\tDireccion\tSexo\n");
			txaConsultaClientes.append("====================================================\n");
			while (rs.next()) 
			{
				txaConsultaClientes.append(rs.getInt("idCliente")+"\t"+rs.getString("nombreCliente")+"\t"+rs.getString("apellidosCliente")+"\t"+
						rs.getString("dniCliente")+"\t"+rs.getString("direccionCliente")+"\t"+rs.getString("sexoCliente")+"\n");
			}
		}
		catch (SQLException e) 
		{
			txaConsultaClientes.selectAll();
			txaConsultaClientes.setText("");
			txaConsultaClientes.append("Error al cargar los datos");	
		}
		finally 
		{
			bd.desconectar(connection);
		}
	}
	
	
	// ==================================== EXPORTAR A PDF ======================================
	public ArrayList<String> obtenerDatosParaExportar()
	{
		bd = new BaseDatos();
		connection = bd.conectar();
		ArrayList<String> datos = new ArrayList<>();
		
		try 
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			sentencia = "SELECT * FROM clientes;";
			FicheroLog.guardar(ControladorLogin.nombreUsuario, sentencia);
			rs = statement.executeQuery(sentencia);
			while (rs.next()) 
			{
				datos.add(rs.getString("IdCliente"));
				datos.add(rs.getString("nombreCliente"));
				datos.add(rs.getString("apellidosCliente"));
				datos.add(rs.getString("dniCliente"));
				datos.add(rs.getString("direccionCliente"));
				datos.add(rs.getString("sexoCliente"));
			}
			
		}
		catch (SQLException e) 
		{
			e.getMessage();
		}
		bd.desconectar(connection);
		return datos;
	}
	
	// =========================================== EXPORTA A PDF ==================================================
	public void exportarAPDF(ArrayList<String> datos) 
	{
		Document documento = new Document();
		Paragraph parrafo = new Paragraph("Listado Clientes", FontFactory.getFont("arial", 22, Font.BOLD));
		try
		{
			FileOutputStream ficheroPDF = new FileOutputStream("clientes.pdf");
			PdfWriter.getInstance(documento, ficheroPDF).setInitialLeading(20);
			documento.open();
			PdfPTable tabla = new PdfPTable(6);
			tabla.addCell("IdCliente");
			tabla.addCell("Nombre");
			tabla.addCell("Apellidos");
			tabla.addCell("Dni");
			tabla.addCell("Direccion");
			tabla.addCell("Sexo");
			
			for (int i =0; i<datos.size(); i++) 
			{
				tabla.addCell(datos.get(i));
			}
			parrafo.setAlignment(Element.ALIGN_CENTER);
			documento.add(parrafo);
			documento.add(new Paragraph("\n"));
			documento.add(tabla);
			documento.close();
			
			FicheroLog.guardar(ControladorLogin.nombreUsuario, "Generacion PDF clientes");
			
			File path = new File("clientes.pdf");
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
	
	
	
	




}

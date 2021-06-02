package es.JoseGalanMaqueda.modelo;

import java.awt.Choice;
import java.awt.Desktop;
import java.awt.TextArea;
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
			sentencia = "SELECT tratamiento_citas.idTratamiento_Cita as 'Id', concat(concat('[ ', date_format(citas.fechaCita, '%d-%m-%Y')),' ', citas.horaCita, ' ]') as 'Fecha y Hora Cita', tratamientos.nombreTratamiento as 'Nombre Tratamiento'\n"
					+ " FROM tratamiento_citas\n"
					+ "join citas on tratamiento_citas.idCitaFk = citas.idCita\n"
					+ "join tratamientos on tratamiento_citas.idTratamientoFK = tratamientos.idTratamiento\n"
					+ "order by DATE_FORMAT(citas.fechaCita, '%Y'), DATE_FORMAT(citas.fechaCita, '%m'), DATE_FORMAT(citas.fechaCita, '%d'),id;";
			FicheroLog.guardar(ControladorLogin.nombreUsuario, sentencia);
			rs = statement.executeQuery(sentencia);
			consultaAsignaciones.selectAll();
			consultaAsignaciones.setText("");
			consultaAsignaciones.append("Id\tFecha y Hora Cita\tNombre Tratamiento\n");
			consultaAsignaciones.append("======================================\n");
			while (rs.next()) 
			{
				consultaAsignaciones.append(rs.getInt("Id")+"\t\t"+rs.getString("Fecha y Hora Cita")+"\t\t"+rs.getString("Nombre Tratamiento")+"\n");
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

	public ArrayList<String> obtenerDatosParaExportar()
	{
		bd = new BaseDatos();
		connection = bd.conectar();
		ArrayList<String> datos = new ArrayList<>();

		try 
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			sentencia = "SELECT tratamiento_citas.idTratamiento_Cita as 'Id', concat(concat('[ ', date_format(citas.fechaCita, '%d/%m/%Y')),' ', citas.horaCita, ' ]') as 'Fecha y Hora Cita', tratamientos.nombreTratamiento as 'Nombre Tratamiento'\n"
					+ " FROM tratamiento_citas\n"
					+ "join citas on tratamiento_citas.idCitaFk = citas.idCita\n"
					+ "join tratamientos on tratamiento_citas.idTratamientoFK = tratamientos.idTratamiento\n"
					+ "order by DATE_FORMAT(citas.fechaCita, '%Y'), DATE_FORMAT(citas.fechaCita, '%m'), DATE_FORMAT(citas.fechaCita, '%d'),id;";
			FicheroLog.guardar(ControladorLogin.nombreUsuario, sentencia);
			rs = statement.executeQuery(sentencia);
			while (rs.next()) 
			{
				datos.add(rs.getString("Id"));
				datos.add(rs.getString("Fecha y Hora Cita"));
				datos.add(rs.getString("Nombre Tratamiento"));
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
		Paragraph parrafo = new Paragraph("Listado Asignaciones a Citas", FontFactory.getFont("arial", 22, Font.BOLD));
		try
		{
			FileOutputStream ficheroPDF = new FileOutputStream("asignaciones.pdf");
			PdfWriter.getInstance(documento, ficheroPDF).setInitialLeading(20);
			documento.open();
			PdfPTable tabla = new PdfPTable(3);
			tabla.addCell("Id");
			tabla.addCell("Fecha y Hora Cita");
			tabla.addCell("Nombre Tratamientos");

			for (int i =0; i<datos.size(); i++) 
			{
				tabla.addCell(datos.get(i));
			}
			parrafo.setAlignment(Element.ALIGN_CENTER);
			documento.add(parrafo);
			documento.add(new Paragraph("\n"));
			documento.add(tabla);
			documento.close();

			FicheroLog.guardar(ControladorLogin.nombreUsuario, "Generacion PDF Asignaciones a Citas");

			File path = new File("asignaciones.pdf");
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
	public void cargarListadoAsignaciones(Choice cholistaAsignaciones, String AsignacionBuscada) 
	{
		bd = new BaseDatos();
		connection = bd.conectar();
		try 
		{
			String[] fecha = AsignacionBuscada.split("/");
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			sentencia = "SELECT tratamiento_citas.idTratamiento_Cita as 'Id', concat(concat('[ ', date_format(citas.fechaCita, '%d/%m/%Y')),' ', citas.horaCita, ' ]') as 'Fecha y Hora Cita', tratamientos.nombreTratamiento as 'Nombre Tratamiento'\n"
					+ " FROM tratamiento_citas\n"
					+ "join citas on tratamiento_citas.idCitaFk = citas.idCita\n"
					+ "join tratamientos on tratamiento_citas.idTratamientoFK = tratamientos.idTratamiento\n"
					+ "where citas.fechaCita like '"+fecha[2]+"-"+fecha[1]+"-"+fecha[0]+"'\n"
					+ "order by DATE_FORMAT(citas.fechaCita, '%Y'), DATE_FORMAT(citas.fechaCita, '%m'), DATE_FORMAT(citas.fechaCita, '%d'),id;";
			rs = statement.executeQuery(sentencia);
			cholistaAsignaciones.removeAll();
			cholistaAsignaciones.add("Selecciona una Asignacion..");
			while (rs.next()) 
			{
				cholistaAsignaciones.add(rs.getInt("Id")+"-"+rs.getString("Fecha y Hora Cita")+"-"+rs.getString("Nombre Tratamiento"));
			}
		}
		catch (SQLException e) 
		{
			cholistaAsignaciones.removeAll();
			cholistaAsignaciones.add("Problema al cargar los datos");
		}
		finally 
		{
			bd.desconectar(connection);
		}
	}


	public void cargarListadoAsignaciones(Choice choListaAsignaciones) 
	{
		bd = new BaseDatos();
		connection = bd.conectar();
		try 
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			sentencia = "SELECT tratamiento_citas.idTratamiento_Cita as 'Id', concat(concat('[ ', date_format(citas.fechaCita, '%d/%m/%Y')),' ', citas.horaCita, ' ]') as 'Fecha y Hora Cita', tratamientos.nombreTratamiento as 'Nombre Tratamiento'\n"
					+ " FROM tratamiento_citas\n"
					+ "join citas on tratamiento_citas.idCitaFk = citas.idCita\n"
					+ "join tratamientos on tratamiento_citas.idTratamientoFK = tratamientos.idTratamiento\n"
					+ "order by DATE_FORMAT(citas.fechaCita, '%Y'), DATE_FORMAT(citas.fechaCita, '%m'), DATE_FORMAT(citas.fechaCita, '%d'),id;";
			rs = statement.executeQuery(sentencia);
			choListaAsignaciones.removeAll();
			choListaAsignaciones.add("Selecciona una Asignacion..");
			while (rs.next()) 
			{
				choListaAsignaciones.add(rs.getInt("Id")+"-"+rs.getString("Fecha y Hora Cita")+"-"+rs.getString("Nombre Tratamiento"));
			}
		}
		catch (SQLException e) 
		{
			choListaAsignaciones.removeAll();
			choListaAsignaciones.add("Problema al cargar los datos");
		}
		finally 
		{
			bd.desconectar(connection);
		}
	}

	// ===================================== ELIMINAR CLIENTES ========================================================
	public boolean eliminarAsignacion(Choice choListaAsignaciones) 
	{
		bd = new BaseDatos();
		connection = bd.conectar();
		boolean eliminado = true;
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			sentencia = "DELETE FROM tratamiento_citas WHERE idTratamiento_Cita = "+choListaAsignaciones.getSelectedItem().split("-")[0]+";";
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
}

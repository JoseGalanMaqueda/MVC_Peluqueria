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

public class ModeloTratamientosCitas
{
	// ================================ BASE DATOS ===============================================
	BaseDatos bd = null;
	String sentencia = "";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	//================================ RELLENA TEXTAREA DE ALTA ASIGNACIONES ==================================================================
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

	//=================================== ALTA ASIGNACIONES ===============================================================================
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


	//=================================== CONSULTA ASIGNACIONES =====================================================================
	public void consultarAsignaciones(TextArea consultaAsignaciones) 
	{
		bd= new BaseDatos();
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
			FicheroLog.guardar(ControladorLogin.nombreUsuario, sentencia);
			rs = statement.executeQuery(sentencia);
			consultaAsignaciones.selectAll();
			consultaAsignaciones.setText("");
			consultaAsignaciones.append("Id\tFecha y Hora Cita\tNombre Tratamiento\n");
			consultaAsignaciones.append("======================================\n");
			while (rs.next()) 
			{
				consultaAsignaciones.append(rs.getInt("Id")+"\t"+rs.getString("Fecha y Hora Cita")+"\t"+rs.getString("Nombre Tratamiento")+"\n");
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

	
	// ======================================= PREPARA ARRAY ANTES DE EXPORTAR A PDF =====================================================
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

	
	// ====================================== EXPORTA A PDF ==========================================
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

	// =============================== CARGAR DATOS EN LISTADO CUANDO FILTRA POR BUSQUEDA ================================================================
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


	// ============================ CARGA DATOS ASIGNACIONES DE MANERA GENERICA =====================================================================
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
	
	
	// ========================================== CARGA DATOS ASIGNACIONES EN MODIFICAR ASIGNACIONES =================================
	public String cargarDatosAsignaciones(String id) 
	{
		bd= new BaseDatos();
		connection = bd.conectar();
		String valores = "";
		try 
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			sentencia = "SELECT * FROM tratamiento_citas WHERE idTratamiento_Cita = "+id+";";
			rs = statement.executeQuery(sentencia);
			while (rs.next()) 
			{
				valores = rs.getInt("idTratamiento_Cita")+"-"+rs.getInt("idCitaFk")+"-"+rs.getInt("idTratamientoFK");
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
	
	// ====================================== METODOS ACTUALIZAR ASIGNACIONES =========================================
	public boolean actualizarAsignaciones(TextField id,Choice listaCitas, Choice listaTratamientos) 
	{
		bd = new BaseDatos();
		connection = bd.conectar();
		boolean actualizado = true;
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			sentencia = "update tratamiento_citas set idCitaFK = "+listaCitas.getSelectedItem().split("-")[0]+", idTratamientoFK = "+listaTratamientos.getSelectedItem().split("-")[0]+"\n"
					+ "where idTratamiento_Cita = "+id.getText()+";";
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
	
	// ================================== COMPROBACION DATOS CHOICE ANTES ACTUALIZAR DATOS ==============================================
	public boolean comprobacionDatosAsignaciones(Choice listaCitas, Choice listaTratamientos) 
	{ 
		bd = new BaseDatos();
		connection = bd.conectar();
		boolean actualizado = true;
		if ((!listaCitas.getSelectedItem().equals("Selecciona una Cita.."))  && (!listaTratamientos.getSelectedItem().equals("Selecciona un Tratamiento..")) ) 
		{
			actualizado = true;
		}
		else 
		{
			actualizado = false;
		}
		return actualizado;
	}
}

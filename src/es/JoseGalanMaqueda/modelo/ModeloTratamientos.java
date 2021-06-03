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

public class ModeloTratamientos
{
	// ================================ BASE DATOS ===============================================
	BaseDatos bd = null;
	String sentencia = "";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	
	
	// ================================ COMPROBACION DATOS ANTES INSERTAR TRATAMIENTOS ============================================
	public boolean comprobacionDatos(TextField nombre) 
	{
		boolean booleano = false;
		if (nombre.getText().length()!=0) {
			booleano = true;
		}
		return booleano;
	}
	
	// ============================= INSERTAR DATOS DE TRATAMIENTOS =============================================================
	public boolean insertarDatosTratamientos(TextField nombre, Choice precio, TextArea descripcion) 
	{
		bd = new BaseDatos();
		connection = bd.conectar();
		boolean insertado = true;
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			sentencia = "INSERT INTO tratamientos VALUES (null, '"+ nombre.getText()+ "', '" +descripcion.getText()+ "', "+ precio.getSelectedItem() +");";
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
	
	// ======================== CARGA LISTA TRATAMIENTOS DE MANERA GENERICA =========================================
	public void cargarListadoTratamientos(Choice choListaTratamientos) 
	{
		bd = new BaseDatos();
		connection = bd.conectar();
		try 
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			sentencia = "SELECT * FROM tratamientos;";
			rs = statement.executeQuery(sentencia);
			choListaTratamientos.removeAll();
			choListaTratamientos.add("Selecciona un Tratamiento..");
			while (rs.next()) 
			{
				choListaTratamientos.add(rs.getInt("idTratamiento")+"-"+rs.getString("nombreTratamiento"));
			}
		}
		catch (SQLException e) 
		{
			choListaTratamientos.removeAll();
			choListaTratamientos.add("Problema al cargar los datos");
		}
		finally 
		{
			bd.desconectar(connection);
		}
	}
	
	// ============================ CARGA LISTADO TRATAMIENTOS FILTRADOS POR BUSQUEDA ===================================================
	public void cargarListadoTratamientos(Choice choListaTratamientos, String TratamientoBuscado) 
	{
		bd = new BaseDatos();
		connection = bd.conectar();
		try 
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			sentencia = "SELECT * FROM tratamientos WHERE nombreTratamiento LIKE '"+TratamientoBuscado+"%';";
			rs = statement.executeQuery(sentencia);
			choListaTratamientos.removeAll();
			choListaTratamientos.add("Selecciona un Tratamiento..");
			while (rs.next()) 
			{
				choListaTratamientos.add(rs.getInt("idTratamiento")+"-"+rs.getString("nombreTratamiento"));
			}
		}
		catch (SQLException e) 
		{
			choListaTratamientos.removeAll();
			choListaTratamientos.add("Problema al cargar los datos");
		}
		finally 
		{
			bd.desconectar(connection);
		}
	}
	
	// =========================== ELIMINAR TRATAMIENTO =====================================================================================
	public boolean eliminarTratamiento(Choice choListaTratamientos) 
	{
		bd = new BaseDatos();
		connection = bd.conectar();
		boolean eliminado = true;
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			sentencia = "DELETE FROM tratamientos WHERE idTratamiento = "+choListaTratamientos.getSelectedItem().split("-")[0]+";";
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
	
	
	// ======================================= CARGA DATOS TRATAMIENTOS PARA VENTANA MODIFICACION ==============================================
	public String cargarDatosTratamientos(String id) 
	{
		bd= new BaseDatos();
		connection = bd.conectar();
		String valores = "";
		try 
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			sentencia = "SELECT * FROM tratamientos WHERE idTratamiento = "+id+";";
			rs = statement.executeQuery(sentencia);
			while (rs.next()) 
			{
				valores = rs.getInt("idTratamiento")+"-"+rs.getString("nombreTratamiento")+"-"+rs.getString("descripcionTratamiento")+"-"+
						rs.getDouble("precioTratamiento");
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
	
	
	// =================================== ACTUALIZACION DE TRATAMIENTOS ================================================================
	public boolean actulizarTratamientos(TextField id, TextField nombre,Choice precios ,TextArea descripcion ) 
	{
		bd = new BaseDatos();
		connection = bd.conectar();
		boolean actualizado = true;
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			sentencia = "UPDATE tratamientos SET nombreTratamiento = '"+nombre.getText()+"',"
					+ "descripcionTratamiento = '"+descripcion.getText()+"',"
					+ " precioTratamiento = "+precios.getSelectedItem()+" "
					+ "WHERE idTratamiento = "+id.getText()+";";
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
	
	// =========================== CONSULTA TRATAMIENTOS ==========================
	
	public void consultaTratamientos(TextArea txaConsultaTratamientos) 
	{
		bd= new BaseDatos();
		connection = bd.conectar();
		try 
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			sentencia = "SELECT * FROM tratamientos;";
			FicheroLog.guardar(ControladorLogin.nombreUsuario, sentencia);
			rs = statement.executeQuery(sentencia);
			txaConsultaTratamientos.selectAll();
			txaConsultaTratamientos.setText("");
			txaConsultaTratamientos.append("IdTratamiento\tNombre\tDescripcion\tPrecio\n");
			txaConsultaTratamientos.append("=======================================================================\n");
			while (rs.next()) 
			{
				txaConsultaTratamientos.append(rs.getInt("idTratamiento")+"\t"+rs.getString("nombreTratamiento")+"\t"+rs.getString("descripcionTratamiento")+"\t"+
						rs.getDouble("precioTratamiento")+"0 €\n");
			}
		}
		catch (SQLException e) 
		{
			txaConsultaTratamientos.selectAll();
			txaConsultaTratamientos.setText("");
			txaConsultaTratamientos.append("Error al cargar los datos");
			bd.desconectar(connection);
		}
	}
	
	// ================================ OBTIENE DATOS PARA EXPORTARLOS MEDIANTE PDF ====================================================================
	public ArrayList<String> obtenerDatosParaExportar()
	{
		bd = new BaseDatos();
		connection = bd.conectar();
		ArrayList<String> datos = new ArrayList<>();
		
		try 
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			sentencia = "SELECT * FROM tratamientos;";
			FicheroLog.guardar(ControladorLogin.nombreUsuario, sentencia);
			rs = statement.executeQuery(sentencia);
			while (rs.next()) 
			{
				datos.add(rs.getString("idTratamiento"));
				datos.add(rs.getString("nombreTratamiento"));
				datos.add(rs.getString("descripcionTratamiento"));
				datos.add(rs.getDouble("precioTratamiento")+"0 €");
			}
			
		}
		catch (SQLException e) 
		{
			e.getMessage();
		}
		bd.desconectar(connection);
		return datos;
	}
	
	
	// ==================================== EXPORTA A PDF ==================================================================================
	public void exportarAPDF(ArrayList<String> datos) 
	{
		Document documento = new Document();
		Paragraph parrafo = new Paragraph("Listado Tratamientos", FontFactory.getFont("arial", 22, Font.BOLD));
		try
		{
			FileOutputStream ficheroPDF = new FileOutputStream("tratamientos.pdf");
			PdfWriter.getInstance(documento, ficheroPDF).setInitialLeading(20);
			documento.open();
			PdfPTable tabla = new PdfPTable(4);
			tabla.addCell("IdTratamiento");
			tabla.addCell("Nombre");
			tabla.addCell("Descripcion");
			tabla.addCell("Precio");
			
			for (int i =0; i<datos.size(); i++) 
			{
				tabla.addCell(datos.get(i));
			}
			parrafo.setAlignment(Element.ALIGN_CENTER);
			documento.add(parrafo);
			documento.add(new Paragraph("\n"));
			documento.add(tabla);
			documento.close();
			
			FicheroLog.guardar(ControladorLogin.nombreUsuario, "Generacion PDF tratamientos");
			
			File path = new File("tratamientos.pdf");
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
	
	// ============================== CARGA TRATAMIENTO PARA LUEGO SELECCIONARLAS DENTRO DE LA LISTA DE MODIFICACION ===============================================
		public String cargarDatoTratamiento(String idTratamiento) 
		{
			bd = new BaseDatos();
			connection = bd.conectar();
			String datosTratamiento= "";
			try 
			{
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				sentencia = "select * from tratamientos where idTratamiento like "+idTratamiento+";";
				rs = statement.executeQuery(sentencia);
				while (rs.next()) 
				{
					datosTratamiento = rs.getInt("idTratamiento")+"-"+rs.getString("nombreTratamiento");
				}
			}
			catch (SQLException e) 
			{
			}
			finally 
			{
				bd.desconectar(connection);
			}
			
			return datosTratamiento;
		}
}














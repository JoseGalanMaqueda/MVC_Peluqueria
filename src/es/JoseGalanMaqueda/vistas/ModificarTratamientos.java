package es.JoseGalanMaqueda.vistas;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;

public class ModificarTratamientos 
{
	// ========================= MODIFICAR TRATAMIENTO PRINCIPAL ===============================================
	public Frame frmModificarTratamientoUno = new Frame("Modificar Cliente");
	Label lblSeleccionarModificarTratamiento = new Label("Selecciona Tratamiento a Modificar:");
	public Choice choListaTratamientos = new Choice();
	public Button btnModificarUno = new Button("Modificar");
	public Button btnCancelarModificarUno = new Button("Cancelar");
	Color clFondo = new Color(204,229,255);
	Label lblBuscarTratamiento = new Label("Buscar");
	public TextField txtBuscarTratamiento = new TextField(12);
	public Button btnBuscarTratamiento = new Button("Buscar");
	
	// ===================================== VENTANA ALTA CLIENTES =======================================================
	public Frame frmModificarTratamientoDos = new Frame("Modificacion Tratamientos");
	Label lblIdModificacionTratamientoDos = new Label("Id:");
	Label lblNombreModificacionTratamientoDos = new Label("Nombre:");
	Label lblPrecioModificacionTratamientoDos = new Label("Precio:");
	Label lblDescipcionModificacionTratamientoDos = new Label("Descripcion:");
	public TextField txtIdModificacionTratamientoDos = new TextField(5);
	public TextField txtNombreModificacionTratamientoDos = new TextField(14);
	String[] precios = {"0.00","1.00","1.50","2.00","2.50","3.00","3.50","4.00","4.50","5.00","5.50","6.00","6.50","7.00","7.50","8.00","8.50","9.00","9.50"};
	public Choice listaPrecios = new Choice(); 
	public TextArea txaDescripcionModificacion = new TextArea(7, 45);
	public Button btnModificacionTratamientoDos = new Button("Modificar");
	public Button btnCancelarModificacionTratamientoDos = new Button("Cancelar");

	// ========================= CONFIRMACION MODIFICAR TRATAMIENTOS ====================================================
	public Frame frmConfirmacionModificarTratamiento = new Frame("Confirmacion");
	public Label lblConfirmacionModificarTratamiento = new Label("¿Estás seguro que deseas Modificarlo?");
	public Button btnSiConfirmacionModificarTratamiento = new Button("Si");
	public Button btnNoConfirmacionModificarTratamiento = new Button("No");

	// ============================== DIALOGO NOTIFICACION ==================================
	public Dialog dlgTratamientoModificado = new Dialog(frmConfirmacionModificarTratamiento, "Operacion Correcta", true);
	public Label lblModificadoCorrectamente = new Label("Tratamiento Modificado Correctamente");

	// ============================== DIALOGO NOTIFICACION ==================================
	public Dialog dlgErrorModificarTratamiento = new Dialog(frmConfirmacionModificarTratamiento, "Error", true);
	public Label lblErrorModificarTratamiento = new Label("Error al Modificar Tratamiento");

	// ============================== DIALOGO NOTIFICACION ==================================
	public Dialog dlgErrorModificarSeleccionarTratamiento = new Dialog(frmModificarTratamientoUno, "Error", true);
	public Label lblErrorModificarSeleccionarTratamiento = new Label("Selecciona un Tratamiento válido");

	public ModificarTratamientos() 
	{
		frmModificarTratamientoUno.setLayout(new FlowLayout());
		frmModificarTratamientoUno.setSize(270, 170);
		frmModificarTratamientoUno.setBackground(clFondo);
		frmModificarTratamientoUno.add(lblBuscarTratamiento);
		frmModificarTratamientoUno.add(txtBuscarTratamiento);
		btnBuscarTratamiento.setBackground(Color.white);
		frmModificarTratamientoUno.add(btnBuscarTratamiento);
		frmModificarTratamientoUno.add(lblSeleccionarModificarTratamiento);
		choListaTratamientos.setBackground(Color.WHITE);
		frmModificarTratamientoUno.add(choListaTratamientos);
		btnModificarUno.setBackground(Color.WHITE);
		btnCancelarModificarUno.setBackground(Color.WHITE);
		frmModificarTratamientoUno.add(btnModificarUno);
		frmModificarTratamientoUno.add(btnCancelarModificarUno);
		frmModificarTratamientoUno.setResizable(false);
		frmModificarTratamientoUno.setLocationRelativeTo(null);
		frmModificarTratamientoUno.setVisible(true);
	}
	
	// ====================================== CREAR VENTANA CONFIRMACION =====================================================================
	public void creacionVentanaConfirmacion(Frame ventana, Label lbl, Button btnUno, Button btnDos) 
	{
		ventana.setSize(250, 100);
		ventana.setLayout(new FlowLayout());
		ventana.setBackground(clFondo);
		ventana.setLocationRelativeTo(null);
		ventana.add(lbl);
		btnUno.setBackground(Color.WHITE);
		btnDos.setBackground(Color.WHITE);
		ventana.add(btnUno);
		ventana.add(btnDos);
		ventana.setVisible(true);
	}

	// ======================================= CREAR DIALOGO NOTIFICACION ==================================================================
	public void creacionDialogoNotificacion(Dialog dialogo, Label lbl) 
	{
		dialogo.setSize(250, 100);
		dialogo.setLayout(new FlowLayout());
		dialogo.setBackground(clFondo);
		dialogo.add(lbl);
		dialogo.setLocationRelativeTo(null);
	}
	
	// ================================================ CARGAR VENTANA CON DATOS ======================================
	public void cargarVentanaEdicionTratamiento() 
	{
		frmModificarTratamientoDos.setSize(400, 270);
		frmModificarTratamientoDos.setLayout(new FlowLayout());
		frmModificarTratamientoDos.add(lblIdModificacionTratamientoDos);
		txtIdModificacionTratamientoDos.setEditable(false);
		frmModificarTratamientoDos.add(txtIdModificacionTratamientoDos);
		frmModificarTratamientoDos.add(lblNombreModificacionTratamientoDos);
		frmModificarTratamientoDos.add(txtNombreModificacionTratamientoDos);
		frmModificarTratamientoDos.add(lblPrecioModificacionTratamientoDos);
		for (String d : precios) {
			listaPrecios.add((d));
		}
		listaPrecios.setBackground(Color.WHITE);
		frmModificarTratamientoDos.add(listaPrecios);
		frmModificarTratamientoDos.add(lblDescipcionModificacionTratamientoDos);
		frmModificarTratamientoDos.add(txaDescripcionModificacion);
		btnModificacionTratamientoDos.setBackground(Color.WHITE);
		frmModificarTratamientoDos.add(btnModificacionTratamientoDos);
		btnCancelarModificacionTratamientoDos.setBackground(Color.WHITE);
		frmModificarTratamientoDos.add(btnCancelarModificacionTratamientoDos);
		frmModificarTratamientoDos.setLocationRelativeTo(null);
		frmModificarTratamientoDos.setResizable(false);
		frmModificarTratamientoDos.setBackground(clFondo);
		frmModificarTratamientoDos.setVisible(true);
	}

	
}

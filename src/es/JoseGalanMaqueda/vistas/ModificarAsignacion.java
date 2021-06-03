package es.JoseGalanMaqueda.vistas;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;

import es.JoseGalanMaqueda.Controladores.ControladorModificarAsignaciones;
import es.JoseGalanMaqueda.modelo.ModeloCitas;
import es.JoseGalanMaqueda.modelo.ModeloTratamientos;
import es.JoseGalanMaqueda.modelo.ModeloTratamientosCitas;

public class ModificarAsignacion 
{
	// ========================= ELIMINAR CLIENTE PRINCIPAL ===============================================
	public Frame frmModificarAsignacionUno = new Frame("Modificar Asignacion");
	Color clFondo = new Color(204,229,255);
	Label lblSeleccionarModificarAsignacion = new Label("Selecciona asignacion a Modificar:");
	public Choice choListaAsignacion = new Choice();
	public Button btnModificar = new Button("Modificar");
	public Button btnCancelarModificar = new Button("Cancelar");
	Label lblBuscarAsignacion = new Label("Buscar Asignacion:");
	public TextField txtBuscarAsignacion = new TextField(12);
	public Button btnBuscarAsignacion = new Button("Buscar");


	// ========================= CONFIRMACION ELIMINAR ====================================================
	public Frame frmConfirmacionModificarAsignacion = new Frame("Confirmacion");
	public Label lblConfirmacionModificarAsignacion = new Label("Estas seguro que quieres eliminarla?");
	public Button btnSiConfirmacionModificarAsignacion = new Button("Si");
	public Button btnNoConfirmacionModificarAsignacion = new Button("No");

	// ============================== DIALOGO NOTIFICACION ==================================
	public Dialog dlgModificadoCorrecto = new Dialog(frmConfirmacionModificarAsignacion, "Operacion Correcta", true);
	public Label lblModificarCorrectamente = new Label("Asignacion Modificada Correctamente");

	// ============================== DIALOGO NOTIFICACION ==================================
	public Dialog dlgErrorModificar = new Dialog(frmConfirmacionModificarAsignacion, "Error", true);
	public Label lblErrorModificar = new Label("Error al Modificar Asignacion");

	// ============================== DIALOGO NOTIFICACION ==================================
	public Dialog dlgErrorModificarSeleccionarModificar = new Dialog(frmModificarAsignacionUno, "Error", true);
	public Label lblErrorModificarSeleccionarModificar = new Label("Selecciona una Asignacion válido");
	
	// ============================= MODIFICAR DOS ====================================================
	public Frame frmModificarAsignacionDos = new Frame("Modificar Asignacion");
	Label lblIdAsignacion = new Label("Id:");
	public TextField txtIdAsignacion = new TextField(5);
	Label lblSeleccionaCita = new Label("Selecciona una Cita:");
	public Choice choListaCitas = new Choice();
	Label lblSeleccionaTratamiento = new Label("Selecciona un tratamiento");
	public Choice choListaTratamiento = new Choice();
	public Button btnModificarAsignacionDos = new Button("Modificar");
	public Button btnCancelarModificarAsignacionDos = new Button("Cancelar");

	public ModificarAsignacion() 
	{
		frmModificarAsignacionUno.setLayout(new FlowLayout());
		frmModificarAsignacionUno.setSize(320, 170);
		frmModificarAsignacionUno.setBackground(clFondo);
		frmModificarAsignacionUno.add(lblBuscarAsignacion);
		frmModificarAsignacionUno.add(txtBuscarAsignacion);
		frmModificarAsignacionUno.add(btnBuscarAsignacion);
		frmModificarAsignacionUno.add(lblSeleccionarModificarAsignacion);
		choListaAsignacion.setBackground(Color.WHITE);
		frmModificarAsignacionUno.add(choListaAsignacion);
		btnModificar.setBackground(Color.WHITE);
		btnCancelarModificar.setBackground(Color.WHITE);
		frmModificarAsignacionUno.add(btnModificar);
		frmModificarAsignacionUno.add(btnCancelarModificar);
		frmModificarAsignacionUno.setResizable(false);
		frmModificarAsignacionUno.setLocationRelativeTo(null);
		frmModificarAsignacionUno.setVisible(true);
	}
	
	public void ventanaEdicionAsignacion() 
	{
		frmModificarAsignacionDos.setSize(300,300);
		frmModificarAsignacionDos.setBackground(clFondo);
		frmModificarAsignacionDos.setLayout(new FlowLayout());
		frmModificarAsignacionDos.add(lblIdAsignacion);
		txtIdAsignacion.setEditable(false);
		frmModificarAsignacionDos.add(txtIdAsignacion);
		frmModificarAsignacionDos.add(lblSeleccionaCita);
		choListaCitas.setBackground(Color.white);
		choListaTratamiento.setBackground(Color.white);
		frmModificarAsignacionDos.add(choListaCitas);
		frmModificarAsignacionDos.add(lblSeleccionaTratamiento);
		frmModificarAsignacionDos.add(choListaTratamiento);
		btnCancelarModificarAsignacionDos.setBackground(Color.white);
		btnModificarAsignacionDos.setBackground(Color.white);
		frmModificarAsignacionDos.add(btnModificarAsignacionDos);
		frmModificarAsignacionDos.add(btnCancelarModificarAsignacionDos);
		frmModificarAsignacionDos.setVisible(true);
	}
	

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

	public static void main(String[] args) {
		ModificarAsignacion vista = new  ModificarAsignacion();
		ModeloTratamientosCitas modelo = new ModeloTratamientosCitas();
		ModeloCitas modeloCitas = new ModeloCitas();
		ModeloTratamientos modeloTratamientos = new ModeloTratamientos();
		new ControladorModificarAsignaciones(vista, modelo, modeloCitas, modeloTratamientos);
	}


}

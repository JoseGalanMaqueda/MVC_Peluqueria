package es.JoseGalanMaqueda.vistas;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;

public class ModificarCita 
{
	// ========================= MODIFICAR CLIENTE PRINCIPAL ===============================================
	public Frame frmModificarCitaUno = new Frame("Modificar Cita");
	public Label lblSeleccionarModificarCita = new Label("Selecciona Cita a Modificar:");
	public Choice choListaCitas = new Choice();
	Label lblBuscarCita = new Label("Buscar Cita:");
	public TextField txtBuscarCita = new TextField(12);
	public Button btnBuscarCita = new Button("Buscar");
	public Button btnModificarUno = new Button("Modificar");
	public Button btnCancelarModificarUno = new Button("Cancelar");
	Color clFondo = new Color(204,229,255);


	// ========================= CONFIRMACION MODIFICAR CLIENTE ====================================================
	public Frame frmConfirmacionModificarCita = new Frame("Confirmacion");
	public Label lblConfirmacionModificarCita = new Label("¿Estás seguro que deseas Modificarla?");
	public Button btnSiConfirmacionModificarCita = new Button("Si");
	public Button btnNoConfirmacionModificarCita = new Button("No");

	// ============================== DIALOGO NOTIFICACION ==================================
	public Dialog dlgCitaModificado = new Dialog(frmConfirmacionModificarCita, "Operacion Correcta", true);
	public Label lblModificadoCorrectamente = new Label("Cita Modificado Correctamente");

	// ============================== DIALOGO NOTIFICACION ==================================
	public Dialog dlgErrorModificarCita = new Dialog(frmConfirmacionModificarCita, "Error", true);
	public Label lblErrorModificarCita = new Label("Error al Modificar Cita");

	// ============================== DIALOGO NOTIFICACION ==================================
	public Dialog dlgErrorModificarSeleccionarCita = new Dialog(frmModificarCitaUno, "Error", true);
	public Label lblErrorModificarSeleccionarCita = new Label("Selecciona una cita válido");
	
	// ================================= MODIFICAR CITA DOS ===================================
	public Frame frmModificarCitaDos = new Frame("Alta Citas");
	Label lblFechaCitas = new Label("Fecha:");
	Label lblHoraCitas = new Label("Hora:");
	Label lblSeleccionaCliente = new Label("Selecciona un Cliente:");
	Label lblIdCita = new Label("Id:");
	public Choice cholistaClientes = new Choice();
	public Choice cholistaHoras = new Choice();
	public TextField txtIdCita = new TextField(5);
	public TextField txtFecha = new TextField(15);
	public Button btnModificarDos = new Button("Modificar");
	public Button btnCancelarCitas = new Button("Cancelar");
	String[] horas = {"9:00","9:30","10:00", "10:30","11:00","11:30","12:00","12:30","13:00","13:30","17:00","17:30","18:00", "18:30","19:00","19:30","20:00"};

	public ModificarCita() 
	{
		frmModificarCitaUno.setLayout(new FlowLayout());
		frmModificarCitaUno.setSize(300, 170);
		frmModificarCitaUno.setBackground(clFondo);
		frmModificarCitaUno.add(lblBuscarCita);
		frmModificarCitaUno.add(txtBuscarCita);
		frmModificarCitaUno.add(btnBuscarCita);
		frmModificarCitaUno.add(lblSeleccionarModificarCita);
		choListaCitas.setBackground(Color.WHITE);
		frmModificarCitaUno.add(choListaCitas);
		btnModificarUno.setBackground(Color.WHITE);
		btnCancelarModificarUno.setBackground(Color.WHITE);
		frmModificarCitaUno.add(btnModificarUno);
		frmModificarCitaUno.add(btnCancelarModificarUno);
		frmModificarCitaUno.setResizable(false);
		frmModificarCitaUno.setLocationRelativeTo(null);
		frmModificarCitaUno.setVisible(true);
	}
	
	public void ventanaModificacion() {
		frmModificarCitaDos.setBackground(clFondo);
		frmModificarCitaDos.setSize(400, 170);
		frmModificarCitaDos.setLayout(new FlowLayout());
		frmModificarCitaDos.add(lblIdCita);
		txtIdCita.setEditable(false);
		frmModificarCitaDos.add(txtIdCita);
		frmModificarCitaDos.add(lblFechaCitas);
		frmModificarCitaDos.add(txtFecha);
		frmModificarCitaDos.add(lblHoraCitas);
		for (String s : horas) 
		{
			cholistaHoras.add(s);
		}
		cholistaHoras.setBackground(Color.WHITE);
		frmModificarCitaDos.add(cholistaHoras);
		frmModificarCitaDos.add(lblSeleccionaCliente);
		cholistaClientes.setBackground(Color.WHITE);
		frmModificarCitaDos.add(cholistaClientes);
		btnModificarDos.setBackground(Color.WHITE);
		frmModificarCitaDos.add(btnModificarDos);
		btnCancelarCitas.setBackground(Color.WHITE);
		frmModificarCitaDos.add(btnCancelarCitas);
		frmModificarCitaDos.setLocationRelativeTo(null);
		frmModificarCitaDos.setResizable(false);
		frmModificarCitaDos.setVisible(true);
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
	
}

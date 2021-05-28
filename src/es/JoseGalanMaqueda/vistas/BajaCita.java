package es.JoseGalanMaqueda.vistas;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;

public class BajaCita 
{

	// ========================= ELIMINAR CLIENTE PRINCIPAL ===============================================
	public Frame frmEliminarCita = new Frame("Eliminar Cita");
	Color clFondo = new Color(204,229,255);
	Label lblSeleccionarEliminarCita = new Label("Selecciona Cita a Eliminar:");
	public Choice choListaCita = new Choice();
	public Button btnEliminar = new Button("Eliminar");
	public Button btnCancelarEliminar = new Button("Cancelar");
	Label lblBuscarCita = new Label("Buscar Cita:");
	public TextField txtBuscarCita = new TextField(12);
	public Button btnBuscarCita = new Button("Buscar");

	// ========================= CONFIRMACION ELIMINAR ====================================================
	public Frame frmConfirmacionEliminarCita = new Frame("Confirmacion");
	public Label lblConfirmacionEliminarCita = new Label("Estas seguro que quieres eliminarla?");
	public Button btnSiConfirmacionElimiarCita = new Button("Si");
	public Button btnNoConfirmacionElimiarCita = new Button("No");

	// ============================== DIALOGO NOTIFICACION ==================================
	public Dialog dlgEliminadoCorrecto = new Dialog(frmConfirmacionEliminarCita, "Operacion Correcta", true);
	public Label lblEliminadoCorrectamente = new Label("Cita Eliminada Correctamente");

	// ============================== DIALOGO NOTIFICACION ==================================
	public Dialog dlgErrorEliminar = new Dialog(frmConfirmacionEliminarCita, "Error", true);
	public Label lblErrorEliminar = new Label("Error al Eliminar Cita");

	public BajaCita() 
	{
		frmEliminarCita.setLayout(new FlowLayout());
		frmEliminarCita.setSize(300, 170);
		frmEliminarCita.setBackground(clFondo);
		frmEliminarCita.add(lblBuscarCita);
		frmEliminarCita.add(txtBuscarCita);
		frmEliminarCita.add(btnBuscarCita);
		frmEliminarCita.add(lblSeleccionarEliminarCita);
		choListaCita.setBackground(Color.WHITE);
		frmEliminarCita.add(choListaCita);
		btnEliminar.setBackground(Color.WHITE);
		btnCancelarEliminar.setBackground(Color.WHITE);
		frmEliminarCita.add(btnEliminar);
		frmEliminarCita.add(btnCancelarEliminar);
		frmEliminarCita.setResizable(false);
		frmEliminarCita.setLocationRelativeTo(null);
		frmEliminarCita.setVisible(true);
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

}

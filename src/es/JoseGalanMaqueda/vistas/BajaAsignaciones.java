package es.JoseGalanMaqueda.vistas;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;

public class BajaAsignaciones 
{
	// ========================= ELIMINAR CLIENTE PRINCIPAL ===============================================
	public Frame frmEliminarAsignacion = new Frame("Eliminar Asignacion");
	Color clFondo = new Color(204,229,255);
	Label lblSeleccionarEliminarAsignacion = new Label("Selecciona asignacion a Eliminar:");
	public Choice choListaAsignacion = new Choice();
	public Button btnEliminar = new Button("Eliminar");
	public Button btnCancelarEliminar = new Button("Cancelar");
	Label lblBuscarAsignacion = new Label("Buscar Asignacion:");
	public TextField txtBuscarAsignacion = new TextField(12);
	public Button btnBuscarAsignacion = new Button("Buscar");


	// ========================= CONFIRMACION ELIMINAR ====================================================
	public Frame frmConfirmacionEliminarAsignacion = new Frame("Confirmacion");
	public Label lblConfirmacionEliminarAsignacion = new Label("Estas seguro que quieres eliminarla?");
	public Button btnSiConfirmacionElimiarAsignacion = new Button("Si");
	public Button btnNoConfirmacionElimiarAsignacion = new Button("No");

	// ============================== DIALOGO NOTIFICACION ==================================
	public Dialog dlgEliminadoCorrecto = new Dialog(frmConfirmacionEliminarAsignacion, "Operacion Correcta", true);
	public Label lblEliminadoCorrectamente = new Label("Asignacion Eliminada Correctamente");

	// ============================== DIALOGO NOTIFICACION ==================================
	public Dialog dlgErrorEliminar = new Dialog(frmConfirmacionEliminarAsignacion, "Error", true);
	public Label lblErrorEliminar = new Label("Error al Eliminar Asignacion");

	public BajaAsignaciones() 
	{
		frmEliminarAsignacion.setLayout(new FlowLayout());
		frmEliminarAsignacion.setSize(320, 170);
		frmEliminarAsignacion.setBackground(clFondo);
		frmEliminarAsignacion.add(lblBuscarAsignacion);
		frmEliminarAsignacion.add(txtBuscarAsignacion);
		frmEliminarAsignacion.add(btnBuscarAsignacion);
		frmEliminarAsignacion.add(lblSeleccionarEliminarAsignacion);
		choListaAsignacion.setBackground(Color.WHITE);
		frmEliminarAsignacion.add(choListaAsignacion);
		btnEliminar.setBackground(Color.WHITE);
		btnCancelarEliminar.setBackground(Color.WHITE);
		frmEliminarAsignacion.add(btnEliminar);
		frmEliminarAsignacion.add(btnCancelarEliminar);
		frmEliminarAsignacion.setResizable(false);
		frmEliminarAsignacion.setLocationRelativeTo(null);
		frmEliminarAsignacion.setVisible(true);
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

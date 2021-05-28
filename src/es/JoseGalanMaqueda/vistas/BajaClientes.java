package es.JoseGalanMaqueda.vistas;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;

public class BajaClientes 
{
	// ========================= ELIMINAR CLIENTE PRINCIPAL ===============================================
	public Frame frmEliminarCliente = new Frame("Eliminar Cliente");
	Color clFondo = new Color(204,229,255);
	Label lblSeleccionarEliminarCliente = new Label("Selecciona Cliente a Eliminar:");
	public Choice choListaCliente = new Choice();
	public Button btnEliminar = new Button("Eliminar");
	public Button btnCancelarEliminar = new Button("Cancelar");
	Label lblBuscarCliente = new Label("Buscar Cliente:");
	public TextField txtBuscarCliente = new TextField(12);
	public Button btnBuscarCliente = new Button("Buscar");

	// ========================= CONFIRMACION ELIMINAR ====================================================
	public Frame frmConfirmacionEliminarCliente = new Frame("Confirmacion");
	public Label lblConfirmacionEliminarCliente = new Label("�Est�s seguro que deseas eliminarlo?");
	public Button btnSiConfirmacionElimiarCliente = new Button("Si");
	public Button btnNoConfirmacionElimiarCliente = new Button("No");

	// ============================== DIALOGO NOTIFICACION ==================================
	public Dialog dlgEliminadoCorrecto = new Dialog(frmConfirmacionEliminarCliente, "Operacion Correcta", true);
	public Label lblEliminadoCorrectamente = new Label("Cliente Eliminado Correctamente");

	// ============================== DIALOGO NOTIFICACION ==================================
	public Dialog dlgErrorEliminar = new Dialog(frmConfirmacionEliminarCliente, "Error", true);
	public Label lblErrorEliminar = new Label("Error al Eliminar Cliente");
	
	public BajaClientes() 
	{
		frmEliminarCliente.setLayout(new FlowLayout());
		frmEliminarCliente.setSize(300, 170);
		frmEliminarCliente.setBackground(clFondo);
		frmEliminarCliente.add(lblBuscarCliente);
		frmEliminarCliente.add(txtBuscarCliente);
		frmEliminarCliente.add(btnBuscarCliente);
		frmEliminarCliente.add(lblSeleccionarEliminarCliente);
		choListaCliente.setBackground(Color.WHITE);
		frmEliminarCliente.add(choListaCliente);
		btnEliminar.setBackground(Color.WHITE);
		btnCancelarEliminar.setBackground(Color.WHITE);
		frmEliminarCliente.add(btnEliminar);
		frmEliminarCliente.add(btnCancelarEliminar);
		frmEliminarCliente.setResizable(false);
		frmEliminarCliente.setLocationRelativeTo(null);
		frmEliminarCliente.setVisible(true);
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

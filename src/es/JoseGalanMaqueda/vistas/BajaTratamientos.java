package es.JoseGalanMaqueda.vistas;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;

public class BajaTratamientos {

	// ========================= ELIMINAR TRATAMIENTO PRINCIPAL ===============================================
	public Frame frmEliminarTratamiento = new Frame("Eliminar Tratamiento");
	Color clFondo = new Color(204,229,255);
	Label lblSeleccionarEliminarTratamiento = new Label("Selecciona Tratamiento a Eliminar:");
	public Choice choListaTratamientos = new Choice();
	public Button btnEliminar = new Button("Eliminar");
	public Button btnCancelarEliminar = new Button("Cancelar");
	Label lblBuscarTratamiento = new Label("Buscar:");
	public TextField txtBuscarTratamiento = new TextField(12);
	public Button btnBuscarCliente = new Button("Buscar");

	// ========================= CONFIRMACION ELIMINAR ====================================================
	public Frame frmConfirmacionEliminarTratamiento = new Frame("Confirmacion");
	public Label lblConfirmacionEliminarTratamiento = new Label("¿Estás seguro que deseas eliminarlo?");
	public Button btnSiConfirmacionElimiarTratamiento = new Button("Si");
	public Button btnNoConfirmacionElimiarTratamiento = new Button("No");

	// ============================== DIALOGO NOTIFICACION ==================================
	public Dialog dlgTratamientoInsertado = new Dialog(frmConfirmacionEliminarTratamiento, "Operacion Correcta", true);
	public Label lblAnadidaCorrectamente = new Label("Tratamiento Eliminado Correctamente");

	// ============================== DIALOGO NOTIFICACION ==================================
	public Dialog dlgErrorInsertarTratamiento = new Dialog(frmConfirmacionEliminarTratamiento, "Error", true);
	public Label lblErrorAnadidoTratamiento = new Label("Error al Eliminar Tratamiento");

	public BajaTratamientos() 
	{
		frmEliminarTratamiento.setLayout(new FlowLayout());
		frmEliminarTratamiento.setSize(270, 170);
		frmEliminarTratamiento.setBackground(clFondo);
		frmEliminarTratamiento.add(lblBuscarTratamiento);
		frmEliminarTratamiento.add(txtBuscarTratamiento);
		btnBuscarCliente.setBackground(Color.white);
		frmEliminarTratamiento.add(btnBuscarCliente);
		frmEliminarTratamiento.add(lblSeleccionarEliminarTratamiento);
		choListaTratamientos.setBackground(Color.WHITE);
		frmEliminarTratamiento.add(choListaTratamientos);
		btnEliminar.setBackground(Color.WHITE);
		btnCancelarEliminar.setBackground(Color.WHITE);
		frmEliminarTratamiento.add(btnEliminar);
		frmEliminarTratamiento.add(btnCancelarEliminar);
		frmEliminarTratamiento.setResizable(false);
		frmEliminarTratamiento.setLocationRelativeTo(null);
		frmEliminarTratamiento.setVisible(true);
	}

	// ======================================= CREAR DIALOGO NOTIFICACION ==================================================================
	public void creacionDialogoNotificacion(Dialog dialogo, Label lbl) {
		dialogo.setSize(250, 100);
		dialogo.setLayout(new FlowLayout());
		dialogo.setBackground(clFondo);
		dialogo.add(lbl);
		dialogo.setLocationRelativeTo(null);
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
}

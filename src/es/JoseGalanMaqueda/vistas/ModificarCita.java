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

	public static void main(String[] args) {
		new ModificarCita();
	}
}

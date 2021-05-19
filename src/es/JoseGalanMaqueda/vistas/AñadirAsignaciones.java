package es.JoseGalanMaqueda.vistas;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;

public class AñadirAsignaciones 
{
	
	public Frame frmAsignaciones = new Frame("Alta Asignación Tratamiento");
	public Label lblCitade = new Label();
	Label lblSeleccionaTratamiento = new Label("Selecciona Tratamientos: ");
	public Button btnAlta = new Button("Alta");
	public Button btnCancelar = new Button("Cancelar");
	Label lblTratamientos = new Label("Tratamientos en la cita");
	public TextArea txaListadoTratamientos = new TextArea(6,33);
	public Choice choListaTratamientos = new Choice();
	Color clFondo = new Color(204,229,255);
	public String datoCita;
	String clienteSeleccionado = "";
	
	// ============================== DIALOGO NOTIFICACION ==================================
	public Dialog dlgTratamientoInsertado = new Dialog(frmAsignaciones, "Operacion Correcta", true);
	public Label lblAnadidaCorrectamente = new Label("Tratamiento Insertado Correctamente");

	// ============================== DIALOGO NOTIFICACION ==================================
	public Dialog dlgErrorInsertarTratamiento = new Dialog(frmAsignaciones, "Error", true);
	public Label lblErrorAnadidoTratamiento = new Label("Error al añadir Tratamiento");
	
	public AñadirAsignaciones() {
		frmAsignaciones.setLayout(new FlowLayout());
		frmAsignaciones.add(lblCitade);
		frmAsignaciones.add(lblSeleccionaTratamiento);
		choListaTratamientos.setBackground(Color.white);
		frmAsignaciones.add(choListaTratamientos);
		btnAlta.setBackground(Color.WHITE);
		btnCancelar.setBackground(Color.WHITE);
		frmAsignaciones.add(btnAlta);
		frmAsignaciones.add(lblTratamientos);
		txaListadoTratamientos.setEditable(false);
		txaListadoTratamientos.setBackground(Color.WHITE);
		frmAsignaciones.add(txaListadoTratamientos);
		frmAsignaciones.add(btnCancelar);
		frmAsignaciones.setBackground(clFondo);
		frmAsignaciones.setSize(280, 300);
		frmAsignaciones.setResizable(false);
		frmAsignaciones.setLocationRelativeTo(null);
		frmAsignaciones.setVisible(true);
	}
	
	public void creacionDialogoNotificacion(Dialog dialogo, Label lbl) {
		dialogo.setSize(260, 100);
		dialogo.setLayout(new FlowLayout());
		dialogo.setBackground(clFondo);
		dialogo.add(lbl);
		dialogo.setLocationRelativeTo(null);
	}

}

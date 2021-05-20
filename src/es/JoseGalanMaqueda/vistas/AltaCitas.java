package es.JoseGalanMaqueda.vistas;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.time.LocalDate;

public class AltaCitas 
{

	public Frame frmAltaCitas = new Frame("Alta Citas");
	Label lblFechaCitas = new Label("Fecha:");
	Label lblHoraCitas = new Label("Hora:");
	Label lblSeleccionaCliente = new Label("Selecciona un Cliente:");
	public Choice cholistaClientes = new Choice();
	public Choice cholistaHoras = new Choice();
	public TextField txtFecha = new TextField(""+obtenerFechaHoy()+"", 20);
	public Button btnAltaCitas = new Button("Alta Cita");
	public Button btnCancelarCitas = new Button("Cancelar");
	Color clFondo = new Color(204,229,255);
	String[] horas = {"9:00","9:30","10:00", "10:30","11:00","11:30","12:00","12:30","13:00","13:30","17:00","17:30","18:00", "18:30","19:00","19:30","20:00"};
	String idCita;

	// ============================== DIALOGO NOTIFICACION ==================================
	public Dialog dlgCitasInsertado = new Dialog(frmAltaCitas, "Operacion Correcta", true);
	public Label lblAnadidaCorrectamente = new Label("Cita añadida correctamente");

	// ============================== DIALOGO NOTIFICACION ==================================
	public Dialog dlgErrorInsertarCita = new Dialog(frmAltaCitas, "Error", true);
	public Label lblErrorAnadidoCita = new Label("Error al insertar");
	
	public AltaCitas() 
	{
		frmAltaCitas.setBackground(clFondo);
		frmAltaCitas.setSize(400, 170);
		frmAltaCitas.setLayout(new FlowLayout());
		frmAltaCitas.add(lblFechaCitas);
		frmAltaCitas.add(txtFecha);
		frmAltaCitas.add(lblHoraCitas);
		for (String s : horas) 
		{
			cholistaHoras.add(s);
		}
		cholistaHoras.setBackground(Color.WHITE);
		frmAltaCitas.add(cholistaHoras);
		frmAltaCitas.add(lblSeleccionaCliente);
		cholistaClientes.setBackground(Color.WHITE);
		frmAltaCitas.add(cholistaClientes);
		btnAltaCitas.setBackground(Color.WHITE);
		frmAltaCitas.add(btnAltaCitas);
		btnCancelarCitas.setBackground(Color.WHITE);
		frmAltaCitas.add(btnCancelarCitas);
		frmAltaCitas.setLocationRelativeTo(null);
		frmAltaCitas.setResizable(false);
		frmAltaCitas.setVisible(true);
	}
	
	public String obtenerFechaHoy() 
	{
		LocalDate fechaAhora = LocalDate.now();
		String fecha = fechaAhora + "";
		return (fecha.split("-")[2] +"/"+ fecha.split("-")[1] +"/"+ fecha.split("-")[0]);
	}
	
	// ============================================= DIALOGO NOTIFICACION ==========================================================
	public void creacionDialogoNotificacion(Dialog dialogo, Label lbl) 
	{
		dialogo.setSize(230, 100);
		dialogo.setLayout(new FlowLayout());
		dialogo.setBackground(clFondo);
		dialogo.add(lbl);
		dialogo.setLocationRelativeTo(null);
	}

	
	
}

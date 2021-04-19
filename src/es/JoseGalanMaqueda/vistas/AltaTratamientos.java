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

public class AltaTratamientos
{
	// ===================================== VENTANA ALTA CLIENTES =======================================================
	public Frame frmAltaTratamientos = new Frame("Alta Tratamientos");
	Label lblNombreAltaTratamiento = new Label("Nombre:");
	Label lblPrecioAltaTratamiento = new Label("Precio:");
	Label lblDescipcionAltaTratamiento = new Label("Descripción:");
	public TextField txtNombreAltaTratamiento = new TextField(20);
	Color clFondo = new Color(204,229,255);
	String[] precios = {"0.00","1.00","1.50","2.00","2.50","3.00","3.50","4.00","4.50","5.00","5.50","6.00","6.50","7.00","7.50","8.00","8.50","9.00","9.50"};
	public Choice listaPrecios = new Choice(); 
	public TextArea txaDescripcion = new TextArea(7, 50);
	public Button btnAltaTratamiento = new Button("Alta");
	public Button btnCancelarAltaTratamiento = new Button("Cancelar");

	// ============================== DIALOGO NOTIFICACION ==================================
	public Dialog dlgTratamientoInsertado = new Dialog(frmAltaTratamientos, "Operacion Correcta", true);
	public Label lblAnadidoCorrectamente = new Label("Tratamiento añadido correctamente");

	// ============================== DIALOGO NOTIFICACION ==================================
	public Dialog dlgErrorInsertarTratamiento = new Dialog(frmAltaTratamientos, "Error", true);
	public Label lblErrorAnadidoTratamiento = new Label("Faltan Datos");

	public AltaTratamientos() 
	{
		frmAltaTratamientos.setSize(400, 270);
		frmAltaTratamientos.setLayout(new FlowLayout());
		frmAltaTratamientos.add(lblNombreAltaTratamiento);
		frmAltaTratamientos.add(txtNombreAltaTratamiento);
		frmAltaTratamientos.add(lblPrecioAltaTratamiento);
		for (String d : precios) {
			listaPrecios.add(d);
		}
		listaPrecios.setBackground(Color.WHITE);
		frmAltaTratamientos.add(listaPrecios);
		frmAltaTratamientos.add(lblDescipcionAltaTratamiento);
		frmAltaTratamientos.add(txaDescripcion);
		btnAltaTratamiento.setBackground(Color.WHITE);
		frmAltaTratamientos.add(btnAltaTratamiento);
		btnCancelarAltaTratamiento.setBackground(Color.WHITE);
		frmAltaTratamientos.add(btnCancelarAltaTratamiento);
		frmAltaTratamientos.setLocationRelativeTo(null);
		frmAltaTratamientos.setResizable(false);
		frmAltaTratamientos.setBackground(clFondo);
		frmAltaTratamientos.setVisible(true);
	}

	public void creacionDialogoNotificacion(Dialog dialogo, Label lbl) {
		dialogo.setSize(250, 100);
		dialogo.setLayout(new FlowLayout());
		dialogo.setBackground(clFondo);
		dialogo.add(lbl);
		dialogo.setLocationRelativeTo(null);
	}
}

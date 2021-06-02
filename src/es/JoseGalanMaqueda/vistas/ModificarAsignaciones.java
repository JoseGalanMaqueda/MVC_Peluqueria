package es.JoseGalanMaqueda.vistas;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;

public class ModificarAsignaciones 
{

	// ========================= MODIFICAR CLIENTE PRINCIPAL ===============================================
		public Frame frmModificarAsignacionUno = new Frame("Modificar Asignacion");
		public Label lblSeleccionarModificarAsignacion = new Label("Selecciona Asignacion a Modificar:");
		public Choice choListaAsignaciones = new Choice();
		Label lblBuscarAsignacion = new Label("Buscar Asignacion:");
		public TextField txtBuscarAsignacion = new TextField(12);
		public Button btnBuscarAsignacion = new Button("Buscar");
		public Button btnModificarUno = new Button("Modificar");
		public Button btnCancelarModificarUno = new Button("Cancelar");
		Color clFondo = new Color(204,229,255);


		// ========================= CONFIRMACION MODIFICAR CLIENTE ====================================================
		public Frame frmConfirmacionModificarAsignacion = new Frame("Confirmacion");
		public Label lblConfirmacionModificarAsignacion = new Label("¿Estás seguro que deseas Modificarla?");
		public Button btnSiConfirmacionModificarAsignacion = new Button("Si");
		public Button btnNoConfirmacionModificarAsignacion = new Button("No");

		// ============================== DIALOGO NOTIFICACION ==================================
		public Dialog dlgAsignacionModificado = new Dialog(frmConfirmacionModificarAsignacion, "Operacion Correcta", true);
		public Label lblModificadoCorrectamente = new Label("Asignacion Modificado Correctamente");

		// ============================== DIALOGO NOTIFICACION ==================================
		public Dialog dlgErrorModificarAsignacion = new Dialog(frmConfirmacionModificarAsignacion, "Error", true);
		public Label lblErrorModificarAsignacion = new Label("Error al Modificar Asignacion");

		// ============================== DIALOGO NOTIFICACION ==================================
		public Dialog dlgErrorModificarSeleccionarAsignacion = new Dialog(frmModificarAsignacionUno, "Error", true);
		public Label lblErrorModificarSeleccionarAsignacion = new Label("Selecciona una asignacion válido");

		public ModificarAsignaciones() 
		{
			frmModificarAsignacionUno.setLayout(new FlowLayout());
			frmModificarAsignacionUno.setSize(320, 170);
			frmModificarAsignacionUno.setBackground(clFondo);
			frmModificarAsignacionUno.add(lblBuscarAsignacion);
			frmModificarAsignacionUno.add(txtBuscarAsignacion);
			frmModificarAsignacionUno.add(btnBuscarAsignacion);
			frmModificarAsignacionUno.add(lblSeleccionarModificarAsignacion);
			choListaAsignaciones.setBackground(Color.WHITE);
			frmModificarAsignacionUno.add(choListaAsignaciones);
			btnModificarUno.setBackground(Color.WHITE);
			btnCancelarModificarUno.setBackground(Color.WHITE);
			frmModificarAsignacionUno.add(btnModificarUno);
			frmModificarAsignacionUno.add(btnCancelarModificarUno);
			frmModificarAsignacionUno.setResizable(false);
			frmModificarAsignacionUno.setLocationRelativeTo(null);
			frmModificarAsignacionUno.setVisible(true);
		}

	public static void main(String[] args) 
	{
		new ModificarAsignaciones();
	}

}

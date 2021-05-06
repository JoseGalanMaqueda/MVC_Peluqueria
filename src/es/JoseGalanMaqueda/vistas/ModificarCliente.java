package es.JoseGalanMaqueda.vistas;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

public class ModificarCliente {

	// ========================= MODIFICAR CLIENTE PRINCIPAL ===============================================
	public Frame frmModificarClienteUno = new Frame("Modificar Cliente");
	public Label lblSeleccionarModificarCliente = new Label("Selecciona Cliente a Modificar:");
	public Choice choListaCliente = new Choice();
	Label lblBuscarCliente = new Label("Buscar Cliente:");
	public TextField txtBuscarCliente = new TextField(12);
	public Button btnBuscarCliente = new Button("Buscar");
	public Button btnModificarUno = new Button("Modificar");
	public Button btnCancelarModificarUno = new Button("Cancelar");
	Color clFondo = new Color(204,229,255);

	// ================== VENTANA MODIFICACION CLIENTES =================================================
	public Frame frmModificacionClientesDos = new Frame("Formulario de Modificacion de Clientes");
	Label lblIdModificacionClienteDos = new Label("Id:");
	Label lblNombreModificacionClienteDos = new Label("Nombre:");
	Label lblApellidosModificacionClientesDos = new Label("Apellidos:");
	Label lblDniModificacionClientesDos = new Label("DNI:");
	Label lblDireccionModificacionClientesDos = new Label("Dirección:");
	Label lblSexoModificacionClientesDos = new Label("Sexo:");
	public TextField txtIdModificacionClientesDos = new TextField(20);
	public TextField txtNombreModificacionClientesDos = new TextField(20);
	public TextField txtApellidosModificacionClientesDos = new TextField(20);
	public TextField txtDniModificacionClientesDos = new TextField(20);
	public TextField txtDireccionModificacionClientesDos = new TextField(20);
	CheckboxGroup chkGeneroDos = new CheckboxGroup();
	public Checkbox chkHombreDos = new Checkbox("Hombre",false, chkGeneroDos);
	public Checkbox chkMujerDos = new Checkbox("Mujer", false, chkGeneroDos);
	public Button btnModificacionClientesDos = new Button("Modificar");
	public Button btnCancelarModificacionClientesDos = new Button("Cancelar");
	Panel pnlUno = new Panel();
	Panel pnlDos = new Panel();
	Panel pnlTres = new Panel();
	Panel pnlCuatro = new Panel();
	Panel pnlCinco = new Panel();
	Panel pnlSeis = new Panel();
	Panel pnlSiete = new Panel();
	Panel pnlOcho = new Panel();
	Panel pnlNueve = new Panel();
	Panel pnlDiez = new Panel();
	Panel pnlOnce = new Panel();
	Panel pnlDoce = new Panel();
	Panel pnlTrece = new Panel();
	Panel pnlCatorce = new Panel();

	public ModificarCliente()
	{
		frmModificarClienteUno.setLayout(new FlowLayout());
		frmModificarClienteUno.setSize(300, 170);
		frmModificarClienteUno.setBackground(clFondo);
		frmModificarClienteUno.add(lblBuscarCliente);
		frmModificarClienteUno.add(txtBuscarCliente);
		frmModificarClienteUno.add(btnBuscarCliente);
		frmModificarClienteUno.add(lblSeleccionarModificarCliente);
		choListaCliente.setBackground(Color.WHITE);
		frmModificarClienteUno.add(choListaCliente);
		btnModificarUno.setBackground(Color.WHITE);
		btnCancelarModificarUno.setBackground(Color.WHITE);
		frmModificarClienteUno.add(btnModificarUno);
		frmModificarClienteUno.add(btnCancelarModificarUno);
		frmModificarClienteUno.setResizable(false);
		frmModificarClienteUno.setLocationRelativeTo(null);
		frmModificarClienteUno.setVisible(true);
	}

	// ========================= CONFIRMACION MODIFICAR CLIENTE ====================================================
	public Frame frmConfirmacionModificarCliente = new Frame("Confirmacion");
	public Label lblConfirmacionModificarCliente = new Label("¿Estás seguro que deseas Modificarlo?");
	public Button btnSiConfirmacionModificarCliente = new Button("Si");
	public Button btnNoConfirmacionModificarCliente = new Button("No");

	// ============================== DIALOGO NOTIFICACION ==================================
	public Dialog dlgClientesModificado = new Dialog(frmConfirmacionModificarCliente, "Operacion Correcta", true);
	public Label lblModificadoCorrectamente = new Label("Cliente Modificado Correctamente");

	// ============================== DIALOGO NOTIFICACION ==================================
	public Dialog dlgErrorModificarCliente = new Dialog(frmConfirmacionModificarCliente, "Error", true);
	public Label lblErrorModificarCliente = new Label("Error al Modificar Cliente");

	// ============================== DIALOGO NOTIFICACION ==================================
	public Dialog dlgErrorModificarSeleccionarCliente = new Dialog(frmModificarClienteUno, "Error", true);
	public Label lblErrorModificarSeleccionarCliente = new Label("Selecciona un cliente válido");

	// =================================== CARGAR VENTANA EDICICION DATOS CLIENTE====================================
	public void cargarVentanaEdicionClientes() {
		frmModificacionClientesDos.setSize(400, 300);
		frmModificacionClientesDos.setLayout(new GridLayout(7,2));
		pnlTrece.add(lblIdModificacionClienteDos);
		txtIdModificacionClientesDos.setEditable(false);
		pnlCatorce.add(txtIdModificacionClientesDos);
		pnlUno.add(lblNombreModificacionClienteDos);
		pnlDos.add(txtNombreModificacionClientesDos);
		pnlTres.add(lblApellidosModificacionClientesDos);
		pnlCuatro.add(txtApellidosModificacionClientesDos);
		pnlCinco.add(lblDniModificacionClientesDos);
		pnlSeis.add(txtDniModificacionClientesDos);
		pnlSiete.add(lblDireccionModificacionClientesDos);
		pnlOcho.add(txtDireccionModificacionClientesDos);
		pnlNueve.add(lblSexoModificacionClientesDos);
		pnlDiez.add(chkHombreDos);
		pnlDiez.add(chkMujerDos);
		btnModificacionClientesDos.setBackground(Color.WHITE);
		pnlOnce.add(btnModificacionClientesDos);
		btnCancelarModificacionClientesDos.setBackground(Color.WHITE);
		pnlDoce.add(btnCancelarModificacionClientesDos);
		frmModificacionClientesDos.add(pnlTrece);
		frmModificacionClientesDos.add(pnlCatorce);
		frmModificacionClientesDos.add(pnlUno);
		frmModificacionClientesDos.add(pnlDos);
		frmModificacionClientesDos.add(pnlTres);
		frmModificacionClientesDos.add(pnlCuatro);
		frmModificacionClientesDos.add(pnlCinco);
		frmModificacionClientesDos.add(pnlSeis);
		frmModificacionClientesDos.add(pnlSiete);
		frmModificacionClientesDos.add(pnlOcho);
		frmModificacionClientesDos.add(pnlNueve);
		frmModificacionClientesDos.add(pnlDiez);
		frmModificacionClientesDos.add(pnlOnce);
		frmModificacionClientesDos.add(pnlDoce);
		frmModificacionClientesDos.setBackground(clFondo);
		frmModificacionClientesDos.setResizable(false);
		frmModificacionClientesDos.setLocationRelativeTo(null);
		frmModificacionClientesDos.setVisible(true);
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
	public void creacionDialogoNotificacion(Dialog dialogo, Label lbl) {
		dialogo.setSize(250, 100);
		dialogo.setLayout(new FlowLayout());
		dialogo.setBackground(clFondo);
		dialogo.add(lbl);
		dialogo.setLocationRelativeTo(null);
	}

}

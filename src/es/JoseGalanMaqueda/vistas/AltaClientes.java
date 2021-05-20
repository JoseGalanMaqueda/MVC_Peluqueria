package es.JoseGalanMaqueda.vistas;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

public class AltaClientes
{
	public Frame frmAltaClientes = new Frame("Formulario de Alta de Clientes");
	Label lblNombreAltaCliente = new Label("Nombre:");
	Label lblApellidosAltaClientes = new Label("Apellidos:");
	Label lblDniAltaClientes = new Label("DNI:");
	Label lblDireccionAltaClientes = new Label("Direcci�n:");
	Label lblSexoAltaClientes = new Label("Sexo:");
	public TextField txtNombreAltaClientes = new TextField(20);
	public TextField txtApellidosAltaClientes = new TextField(20);
	public TextField txtDniAltaClientes = new TextField(20);
	public TextField txtDireccionAltaClientes = new TextField(20);
	CheckboxGroup chkGenero = new CheckboxGroup();
	public Checkbox chkHombre = new Checkbox("Hombre",false, chkGenero);
	public Checkbox chkMujer = new Checkbox("Mujer", false, chkGenero);
	public Button btnAltaClientes = new Button("Alta");
	public Button btnCancelarAltaClientes = new Button("Cancelar");
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
	Color clFondo = new Color(204,229,255);

	// ============================== DIALOGO NOTIFICACION ==================================
	public Dialog dlgClienteInsertado = new Dialog(frmAltaClientes, "Operacion Correcta", true);
	public Label lblAnadidoCorrectamente = new Label("Cliente a�adido correctamente");

	// ============================== DIALOGO NOTIFICACION ==================================
	public Dialog dlgErrorInsertarCliente = new Dialog(frmAltaClientes, "Error", true);
	public Label lblErrorAnadidoCliente = new Label("Faltan Datos");

	public AltaClientes() 
	{
		frmAltaClientes.setSize(400, 300);
		frmAltaClientes.setLayout(new GridLayout(6,2));
		pnlUno.add(lblNombreAltaCliente);
		pnlDos.add(txtNombreAltaClientes);
		pnlTres.add(lblApellidosAltaClientes);
		pnlCuatro.add(txtApellidosAltaClientes);
		pnlCinco.add(lblDniAltaClientes);
		pnlSeis.add(txtDniAltaClientes);
		pnlSiete.add(lblDireccionAltaClientes);
		pnlOcho.add(txtDireccionAltaClientes);
		pnlNueve.add(lblSexoAltaClientes);
		pnlDiez.add(chkHombre);
		pnlDiez.add(chkMujer);
		btnAltaClientes.setBackground(Color.WHITE);
		pnlOnce.add(btnAltaClientes);
		btnCancelarAltaClientes.setBackground(Color.WHITE);
		pnlDoce.add(btnCancelarAltaClientes);
		frmAltaClientes.add(pnlUno);
		frmAltaClientes.add(pnlDos);
		frmAltaClientes.add(pnlTres);
		frmAltaClientes.add(pnlCuatro);
		frmAltaClientes.add(pnlCinco);
		frmAltaClientes.add(pnlSeis);
		frmAltaClientes.add(pnlSiete);
		frmAltaClientes.add(pnlOcho);
		frmAltaClientes.add(pnlNueve);
		frmAltaClientes.add(pnlDiez);
		frmAltaClientes.add(pnlOnce);
		frmAltaClientes.add(pnlDoce);
		frmAltaClientes.setBackground(clFondo);
		frmAltaClientes.setResizable(false);
		frmAltaClientes.setLocationRelativeTo(null);
		frmAltaClientes.setVisible(true);
	}

	public void creacionDialogoNotificacion(Dialog dialogo, Label lbl) 
	{
		dialogo.setSize(230, 100);
		dialogo.setLayout(new FlowLayout());
		dialogo.setBackground(clFondo);
		dialogo.add(lbl);
		dialogo.setLocationRelativeTo(null);
	}

}

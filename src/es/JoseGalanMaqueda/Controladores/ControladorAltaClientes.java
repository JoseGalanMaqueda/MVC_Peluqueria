package es.JoseGalanMaqueda.Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import es.JoseGalanMaqueda.modelo.ModeloCliente;
import es.JoseGalanMaqueda.vistas.AltaClientes;

public class ControladorAltaClientes implements WindowListener, ActionListener, ItemListener
{
	AltaClientes altaCliente;
	ModeloCliente modeloCliente;
	String eleccion;
	
	// =============================== CONSTRUCTOR VISTA ALTA CLIENTES Y MODELO CLIENTES ===================================
	public ControladorAltaClientes(AltaClientes altaCliente, ModeloCliente modeloCliente) {
		this.altaCliente = altaCliente;
		this.modeloCliente = modeloCliente;
		
		this.altaCliente.frmAltaClientes.addWindowListener(this);
		this.altaCliente.chkHombre.addItemListener(this);
		this.altaCliente.chkMujer.addItemListener(this);
		this.altaCliente.btnAltaClientes.addActionListener(this);
		this.altaCliente.btnCancelarAltaClientes.addActionListener(this);
		
		this.altaCliente.dlgClienteInsertado.addWindowListener(this);
		this.altaCliente.dlgErrorInsertarCliente.addWindowListener(this);
		
	}
	
	//=============================== WINDOWS LISTENER  ===================================
	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {
		
		if (altaCliente.frmAltaClientes.isActive()) // Ventana Alta Clientes 
		{
			altaCliente.frmAltaClientes.setVisible(false);
		}else if (altaCliente.dlgErrorInsertarCliente.isActive()) { // Dialogo Error Alta Clientes
			altaCliente.dlgErrorInsertarCliente.setVisible(false);
		}else if (altaCliente.dlgClienteInsertado.isActive()) // Dialogo Confirmacion Alta Clientes
		{
			altaCliente.dlgClienteInsertado.setVisible(false);
			altaCliente.frmAltaClientes.setVisible(false);
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}

	// =============================== ITEM LISTENER ===================================
	@Override
	public void itemStateChanged(ItemEvent e) {
		if ("Hombre".equals(e.getItem()))
		{
			if (e.getStateChange() == ItemEvent.SELECTED)
			{
				eleccion="Hombre";
			}
		}
		else if ("Mujer".equals(e.getItem())) 
		{
			if (e.getStateChange() == ItemEvent.SELECTED)
			{
				eleccion="Mujer";
			}
		}
	}

	// =============================== ACTION LISTENER ===================================
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if (arg0.getSource().equals(altaCliente.btnAltaClientes)) // BOTON ALTA CLIENTES
		{
			altaCliente.creacionDialogoNotificacion(altaCliente.dlgErrorInsertarCliente, altaCliente.lblErrorAnadidoCliente);
			if (modeloCliente.comprobacionDatosIntroducidos(altaCliente.txtNombreAltaClientes,
					altaCliente.txtApellidosAltaClientes, altaCliente.txtDniAltaClientes, altaCliente.txtDireccionAltaClientes, eleccion))
			{
				if (modeloCliente.insertarDatosClientes(altaCliente.txtNombreAltaClientes, altaCliente.txtApellidosAltaClientes, altaCliente.txtDniAltaClientes, altaCliente.txtDireccionAltaClientes, eleccion))
				{
					altaCliente.creacionDialogoNotificacion(altaCliente.dlgClienteInsertado, altaCliente.lblAnadidoCorrectamente);
					altaCliente.dlgClienteInsertado.setVisible(true);
				}else {
					altaCliente.lblErrorAnadidoCliente.setText("Error al Insertar");
					altaCliente.dlgErrorInsertarCliente.setVisible(true);
				}
			}else {
				altaCliente.dlgErrorInsertarCliente.setVisible(true);
			}
		}else if (arg0.getSource().equals(altaCliente.btnCancelarAltaClientes)) // BOTON CANCELAR ALTA CLIENTES
		{
			altaCliente.frmAltaClientes.setVisible(false);
		}
	}
	
	
}

package es.JoseGalanMaqueda.Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import es.JoseGalanMaqueda.modelo.ModeloCliente;
import es.JoseGalanMaqueda.vistas.AltaClientes;

public class ControladorClientes implements WindowListener, ActionListener, ItemListener
{
	AltaClientes altaCliente;
	ModeloCliente modeloCliente;
	String eleccion;
	
	public ControladorClientes(AltaClientes altaCliente, ModeloCliente modeloCliente) {
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
	
	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {
		
		if (this.altaCliente.frmAltaClientes.isActive()) 
		{
			this.altaCliente.frmAltaClientes.setVisible(false);
		}else if (this.altaCliente.dlgErrorInsertarCliente.isActive()) {
			this.altaCliente.dlgErrorInsertarCliente.setVisible(false);
		}else if (this.altaCliente.dlgClienteInsertado.isActive())
		{
			this.altaCliente.dlgClienteInsertado.setVisible(false);
			this.altaCliente.frmAltaClientes.setVisible(false);
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

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if (arg0.getSource().equals(this.altaCliente.btnAltaClientes))
		{
			this.altaCliente.creacionDialogoNotificacion(this.altaCliente.dlgErrorInsertarCliente, this.altaCliente.lblErrorAnadidoCliente);
			if (this.modeloCliente.comprobacionDatosIntroducidos(this.altaCliente.txtNombreAltaClientes,
					this.altaCliente.txtApellidosAltaClientes, this.altaCliente.txtDniAltaClientes, this.altaCliente.txtDireccionAltaClientes, eleccion))
			{
				if (this.modeloCliente.insertarDatosClientes(this.altaCliente.txtNombreAltaClientes, this.altaCliente.txtApellidosAltaClientes, this.altaCliente.txtDniAltaClientes, this.altaCliente.txtDireccionAltaClientes, eleccion))
				{
					this.altaCliente.creacionDialogoNotificacion(this.altaCliente.dlgClienteInsertado, this.altaCliente.lblAnadidoCorrectamente);
					this.altaCliente.dlgClienteInsertado.setVisible(true);
				}else {
					this.altaCliente.lblErrorAnadidoCliente.setText("Error al Insertar");
					this.altaCliente.dlgErrorInsertarCliente.setVisible(true);
				}
			}else {
				this.altaCliente.dlgErrorInsertarCliente.setVisible(true);
			}
		}
	}
	
	
}

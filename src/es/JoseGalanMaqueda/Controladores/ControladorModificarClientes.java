package es.JoseGalanMaqueda.Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import es.JoseGalanMaqueda.modelo.ModeloCliente;
import es.JoseGalanMaqueda.vistas.ModificarCliente;

public class ControladorModificarClientes implements WindowListener, ActionListener {
	
	ModificarCliente vistaModificarCliente;
	ModeloCliente modeloCliente;
	
	public ControladorModificarClientes(ModificarCliente vistaModificarCliente, ModeloCliente modeloCliente) {
		this.vistaModificarCliente = vistaModificarCliente;
		this.modeloCliente = modeloCliente;
		
		// ======= VENTANA ELECCION CLIENTE =======
		this.vistaModificarCliente.frmModificarClienteUno.addWindowListener(this);
		this.vistaModificarCliente.btnBuscarCliente.addActionListener(this);
		this.vistaModificarCliente.btnModificarUno.addActionListener(this);
		this.vistaModificarCliente.btnCancelarModificarUno.addActionListener(this);
		this.modeloCliente.cargarListadoClientes(this.vistaModificarCliente.choListaCliente);
		
		// ======= DIALOGO ERROR SELECCION CLIENTE ======
		this.vistaModificarCliente.dlgErrorModificarSeleccionarCliente.addWindowListener(this);
		
		// ======= VENTANA EDICION CLIENTES =============
		this.vistaModificarCliente.frmModificacionClientesDos.addWindowListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource().equals(vistaModificarCliente.btnCancelarModificarUno)) 
		{
			vistaModificarCliente.frmModificarClienteUno.setVisible(false);
		}
		else if (e.getSource().equals(vistaModificarCliente.btnBuscarCliente)) 
		{
			modeloCliente.cargarListadoClientes(vistaModificarCliente.choListaCliente, vistaModificarCliente.txtBuscarCliente.getText());
		}
		else if (e.getSource().equals(vistaModificarCliente.btnModificarUno)) 
		{
			vistaModificarCliente.creacionDialogoNotificacion(vistaModificarCliente.dlgErrorModificarSeleccionarCliente, vistaModificarCliente.lblErrorModificarSeleccionarCliente);
			if (!vistaModificarCliente.choListaCliente.getSelectedItem().equals("Selecciona un Cliente..")) 
			{
				vistaModificarCliente.cargarVentanaEdicionClientes();
			}
			else 
			{
				vistaModificarCliente.dlgErrorModificarSeleccionarCliente.setVisible(true);
			}
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) 
	{
		if (vistaModificarCliente.frmModificarClienteUno.isActive()) 
		{
			vistaModificarCliente.frmModificarClienteUno.setVisible(false);
		}
		else if (vistaModificarCliente.dlgErrorModificarSeleccionarCliente.isActive()) 
		{
			vistaModificarCliente.dlgErrorModificarSeleccionarCliente.setVisible(false);
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
	
	
}

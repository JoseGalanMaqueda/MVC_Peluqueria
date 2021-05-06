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
		this.vistaModificarCliente.btnModificarUno.addActionListener(this);
		this.vistaModificarCliente.btnCancelarModificarUno.addActionListener(this);
		this.modeloCliente.cargarListadoClientes(this.vistaModificarCliente.choListaCliente);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
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

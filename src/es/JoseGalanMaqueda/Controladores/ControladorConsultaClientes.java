package es.JoseGalanMaqueda.Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import es.JoseGalanMaqueda.modelo.ModeloCliente;
import es.JoseGalanMaqueda.vistas.ConsultaClientes;

public class ControladorConsultaClientes implements ActionListener, WindowListener {
	
	ConsultaClientes consultaClientes;
	ModeloCliente modeloClientes;
	
	public ControladorConsultaClientes(ConsultaClientes consultaClientes, ModeloCliente modeloCliente) {
		this.consultaClientes = consultaClientes;
		this.modeloClientes = modeloCliente;
		
		this.consultaClientes.btnCancelarConsultaClientes.addActionListener(this);
		this.consultaClientes.btnExportarPdfClientes.addActionListener(this);
		this.consultaClientes.frmConsulaClientes.addWindowListener(this);
		
		this.modeloClientes.consultaClientes(this.consultaClientes.txaConsultaClientes);
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {
		if (consultaClientes.frmConsulaClientes.isActive()) {
			consultaClientes.frmConsulaClientes.setVisible(false);
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
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(consultaClientes.btnCancelarConsultaClientes)) {
			consultaClientes.frmConsulaClientes.setVisible(false);
		}
	}
	
}

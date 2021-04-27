package es.JoseGalanMaqueda.Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import es.JoseGalanMaqueda.modelo.ModeloCliente;
import es.JoseGalanMaqueda.vistas.BajaClientes;

public class ControladorBajaCliente implements WindowListener, ActionListener {
	
	BajaClientes bajaCliente;
	ModeloCliente modeloCliente;
	String eleccion;
	
	public ControladorBajaCliente(BajaClientes bajaCliente, ModeloCliente modeloCliente) {
		this.bajaCliente = bajaCliente;
		this.modeloCliente = modeloCliente;
		
		this.bajaCliente.btnBuscarCliente.addActionListener(this);
		this.bajaCliente.btnCancelarEliminar.addActionListener(this);
		this.bajaCliente.btnEliminar.addActionListener(this);
		this.bajaCliente.btnNoConfirmacionElimiarCliente.addActionListener(this);
		this.bajaCliente.btnSiConfirmacionElimiarCliente.addActionListener(this);
		
		this.bajaCliente.frmEliminarCliente.addWindowListener(this);
		this.bajaCliente.frmConfirmacionEliminarCliente.addWindowListener(this);
		this.bajaCliente.dlgErrorEliminar.addWindowListener(this);
		this.bajaCliente.dlgEliminadoCorrecto.addWindowListener(this);
		
		modeloCliente.cargarListadoClientes(bajaCliente.choListaCliente);
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(bajaCliente.btnCancelarEliminar)) {
			bajaCliente.frmEliminarCliente.setVisible(false);
		}else if (e.getSource().equals(bajaCliente.btnNoConfirmacionElimiarCliente)) {
			bajaCliente.frmConfirmacionEliminarCliente.setVisible(false);
		}else if (e.getSource().equals(bajaCliente.btnBuscarCliente)) {
			modeloCliente.cargarListadoClientes(bajaCliente.choListaCliente, bajaCliente.txtBuscarCliente.getText());
		}else if (e.getSource().equals(bajaCliente.btnEliminar)) {
			bajaCliente.creacionDialogoNotificacion(bajaCliente.dlgErrorEliminar, bajaCliente.lblErrorEliminar);
			if (!bajaCliente.choListaCliente.getSelectedItem().equals("Selecciona un Cliente..")) {
				bajaCliente.creacionVentanaConfirmacion(bajaCliente.frmConfirmacionEliminarCliente, bajaCliente.lblConfirmacionEliminarCliente, bajaCliente.btnSiConfirmacionElimiarCliente, bajaCliente.btnNoConfirmacionElimiarCliente);
			}else {
				bajaCliente.lblErrorEliminar.setText("Selecciona Cliente válido");
				bajaCliente.dlgErrorEliminar.setVisible(true);
			}
		}else if (e.getSource().equals(bajaCliente.btnSiConfirmacionElimiarCliente)) {
			bajaCliente.creacionDialogoNotificacion(bajaCliente.dlgErrorEliminar, bajaCliente.lblErrorEliminar);
			if (modeloCliente.eliminarClientes(bajaCliente.choListaCliente)) {
				bajaCliente.creacionDialogoNotificacion(bajaCliente.dlgEliminadoCorrecto, bajaCliente.lblEliminadoCorrectamente);
				bajaCliente.dlgEliminadoCorrecto.setVisible(true);
			}else {
				bajaCliente.lblErrorEliminar.setText("Error al eliminar");
				bajaCliente.dlgErrorEliminar.setVisible(true);
			}
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {
		if (bajaCliente.frmEliminarCliente.isActive()) {
			bajaCliente.frmEliminarCliente.setVisible(false);
		}else if (bajaCliente.frmConfirmacionEliminarCliente.isActive()) {
			bajaCliente.frmConfirmacionEliminarCliente.setVisible(false);
		}else if (bajaCliente.dlgErrorEliminar.isActive()) {
			bajaCliente.dlgErrorEliminar.setVisible(false);
		}else if (bajaCliente.dlgEliminadoCorrecto.isActive()) {
			bajaCliente.dlgEliminadoCorrecto.setVisible(false);
			bajaCliente.frmConfirmacionEliminarCliente.setVisible(false);
			bajaCliente.frmEliminarCliente.setVisible(false);
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

package es.JoseGalanMaqueda.Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import es.JoseGalanMaqueda.modelo.ModeloCitas;
import es.JoseGalanMaqueda.vistas.BajaCita;

public class ControladorBajaCitas implements WindowListener, ActionListener
{
	BajaCita vista;
	ModeloCitas modelo;
	
	public ControladorBajaCitas(BajaCita vista, ModeloCitas modelo) 
	{
		this.vista = vista;
		this.modelo = modelo;
		
		this.vista.btnBuscarCita.addActionListener(this);
		this.vista.btnCancelarEliminar.addActionListener(this);
		this.vista.btnEliminar.addActionListener(this);
		this.vista.btnNoConfirmacionElimiarCita.addActionListener(this);
		this.vista.btnSiConfirmacionElimiarCita.addActionListener(this);
		
		this.vista.frmEliminarCita.addWindowListener(this);
		this.vista.frmConfirmacionEliminarCita.addWindowListener(this);
		this.vista.dlgErrorEliminar.addWindowListener(this);
		this.vista.dlgEliminadoCorrecto.addWindowListener(this);
		
		modelo.cargarListadoClientes(vista.choListaCita);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource().equals(vista.btnCancelarEliminar)) 
		{
			vista.frmEliminarCita.setVisible(false);
		}
		else if (e.getSource().equals(vista.btnNoConfirmacionElimiarCita)) 
		{
			vista.frmConfirmacionEliminarCita.setVisible(false);
		}
		else if (e.getSource().equals(vista.btnBuscarCita)) 
		{
			modelo.cargarListadoClientes(vista.choListaCita, vista.txtBuscarCita.getText());
		}
		else if (e.getSource().equals(vista.btnEliminar)) 
		{
			vista.creacionDialogoNotificacion(vista.dlgErrorEliminar, vista.lblErrorEliminar);
			if (!vista.choListaCita.getSelectedItem().equals("Selecciona una Cita..")) 
			{
				vista.creacionVentanaConfirmacion(vista.frmConfirmacionEliminarCita, vista.lblConfirmacionEliminarCita, vista.btnSiConfirmacionElimiarCita, vista.btnNoConfirmacionElimiarCita);
			}
			else 
			{
				vista.lblErrorEliminar.setText("Selecciona una Cita válida");
				vista.dlgErrorEliminar.setVisible(true);
			}
		}
		else if (e.getSource().equals(vista.btnSiConfirmacionElimiarCita)) 
		{
			vista.creacionDialogoNotificacion(vista.dlgErrorEliminar, vista.lblErrorEliminar);
			if (modelo.eliminarClientes(vista.choListaCita)) 
			{
				vista.creacionDialogoNotificacion(vista.dlgEliminadoCorrecto, vista.lblEliminadoCorrectamente);
				vista.dlgEliminadoCorrecto.setVisible(true);
			}
			else 
			{
				vista.lblErrorEliminar.setText("Error al eliminar");
				vista.dlgErrorEliminar.setVisible(true);
			}
		}
		
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) 
	{
		if (vista.frmEliminarCita.isActive()) 
		{
			vista.frmEliminarCita.setVisible(false);
		}
		else if (vista.frmConfirmacionEliminarCita.isActive()) 
		{
			vista.frmConfirmacionEliminarCita.setVisible(false);
		}
		else if (vista.dlgErrorEliminar.isActive()) 
		{
			vista.dlgErrorEliminar.setVisible(false);
		}
		else if (vista.dlgEliminadoCorrecto.isActive()) 
		{
			vista.dlgEliminadoCorrecto.setVisible(false);
			vista.frmConfirmacionEliminarCita.setVisible(false);
			vista.frmEliminarCita.setVisible(false);
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

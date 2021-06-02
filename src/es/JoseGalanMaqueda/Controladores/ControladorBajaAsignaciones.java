package es.JoseGalanMaqueda.Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import es.JoseGalanMaqueda.modelo.ModeloTratamientosCitas;
import es.JoseGalanMaqueda.vistas.BajaAsignaciones;


public class ControladorBajaAsignaciones implements ActionListener, WindowListener
{
	BajaAsignaciones vista;
	ModeloTratamientosCitas modelo;

	public ControladorBajaAsignaciones(BajaAsignaciones vista, ModeloTratamientosCitas modelo) 
	{
		this.vista = vista;
		this.modelo = modelo;

		this.vista.btnBuscarAsignacion.addActionListener(this);
		this.vista.btnCancelarEliminar.addActionListener(this);
		this.vista.btnEliminar.addActionListener(this);
		this.vista.btnNoConfirmacionElimiarAsignacion.addActionListener(this);
		this.vista.btnSiConfirmacionElimiarAsignacion.addActionListener(this);

		this.vista.frmEliminarAsignacion.addWindowListener(this);
		this.vista.frmConfirmacionEliminarAsignacion.addWindowListener(this);
		this.vista.dlgErrorEliminar.addWindowListener(this);
		this.vista.dlgEliminadoCorrecto.addWindowListener(this);

		modelo.cargarListadoAsignaciones(vista.choListaAsignacion);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource().equals(vista.btnCancelarEliminar)) 
		{
			vista.frmEliminarAsignacion.setVisible(false);
		}
		else if (e.getSource().equals(vista.btnNoConfirmacionElimiarAsignacion)) 
		{
			vista.frmConfirmacionEliminarAsignacion.setVisible(false);
		}
		else if (e.getSource().equals(vista.btnBuscarAsignacion)) 
		{
			modelo.cargarListadoAsignaciones(vista.choListaAsignacion, vista.txtBuscarAsignacion.getText());
		}
		else if (e.getSource().equals(vista.btnEliminar)) 
		{
			vista.creacionDialogoNotificacion(vista.dlgErrorEliminar, vista.lblErrorEliminar);
			if (!vista.choListaAsignacion.getSelectedItem().equals("Selecciona una Asignacion..")) 
			{
				vista.creacionVentanaConfirmacion(vista.frmConfirmacionEliminarAsignacion, vista.lblConfirmacionEliminarAsignacion, vista.btnSiConfirmacionElimiarAsignacion, vista.btnNoConfirmacionElimiarAsignacion);
			}
			else 
			{
				vista.lblErrorEliminar.setText("Selecciona una Asignacion válida");
				vista.dlgErrorEliminar.setVisible(true);
			}
		}
		else if (e.getSource().equals(vista.btnSiConfirmacionElimiarAsignacion)) 
		{
			vista.creacionDialogoNotificacion(vista.dlgErrorEliminar, vista.lblErrorEliminar);
			if (modelo.eliminarAsignacion(vista.choListaAsignacion)) 
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
		if (vista.frmEliminarAsignacion.isActive()) 
		{
			vista.frmEliminarAsignacion.setVisible(false);
		}
		else if (vista.frmConfirmacionEliminarAsignacion.isActive()) 
		{
			vista.frmConfirmacionEliminarAsignacion.setVisible(false);
		}
		else if (vista.dlgErrorEliminar.isActive()) 
		{
			vista.dlgErrorEliminar.setVisible(false);
		}
		else if (vista.dlgEliminadoCorrecto.isActive()) 
		{
			vista.dlgEliminadoCorrecto.setVisible(false);
			vista.frmConfirmacionEliminarAsignacion.setVisible(false);
			vista.frmEliminarAsignacion.setVisible(false);
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


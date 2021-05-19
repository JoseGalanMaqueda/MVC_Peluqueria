package es.JoseGalanMaqueda.Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import es.JoseGalanMaqueda.modelo.ModeloCitas;
import es.JoseGalanMaqueda.modelo.ModeloCliente;
import es.JoseGalanMaqueda.modelo.ModeloTratamientosCitas;
import es.JoseGalanMaqueda.vistas.AltaCitas;
import es.JoseGalanMaqueda.vistas.AñadirAsignaciones;

public class ControladorAltaCitas implements WindowListener, ActionListener
{
	
	AltaCitas vista;
	ModeloCitas modeloCitas;
	
	public ControladorAltaCitas(AltaCitas vista, ModeloCitas modeloCitas) 
	{
		this.vista = vista;
		this.modeloCitas = modeloCitas;
		ModeloCliente modeloCliente = new ModeloCliente();
		
		this.vista.frmAltaCitas.addWindowListener(this);
		this.vista.btnAltaCitas.addActionListener(this);
		this.vista.btnCancelarCitas.addActionListener(this);
		
		this.vista.dlgCitasInsertado.addWindowListener(this);
		this.vista.dlgErrorInsertarCita.addWindowListener(this);
		
		modeloCliente.cargarListadoClientes(this.vista.cholistaClientes);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource().equals(vista.btnCancelarCitas)) 
		{
			vista.frmAltaCitas.setVisible(false);
		}
		else if (e.getSource().equals(vista.btnAltaCitas)) 
		{
			vista.creacionDialogoNotificacion(vista.dlgErrorInsertarCita, vista.lblErrorAnadidoCita);
			if (modeloCitas.comprobacionCitas(vista.txtFecha, vista.cholistaClientes)) 
			{
				if (modeloCitas.insertarCita(vista.txtFecha, vista.cholistaHoras, vista.cholistaClientes)) 
				{
					String clienteSeleccionado = vista.cholistaClientes.getSelectedItem().split("-")[1];
					String idCita = modeloCitas.idUltimaCita();
					vista.creacionDialogoNotificacion(vista.dlgCitasInsertado, vista.lblAnadidaCorrectamente);
					vista.dlgCitasInsertado.setVisible(true);
					AñadirAsignaciones vistaAsignaciones = new AñadirAsignaciones();
					ModeloTratamientosCitas modeloTratamientosCitas = new ModeloTratamientosCitas();
					new ControladorAñadirAsignaciones(vistaAsignaciones, modeloTratamientosCitas, idCita, clienteSeleccionado);
				}
				else 
				{
					vista.dlgErrorInsertarCita.setVisible(true);
				}
			}
			else 
			{
				vista.lblErrorAnadidoCita.setText("Datos incorrectos");
				vista.dlgErrorInsertarCita.setVisible(true);
			}
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) 
	{
		if (vista.frmAltaCitas.isActive()) 
		{
			vista.frmAltaCitas.setVisible(false);
		}
		else if (vista.dlgErrorInsertarCita.isActive()) 
		{
			vista.dlgErrorInsertarCita.setVisible(false);
		}
		else if (vista.dlgCitasInsertado.isActive()) 
		{
			vista.dlgCitasInsertado.setVisible(false);
			vista.frmAltaCitas.setVisible(false);
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

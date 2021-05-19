package es.JoseGalanMaqueda.Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import es.JoseGalanMaqueda.modelo.ModeloTratamientos;
import es.JoseGalanMaqueda.modelo.ModeloTratamientosCitas;
import es.JoseGalanMaqueda.vistas.AñadirAsignaciones;

public class ControladorAñadirAsignaciones implements WindowListener, ActionListener
{
	AñadirAsignaciones vista;
	ModeloTratamientosCitas modelo;
	String idCita;
	
	public ControladorAñadirAsignaciones(AñadirAsignaciones vista, ModeloTratamientosCitas modelo,String idCita, String clienteSeleccionado) 
	{
		this.vista = vista;
		this.modelo = modelo;
		this.idCita = idCita;
		
		this.vista.frmAsignaciones.addWindowListener(this);
		this.vista.btnCancelar.addActionListener(this);
		this.vista.btnAlta.addActionListener(this);
		this.vista.lblCitade.setText("Cita de "+clienteSeleccionado);
		
		ModeloTratamientos modeloTratamientos = new ModeloTratamientos();
		modeloTratamientos.cargarListadoTratamientos(vista.choListaTratamientos);
		
		
		this.vista.dlgErrorInsertarTratamiento.addWindowListener(this);
		this.vista.dlgTratamientoInsertado.addWindowListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource().equals(vista.btnCancelar)) 
		{
			vista.frmAsignaciones.setVisible(false);
		}
		else if (e.getSource().equals(vista.btnAlta)) 
		{
			vista.creacionDialogoNotificacion(vista.dlgErrorInsertarTratamiento, vista.lblErrorAnadidoTratamiento);
			if (!vista.choListaTratamientos.getSelectedItem().equals("Selecciona un Tratamiento..")) 
			{
				if (modelo.insertarTratamientoCita(idCita, vista.choListaTratamientos)) 
				{
					modelo.rellenarTextArea(idCita, vista.txaListadoTratamientos);
				}
				else 
				{
					vista.dlgErrorInsertarTratamiento.setVisible(true);
				}
			}
			else 
			{
				vista.lblErrorAnadidoTratamiento.setText("Selecciona Tratamiento Correcto");
				vista.dlgErrorInsertarTratamiento.setVisible(true);
			}
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) 
	{
		if (vista.frmAsignaciones.isActive()) 
		{
			vista.frmAsignaciones.setVisible(false);
		}
		else if (vista.dlgErrorInsertarTratamiento.isActive()) 
		{
			vista.dlgErrorInsertarTratamiento.setVisible(false);
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

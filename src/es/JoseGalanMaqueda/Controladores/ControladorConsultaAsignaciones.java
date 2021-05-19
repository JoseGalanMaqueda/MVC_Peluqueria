package es.JoseGalanMaqueda.Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import es.JoseGalanMaqueda.modelo.ModeloTratamientosCitas;
import es.JoseGalanMaqueda.vistas.ConsultarAsignaciones;

public class ControladorConsultaAsignaciones implements WindowListener, ActionListener
{
	
	ConsultarAsignaciones vista;
	ModeloTratamientosCitas modelo;
	
	public ControladorConsultaAsignaciones(ConsultarAsignaciones vista, ModeloTratamientosCitas modelo) 
	{
		this.vista = vista;
		this.modelo = modelo;
		
		this.vista.frmConsultaAsignaciones.addWindowListener(this);
		this.vista.btnCancelarConsultaAsignaciones.addActionListener(this);
		this.vista.btnExportarPdfAsignaciones.addActionListener(this);
		
		modelo.consultarAsignaciones(vista.txaConsultaAsignaciones);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource().equals(vista.btnCancelarConsultaAsignaciones)) 
		{
			vista.frmConsultaAsignaciones.setVisible(false);
		}
		else if (e.getSource().equals(vista.btnExportarPdfAsignaciones)) 
		{
			System.out.println("exportar");
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) 
	{
		vista.frmConsultaAsignaciones.setVisible(false);
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

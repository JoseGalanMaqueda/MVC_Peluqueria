package es.JoseGalanMaqueda.Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import es.JoseGalanMaqueda.modelo.ModeloCitas;
import es.JoseGalanMaqueda.vistas.ConsultarCitas;

public class ControladorConsultaCitas implements WindowListener, ActionListener
{
	ConsultarCitas vista;
	ModeloCitas modelo;
	
	public ControladorConsultaCitas(ConsultarCitas vista , ModeloCitas modelo) 
	{
		this.vista = vista;
		this.modelo = modelo;
		
		this.vista.frmConsulaCitas.addWindowListener(this);
		this.vista.btnCancelarConsultaCitas.addActionListener(this);
		this.vista.btnExportarPdfCitas.addActionListener(this);
		
		this.modelo.ConsultaCitas(vista.txaConsultaCitas);
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) 
	{
		if (vista.frmConsulaCitas.isActive()) 
		{
			vista.frmConsulaCitas.setVisible(false);
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
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource().equals(vista.btnCancelarConsultaCitas)) 
		{
			vista.frmConsulaCitas.setVisible(false);
		}
		else if (e.getSource().equals(vista.btnExportarPdfCitas)) 
		{
			System.out.println("Exportar");
		}
	}
}

















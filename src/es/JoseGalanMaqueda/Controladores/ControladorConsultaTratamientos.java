package es.JoseGalanMaqueda.Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import es.JoseGalanMaqueda.modelo.ModeloTratamientos;
import es.JoseGalanMaqueda.vistas.ConsultaTratamientos;

public class ControladorConsultaTratamientos implements WindowListener, ActionListener
{
	ConsultaTratamientos vista;
	ModeloTratamientos modelo;
	
	public ControladorConsultaTratamientos(ConsultaTratamientos vista, ModeloTratamientos modelo) 
	{
		this.vista = vista;
		this.modelo = modelo;
		
		// ======================= VENTANA PRINCIPAL =================
		this.vista.frmConsulaTratamientos.addWindowListener(this);
		this.vista.btnExportarPdfTratamientos.addActionListener(this);
		this.vista.btnCancelarConsultaTratamientos.addActionListener(this);
		this.modelo.consultaTratamientos(vista.txaConsultaTratamientos);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource().equals(vista.btnCancelarConsultaTratamientos)) 
		{
			vista.frmConsulaTratamientos.setVisible(false);
		}
		else if (e.getSource().equals(vista.btnExportarPdfTratamientos)) 
		{
			modelo.exportarAPDF(modelo.obtenerDatosParaExportar());
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) 
	{
		if (vista.frmConsulaTratamientos.isActive()) 
		{
			vista.frmConsulaTratamientos.setVisible(false);
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

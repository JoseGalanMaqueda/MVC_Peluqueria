package es.JoseGalanMaqueda.Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import es.JoseGalanMaqueda.modelo.ModeloTratamientos;
import es.JoseGalanMaqueda.vistas.AltaTratamientos;

public class ControladorAltaTratamientos implements WindowListener, ActionListener 
{
	
	AltaTratamientos altaTratamientos;
	ModeloTratamientos modeloTratamientos;
	
	public ControladorAltaTratamientos(AltaTratamientos altaTratamientos, ModeloTratamientos modeloTratamientos ) 
	{
		this.altaTratamientos = altaTratamientos;
		this.modeloTratamientos = modeloTratamientos;
		
		this.altaTratamientos.frmAltaTratamientos.addWindowListener(this);
		this.altaTratamientos.btnAltaTratamiento.addActionListener(this);
		this.altaTratamientos.btnCancelarAltaTratamiento.addActionListener(this);
		
		this.altaTratamientos.dlgErrorInsertarTratamiento.addWindowListener(this);
		this.altaTratamientos.dlgTratamientoInsertado.addWindowListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource().equals(altaTratamientos.btnCancelarAltaTratamiento)) 
		{
			altaTratamientos.frmAltaTratamientos.setVisible(false);
		}
		else if (e.getSource().equals(altaTratamientos.btnAltaTratamiento)) // BOTON ALTA CLIENTES
		{
			altaTratamientos.creacionDialogoNotificacion(altaTratamientos.dlgErrorInsertarTratamiento, altaTratamientos.lblErrorAnadidoTratamiento);
			if (modeloTratamientos.comprobacionDatos(altaTratamientos.txtNombreAltaTratamiento))
			{
				if (modeloTratamientos.insertarDatosTratamientos(altaTratamientos.txtNombreAltaTratamiento, altaTratamientos.listaPrecios, altaTratamientos.txaDescripcion))
				{
					altaTratamientos.creacionDialogoNotificacion(altaTratamientos.dlgTratamientoInsertado, altaTratamientos.lblAnadidoCorrectamente);
					altaTratamientos.dlgTratamientoInsertado.setVisible(true);
				}
				else 
				{
					altaTratamientos.lblErrorAnadidoTratamiento.setText("Error al Insertar");
					altaTratamientos.dlgErrorInsertarTratamiento.setVisible(true);
				}
			}
			else 
			{
				altaTratamientos.dlgErrorInsertarTratamiento.setVisible(true);
			}
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) 
	{
		if (altaTratamientos.frmAltaTratamientos.isActive()) 
		{
			altaTratamientos.frmAltaTratamientos.setVisible(false);
		}
		if (altaTratamientos.dlgErrorInsertarTratamiento.isActive()) 
		{
			altaTratamientos.dlgErrorInsertarTratamiento.setVisible(false);
		}
		if (altaTratamientos.dlgTratamientoInsertado.isActive()) 
		{
			altaTratamientos.dlgTratamientoInsertado.setVisible(false);
			altaTratamientos.frmAltaTratamientos.setVisible(false);
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

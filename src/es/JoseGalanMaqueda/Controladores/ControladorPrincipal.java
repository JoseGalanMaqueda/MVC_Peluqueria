package es.JoseGalanMaqueda.Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import es.JoseGalanMaqueda.vistas.AltaClientes;
import es.JoseGalanMaqueda.vistas.AltaTratamientos;
import es.JoseGalanMaqueda.vistas.Principal;

public class ControladorPrincipal implements ActionListener, WindowListener
{
	Principal vistaPrincipal;
	
	public ControladorPrincipal(Principal principal)
	{
		this.vistaPrincipal = principal;
		this.vistaPrincipal.ventanaPrincipal.addWindowListener(this);
		this.vistaPrincipal.mniAltaCitas.addActionListener(this);
		this.vistaPrincipal.mniBajaCliente.addActionListener(this);
		this.vistaPrincipal.mniModificacionCliente.addActionListener(this);
		this.vistaPrincipal.mniConsultaCliente.addActionListener(this);
		this.vistaPrincipal.mniAltaTratamiento.addActionListener(this);
		this.vistaPrincipal.mniBajaTratamiento.addActionListener(this);
		this.vistaPrincipal.mniModificacionTratamiento.addActionListener(this);
		this.vistaPrincipal.mniConsultaTratamientos.addActionListener(this);
		this.vistaPrincipal.mniAltaCitas.addActionListener(this);
		this.vistaPrincipal.mniConsultaCitas.addActionListener(this);
		this.vistaPrincipal.mniConsultarAsignacion.addActionListener(this);
	}

	@Override
	public void windowActivated(WindowEvent arg0){}

	@Override
	public void windowClosed(WindowEvent arg0){}

	@Override
	public void windowClosing(WindowEvent arg0)
	{
		if (this.vistaPrincipal.ventanaPrincipal.isActive())
		{
			System.exit(0);
		}
	}

	@Override
	public void windowDeactivated(WindowEvent arg0){}

	@Override
	public void windowDeiconified(WindowEvent arg0){}

	@Override
	public void windowIconified(WindowEvent arg0){}

	@Override
	public void windowOpened(WindowEvent arg0){}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if (arg0.getSource().equals(this.vistaPrincipal.mniAltaCliente))
		{
			new AltaClientes();
		}
		else if (arg0.getSource().equals(this.vistaPrincipal.mniAltaTratamiento))
		{
			new AltaTratamientos();
		}
	}

}

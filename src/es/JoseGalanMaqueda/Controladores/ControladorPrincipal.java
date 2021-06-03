package es.JoseGalanMaqueda.Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import es.JoseGalanMaqueda.modelo.Ayuda;
import es.JoseGalanMaqueda.modelo.ModeloCitas;
import es.JoseGalanMaqueda.modelo.ModeloCliente;
import es.JoseGalanMaqueda.modelo.ModeloTratamientos;
import es.JoseGalanMaqueda.modelo.ModeloTratamientosCitas;
import es.JoseGalanMaqueda.vistas.AltaCitas;
import es.JoseGalanMaqueda.vistas.AltaClientes;
import es.JoseGalanMaqueda.vistas.AltaTratamientos;
import es.JoseGalanMaqueda.vistas.BajaAsignaciones;
import es.JoseGalanMaqueda.vistas.BajaCita;
import es.JoseGalanMaqueda.vistas.BajaClientes;
import es.JoseGalanMaqueda.vistas.BajaTratamientos;
import es.JoseGalanMaqueda.vistas.ConsultaClientes;
import es.JoseGalanMaqueda.vistas.ConsultaTratamientos;
import es.JoseGalanMaqueda.vistas.ConsultarAsignaciones;
import es.JoseGalanMaqueda.vistas.ConsultarCitas;
import es.JoseGalanMaqueda.vistas.ModificarAsignacion;
import es.JoseGalanMaqueda.vistas.ModificarCita;
import es.JoseGalanMaqueda.vistas.ModificarCliente;
import es.JoseGalanMaqueda.vistas.ModificarTratamientos;
import es.JoseGalanMaqueda.vistas.Principal;

public class ControladorPrincipal implements ActionListener, WindowListener
{
	Principal vistaPrincipal;
	ModeloCitas modelo;
	
	public ControladorPrincipal(Principal principal, ModeloCitas modelo)
	{
		this.vistaPrincipal = principal;
		this.modelo = modelo;
		
		this.vistaPrincipal.ventanaPrincipal.addWindowListener(this);
		
		this.vistaPrincipal.mniAltaCliente.addActionListener(this);
		this.vistaPrincipal.mniBajaCliente.addActionListener(this);
		this.vistaPrincipal.mniModificacionCliente.addActionListener(this);
		this.vistaPrincipal.mniConsultaCliente.addActionListener(this);
		
		this.vistaPrincipal.mniAltaTratamiento.addActionListener(this);
		this.vistaPrincipal.mniBajaTratamiento.addActionListener(this);
		this.vistaPrincipal.mniModificacionTratamiento.addActionListener(this);
		this.vistaPrincipal.mniConsultaTratamientos.addActionListener(this);
		
		this.vistaPrincipal.mniAltaCitas.addActionListener(this);
		this.vistaPrincipal.mniConsultaCitas.addActionListener(this);
		this.vistaPrincipal.mniBajaCitas.addActionListener(this);
		this.vistaPrincipal.mniModificacionCitas.addActionListener(this);
		
		this.vistaPrincipal.mniConsultarAsignacion.addActionListener(this);
		this.vistaPrincipal.mniBajaAsignacion.addActionListener(this);
		this.vistaPrincipal.mniModificacionAsignacion.addActionListener(this);
		
		this.vistaPrincipal.mniAyuda.addActionListener(this);
		
		this.vistaPrincipal.btnActualizar.addActionListener(this);
		
		this.modelo.consultaPrincipal(this.vistaPrincipal.txaCitasHoy);
	}

	@Override
	public void windowActivated(WindowEvent arg0){}

	@Override
	public void windowClosed(WindowEvent arg0){}

	@Override
	public void windowClosing(WindowEvent arg0)
	{
		if (vistaPrincipal.ventanaPrincipal.isActive())
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
		if (arg0.getSource().equals(vistaPrincipal.btnActualizar)) 
		{
			this.modelo.consultaPrincipal(this.vistaPrincipal.txaCitasHoy);
		}
		else if (arg0.getSource().equals(vistaPrincipal.mniAltaCliente))
		{
			AltaClientes altaClientes = new AltaClientes();
			ModeloCliente modeloClientes = new ModeloCliente();
			new ControladorAltaClientes(altaClientes, modeloClientes);
		}
		else if (arg0.getSource().equals(vistaPrincipal.mniBajaCliente)) {
			BajaClientes bajaClientes = new BajaClientes();
			ModeloCliente modeloCliente = new ModeloCliente();
			new ControladorBajaCliente(bajaClientes, modeloCliente);
		}
		else if (arg0.getSource().equals(vistaPrincipal.mniModificacionCliente)) 
		{
			ModificarCliente modificarCliente = new ModificarCliente();
			ModeloCliente modeloCliente = new ModeloCliente();
			new ControladorModificarClientes(modificarCliente, modeloCliente);
		}
		else if (arg0.getSource().equals(vistaPrincipal.mniConsultaCliente)) 
		{
			ConsultaClientes consultaClientes = new ConsultaClientes();
			ModeloCliente modeloCliente = new ModeloCliente();
			new ControladorConsultaClientes(consultaClientes, modeloCliente);
		}
		else if (arg0.getSource().equals(vistaPrincipal.mniAltaTratamiento))
		{
			AltaTratamientos altaTratamientos = new AltaTratamientos();
			ModeloTratamientos modeloTratamientos = new ModeloTratamientos();
			new ControladorAltaTratamientos(altaTratamientos, modeloTratamientos);
		}
		else if (arg0.getSource().equals(vistaPrincipal.mniBajaTratamiento)) 
		{
			BajaTratamientos bajaTratamientos = new BajaTratamientos();
			ModeloTratamientos modeloTratamientos = new ModeloTratamientos();
			new ControladorBajaTratamientos(bajaTratamientos, modeloTratamientos);
		}
		else if (arg0.getSource().equals(vistaPrincipal.mniModificacionTratamiento)) 
		{
			ModificarTratamientos modificarTratamientos = new ModificarTratamientos();
			ModeloTratamientos modeloTratamientos = new ModeloTratamientos();
			new ControladorModificarTratamientos(modificarTratamientos, modeloTratamientos);
		}
		else if (arg0.getSource().equals(vistaPrincipal.mniConsultaTratamientos)) 
		{
			ConsultaTratamientos consultaTratamientos = new ConsultaTratamientos();
			ModeloTratamientos modeloTratamientos = new ModeloTratamientos();
			new ControladorConsultaTratamientos(consultaTratamientos, modeloTratamientos);
		}
		else if (arg0.getSource().equals(vistaPrincipal.mniAltaCitas)) 
		{
			AltaCitas vista = new AltaCitas();
			ModeloCitas modeloCitas = new ModeloCitas();
			new ControladorAltaCitas(vista, modeloCitas);
		}
		else if (arg0.getSource().equals(vistaPrincipal.mniBajaCitas)) 
		{
			BajaCita vista = new BajaCita();
			ModeloCitas modeloCitas = new ModeloCitas();
			new ControladorBajaCitas(vista, modeloCitas);
		}
		else if (arg0.getSource().equals(vistaPrincipal.mniModificacionCitas)) 
		{
			ModificarCita vista = new ModificarCita();
			ModeloCitas modelo = new ModeloCitas();
			ModeloCliente modeloCliente = new ModeloCliente();
			new ControladorModificarCita(vista, modelo, modeloCliente);
		}
		else if (arg0.getSource().equals(vistaPrincipal.mniConsultaCitas)) 
		{
			ConsultarCitas vista = new ConsultarCitas();
			ModeloCitas modelo = new ModeloCitas();
			new ControladorConsultaCitas(vista, modelo);
		}
		else if (arg0.getSource().equals(vistaPrincipal.mniConsultarAsignacion)) 
		{
			ConsultarAsignaciones vista = new ConsultarAsignaciones();
			ModeloTratamientosCitas modelo = new ModeloTratamientosCitas();
			new ControladorConsultaAsignaciones(vista, modelo);
		}
		else if (arg0.getSource().equals(vistaPrincipal.mniBajaAsignacion)) 
		{
			BajaAsignaciones vista = new BajaAsignaciones();
			ModeloTratamientosCitas modelo = new ModeloTratamientosCitas();
			new ControladorBajaAsignaciones(vista, modelo);
		}
		else if (arg0.getSource().equals(vistaPrincipal.mniModificacionAsignacion)) 
		{
			ModificarAsignacion vista = new  ModificarAsignacion();
			ModeloTratamientosCitas modelo = new ModeloTratamientosCitas();
			ModeloCitas modeloCitas = new ModeloCitas();
			ModeloTratamientos modeloTratamientos = new ModeloTratamientos();
			new ControladorModificarAsignaciones(vista, modelo, modeloCitas, modeloTratamientos);
		}
		else if (arg0.getSource().equals(vistaPrincipal.mniAyuda)) 
		{
			Ayuda.ejecutarAyuda();
		}
	}

}

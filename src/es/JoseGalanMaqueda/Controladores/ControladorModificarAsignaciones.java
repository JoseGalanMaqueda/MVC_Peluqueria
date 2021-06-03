package es.JoseGalanMaqueda.Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import es.JoseGalanMaqueda.modelo.ModeloCitas;
import es.JoseGalanMaqueda.modelo.ModeloTratamientos;
import es.JoseGalanMaqueda.modelo.ModeloTratamientosCitas;
import es.JoseGalanMaqueda.vistas.ModificarAsignacion;

public class ControladorModificarAsignaciones implements ActionListener, WindowListener
{
	// ===================== ATRIBUTOS ===============================
	ModificarAsignacion vista;
	ModeloTratamientosCitas modelo;
	ModeloCitas modeloCitas;
	ModeloTratamientos modeloTratamientos;

	// ====================== CONSTRUCTOR =================================
	public ControladorModificarAsignaciones(ModificarAsignacion vista, ModeloTratamientosCitas modelo, ModeloCitas modeloCitas,ModeloTratamientos modeloTratamientos) 
	{
		this.vista = vista;
		this.modelo = modelo;
		this.modeloCitas = modeloCitas;
		this.modeloTratamientos = modeloTratamientos;


		// ======= VENTANA ELECCION CITA =======
		this.vista.frmModificarAsignacionUno.addWindowListener(this);
		this.vista.btnModificar.addActionListener(this);
		this.vista.btnCancelarModificar.addActionListener(this);
		this.modelo.cargarListadoAsignaciones(this.vista.choListaAsignacion);

		// ======= DIALOGO ERROR SELECCION CLIENTE ======
		this.vista.dlgErrorModificarSeleccionarModificar.addWindowListener(this);

		// ======= VENTANA EDICION CLIENTES =============
		this.vista.frmModificarAsignacionUno.addWindowListener(this);
		this.vista.btnCancelarModificarAsignacionDos.addActionListener(this);
		this.vista.btnModificarAsignacionDos.addActionListener(this);

		// ========== VENTANA CONFIRMACION ================
		this.vista.frmConfirmacionModificarAsignacion.addWindowListener(this);
		this.vista.btnSiConfirmacionModificarAsignacion.addActionListener(this);
		this.vista.btnNoConfirmacionModificarAsignacion.addActionListener(this);

		// ======= DIALOGO ERROR MODIFICAR ================
		this.vista.dlgErrorModificar.addWindowListener(this);

		// ======== DIALOGO MODIFICACION CORRECTA ===========
		this.vista.dlgModificadoCorrecto.addWindowListener(this);
		
		// ======= VENTANA MODIFICACION DOS ===================
		this.vista.frmModificarAsignacionDos.addWindowListener(this);
		this.vista.btnCancelarModificarAsignacionDos.addActionListener(this);
		this.vista.btnModificarAsignacionDos.addActionListener(this);
	}

	// ====================== ACTION LISTENER ==============================================
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource().equals(vista.btnCancelarModificar)) 
		{
			vista.frmModificarAsignacionUno.setVisible(false);
		}
		else if (e.getSource().equals(vista.btnModificar)) 
		{
			vista.creacionDialogoNotificacion(vista.dlgErrorModificarSeleccionarModificar, vista.lblErrorModificarSeleccionarModificar);
			if (!vista.choListaAsignacion.getSelectedItem().equals("Selecciona una Asignacion..")) 
			{
				vista.ventanaEdicionAsignacion();
				String[] datos = (modelo.cargarDatosAsignaciones(vista.choListaAsignacion.getSelectedItem().split("-")[0])).split("-");
				cargarDatosVentana(datos);
			}
			else 
			{
				vista.dlgErrorModificarSeleccionarModificar.setVisible(true);
			}
		}
		else if (e.getSource().equals(vista.btnCancelarModificarAsignacionDos)) 
		{
			vista.frmModificarAsignacionDos.setVisible(false);
		}
		else if (e.getSource().equals(vista.btnModificarAsignacionDos)) 
		{
			vista.creacionDialogoNotificacion(vista.dlgErrorModificar, vista.lblErrorModificar);
			if (modelo.comprobacionDatosAsignaciones(vista.choListaCitas, vista.choListaTratamiento))
			{
				vista.creacionVentanaConfirmacion(vista.frmConfirmacionModificarAsignacion, vista.lblConfirmacionModificarAsignacion, vista.btnSiConfirmacionModificarAsignacion, vista.btnNoConfirmacionModificarAsignacion);
			}
			else 
			{
				vista.lblErrorModificar.setText("Faltan Datos");
				vista.dlgErrorModificar.setVisible(true);
			}
		}
		else if (e.getSource().equals(vista.btnNoConfirmacionModificarAsignacion)) 
		{
			vista.frmConfirmacionModificarAsignacion.setVisible(false);
		}
		else if (e.getSource().equals(vista.btnSiConfirmacionModificarAsignacion)) 
		{
			if (modelo.actualizarAsignaciones(vista.txtIdAsignacion,vista.choListaCitas,vista.choListaTratamiento))
			{
				vista.creacionDialogoNotificacion(vista.dlgModificadoCorrecto, vista.lblModificarCorrectamente);
				vista.dlgModificadoCorrecto.setVisible(true);
			}
			else 
			{
				vista.lblErrorModificar.setText("Error al Insertar");
				vista.dlgErrorModificar.setVisible(true);
			}
		}
	}

	// ==================================== WINDOW LISTENER ===================================================
	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) 
	{
		if (vista.frmModificarAsignacionUno.isActive()) 
		{
			vista.frmModificarAsignacionUno.setVisible(false);
		}
		else if (vista.dlgErrorModificarSeleccionarModificar.isActive()) 
		{
			vista.dlgErrorModificarSeleccionarModificar.setVisible(false);
		}
		else if (vista.frmModificarAsignacionDos.isActive()) 
		{
			vista.frmModificarAsignacionDos.setVisible(false);
		}
		else if (vista.dlgErrorModificar.isActive()) 
		{
			vista.dlgErrorModificar.setVisible(false);
		}
		else if (vista.dlgModificadoCorrecto.isActive()) 
		{
			vista.dlgModificadoCorrecto.setVisible(false);
			vista.frmConfirmacionModificarAsignacion.setVisible(false);
			vista.frmModificarAsignacionDos.setVisible(false);
			vista.frmModificarAsignacionUno.setVisible(false);
		}
		else if (vista.frmConfirmacionModificarAsignacion.isActive()) 
		{
			vista.frmConfirmacionModificarAsignacion.setVisible(false);
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

	// ============================== CARGAR DATOS VENTANA =============================================
	public void cargarDatosVentana(String[] datos) 
	{
		vista.txtIdAsignacion.setText(datos[0]);
		
		String[] datosAsignacion = modelo.cargarDatosAsignaciones(datos[0]).split("-");
		
		modeloCitas.cargarListadoCitas(vista.choListaCitas);
		modeloTratamientos.cargarListadoTratamientos(vista.choListaTratamiento);
		
		String cita = modeloCitas.cargarDatoCita(datosAsignacion[1]);
		vista.choListaCitas.select(cita);
		
		String tratamiento = modeloTratamientos.cargarDatoTratamiento(datosAsignacion[2]);
		vista.choListaTratamiento.select(tratamiento);
	}

}


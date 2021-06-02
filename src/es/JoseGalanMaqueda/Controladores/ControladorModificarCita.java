package es.JoseGalanMaqueda.Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import es.JoseGalanMaqueda.modelo.ModeloCitas;
import es.JoseGalanMaqueda.modelo.ModeloCliente;
import es.JoseGalanMaqueda.vistas.ModificarCita;

public class ControladorModificarCita implements ActionListener, WindowListener
{
	// ===================== ATRIBUTOS ===============================
	ModificarCita vista;
	ModeloCitas modelo;
	ModeloCliente modeloCliente;
	String eleccionDos="";

	// ====================== CONSTRUCTOR =================================
	public ControladorModificarCita(ModificarCita vista, ModeloCitas modelo, ModeloCliente modeloCliente) 
	{
		this.vista = vista;
		this.modelo = modelo;
		this.modeloCliente = modeloCliente;


		// ======= VENTANA ELECCION CITA =======
		this.vista.frmModificarCitaUno.addWindowListener(this);
		this.vista.btnBuscarCita.addActionListener(this);
		this.vista.btnModificarUno.addActionListener(this);
		this.vista.btnCancelarModificarUno.addActionListener(this);
		this.modelo.cargarListadoCitas(this.vista.choListaCitas);

		// ======= DIALOGO ERROR SELECCION CLIENTE ======
		this.vista.dlgErrorModificarSeleccionarCita.addWindowListener(this);

		// ======= VENTANA EDICION CLIENTES =============
		this.vista.frmModificarCitaDos.addWindowListener(this);
		this.vista.btnCancelarCitas.addActionListener(this);
		this.vista.btnModificarDos.addActionListener(this);

		// ========== VENTANA CONFIRMACION ================
		this.vista.frmConfirmacionModificarCita.addWindowListener(this);
		this.vista.btnSiConfirmacionModificarCita.addActionListener(this);
		this.vista.btnNoConfirmacionModificarCita.addActionListener(this);

		// ======= DIALOGO ERROR MODIFICAR ================
		this.vista.dlgErrorModificarCita.addWindowListener(this);

		// ======== DIALOGO MODIFICACION CORRECTA ===========
		this.vista.dlgCitaModificado.addWindowListener(this);
	}

	// ====================== ACTION LISTENER ==============================================
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource().equals(vista.btnCancelarModificarUno)) 
		{
			vista.frmModificarCitaUno.setVisible(false);
		}
		else if (e.getSource().equals(vista.btnBuscarCita)) 
		{
			modelo.cargarListadoCitas(vista.choListaCitas, vista.txtBuscarCita.getText());
		}
		else if (e.getSource().equals(vista.btnModificarUno)) 
		{
			vista.creacionDialogoNotificacion(vista.dlgErrorModificarSeleccionarCita, vista.lblErrorModificarSeleccionarCita);
			if (!vista.choListaCitas.getSelectedItem().equals("Selecciona una Cita..")) 
			{
				vista.ventanaModificacion();
				String[] datos = (modelo.cargarDatosCitas(vista.choListaCitas.getSelectedItem().split("-")[0])).split("-");
				cargarDatosVentana(datos);
			}
			else 
			{
				vista.dlgErrorModificarSeleccionarCita.setVisible(true);
			}
		}
		else if (e.getSource().equals(vista.btnCancelarCitas)) 
		{
			vista.frmModificarCitaDos.setVisible(false);
		}
		else if (e.getSource().equals(vista.btnModificarDos)) 
		{
			vista.creacionDialogoNotificacion(vista.dlgErrorModificarCita, vista.lblErrorModificarCita);
			if (modelo.comprobacionCitas(vista.txtFecha, vista.cholistaClientes))
			{
				vista.creacionVentanaConfirmacion(vista.frmConfirmacionModificarCita, vista.lblConfirmacionModificarCita, vista.btnSiConfirmacionModificarCita, vista.btnNoConfirmacionModificarCita);
			}
			else 
			{
				vista.lblErrorModificarCita.setText("Faltan Datos");
				vista.dlgErrorModificarCita.setVisible(true);
			}
		}
		else if (e.getSource().equals(vista.btnNoConfirmacionModificarCita)) 
		{
			vista.frmConfirmacionModificarCita.setVisible(false);
		}
		else if (e.getSource().equals(vista.btnSiConfirmacionModificarCita)) 
		{
			if (modelo.actualizarCita(vista.txtIdCita,vista.txtFecha,vista.cholistaHoras, vista.cholistaClientes))
			{
				vista.creacionDialogoNotificacion(vista.dlgCitaModificado, vista.lblModificadoCorrectamente);
				vista.dlgCitaModificado.setVisible(true);
			}
			else 
			{
				vista.lblErrorModificarCita.setText("Error al Insertar");
				vista.dlgErrorModificarCita.setVisible(true);
			}
		}
	}

	// ==================================== WINDOW LISTENER ===================================================
	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) 
	{
		if (vista.frmModificarCitaUno.isActive()) 
		{
			vista.frmModificarCitaUno.setVisible(false);
		}
		else if (vista.dlgErrorModificarSeleccionarCita.isActive()) 
		{
			vista.dlgErrorModificarSeleccionarCita.setVisible(false);
		}
		else if (vista.frmModificarCitaDos.isActive()) 
		{
			vista.frmModificarCitaDos.setVisible(false);
		}
		else if (vista.dlgErrorModificarCita.isActive()) 
		{
			vista.dlgErrorModificarCita.setVisible(false);
		}
		else if (vista.dlgCitaModificado.isActive()) 
		{
			vista.dlgCitaModificado.setVisible(false);
			vista.frmConfirmacionModificarCita.setVisible(false);
			vista.frmModificarCitaDos.setVisible(false);
			vista.frmModificarCitaUno.setVisible(false);
		}
		else if (vista.frmConfirmacionModificarCita.isActive()) 
		{
			vista.frmConfirmacionModificarCita.setVisible(false);
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
		vista.txtIdCita.setText(datos[0]);
		vista.txtFecha.setText(datos[3]+"/"+datos[2]+"/"+datos[1]);
		String[] quitarSegundos = datos[4].split(":");
		vista.cholistaHoras.select(quitarSegundos[0]+":"+quitarSegundos[1]);
		modeloCliente.cargarListadoClientes(vista.cholistaClientes);
		String cliente = modeloCliente.cargarDatoCliente(datos[5]);
		vista.cholistaClientes.select(cliente);
	}

}


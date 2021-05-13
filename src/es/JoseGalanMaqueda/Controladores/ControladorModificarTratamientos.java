package es.JoseGalanMaqueda.Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import es.JoseGalanMaqueda.modelo.ModeloTratamientos;
import es.JoseGalanMaqueda.vistas.ModificarTratamientos;

public class ControladorModificarTratamientos implements ActionListener, WindowListener 
{
	// ===================================== ATRIBUTOS ==================================================
	ModificarTratamientos vista;
	ModeloTratamientos modelo;

	// =================================== CONSTRUCTOR ================================================
	public ControladorModificarTratamientos(ModificarTratamientos vista, ModeloTratamientos modelo) 
	{
		this.vista = vista;
		this.modelo = modelo;

		// ========================= VENTANA PRINCIPAL =============================
		this.vista.frmModificarTratamientoUno.addWindowListener(this);
		this.vista.btnCancelarModificarUno.addActionListener(this);
		this.vista.btnModificarUno.addActionListener(this);
		this.vista.btnBuscarTratamiento.addActionListener(this);
		modelo.cargarListadoTratamientos(this.vista.choListaTratamientos);

		// ======================== DIALOGO ERROR SELECCIONAR ====================
		this.vista.dlgErrorModificarSeleccionarTratamiento.addWindowListener(this);

		// ======================== VENTANA MODIFICACION ==========================
		this.vista.frmModificarTratamientoDos.addWindowListener(this);
		this.vista.btnModificacionTratamientoDos.addActionListener(this);
		this.vista.btnCancelarModificacionTratamientoDos.addActionListener(this);

		// ========================= DIALOGO ERROR ==================================
		this.vista.dlgErrorModificarTratamiento.addWindowListener(this);

		// ======================== VENTANA CONFIRMACION ============================
		this.vista.frmConfirmacionModificarTratamiento.addWindowListener(this);
		this.vista.btnNoConfirmacionModificarTratamiento.addActionListener(this);
		this.vista.btnSiConfirmacionModificarTratamiento.addActionListener(this);
		
		// ====================== DIALOGO CORRECTO ==================================
		this.vista.dlgTratamientoModificado.addWindowListener(this);

	}

	// ========================================= WINDOWS LISTENER ===================================
	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) 
	{
		if (vista.frmModificarTratamientoUno.isActive()) 
		{
			vista.frmModificarTratamientoUno.setVisible(false);
		}
		else if (vista.dlgErrorModificarSeleccionarTratamiento.isActive()) 
		{
			vista.dlgErrorModificarSeleccionarTratamiento.setVisible(false);
		}
		else if (vista.dlgErrorModificarTratamiento.isActive()) 
		{
			vista.dlgErrorModificarTratamiento.setVisible(false);
		}
		else if (vista.frmConfirmacionModificarTratamiento.isActive()) 
		{
			vista.frmConfirmacionModificarTratamiento.setVisible(false);
		}
		else if (vista.dlgTratamientoModificado.isActive()) 
		{
			vista.dlgTratamientoModificado.setVisible(false);
			vista.frmConfirmacionModificarTratamiento.setVisible(false);
			vista.frmModificarTratamientoDos.setVisible(false);
			vista.frmModificarTratamientoUno.setVisible(false);
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

	
	// ============================================= ACTION LISTENER =====================================================
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource().equals(vista.btnBuscarTratamiento)) 
		{
			modelo.cargarListadoTratamientos(vista.choListaTratamientos, vista.txtBuscarTratamiento.getText());
		}
		else if (e.getSource().equals(vista.btnCancelarModificarUno)) 
		{
			vista.frmModificarTratamientoUno.setVisible(false);
		}
		else if (e.getSource().equals(vista.btnModificarUno)) 
		{
			vista.creacionDialogoNotificacion(vista.dlgErrorModificarSeleccionarTratamiento, vista.lblErrorModificarSeleccionarTratamiento);
			if (!vista.choListaTratamientos.getSelectedItem().equals("Selecciona un Tratamiento..")) 
			{
				vista.cargarVentanaEdicionTratamiento();
				String[] datos = modelo.cargarDatosTratamientos(vista.choListaTratamientos.getSelectedItem().split("-")[0]).split("-");
				cargarDatosVentana(datos);
			}
			else 
			{
				vista.dlgErrorModificarSeleccionarTratamiento.setVisible(true);
			}
		}
		else if (e.getSource().equals(vista.btnCancelarModificacionTratamientoDos)) 
		{
			vista.frmModificarTratamientoDos.setVisible(false);
		}
		else if (e.getSource().equals(vista.btnModificacionTratamientoDos)) 
		{
			vista.creacionDialogoNotificacion(vista.dlgErrorModificarTratamiento, vista.lblErrorModificarTratamiento);
			if (modelo.comprobacionDatos(vista.txtNombreModificacionTratamientoDos)) 
			{
				vista.creacionVentanaConfirmacion(vista.frmConfirmacionModificarTratamiento, vista.lblConfirmacionModificarTratamiento, vista.btnSiConfirmacionModificarTratamiento, vista.btnNoConfirmacionModificarTratamiento);
			}
			else 
			{
				vista.lblErrorModificarTratamiento.setText("Datos incorrectos");
				vista.dlgErrorModificarTratamiento.setVisible(true);
			}
		}
		else if (e.getSource().equals(vista.btnNoConfirmacionModificarTratamiento)) 
		{
			vista.frmConfirmacionModificarTratamiento.setVisible(false);
		}
		else if (e.getSource().equals(vista.btnSiConfirmacionModificarTratamiento)) 
		{
			if (modelo.actulizarTratamientos(vista.txtIdModificacionTratamientoDos,vista.txtNombreModificacionTratamientoDos, vista.listaPrecios, vista.txaDescripcionModificacion))
			{
				vista.creacionDialogoNotificacion(vista.dlgTratamientoModificado, vista.lblModificadoCorrectamente);
				vista.dlgTratamientoModificado.setVisible(true);
			}else {
				vista.lblErrorModificarTratamiento.setText("Error al Modificar");
				vista.dlgErrorModificarTratamiento.setVisible(true);
			}
		}
	}

	public void cargarDatosVentana(String[] datos) 
	{
		vista.txtIdModificacionTratamientoDos.setText(datos[0]);
		vista.txtNombreModificacionTratamientoDos.setText(datos[1]);
		vista.txaDescripcionModificacion.setText(datos[2]);
		vista.listaPrecios.select(datos[3]+"0");
	}
}



















package es.JoseGalanMaqueda.Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import es.JoseGalanMaqueda.modelo.ModeloCliente;
import es.JoseGalanMaqueda.vistas.ModificarCliente;

public class ControladorModificarClientes implements WindowListener, ActionListener {
	
	// ===================== ATRIBUTOS ===============================
	ModificarCliente vistaModificarCliente;
	ModeloCliente modeloCliente;
	String eleccionDos="";
	
	// ====================== CONSTRUCTOR =================================
	public ControladorModificarClientes(ModificarCliente vistaModificarCliente, ModeloCliente modeloCliente) {
		this.vistaModificarCliente = vistaModificarCliente;
		this.modeloCliente = modeloCliente;
		
		
		// ======= VENTANA ELECCION CLIENTE =======
		this.vistaModificarCliente.frmModificarClienteUno.addWindowListener(this);
		this.vistaModificarCliente.btnBuscarCliente.addActionListener(this);
		this.vistaModificarCliente.btnModificarUno.addActionListener(this);
		this.vistaModificarCliente.btnCancelarModificarUno.addActionListener(this);
		this.modeloCliente.cargarListadoClientes(this.vistaModificarCliente.choListaCliente);
		
		// ======= DIALOGO ERROR SELECCION CLIENTE ======
		this.vistaModificarCliente.dlgErrorModificarSeleccionarCliente.addWindowListener(this);
		
		// ======= VENTANA EDICION CLIENTES =============
		this.vistaModificarCliente.frmModificacionClientesDos.addWindowListener(this);
		this.vistaModificarCliente.btnCancelarModificacionClientesDos.addActionListener(this);
		this.vistaModificarCliente.btnModificacionClientesDos.addActionListener(this);
		
		// ======= DIALOGO ERROR MODIFICAR ================
		this.vistaModificarCliente.dlgErrorModificarCliente.addWindowListener(this);
		
		// ======== DIALOGO MODIFICACION CORRECTA ===========
		this.vistaModificarCliente.dlgClientesModificado.addWindowListener(this);
	}

	// ====================== ACTION LISTENER ==============================================
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource().equals(vistaModificarCliente.btnCancelarModificarUno)) 
		{
			vistaModificarCliente.frmModificarClienteUno.setVisible(false);
		}
		else if (e.getSource().equals(vistaModificarCliente.btnBuscarCliente)) 
		{
			modeloCliente.cargarListadoClientes(vistaModificarCliente.choListaCliente, vistaModificarCliente.txtBuscarCliente.getText());
		}
		else if (e.getSource().equals(vistaModificarCliente.btnModificarUno)) 
		{
			vistaModificarCliente.creacionDialogoNotificacion(vistaModificarCliente.dlgErrorModificarSeleccionarCliente, vistaModificarCliente.lblErrorModificarSeleccionarCliente);
			if (!vistaModificarCliente.choListaCliente.getSelectedItem().equals("Selecciona un Cliente..")) 
			{
				vistaModificarCliente.cargarVentanaEdicionClientes();
				String[] datos = (modeloCliente.cargarDatosCliente(vistaModificarCliente.choListaCliente.getSelectedItem().split("-")[0])).split("-");
				cargarDatosVentana(datos);
			}
			else 
			{
				vistaModificarCliente.dlgErrorModificarSeleccionarCliente.setVisible(true);
			}
		}
		else if (e.getSource().equals(vistaModificarCliente.btnCancelarModificacionClientesDos)) 
		{
			vistaModificarCliente.frmModificacionClientesDos.setVisible(false);
		}
		else if (e.getSource().equals(vistaModificarCliente.btnModificacionClientesDos)) 
		{
			vistaModificarCliente.creacionDialogoNotificacion(vistaModificarCliente.dlgErrorModificarCliente, vistaModificarCliente.lblErrorModificarCliente);
			if (modeloCliente.comprobacionDatosIntroducidos(vistaModificarCliente.txtNombreModificacionClientesDos,
					vistaModificarCliente.txtApellidosModificacionClientesDos, vistaModificarCliente.txtDniModificacionClientesDos, vistaModificarCliente.txtDireccionModificacionClientesDos, eleccionDos))
			{
				if (modeloCliente.actualizarCliente(vistaModificarCliente.txtIdModificacionClientesDos,vistaModificarCliente.txtNombreModificacionClientesDos,
						vistaModificarCliente.txtApellidosModificacionClientesDos, vistaModificarCliente.txtDniModificacionClientesDos, vistaModificarCliente.txtDireccionModificacionClientesDos, eleccionDos))
				{
					vistaModificarCliente.creacionDialogoNotificacion(vistaModificarCliente.dlgClientesModificado, vistaModificarCliente.lblModificadoCorrectamente);
					vistaModificarCliente.dlgClientesModificado.setVisible(true);
				}else {
					vistaModificarCliente.lblErrorModificarCliente.setText("Error al Insertar");
					vistaModificarCliente.dlgErrorModificarCliente.setVisible(true);
				}
			}else {
				vistaModificarCliente.lblErrorModificarCliente.setText("Faltan Datos");
				vistaModificarCliente.dlgErrorModificarCliente.setVisible(true);
			}
		}
	}

	// ==================================== WINDOW LISTENER ===================================================
	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) 
	{
		if (vistaModificarCliente.frmModificarClienteUno.isActive()) 
		{
			vistaModificarCliente.frmModificarClienteUno.setVisible(false);
		}
		else if (vistaModificarCliente.dlgErrorModificarSeleccionarCliente.isActive()) 
		{
			vistaModificarCliente.dlgErrorModificarSeleccionarCliente.setVisible(false);
		}
		else if (vistaModificarCliente.frmModificacionClientesDos.isActive()) 
		{
			vistaModificarCliente.frmModificacionClientesDos.setVisible(false);
		}
		else if (vistaModificarCliente.dlgErrorModificarCliente.isActive()) 
		{
			vistaModificarCliente.dlgErrorModificarCliente.setVisible(false);
		}
		else if (vistaModificarCliente.dlgClientesModificado.isActive()) 
		{
			vistaModificarCliente.dlgClientesModificado.setVisible(false);
			vistaModificarCliente.frmModificacionClientesDos.setVisible(false);
			vistaModificarCliente.frmModificarClienteUno.setVisible(false);
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
	public void cargarDatosVentana(String[] datos) {
		vistaModificarCliente.txtIdModificacionClientesDos.setText(datos[0]);
		vistaModificarCliente.txtNombreModificacionClientesDos.setText(datos[1]);
		vistaModificarCliente.txtApellidosModificacionClientesDos.setText(datos[2]);
		vistaModificarCliente.txtDniModificacionClientesDos.setText(datos[3]);
		vistaModificarCliente.txtDireccionModificacionClientesDos.setText(datos[4]);
		if (datos[5].equals("Hombre")) 
		{
			vistaModificarCliente.chkHombreDos.setState(true);
			eleccionDos = "Hombre";
		}
		else 
		{
			vistaModificarCliente.chkMujerDos.setState(true);
			eleccionDos = "Mujer";
		}
	}
	
	
}

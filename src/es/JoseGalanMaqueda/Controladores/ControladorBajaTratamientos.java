package es.JoseGalanMaqueda.Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import es.JoseGalanMaqueda.modelo.ModeloTratamientos;
import es.JoseGalanMaqueda.vistas.BajaTratamientos;

public class ControladorBajaTratamientos implements WindowListener, ActionListener 
{
	
	// ======================= ATRIBUTOS ===========================
	BajaTratamientos vista;
	ModeloTratamientos modelo;
	
	// ======================== CONTROLADOR =========================================
	public ControladorBajaTratamientos( BajaTratamientos vista, ModeloTratamientos modelo) 
	{
		this.vista = vista;
		this.modelo = modelo;
		
		// ====================== VENTANA PRINCIPAL =======================
		this.vista.frmEliminarTratamiento.addWindowListener(this);
		this.vista.btnBuscarCliente.addActionListener(this);
		this.vista.btnCancelarEliminar.addActionListener(this);
		this.vista.btnEliminar.addActionListener(this);
		this.modelo.cargarListadoTratamientos(this.vista.choListaTratamientos);
		
		// ====================== DIALOGO ERROR ============================
		this.vista.dlgErrorInsertarTratamiento.addWindowListener(this);
		
		// ====================== DIALOGO CORRECTO ===========================
		this.vista.dlgTratamientoInsertado.addWindowListener(this);
		
		// ===================== VENTANA CONFIRMACION ======================
		this.vista.frmConfirmacionEliminarTratamiento.addWindowListener(this);
		this.vista.btnSiConfirmacionElimiarTratamiento.addActionListener(this);
		this.vista.btnNoConfirmacionElimiarTratamiento.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource().equals(vista.btnCancelarEliminar)) 
		{
			vista.frmEliminarTratamiento.setVisible(false);
		}
		else if (e.getSource().equals(vista.btnBuscarCliente)) 
		{
			modelo.cargarListadoTratamientos(vista.choListaTratamientos, vista.txtBuscarTratamiento.getText());
		}
		else if (e.getSource().equals(vista.btnEliminar)) 
		{
			vista.creacionDialogoNotificacion(vista.dlgErrorInsertarTratamiento, vista.lblErrorAnadidoTratamiento);
			if (!vista.choListaTratamientos.getSelectedItem().equals("Selecciona un Tratamiento..")) 
			{
				vista.creacionVentanaConfirmacion(vista.frmConfirmacionEliminarTratamiento, vista.lblConfirmacionEliminarTratamiento, vista.btnSiConfirmacionElimiarTratamiento, vista.btnNoConfirmacionElimiarTratamiento);
			}
			else 
			{
				vista.lblErrorAnadidoTratamiento.setText("Selecciona Tratamiento Válido");
				vista.dlgErrorInsertarTratamiento.setVisible(true);
			}
		}
		else if (e.getSource().equals(vista.btnNoConfirmacionElimiarTratamiento)) 
		{
			vista.frmConfirmacionEliminarTratamiento.setVisible(false);
		}
		else if (e.getSource().equals(vista.btnSiConfirmacionElimiarTratamiento)) 
		{
			vista.creacionDialogoNotificacion(vista.dlgErrorInsertarTratamiento, vista.lblErrorAnadidoTratamiento);
			if (modelo.eliminarTratamiento(vista.choListaTratamientos)) 
			{
				vista.lblAnadidaCorrectamente.setText("Tratamiento Eliminado");
				vista.creacionDialogoNotificacion(vista.dlgTratamientoInsertado, vista.lblAnadidaCorrectamente);
				vista.dlgTratamientoInsertado.setVisible(true);
			}
			else 
			{
				vista.lblErrorAnadidoTratamiento.setText("Error al eliminar");
				vista.dlgErrorInsertarTratamiento.setVisible(true);
			}
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) 
	{
		if (vista.frmEliminarTratamiento.isActive()) 
		{
			vista.frmEliminarTratamiento.setVisible(false);
		}
		else if (vista.dlgErrorInsertarTratamiento.isActive()) 
		{
			vista.dlgErrorInsertarTratamiento.setVisible(false);
		}
		else if (vista.frmConfirmacionEliminarTratamiento.isActive()) 
		{
			vista.frmConfirmacionEliminarTratamiento.setVisible(false);
		}
		else if (vista.dlgTratamientoInsertado.isActive()) 
		{
			vista.dlgTratamientoInsertado.setVisible(false);
			vista.frmConfirmacionEliminarTratamiento.setVisible(false);
			vista.frmEliminarTratamiento.setVisible(false);
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

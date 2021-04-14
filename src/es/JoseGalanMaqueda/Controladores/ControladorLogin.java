package es.JoseGalanMaqueda.Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import es.JoseGalanMaqueda.modelo.ModeloUsuarios;
import es.JoseGalanMaqueda.vistas.Login;
import es.JoseGalanMaqueda.vistas.Principal;

public class ControladorLogin implements WindowListener, ActionListener {
	
	Login login;
	ModeloUsuarios modeloUsuarios;
	
	public ControladorLogin(Login login, ModeloUsuarios modeloUsuarios) {
		this.login = login;
		this.modeloUsuarios = modeloUsuarios;
		
		this.login.frmVentanaLogin.addWindowListener(this);
		this.login.btnAcceder.addActionListener(this);
		this.login.btnLimpiar.addActionListener(this);
		this.login.dlgError.addWindowListener(this);
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {
		
		if (this.login.frmVentanaLogin.isActive()) 
		{
			this.login.frmVentanaLogin.setVisible(false);
		}else if (this.login.dlgError.isActive()) {
			this.login.dlgError.setVisible(false);
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
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.login.btnLimpiar)) {
			this.login.txtNombreUsuario.selectAll();
			this.login.txtNombreUsuario.setText("");
			this.login.txtPassword.selectAll();
			this.login.txtPassword.setText("");
			this.login.txtNombreUsuario.requestFocus();
		}
		else if (e.getSource().equals(this.login.btnAcceder)) {
			int tipo = this.modeloUsuarios.comprobacionUsuario(this.login.txtNombreUsuario.getText(), this.login.txtPassword.getText());
			if (tipo == 1 || tipo == 0) {
				Principal principal = new Principal(tipo);
				new ControladorPrincipal(principal);
				this.login.frmVentanaLogin.setVisible(false);
			}else {
				this.login.DialogoError();
				this.login.lblError.setText("Datos Incorrectos");
			}
		}
	}

}

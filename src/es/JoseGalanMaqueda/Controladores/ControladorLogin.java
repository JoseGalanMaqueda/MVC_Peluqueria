package es.JoseGalanMaqueda.Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import es.JoseGalanMaqueda.modelo.FicheroLog;
import es.JoseGalanMaqueda.modelo.ModeloUsuarios;
import es.JoseGalanMaqueda.vistas.Login;
import es.JoseGalanMaqueda.vistas.Principal;

public class ControladorLogin implements WindowListener, ActionListener 
{

	Login login;
	ModeloUsuarios modeloUsuarios;
	public static String nombreUsuario;

	public ControladorLogin(Login login, ModeloUsuarios modeloUsuarios) 
	{
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
	public void windowClosing(WindowEvent e) 
	{

		if (login.frmVentanaLogin.isActive()) 
		{
			login.frmVentanaLogin.setVisible(false);
		}
		else if (login.dlgError.isActive()) 
		{
			login.dlgError.setVisible(false);
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
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource().equals(login.btnLimpiar)) 
		{
			login.txtNombreUsuario.selectAll();
			login.txtNombreUsuario.setText("");
			login.txtPassword.selectAll();
			login.txtPassword.setText("");
			login.txtNombreUsuario.requestFocus();
		}
		else if (e.getSource().equals(login.btnAcceder)) 
		{
			int tipo = modeloUsuarios.comprobacionUsuario(login.txtNombreUsuario.getText(), login.txtPassword.getText());
			if (tipo == 1 || tipo == 0) 
			{
				nombreUsuario = login.txtNombreUsuario.getText();
				FicheroLog.guardar(nombreUsuario, "Acceso a Aplicación");
				Principal principal = new Principal(tipo);
				new ControladorPrincipal(principal);
				login.frmVentanaLogin.setVisible(false);
			}
			else 
			{
				login.DialogoError();
				login.lblError.setText("Datos Incorrectos");
			}
		}
	}

}

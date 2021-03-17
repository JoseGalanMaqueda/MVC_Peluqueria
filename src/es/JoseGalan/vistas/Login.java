package es.JoseGalan.vistas;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

public class Login
{
	Frame frmVentanaLogin = new Frame("Login");
	Label lblnombreUsuario = new Label("Usuario:");
	Label lblpassword = new Label("Contraseña:");
	TextField txtNombreUsuario = new TextField(15);
	TextField txtPassword = new TextField(15);
	Button btnAcceder = new Button("Acceder");
	Button btnLimpiar = new Button("Limpiar");
	Panel pnlUno = new Panel();
	Panel pnlDos = new Panel();
	Panel pnlTres = new Panel();
	Panel pnlCuatro = new Panel();
	Panel pnlCinco = new Panel();
	Panel pnlSeis = new Panel();
	Color colorPrincipal = new Color(204,229,255);
	
	public Login() 
	{
		frmVentanaLogin.setLayout(new GridLayout(3,2));
		frmVentanaLogin.setSize(290,160);
		pnlUno.add(lblnombreUsuario);
		pnlDos.add(txtNombreUsuario);
		pnlTres.add(lblpassword);
		pnlCuatro.add(txtPassword);
		pnlCinco.add(btnAcceder);
		pnlSeis.add(btnLimpiar);
		frmVentanaLogin.add(pnlUno);
		frmVentanaLogin.add(pnlDos);
		frmVentanaLogin.add(pnlTres);
		txtPassword.setEchoChar('*');
		frmVentanaLogin.add(pnlCuatro);
		btnAcceder.setBackground(Color.WHITE);
		frmVentanaLogin.add(pnlCinco);
		btnLimpiar.setBackground(Color.WHITE);
		frmVentanaLogin.add(pnlSeis);
		frmVentanaLogin.setBackground(colorPrincipal);
		frmVentanaLogin.setResizable(false);
		frmVentanaLogin.setLocationRelativeTo(null);
		frmVentanaLogin.setVisible(true);
	}
	
}

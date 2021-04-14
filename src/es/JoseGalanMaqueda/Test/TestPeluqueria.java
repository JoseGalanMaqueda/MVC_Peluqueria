package es.JoseGalanMaqueda.Test;

import es.JoseGalanMaqueda.Controladores.ControladorLogin;
import es.JoseGalanMaqueda.modelo.ModeloUsuarios;
import es.JoseGalanMaqueda.vistas.Login;

public class TestPeluqueria
{

	public static void main(String[] args)
	{
		Login login = new Login();
		ModeloUsuarios modeloUsuarios = new ModeloUsuarios();
		new ControladorLogin(login, modeloUsuarios);
	}

}

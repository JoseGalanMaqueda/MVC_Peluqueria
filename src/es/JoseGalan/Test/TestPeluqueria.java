package es.JoseGalan.Test;

import es.JoseGalan.vistas.Login;
import es.JoseGalanMaqueda.modelo.ModeloUsuarios;

public class TestPeluqueria
{

	public static void main(String[] args)
	{
		Login login = new Login();
		ModeloUsuarios modeloUsuarios = new ModeloUsuarios();
		new ControladorUsuarios(login, modeloUsuarios);
	}

}

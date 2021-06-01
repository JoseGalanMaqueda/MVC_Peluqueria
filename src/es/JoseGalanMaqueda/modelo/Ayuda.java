package es.JoseGalanMaqueda.modelo;

import java.io.IOException;

public class Ayuda 
{

	public static void ejecutarAyuda() 
	{
		try
		{
			Runtime.getRuntime().exec("hh.exe //Ayuda//AyudaGestion.chm");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}

package es.JoseGalanMaqueda.vistas;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextArea;

public class Principal
{
	//===================================== VENTANA PRINCIPAL ==================================
	public Frame ventanaPrincipal = new Frame("Peluqueria Forever Young");
	Label lblProximasCitas = new Label("Proximas Citas:");
	TextArea txaCitasHoy = new TextArea(16,54);
	public Button btnActualizar = new Button("Actualizar Citas");
	Color clFondo = new Color(204,229,255);
	public static int tipo;

	//===================================== MENU PRINCIPAL =====================================
	MenuBar menuPrincipal = new MenuBar();

	// ==================================== MENU CLIENTES ======================================
	Menu mnuCliente = new Menu("Clientes");
	public MenuItem mniAltaCliente = new MenuItem("Nuevo Cliente");
	public MenuItem mniBajaCliente = new MenuItem("Eliminar Cliente");
	public MenuItem mniModificacionCliente = new MenuItem("Editar Cliente");
	public MenuItem mniConsultaCliente = new MenuItem("Consultar Clientes");

	// =================================== MENU TRATAMIENTOS ===================================
	Menu mnuTratamientos = new Menu("Tratamientos");
	public MenuItem mniAltaTratamiento = new MenuItem("Nuevo Tratamientos");
	public MenuItem mniBajaTratamiento = new MenuItem("Eliminar Tratamiento");
	public MenuItem mniModificacionTratamiento = new MenuItem("Editar Tratamiento");
	public MenuItem mniConsultaTratamientos = new MenuItem("Consultar Tratamiento");

	// =================================== MENU CITAS ==========================================
	Menu mnuCitas = new Menu("Citas");
	public MenuItem mniAltaCitas = new MenuItem("Nueva Cita");
	public MenuItem mniConsultaCitas = new MenuItem("Consultar Citas");

	// ================================== MENU ASIGNAR TRATAMIENTOS ===================================
	Menu mnuAsignarTratamientos = new Menu("Asignacion Tratamientos");
	public MenuItem mniConsultarAsignacion = new MenuItem("Consultar Asignaciones");

	public Principal(int tipo) {
		ventanaPrincipal.setSize(450, 400);
		ventanaPrincipal.setLayout(new FlowLayout());
		Principal.tipo = tipo; // Para tener el usuario que ha accedido a nuestra aplicacion

		mnuCliente.add(mniAltaCliente);
		if (tipo == 0) {
			mnuCliente.add(mniBajaCliente);
			mnuCliente.add(mniModificacionCliente);
			mnuCliente.add(mniConsultaCliente);
		}
		menuPrincipal.add(mnuCliente);

		mnuTratamientos.add(mniAltaTratamiento);
		if (tipo == 0) {
			mnuTratamientos.add(mniBajaTratamiento);
			mnuTratamientos.add(mniModificacionTratamiento);
			mnuTratamientos.add(mniConsultaTratamientos);
		}
		menuPrincipal.add(mnuTratamientos);

		mnuCitas.add(mniAltaCitas);
		if (tipo == 0) {
			mnuCitas.add(mniConsultaCitas);
		}
		menuPrincipal.add(mnuCitas);

		if (tipo == 0) {
			mnuAsignarTratamientos.add(mniConsultarAsignacion);
			menuPrincipal.add(mnuAsignarTratamientos);
		}


		ventanaPrincipal.setMenuBar(menuPrincipal);
		ventanaPrincipal.add(lblProximasCitas);
		txaCitasHoy.setEditable(false);
		txaCitasHoy.setBackground(Color.WHITE);
		ventanaPrincipal.add(txaCitasHoy);
		btnActualizar.setBackground(Color.WHITE);
		ventanaPrincipal.add(btnActualizar);
		ventanaPrincipal.setBackground(clFondo);
		ventanaPrincipal.setLocationRelativeTo(null);
		ventanaPrincipal.setResizable(false);
		ventanaPrincipal.setVisible(true);
	}
}

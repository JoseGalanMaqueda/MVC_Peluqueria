package es.JoseGalanMaqueda.vistas;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;

public class ConsultaClientes {

	// ==================================== VENTANA PRINCIPAL ===================================
	public Frame frmConsulaClientes = new Frame("Consulta Clientes");
	Label lblInformacionClientes = new Label("Información Clientes:");
	public TextArea txaConsultaClientes = new TextArea(15,50);
	public Button btnExportarPdfClientes = new Button("Exportar a PDF");
	public Button btnCancelarConsultaClientes = new Button("Cancelar");
	Color clFondo = new Color(204,229,255);
	
	
	public ConsultaClientes() 
	{
		frmConsulaClientes.setLayout(new FlowLayout());
		frmConsulaClientes.setSize(450, 350);
		frmConsulaClientes.setBackground(clFondo);
		frmConsulaClientes.add(lblInformacionClientes);
		txaConsultaClientes.setEditable(false);
		txaConsultaClientes.setBackground(Color.WHITE);
		frmConsulaClientes.add(txaConsultaClientes);
		btnExportarPdfClientes.setBackground(Color.WHITE);
		frmConsulaClientes.add(btnExportarPdfClientes);
		btnCancelarConsultaClientes.setBackground(Color.WHITE);
		frmConsulaClientes.add(btnCancelarConsultaClientes);
		frmConsulaClientes.setResizable(false);
		frmConsulaClientes.setLocationRelativeTo(null);
		frmConsulaClientes.setVisible(true);
	}
	
}

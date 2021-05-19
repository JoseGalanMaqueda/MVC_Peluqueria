package es.JoseGalanMaqueda.vistas;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;

public class ConsultarCitas 
{
	// ==================================== VENTANA PRINCIPAL ===================================
	public Frame frmConsulaCitas = new Frame("Consulta Citas");
	Label lblInformacionCitas = new Label("Información Citas:");
	public TextArea txaConsultaCitas = new TextArea(15,50);
	public Button btnExportarPdfCitas = new Button("Exportar a PDF");
	public Button btnCancelarConsultaCitas = new Button("Cancelar");
	Color clFondo = new Color(204,229,255);
	
	public ConsultarCitas() 
	{
		frmConsulaCitas.setLayout(new FlowLayout());
		frmConsulaCitas.setSize(500, 350);
		frmConsulaCitas.setBackground(clFondo);
		frmConsulaCitas.add(lblInformacionCitas);
		txaConsultaCitas.setEditable(false);
		txaConsultaCitas.setBackground(Color.WHITE);
		frmConsulaCitas.add(txaConsultaCitas);
		btnExportarPdfCitas.setBackground(Color.WHITE);
		frmConsulaCitas.add(btnExportarPdfCitas);
		btnCancelarConsultaCitas.setBackground(Color.WHITE);
		frmConsulaCitas.add(btnCancelarConsultaCitas);
		frmConsulaCitas.setResizable(false);
		frmConsulaCitas.setLocationRelativeTo(null);
		frmConsulaCitas.setVisible(true);
	}
	
}

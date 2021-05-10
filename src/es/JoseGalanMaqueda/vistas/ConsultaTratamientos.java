package es.JoseGalanMaqueda.vistas;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;

public class ConsultaTratamientos 
{
	// ==================================== VENTANA PRINCIPAL ===================================
	public Frame frmConsulaTratamientos = new Frame("Consulta Tratamientos");
	Label lblInformacionTratamientos = new Label("Información Tratamientos:");
	public TextArea txaConsultaTratamientos = new TextArea(15,50);
	public Button btnExportarPdfTratamientos = new Button("Exportar a PDF");
	public Button btnCancelarConsultaTratamientos = new Button("Cancelar");
	Color clFondo = new Color(204,229,255);
	
	public ConsultaTratamientos() 
	{
		frmConsulaTratamientos.setLayout(new FlowLayout());
		frmConsulaTratamientos.setSize(500, 350);
		frmConsulaTratamientos.setBackground(clFondo);
		frmConsulaTratamientos.add(lblInformacionTratamientos);
		txaConsultaTratamientos.setEditable(false);
		txaConsultaTratamientos.setBackground(Color.WHITE);
		
		frmConsulaTratamientos.add(txaConsultaTratamientos);
		btnExportarPdfTratamientos.setBackground(Color.WHITE);
		frmConsulaTratamientos.add(btnExportarPdfTratamientos);
		btnCancelarConsultaTratamientos.setBackground(Color.WHITE);
		frmConsulaTratamientos.add(btnCancelarConsultaTratamientos);
		frmConsulaTratamientos.setResizable(false);
		frmConsulaTratamientos.setLocationRelativeTo(null);
		frmConsulaTratamientos.setVisible(true);
	}
	
}

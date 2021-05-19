package es.JoseGalanMaqueda.vistas;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;

public class ConsultarAsignaciones 
{
	// ==================================== VENTANA PRINCIPAL ===================================
	public Frame frmConsultaAsignaciones = new Frame("Consulta Asignaciones Tratamientos a Citas");
	Label lblInformacionAsignaciones = new Label("Información Asignaciones Tratamientos a Citas:");
	public TextArea txaConsultaAsignaciones = new TextArea(15,50);
	public Button btnExportarPdfAsignaciones = new Button("Exportar a PDF");
	public Button btnCancelarConsultaAsignaciones = new Button("Cancelar");
	Color clFondo = new Color(204,229,255);

	public ConsultarAsignaciones() 
	{
		frmConsultaAsignaciones.setLayout(new FlowLayout());
		frmConsultaAsignaciones.setSize(500, 350);
		frmConsultaAsignaciones.setBackground(clFondo);
		frmConsultaAsignaciones.add(lblInformacionAsignaciones);
		txaConsultaAsignaciones.setEditable(false);
		txaConsultaAsignaciones.setBackground(Color.WHITE);
		frmConsultaAsignaciones.add(txaConsultaAsignaciones);
		btnExportarPdfAsignaciones.setBackground(Color.WHITE);
		frmConsultaAsignaciones.add(btnExportarPdfAsignaciones);
		btnCancelarConsultaAsignaciones.setBackground(Color.WHITE);
		frmConsultaAsignaciones.add(btnCancelarConsultaAsignaciones);
		frmConsultaAsignaciones.setResizable(false);
		frmConsultaAsignaciones.setLocationRelativeTo(null);
		frmConsultaAsignaciones.setVisible(true);
	}

}

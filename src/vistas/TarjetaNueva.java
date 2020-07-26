package vistas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import funciones.DAOTarjeta;
import modelos.Tarjeta;

public class TarjetaNueva extends JDialog implements ActionListener{
	JTextField tId, tTipo;
	JTextArea tDescripcion;
	JButton bAceptar;
	Tarjeta tarjeta;
	public TarjetaNueva(TarjetaFrame tarjetaFrame) {
		super(tarjetaFrame, true);
		this.setTitle("Crear Tarjeta");
		this.setSize(300, 370);
		this.setLocation(200, 100);
		this.getContentPane().add(crearPanelVentana(), BorderLayout.CENTER);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private Component crearPanelVentana() {
		JPanel panel = new JPanel();
		JLabel lId = new JLabel("Identificador");
		JLabel lDescripcion = new JLabel("Descripción");
		JLabel lTipo =  new JLabel("Tipo");
		tId = new JTextField(20);
		tDescripcion = new JTextArea(10,10);
		tDescripcion.setMaximumSize(new Dimension(10, 10));
		tTipo = new JTextField(20);
		bAceptar = new JButton("Aceptar");
		bAceptar.setActionCommand("aceptar");
		bAceptar.addActionListener(this);
		bAceptar.setHorizontalAlignment(SwingConstants.CENTER);
		bAceptar.setVerticalAlignment(SwingConstants.BOTTOM);
		panel.add(lId);
		panel.add(tId);
		panel.add(lDescripcion);
		panel.add(tDescripcion);
		panel.add(Box.createVerticalGlue());
		panel.add(lTipo);
		panel.add(tTipo);
		panel.add(bAceptar);
		return panel;
	}
	
	

	public Tarjeta getTarjeta() {
		return tarjeta;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("aceptar")) {
			try {
				
				tarjeta =  new Tarjeta(Integer.parseInt(tId.getText()), tTipo.getText(), tDescripcion.getText());
				DAOTarjeta.añadirTarjeta(tarjeta);
		
			} catch (NumberFormatException | SQLException e1) {
				JOptionPane.showMessageDialog(this, "Rellene el ID con un dato numérico", "Error: Campo incorrecto", JOptionPane.ERROR_MESSAGE);
			}
			this.dispose();
		}
	}
}

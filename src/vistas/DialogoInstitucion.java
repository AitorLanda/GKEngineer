package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import controladores.Excepciones;
import funciones.DAOInstitucion;
import modelos.Institucion;

public class DialogoInstitucion extends JDialog implements ActionListener {

	private JTextField identificador;
	private JTextField nombre;

	private JPanel panelSeparador;
	private JScrollPane sPanel;
	JList<String> listaPisos;

	Institucion institucion;

	private JFrame panelAnterior;

	public DialogoInstitucion(JFrame panelAnterior) {
		super(panelAnterior, "Añadir Institución", true);

		this.panelAnterior = panelAnterior;

		this.setSize(550, 500);

		this.setLocation(200, 50);
		this.setContentPane(crearPanelVentana());
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private Container crearPanelVentana() {
		JPanel panel = new JPanel(new BorderLayout(0, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		panel.add(crearPanelScroll(), BorderLayout.CENTER);
		panel.add(crearPanelBotones(), BorderLayout.SOUTH);
		return panel;
	}

	private Component crearPanelScroll() {
		sPanel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sPanel.add(crearPanelSeparador());
		sPanel.setViewportView(panelSeparador);
		return sPanel;
	}

	private Component crearPanelSeparador() {
		panelSeparador = new JPanel(new BorderLayout(10, 0));

		panelSeparador.add(crearPanelDatos(), BorderLayout.CENTER);

		return panelSeparador;
	}

	private Component crearPanelDatos() {
		JPanel panel = new JPanel(new GridLayout(12, 1));

		identificador = new JTextField(20);
		nombre = new JTextField(20);

		panel.add(crearComponentBorder(identificador, "Identificador"));
		panel.add(crearComponentBorder(nombre, "Nombre Programa"));

		return panel;
	}

	private Component crearComponentBorder(Component comp, String titulo) {
		JPanel panel = new JPanel(new GridLayout(1, 1));
		panel.setBorder(new TitledBorder(null, titulo, TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null,
				new Color(Color.TRANSLUCENT)));
		panel.add(comp);
		return panel;
	}

	private Component crearPanelBotones() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 20, 0));
		JButton bOk = new JButton("OK");
		bOk.setActionCommand("ok");
		bOk.addActionListener(this);

		JButton bCancel = new JButton("Cancelar");
		bCancel.setActionCommand("cancel");
		bCancel.addActionListener(this);

		panel.add(bOk);
		panel.add(bCancel);
		return panel;
	}

	private void comprobacionDeDatos() throws Excepciones.HayCamposIncompletos, Excepciones.UsernameRepetido,
			Excepciones.UsernameDeFisioIncorrecto {
		if (nombre.getText().isEmpty())
			throw new Excepciones.HayCamposIncompletos();
		if (identificador.getText().isEmpty())
			throw new Excepciones.HayCamposIncompletos();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("ok")) {
			try {
				comprobacionDeDatos();
				Institucion institucio = new Institucion(Integer.parseInt(identificador.getText()), nombre.getText());
				DAOInstitucion.añadirInstitucion(institucio);
				panelAnterior.setVisible(true);
				this.dispose();
			} catch (Excepciones.HayCamposIncompletos e1) {
				JOptionPane.showMessageDialog(this, "Rellene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
			} catch (Excepciones.UsernameRepetido e1) {
				JOptionPane.showMessageDialog(this, "El username escrito ya está en uso, cámbielo", "Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (Excepciones.UsernameDeFisioIncorrecto e1) {
				JOptionPane.showMessageDialog(this, "El username del fisioterapeuta es incorrecto, cámbielo", "Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		if (e.getActionCommand().equals("cancel")) {
			panelAnterior.setVisible(true);
			dispose();
		}
	}

}

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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import controladores.Excepciones;
import funciones.DAOPiso;
import modelos.Piso;

public class DialogoPiso extends JDialog implements ActionListener {

	private JTextField identificador;
	private JTextField nombrePrograma;
	private JTextField direccion;
	private JTextField telefono;
	private JTextField aforo;
	private JComboBox<String> tipo;
	private JTextField intensidad;
	private JTextField suvencion;

	private JPanel panelSeparador;
	private JScrollPane sPanel;
	JList<String> listaPisos;

	Piso piso;

	private JFrame panelAnterior;

	public DialogoPiso(JFrame panelAnterior) {
		super(panelAnterior, "Añadir Piso", true);

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
		nombrePrograma = new JTextField(20);
		aforo = new JTextField(20);
		intensidad = new JTextField(20);
		suvencion = new JTextField(20);
		direccion = new JTextField(20);
		telefono = new JTextField(20);
		String tip[] = { "Solo Mujeres", "Mixtos", "Solo Hombres" };
		tipo = new JComboBox<>(tip);
		tipo.setSelectedItem(null);

		panel.add(crearComponentBorder(identificador, "Identificador"));
		panel.add(crearComponentBorder(nombrePrograma, "Nombre Programa"));
		panel.add(crearComponentBorder(aforo, "Aforo Maximo"));
		panel.add(crearComponentBorder(tipo, "Tipo de piso"));
		panel.add(crearComponentBorder(intensidad, "Intesidad"));
		panel.add(crearComponentBorder(direccion, "Direccion del Piso"));
		panel.add(crearComponentBorder(suvencion, "Sunvenciones"));
		panel.add(crearComponentBorder(telefono, "Teléfono"));

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
		if (nombrePrograma.getText().isEmpty())
			throw new Excepciones.HayCamposIncompletos();
		if (direccion.getText().isEmpty())
			throw new Excepciones.HayCamposIncompletos();
		if (identificador.getText().isEmpty())
			throw new Excepciones.HayCamposIncompletos();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("ok")) {
			try {
				comprobacionDeDatos();
				Piso pisoo = new Piso(Integer.parseInt(identificador.getText()), nombrePrograma.getText(),
						direccion.getText(), Integer.parseInt(aforo.getText()), (String) tipo.getSelectedItem(),
						intensidad.getText(), (long) Integer.parseInt(telefono.getText()),
						Integer.parseInt(suvencion.getText()));
				DAOPiso.añadirPiso(pisoo);
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

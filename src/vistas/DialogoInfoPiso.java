package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import PatronesDeDiseño.EstadoAmarillo;
import PatronesDeDiseño.EstadoRojo;
import PatronesDeDiseño.EstadoVerde;
import PatronesDeDiseño.PisoEsta;
import modelos.Piso;

public class DialogoInfoPiso extends JDialog implements ActionListener {

	private JLabel identificador;
	private JLabel nomPrograma;
	private JLabel tipo;
	private JLabel aforo;
	private JLabel intensidad;
	private JLabel direccion;
	private JLabel telefono;
	private JLabel subven;
	private JLabel Cuadrada;

	private JPanel panelSeparador;
	private JScrollPane sPanel;

	Piso piso;
	private JDialog panelAnterior;

	public DialogoInfoPiso(JDialog panelAnterior, Piso piso) {
		super(panelAnterior, "INFORMACION DEL TRABAJADOR", true);

		this.panelAnterior = panelAnterior;
		this.piso = piso;

		this.setSize(780, 600);

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

		panelSeparador.add(crearPanelDatos());

		return panelSeparador;
	}

	private Component crearPanelDatos() {
		JPanel panel = new JPanel(new GridLayout(12, 1));

		identificador = new JLabel(String.valueOf(this.piso.getId()));
		nomPrograma = new JLabel(this.piso.getNomPrograma());
		tipo = new JLabel(this.piso.getTipo());
		intensidad = new JLabel(this.piso.getIntensidad());
		direccion = new JLabel(this.piso.getDireccion());
		telefono = new JLabel(String.valueOf(this.piso.getTelefono()));
		aforo = new JLabel(String.valueOf(this.piso.getAforo()));
		subven = new JLabel(String.valueOf(this.piso.getSubvencion()));
		// Crea el cuadrado que cambiara de color
		Cuadrada = new JLabel();
		// Añade las medidas al cuadrado
		Cuadrada.setPreferredSize(new Dimension(2, 2));
		// Añade color al cuadrado
		setBackColor();

		panel.add(crearComponentBorder(identificador, "Identificador"));
		panel.add(crearComponentBorder(nomPrograma, "Nombre del programa al que pertonece"));
		panel.add(crearComponentBorder(tipo, "Tipo de piso"));
		panel.add(crearComponentBorder(intensidad, "Intensidad"));
		panel.add(crearComponentBorder(aforo, "Aforo del piso"));
		panel.add(crearComponentBorder(direccion, "Domicilio"));
		panel.add(crearComponentBorder(telefono, "Teléfono"));
		panel.add(crearComponentBorder(subven, "Subvencion recibida"));
		panel.add(crearComponentBorder(Cuadrada, "Estado de ocupacion del piso"));

		return panel;
	}

	// Funcion que usa State
	public void setBackColor() {
		PisoEsta objPiso = new PisoEsta();
		// Comprueba la cantidad de personas que viven en el piso
		if (this.piso.getInquilino().size() == 0) {
			// Añade el estado
			objPiso.setEstado(new EstadoVerde());
			// Añade el color al Cuadrado
			Cuadrada = objPiso.mostrar(Cuadrada);
		} else if (this.piso.getInquilino().size() > 0 && (this.piso.getInquilino().size() < this.piso.getAforo())) {
			// Añade el estado
			objPiso.setEstado(new EstadoAmarillo());
			// Añade el color al Cuadrado
			Cuadrada = objPiso.mostrar(Cuadrada);
		} else if (this.piso.getInquilino().size() == this.piso.getAforo()) {
			// Añade el estado
			objPiso.setEstado(new EstadoRojo());
			// Añade el color al Cuadrado
			Cuadrada = objPiso.mostrar(Cuadrada);
		}
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

		JButton bClose = new JButton("Cerrar");
		bClose.setActionCommand("close");
		bClose.addActionListener(this);

		panel.add(bClose);
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("close")) {
			this.dispose();
			panelAnterior.setVisible(true);
		}
	}
}

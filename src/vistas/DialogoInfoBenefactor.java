package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import modelos.Beneficiario;

public class DialogoInfoBenefactor extends JDialog implements ActionListener {

	private JLabel identificador;
	private JLabel nombre;
	private JLabel apellido1;
	private JLabel apellido2;
	private JLabel fnac;
	private JLabel nacionalidad;
	private JLabel documentoIdentidad;
	private JLabel direccion;
	private JLabel telefono;
	private JLabel genero;
	private JLabel divFuncional;
	private JLabel fLlegada;
	private JLabel fAlta;
	private JLabel fBaja;
	private JLabel fCaducidad;
	private JLabel comentarios;
	private JLabel estadoCivil;

	private JPanel panelSeparador;
	private JScrollPane sPanel;
	JList<String> listaFecha;

	Beneficiario beneficiario;
	private JDialog panelAnterior;
	// private JComboBox<String> tipoSueldo;

	public DialogoInfoBenefactor(JDialog panelAnterior, Beneficiario beneficiario) {
		super(panelAnterior, "INFORMACION DEL BENEFICIARIO", true);

		this.panelAnterior = panelAnterior;
		this.beneficiario = beneficiario;

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

		identificador = new JLabel(String.valueOf(this.beneficiario.getId()));
		nombre = new JLabel(this.beneficiario.getNombre());
		apellido1 = new JLabel(this.beneficiario.getApellido1());
		apellido2 = new JLabel(this.beneficiario.getApellido2());
		fnac = new JLabel(this.beneficiario.getFnac());
		nacionalidad = new JLabel(this.beneficiario.getNacionalidad());
		direccion = new JLabel(this.beneficiario.getDireccion());
		documentoIdentidad = new JLabel(this.beneficiario.getDocumentoIdentidad());
		telefono = new JLabel(String.valueOf(this.beneficiario.getTelefono()));
		genero = new JLabel(String.valueOf(this.beneficiario.getGenero()));
		divFuncional = new JLabel(this.beneficiario.getDivFuncional());
		fLlegada = new JLabel(this.beneficiario.getfLlegada());
		fAlta = new JLabel(this.beneficiario.getfAlta());
		fBaja = new JLabel(this.beneficiario.getfBaja());

		fCaducidad = new JLabel(this.beneficiario.getfCaducidad());
		comentarios = new JLabel(this.beneficiario.getComentarios());
		estadoCivil = new JLabel(this.beneficiario.getEstadoCivil());

		panel.add(crearComponentBorder(identificador, "Identificador"));
		panel.add(crearComponentBorder(nombre, "Nombre"));
		panel.add(crearComponentBorder(apellido1, "Primer Apellido"));
		panel.add(crearComponentBorder(apellido2, "Segundo Apellido"));
		panel.add(crearComponentBorder(genero, "Genero"));
		panel.add(crearComponentBorder(fnac, "Fecha de nacimiento"));
		panel.add(crearComponentBorder(direccion, "Domicilio"));
		panel.add(crearComponentBorder(nacionalidad, "Nacionalidad"));
		panel.add(crearComponentBorder(documentoIdentidad, "Documento de identidad"));
		panel.add(crearComponentBorder(telefono, "Teléfono"));
		panel.add(crearComponentBorder(divFuncional, "Diversidad Funcional"));
		panel.add(crearComponentBorder(fLlegada, "Fecha de llegada"));
		panel.add(crearComponentBorder(fAlta, "Fecha de Alta"));
		panel.add(crearComponentBorder(fBaja, "Fecha de Baja"));
		panel.add(crearComponentBorder(fCaducidad, "Fecha de caducidad"));
		panel.add(crearComponentBorder(comentarios, "Comentarios"));
		panel.add(crearComponentBorder(estadoCivil, "Estado Civil"));

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

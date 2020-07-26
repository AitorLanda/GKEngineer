package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import PatronesDeDiseño.BrutoAnual;
import PatronesDeDiseño.BrutoMensual;
import PatronesDeDiseño.NetoAnual;
import PatronesDeDiseño.NetoMensual;
import modelos.Empleado;
import modelos.Trabajador;

public class DialogoInfoTrabajador extends JDialog implements ActionListener, ItemListener {

	private JLabel identificador;
	private JLabel nombre;
	private JLabel apellido1;
	private JLabel apellido2;
	private JLabel fnac;
	private JLabel nacionalidad;
	private JLabel documentoIdentidad;
	private JLabel direccion;
	private JLabel telefono;
	private JLabel puesto;
	private JLabel email;
	private JLabel genero;
	private JLabel username;
	private JLabel password;

	private JPanel panelSeparador;
	private JScrollPane sPanel;
	JList<String> listaFecha;
	JLabel lSueldo;
	float sueldo;

	Trabajador trabajador;
	private JDialog panelAnterior;
	JComboBox<String> tipoSueldo;
	
	String [] lsa ={"Bruto Mensual","Bruto Anual", "Neto Mensual", "Neto Anual"};

	public DialogoInfoTrabajador(JDialog panelAnterior, Trabajador trabajador) {
		super(panelAnterior, "INFORMACION DEL TRABAJADOR", true);

		this.panelAnterior = panelAnterior;
		this.trabajador = trabajador;

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
		
		if (trabajador instanceof Empleado)
			sueldo = ((Empleado) trabajador).getSalario();
		identificador = new JLabel(String.valueOf(this.trabajador.getId()));
		nombre = new JLabel(this.trabajador.getNombre());
		apellido1 = new JLabel(this.trabajador.getApellido1());
		apellido2 = new JLabel(this.trabajador.getApellido2());
		fnac = new JLabel(this.trabajador.getFnac());
		nacionalidad = new JLabel(this.trabajador.getNacionalidad());
		direccion = new JLabel(this.trabajador.getDireccion());
		documentoIdentidad = new JLabel(this.trabajador.getDocumentoIdentidad());
		telefono = new JLabel(String.valueOf(this.trabajador.getTelefono()));
		genero = new JLabel(String.valueOf(this.trabajador.getGenero()));
		email = new JLabel(this.trabajador.getEmail());
		puesto = new JLabel(this.trabajador.getPuesto());
		username = new JLabel(this.trabajador.getUsername());
		lSueldo = new JLabel(String.valueOf(this.sueldo));
		password = new JLabel(this.trabajador.getPassword());
		tipoSueldo = new JComboBox<String>(lsa);
		tipoSueldo.addItemListener(this);
		tipoSueldo.setSelectedIndex(0);

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
		panel.add(crearComponentBorder(email, "Email"));
		panel.add(crearComponentBorder(puesto, "Puesto"));
		panel.add(crearComponentBorder(tipoSueldo, "Tipo de vista de sueldo"));
		panel.add(crearComponentBorder(lSueldo, "Sueldo"));
		panel.add(crearComponentBorder(username, "Nombre de usuario"));
		panel.add(crearComponentBorder(password, "Contraseña"));

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

	@Override
	public void itemStateChanged(ItemEvent e) {
		float sueldo = 0;
		if (tipoSueldo.getSelectedItem().equals("Bruto Mensual")) {
			BrutoMensual bM = new BrutoMensual();
			sueldo = bM.Operacion(this.sueldo);

		} else if (tipoSueldo.getSelectedItem().equals("Neto Mensual")) {
			NetoMensual nM = new NetoMensual();
			sueldo = nM.Operacion(this.sueldo);

		} else if (tipoSueldo.getSelectedItem().equals("Bruto Anual")) {
			BrutoAnual bA = new BrutoAnual();
			sueldo = bA.Operacion(this.sueldo);

		} else if (tipoSueldo.getSelectedItem().equals("Neto Anual")) {
			NetoAnual nA = new NetoAnual();
			sueldo = nA.Operacion(this.sueldo);

		}
		lSueldo.setText(String.valueOf(sueldo));
		sPanel.revalidate();
	}

}

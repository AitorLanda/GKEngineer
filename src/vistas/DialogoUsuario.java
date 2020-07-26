package vistas;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.TitledBorder;

import controladores.Excepciones;
import funciones.DAOBeneficiario;
import funciones.DAOEmpleado;
import modelos.Beneficiario;
import modelos.Empleado;
import modelos.Persona;
import modelos.Trabajador;

public class DialogoUsuario extends JDialog implements ActionListener, ItemListener {

	private String tipoUsuario;
	
	private JTextField identificador;
	private JTextField nombre;
	private JTextField apellido1;
	private JTextField apellido2;
	private JTextField fnac;
	private JTextField nacionalidad;
	private JTextField documentoIdentidad;
	private JTextArea formacion;
	private JTextField direccion;
	private JTextField telefono;
	private Checkbox divFuncional;
	private JTextField diversidadFuncional;
	private JTextField fLlegada;
	private JTextField fCaducidad;
	private JTextArea comentarios;
	private JComboBox<String> estadoCivil;
	private JTextField puesto;
	private JTextField email;
	private JComboBox<Character>genero;
	
	private JTextField username;
	private JPasswordField password;

	private JPanel panelSeparador;
	private JPanel panelBeneficiario;
	private JScrollPane sPanel;
	JList<String>listaFecha;

	Persona persona;
	JRadioButton rb1, rb2;
	private ListModel<String> model;

	private JPanel panelTrabajador;
	private JFrame panelAnterior;
	private JComboBox<String> tipoTrabajador;
	
	public DialogoUsuario(JFrame panelAnterior, Persona person, String tipoTrabajador) {
		super(panelAnterior, "Añadir Persona", true);
		
		this.panelAnterior = panelAnterior;
		this.tipoUsuario = tipoTrabajador;
		this.persona = person;

		this.setSize(550, 500);

		this.setLocation(200, 50);
		this.setContentPane(crearPanelVentana());
		this.setResizable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private Container crearPanelVentana() {
		JPanel panel = new JPanel(new BorderLayout(0, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		panel.add(crearPanelSeleccion(), BorderLayout.NORTH);
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

	private Component crearPanelSeleccion() {
		JPanel panel = new JPanel(new GridLayout(1, 4));
		rb1 = new JRadioButton("Trabajador");
		rb2 = new JRadioButton("Beneficiario");
		ButtonGroup grupo = new ButtonGroup();
		rb1.addActionListener(this);
		rb2.addActionListener(this);
		rb2.setSelected(true);
		grupo.add(rb1);
		grupo.add(rb2);
		panel.add(Box.createHorizontalGlue());
		panel.add(rb1);
		panel.add(rb2);
		panel.add(Box.createHorizontalGlue());
		return panel;
	}

	private Component crearPanelSeparador() {
		panelSeparador = new JPanel(new BorderLayout(10, 0));

		panelSeparador.add(crearPanelDatos(), BorderLayout.CENTER);
		panelSeparador.add(crearPanelBeneficiario(), BorderLayout.EAST);
		
		return panelSeparador;
	}

	private Component crearPanelDatos() {
		JPanel panel = new JPanel(new GridLayout(12, 1));
		
		identificador = new JTextField(20);
		nombre = new JTextField(20);
		apellido1 = new JTextField(20);
		apellido2 = new JTextField(20);
		fnac = new JTextField(20);
		nacionalidad =  new JTextField(20);
		direccion = new JTextField(20);
		documentoIdentidad = new JTextField(20);
		telefono = new JTextField(20);
		formacion = new JTextArea();
		Character[] gen= {'F', 'M', 'X'};
		genero = new JComboBox<>(gen);
		genero.setSelectedItem(null);
		
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
		panel.add(crearComponentBorder(formacion, "Formación"));
		
		return panel;
	}

	private Component crearPanelBeneficiario() {
		panelBeneficiario = new JPanel(new GridLayout(10, 1));
		divFuncional = new Checkbox("Diversidad funcional", false);
		comentarios = new JTextArea();
		diversidadFuncional = new JTextField(20);
		fCaducidad = new JTextField(20);
		estadoCivil =  new JComboBox<>(Beneficiario.estados);
		fLlegada = new JTextField(20);
		estadoCivil.addActionListener(this);
		estadoCivil.setSelectedItem(null);
		diversidadFuncional.setEditable(false);
		divFuncional.addItemListener(this);
		
		panelBeneficiario.add(divFuncional);
		panelBeneficiario.add(crearComponentBorder(diversidadFuncional, "Descripción de Diversidad Funcional"));
		panelBeneficiario.add(crearComponentBorder(fLlegada, "Fecha de llegada a España"));
		panelBeneficiario.add(crearComponentBorder(estadoCivil, "Estado Civil"));
		panelBeneficiario.add(crearComponentBorder(fCaducidad, "Fecha Cad. Banco de Alimentos"));
		panelBeneficiario.add(crearComponentBorder(comentarios, "Comentarios"));
		return panelBeneficiario;
	}
	
	private Component crearPanelTrabajador() {
		panelTrabajador = new JPanel (new GridLayout(10, 1));
		email = new JTextField(20);
		puesto = new JTextField(20);
		username = new JTextField(20);
		password  = new JPasswordField();
		tipoTrabajador = new JComboBox<>(Trabajador.tipos);
		tipoTrabajador.addActionListener(this);
		tipoTrabajador.setSelectedItem(null);
		
		panelTrabajador.add(crearComponentBorder(email, "Email"));
		panelTrabajador.add(crearComponentBorder(puesto, "Puesto"));
		panelTrabajador.add(crearComponentBorder(tipoTrabajador, "Tipo de Trabajador"));
		panelTrabajador.add(crearComponentBorder(username, "Nombre de usuario"));
		panelTrabajador.add(crearComponentBorder(password, "Contraseña"));
		return panelTrabajador;
	}

	private Component crearComponentBorder(Component comp, String titulo) {
		JPanel panel = new JPanel(new GridLayout(1, 1));
		panel.setBorder(new TitledBorder(null, titulo, TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, new Color(Color.TRANSLUCENT)));
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
		if (apellido1.getText().isEmpty())
			throw new Excepciones.HayCamposIncompletos();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("ok")) {
			try {
				comprobacionDeDatos();
				if(rb1.isSelected())
					tipoUsuario=rb1.getText();
				else
					tipoUsuario=rb2.getText();
				
				switch (tipoUsuario) {
				case "Trabajador":
					persona = new Empleado(Integer.parseInt(identificador.getText()), nombre.getText(),
							apellido1.getText(), apellido2.getText(), nacionalidad.getText(), fnac.getText(),
							(String) tipoTrabajador.getSelectedItem(), 
							documentoIdentidad.getText(),
							direccion.getText(), 
							(String) telefono.getText(), 
							(String) genero.getSelectedItem(),
							email.getText(), 
							puesto.getText(), username.getText(), password.getPassword().toString(), "0");
					DAOEmpleado.añadirEmpleado((Empleado) persona);
					break;
				 case "Beneficiario":
					 persona = new Beneficiario(Integer.parseInt(identificador.getText()),
							 nombre.getText(),apellido1.getText(), nacionalidad.getText());
					 DAOBeneficiario.añadirBeneficiario((Beneficiario) persona);
					 break;
				default:
					System.out.println("Error en Action Performed de OK");
					break;
				}
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

		if (e.getActionCommand().equals("Trabajador")) {
			panelBeneficiario.setVisible(false);
			panelSeparador.add(crearPanelTrabajador(), BorderLayout.EAST);
		}

		if (e.getActionCommand().equals("Beneficiario")) {
			panelTrabajador.setVisible(false);
			panelSeparador.add(crearPanelBeneficiario(), BorderLayout.EAST);
		}

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (divFuncional.getState()) {
			diversidadFuncional.setEditable(true);
		}
		if (!divFuncional.getState()) {
			diversidadFuncional.setEditable(false);
		}
	}
}

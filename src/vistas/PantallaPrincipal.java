package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import gnu.io.CommPortIdentifier;
import modelos.Trabajador;
import serial.Lector;
import serial.SerialComm;

public class PantallaPrincipal extends JFrame implements ActionListener{
	
	static public final String ICONO_AÑADIR = "/iconos/add-button.png";
	static public final String ICONO_BUSCAR = "/iconos/busqueda.png";
	static public final String ICONO_ACTIVIDAD = "/iconos/actividad.png";
	static public final String ICONO_PISO = "/iconos/piso.png";
	static public final String ICONO_INSTITUCION = "/iconos/institution.png";
	static public final String ICONO_TARJETA = "/iconos/tarjeta.png";
	static public final int AFORO_COMEDOR = 6;
	Trabajador trabajador;
	
	private MiAction accAñadir;
	private MiAction accAñadirPiso;
	private MiAction accBuscar;
	private MiAction accGestionarActividad;
	private MiAction accLogOut;
	private MiAction accGrafico;
	private MiAction accTarjeta;
	
	SerialComm lineaSerie;
	Lector hiloLectura;
	CommPortIdentifier puerto;

	public PantallaPrincipal(Trabajador user) {
		this.setTitle(user.getNombre() + " " + user.getApellido1() + " " + user.getApellido2());

		this.trabajador = user;
		this.crearAcciones();
		this.setSize(1024, 600);
		this.setLocation(100, 100);
		this.setJMenuBar(crearBarraMenu());
		this.getContentPane().add(crearPanelCentro(), BorderLayout.CENTER);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		lineaSerie = new SerialComm();

		puerto = lineaSerie.encontrarPuerto();

		if (puerto == null) {
			System.out.println("No se ha encontrado una linea serie");
		} else {
			try {
				lineaSerie.conectar(puerto);
			} catch (Exception e) {

				e.printStackTrace();
			}

			lineaSerie.escribir(AFORO_COMEDOR+"$");

			System.out.println("Linea serie encontrada en: " + puerto.getName());
			hiloLectura = new Lector(lineaSerie, puerto, this);
			hiloLectura.start();
		}
	}
	
	
	private Component crearPanelCentro() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(panelIconos(), BorderLayout.CENTER);
		return panel;
	}


	private Component panelIconos() {
		JPanel panel =  new JPanel(new GridLayout(2, 6));
		if (trabajador.getTipoPersona().equals("EMPLEADO")) {
			panel.add(crearBoton(this.getClass().getResource(PantallaPrincipal.ICONO_BUSCAR), "Buscar", "buscar"));
			panel.add(crearBoton(this.getClass().getResource(PantallaPrincipal.ICONO_PISO), "Añadir piso", "piso"));
			panel.add(crearBoton(this.getClass().getResource(PantallaPrincipal.ICONO_ACTIVIDAD), "Gestionar actividad", "actividad"));
			panel.add(crearBoton(this.getClass().getResource(PantallaPrincipal.ICONO_AÑADIR), "Añadir persona", "añadir"));
			panel.add(crearBoton(this.getClass().getResource(PantallaPrincipal.ICONO_TARJETA), "Agregar tarjeta", "tarjeta"));
			panel.add(crearBoton(this.getClass().getResource(PantallaPrincipal.ICONO_INSTITUCION), "Institución", "institucion"));
		}
		return panel;
	}


	public JButton crearBoton(URL url, String descripcion, String nombre) {
		JButton b;
		b = new JButton(new ImageIcon(url));
		b.setText(descripcion);
		b.setFont(new Font("Arial", Font.BOLD, 20));
		b.setVerticalTextPosition(SwingConstants.BOTTOM);
		b.setHorizontalTextPosition(SwingConstants.CENTER);
		b.setBorder(null);
		b.setOpaque(false);
		b.setBackground(new Color(Color.TRANSLUCENT));
		b.addActionListener(this);
		b.setActionCommand(nombre);
		return b;
	}

	private JMenuBar crearBarraMenu() {
		JMenuBar barra = new JMenuBar();
		JMenu administrar = new JMenu("Administrar");
		JMenu salir = new JMenu("Salir");
		
		administrar.add(accAñadir);
		administrar.addSeparator();
		administrar.add(accBuscar);
		administrar.addSeparator();
		administrar.add(accGestionarActividad);
		administrar.addSeparator();
		administrar.add(accAñadirPiso);
		administrar.addSeparator();
		administrar.add(accGrafico);
		administrar.addSeparator();
		administrar.add(accTarjeta);
		barra.add(administrar);
		barra.add(Box.createHorizontalGlue());

		salir.add(accLogOut);
		barra.add(salir);

		return barra;
	}
	
	private void crearAcciones() {
		accAñadir = new MiAction(this, "Añadir persona", "Añadir una persona", KeyEvent.VK_A);
		accBuscar = new MiAction(this, "Buscar","Realizar una busqueda", KeyEvent.VK_B);
		accAñadirPiso = new MiAction(this, "Pisos", "Añadir pisos", KeyEvent.VK_P);
		accGestionarActividad = new MiAction(this, "Gestionar Actividad", "Gestionar actividades",KeyEvent.VK_G);
		accGrafico = new MiAction(this, "Instituciones", "Consultar instituciones", KeyEvent.VK_C);
		accTarjeta = new MiAction(this, "Tarjeta", "Adjudicar tarjeta", KeyEvent.VK_T);
		accLogOut = new MiAction(this, "Log out","Cerrar la sesión actual", KeyEvent.VK_O);
	}

	private Component crearToolBar() {
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);

		toolBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		toolBar.add(Box.createHorizontalGlue());
		
		toolBar.add(new JButton(accLogOut));
		return toolBar;
	}

	private void cerrarSesion() {
		Login frame = new Login();
		this.dispose();
	}

	private class MiAction extends AbstractAction {

		String texto;

		public MiAction(PantallaPrincipal frame, String texto, String descrip, Integer nemonic) {
			super(texto);
			this.texto = texto;
			this.putValue(Action.SHORT_DESCRIPTION, descrip);
			this.putValue(Action.MNEMONIC_KEY, nemonic);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (texto) {
			case "Añadir persona":
				añade();
				break;
			case "Buscar":
				busqueda();
				break;
			case "Gestionar Actividad":
				añadeActividad();
				break;
			case "Pisos":
				añadePiso();
				break;
			case "Institución":
				añadeInstitucion();
				break;
			case "Tarjeta":
				adjudicaTarjeta();
				break;
			case "Log out":
				cerrarSesion();
				break;
			default:
				JOptionPane.showMessageDialog(PantallaPrincipal.this, "Error en el switch case", "Error",
						JOptionPane.ERROR_MESSAGE);
				break;

			}
		}
	}
	
	public static void main(String[] args) {
		PantallaPrincipal prin = new PantallaPrincipal(new Trabajador(12, "Aitor", "Landa", "Arrue", "alanda", "pass","EMPLEADO"));
	}


	public void añadeActividad() {
		DialogoInfoActividad dialogoActi = new DialogoInfoActividad(this);
	}


	public void adjudicaTarjeta() {
		TarjetaFrame tarjeta = new TarjetaFrame(this);		
	}


	public void busqueda() {
		VisualizarBusqueda buscar = new VisualizarBusqueda(this);
	}

	private void añadeInstitucion() {
		DialogoInstitucion dialogoInsti = new DialogoInstitucion(this);
	}
	
	public void añadePiso() {
		DialogoPiso dialogoPis = new DialogoPiso(this);
	}
	
	public void añade() {
		this.setVisible(false);
		DialogoUsuario dialogo = new DialogoUsuario(this, trabajador,trabajador.getTipoPersona());
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("buscar")) {
			busqueda();
		}
		
		if(e.getActionCommand().equals("añadir")) {
			añade();
		}
		if (e.getActionCommand().equals("actividad")) {
			añadeActividad();
		}
		if (e.getActionCommand().equals("piso")) {
			añadePiso();
		}
		if (e.getActionCommand().equals("tarjeta")) {
			adjudicaTarjeta();
		}
		if (e.getActionCommand().equals("institucion")) {
			añadeInstitucion();
		}
	}
}

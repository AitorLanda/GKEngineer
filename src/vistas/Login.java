package vistas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import funciones.DAOEmpleado;
import funciones.DAOLogin;
import funciones.DAOVoluntario;
import modelos.Empleado;
import modelos.Voluntario;

public class Login implements ActionListener {
	
	private final String IMAGEN_LOGIN = "/imagenes/mano.jpg";
	public final String IMAGEN_VENTANA = "/imagenes/icono_prigmotion.png";
	private final String TITLE_IDENTIFICACION = "Identificacion";
	private final String STRING_LOGING = "Login";
	private final String STRING_USERNAME = "Username:";
	private final String STRING_PASSWORD = "Password :";
	private final String STRING_ERROR = "Error";
	private final String STRING_ERROR_CARGAR_USUARIO = "Error al cargar datos de usuario. Contacte con el administrador.";
	private final String STRING_ERROR_DATOS_INCORRECTOS = "Usuario o contraseña incorrectos.";
	private final String STRING_ERROR_FALTAN_DATOS = "Rellene todos los campos.";
	private final String NOMBRE_VENTANA_LOGING = "Control de acceso";
	private JFrame ventana;
	private JButton bLogin;
	private JTextField textoUsuario;
	private JPasswordField textoPass;
	
	public Login() {
		ventana = new JFrame(NOMBRE_VENTANA_LOGING);
		ventana.setSize(400, 260);
		ventana.setLocation(100, 100);
		ventana.setContentPane(crearPanelVentana());
		ventana.setResizable(false);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setVisible(true);
	}

	private Container crearPanelVentana() {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 20));
		panel.setLayout(null);
		panel.add(crearPanelTextos());
		panel.add(crearPanelBotones());

		JLabel lblImagen = new JLabel("");	
		
		
		lblImagen.setIcon(new ImageIcon(Login.class.getResource(IMAGEN_LOGIN)));

		lblImagen.setBounds(0, 0, 394, 231);
		panel.add(lblImagen);

		return panel;
	}

	private Component crearPanelTextos() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
		panel.setOpaque(false);
		panel.setBounds(70, 40, 254, 98);
		textoUsuario = new JTextField();
		textoPass = new JPasswordField();
		panel.setBorder(BorderFactory.createTitledBorder(null, TITLE_IDENTIFICACION, TitledBorder.CENTER, 0,
				new Font("Arial", Font.PLAIN, 20),Color.BLACK.darker()));

		textoUsuario.addActionListener(this);
		textoUsuario.setActionCommand(STRING_LOGING);
		textoPass.addActionListener(this);
		textoPass.setActionCommand(STRING_LOGING);

		JLabel username = new JLabel(STRING_USERNAME);
		JLabel password = new JLabel(STRING_PASSWORD);

		username.setOpaque(true);
		username.setHorizontalAlignment(JLabel.CENTER);
		username.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		password.setOpaque(true);
		password.setHorizontalAlignment(JLabel.CENTER);
		password.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

		panel.add(username);
		panel.add(textoUsuario);
		panel.add(password);
		panel.add(textoPass);

		return panel;
	}

	private Component crearPanelBotones() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setOpaque(false);
		panel.setBounds(70, 176, 254, 33);
		panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

		bLogin = new JButton(STRING_LOGING);
		bLogin.addActionListener(this);
		bLogin.setActionCommand(STRING_LOGING);
		panel.add(bLogin);

		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String username;
		String password;

		if (e.getActionCommand().equals(STRING_LOGING)) {
			username = textoUsuario.getText();
			password = String.valueOf(textoPass.getPassword());
			if (!username.isEmpty() && !password.isEmpty()){
				try {
					if (DAOLogin.comprobarPassword(password)&&DAOLogin.comprobarUsername(username)) {
						switch(DAOLogin.buscarTipoTrabajador(username, password)) {
						case "EMPLEADO":
							Empleado emp = new Empleado(DAOEmpleado.buscarEmpleado("where loginuser='"+username+"' and loginpass='"+password+"';").get(0));
							PantallaPrincipal frameEmp = new PantallaPrincipal(emp);
							break;
						case "VOLUNTARIO":
							Voluntario vol = new Voluntario(DAOVoluntario.buscarEmpleado("where loginuser='"+username+"' and loginpass='"+password+"';").get(0));
							PantallaPrincipal frameVol = new PantallaPrincipal(vol);
							break;
						case "ADMINISTRADOR":
							break;
						}
							ventana.dispose();

						} else {
							JOptionPane.showMessageDialog(ventana,
									STRING_ERROR_CARGAR_USUARIO, STRING_ERROR,
									JOptionPane.ERROR_MESSAGE);
						}
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				} else {
					JOptionPane.showMessageDialog(ventana, STRING_ERROR_DATOS_INCORRECTOS, STRING_ERROR,
							JOptionPane.ERROR_MESSAGE);
					textoUsuario.setText(null);
					textoPass.setText(null);
					textoUsuario.requestFocus();
				}
			}
			else{
				JOptionPane.showMessageDialog(ventana, STRING_ERROR_FALTAN_DATOS, STRING_ERROR,
						JOptionPane.ERROR_MESSAGE);
			}
		}
		

	public static void main(String[] args) {
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		Login loginframe = new Login();
	}
}

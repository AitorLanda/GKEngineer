package controladores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class CommunicatorSQL {
	
	static Connection conexion;
	final static String DIRECCION = "jdbc:postgresql://localhost:5432/afro";
	
	public static Connection getConexion() {
		
		try {
			conexion = DriverManager.getConnection(DIRECCION, "postgres","aitor");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error en la conexión con el servidor.", "Error: base de datos", JOptionPane.ERROR_MESSAGE);
		}
		
		return conexion;
	}
}
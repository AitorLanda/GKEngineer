package funciones;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controladores.CommunicatorSQL;

public class DAOLogin {
	
	public static boolean comprobarUsername(String user) throws SQLException {
		ResultSet result;
		Statement stmt = CommunicatorSQL.getConexion().createStatement();
		result = stmt.executeQuery("SELECT loginuser from empleado where loginuser='"+user+"';");
		result.next();
		if(result.getString("loginuser").equals(user))
			return true;
		else
			return false;
	}
	
	public static boolean comprobarPassword(String pass) throws SQLException {
		ResultSet result;
		Statement stmt = CommunicatorSQL.getConexion().createStatement();
		result = stmt.executeQuery("SELECT loginpass from empleado where loginpass='"+pass+"';");
		result.next();
		if(result.getString("loginpass").equals(pass))
			return true;
		else
			return false;
	}
	
	public static String buscarTipoTrabajador(String username, String password) throws SQLException {
		ResultSet result;
		Statement stmt = CommunicatorSQL.getConexion().createStatement();
		result = stmt.executeQuery("SELECT tipo from empleado where loginuser='"+username+"' and loginpass='"+password+"';");
		result.next();
		String tipo = result.getString("tipo");
		return tipo;
	}
}

package funciones;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controladores.CommunicatorSQL;
import modelos.Voluntario;

public class DAOVoluntario {
	public static List<Voluntario> buscarEmpleado(String query) throws SQLException {
		ResultSet result;
		Statement stmt = CommunicatorSQL.getConexion().createStatement();
		List<Voluntario> list = new ArrayList<>();
		result = stmt.executeQuery("SELECT * FROM empleado "+query);
		while (result.next()) {
			Voluntario persona = new Voluntario(Integer.parseInt(result.getString("id")), result.getString("nombre"), result.getString("apellido1"), result.getString("apellido2"), 
										result.getString("nacionalidad"), result.getString("fnac"), result.getString("tipoDocIdentidad"), 
										result.getString("docIdentidad"), result.getString("direccion"), result.getString("telefono"), result.getString("genero"), result.getString("email"),
										result.getString("puesto"), result.getString("loginuser"), result.getString("loginpass"));
			list.add(persona);
		}
		
		return list;	
	}

	public static void añadirVoluntario(Voluntario trabajador) throws SQLException {
		Statement stmt  = CommunicatorSQL.getConexion().createStatement();
			stmt.executeUpdate("INSERT INTO empleado (id, nombre, apellido1, apellido2, nacionalidad, fnac, tipoDocIdentidad, docIdentidad, direccion, telefono, genero,"
					+ "puesto, email, salario) VALUES('"+trabajador.getId()+"','"+trabajador.getNombre()+"','"+trabajador.getApellido1()+"','"+trabajador.getApellido2()
					+"','"+trabajador.getNacionalidad()+"','"+trabajador.getFnac()+"','"+trabajador.getTipoDocumento()+"','"+trabajador.getDocumentoIdentidad()
					+"','"+trabajador.getDireccion()+"','"+trabajador.getTelefono()+"','"+trabajador.getGenero()+"','"+trabajador.getPuesto()+"','"+trabajador.getEmail()+"');");
	}
}

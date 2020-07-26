package funciones;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controladores.CommunicatorSQL;
import modelos.Institucion;

public class DAOInstitucion {
	public final static String BUSQUEDA_INSTITUCION = "SELECT * FROM institucion ";

	public DAOInstitucion() {
		// TODO Auto-generated constructor stub
	}

	public static List<Institucion> buscarInstitucion(String query) throws SQLException {
		ResultSet result;
		Statement stmt = CommunicatorSQL.getConexion().createStatement();
		List<Institucion> list = new ArrayList<>();
		result = stmt.executeQuery(BUSQUEDA_INSTITUCION + query);
		while (result.next()) {
			Institucion institucion = new Institucion(Integer.parseInt(result.getString("id")),
					result.getString("nombre"));

			list.add(institucion);
		}
		return list;
	}

	public static void añadirInstitucion(Institucion institucion) throws SQLException {
		Statement stmt = CommunicatorSQL.getConexion().createStatement();
		stmt.executeUpdate("INSERT into institucion (id, nombre) VALUES ('" + institucion.getId() + "','"
				+ institucion.getNombre() + "');");
	}

}

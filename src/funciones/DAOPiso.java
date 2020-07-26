package funciones;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controladores.CommunicatorSQL;
import modelos.Piso;

public class DAOPiso {

	public final static String BUSQUEDA_PISO = "SELECT * FROM piso ";

	public static List<Piso> buscarPiso(String query) throws SQLException{
		ResultSet result, resultSub;
		Statement stmt = CommunicatorSQL.getConexion().createStatement();
		List<Piso> list = new ArrayList<>();
		result = stmt.executeQuery(BUSQUEDA_PISO+query);
		while (result.next()) {
			Piso piso = new Piso(Integer.parseInt(result.getString("id")), result.getString("nomPrograma"), result.getString("direccion"), 
								Integer.parseInt(result.getString("aforo")), result.getString("tipo"), result.getString("intensidad"), 
								Long.parseLong(result.getString("telefono")), result.getInt("porcentaje"));
			list.add(piso);
		}
		return list;
	}

	public static void añadirPiso (Piso piso) throws SQLException {
		Statement stmt = CommunicatorSQL.getConexion().createStatement();
		stmt.executeUpdate("INSERT into piso (id,nomPrograma,direccion,aforo,tipo,intensidad,telefono) VALUES('"+piso.getId()+"','"+piso.getNomPrograma()+"','"+piso.getDireccion()
							+"','"+piso.getAforo()+"','"+piso.getTipo()+"','"+piso.getIntensidad()+"','"+piso.getTelefono()+"');");
		stmt.executeUpdate("INSERT into subvencion (idPiso, idins, porcentaje) values('"+piso.getId()+"','"+"0"+"','"+piso.getSubvencion()+"');");
	}

}

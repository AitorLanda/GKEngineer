package funciones;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controladores.CommunicatorSQL;
import modelos.Actividad;

public class DAOActividad {
	
	public final static String BUSQUEDA_ACT = "SELECT * FROM actividad ";
	
	public DAOActividad() {
		// TODO Auto-generated constructor stub
	}

	public static List<Actividad> buscarActividad(String query) throws SQLException{
		ResultSet result;
		Statement stmt = CommunicatorSQL.getConexion().createStatement();
		List<Actividad> list = new ArrayList<>();
		result = stmt.executeQuery(BUSQUEDA_ACT+query);
		while (result.next()) {
			Actividad act = new Actividad(Integer.parseInt(result.getString("id")), result.getString("nombre"), result.getString("descripcion"), Integer.parseInt(result.getString("plazas")),
								result.getString("fini"), result.getString("ffin"));	
			//act.setCoordinador(DAOTrabajador.buscarTrabajador("where id="+result.getString("idCoordi")));
			//añadirImpartidores(act.getId());
			//añadirParticipantes(act.getId());
			list.add(act);
		}
		return list;
	}

	public static void añadirActividad(Actividad act) throws SQLException {
		Statement stmt = CommunicatorSQL.getConexion().createStatement();
		stmt.executeUpdate("INSERT INTO actividad (id,nombre,descripcion,plazas,fini,ffin) VALUES ('" + act.getId()
				+ "','" + act.getNombre() + "','" + act.getDescripcion() + "','" + act.getPlazas() + "','"
				+ act.getfIni() + "','" + act.getfFin() + "');");
		}
	
	public static int contarActividades() throws SQLException{
		ResultSet result;
		Statement stmt = CommunicatorSQL.getConexion().createStatement();
		result = stmt.executeQuery("SELECT COUNT(*) as c FROM actividad;");
		result.next();
		return result.getInt("c");
	}

}

package funciones;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controladores.CommunicatorSQL;
import modelos.Tarjeta;
import state.EstadoRojo;
import state.EstadoVerde;

public class DAOTarjeta {

	public DAOTarjeta() {
	}
	
	public static List<Tarjeta> buscarTarjetas() throws SQLException {
		List<Tarjeta>lista = new ArrayList<>();
		Statement stmt = CommunicatorSQL.getConexion().createStatement();
		ResultSet result;
		result = stmt.executeQuery("SELECT * FROM TARJETA");
		
		while (result.next()) {
			Tarjeta tarjeta =  new Tarjeta(Integer.parseInt(result.getString("id")), result.getString("tipo"), result.getString("descripcion"));
			tarjeta = comprobarEstadoTarjeta(tarjeta);
			lista.add(tarjeta);
		}
		return lista;
	}
	
	public static Tarjeta comprobarEstadoTarjeta(Tarjeta tarjeta) throws SQLException {
		Statement stmt = CommunicatorSQL.getConexion().createStatement();
		ResultSet consulta;
		consulta = stmt.executeQuery("SELECT idtarjeta FROM beneficiario p JOIN tarjeta t on p.idtarjeta=t.id");
		if (consulta.next())
			tarjeta.setEstado(new EstadoVerde());
		else
			tarjeta.setEstado(new EstadoRojo());
		return tarjeta;
	}
	
	public static void añadirTarjeta(Tarjeta tarjeta) throws SQLException {
		Statement stmt =  CommunicatorSQL.getConexion().createStatement();
		stmt.executeUpdate("INSERT into tarjeta (id, tipo, descripcion) values('"+tarjeta.getId()+"','"+tarjeta.getTipo()+"','"+tarjeta.getDescripcion()+"');");
	}
	
	public static void adjudicarTarjeta(Tarjeta tarjeta, int idBen)throws SQLException{
		Statement stmt = CommunicatorSQL.getConexion().createStatement();
		stmt.executeUpdate("UPDATE beneficiario set idtarjeta="+tarjeta.getId()+" where beneficiario.id="+idBen+";");
		tarjeta.setEstado(new EstadoRojo());
	}
	
	public static void eliminarTarjeta(Tarjeta tarjeta) throws SQLException {
		Statement stmt = CommunicatorSQL.getConexion().createStatement();
		stmt.executeUpdate("delete idtarjeta from beneficiario where idtarjeta="+tarjeta.getId()+";");
		stmt.executeUpdate("DELETE* from tarjeta where id="+tarjeta.getId()+";");
	}

}

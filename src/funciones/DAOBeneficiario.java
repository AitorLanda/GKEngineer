package funciones;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controladores.CommunicatorSQL;
import modelos.Beneficiario;
import modelos.Persona;

public class DAOBeneficiario {
	
	public final static String BUSQUEDA_PERSONA = "SELECT * FROM beneficiario ";

	public DAOBeneficiario() {
		// TODO Auto-generated constructor stub
	}

	public static List<Beneficiario> buscarPersona(String query) throws SQLException {
		ResultSet result;
		Statement stmt = CommunicatorSQL.getConexion().createStatement();
		List<Beneficiario> list = new ArrayList<>();
		result = stmt.executeQuery(BUSQUEDA_PERSONA+query);
		while (result.next()) {
			Beneficiario persona = new Beneficiario(Integer.parseInt(result.getString("id")), result.getString("nombre"), result.getString("apellido1"), result.getString("apellido2"), 
										result.getString("nacionalidad"), result.getString("fnac"), result.getString("tipoDocIdentidad"), 
										result.getString("docIdentidad"), result.getString("direccion"), result.getLong("telefono"), result.getString("genero"), 
										result.getString("divFuncional"), result.getString("fLlegada"), /*result.getString("fAlta"),
										result.getString("fBaja"),*/ result.getString("fCaducidad"), result.getString("comentarios"), result.getString("estcivil"), 
										result.getBoolean("trasocial"), result.getLong("tis"));
			list.add(persona);
		}
		
		return list;	
	}

	public static void añadirBeneficiario (Beneficiario persona) throws SQLException {
		Statement stmt = CommunicatorSQL.getConexion().createStatement();
		if (!persona.getTipoPersona().equals(Persona.tipos[0]))
			return;
		stmt.executeUpdate("INSERT into beneficiario (identificador,nombre,apellido1,apellido2,nacionalidad,fnac,tipoDocIdentidad,docIdentidad,direccion,telefono,genero,divFuncional,fLlegada,"
				+ "fAlta,fBaja,fCaducidad,comentarios,estcivil,trasocial,tis)"+" VALUES ('"+persona.getId()+"','"+persona.getNombre()+"','"+persona.getApellido1()+"','"+persona.getApellido2()
				+"','"+persona.getNacionalidad()+"','"+persona.getFnac()+"','"+persona.getTipoDocumento()+"','"+persona.getDocumentoIdentidad()+"','"+persona.getDireccion()
				+"','"+persona.getDireccion()+"','"+persona.getTelefono()+"','"+persona.getGenero()+"','"+persona.getDivFuncional()+"','"+persona.getfLlegada()+"','"+persona.getfAlta()
				+"','"+persona.getfBaja()+"','"+persona.getfCaducidad()+"','"+persona.getComentarios()+"','"+persona.getEstadoCivil()+"','"+persona.isTrabajadoraSocial()+"','"+persona.getTis()+"');");
	}

}

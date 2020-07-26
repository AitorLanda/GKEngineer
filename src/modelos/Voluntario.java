package modelos;

public class Voluntario extends Trabajador {

	public Voluntario(int id, String nombre, String apellido1, String nacionalidad, String user, String password) {
		super(id, nombre, apellido1, nacionalidad, user, password);
		setTipoPersona(Persona.tipos[2]);
		username = user;
		this.password = password;
	}

	public Voluntario(int id, String nombre, String apellido1, String apellido2, String nacionalidad, String fnac,
			String tipoDoc, String docIden, String direccion, String telefono, String genero, String email,
			String puesto, String user, String pass) {
		super(id, nombre, apellido1, apellido2, nacionalidad, fnac, tipoDoc, docIden, direccion, telefono, genero, email, puesto, user, pass);
		setTipoPersona(Persona.tipos[2]);
	}
	
	public Voluntario(Voluntario vol) {
		super(vol.getId(), vol.getNombre(), vol.getApellido1(), vol.getApellido2(), vol.getNacionalidad(), vol.getFnac(), vol.getTipoDocumento(), vol.getDocumentoIdentidad(),
				vol.getDireccion(), String.valueOf(vol.getTelefono()), String.valueOf(vol.getGenero()), vol.getEmail(), vol.getPuesto(), vol.getUsername(), vol.getPassword());
		setTipoPersona(Persona.tipos[2]);
	}


}

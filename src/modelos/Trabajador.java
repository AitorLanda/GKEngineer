package modelos;

	
public class Trabajador extends Usuario {
	String puesto;
	String email;
	
	public Trabajador(int id, String nombre, String apellido1, String nacionalidad, String user, String password) {
		super(id, nombre, apellido1, nacionalidad);
		username = user;
		this.password = password;
	}
	public Trabajador(int id, String nombre, String apellido1, String nacionalidad, String user, String password, String tipo) {
		super(id, nombre, apellido1, nacionalidad);
		setTipoPersona(tipo);
		username = user;
		this.password = password;
	}
	public Trabajador(int id, String nombre, String apellido1, String apellido2, String nacionalidad,
			String fnac, String tipoDocIdentidad, String docIdentidad, String direccion, String telefono, String genero,
			String email, String puesto, String user, String pass) {
		super(id, nombre, apellido1, nacionalidad);
		username = user;
		password = pass;
	}
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}

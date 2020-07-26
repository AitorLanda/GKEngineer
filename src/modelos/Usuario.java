package modelos;

public class Usuario extends Persona {
	String username;
	String password;
	
	public Usuario(int id, String nombre, String apellido1, String nacionalidad) {
		super(id, nombre, apellido1, nacionalidad);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

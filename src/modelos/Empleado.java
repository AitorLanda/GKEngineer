package modelos;

public class Empleado extends Trabajador {
	float salario;
	
	public Empleado(int id, String nombre, String apellido1, String nacionalidad, String user, String password, float salario) {
		super(id, nombre, apellido1, nacionalidad, user, password);
		setTipoPersona(Persona.tipos[1]);
		this.salario = salario;
	}
	
	public Empleado(Empleado emp) {	
		super(emp.getId(), emp.getNombre(), emp.getApellido1(), emp.getApellido2(), emp.getNacionalidad(), emp.getFnac(), emp.getTipoDocumento(), emp.getDocumentoIdentidad(),
				emp.getDireccion(), String.valueOf(emp.getTelefono()), String.valueOf(emp.getGenero()), emp.getEmail(), emp.getPuesto(), emp.getUsername(), emp.getPassword());
		setTipoPersona(Persona.tipos[1]);
		username = emp.getUsername();
		password = emp.getPassword();
	}
	
	public Empleado(int id, String nombre, String apellido1, String apellido2, String nacionalidad, String fnac,
			String tipoDoc, String docIden, String direc, String tele, String gen, String email,
			String puesto, String user, String pass, String salario) {
		super(id, nombre, apellido1, apellido2, nacionalidad, fnac, tipoDoc, docIden, direc, tele, gen, email, puesto, user, pass);
		this.salario = Float.parseFloat(salario);
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

}

package modelos;
import java.util.List;

public class Persona {
	public final static String[] tipos = {"BENEFICIARIO", "EMPLEADO", "VOLUNTARIO"};
	int id;
	String nombre;
	String apellido1;
	String apellido2;
	String fnac;
	String tipoDocumento;//
	String documentoIdentidad;
	String direccion;
	String nacionalidad;
	List<String> formacion;
	String tipoPersona;
	long telefono;
	char genero;
	
	public Persona(int id, String nombre, String apellido1, String nacionalidad) {
		this.id=id;
		this.nombre=nombre;
		this.apellido1=apellido1;
		this.nacionalidad = nacionalidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDocumentoIdentidad() {
		return documentoIdentidad;
	}

	public void setDocumentoIdentidad(String documentoIdentidad) {
		this.documentoIdentidad = documentoIdentidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public List<String> getFormacion() {
		return formacion;
	}

	public void setFormacion(List<String> formacion) {
		this.formacion = formacion;
	}

	public long getTelefono() {
		return telefono;
	}

	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}

	public char getGenero() {
		return genero;
	}

	public void setGenero(char genero) {
		this.genero = genero;
	}

	public String getFnac() {
		return fnac;
	}

	public void setFnac(String fnac) {
		this.fnac = fnac;
	}

	public String getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", fnac=" + fnac
				+ ", nacionalidad=" + nacionalidad + ", telefono=" + telefono + ", genero=" + genero + "]";
	}
	
}

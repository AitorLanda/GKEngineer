package modelos;
import java.util.List;

public class Piso {
	

	int id;
	String nomPrograma;
	String direccion;
	int aforo;
	String tipo;
	String intensidad;
	long telefono;
	int subvencion;
	List<Periodo> trabajadores;
	List<Periodo> inquilinos;
	
	public Piso(int id, String nomPrograma, String direccion, int aforo) {
		this.id = id;
		this.nomPrograma = nomPrograma;
		this.direccion = direccion;
	}
	
	public Piso(int id, String nomPrograma, String direccion, int aforo, String tipo, String intensidad, long telefono, int subvencion) {
		this.id = id;
		this.nomPrograma = nomPrograma;
		this.direccion = direccion;
		this.aforo = aforo;
		this.tipo = tipo;
		this.intensidad = intensidad;
		this.telefono = telefono;
		this.subvencion = subvencion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomPrograma() {
		return nomPrograma;
	}

	public void setNomPrograma(String nomPrograma) {
		this.nomPrograma = nomPrograma;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getAforo() {
		return aforo;
	}

	public void setAforo(int aforo) {
		this.aforo = aforo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getIntensidad() {
		return intensidad;
	}

	public void setIntensidad(String intensidad) {
		this.intensidad = intensidad;
	}

	public long getTelefono() {
		return telefono;
	}

	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}

	public int getSubvencion() {
		return subvencion;
	}

	public void setSubvencion(int subvencion) {
		this.subvencion = subvencion;
	}

	public List<Periodo> getTrabajadores() {
		return trabajadores;
	}

	public void addTrabajadores(Periodo trabajador) {
		trabajadores.add(trabajador);
	}

	public List<Periodo> getInquilino() {
		return inquilinos;
	}

	public void addInquilino(Periodo inquilino) {
		inquilinos.add(inquilino);
	}	
	
	@Override
	public String toString() {
		return "Piso [id=" + id + ", nomPrograma=" + nomPrograma + ", direccion=" + direccion + ", aforo=" + aforo
				+ ", intensidad=" + intensidad + ", telefono=" + telefono + "]";
	}
}

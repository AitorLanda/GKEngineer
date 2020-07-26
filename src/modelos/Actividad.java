package modelos;
import java.util.List;

public class Actividad {
	
	int id;
	String nombre;
	String descripcion;
	int plazas;
	String fIni;
	String fFin;
	Empleado coordinador;
	List <Periodo> listImpartidores;
	List <Periodo> listParticipantes;
 	
	public Actividad(int id, String nombre, int plazas, String fIni, String fFin) {
		this.id =id;
		this.nombre = nombre;
		this.plazas = plazas;
		this.fIni = fIni;
		this.fFin = fFin;
	}
	
	public Actividad(int id, String nombre, String descripcion, int plazas, String fIni, String fFin) {
		this.id =id;
		this.nombre = nombre;
		this.plazas = plazas;
		this.fIni = fIni;
		this.fFin = fFin;
		this.descripcion = descripcion;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getPlazas() {
		return plazas;
	}

	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}

	public String getfIni() {
		return fIni;
	}

	public void setfIni(String fIni) {
		this.fIni = fIni;
	}

	public String getfFin() {
		return fFin;
	}

	public void setfFin(String fFin) {
		this.fFin = fFin;
	}

	public List<Periodo> getListImpartidores() {
		return listImpartidores;
	}

	public void addImpartidores(Periodo impartidor) {
		listImpartidores.add(impartidor);
	}

	public List<Periodo> getListParticipantes() {
		return listParticipantes;
	}

	public void addParticipantes(Periodo participantes) {
		listParticipantes.add(participantes);
	}

	public Empleado getCoordinador() {
		return coordinador;
	}

	public void setCoordinador(Empleado coordinador) {
		this.coordinador = coordinador;
	}
	
	@Override
	public String toString() {
		return "Actividad [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", plazas=" + plazas
				+ ", fIni=" + fIni + ", fFin=" + fFin + ", coordinador=" + coordinador + "]";
	}
}

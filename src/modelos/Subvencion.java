package modelos;

public class Subvencion extends Periodo {
	
	int porcentaje;
	public Subvencion(int idInstitucion, String inicio, String fin, int porcentaje) {
		super(idInstitucion, inicio, fin);
		this.porcentaje = porcentaje;
	}
	

}

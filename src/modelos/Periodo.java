package modelos;

import controladores.CommunicatorSQL;

public class Periodo{
	int idObjeto;
	String fIni;
	String fFin;
	CommunicatorSQL com;
	
	public Periodo (int id, String inicio, String fin) {
		idObjeto = id;
		fIni = inicio;
		fFin = fin;
	}
}

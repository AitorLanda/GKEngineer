package state;

import modelos.Tarjeta;

public class EstadoRojo extends EstadoTarjeta {
	String estado = "ROJO";
	public EstadoRojo() {
	}
	
	@Override
    public String mostrar() {
		return estado;
   }
}


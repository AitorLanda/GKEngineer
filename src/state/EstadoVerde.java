package state;

import modelos.Tarjeta;

public class EstadoVerde extends EstadoTarjeta {
	String estado = "VERDE";

	public EstadoVerde() {
	}
	
	@Override
    public String mostrar() {
	return estado;
   }
}

package state;

import modelos.Tarjeta;

public class EstadoNaranja extends EstadoTarjeta {
	String estado = "NARANJA";

	public EstadoNaranja(Tarjeta objTarjeta) {
		this.objTarjeta = objTarjeta;
	}
	
	@Override
    public String mostrar() {
		return estado;
      
   }
}


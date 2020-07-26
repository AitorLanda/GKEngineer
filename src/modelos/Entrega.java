package modelos;

public class Entrega {
	Tarjeta tarjeta;
	String fecha;// dd/MM/yyyy HH:mm
	BeneficiarioB personaB;
	
	public Entrega(Tarjeta tarjeta, String fecha) {
		this.tarjeta = tarjeta;
		this.fecha = fecha;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Tarjeta getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}

	public BeneficiarioB getPersonaB() {
		return personaB;
	}

	public void setPersonaB(BeneficiarioB personaB) {
		this.personaB = personaB;
	}

	
}

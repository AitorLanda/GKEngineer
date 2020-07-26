package PatronesDeDiseño;

import javax.swing.JLabel;

public class PisoEsta extends EstadoPiso  {
	private EstadoPiso objEstadoPiso;

	// Constructor de la clase
	public PisoEsta() {
	}

	// Añade el estado donde tiene que estar el cuadrado
	public void setEstado(EstadoPiso objEstadoPiso) {
		this.objEstadoPiso = objEstadoPiso;
	}

	// Funcion que cambia de color al cuadrado
	@Override
	public JLabel mostrar(JLabel Cuadrada) {
		return Cuadrada;
	}
}

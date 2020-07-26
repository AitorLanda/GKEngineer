package PatronesDeDiseño;

import java.awt.Color;

import javax.swing.JLabel;

public class EstadoAmarillo extends EstadoPiso {
	public EstadoAmarillo() {
	}

	//Cambia el color a Amarillo
	@Override
	public JLabel mostrar(JLabel Cuadrada) {
		Cuadrada.setBackground(Color.YELLOW);
		return Cuadrada;
	}
}




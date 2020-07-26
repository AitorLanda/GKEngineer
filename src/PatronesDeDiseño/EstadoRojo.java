package PatronesDeDiseño;

import java.awt.Color;

import javax.swing.JLabel;

public class EstadoRojo extends EstadoPiso {
	public EstadoRojo() {
	}

	// Cambia el color a Rojo
	@Override
	public JLabel mostrar(JLabel Cuadrada) {
		Cuadrada.setBackground(Color.RED);
		return Cuadrada;
	}
}


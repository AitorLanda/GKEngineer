package PatronesDeDise�o;

import java.awt.Color;

import javax.swing.JLabel;

public class EstadoVerde extends EstadoPiso {
	public EstadoVerde() {
	}

	// Cambia el color a Verde
	@Override
	public JLabel mostrar(JLabel Cuadrada) {
		Cuadrada.setBackground(Color.GREEN);
		return Cuadrada;
	}
}



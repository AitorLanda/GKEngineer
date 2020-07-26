package PatronesDeDiseño;

import javax.swing.JLabel;

//Clase Abstracta de el Patron de Diseño de State, aqui se determinan las funciones comunes para todas las clases
public abstract class EstadoPiso {
	public EstadoPiso() {
	}

	//Funcion comun para todas las clases
	public abstract JLabel mostrar(JLabel Cuadrada);
}




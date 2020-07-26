package PatronesDeDiseño;

//Clase General para todas las  funciones de Template, extienden de aqui
public abstract class Sueldo {

	public float obtener(float num) {
		float numero = this.Operacion(num);
		return numero;
	}

	// Funcion Abstracta general
	public abstract float Operacion(float num);

}

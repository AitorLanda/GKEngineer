package modelos;

import state.EstadoRojo;
import state.EstadoTarjeta;
import state.EstadoVerde;

public class Tarjeta {
	
	int id;
	String tipo;
	String descripcion;
	EstadoTarjeta objEstadoTarjeta;
	public Tarjeta(int id, String tipo) {
		this.id=id;
		this.tipo=tipo;
		objEstadoTarjeta = new EstadoVerde();
	}
	
	public Tarjeta(int id, String tipo, String descripcion) {
		this.id=id;
		this.tipo=tipo;
		this.descripcion = descripcion;
		objEstadoTarjeta = new EstadoVerde();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Tarjeta() {
		this.objEstadoTarjeta = new EstadoRojo();
	}
	
	public void setEstado (EstadoTarjeta objEstadoTarjeta) {
		this.objEstadoTarjeta = objEstadoTarjeta;
	}
	
     public String mostrarAviso() {
       return this.objEstadoTarjeta.mostrar();
    }
}

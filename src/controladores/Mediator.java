package controladores;

import java.awt.Component;

import vistas.Busqueda;
import vistas.BusquedaActividad;
import vistas.BusquedaPersona;
import vistas.BusquedaPiso;

public class Mediator {
	Busqueda bus;
	
	public Mediator(Busqueda busqueda) {
		bus = busqueda;
	}
	
	public Component agregarBusqueda() {
		return bus.crearPanelSuperior2();
	}
	
	public Component agregarBusquedaAmpliada() {
		return bus.crearPanelAmpliado();
	}
	
	public Busqueda getBus() {
		return bus;
	}

	public void cambioDeBusqueda(String busquedaNueva) {
		switch (busquedaNueva) {
		case "PISO":
			bus = new BusquedaPiso(bus.getFrame());
			break;
		case "ACT":
			bus = new BusquedaActividad(bus.getFrame());
			break;
		case "PERSONA":
			bus = new BusquedaPersona(bus.getFrame());
			break;
		}
	}
	
	public void ampliarBusqueda() {
		bus.getpAmpliado().setVisible(true);
	}
	
	public void ocultarBusquedaAmpliada() {
		bus.getpAmpliado().setVisible(false);
	}
}

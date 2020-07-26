package modelos;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

public class ListaPersona extends AbstractListModel {
	
	List<Persona> lista;
	public ListaPersona() {
		lista = new ArrayList<>();
	}
	
	public void add(Persona a) {
		lista.add(a);
		this.fireContentsChanged(lista, 0, lista.size()-1);
	}
	public void add(List<Beneficiario> personas) {
		lista.addAll(personas);
		this.fireContentsChanged(lista, 0, lista.size()-1);
	}
	public void remove (int index) {
		lista.remove(index);
		this.fireContentsChanged(lista, 0, lista.size()-1);
	}
	public void remove (int indices []) {
		List<Persona> personasSeleccionados = new ArrayList<>();
		for (int indice: indices) {
			personasSeleccionados.add(lista.get(indice));
		}
		lista.removeAll(personasSeleccionados);
		this.fireContentsChanged(lista, 0, lista.size()-1);
	}

	@Override
	public int getSize() {
		
		return lista.size();
	}

	@Override
	public Object getElementAt(int index) {
		
		return lista.get(index);
	}

	public void clear() {
		lista.clear();
		this.fireContentsChanged(lista, 0, lista.size()-1);
		
	}

}

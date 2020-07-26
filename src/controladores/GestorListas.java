package controladores;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import funciones.DAOBeneficiario;
import modelos.ListaPersona;
import modelos.Persona;

public class GestorListas {
	
	ListaPersona listaBeneficiario;
	ListaPersona gruposPbl[];
	int numGrupos;
	
	public GestorListas (int numGrupos) {
		this.numGrupos = numGrupos;
		try {
			listaBeneficiario = leerListaFichero();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		gruposPbl = leerGruposFichero();
	
	}
	public ListaPersona getListaClase() {
		return listaBeneficiario;
	}
	public ListaPersona getGrupo (int i) {
		return gruposPbl [i];
	}

	public void transferirAListaClase (int indiceGrupo, int []indicesPersonas) {
		for (int indice : indicesPersonas) {
			listaBeneficiario.add((Persona)gruposPbl[indiceGrupo].getElementAt(indice));
		}
		gruposPbl[indiceGrupo].remove(indicesPersonas);
	}
	public void transferirAGrupo(int []indicesAlumnos, int indiceGrupo) {
		for (int indice : indicesAlumnos) {
			gruposPbl[indiceGrupo].add((Persona)listaBeneficiario.getElementAt(indice));
			
		}
		listaBeneficiario.remove(indicesAlumnos);
	}
	public void reiniciar() throws SQLException {
		try {
			listaBeneficiario = leerListaFichero();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (ListaPersona grupo : gruposPbl) {
			grupo.clear();
		}
	}
	
	public void salvarDatos () {
		
	}
	
	private ListaPersona [] leerGruposFichero() {
		ListaPersona [] grupos = null;
		if (grupos == null) {
			grupos = new ListaPersona [numGrupos];
			for (int i = 0; i<numGrupos; i++) {
				grupos[i] = new ListaPersona();
			}
		}
		return grupos;
	}

	private ListaPersona leerListaFichero() throws SQLException {
		ListaPersona lista = null;
		
		lista = new ListaPersona();
		lista.add(DAOBeneficiario.buscarPersona(";"));

		return lista;
	}
}

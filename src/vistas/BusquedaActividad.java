package vistas;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BusquedaActividad extends Busqueda{
	JTextField tIdentificador, tNombre, tDescripcion, tPlazas, tCoordinador;
	JTextField tFini, tFfin, tImpartidor, tParticipante;
	public BusquedaActividad(VisualizarBusqueda frame) {
		super(frame);
		crearPanelSuperior2();
		crearPanelAmpliado();
	}

	@Override
	public Component crearPanelSuperior2() {
		pSuperior2 = new JPanel(new GridLayout(2,6)); 	
		JLabel lId = new JLabel("Identificador");
		tIdentificador = new JTextField(40);
		tIdentificador.setName("id");
		JLabel lNombre = new JLabel("Nombre");
		tNombre = new JTextField(40);
		tNombre.setName("nombre");
		JLabel lDescripcion = new JLabel("Descripción");
		tDescripcion = new JTextField(40);
		tDescripcion.setName("descripcion");
		JLabel lPlazas = new JLabel("Plazas");
		tPlazas = new JTextField(40);
		tPlazas.setName("plazas");
		JLabel lCoordinador = new JLabel("Coordinador");
		tCoordinador = new JTextField(40);
		tCoordinador.setName("coordinador");
		JLabel lEspacio = new JLabel("");

		pSuperior2.add(lId);
		pSuperior2.add(tIdentificador);
		pSuperior2.add(Box.createHorizontalGlue());
		pSuperior2.add(lNombre);
		pSuperior2.add(tNombre);
		pSuperior2.add(Box.createHorizontalGlue());
		pSuperior2.add(lDescripcion);
		pSuperior2.add(tDescripcion);
		pSuperior2.add(lPlazas);
		pSuperior2.add(tPlazas);
		pSuperior2.add(Box.createHorizontalGlue());
		pSuperior2.add(lCoordinador);
		pSuperior2.add(tCoordinador);
		pSuperior2.add(Box.createHorizontalGlue());
		pSuperior2.add(lEspacio);
		pSuperior2.add(Box.createHorizontalGlue());
		pSuperior2.add(lEspacio);
		return pSuperior2;
	}

	@Override
	public Component crearPanelAmpliado() {
		pAmpliado = new JPanel(new GridLayout(2,4));
		JLabel lFini = new JLabel("Fecha Inicio");
		tFini = new JTextField(40);
		tFini.setName("fini");
		JLabel lFfin = new JLabel("Fecha Fin");
		tFfin = new JTextField(40);
		tFfin.setName("ffin");
		JLabel lImpartidor = new JLabel("Impartidor");
		tImpartidor = new JTextField(40);
		tImpartidor.setName("impartidor");
		JLabel lParticipante = new JLabel("Participante");
		tParticipante = new JTextField(40);
		tParticipante.setName("participante");
		
		pAmpliado.add(lFini);
		pAmpliado.add(tFini);
		pAmpliado.add(Box.createHorizontalGlue());
		pAmpliado.add(lFfin);
		pAmpliado.add(tFfin);
		pAmpliado.add(Box.createHorizontalGlue());
		pAmpliado.add(lImpartidor);
		pAmpliado.add(tImpartidor);
		pAmpliado.add(Box.createHorizontalGlue());
		pAmpliado.add(lParticipante);
		pAmpliado.add(tParticipante);
		pAmpliado.setVisible(false);
		return pAmpliado;
	}

	@Override
	public List<JTextField> llenarListaComponentes() {
		tlistaCampos = new ArrayList <>();
		tlistaCampos.add(tIdentificador);
		tlistaCampos.add(tNombre);
		tlistaCampos.add(tDescripcion);
		tlistaCampos.add(tPlazas);
		tlistaCampos.add(tCoordinador);
		tlistaCampos.add(tParticipante);
		tlistaCampos.add(tCoordinador);
		return tlistaCampos;
	}

}

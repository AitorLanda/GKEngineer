package vistas;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BusquedaPiso extends Busqueda{
	JTextField tIdentificador, tNombre, tDireccion, tAforo, tIntensidad, tTelefono, tSubvencion, tTrabajador, tInquilino;
	JComboBox<String>cTipo;
	final static String[] tiposPisos = {"HOMBRES","MUJERES","MIXTO"};
	
	public BusquedaPiso(VisualizarBusqueda frame) {
		super(frame);
		crearPanelSuperior2();
		crearPanelAmpliado();
	}

	@Override
	public Component crearPanelSuperior2() {
		pSuperior2 = new JPanel(new GridLayout(2,6)); 
		JLabel lIdentificado = new JLabel("Identificador");
		tIdentificador = new JTextField(40);
		tIdentificador.setName("id");
		JLabel lNombre = new JLabel("Nombre");
		tNombre = new JTextField(40);
		tNombre.setName("nomPrograma");
		JLabel lDireccion = new JLabel("Dirección");
		tDireccion = new JTextField(40);
		tDireccion.setName("direccion");
		JLabel lAforo = new JLabel("Aforo");
		tAforo = new JTextField(40);
		tAforo.setName("aforo");
		JLabel lTipo  = new JLabel("Tipo");
		JLabel lIntensidad = new JLabel("Intendidad");
		tIntensidad = new JTextField(40);
		tIntensidad.setName("intensidad");
		
		pSuperior2.add(lIdentificado);
		pSuperior2.add(tIdentificador);
		pSuperior2.add(Box.createHorizontalGlue());
		pSuperior2.add(lNombre);
		pSuperior2.add(tNombre);
		pSuperior2.add(Box.createHorizontalGlue());
		pSuperior2.add(lDireccion);
		pSuperior2.add(tDireccion);
		pSuperior2.add(lAforo);
		pSuperior2.add(tAforo);
		pSuperior2.add(Box.createHorizontalGlue());
		pSuperior2.add(lTipo);
		pSuperior2.add(crearComboBox());
		pSuperior2.add(Box.createHorizontalGlue());
		pSuperior2.add(lIntensidad);
		pSuperior2.add(tIntensidad);
		return pSuperior2;
	}

	private Component crearComboBox() {
		cTipo = new JComboBox<>(tiposPisos);
		cTipo.setSelectedItem(null);
		return cTipo;
	}

	@Override
	public Component crearPanelAmpliado() {
		pAmpliado = new JPanel(new GridLayout(2,6));
		JLabel lTelefono = new JLabel("Telefono");
		tTelefono = new JTextField(40);
		tTelefono.setName("telefono");
		JLabel lSubvencion = new JLabel("Subvencion");
		tSubvencion = new JTextField(40);
		tSubvencion.setName("subvencion");
		JLabel lInquilino = new JLabel("Inquilino");
		tInquilino = new JTextField(40);
		tInquilino.setName("inquilino");
		JLabel lTrabajador = new JLabel("Trabajador");
		tTrabajador = new JTextField(40);
		tTrabajador.setName("trabajador");
		
		pAmpliado.add(lTelefono);
		pAmpliado.add(tTelefono);
		pAmpliado.add(Box.createHorizontalGlue());
		pAmpliado.add(lSubvencion);
		pAmpliado.add(tSubvencion);
		pAmpliado.add(Box.createHorizontalGlue());
		pAmpliado.add(lInquilino);
		pAmpliado.add(tInquilino);
		pAmpliado.add(Box.createHorizontalGlue());
		pAmpliado.add(lTrabajador);
		pAmpliado.add(tTrabajador);
		pAmpliado.setVisible(false);
		return pAmpliado;
	}

	@Override
	public List<JTextField> llenarListaComponentes() {
		tlistaCampos = new ArrayList<>();
		tlistaCampos.add(tDireccion);
		tlistaCampos.add(tIdentificador);
		tlistaCampos.add(tIntensidad);
		tlistaCampos.add(tNombre);
		tlistaCampos.add(tSubvencion);
		tlistaCampos.add(tTelefono);
		tlistaCampos.add(tInquilino);
		tlistaCampos.add(tTrabajador);
		tlistaCampos.add(tAforo);
		return tlistaCampos;
	}

}

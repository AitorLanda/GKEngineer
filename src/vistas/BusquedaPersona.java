package vistas;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelos.Trabajador;

public class BusquedaPersona extends Busqueda{

	public JTextField tNombre, tApellido1, tApellido2, tNacionalidad, tIdentificador, tGenero, tTelefono, tDocumentoIdentidad;
	public JPanel pCombo;
	private JComboBox<String> ctipoPersona;
	public BusquedaPersona(VisualizarBusqueda visualizarBusqueda) {
		super(visualizarBusqueda);
		crearPanelSuperior2();
		crearPanelAmpliado();
	}	

	@Override
	public Component crearPanelSuperior2() {
		pSuperior2 = new JPanel(new GridLayout(2, 6));
		JLabel lNombre = new JLabel("Nombre");
		tNombre = new JTextField(40);
		tNombre.setName("nombre");
		JLabel lApellido1 = new JLabel("Apellido 1");
		tApellido1 = new JTextField(40);
		tApellido1.setName("apellido1");
		JLabel lApellido2 = new JLabel("Apellido 2");
		tApellido2 =  new JTextField(40);
		tApellido2.setName("apellido2");
		JLabel lNacionalidad = new JLabel("Nacionalidad");
		tNacionalidad = new JTextField(40);
		tNacionalidad.setName("nacionalidad");
		JLabel lId = new JLabel("Identificador");
		tIdentificador = new JTextField(40);
		tIdentificador.setName("id");
		JLabel lComboBox = new JLabel("Tipo");
		
		pSuperior2.add(lId);
		pSuperior2.add(tIdentificador);
		pSuperior2.add(Box.createHorizontalGlue());
		pSuperior2.add(lNombre);
		pSuperior2.add(tNombre);
		pSuperior2.add(Box.createHorizontalGlue());
		pSuperior2.add(lApellido1);
		pSuperior2.add(tApellido1);
		pSuperior2.add(lApellido2);
		pSuperior2.add(tApellido2);
		pSuperior2.add(Box.createHorizontalGlue());
		pSuperior2.add(lNacionalidad);
		pSuperior2.add(tNacionalidad);
		pSuperior2.add(Box.createHorizontalGlue());
		pSuperior2.add(lComboBox);
		pSuperior2.add(crearPanelCombo());
		return pSuperior2;
	}

	@Override
	public Component crearPanelAmpliado() {
		pAmpliado = new JPanel(new GridLayout(2,4));
		JLabel lGenero = new JLabel("Genero");
		tGenero = new JTextField(40);
		tGenero.setName("genero");
		JLabel lTelefono = new JLabel("Telefono");
		tTelefono = new JTextField(40);
		tTelefono.setName("telefono");
		JLabel lDocumentoIdentidad = new JLabel("Documento Identidad");
		tDocumentoIdentidad = new JTextField(40);
		tDocumentoIdentidad.setName("docIdentidad");
		JLabel lEspacio = new JLabel("");
		
		pAmpliado.add(lTelefono);
		pAmpliado.add(tTelefono);
		pAmpliado.add(Box.createHorizontalGlue());
		pAmpliado.add(lGenero);
		pAmpliado.add(tGenero);
		pAmpliado.add(Box.createHorizontalGlue());
		pAmpliado.add(lDocumentoIdentidad);
		pAmpliado.add(tDocumentoIdentidad);
		pAmpliado.add(Box.createHorizontalGlue());
		pAmpliado.add(lEspacio);
		pAmpliado.add(Box.createHorizontalGlue());
		pAmpliado.add(lEspacio);
		pAmpliado.setVisible(false);
		return pAmpliado;		
	}
	
	public Component crearPanelCombo() {

		ctipoPersona = new JComboBox<String>();

		ctipoPersona.setModel(new DefaultComboBoxModel<String>(Trabajador.tipos));
		ctipoPersona.setSelectedItem(null);
		ctipoPersona.setBackground(Color.WHITE);
		pCombo = new JPanel(new GridLayout(1,2));
		pCombo.add(ctipoPersona);

		return pCombo;
	}
	

	public JComboBox<String> getCtipoPersona() {
		return ctipoPersona;
	}

	@Override
	public List<JTextField> llenarListaComponentes() {
		tlistaCampos = new ArrayList <>();
		tlistaCampos.add(tIdentificador);
		tlistaCampos.add(tNombre);
		tlistaCampos.add(tApellido1);
		tlistaCampos.add(tApellido2);
		tlistaCampos.add(tNacionalidad);
		tlistaCampos.add(tDocumentoIdentidad);
		tlistaCampos.add(tGenero);
		tlistaCampos.add(tTelefono);
		return tlistaCampos;
	}

}

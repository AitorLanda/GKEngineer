package vistas;

import java.awt.Component;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class Busqueda extends Component{

	public JPanel pSuperior1, pAmpliado;
	public JPanel pSuperior2;
	
	List<JTextField> tlistaCampos;
	VisualizarBusqueda frame;

	public Busqueda(VisualizarBusqueda visualizarBusqueda) {
		frame = visualizarBusqueda;
	}

	public abstract Component crearPanelSuperior2();
	
	public VisualizarBusqueda getFrame() {
		return frame;
	}
	
	public JPanel getpAmpliado() {
		return pAmpliado;
	}
	
	public List<JTextField> getTlistaCampos() {
		return tlistaCampos;
	}

	public abstract Component crearPanelAmpliado();
	
	public abstract List<JTextField> llenarListaComponentes();
}

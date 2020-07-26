package vistas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.toedter.calendar.JDateChooser;

import controladores.Mediator;
import funciones.DAOActividad;
import funciones.DAOBeneficiario;
import funciones.DAOEmpleado;
import funciones.DAOPiso;
import funciones.DAOVoluntario;
import modelos.Actividad;
import modelos.Beneficiario;
import modelos.Empleado;
import modelos.Persona;
import modelos.Piso;
import modelos.Trabajador;
import modelos.Voluntario;
import vistas.DialogoInfoActividad;
import vistas.DialogoInfoPiso;

public class VisualizarBusqueda extends JDialog implements ListSelectionListener, ActionListener{
	
	final static String WHERE = "WHERE";
	final static String SELECT = "SELECT";
	final static String FROM = "FROM";
	final static String BUSQUEDA = "buscar";
	final static String JOIN = "JOIN";

	public JPanel pVentana, pBotonA, pBotonS, pMedio, pSuperior, pSuperior1, pAmpliado;
	public JPanel pSuperior2;
	public JPanel pCombo;
	public JScrollPane sPanel;
	public JDateChooser dateChooser;
	public JButton bEdit, bRemove, bSave, bCancel;
	public JCheckBox bAdd;
	private final String ICONO_EDIT = "/iconos/lupa.png";
	private final String ICONO_REMOVE = "/iconos/remove.png";
	private final String ICONO_SAVE = "/iconos/save.png";
	private final String ICONO_CANCEL = "/iconos/eliminar.png";
	public Persona persona;
	
	JRadioButton rb1, rb2, rb3;
	List<JTextField> tlistaCampos;
	JComboBox<String> ctipoPersona;
	JList<Persona> listaPersonas;
	JList<Piso> listaPisos;
	JList<Actividad> listaActividades;
	DefaultListModel<Persona> modeloPersona;
	DefaultListModel<Piso> modeloPiso;
	DefaultListModel<Actividad> modeloAct;
	
	Mediator mediatorBusqueda;
	Busqueda busqueda;
	PantallaPrincipal frame;
	
	public VisualizarBusqueda(PantallaPrincipal frame) {
		super(frame, true);
		listaPersonas = new JList<>();
		listaPersonas.addListSelectionListener(this);
		modeloPersona = new DefaultListModel<>();
		listaActividades = new JList<>();
		listaActividades.addListSelectionListener(this);
		modeloAct = new DefaultListModel<>();
		listaPisos = new JList<>();
		listaPisos.addListSelectionListener(this);
		modeloPiso = new DefaultListModel<>();
		this.frame = frame;
		this.setTitle("Busqueda de Datos");
		this.setSize(800, 600);
		this.setLocation(200, 100);
		this.getContentPane().add(crearPanelVentana(), BorderLayout.CENTER);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private Component crearPanelVentana() {
		pVentana = new JPanel(new BorderLayout());
		pVentana.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		pVentana.add(crearPanelSuperior(), BorderLayout.NORTH);
		pVentana.add(crearPanelMedio(), BorderLayout.CENTER);
		pVentana.add(crearPanelBotones2(), BorderLayout.SOUTH);
		return pVentana;
	}

	private Component crearPanelSuperior() {
		pSuperior = new JPanel(new GridLayout(3, 1));
		pSuperior.add(crearPanelSuperior1());	
		pSuperior2 = (JPanel) mediatorBusqueda.agregarBusqueda();
		pAmpliado = (JPanel) mediatorBusqueda.agregarBusquedaAmpliada();
		pSuperior.add(pSuperior2);
		pSuperior.add(pAmpliado);
		return pSuperior;

	}

	public Component crearPanelSuperior1() {
		pSuperior1 = new JPanel(new GridLayout(1, 3));
		rb1 = new JRadioButton("Usuario");
		rb2 = new JRadioButton("Piso");
		rb3 = new JRadioButton("Actividad");
		ButtonGroup grupo = new ButtonGroup();
		rb1.setActionCommand("PERSONA");
		rb2.setActionCommand("PISO");
		rb3.setActionCommand("ACT");
		rb1.addActionListener(this);
		rb2.addActionListener(this);
		rb3.addActionListener(this);
		grupo.add(rb1);
		grupo.add(rb2);
		grupo.add(rb3);
		rb1.setSelected(true);
		mediatorBusqueda = new Mediator(new BusquedaPersona(this));
		pSuperior1.add(Box.createHorizontalGlue());
		pSuperior1.add(rb1);
		pSuperior1.add(rb2);
		pSuperior1.add(rb3);
		return pSuperior1;
	}

	private JPanel crearPanelMedio() {
		pMedio = new JPanel(new BorderLayout());
		pMedio.add(crearPanelBotones(), BorderLayout.NORTH);
		pMedio.add(crearPanelCentral(), BorderLayout.CENTER);
		return pMedio;

	}

	private JScrollPane crearPanelCentral() {
		sPanel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		listaPersonas.setModel(modeloPersona);
		listaPersonas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaPersonas.addListSelectionListener(this);
		sPanel.setViewportView(listaPersonas);
		return sPanel;
	}

	public Component crearPanelBotones() {
		pBotonA = new JPanel(new FlowLayout());
		pBotonA.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		bAdd = new JCheckBox("Add");
		bAdd.setActionCommand("add");
		bAdd.addActionListener(this);
		bAdd.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pBotonA.add(bAdd);

		bEdit = new JButton("Buscar");
		bEdit.setIcon(new ImageIcon(this.getClass().getResource(ICONO_EDIT)));
		bEdit.setActionCommand("buscar");
		bEdit.addActionListener(this);
		bEdit.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pBotonA.add(bEdit);

		bRemove = new JButton("Remove");
		bRemove.setIcon(new ImageIcon(this.getClass().getResource(ICONO_REMOVE)));
		bRemove.setActionCommand("remove");
		bRemove.addActionListener(this);
		bRemove.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pBotonA.add(bRemove);
		return pBotonA;
	}

	public Component crearPanelBotones2() {

		pBotonS = new JPanel(new FlowLayout());
		pBotonS.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		bSave = new JButton("Save");
		bSave.setIcon(new ImageIcon(this.getClass().getResource(ICONO_SAVE)));
		bSave.addActionListener(this);
		bSave.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pBotonS.add(bSave);
		bCancel = new JButton("Cancel");
		bCancel.setIcon(new ImageIcon(this.getClass().getResource(ICONO_CANCEL)));
		bCancel.addActionListener(this);
		bCancel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pBotonS.add(bCancel);
		return pBotonS;
	}

	public JList<Persona> getLista() {
		return listaPersonas;
	}

	public Persona getPaciente() {
		return persona;
	}

	public String conseguirFecha() {
		String fecha = dateChooser.getCalendar().get(java.util.Calendar.DAY_OF_MONTH) + "/"
				+ (dateChooser.getCalendar().get(java.util.Calendar.MONTH) + 1) + "/"
				+ (dateChooser.getCalendar().get(java.util.Calendar.YEAR));
		return fecha;
	}
	
	public String comprobarCampos() {
		String query=null;
		int j=0;
		tlistaCampos = mediatorBusqueda.getBus().llenarListaComponentes();
		if(rb1.isSelected())
			query = (String)((BusquedaPersona) mediatorBusqueda.getBus()).getCtipoPersona().getSelectedItem();
		else if(rb2.isSelected())
			query=rb2.getText()+ " join subvencion on id=idPiso";
		else if(rb3.isSelected())
			query=rb3.getText();
		
		for(int i=0; i<tlistaCampos.size(); i++) {
			if (tlistaCampos.get(i).getText().equals(""))
				continue;
			else {
				if(j==0) {
					query +=" WHERE";
					j++;
				}
				if(tlistaCampos.get(i).getName().equals("identificador") || tlistaCampos.get(i).getName().equals("id")
						|| tlistaCampos.get(i).getName().equals("aforo"))					
					query+=" "+tlistaCampos.get(i).getName()+"="+tlistaCampos.get(i).getText()+" AND";
				else
					query+=" "+tlistaCampos.get(i).getName()+"='"+tlistaCampos.get(i).getText()+"' AND";
			}
		}
		if((!query.equals(null)  && !query.equals(rb2.getText()) && !query.equals(rb3.getText()))
				|| (rb1.isSelected()&&!query.equals((String)((BusquedaPersona) mediatorBusqueda.getBus()).getCtipoPersona().getSelectedItem()))) {
			try {
				query = query.substring(0, query.length()-4);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Lista Vacia.", "Lista Vacia", JOptionPane.ERROR_MESSAGE);
			}
		}
		query+=";";
		System.out.println(query);
		return query;
	}
	
	public PantallaPrincipal getFrame() {
		return frame;
	}

	
	
	public JPanel getpAmpliado() {
		return pAmpliado;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(BUSQUEDA)) {
			if (rb1.isSelected()) {
				sPanel.setViewportView(listaPersonas);
				modeloPersona.clear();
				pMedio.revalidate();
				try {
				switch ((String)((BusquedaPersona) mediatorBusqueda.getBus()).getCtipoPersona().getSelectedItem()) {
				case "BENEFICIARIO":
					List<Beneficiario> listBen = new ArrayList<>();
					listBen = DAOBeneficiario.buscarPersona(comprobarCampos());
					for(Beneficiario ben:listBen)
						modeloPersona.addElement(ben);
					break;
				case "VOLUNTARIO":
					List<Voluntario> listVol = new ArrayList<>();
					listVol =  DAOVoluntario.buscarEmpleado(comprobarCampos());
					for(Voluntario vol:listVol)
						modeloPersona.addElement(vol);
					break;
				default:			
					List<Empleado> listEmp = new ArrayList<>();
					listEmp = DAOEmpleado.buscarEmpleado(comprobarCampos());
					for(Empleado emp:listEmp)
						modeloPersona.addElement(emp);
					break;
					}
					
				}catch (SQLException e1) {
						e1.printStackTrace();
				}
			}
			if(rb2.isSelected()) {
				sPanel.setViewportView(listaPisos);
				modeloPiso.clear();
				pMedio.revalidate();
				listaPisos.setModel(modeloPiso);
				List<Piso> listPiso = new ArrayList<>();
					try {
						listPiso = DAOPiso.buscarPiso(comprobarCampos());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				for(Piso piso:listPiso)
					modeloPiso.addElement(piso);
			}
			if(rb3.isSelected()) {
				sPanel.setViewportView(listaActividades);
				modeloAct.clear();
				pMedio.revalidate();
				listaActividades.setModel(modeloAct);
				List<Actividad> listAct = new ArrayList<>();
					try {
						listAct = DAOActividad.buscarActividad(comprobarCampos());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				for(Actividad act:listAct)
					modeloAct.addElement(act);
			}
		}

		if(e.getActionCommand().equals("Cancel")) {
			this.dispose();
		}
		
		if(e.getActionCommand().equals("PERSONA") || e.getActionCommand().equals("PISO") || e.getActionCommand().equals("ACT")) {
			bAdd.setSelected(false);
			mediatorBusqueda.ocultarBusquedaAmpliada();
			mediatorBusqueda.cambioDeBusqueda(e.getActionCommand());
			pSuperior.remove(pSuperior2);
			pSuperior.remove(pAmpliado);
			pSuperior2 = (JPanel) mediatorBusqueda.agregarBusqueda();
			pAmpliado = (JPanel) mediatorBusqueda.agregarBusquedaAmpliada();
			pSuperior.add(pSuperior2);
			pSuperior.add(pAmpliado);
			pSuperior.revalidate();
		}
			
		if(e.getActionCommand().equals("add")) {
			if (!bAdd.isSelected()){
				mediatorBusqueda.ocultarBusquedaAmpliada();
			}else {
				mediatorBusqueda.ampliarBusqueda();
			}
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (rb1.isSelected() && ((String)((BusquedaPersona) mediatorBusqueda.getBus()).getCtipoPersona().getSelectedItem()).equals("EMPLEADO")) {
			DialogoInfoTrabajador InfoUst = new DialogoInfoTrabajador(this, (Trabajador) listaPersonas.getSelectedValue());
		} else if (rb1.isSelected() &&((String)((BusquedaPersona) mediatorBusqueda.getBus()).getCtipoPersona().getSelectedItem()).equals("BENEFICIARIO")) {
			DialogoInfoBenefactor InfoUsb = new DialogoInfoBenefactor(this, (Beneficiario) listaPersonas.getSelectedValue());
		} else if (rb1.isSelected() &&((String)((BusquedaPersona) mediatorBusqueda.getBus()).getCtipoPersona().getSelectedItem()).equals("VOLUNTARIO")) {
			DialogoInfoVoluntario InfoUsv = new DialogoInfoVoluntario(this, (Voluntario) listaPersonas.getSelectedValue());
		} else if (rb2.isSelected()) {
			DialogoInfoPiso infoPiso = new DialogoInfoPiso(this, listaPisos.getSelectedValue());
		} else if (rb3.isSelected()) {
			DialogoInfoActividad infoAct = new DialogoInfoActividad(frame);
		}
	}
}


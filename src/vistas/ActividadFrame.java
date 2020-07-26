package vistas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controladores.GestorListas;
import funciones.DAOActividad;
import modelos.Persona;

public class ActividadFrame extends JFrame implements ActionListener {
	private static final String FILE_IMAGEN_DCHA = "/iconos/1rightarrow.png";
	private static final String FILE_IMAGEN_IZDA = "/iconos/1leftarrow.png";
	static int NUM_GRUPOS;
	GestorListas gestorListas;
	JList<Persona> vListaClase;
	List<JList<Persona>> vListaGrupos;
	
	public ActividadFrame() {
		super ("Gestionar Actividades");
		
		try {
			NUM_GRUPOS = DAOActividad.contarActividades();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		gestorListas = new GestorListas(NUM_GRUPOS);
		vListaGrupos = new ArrayList<>();
		this.setLocation(100,100);
		this.setSize(900, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(crearPanelVentana());
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				gestorListas.salvarDatos();
				super.windowClosing(e);
			}
			
		});
		this.setVisible(true);
	}
	private Container crearPanelVentana() {
		JPanel panel = new JPanel(new BorderLayout());
		
		panel.add(crearToolBar(), BorderLayout.NORTH);
		panel.add(crearPanelSeparador(), BorderLayout.CENTER);
		return panel;
	}
	private Component crearPanelSeparador() {
		JSplitPane sPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				crearPanelListaClase(),crearPanelGrupos());
		sPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		sPanel.setDividerLocation(200);		
		return sPanel;
	}
	private Component crearToolBar() {
		JToolBar tool = new JToolBar();
		JButton b = new JButton("Añadir Actividad");
		b.setIcon(new ImageIcon(this.getClass().getResource("/iconos/add.png")));
		b.setActionCommand("act");
		b.addActionListener(this);
		tool.add(b);
		return tool;
	}
	private Component crearPanelGrupos() {
		JPanel panel = new JPanel (new GridLayout(((NUM_GRUPOS%2==0)?(NUM_GRUPOS/2):(NUM_GRUPOS/2+1)),2,20,20));
		for (int i = 0; i <NUM_GRUPOS; i++) {
			panel.add(crearPanelGrupo(i));
		}
		return panel;
	}
	private Component crearPanelGrupo(int index) {
		JPanel panel = new JPanel(new BorderLayout(20,0));
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLoweredBevelBorder(),
				"grupo_"+(index+1)));
		panel.add(crearPanelBotones(index), BorderLayout.WEST);
		panel.add(crearPanelListaGrupo(index),BorderLayout.CENTER);
		return panel;
	}
	private Component crearPanelListaGrupo(int index) {
		JScrollPane panel = new JScrollPane();
		JList<Persona> vLista = new JList<>();
		
		vLista.setModel(gestorListas.getGrupo(index));
		vLista.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		vListaGrupos.add(index, vLista);
		
		panel.setViewportView(vLista);
		return panel;
	}
	private Component crearPanelBotones(int index) {
		JPanel panel = new JPanel (new GridLayout(2,1,0,20));
		JButton botonDcha = new JButton(new ImageIcon(this.getClass().getResource(FILE_IMAGEN_DCHA)));
		botonDcha.addActionListener(this);
		botonDcha.setActionCommand(index+"_dcha");
		JButton botonIzda = new JButton(new ImageIcon(this.getClass().getResource(FILE_IMAGEN_IZDA)));
		botonIzda.addActionListener(this);
		botonIzda.setActionCommand(index+"_izda");
		panel.add(botonDcha);
		panel.add(botonIzda);
		return panel;
	}
	private Component crearPanelListaClase() {
		JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLoweredBevelBorder(),"Lista clase"));
		vListaClase = new JList<>();
		vListaClase.setModel(gestorListas.getListaClase());
		vListaClase.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		panel.setViewportView(vListaClase);
		return panel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("act")) {
			DialogoInfoActividad dialogo = new DialogoInfoActividad(this);
		}else {
			int indexGrupo;
			String sentido;
			String [] valores = e.getActionCommand().split("[_]");
			indexGrupo = Integer.parseInt(valores[0]);
			sentido = valores[1];
			if (sentido.equals("dcha")) {
				int [] indicesSeleccionados = vListaClase.getSelectedIndices();
				if (indicesSeleccionados.length ==0) {
					JOptionPane.showMessageDialog(this, "No hay alumnos seleccionados","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				vListaClase.clearSelection();
				gestorListas.transferirAGrupo(indicesSeleccionados, indexGrupo);
			}
			if (sentido.equals("izda")) {
				int [] indicesSeleccionados = vListaGrupos.get(indexGrupo).getSelectedIndices();
				if (indicesSeleccionados.length ==0) {
					JOptionPane.showMessageDialog(this, "No hay alumnos seleccionados","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				vListaGrupos.get(indexGrupo).clearSelection();
				gestorListas.transferirAListaClase(indexGrupo, indicesSeleccionados);
			}
		}
	}
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ActividadFrame principal = new ActividadFrame();

	}
	

}

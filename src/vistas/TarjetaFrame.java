package vistas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import funciones.DAOTarjeta;
import modelos.RendererTarjeta;
import modelos.Tarjeta;

public class TarjetaFrame extends JDialog implements ListSelectionListener, ActionListener{
	
	JFrame frame;
	JList<Tarjeta> listTarjetas;
	RendererTarjeta renderT;
	DefaultListModel<Tarjeta> modelo;
	JTextField tTarjeta, tIdentificador, tNombre;
	JButton bOk, bCancel, bAdd, bRemove;
	final static String ICONO_ADD = "/iconos/add.png";
	final static String ICONO_REMOVE = "/iconos/remove.png";

	public TarjetaFrame(JFrame frame) {	
		super(frame, true);
		listTarjetas = new JList<>();
		renderT = new RendererTarjeta();
		modelo = new DefaultListModel<>();
		this.frame = frame;
		this.setTitle("Busqueda de Datos");
		this.setSize(400, 400);
		this.setResizable(false);
		this.setLocation(200, 100);
		this.getContentPane().add(crearPanelVentana(), BorderLayout.CENTER);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private Component crearPanelVentana() {
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.add(crearToolbar(),BorderLayout.NORTH);
		panelPrincipal.add(crearPanelCentral(), BorderLayout.CENTER);
		panelPrincipal.add(crearPanelLateral(), BorderLayout.WEST);
		panelPrincipal.add(crearPanelBotones(), BorderLayout.SOUTH);
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		return panelPrincipal;
	}

	private Component crearToolbar() {
		JToolBar tool  = new JToolBar();
		bAdd = new JButton("Añadir Tarjeta", new ImageIcon(this.getClass().getResource(ICONO_ADD)));
		bAdd.setActionCommand("add");
		bAdd.addActionListener(this);
		
		bRemove = new JButton("Eliminar Tarjeta", new ImageIcon(this.getClass().getResource(ICONO_REMOVE)));
		bRemove.setActionCommand("remove");
		bRemove.addActionListener(this);
		bRemove.setEnabled(false);
		tool.setFloatable(false);
		tool.add(bAdd);
		tool.add(bRemove);
		return tool;
	}

	private Component crearPanelBotones() {
		JPanel panel =  new JPanel(new GridLayout(1, 2));
		bOk = new JButton("Guardar");
		bCancel = new JButton("Cerrar");
		
		bOk.addActionListener(this);
		bCancel.addActionListener(this);
		panel.add(bOk);
		panel.add(bCancel);
		return panel;
	}

	private Component crearPanelLateral() {
		JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		List<Tarjeta> listTarjeta = new ArrayList<>();
		listTarjetas.addListSelectionListener(this);
		listTarjetas.setModel(modelo);
		listTarjetas.setCellRenderer(renderT);
		listTarjetas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		try {
			listTarjeta = DAOTarjeta.buscarTarjetas();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!listTarjeta.isEmpty())
			for(Tarjeta tarjeta:listTarjeta) {
				modelo.addElement(tarjeta);
			}
		//panel.add(listTarjetas);
		panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 5));
		panel.setPreferredSize(new Dimension(100 , panel.getHeight()));
		panel.setViewportView(listTarjetas);
		return panel;
	}

	private Component crearPanelCentral() {
		JPanel panel  = new JPanel(/*new GridLayout(2, 2)*/);
		JLabel lTarjeta = new JLabel("Tarjeta");
		tTarjeta = new JTextField(20);
		tIdentificador = new JTextField(20);
		tNombre = new JTextField(20);
		
		if(!listTarjetas.isSelectionEmpty()) {
			tTarjeta.setText(String.valueOf(listTarjetas.getSelectedValue().getId()));
		}
		tIdentificador.setBorder(BorderFactory.createTitledBorder("Identificador del beneficiario"));
		tNombre.setBorder(BorderFactory.createTitledBorder("Nombre del beneficiario"));
		
		panel.add(lTarjeta);
		panel.add(tTarjeta);
		panel.add(tIdentificador);
		panel.add(tNombre);
		
		return panel;
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		tTarjeta.setText(String.valueOf(listTarjetas.getSelectedValue().getId()));
		
		if(listTarjetas.getSelectedValue()!=null) {
			bRemove.setEnabled(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("add")) {
			TarjetaNueva nueva = new TarjetaNueva(this);
			if(nueva.getTarjeta()!=null)
				modelo.addElement(nueva.getTarjeta());
		}
		if(e.getActionCommand().equals("remove")) {
			try {
				DAOTarjeta.eliminarTarjeta(listTarjetas.getSelectedValue());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			modelo.remove(listTarjetas.getSelectedIndex());
			
		}
		if(e.getActionCommand().equals("Guardar")) {
			try {
				DAOTarjeta.adjudicarTarjeta(listTarjetas.getSelectedValue(), Integer.parseInt(tIdentificador.getText()));
				listTarjetas.revalidate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getActionCommand().equals("Cerrar")) {
			this.dispose();
		}
	}

}

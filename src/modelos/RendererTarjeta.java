package modelos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class RendererTarjeta extends JLabel implements ListCellRenderer<Tarjeta> {
	private final Color COLOR_VERDE = new Color(34, 177, 76);
	private final Color COLOR_ROJO = new Color(255, 0, 0);
	private final Color COLOR_NARANJA = new Color(255, 164, 32);
	private final int TAMAÑOLETRA = 16;
	private final int DELGADEZ = 1;
	Beneficiario beneficiario;
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Tarjeta> list, Tarjeta t, int index,
			boolean isSelected, boolean cellHasFocus) {
		Border border = BorderFactory.createLineBorder(Color.BLUE, DELGADEZ);
		Border borderNormal = BorderFactory.createBevelBorder(BevelBorder.RAISED);
		setFont(new Font("Arial", Font.BOLD, TAMAÑOLETRA));
		
		if(t.mostrarAviso().equals("VERDE"))
			setForeground(COLOR_VERDE);
		
		if(t.mostrarAviso().equals("ROJO"))
			setForeground(COLOR_ROJO);
		
		if((t.mostrarAviso().equals("NARANJA")) && (beneficiario.getfCaducidad().equals(beneficiario.getFechaActual())))
			setForeground(COLOR_NARANJA);
		
		if (isSelected && cellHasFocus) {
			setBorder(border);
		} else {
			setBorder(borderNormal);
		}

		this.setText(String.valueOf(t.getId()));
		this.setOpaque(true);

		return this;
	}

}


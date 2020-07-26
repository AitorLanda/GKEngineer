package serial;

import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import gnu.io.CommPortIdentifier;

public class Lector extends Thread {
	SerialComm lineaSerie;
	CommPortIdentifier puerto;
	volatile boolean parar = false;
	public boolean terminado;
	boolean agotado;
	JDialog completoV;
	JDialog completoW;
	boolean existeWarning = false;
	boolean existeCompleto = false;
	JFrame frame;
	public Lector(SerialComm lineaSerie, CommPortIdentifier puerto, JFrame frame) {
		this.lineaSerie = lineaSerie;
		this.puerto = puerto;
		this.frame = frame;
	}

	@Override
	public void run() {
		String mensaje = null;

		try {
			do {
				mensaje = lineaSerie.leer();
				System.out.println(mensaje);
				if (mensaje.contains("W") && !existeWarning) {
					existeWarning = true;
					ventanaCasiCompleto();

				} else if (mensaje.contains("C") && !existeCompleto) {
					existeCompleto = true;
					ventanaCompleto();
				}

			} while (!mensaje.contains("fin"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("fin hilo lector");
	}

	public void ventanaCompleto() {
		JOptionPane.showMessageDialog(null, "El comedor ha llegado a su máximo", "Completo", JOptionPane.ERROR_MESSAGE);
		existeWarning =false;
		existeCompleto=false;
	}

	public void ventanaCasiCompleto() {
		JOptionPane.showMessageDialog(null, "El comedor está a más de su 70% de ocupación", "Casi completo", JOptionPane.WARNING_MESSAGE);
	}

	public void parar() {
		parar = true;
	}

	public boolean isTerminado() {
		return terminado;
	}

	public void setTerminado(boolean terminado) {
		this.terminado = terminado;
	}

	public boolean isAgotado() {
		return agotado;
	}

}

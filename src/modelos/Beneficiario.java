package modelos;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Beneficiario extends Persona {
	String divFuncional;
	String fLlegada;
	String fAlta;
	String fBaja;
	String fCaducidad;
	String comentarios;
	String estadoCivil;
	Tarjeta tarjeta;
	public static String estados[]= {"Casado/a","Viudo/a","Soltero/a","Separado/a","Divorciado/a", "Pareja de hecho"};

	boolean trabajadoraSocial;//
	long tis;//
	
	Date fechaActual;
	SimpleDateFormat formatter;
	
	public Beneficiario(int id, String nombre, String apellido1, String nacionalidad) {
		super(id, nombre, apellido1, nacionalidad);
		formatter  = new SimpleDateFormat("dd-MM-yyyy");
		fAlta = formatter.format(fechaActual);
		setTipoPersona(Persona.tipos[0]);
	}
	
	public Beneficiario(int id, String nombre, String apellido1, String apellido2, String nacionalidad, String fnac, String tipoDoc, 
						String docIden, String direccion, long telefono, String genero, String divFuncional, String fLlegada, /*String fAlta,
						String fBaja, */String fCaducidad, String comentarios, String estadoCivil, boolean traSocial, long tis) {
		super(id, nombre, apellido1, nacionalidad);
		this.apellido2 = apellido2;
		this.fnac = fnac;
		this.tipoDocumento = tipoDoc;
		documentoIdentidad = docIden;
		this.direccion = direccion;
		this.telefono = (telefono);
		this.genero = genero.charAt(0);
		this.divFuncional = divFuncional;
		this.fCaducidad = fCaducidad;
		this.comentarios = comentarios;
		this.estadoCivil = estadoCivil;
		trabajadoraSocial = traSocial;
		this.tis = tis;
		setTipoPersona(Persona.tipos[0]);
	}

	public String getDivFuncional() {
		return divFuncional;
	}

	public void setDivFuncional(String divFuncional) {
		this.divFuncional = divFuncional;
	}

	public String getfLlegada() {
		return fLlegada;
	}

	public void setfLlegada(String fLlegada) {
		this.fLlegada = fLlegada;
	}

	public String getfAlta() {
		return fAlta;
	}

	public void setfAlta(String fAlta) {
		this.fAlta = fAlta;
	}

	public String getfBaja() {
		return fBaja;
	}

	public void setfBaja(String fBaja) {
		this.fBaja = fBaja;
	}

	public String getfCaducidad() {
		return fCaducidad;
	}

	public void setfCaducidad(String fCaducidad) {
		this.fCaducidad = fCaducidad;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public boolean isTrabajadoraSocial() {
		return trabajadoraSocial;
	}

	public void setTrabajadoraSocial(boolean trabajadoraSocial) {
		this.trabajadoraSocial = trabajadoraSocial;
	}

	public long getTis() {
		return tis;
	}

	public void setTis(long tis) {
		this.tis = tis;
	}

	public Date getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}

	public SimpleDateFormat getFormatter() {
		return formatter;
	}

	public void setFormatter(SimpleDateFormat formatter) {
		this.formatter = formatter;
	}

	public Tarjeta getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}
	
	

}

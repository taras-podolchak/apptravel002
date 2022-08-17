package com.appvisibility.apptravel002.ui.entities;

public class Contacto_cnt {

	private	int	id_cnt	;
	private	String cargo_cnt;
	private	String nombre_cnt;
	private	String apellido1_cnt;
	private	String apellido2_cnt;
	private	String movil_cnt;
	private	String telefono_cnt;
	private	String email_cnt;

	public Contacto_cnt() {
		super();
	}

	public Contacto_cnt(int id_cnt, String cargo_cnt, String nombre_cnt, String apellido1_cnt, String apellido2_cnt, String movil_cnt, String telefono_cnt, String email_cnt) {
		this.id_cnt = id_cnt;
		this.cargo_cnt = cargo_cnt;
		this.nombre_cnt = nombre_cnt;
		this.apellido1_cnt = apellido1_cnt;
		this.apellido2_cnt = apellido2_cnt;
		this.movil_cnt = movil_cnt;
		this.telefono_cnt = telefono_cnt;
		this.email_cnt = email_cnt;
	}

	public 	int	getId_cnt	() {return 	id_cnt	;}	public void 	setId_cnt	(int	id_cnt	) {this.	id_cnt=	id_cnt	;}
	public 	String getCargo_cnt() {return cargo_cnt;}	public void setCargo_cnt(String	contactocargo_cnt	) {this.cargo_cnt =	contactocargo_cnt	;}
	public 	String getNombre_cnt() {return nombre_cnt;}	public void setNombre_cnt(String	nombre_cnt	) {this.nombre_cnt =	nombre_cnt	;}
	public 	String getApellido1_cnt() {return apellido1_cnt;}	public void setApellido1_cnt(String	apellido1_cnt	) {this.apellido1_cnt =	apellido1_cnt	;}
	public 	String getApellido2_cnt() {return apellido2_cnt;}	public void setApellido2_cnt(String	apellido2_cnt	) {this.apellido2_cnt =	apellido2_cnt	;}
	public 	String getMovil_cnt() {return movil_cnt;}	public void setMovil_cnt(String	movil_cnt	) {this.movil_cnt =	movil_cnt	;}
	public 	String getTelefono_cnt() {return telefono_cnt;}	public void setTelefono_cnt(String	telefono_cnt	) {this.telefono_cnt =	telefono_cnt	;}
	public 	String getEmail_cnt() {return email_cnt;}	public void setEmail_cnt(String	email_cnt	) {this.email_cnt =	email_cnt	;}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(	id_cnt+	" - ");
//		builder.append(	cargo_cnt+	" - ");
		builder.append(	nombre_cnt +	" - ");
		builder.append(	apellido1_cnt +	" - ");
		builder.append(	apellido2_cnt +	" - ");
		builder.append(	movil_cnt +	" - ");
		builder.append(	telefono_cnt +	" - ");
		builder.append(	email_cnt +	" - ");
		return builder.toString();
	}
}

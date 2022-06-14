package com.appvisibility.apptravel002.ui.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Persona_prs implements Serializable, Parcelable {

	private	int	id_prs	;
	private	String	apodo_prs	;
	private	String	contrasenna_prs	;
	private	boolean	recordarcontrasenna_prs	;
	private	String	actividadtipo_prs	;
	private	String	documentotipo_prs	;
	private	String	dni_prs	;
	private	String	nombre_prs	;
	private	String	apellido1_prs	;
	private	String	apellido2_prs	;
	private	String	razonsocial_prs	;
	private	String	numerocta_prs	;
	private	String	direccion_prs	;
	private	String	localidad_prs	;
	private	String	cpostal_prs	;
	private	String	pais_prs	;
	private	String	movil_prs	;
	private	String	email_prs	;
	private	String	telefono_prs	;
	private	String	web_prs	;
	private	int	usuariotipo_prs	;
	private	String	fotopropia_prs	;
	private	String	fotoorg_prs	;
	private	boolean	federado_prs	;
	private	String	federacionfoto_prs	;
	private	boolean	seguro_prs	;
	private	String	segurocompannia_prs	;
	private	String	seguropoliza_prs	;
	private	String	fechacaducidadseguro_prs	;
	private	String	fechaalta_prs	;
	private	String	antiguedad_prs	;
	private	String	fechabaja_prs	;
	private	boolean	solicitabaja_prs	;
	private	String	contacto1cargo_prs	;
	private	String	contacto1nombre_prs	;
	private	String	contacto1apellido1_prs	;
	private	String	contacto1apellido2_prs	;
	private	String	contacto1movil_prs	;
	private	String	contacto1telefono_prs	;
	private	String	contacto1email_prs	;
	private	String	contacto2cargo_prs	;
	private	String	contacto2nombre_prs	;
	private	String	contacto2apellido1_prs	;
	private	String	contacto2apellido2_prs	;
	private	String	contacto2movil_prs	;
	private	String	contacto2telefono_prs	;
	private	String	contacto2email_prs	;
	private	int	preferencia_prs	;
	private	int	fiabilidadpre_prs	;
	private	int	valoracionorgpre_prs	;
	private	int	antiguedadpre_prs	;
	private	int	volumencomprapre_prs	;
	private	int	cochepre_prs	;
	private	int	nrelacionespre_prs	;
	private	String	coche_prs	;
	private	int	nps01_prs	;
	private	String	nps01fecha_prs	;
	private	int	nps02_prs	;
	private	String	nps02fecha_prs	;
	private	int	nps03_prs	;
	private	String	nps03fecha_prs	;
	private	boolean	condicioneslegales_prs	;

	public Persona_prs() {
		super();
	}

	protected Persona_prs(Parcel in) {
		id_prs = in.readInt();
		apodo_prs = in.readString();
		contrasenna_prs = in.readString();
		recordarcontrasenna_prs = in.readByte() != 0;
		actividadtipo_prs = in.readString();
		documentotipo_prs = in.readString();
		dni_prs = in.readString();
		nombre_prs = in.readString();
		apellido1_prs = in.readString();
		apellido2_prs = in.readString();
		razonsocial_prs = in.readString();
		numerocta_prs = in.readString();
		direccion_prs = in.readString();
		localidad_prs = in.readString();
		cpostal_prs = in.readString();
		pais_prs = in.readString();
		movil_prs = in.readString();
		email_prs = in.readString();
		telefono_prs = in.readString();
		web_prs = in.readString();
		usuariotipo_prs = in.readInt();
		fotopropia_prs = in.readString();
		fotoorg_prs = in.readString();
		federado_prs = in.readByte() != 0;
		federacionfoto_prs = in.readString();
		seguro_prs = in.readByte() != 0;
		segurocompannia_prs = in.readString();
		seguropoliza_prs = in.readString();
		fechacaducidadseguro_prs = in.readString();
		fechaalta_prs = in.readString();
		antiguedad_prs = in.readString();
		fechabaja_prs = in.readString();
		solicitabaja_prs = in.readByte() != 0;
		contacto1cargo_prs = in.readString();
		contacto1nombre_prs = in.readString();
		contacto1apellido1_prs = in.readString();
		contacto1apellido2_prs = in.readString();
		contacto1movil_prs = in.readString();
		contacto1telefono_prs = in.readString();
		contacto1email_prs = in.readString();
		contacto2cargo_prs = in.readString();
		contacto2nombre_prs = in.readString();
		contacto2apellido1_prs = in.readString();
		contacto2apellido2_prs = in.readString();
		contacto2movil_prs = in.readString();
		contacto2telefono_prs = in.readString();
		contacto2email_prs = in.readString();
		preferencia_prs = in.readInt();
		fiabilidadpre_prs = in.readInt();
		valoracionorgpre_prs = in.readInt();
		antiguedadpre_prs = in.readInt();
		volumencomprapre_prs = in.readInt();
		cochepre_prs = in.readInt();
		nrelacionespre_prs = in.readInt();
		coche_prs = in.readString();
		nps01_prs = in.readInt();
		nps01fecha_prs = in.readString();
		nps02_prs = in.readInt();
		nps02fecha_prs = in.readString();
		nps03_prs = in.readInt();
		nps03fecha_prs = in.readString();
		condicioneslegales_prs = in.readByte() != 0;
	}

	public static final Creator<Persona_prs> CREATOR = new Creator<Persona_prs>() {
		@Override
		public Persona_prs createFromParcel(Parcel in) {
			return new Persona_prs(in);
		}

		@Override
		public Persona_prs[] newArray(int size) {
			return new Persona_prs[size];
		}
	};

	public 	int	getId_prs	() {return 	id_prs	;}	public void 	setId_prs	(int	id_prs	) {this.	id_prs=	id_prs	;}
	public 	String	getApodo_prs	() {return 	apodo_prs	;}	public void 	setApodo_prs	(	String	apodo_prs	) {this.	apodo_prs=	apodo_prs	;}
	public 	String	getContrasenna_prs	() {return 	contrasenna_prs	;}	public void 	setContrasenna_prs	(	String	contrasenna_prs	) {this.	contrasenna_prs=	contrasenna_prs	;}
	public 	boolean	getRecordarcontrasenna_prs	() {return 	recordarcontrasenna_prs	;}	public void 	setRecordarcontrasenna_prs	(	boolean	recordarcontrasenna_prs	) {this.	recordarcontrasenna_prs=	recordarcontrasenna_prs	;}
	public 	String	getActividadtipo_prs	() {return 	actividadtipo_prs	;}	public void 	setActividadtipo_prs	(	String	actividadtipo_prs	) {this.	actividadtipo_prs=	actividadtipo_prs	;}
	public 	String	getDocumentotipo_prs	() {return 	documentotipo_prs	;}	public void 	setDocumentotipo_prs	(	String	documentotipo_prs	) {this.	documentotipo_prs=	documentotipo_prs	;}
	public 	String	getDni_prs	() {return 	dni_prs	;}	public void 	setDni_prs	(	String	dni_prs	) {this.	dni_prs=	dni_prs	;}
	public 	String	getNombre_prs	() {return 	nombre_prs	;}	public void 	setNombre_prs	(	String	nombre_prs	) {this.	nombre_prs=	nombre_prs	;}
	public 	String	getApellido1_prs	() {return 	apellido1_prs	;}	public void 	setApellido1_prs	(	String	apellido1_prs	) {this.	apellido1_prs=	apellido1_prs	;}
	public 	String	getApellido2_prs	() {return 	apellido2_prs	;}	public void 	setApellido2_prs	(	String	apellido2_prs	) {this.	apellido2_prs=	apellido2_prs	;}
	public 	String	getRazonsocial_prs	() {return 	razonsocial_prs	;}	public void 	setRazonsocial_prs	(	String	razonsocial_prs	) {this.	razonsocial_prs=	razonsocial_prs	;}
	public 	String	getNumerocta_prs	() {return 	numerocta_prs	;}	public void 	setNumerocta_prs	(	String	numerocta_prs	) {this.	numerocta_prs=	numerocta_prs	;}
	public 	String	getDireccion_prs	() {return 	direccion_prs	;}	public void 	setDireccion_prs	(	String	direccion_prs	) {this.	direccion_prs=	direccion_prs	;}
	public 	String	getLocalidad_prs	() {return 	localidad_prs	;}	public void 	setLocalidad_prs	(	String	localidad_prs	) {this.	localidad_prs=	localidad_prs	;}
	public 	String	getCpostal_prs	() {return 	cpostal_prs	;}	public void 	setCpostal_prs	(	String	cpostal_prs	) {this.	cpostal_prs=	cpostal_prs	;}
	public 	String	getPais_prs	() {return 	pais_prs	;}	public void 	setPais_prs	(	String	pais_prs	) {this.	pais_prs=	pais_prs	;}
	public 	String	getMovil_prs	() {return 	movil_prs	;}	public void 	setMovil_prs	(	String	movil_prs	) {this.	movil_prs=	movil_prs	;}
	public 	String	getEmail_prs	() {return 	email_prs	;}	public void 	setEmail_prs	(	String	email_prs	) {this.	email_prs=	email_prs	;}
	public 	String	getTelefono_prs	() {return 	telefono_prs	;}	public void 	setTelefono_prs	(	String	telefono_prs	) {this.	telefono_prs=	telefono_prs	;}
	public 	String	getWeb_prs	() {return 	web_prs	;}	public void 	setWeb_prs	(	String	web_prs	) {this.	web_prs=	web_prs	;}
	public 	int	getUsuariotipo_prs	() {return 	usuariotipo_prs	;}	public void 	setUsuariotipo_prs	(	int	usuariotipo_prs	) {this.	usuariotipo_prs=	usuariotipo_prs	;}
	public 	String	getFotopropia_prs	() {return 	fotopropia_prs	;}	public void 	setFotopropia_prs	(	String	fotopropia_prs	) {this.	fotopropia_prs=	fotopropia_prs	;}
	public 	String	getFotoorg_prs	() {return 	fotoorg_prs	;}	public void 	setFotoorg_prs	(	String	fotoorg_prs	) {this.	fotoorg_prs=	fotoorg_prs	;}
	public 	boolean	getFederado_prs	() {return 	federado_prs	;}	public void 	setFederado_prs	(	boolean	federado_prs	) {this.	federado_prs=	federado_prs	;}
	public 	String	getFederacionfoto_prs	() {return 	federacionfoto_prs	;}	public void 	setFederacionfoto_prs	(	String	federacionfoto_prs	) {this.	federacionfoto_prs=	federacionfoto_prs	;}
	public 	boolean	getSeguro_prs	() {return 	seguro_prs	;}	public void 	setSeguro_prs	(	boolean	seguro_prs	) {this.	seguro_prs=	seguro_prs	;}
	public 	String	getSegurocompannia_prs	() {return 	segurocompannia_prs	;}	public void 	setSegurocompannia_prs	(	String	segurocompannia_prs	) {this.	segurocompannia_prs=	segurocompannia_prs	;}
	public 	String	getSeguropoliza_prs	() {return 	seguropoliza_prs	;}	public void 	setSeguropoliza_prs	(	String	seguropoliza_prs	) {this.	seguropoliza_prs=	seguropoliza_prs	;}
	public 	String	getFechacaducidadseguro_prs	() {return 	fechacaducidadseguro_prs	;}	public void 	setFechacaducidadseguro_prs	(	String	fechacaducidadseguro_prs	) {this.	fechacaducidadseguro_prs=	fechacaducidadseguro_prs	;}
	public 	String	getFechaalta_prs	() {return 	fechaalta_prs	;}	public void 	setFechaalta_prs	(	String	fechaalta_prs	) {this.	fechaalta_prs=	fechaalta_prs	;}
	public 	String	getAntiguedad_prs	() {return 	antiguedad_prs	;}	public void 	setAntiguedad_prs	(	String	antiguedad_prs	) {this.	antiguedad_prs=	antiguedad_prs	;}
	public 	String	getFechabaja_prs	() {return 	fechabaja_prs	;}	public void 	setFechabaja_prs	(	String	fechabaja_prs	) {this.	fechabaja_prs=	fechabaja_prs	;}
	public 	boolean	getSolicitabaja_prs	() {return 	solicitabaja_prs	;}	public void 	setSolicitabaja_prs	(	boolean	solicitabaja_prs	) {this.	solicitabaja_prs=	solicitabaja_prs	;}
	public 	String	getContacto1Cargo_prs	() {return 	contacto1cargo_prs	;}	public void 	setContacto1Cargo_prs	(	String	contacto1cargo_prs	) {this.	contacto1cargo_prs=	contacto1cargo_prs	;}
	public 	String	getContacto1Nombre_prs	() {return 	contacto1nombre_prs	;}	public void 	setContacto1Nombre_prs	(	String	contacto1nombre_prs	) {this.	contacto1nombre_prs=	contacto1nombre_prs	;}
	public 	String	getContacto1Apellido1_prs	() {return 	contacto1apellido1_prs	;}	public void 	setContacto1Apellido1_prs	(	String	contacto1apellido1_prs	) {this.	contacto1apellido1_prs=	contacto1apellido1_prs	;}
	public 	String	getContacto1Apellido2_prs	() {return 	contacto1apellido2_prs	;}	public void 	setContacto1Apellido2_prs	(	String	contacto1apellido2_prs	) {this.	contacto1apellido2_prs=	contacto1apellido2_prs	;}
	public 	String	getContacto1Movil_prs	() {return 	contacto1movil_prs	;}	public void 	setContacto1Movil_prs	(	String	contacto1movil_prs	) {this.	contacto1movil_prs=	contacto1movil_prs	;}
	public 	String	getContacto1Telefono_prs	() {return 	contacto1telefono_prs	;}	public void 	setContacto1Telefono_prs	(	String	contacto1telefono_prs	) {this.	contacto1telefono_prs=	contacto1telefono_prs	;}
	public 	String	getContacto1Email_prs	() {return 	contacto1email_prs	;}	public void 	setContacto1Email_prs	(	String	contacto1email_prs	) {this.	contacto1email_prs=	contacto1email_prs	;}
	public 	String	getContacto2Cargo_prs	() {return 	contacto2cargo_prs	;}	public void 	setContacto2Cargo_prs	(	String	contacto2cargo_prs	) {this.	contacto2cargo_prs=	contacto2cargo_prs	;}
	public 	String	getContacto2Nombre_prs	() {return 	contacto2nombre_prs	;}	public void 	setContacto2Nombre_prs	(	String	contacto2nombre_prs	) {this.	contacto2nombre_prs=	contacto2nombre_prs	;}
	public 	String	getContacto2Apellido1_prs	() {return 	contacto2apellido1_prs	;}	public void 	setContacto2Apellido1_prs	(	String	contacto2apellido1_prs	) {this.	contacto2apellido1_prs=	contacto2apellido1_prs	;}
	public 	String	getContacto2Apellido2_prs	() {return 	contacto2apellido2_prs	;}	public void 	setContacto2Apellido2_prs	(	String	contacto2apellido2_prs	) {this.	contacto2apellido2_prs=	contacto2apellido2_prs	;}
	public 	String	getContacto2Movil_prs	() {return 	contacto2movil_prs	;}	public void 	setContacto2Movil_prs	(	String	contacto2movil_prs	) {this.	contacto2movil_prs=	contacto2movil_prs	;}
	public 	String	getContacto2Telefono_prs	() {return 	contacto2telefono_prs	;}	public void 	setContacto2Telefono_prs	(	String	contacto2telefono_prs	) {this.	contacto2telefono_prs=	contacto2telefono_prs	;}
	public 	String	getContacto2Email_prs	() {return 	contacto2email_prs	;}	public void 	setContacto2Email_prs	(	String	contacto2email_prs	) {this.	contacto2email_prs=	contacto2email_prs	;}
	public 	int	getPreferencia_prs	() {return 	preferencia_prs	;}	public void 	setPreferencia_prs	(	int	preferencia_prs	) {this.	preferencia_prs=	preferencia_prs	;}
	public 	int	getFiabilidadpre_prs	() {return 	fiabilidadpre_prs	;}	public void 	setFiabilidadpre_prs	(	int	fiabilidadpre_prs	) {this.	fiabilidadpre_prs=	fiabilidadpre_prs	;}
	public 	int	getValoracionorgpre_prs	() {return 	valoracionorgpre_prs	;}	public void 	setValoracionorgpre_prs	(	int	valoracionorgpre_prs	) {this.	valoracionorgpre_prs=	valoracionorgpre_prs	;}
	public 	int	getAntiguedadpre_prs	() {return 	antiguedadpre_prs	;}	public void 	setAntiguedadpre_prs	(	int	antiguedadpre_prs	) {this.	antiguedadpre_prs=	antiguedadpre_prs	;}
	public 	int	getVolumencomprapre_prs	() {return 	volumencomprapre_prs	;}	public void 	setVolumencomprapre_prs	(	int	volumencomprapre_prs	) {this.	volumencomprapre_prs=	volumencomprapre_prs	;}
	public 	int	getCochepre_prs	() {return 	cochepre_prs	;}	public void 	setCochepre_prs	(	int	cochepre_prs	) {this.	cochepre_prs=	cochepre_prs	;}
	public 	int	getNrelacionespre_prs	() {return 	nrelacionespre_prs	;}	public void 	setNrelacionespre_prs	(	int	nrelacionespre_prs	) {this.	nrelacionespre_prs=	nrelacionespre_prs	;}
	public 	String	getCoche_prs	() {return 	coche_prs	;}	public void 	setCoche_prs	(	String	coche_prs	) {this.	coche_prs=	coche_prs	;}
	public 	int	getNps01_prs	() {return 	nps01_prs	;}	public void 	setNps01_prs	(	int	nps01_prs	) {this.	nps01_prs=	nps01_prs	;}
	public 	String	getNps01Fecha_prs	() {return 	nps01fecha_prs	;}	public void 	setNps01Fecha_prs	(	String	nps01fecha_prs	) {this.	nps01fecha_prs=	nps01fecha_prs	;}
	public 	int	getNps02_prs	() {return 	nps02_prs	;}	public void 	setNps02_prs	(	int	nps02_prs	) {this.	nps02_prs=	nps02_prs	;}
	public 	String	getNps02Fecha_prs	() {return 	nps02fecha_prs	;}	public void 	setNps02Fecha_prs	(	String	nps02fecha_prs	) {this.	nps02fecha_prs=	nps02fecha_prs	;}
	public 	int	getNps03_prs	() {return 	nps03_prs	;}	public void 	setNps03_prs	(	int	nps03_prs	) {this.	nps03_prs=	nps03_prs	;}
	public 	String	getNps03Fecha_prs	() {return 	nps03fecha_prs	;}	public void 	setNps03Fecha_prs	(	String	nps03fecha_prs	) {this.	nps03fecha_prs=	nps03fecha_prs	;}
	public 	boolean	getCondicioneslegales_prs	() {return 	condicioneslegales_prs	;}	public void 	setCondicioneslegales_prs	(	boolean	condicioneslegales_prs	) {this.	condicioneslegales_prs=	condicioneslegales_prs	;}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("	Persona	 [	id_prs=	");	builder.append(	id_prs+	" - ");
		builder.append("	0	 [	apodo_prs=	");	builder.append(	apodo_prs+	" - ");
		builder.append("	private	 [	contrasenna_prs=	");	builder.append(	contrasenna_prs+	" - ");
		builder.append("	private	 [	recordarcontrasenna_prs=	");	builder.append(	recordarcontrasenna_prs+	" - ");
		builder.append("	private	 [	actividadtipo_prs=	");	builder.append(	actividadtipo_prs+	" - ");
		builder.append("	private	 [	documentotipo_prs=	");	builder.append(	documentotipo_prs+	" - ");
		builder.append("	private	 [	dni_prs=	");	builder.append(	dni_prs+	" - ");
		builder.append("	private	 [	nombre_prs=	");	builder.append(	nombre_prs+	" - ");
		builder.append("	private	 [	apellido1_prs=	");	builder.append(	apellido1_prs+	" - ");
		builder.append("	private	 [	apellido2_prs=	");	builder.append(	apellido2_prs+	" - ");
		builder.append("	private	 [	razonsocial_prs=	");	builder.append(	razonsocial_prs+	" - ");
		builder.append("	private	 [	numerocta_prs=	");	builder.append(	numerocta_prs+	" - ");
		builder.append("	private	 [	direccion_prs=	");	builder.append(	direccion_prs+	" - ");
		builder.append("	private	 [	localidad_prs=	");	builder.append(	localidad_prs+	" - ");
		builder.append("	private	 [	cpostal_prs=	");	builder.append(	cpostal_prs+	" - ");
		builder.append("	private	 [	pais_prs=	");	builder.append(	pais_prs+	" - ");
		builder.append("	private	 [	movil_prs=	");	builder.append(	movil_prs+	" - ");
		builder.append("	private	 [	email_prs=	");	builder.append(	email_prs+	" - ");
		builder.append("	private	 [	telefono_prs=	");	builder.append(	telefono_prs+	" - ");
		builder.append("	private	 [	web_prs=	");	builder.append(	web_prs+	" - ");
		builder.append("	private	 [	usuariotipo_prs=	");	builder.append(	usuariotipo_prs+	" - ");
		builder.append("	private	 [	fotopropia_prs=	");	builder.append(	fotopropia_prs+	" - ");
		builder.append("	private	 [	fotoorg_prs=	");	builder.append(	fotoorg_prs+	" - ");
		builder.append("	private	 [	federado_prs=	");	builder.append(	federado_prs+	" - ");
		builder.append("	private	 [	federacionfoto_prs=	");	builder.append(	federacionfoto_prs+	" - ");
		builder.append("	private	 [	seguro_prs=	");	builder.append(	seguro_prs+	" - ");
		builder.append("	private	 [	segurocompannia_prs=	");	builder.append(	segurocompannia_prs+	" - ");
		builder.append("	private	 [	seguropoliza_prs=	");	builder.append(	seguropoliza_prs+	" - ");
		builder.append("	private	 [	fechacaducidadseguro_prs=	");	builder.append(	fechacaducidadseguro_prs+	" - ");
		builder.append("	private	 [	fechaalta_prs=	");	builder.append(	fechaalta_prs+	" - ");
		builder.append("	private	 [	antiguedad_prs=	");	builder.append(	antiguedad_prs+	" - ");
		builder.append("	private	 [	fechabaja_prs=	");	builder.append(	fechabaja_prs+	" - ");
		builder.append("	private	 [	solicitabaja_prs=	");	builder.append(	solicitabaja_prs+	" - ");
		builder.append("	private	 [	contacto1cargo_prs=	");	builder.append(	contacto1cargo_prs+	" - ");
		builder.append("	private	 [	contacto1nombre_prs=	");	builder.append(	contacto1nombre_prs+	" - ");
		builder.append("	private	 [	contacto1apellido1_prs=	");	builder.append(	contacto1apellido1_prs+	" - ");
		builder.append("	private	 [	contacto1apellido2_prs=	");	builder.append(	contacto1apellido2_prs+	" - ");
		builder.append("	private	 [	contacto1movil_prs=	");	builder.append(	contacto1movil_prs+	" - ");
		builder.append("	private	 [	contacto1telefono_prs=	");	builder.append(	contacto1telefono_prs+	" - ");
		builder.append("	private	 [	contacto1email_prs=	");	builder.append(	contacto1email_prs+	" - ");
		builder.append("	private	 [	contacto2cargo_prs=	");	builder.append(	contacto2cargo_prs+	" - ");
		builder.append("	private	 [	contacto2nombre_prs=	");	builder.append(	contacto2nombre_prs+	" - ");
		builder.append("	private	 [	contacto2apellido1_prs=	");	builder.append(	contacto2apellido1_prs+	" - ");
		builder.append("	private	 [	contacto2apellido2_prs=	");	builder.append(	contacto2apellido2_prs+	" - ");
		builder.append("	private	 [	contacto2movil_prs=	");	builder.append(	contacto2movil_prs+	" - ");
		builder.append("	private	 [	contacto2telefono_prs=	");	builder.append(	contacto2telefono_prs+	" - ");
		builder.append("	private	 [	contacto2email_prs=	");	builder.append(	contacto2email_prs+	" - ");
		builder.append("	private	 [	preferencia_prs=	");	builder.append(	preferencia_prs+	" - ");
		builder.append("	private	 [	fiabilidadpre_prs=	");	builder.append(	fiabilidadpre_prs+	" - ");
		builder.append("	private	 [	valoracionorgpre_prs=	");	builder.append(	valoracionorgpre_prs+	" - ");
		builder.append("	private	 [	antiguedadpre_prs=	");	builder.append(	antiguedadpre_prs+	" - ");
		builder.append("	private	 [	volumencomprapre_prs=	");	builder.append(	volumencomprapre_prs+	" - ");
		builder.append("	private	 [	cochepre_prs=	");	builder.append(	cochepre_prs+	" - ");
		builder.append("	private	 [	nrelacionespre_prs=	");	builder.append(	nrelacionespre_prs+	" - ");
		builder.append("	private	 [	coche_prs=	");	builder.append(	coche_prs+	" - ");
		builder.append("	private	 [	nps01_prs=	");	builder.append(	nps01_prs+	" - ");
		builder.append("	private	 [	nps01fecha_prs=	");	builder.append(	nps01fecha_prs+	" - ");
		builder.append("	private	 [	nps02_prs=	");	builder.append(	nps02_prs+	" - ");
		builder.append("	private	 [	nps02fecha_prs=	");	builder.append(	nps02fecha_prs+	" - ");
		builder.append("	private	 [	nps03_prs=	");	builder.append(	nps03_prs+	" - ");
		builder.append("	private	 [	nps03fecha_prs=	");	builder.append(	nps03fecha_prs+	" - ");
		builder.append(", 			condicioneslegales_prs=	");	builder.append(	condicioneslegales_prs+	" - ");
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id_prs);
		dest.writeString(apodo_prs);
		dest.writeString(contrasenna_prs);
		dest.writeByte((byte) (recordarcontrasenna_prs ? 1 : 0));
		dest.writeString(actividadtipo_prs);
		dest.writeString(documentotipo_prs);
		dest.writeString(dni_prs);
		dest.writeString(nombre_prs);
		dest.writeString(apellido1_prs);
		dest.writeString(apellido2_prs);
		dest.writeString(razonsocial_prs);
		dest.writeString(numerocta_prs);
		dest.writeString(direccion_prs);
		dest.writeString(localidad_prs);
		dest.writeString(cpostal_prs);
		dest.writeString(pais_prs);
		dest.writeString(movil_prs);
		dest.writeString(email_prs);
		dest.writeString(telefono_prs);
		dest.writeString(web_prs);
		dest.writeInt(usuariotipo_prs);
		dest.writeString(fotopropia_prs);
		dest.writeString(fotoorg_prs);
		dest.writeByte((byte) (federado_prs ? 1 : 0));
		dest.writeString(federacionfoto_prs);
		dest.writeByte((byte) (seguro_prs ? 1 : 0));
		dest.writeString(segurocompannia_prs);
		dest.writeString(seguropoliza_prs);
		dest.writeString(fechacaducidadseguro_prs);
		dest.writeString(fechaalta_prs);
		dest.writeString(antiguedad_prs);
		dest.writeString(fechabaja_prs);
		dest.writeByte((byte) (solicitabaja_prs ? 1 : 0));
		dest.writeString(contacto1cargo_prs);
		dest.writeString(contacto1nombre_prs);
		dest.writeString(contacto1apellido1_prs);
		dest.writeString(contacto1apellido2_prs);
		dest.writeString(contacto1movil_prs);
		dest.writeString(contacto1telefono_prs);
		dest.writeString(contacto1email_prs);
		dest.writeString(contacto2cargo_prs);
		dest.writeString(contacto2nombre_prs);
		dest.writeString(contacto2apellido1_prs);
		dest.writeString(contacto2apellido2_prs);
		dest.writeString(contacto2movil_prs);
		dest.writeString(contacto2telefono_prs);
		dest.writeString(contacto2email_prs);
		dest.writeInt(preferencia_prs);
		dest.writeInt(fiabilidadpre_prs);
		dest.writeInt(valoracionorgpre_prs);
		dest.writeInt(antiguedadpre_prs);
		dest.writeInt(volumencomprapre_prs);
		dest.writeInt(cochepre_prs);
		dest.writeInt(nrelacionespre_prs);
		dest.writeString(coche_prs);
		dest.writeInt(nps01_prs);
		dest.writeString(nps01fecha_prs);
		dest.writeInt(nps02_prs);
		dest.writeString(nps02fecha_prs);
		dest.writeInt(nps03_prs);
		dest.writeString(nps03fecha_prs);
		dest.writeByte((byte) (condicioneslegales_prs ? 1 : 0));
	}
}

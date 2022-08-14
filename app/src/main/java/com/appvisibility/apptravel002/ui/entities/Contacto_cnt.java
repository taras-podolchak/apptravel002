package com.appvisibility.apptravel002.ui.entities;

import java.io.Serializable;

public class Contacto_cnt {

	private	int	id_cnt	;
	private	String contactocargo_cnt;
	private	String contactonombre_cnt;
	private	String contactoapellido1_cnt;
	private	String contactoapellido2_cnt;
	private	String contactomovil_cnt;
	private	String contactotelefono_cnt;
	private	String contactoemail_cnt;

	public Contacto_cnt() {
		super();
	}

	public Contacto_cnt(int id_cnt, String contactocargo_cnt, String contactonombre_cnt, String contactoapellido1_cnt, String contactoapellido2_cnt, String contactomovil_cnt, String contactotelefono_cnt, String contactoemail_cnt) {
		this.id_cnt = id_cnt;
		this.contactocargo_cnt = contactocargo_cnt;
		this.contactonombre_cnt = contactonombre_cnt;
		this.contactoapellido1_cnt = contactoapellido1_cnt;
		this.contactoapellido2_cnt = contactoapellido2_cnt;
		this.contactomovil_cnt = contactomovil_cnt;
		this.contactotelefono_cnt = contactotelefono_cnt;
		this.contactoemail_cnt = contactoemail_cnt;
	}

	public 	int	getId_cnt	() {return 	id_cnt	;}	public void 	setId_cnt	(int	id_cnt	) {this.	id_cnt=	id_cnt	;}
	public 	String	getContactoCargo_cnt	() {return contactocargo_cnt;}	public void 	setContactoCargo_cnt	(String	contactocargo_cnt	) {this.contactocargo_cnt =	contactocargo_cnt	;}
	public 	String	getContactoNombre_cnt	() {return contactonombre_cnt;}	public void 	setContactoNombre_cnt	(String	contactonombre_cnt	) {this.contactonombre_cnt =	contactonombre_cnt	;}
	public 	String	getContactoApellido1_cnt	() {return contactoapellido1_cnt;}	public void 	setContactoApellido1_cnt	(String	contactoapellido1_cnt	) {this.contactoapellido1_cnt =	contactoapellido1_cnt	;}
	public 	String	getContactoApellido2_cnt	() {return contactoapellido2_cnt;}	public void 	setContactoApellido2_cnt	(String	contactoapellido2_cnt	) {this.contactoapellido2_cnt =	contactoapellido2_cnt	;}
	public 	String	getContactoMovil_cnt	() {return contactomovil_cnt;}	public void 	setContactoMovil_cnt	(String	contactomovil_cnt	) {this.contactomovil_cnt =	contactomovil_cnt	;}
	public 	String	getContactoTelefono_cnt	() {return contactotelefono_cnt;}	public void 	setContactoTelefono_cnt	(String	contactotelefono_cnt	) {this.contactotelefono_cnt =	contactotelefono_cnt	;}
	public 	String	getContactoEmail_cnt	() {return contactoemail_cnt;}	public void 	setContactoEmail_cnt	(String	contactoemail_cnt	) {this.contactoemail_cnt =	contactoemail_cnt	;}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(	id_cnt+	" - ");
//		builder.append(	contactocargo_cnt+	" - ");
		builder.append(	contactonombre_cnt +	" - ");
		builder.append(	contactoapellido1_cnt +	" - ");
		builder.append(	contactoapellido2_cnt +	" - ");
		builder.append(	contactomovil_cnt +	" - ");
		builder.append(	contactotelefono_cnt +	" - ");
		builder.append(	contactoemail_cnt +	" - ");
		return builder.toString();
	}
}

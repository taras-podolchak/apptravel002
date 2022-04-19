package com.appvisibility.apptravel002.ui.entities;

import java.io.Serializable;

public class 	Participar_evetpr implements Serializable	{													
															
	private	int	id_evetpr	;											
	private	int	id_eve	;											
	private	int	id_tpr	;											
															
	public 	Participar_evetpr	() {												
		super();													
	}														
															
	public 	Participar_evetpr	(												
			int	id_evetpr	,										
			int	id_eve	,										
			int	id_tpr											
			) {												
		super();													
		this.	id_evetpr=	id_evetpr	;										
		this.	id_eve=	id_eve	;										
		this.	id_tpr=	id_tpr	;										
	}														
															
	public 	int	getId_Evetpr	() {return 	id_evetpr	;}	public void 	setId_Evetpr	(	int	id_evetpr	) {this.	id_evetpr=	id_evetpr	;}
	public 	int	getId_Eve	() {return 	id_eve	;}	public void 	setId_Eve	(	int	id_eve	) {this.	id_eve=	id_eve	;}
	public 	int	getId_Tpr	() {return 	id_tpr	;}	public void 	setId_Tpr	(	int	id_tpr	) {this.	id_tpr=	id_tpr	;}
															
	@Override														
	public String toString() {														
		StringBuilder builder = new StringBuilder();													
		builder.append("	Participar_evetpr	 [	id_evetpr=	");	builder.append(	id_evetpr+	" - ");						
		builder.append(", 			id_eve=	");	builder.append(	id_eve+	" - ");						
		builder.append(", 			id_tpr=	");	builder.append(	id_tpr+	" - ");						
		builder.append("]");													
		return builder.toString();													
	}														
}															

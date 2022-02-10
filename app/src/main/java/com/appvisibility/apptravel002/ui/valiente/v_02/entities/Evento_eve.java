package com.appvisibility.apptravel002.ui.valiente.v_02.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Evento_eve implements Serializable {
//public class Evento_eve extends ArrayList<CharSequence> implements Serializable {

    private	int	id_eve	;
    private	String	titulo_eve	;
    private	int	nparticipantes_eve	;
    private	boolean	viajeconavion_eve	;
    private	String	fechapagosennal_eve	;
    private	String	fechapagototal_eve	;
    private	int	precio_eve	;
    private	String	foto_eve	;
    private	String	descgeneral_eve	;
    private	String	descrecomendaciones_eve	;
    private	String	descseguridad_eve	;
    private	String	nivel_eve	;
    private	String	transportetipo_eve	;
    private	String	fechaidatru_eve	;
    private	String	salidaidatru_eve	;
    private	String	salidacoordenadastru_eve	;
    private	String	llegadaidatru_eve	;
    private	String	llegadacoordenadastru_eve	;
    private	int	distanciaidatru_eve	;
    private	String	fechavueltatru_eve	;
    private	String	salidavueltatru_eve	;
    private	String	llegadavueltatru_eve	;
    private	int	distanciavueltatru_eve	;
    private	String	estado_eve	;
    private	String	descestado_eve	;

    public Evento_eve() {
        super();
    }

    public Evento_eve(
            int	id_eve	,
            String	titulo_eve	,
            int	nparticipantes_eve	,
            String	foto_eve	,
            String	nivel_eve	,
            String	transportetipo_eve	,
            String	fechaidatru_eve	,
            String	fechavueltatru_eve
    ) {
        super();
        this.	id_eve=	id_eve	;
        this.	titulo_eve=	titulo_eve	;
        this.	nparticipantes_eve=	nparticipantes_eve	;
        this.	foto_eve=	foto_eve	;
        this.	nivel_eve=	nivel_eve	;
        this.	transportetipo_eve=  transportetipo_eve	;
        this.	fechaidatru_eve=	fechaidatru_eve	;
        this.	fechavueltatru_eve=	fechavueltatru_eve	;
    }

    public Evento_eve(
            int	id_eve	,
            String	titulo_eve	,
            int	nparticipantes_eve	,
            boolean	viajeconavion_eve	,
            String	fechapagosennal_eve	,
            String	fechapagototal_eve	,
            int	precio_eve	,
            String	foto_eve	,
            String	descgeneral_eve	,
            String	descrecomendaciones_eve	,
            String	descseguridad_eve	,
            String	nivel_eve	,
            String	transportetipo_eve	,
            String	fechaidatru_eve	,
            String	salidaidatru_eve	,
            String	salidacoordenadastru_eve	,
            String	llegadaidatru_eve	,
            String	llegadacoordenadastru_eve	,
            int	distanciaidatru_eve	,
            String	fechavueltatru_eve	,
            String	salidavueltatru_eve	,
            String	llegadavueltatru_eve	,
            int	distanciavueltatru_eve	,
            String	estado_eve	,
            String	descestado_eve
    ) {
        super();
        this.	id_eve=	id_eve	;
        this.	titulo_eve=	titulo_eve	;
        this.	nparticipantes_eve=	nparticipantes_eve	;
        this.	viajeconavion_eve=	viajeconavion_eve	;
        this.	fechapagosennal_eve=	fechapagosennal_eve	;
        this.	fechapagototal_eve=	fechapagototal_eve	;
        this.	precio_eve=	precio_eve	;
        this.	foto_eve=	foto_eve	;
        this.	descgeneral_eve=	descgeneral_eve	;
        this.	descrecomendaciones_eve=	descrecomendaciones_eve	;
        this.	descseguridad_eve=	descseguridad_eve	;
        this.	nivel_eve=	nivel_eve	;
        this.	transportetipo_eve=	transportetipo_eve	;
        this.	fechaidatru_eve=	fechaidatru_eve	;
        this.	salidaidatru_eve=	salidaidatru_eve	;
        this.	salidacoordenadastru_eve=	salidacoordenadastru_eve	;
        this.	llegadaidatru_eve=	llegadaidatru_eve	;
        this.	llegadacoordenadastru_eve=	llegadacoordenadastru_eve	;
        this.	distanciaidatru_eve=	distanciaidatru_eve	;
        this.	fechavueltatru_eve=	fechavueltatru_eve	;
        this.	salidavueltatru_eve=	salidavueltatru_eve	;
        this.	llegadavueltatru_eve=	llegadavueltatru_eve	;
        this.	distanciavueltatru_eve=	distanciavueltatru_eve	;
        this.	estado_eve=	estado_eve	;
        this.	descestado_eve=	descestado_eve;
    }

    public 	int	getId_eve	() {return 	id_eve	;}	public void 	setId_eve	(	int	id_eve	) {this.	id_eve=	id_eve	;}
    public 	String	getTitulo_eve	() {return 	titulo_eve	;}	public void 	setTitulo_eve	(	String	titulo_eve	) {this.	titulo_eve=	titulo_eve	;}
    public 	int	getNparticipantes_eve	() {return 	nparticipantes_eve	;}	public void 	setNparticipantes_eve	(	int	nparticipantes_eve	) {this.	nparticipantes_eve=	nparticipantes_eve	;}
    public 	boolean	getViajeconavion_eve	() {return 	viajeconavion_eve	;}	public void 	setViajeconavion_eve	(	boolean	viajeconavion_eve	) {this.	viajeconavion_eve=	viajeconavion_eve	;}
    public 	String	getFechapagosennal_eve	() {return 	fechapagosennal_eve	;}	public void 	setFechapagosennal_eve	(	String	fechapagosennal_eve	) {this.	fechapagosennal_eve=	fechapagosennal_eve	;}
    public 	String	getFechapagototal_eve	() {return 	fechapagototal_eve	;}	public void 	setFechapagototal_eve	(	String	fechapagototal_eve	) {this.	fechapagototal_eve=	fechapagototal_eve	;}
    public 	int	getPrecio_eve	() {return 	precio_eve	;}	public void 	setPrecio_eve	(	int	precio_eve	) {this.	precio_eve=	precio_eve	;}
    public String getFoto_eve	() {return 	foto_eve	;}	public void 	setFoto_eve	(String	foto_eve	) {this.	foto_eve=	foto_eve	;}
    public 	String	getDescgeneral_eve	() {return 	descgeneral_eve	;}	public void 	setDescgeneral_eve	(	String	descgeneral_eve	) {this.	descgeneral_eve=	descgeneral_eve	;}
    public 	String	getDescrecomendaciones_eve	() {return 	descrecomendaciones_eve	;}	public void 	setDescrecomendaciones_eve	(	String	descrecomendaciones_eve	) {this.	descrecomendaciones_eve=	descrecomendaciones_eve	;}
    public 	String	getDescseguridad_eve	() {return 	descseguridad_eve	;}	public void 	setDescseguridad_eve	(	String	descseguridad_eve	) {this.	descseguridad_eve=	descseguridad_eve	;}
    public 	String	getNivel_eve	() {return 	nivel_eve	;}	public void 	setNivel_eve	(	String	nivel_eve	) {this.	nivel_eve=	nivel_eve	;}
    public 	String	getTransportetipo_eve	() {return 	transportetipo_eve	;}	public void 	setTransportetipo_eve	(	String	transportetipo_eve	) {this.	transportetipo_eve=	transportetipo_eve	;}
    public 	String	getFechaidatru_eve	() {return 	fechaidatru_eve	;}	public void 	setFechaidatru_eve	(	String	fechaidatru_eve	) {this.	fechaidatru_eve=	fechaidatru_eve	;}
    public 	String	getSalidaidatru_eve	() {return 	salidaidatru_eve	;}	public void 	setSalidaidatru_eve	(	String	salidaidatru_eve	) {this.	salidaidatru_eve=	salidaidatru_eve	;}
    public 	String	getSalidacoordenadastru_eve	() {return 	salidacoordenadastru_eve	;}	public void 	setSalidacoordenadastru_eve	(	String	salidacoordenadastru_eve	) {this.	salidacoordenadastru_eve=	salidacoordenadastru_eve	;}
    public 	String	getLlegadaidatru_eve	() {return 	llegadaidatru_eve	;}	public void 	setLlegadaidatru_eve	(	String	llegadaidatru_eve	) {this.	llegadaidatru_eve=	llegadaidatru_eve	;}
    public 	String	getLlegadacoordenadastru_eve	() {return 	llegadacoordenadastru_eve	;}	public void 	setLlegadacoordenadastru_eve	(	String	llegadacoordenadastru_eve	) {this.	llegadacoordenadastru_eve=	llegadacoordenadastru_eve	;}
    public 	int	getDistanciaidatru_eve	() {return 	distanciaidatru_eve	;}	public void 	setDistanciaidatru_eve	(	int	distanciaidatru_eve	) {this.	distanciaidatru_eve=	distanciaidatru_eve	;}
    public 	String	getFechavueltatru_eve	() {return 	fechavueltatru_eve	;}	public void 	setFechavueltatru_eve	(	String	fechavueltatru_eve	) {this.	fechavueltatru_eve=	fechavueltatru_eve	;}
    public 	String	getSalidavueltatru_eve	() {return 	salidavueltatru_eve	;}	public void 	setSalidavueltatru_eve	(	String	salidavueltatru_eve	) {this.	salidavueltatru_eve=	salidavueltatru_eve	;}
    public 	String	getLlegadavueltatru_eve	() {return 	llegadavueltatru_eve	;}	public void 	setLlegadavueltatru_eve	(	String	llegadavueltatru_eve	) {this.	llegadavueltatru_eve=	llegadavueltatru_eve	;}
    public 	int	getDistanciavueltatru_eve	() {return 	distanciavueltatru_eve	;}	public void 	setDistanciavueltatru_eve	(	int	distanciavueltatru_eve	) {this.	distanciavueltatru_eve=	distanciavueltatru_eve	;}
    public 	String	getEstado_eve	() {return 	estado_eve	;}	public void 	setEstado_eve	(	String	estado_eve	) {this.	estado_eve=	estado_eve	;}
    public 	String	getDescestado_eve	() {return 	descestado_eve	;}	public void 	setDescestado_eve	(	String	descestado_eve	) {this.	descestado_eve=	descestado_eve	;}

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("	Empleado	 [	id_eve=	");	builder.append(	id_eve+	" - ");
        builder.append("	0	 [	titulo_eve=	");	builder.append(	titulo_eve+	" - ");
        builder.append("	0	 [	nparticipantes_eve=	");	builder.append(	nparticipantes_eve+	" - ");
        builder.append("	0	 [	viajeconavion_eve=	");	builder.append(	viajeconavion_eve+	" - ");
        builder.append("	0	 [	fechapagosennal_eve=	");	builder.append(	fechapagosennal_eve+	" - ");
        builder.append("	0	 [	fechapagototal_eve=	");	builder.append(	fechapagototal_eve+	" - ");
        builder.append("	0	 [	precio_eve=	");	builder.append(	precio_eve+	" - ");
        builder.append("	0	 [	foto_eve=	");	builder.append(	foto_eve+	" - ");
        builder.append("	0	 [	descgeneral_eve=	");	builder.append(	descgeneral_eve+	" - ");
        builder.append("	0	 [	descrecomendaciones_eve=	");	builder.append(	descrecomendaciones_eve+	" - ");
        builder.append("	0	 [	descseguridad_eve=	");	builder.append(	descseguridad_eve+	" - ");
        builder.append("	0	 [	nivel_eve=	");	builder.append(	nivel_eve+	" - ");
        builder.append("	0	 [	transportetipo_eve=	");	builder.append(	transportetipo_eve+	" - ");
        builder.append("	0	 [	fechaidatru_eve=	");	builder.append(	fechaidatru_eve+	" - ");
        builder.append("	0	 [	salidaidatru_eve=	");	builder.append(	salidaidatru_eve+	" - ");
        builder.append("	0	 [	salidacoordenadastru_eve=	");	builder.append(	salidacoordenadastru_eve+	" - ");
        builder.append("	0	 [	llegadaidatru_eve=	");	builder.append(	llegadaidatru_eve+	" - ");
        builder.append("	0	 [	llegadacoordenadastru_eve=	");	builder.append(	llegadacoordenadastru_eve+	" - ");
        builder.append("	0	 [	distanciaidatru_eve=	");	builder.append(	distanciaidatru_eve+	" - ");
        builder.append("	0	 [	fechavueltatru_eve=	");	builder.append(	fechavueltatru_eve+	" - ");
        builder.append("	0	 [	salidavueltatru_eve=	");	builder.append(	salidavueltatru_eve+	" - ");
        builder.append("	0	 [	llegadavueltatru_eve=	");	builder.append(	llegadavueltatru_eve+	" - ");
        builder.append("	0	 [	distanciavueltatru_eve=	");	builder.append(	distanciavueltatru_eve+	" - ");
        builder.append("	0	 [	estado_eve=	");	builder.append(	estado_eve+	" - ");
        builder.append(", 			descestado_eve=	");	builder.append(	descestado_eve+	" - ");
        builder.append("]");
        return builder.toString();
    }
}

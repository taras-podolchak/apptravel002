package com.appvisibility.apptravel002.ui.entities;

public class Transportepropio_tpr {

    private	int	id_tpr	;
    private	int	plazaslibres_tpr	;

    public 	Transportepropio_tpr	() {
        super();
    }

    public 	Transportepropio_tpr	(
            int	id_tpr	,
            int	plazaslibres_tpr
    ) {
        super();
        this.	id_tpr=	id_tpr	;
        this.	plazaslibres_tpr=	plazaslibres_tpr ;
    }

    public 	int	getId_Tpr	() {return 	id_tpr	;}	public void 	setId_Tpr	(	int	id_tpr	) {this.	id_tpr=	id_tpr	;}
    public 	int	getPlazaslibres_Tpr	() {return 	plazaslibres_tpr	;}	public void 	setPlazaslibres_Tpr	(	int	plazaslibres_tpr	) {this.	plazaslibres_tpr=	plazaslibres_tpr	;}

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("	Transportepropio_tpr	 [	id_tpr=	");	builder.append(	id_tpr+	" - ");
        builder.append(", 			plazaslibres_tpr=	");	builder.append(	plazaslibres_tpr+	" - ");
        builder.append("]");
        return builder.toString();
    }
}
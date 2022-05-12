package com.appvisibility.apptravel002.ui.entities;

import java.io.Serializable;

public class Inscribir_eveprstpr implements Serializable {
    int id_eveprstpr;
    int id_eve;
    int id_prs;
    int id_tpr;
    int preferenciaval_eveprstpr;
    int preferenciacol_eveprstpr;
    boolean noasistio_eveprstpr;
    String estado_eveprstpr;
    String descestado_eveprstpr;

    public Inscribir_eveprstpr() {
        super();
    }

    public Inscribir_eveprstpr(int id_eveprstpr, int id_eve, int id_prs, int id_tpr, int preferenciaval_eveprstpr, int preferenciacol_eveprstpr, boolean noasistio_eveprstpr, String estado_eveprstpr, String descestado_eveprstpr) {
        super();
        this.id_eveprstpr = id_eveprstpr;
        this.id_eve = id_eve;
        this.id_prs = id_prs;
        this.id_tpr = id_tpr;
        this.preferenciaval_eveprstpr = preferenciaval_eveprstpr;
        this.preferenciacol_eveprstpr = preferenciacol_eveprstpr;
        this.noasistio_eveprstpr = noasistio_eveprstpr;
        this.estado_eveprstpr = estado_eveprstpr;
        this.descestado_eveprstpr = descestado_eveprstpr;
    }

    public int getId_eveprstpr() {
        return id_eveprstpr;
    }

    public void setId_eveprstpr(int id_eveprstpr) {
        this.id_eveprstpr = id_eveprstpr;
    }

    public int getId_eve() {
        return id_eve;
    }

    public void setId_eve(int id_eve) {
        this.id_eve = id_eve;
    }

    public int getId_prs() {
        return id_prs;
    }

    public void setId_prs(int id_prs) {
        this.id_prs = id_prs;
    }

    public int getId_tpr() {
        return id_tpr;
    }

    public void setId_tpr(int id_tpr) {
        this.id_tpr = id_tpr;
    }

    public int getPreferenciaval_eveprstpr() {
        return preferenciaval_eveprstpr;
    }

    public void setPreferenciaval_eveprstpr(int preferenciaval_eveprstpr) {
        this.preferenciaval_eveprstpr = preferenciaval_eveprstpr;
    }

    public int getPreferenciacol_eveprstpr() {
        return preferenciacol_eveprstpr;
    }

    public void setPreferenciacol_eveprstpr(int preferenciacol_eveprstpr) {
        this.preferenciacol_eveprstpr = preferenciacol_eveprstpr;
    }

    public boolean getNoasistio_eveprstpr() {
        return noasistio_eveprstpr;
    }

    public void setNoasistio_eveprstpr(boolean noasistio_eveprstpr) {
        this.noasistio_eveprstpr = noasistio_eveprstpr;
    }

    public String getEstado_eveprstpr() {
        return estado_eveprstpr;
    }

    public void setEstado_eveprstpr(String estado_eveprstpr) {
        this.estado_eveprstpr = estado_eveprstpr;
    }

    public String getDescestado_eveprstpr() {
        return descestado_eveprstpr;
    }

    public void setDescestado_eveprstpr(String descestado_eveprstpr) {
        this.descestado_eveprstpr = descestado_eveprstpr;
    }

    @Override
    public String toString() {
        return "Inscribir_eveprstpr{" +
                "id_eve=" + id_eve +
                ", id_prs=" + id_prs +
                ", estado_eveprstpr='" + estado_eveprstpr + '\'' +
                '}';
    }
}

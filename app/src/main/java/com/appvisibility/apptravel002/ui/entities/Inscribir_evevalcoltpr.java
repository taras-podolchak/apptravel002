package com.appvisibility.apptravel002.ui.entities;

import java.io.Serializable;

public class Inscribir_evevalcoltpr implements Serializable {
    int id_evevalcoltpr;
    int id_eve;
    int id_prs;
    int id_tpr;
    int preferenciaval_evevalcoltpr;
    int preferenciacol_evevalcoltpr;
    boolean noasistio_evevalcoltpr;
    String estado_evevalcoltpr;
    String descestado_evevalcoltpr;

    public Inscribir_evevalcoltpr() {
        super();
    }

    public Inscribir_evevalcoltpr(int id_evevalcoltpr, int id_eve, int id_prs, int id_tpr, int preferenciaval_evevalcoltpr, int preferenciacol_evevalcoltpr, boolean noasistio_evevalcoltpr, String estado_evevalcoltpr, String descestado_evevalcoltpr) {
        super();
        this.id_evevalcoltpr = id_evevalcoltpr;
        this.id_eve = id_eve;
        this.id_prs = id_prs;
        this.id_tpr = id_tpr;
        this.preferenciaval_evevalcoltpr = preferenciaval_evevalcoltpr;
        this.preferenciacol_evevalcoltpr = preferenciacol_evevalcoltpr;
        this.noasistio_evevalcoltpr = noasistio_evevalcoltpr;
        this.estado_evevalcoltpr = estado_evevalcoltpr;
        this.descestado_evevalcoltpr = descestado_evevalcoltpr;
    }

    public int getId_evevalcoltpr() {
        return id_evevalcoltpr;
    }

    public void setId_evevalcoltpr(int id_evevalcoltpr) {
        this.id_evevalcoltpr = id_evevalcoltpr;
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

    public int getPreferenciaval_evevalcoltpr() {
        return preferenciaval_evevalcoltpr;
    }

    public void setPreferenciaval_evevalcoltpr(int preferenciaval_evevalcoltpr) {
        this.preferenciaval_evevalcoltpr = preferenciaval_evevalcoltpr;
    }

    public int getPreferenciacol_evevalcoltpr() {
        return preferenciacol_evevalcoltpr;
    }

    public void setPreferenciacol_evevalcoltpr(int preferenciacol_evevalcoltpr) {
        this.preferenciacol_evevalcoltpr = preferenciacol_evevalcoltpr;
    }

    public boolean getNoasistio_evevalcoltpr() {
        return noasistio_evevalcoltpr;
    }

    public void setNoasistio_evevalcoltpr(boolean noasistio_evevalcoltpr) {
        this.noasistio_evevalcoltpr = noasistio_evevalcoltpr;
    }

    public String getEstado_evevalcoltpr() {
        return estado_evevalcoltpr;
    }

    public void setEstado_evevalcoltpr(String estado_evevalcoltpr) {
        this.estado_evevalcoltpr = estado_evevalcoltpr;
    }

    public String getDescestado_evevalcoltpr() {
        return descestado_evevalcoltpr;
    }

    public void setDescestado_evevalcoltpr(String descestado_evevalcoltpr) {
        this.descestado_evevalcoltpr = descestado_evevalcoltpr;
    }

    @Override
    public String toString() {
        return "Inscribir_evevalcoltpr{" +
                "id_eve=" + id_eve +
                ", id_prs=" + id_prs +
                ", estado_evevalcoltpr='" + estado_evevalcoltpr + '\'' +
                '}';
    }
}

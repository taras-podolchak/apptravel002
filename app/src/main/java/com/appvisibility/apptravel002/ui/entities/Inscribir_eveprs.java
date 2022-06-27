package com.appvisibility.apptravel002.ui.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Inscribir_eveprs implements Serializable, Parcelable {
    int id_eveprs;
    int id_eve;
    int id_prs;
    int id_tpr;
    int plazaslibres_eveprs;
    int preferenciaprs_eveprs;
    boolean noasistio_eveprs;
    String estado_eveprs;
    String descestado_eveprs;

    public Inscribir_eveprs() {
        super();
    }

    public Inscribir_eveprs(int id_eveprs, int id_eve, int id_prs, int id_tpr, int plazaslibres_eveprs, int preferenciaprs_eveprs, boolean noasistio_eveprs, String estado_eveprs, String descestado_eveprs) {
        super();
        this.id_eveprs = id_eveprs;
        this.id_eve = id_eve;
        this.id_prs = id_prs;
        this.id_tpr = id_tpr;
        this.plazaslibres_eveprs = plazaslibres_eveprs;
        this.preferenciaprs_eveprs = preferenciaprs_eveprs;
        this.noasistio_eveprs = noasistio_eveprs;
        this.estado_eveprs = estado_eveprs;
        this.descestado_eveprs = descestado_eveprs;
    }

    protected Inscribir_eveprs(Parcel in) {
        id_eveprs = in.readInt();
        id_eve = in.readInt();
        id_prs = in.readInt();
        id_tpr = in.readInt();
        plazaslibres_eveprs = in.readInt();
        preferenciaprs_eveprs = in.readInt();
        noasistio_eveprs = in.readByte() != 0;
        estado_eveprs = in.readString();
        descestado_eveprs = in.readString();
    }

    public static final Creator<Inscribir_eveprs> CREATOR = new Creator<Inscribir_eveprs>() {
        @Override
        public Inscribir_eveprs createFromParcel(Parcel in) {
            return new Inscribir_eveprs(in);
        }

        @Override
        public Inscribir_eveprs[] newArray(int size) {
            return new Inscribir_eveprs[size];
        }
    };

    public int getId_eveprs() {
        return id_eveprs;
    }

    public void setId_eveprs(int id_eveprs) {
        this.id_eveprs = id_eveprs;
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

    public void getId_tpr(int id_tpr) {
        this.id_prs = id_tpr;
    }

    public int getPlazaslibres_eveprs() {
        return plazaslibres_eveprs;
    }

    public void setPlazaslibres_eveprs(int plazaslibres_eveprs) {
        this.plazaslibres_eveprs = plazaslibres_eveprs;
    }

    public int getPreferenciaprs_eveprs() {
        return preferenciaprs_eveprs;
    }

    public void setPreferenciaprs_eveprs(int preferenciaprs_eveprs) {
        this.preferenciaprs_eveprs = preferenciaprs_eveprs;
    }

    public boolean getNoasistio_eveprs() {
        return noasistio_eveprs;
    }

    public void setNoasistio_eveprs(boolean noasistio_eveprs) {
        this.noasistio_eveprs = noasistio_eveprs;
    }

    public String getEstado_eveprs() {
        return estado_eveprs;
    }

    public void setEstado_eveprs(String estado_eveprs) {
        this.estado_eveprs = estado_eveprs;
    }

    public String getDescestado_eveprs() {
        return descestado_eveprs;
    }

    public void setDescestado_eveprs(String descestado_eveprs) {
        this.descestado_eveprs = descestado_eveprs;
    }

    @Override
    public String toString() {
        return "Inscribir_eveprs{" +
                "id_eve=" + id_eve +
                ", id_prs=" + id_prs +
                ", estado_eveprs='" + estado_eveprs + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_eveprs);
        dest.writeInt(id_eve);
        dest.writeInt(id_prs);
        dest.writeInt(id_tpr);
        dest.writeInt(plazaslibres_eveprs);
        dest.writeInt(preferenciaprs_eveprs);
        dest.writeByte((byte) (noasistio_eveprs ? 1 : 0));
        dest.writeString(estado_eveprs);
        dest.writeString(descestado_eveprs);
    }
}

package com.appvisibility.apptravel002.ui.entities;

import java.io.Serializable;

public class Alojamiento_alo implements Serializable {

    private int id_alo;
    private int preferencia_alo;
    private int plazas_alo;
    private String fechalimite_alo;
    private String fechaentrada_alo;
    private String fechasalida_alo;
    private String nombre_alo;
    private String direccion_alo;
    private String localidad_alo;
    private String cpostal_alo;
    private String desalojamiento_alo;
    private String web_alo;
    private String email_alo;
    private String tipoalt_alo;

    public Alojamiento_alo() {
    }

    public Alojamiento_alo(int id_alo, int preferencia_alo, int plazas_alo, String fechalimite_alo, String fechaentrada_alo, String fechasalida_alo, String nombre_alo, String direccion_alo, String localidad_alo, String cpostal_alo, String desalojamiento_alo, String web_alo, String email_alo, String tipoalt_alo) {
        this.id_alo = id_alo;
        this.preferencia_alo = preferencia_alo;
        this.plazas_alo = plazas_alo;
        this.fechalimite_alo = fechalimite_alo;
        this.fechaentrada_alo = fechaentrada_alo;
        this.fechasalida_alo = fechasalida_alo;
        this.nombre_alo = nombre_alo;
        this.direccion_alo = direccion_alo;
        this.localidad_alo = localidad_alo;
        this.cpostal_alo = cpostal_alo;
        this.desalojamiento_alo = desalojamiento_alo;
        this.web_alo = web_alo;
        this.email_alo = email_alo;
        this.tipoalt_alo = tipoalt_alo;
    }

    public int getId_alo() {
        return id_alo;
    }

    public void setId_alo(int id_alo) {
        this.id_alo = id_alo;
    }

    public int getPreferencia_alo() {
        return preferencia_alo;
    }

    public void setPreferencia_alo(int preferencia_alo) {
        this.preferencia_alo = preferencia_alo;
    }

    public int getPlazas_alo() {
        return plazas_alo;
    }

    public void setPlazas_alo(int plazas_alo) {
        this.plazas_alo = plazas_alo;
    }

    public String getFechalimite_alo() {
        return fechalimite_alo;
    }

    public void setFechalimite_alo(String fechalimite_alo) {
        this.fechalimite_alo = fechalimite_alo;
    }

    public String getFechaentrada_alo() {
        return fechaentrada_alo;
    }

    public void setFechaentrada_alo(String fechaentrada_alo) {
        this.fechaentrada_alo = fechaentrada_alo;
    }

    public String getFechasalida_alo() {
        return fechasalida_alo;
    }

    public void setFechasalida_alo(String fechasalida_alo) {
        this.fechasalida_alo = fechasalida_alo;
    }

    public String getNombre_alo() {
        return nombre_alo;
    }

    public void setNombre_alo(String nombre_alo) {
        this.nombre_alo = nombre_alo;
    }

    public String getDireccion_alo() {
        return direccion_alo;
    }

    public void setDireccion_alo(String direccion_alo) {
        this.direccion_alo = direccion_alo;
    }

    public String getLocalidad_alo() {
        return localidad_alo;
    }

    public void setLocalidad_alo(String localidad_alo) {
        this.localidad_alo = localidad_alo;
    }

    public String getCpostal_alo() {
        return cpostal_alo;
    }

    public void setCpostal_alo(String cpostal_alo) {
        this.cpostal_alo = cpostal_alo;
    }

    public String getDesalojamiento_alo() {
        return desalojamiento_alo;
    }

    public void setDesalojamiento_alo(String desalojamiento_alo) {
        this.desalojamiento_alo = desalojamiento_alo;
    }

    public String getWeb_alo() {
        return web_alo;
    }

    public void setWeb_alo(String web_alo) {
        this.web_alo = web_alo;
    }

    public String getEmail_alo() {
        return email_alo;
    }

    public void setEmail_alo(String email_alo) {
        this.email_alo = email_alo;
    }

    public String getTipoalt_alo() {
        return tipoalt_alo;
    }

    public void setTipoalt_alo(String tipoalt_alo) {
        this.tipoalt_alo = tipoalt_alo;
    }
}

package com.appvisibility.apptravel002.ui.entities;

import java.io.Serializable;
import java.util.Objects;

public class Actividad_act implements Serializable {

    private int id_act;
    private int id_eve;
    private String fecha_act;
    private String actividadtipo_act;
    private String nombre_act;
    private String foto_act;
    private String descactividad_act;
    private String nivel_act;
    private String salida_act;
    private String salidacoordenadas_act;
    private String llegada_act;
    private String llegadacoordenadastru_eve;
    private int horas_act;
    private String wikiloc_act;
    private int desnivel_act;
    private int distancia_act;

    public Actividad_act() {
        super();
    }

    public int getId_act() {
        return id_act;
    }

    public void setId_act(int id_act) {
        this.id_act = id_act;
    }

    public int getId_eve() {
        return id_eve;
    }

    public void setId_eve(int id_eve) {
        this.id_eve = id_eve;
    }

    public String getFecha_act() {
        return fecha_act;
    }

    public void setFecha_act(String fecha_act) {
        this.fecha_act = fecha_act;
    }

    public String getActividadtipo_act() {
        return actividadtipo_act;
    }

    public void setActividadtipo_act(String actividadtipo_act) {
        this.actividadtipo_act = actividadtipo_act;
    }

    public String getNombre_act() {
        return nombre_act;
    }

    public void setNombre_act(String nombre_act) {
        this.nombre_act = nombre_act;
    }

    public String getFoto_act() {
        return foto_act;
    }

    public void setFoto_act(String foto_act) {
        this.foto_act = foto_act;
    }

    public String getDescactividad_act() {
        return descactividad_act;
    }

    public void setDescactividad_act(String descactividad_act) {
        this.descactividad_act = descactividad_act;
    }

    public String getNivel_act() {
        return nivel_act;
    }

    public void setNivel_act(String nivel_act) {
        this.nivel_act = nivel_act;
    }

    public String getSalida_act() {
        return salida_act;
    }

    public void setSalida_act(String salida_act) {
        this.salida_act = salida_act;
    }

    public String getSalidacoordenadas_act() {
        return salidacoordenadas_act;
    }

    public void setSalidacoordenadas_act(String salidacoordenadas_act) {
        this.salidacoordenadas_act = salidacoordenadas_act;
    }

    public String getLlegada_act() {
        return llegada_act;
    }

    public void setLlegada_act(String llegada_act) {
        this.llegada_act = llegada_act;
    }

    public String getLlegadacoordenadastru_eve() {
        return llegadacoordenadastru_eve;
    }

    public void setLlegadacoordenadastru_eve(String llegadacoordenadastru_eve) {
        this.llegadacoordenadastru_eve = llegadacoordenadastru_eve;
    }

    public int getHoras_act() {
        return horas_act;
    }

    public void setHoras_act(int horas_act) {
        this.horas_act = horas_act;
    }

    public String getWikiloc_act() {
        return wikiloc_act;
    }

    public void setWikiloc_act(String wikiloc_act) {
        this.wikiloc_act = wikiloc_act;
    }

    public int getDesnivel_act() {
        return desnivel_act;
    }

    public void setDesnivel_act(int desnivel_act) {
        this.desnivel_act = desnivel_act;
    }

    public int getDistancia_act() {
        return distancia_act;
    }

    public void setDistancia_act(int distancia_act) {
        this.distancia_act = distancia_act;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actividad_act that = (Actividad_act) o;
        return horas_act == that.horas_act && desnivel_act == that.desnivel_act && distancia_act == that.distancia_act && Objects.equals(fecha_act, that.fecha_act) && Objects.equals(actividadtipo_act, that.actividadtipo_act) && nombre_act.equals(that.nombre_act) && Objects.equals(nivel_act, that.nivel_act) && Objects.equals(wikiloc_act, that.wikiloc_act);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fecha_act, actividadtipo_act, nombre_act, nivel_act, horas_act, wikiloc_act, desnivel_act, distancia_act);
    }

}
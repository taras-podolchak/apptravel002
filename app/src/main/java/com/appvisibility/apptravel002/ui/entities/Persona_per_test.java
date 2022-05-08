package com.appvisibility.apptravel002.ui.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Persona_per_test implements Serializable {

    private int id_per;
    private String coche_per;
    private int nps01_per;
    private String nps01fecha_per;
    private int nps02_per;
    private String nps02fecha_per;
    private int nps03_per;
    private String nps03fecha_per;
    private String apodo_per;
    private String contrasenna_per;
    private boolean recordarcontrasenna_per;
    private String actividadtipo_per;
    private String documentotipo_per;
    private String nombre_per;
    private String apellido1_per;
    private String apellido2_per;
    private String dni_per;
    private String direccion_per;
    private String localidad_per;
    private String cpostal_per;
    private String pais_per;
    private String movil_per;
    private String email_per;
    private int usuariotipo_per;
    private String fotopropia_per;
    private String fotoorg_per;
    private int federado_per;
    private String federacionfoto_per;
    private boolean seguro_per;
    private String segurocompannia_per;
    private String seguropoliza_per;
    private String fechacaducidadseguro_per;
    private String fechaalta_per;
    private String fechabaja_per;
    private int antiguedad_per;
    private boolean solicitabaja_per;
    private boolean condicioneslegales_per;
    private String emergencia1telefono_per;
    private String emergencia1relacion_per;
    private String emergencia2telefono_per;
    private String emergencia2relacion_per;
    private double preferencia_per;
    private int antiguedadpre_per;
    private int fiabilidadpre_per;
    private int volumencomprapre_per;
    private int cochepre_per;
    private int plazaslibres_per;
    private int valoracionorgpre_per;
    private int nrelacionespre_per;
    private String numerocta_per;
    private List<String> alimentacions = new ArrayList<>();

    public Persona_per_test() {
    }

    public Persona_per_test(int id_per, String coche_per, int nps01_per, String nps01fecha_per, int nps02_per, String nps02fecha_per, int nps03_per, String nps03fecha_per, String apodo_per, String contrasenna_per, boolean recordarcontrasenna_per, String actividadtipo_per, String documentotipo_per, String nombre_per, String apellido1_per, String apellido2_per, String dni_per, String direccion_per, String localidad_per, String cpostal_per, String pais_per, String movil_per, String email_per, int usuariotipo_per, String fotopropia_per, String fotoorg_per, int federado_per, String federacionfoto_per, boolean seguro_per, String segurocompannia_per, String seguropoliza_per, String fechacaducidadseguro_per, String fechaalta_per, String fechabaja_per, int antiguedad_per, boolean solicitabaja_per, boolean condicioneslegales_per, String emergencia1telefono_per, String emergencia1relacion_per, String emergencia2telefono_per, String emergencia2relacion_per, double preferencia_per, int antiguedadpre_per, int fiabilidadpre_per, int volumencomprapre_per, int cochepre_per, int plazaslibres_per, int valoracionorgpre_per, int nrelacionespre_per, String numerocta_per, List<String> listAlimentacion) {
        this.id_per = id_per;
        this.coche_per = coche_per;
        this.nps01_per = nps01_per;
        this.nps01fecha_per = nps01fecha_per;
        this.nps02_per = nps02_per;
        this.nps02fecha_per = nps02fecha_per;
        this.nps03_per = nps03_per;
        this.nps03fecha_per = nps03fecha_per;
        this.apodo_per = apodo_per;
        this.contrasenna_per = contrasenna_per;
        this.recordarcontrasenna_per = recordarcontrasenna_per;
        this.actividadtipo_per = actividadtipo_per;
        this.documentotipo_per = documentotipo_per;
        this.nombre_per = nombre_per;
        this.apellido1_per = apellido1_per;
        this.apellido2_per = apellido2_per;
        this.dni_per = dni_per;
        this.direccion_per = direccion_per;
        this.localidad_per = localidad_per;
        this.cpostal_per = cpostal_per;
        this.pais_per = pais_per;
        this.movil_per = movil_per;
        this.email_per = email_per;
        this.usuariotipo_per = usuariotipo_per;
        this.fotopropia_per = fotopropia_per;
        this.fotoorg_per = fotoorg_per;
        this.federado_per = federado_per;
        this.federacionfoto_per = federacionfoto_per;
        this.seguro_per = seguro_per;
        this.segurocompannia_per = segurocompannia_per;
        this.seguropoliza_per = seguropoliza_per;
        this.fechacaducidadseguro_per = fechacaducidadseguro_per;
        this.fechaalta_per = fechaalta_per;
        this.fechabaja_per = fechabaja_per;
        this.antiguedad_per = antiguedad_per;
        this.solicitabaja_per = solicitabaja_per;
        this.condicioneslegales_per = condicioneslegales_per;
        this.emergencia1telefono_per = emergencia1telefono_per;
        this.emergencia1relacion_per = emergencia1relacion_per;
        this.emergencia2telefono_per = emergencia2telefono_per;
        this.emergencia2relacion_per = emergencia2relacion_per;
        this.preferencia_per = preferencia_per;
        this.antiguedadpre_per = antiguedadpre_per;
        this.fiabilidadpre_per = fiabilidadpre_per;
        this.volumencomprapre_per = volumencomprapre_per;
        this.cochepre_per = cochepre_per;
        this.plazaslibres_per = plazaslibres_per;
        this.valoracionorgpre_per = valoracionorgpre_per;
        this.nrelacionespre_per = nrelacionespre_per;
        this.numerocta_per = numerocta_per;
        this.alimentacions = listAlimentacion;
    }

    public int getId_per() {
        return id_per;
    }

    public void setId_per(int id_per) {
        this.id_per = id_per;
    }

    public String getCoche_per() {
        return coche_per;
    }

    public void setCoche_per(String coche_per) {
        this.coche_per = coche_per;
    }

    public int getNps01_per() {
        return nps01_per;
    }

    public void setNps01_per(int nps01_per) {
        this.nps01_per = nps01_per;
    }

    public String getNps01fecha_per() {
        return nps01fecha_per;
    }

    public void setNps01fecha_per(String nps01fecha_per) {
        this.nps01fecha_per = nps01fecha_per;
    }

    public int getNps02_per() {
        return nps02_per;
    }

    public void setNps02_per(int nps02_per) {
        this.nps02_per = nps02_per;
    }

    public String getNps02fecha_per() {
        return nps02fecha_per;
    }

    public void setNps02fecha_per(String nps02fecha_per) {
        this.nps02fecha_per = nps02fecha_per;
    }

    public int getNps03_per() {
        return nps03_per;
    }

    public void setNps03_per(int nps03_per) {
        this.nps03_per = nps03_per;
    }

    public String getNps03fecha_per() {
        return nps03fecha_per;
    }

    public void setNps03fecha_per(String nps03fecha_per) {
        this.nps03fecha_per = nps03fecha_per;
    }

    public String getApodo_per() {
        return apodo_per;
    }

    public void setApodo_per(String apodo_per) {
        this.apodo_per = apodo_per;
    }

    public String getContrasenna_per() {
        return contrasenna_per;
    }

    public void setContrasenna_per(String contrasenna_per) {
        this.contrasenna_per = contrasenna_per;
    }

    public boolean isRecordarcontrasenna_per() {
        return recordarcontrasenna_per;
    }

    public void setRecordarcontrasenna_per(boolean recordarcontrasenna_per) {
        this.recordarcontrasenna_per = recordarcontrasenna_per;
    }

    public String getActividadtipo_per() {
        return actividadtipo_per;
    }

    public void setActividadtipo_per(String actividadtipo_per) {
        this.actividadtipo_per = actividadtipo_per;
    }

    public String getDocumentotipo_per() {
        return documentotipo_per;
    }

    public void setDocumentotipo_per(String documentotipo_per) {
        this.documentotipo_per = documentotipo_per;
    }

    public String getNombre_per() {
        return nombre_per;
    }

    public void setNombre_per(String nombre_per) {
        this.nombre_per = nombre_per;
    }

    public String getApellido1_per() {
        return apellido1_per;
    }

    public void setApellido1_per(String apellido1_per) {
        this.apellido1_per = apellido1_per;
    }

    public String getApellido2_per() {
        return apellido2_per;
    }

    public void setApellido2_per(String apellido2_per) {
        this.apellido2_per = apellido2_per;
    }

    public String getDni_per() {
        return dni_per;
    }

    public void setDni_per(String dni_per) {
        this.dni_per = dni_per;
    }

    public String getDireccion_per() {
        return direccion_per;
    }

    public void setDireccion_per(String direccion_per) {
        this.direccion_per = direccion_per;
    }

    public String getLocalidad_per() {
        return localidad_per;
    }

    public void setLocalidad_per(String localidad_per) {
        this.localidad_per = localidad_per;
    }

    public String getCpostal_per() {
        return cpostal_per;
    }

    public void setCpostal_per(String cpostal_per) {
        this.cpostal_per = cpostal_per;
    }

    public String getPais_per() {
        return pais_per;
    }

    public void setPais_per(String pais_per) {
        this.pais_per = pais_per;
    }

    public String getMovil_per() {
        return movil_per;
    }

    public void setMovil_per(String movil_per) {
        this.movil_per = movil_per;
    }

    public String getEmail_per() {
        return email_per;
    }

    public void setEmail_per(String email_per) {
        this.email_per = email_per;
    }

    public int getUsuariotipo_per() {
        return usuariotipo_per;
    }

    public void setUsuariotipo_per(int usuariotipo_per) {
        this.usuariotipo_per = usuariotipo_per;
    }

    public String getFotopropia_per() {
        return fotopropia_per;
    }

    public void setFotopropia_per(String fotopropia_per) {
        this.fotopropia_per = fotopropia_per;
    }

    public String getFotoorg_per() {
        return fotoorg_per;
    }

    public void setFotoorg_per(String fotoorg_per) {
        this.fotoorg_per = fotoorg_per;
    }

    public int getFederado_per() {
        return federado_per;
    }

    public void setFederado_per(int federado_per) {
        this.federado_per = federado_per;
    }

    public String getFederacionfoto_per() {
        return federacionfoto_per;
    }

    public void setFederacionfoto_per(String federacionfoto_per) {
        this.federacionfoto_per = federacionfoto_per;
    }

    public boolean isSeguro_per() {
        return seguro_per;
    }

    public void setSeguro_per(boolean seguro_per) {
        this.seguro_per = seguro_per;
    }

    public String getSegurocompannia_per() {
        return segurocompannia_per;
    }

    public void setSegurocompannia_per(String segurocompannia_per) {
        this.segurocompannia_per = segurocompannia_per;
    }

    public String getSeguropoliza_per() {
        return seguropoliza_per;
    }

    public void setSeguropoliza_per(String seguropoliza_per) {
        this.seguropoliza_per = seguropoliza_per;
    }

    public String getFechacaducidadseguro_per() {
        return fechacaducidadseguro_per;
    }

    public void setFechacaducidadseguro_per(String fechacaducidadseguro_per) {
        this.fechacaducidadseguro_per = fechacaducidadseguro_per;
    }

    public String getFechaalta_per() {
        return fechaalta_per;
    }

    public void setFechaalta_per(String fechaalta_per) {
        this.fechaalta_per = fechaalta_per;
    }

    public String getFechabaja_per() {
        return fechabaja_per;
    }

    public void setFechabaja_per(String fechabaja_per) {
        this.fechabaja_per = fechabaja_per;
    }

    public int getAntiguedad_per() {
        return antiguedad_per;
    }

    public void setAntiguedad_per(int antiguedad_per) {
        this.antiguedad_per = antiguedad_per;
    }

    public boolean isSolicitabaja_per() {
        return solicitabaja_per;
    }

    public void setSolicitabaja_per(boolean solicitabaja_per) {
        this.solicitabaja_per = solicitabaja_per;
    }

    public boolean isCondicioneslegales_per() {
        return condicioneslegales_per;
    }

    public void setCondicioneslegales_per(boolean condicioneslegales_per) {
        this.condicioneslegales_per = condicioneslegales_per;
    }

    public String getEmergencia1telefono_per() {
        return emergencia1telefono_per;
    }

    public void setEmergencia1telefono_per(String emergencia1telefono_per) {
        this.emergencia1telefono_per = emergencia1telefono_per;
    }

    public String getEmergencia1relacion_per() {
        return emergencia1relacion_per;
    }

    public void setEmergencia1relacion_per(String emergencia1relacion_per) {
        this.emergencia1relacion_per = emergencia1relacion_per;
    }

    public String getEmergencia2telefono_per() {
        return emergencia2telefono_per;
    }

    public void setEmergencia2telefono_per(String emergencia2telefono_per) {
        this.emergencia2telefono_per = emergencia2telefono_per;
    }

    public String getEmergencia2relacion_per() {
        return emergencia2relacion_per;
    }

    public void setEmergencia2relacion_per(String emergencia2relacion_per) {
        this.emergencia2relacion_per = emergencia2relacion_per;
    }

    public double getPreferencia_per() {
        return preferencia_per;
    }

    public void setPreferencia_per(double preferencia_per) {
        this.preferencia_per = preferencia_per;
    }

    public int getAntiguedadpre_per() {
        return antiguedadpre_per;
    }

    public void setAntiguedadpre_per(int antiguedadpre_per) {
        this.antiguedadpre_per = antiguedadpre_per;
    }

    public int getFiabilidadpre_per() {
        return fiabilidadpre_per;
    }

    public void setFiabilidadpre_per(int fiabilidadpre_per) {
        this.fiabilidadpre_per = fiabilidadpre_per;
    }

    public int getVolumencomprapre_per() {
        return volumencomprapre_per;
    }

    public void setVolumencomprapre_per(int volumencomprapre_per) {
        this.volumencomprapre_per = volumencomprapre_per;
    }

    public int getCochepre_per() {
        return cochepre_per;
    }

    public void setCochepre_per(int cochepre_per) {
        this.cochepre_per = cochepre_per;
    }

    public int getPlazaslibres_per() {
        return plazaslibres_per;
    }

    public void setPlazaslibres_per(int plazaslibres_per) {
        this.plazaslibres_per = plazaslibres_per;
    }

    public int getValoracionorgpre_per() {
        return valoracionorgpre_per;
    }

    public void setValoracionorgpre_per(int valoracionorgpre_per) {
        this.valoracionorgpre_per = valoracionorgpre_per;
    }

    public int getNrelacionespre_per() {
        return nrelacionespre_per;
    }

    public void setNrelacionespre_per(int nrelacionespre_per) {
        this.nrelacionespre_per = nrelacionespre_per;
    }

    public String getNumerocta_per() {
        return numerocta_per;
    }

    public void setNumerocta_per(String numerocta_per) {
        this.numerocta_per = numerocta_per;
    }

    public List<String> getAlimentacions() {
        return alimentacions;
    }

    public void setAlimentacions(List<String> alimentacions) {
        this.alimentacions = alimentacions;
    }
}

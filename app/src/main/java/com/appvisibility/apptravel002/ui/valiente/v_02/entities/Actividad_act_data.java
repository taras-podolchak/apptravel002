package com.appvisibility.apptravel002.ui.valiente.v_02.entities;

import java.util.ArrayList;
import java.util.List;

public class Actividad_act_data {
    public static List<Actividad_act> actividades = new ArrayList<>();

    public static List<Actividad_act> actividades() {
        actividades.add(new Actividad_act(1, 1, "29/12/2021", "senderismo circular", "Otal", "Medio-alto", 7, 1400, 9));
        actividades.add(new Actividad_act(2, 1, "30/12/2021", "senderismo circular", "Faja Racó", "Medio-alto", 7, 600, 10));
        actividades.add(new Actividad_act(3, 1, "31/12/2021", "fiesta", "Quimboa Alto", "Medio", 7, 950, 12));
        actividades.add(new Actividad_act(4, 1, "01/01/2022", "senderismo circular", "Bosque Gamueta", "Medio-bajo", 3, 400, 7));
        actividades.add(new Actividad_act(5, 2, "04/01/2022", "senderismo circular", "Senda Whistler", "Medio", 7, 800, 15));
        actividades.add(new Actividad_act(6, 3, "05/01/2022", "senderismo lineal", "El Hueso", "Medio-bajo", 5, 4, 8));
        actividades.add(new Actividad_act(7, 3, "06/01/2022", "senderismo lineal", "El Laberinto", "Medio-alto", 7, 800, 10));
        actividades.add(new Actividad_act(8, 4, "12/01/2022", "senderismo circular", "Perdiguera", "Alto", 7, 600, 10));
        actividades.add(new Actividad_act(9, 5, "18/01/2022", "senderismo circular", "La Camorca", "Medio", 7, 650, 18));
        return actividades;
    }

    public static List<Actividad_act> actividadesExtracto (int id_eve) {
        List<Actividad_act> actividadesExtracto = new ArrayList<>();
        for (Actividad_act a:actividades){
            if (a.getId_eve()==id_eve){
                actividadesExtracto.add(a);
            }
        }
        return actividadesExtracto;
    }
}

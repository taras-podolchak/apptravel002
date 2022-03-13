package com.appvisibility.apptravel002.ui.valiente.v_02.entities;

import java.util.ArrayList;
import java.util.List;

public class Evento_eve_data {
    public static List<Evento_eve> eventos = new ArrayList<>();

    public static List<Evento_eve> eventos() {
        eventos.add(new Evento_eve(1, "Fin de Año montañero", 10, "https://amigosmontanawo.eboe62.com/Evento/findeanoamigosmontana.jpg", "Medio-alto", "Coches particulares", "28-Diciembre-2021", "1-Enero-2022"));
        eventos.add(new Evento_eve(2, "Senda Whistler", 10, "https://amigosmontanawo.eboe62.com/Evento/littlecuerdalargaamigosmontana.jpg", "Medio", "Coches particulares", "4-Enero-2022", "5-Enero-2022"));
        eventos.add(new Evento_eve(3, "Noche de Reyes", 10, "https://amigosmontanawo.eboe62.com/Evento/nochereyesamigosmontana.jpg", "Medio-alto", "Coches particulares", "5-Enero-2022", "6-Enero-2022"));
        eventos.add(new Evento_eve(4, "Little Cuerda Larga", 10, "https://amigosmontanawo.eboe62.com/Evento/littlecuerdalargaamigosmontana.jpg", "Alto", "Coches particulares", "12-Enero-2022", "12-Enero-2022"));
        eventos.add(new Evento_eve(5, "Cerro de la Camorca", 10, "https://amigosmontanawo.eboe62.com/Evento/camorcaamigosmontana.jpg", "Medio", "Coches particulares", "18-Enero-2022", "18-Enero-2022"));
        eventos.add(new Evento_eve(6, "Licencia Federativa", 0, "https://amigosmontanawo.eboe62.com/Evento/licenciaamigosmontana.jpeg", null, null, "17-Diciembre-2021", "31-Diciembre-2022"));
        return eventos;
    }


}

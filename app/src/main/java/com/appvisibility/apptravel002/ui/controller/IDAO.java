package com.appvisibility.apptravel002.ui.controller;

import java.util.List;

public interface IDAO<T>{

    <T> void tabla1ChangeListener (String coleccion, String criterio, List<T> lista, Class<T> T);

}

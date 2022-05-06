package com.appvisibility.apptravel002.ui.repository;

import androidx.recyclerview.widget.RecyclerView;

import com.appvisibility.apptravel002.ui.entities.Inscribir_evevalcoltpr;
import com.google.firebase.firestore.Query;

import java.util.List;

public interface IDAO2 <T> {
/*
        public <T> void tabla (Query query, List<T> lista, Class<T> tipoObjeto, RecyclerView.Adapter miAdapter);
        public List<T> CollectionChangeListener(Query query, List<T> lista, Class<T> tipoObjeto, RecyclerView.Adapter miAdapter);
*/
        public List<T> CollectionChangeListener(Query query, Class<T> tipoObjeto, RecyclerView.Adapter miAdapter);

}
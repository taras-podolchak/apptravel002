package com.appvisibility.apptravel002.ui.controller.interfaces;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.Query;

import java.util.List;

public interface IDAO<T, S, R>{

    <T> void tabla1ChangeListener (Query query, List<T> lista, Class<T> tipoObjeto, RecyclerView.Adapter miAdapter);

    <S> void tabla2ChangeListener (Query query, List<S> lista, Class<S> tipoObjeto, RecyclerView.Adapter miAdapter);

    <R> void tabla3ChangeListener (Query query, List<R> lista, Class<R> tipoObjeto, RecyclerView.Adapter miAdapter);
}

package com.appvisibility.apptravel002.ui.controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.entities.Actividad_act;
import com.appvisibility.apptravel002.ui.entities.Evento_eve;
import com.appvisibility.apptravel002.ui.service.v02_00_eve_Adapter;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
//import com.appvisibility.apptravel002.ui.valiente.v_02.entities.Actividad_act_data;
//import com.appvisibility.apptravel002.ui.valiente.v_02.entities.Evento_eve_data;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link V_02#newInstance} factory method to
 * create an instance of this fragment.
 */

public class V_02 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView miRecicler;            //V_02
    private RecyclerView.Adapter miAdapter;     //V_02

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // TODO: Entities
    List<Evento_eve> eventos = new ArrayList<>();
    private Context mContext;

    public V_02() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment V_02.
     */
    // TODO: Rename and change types and number of parameters
    public static V_02 newInstance(String param1, String param2) {
        V_02 fragment = new V_02();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_02,
                container, false);

        this.miRecicler = (RecyclerView) view.findViewById(R.id.v02_00_eve);
        this.miRecicler.setHasFixedSize(true);
        this.miRecicler.setLayoutManager(new LinearLayoutManager(mContext));

        eventosChangeListener("evento_eve", "id_eve");

        this.miAdapter = new v02_00_eve_Adapter(eventos, mContext);
        this.miRecicler.setAdapter(miAdapter);
        return view;
    }

    public void eventosChangeListener(String coleccion, String orden) {
        FirebaseFirestore db2 = FirebaseFirestore.getInstance();
        Query query = db2.collection(coleccion).orderBy(orden, Query.Direction.ASCENDING);
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException error) {
                Evento_eve enProceso;
                if (error != null) {
                    Log.e("Error en Firestore", error.getMessage());
                    return;
                }
                eventos.clear();
                for (DocumentSnapshot i : snapshots) {
                    eventos.add(i.toObject(Evento_eve.class));
                }
                miAdapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "Datos recibidos!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
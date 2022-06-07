package com.appvisibility.apptravel002.ui.controller;

import static android.content.ContentValues.TAG;

import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.entities.Evento_eve;
import com.appvisibility.apptravel002.ui.service.v02_00_eve_Adapter;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link V_02#newInstance} factory method to
 * create an instance of this fragment.
 */

public class V_02 extends Fragment implements IDAO<Evento_eve, Object, Object> {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Campos de xml
    private RecyclerView v02_recycler_eve;

    // Acceso a datos
    FirebaseFirestore fbf = FirebaseFirestore.getInstance();

    // Entities
    List<Evento_eve> eventos = new ArrayList<>();
    private Context mContext;

    // Service
    private v02_00_eve_Adapter v02_adapter_eve;

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
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM1, param1);
        bundle.putString(ARG_PARAM2, param2);
        fragment.setArguments(bundle);
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
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_02, container, false);

        this.v02_recycler_eve = (RecyclerView) view.findViewById(R.id.v02_rcv_eventos);
        this.v02_recycler_eve.setHasFixedSize(true);
        this.v02_recycler_eve.setLayoutManager(new LinearLayoutManager(mContext));
        this.v02_adapter_eve = new v02_00_eve_Adapter(eventos, mContext, getResources().getInteger(R.integer.accion_a_v_03));

        Query query1 = fbf.collection("evento_eve").orderBy("id_eve", Query.Direction.ASCENDING);
        tabla1ChangeListener(query1, eventos, Evento_eve.class, v02_adapter_eve);

        this.v02_recycler_eve.setAdapter(v02_adapter_eve);
        return view;
    }//Fin de constructor

    @Override
    public <T> void tabla1ChangeListener(Query query, List<T> lista, Class<T> tipoObjeto, RecyclerView.Adapter miAdapter) {
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException error) {
                T enProceso;
                if (error != null) {
                    Log.e("Error en Firestore", error.getMessage());
                    return;
                }
                lista.clear();
                for (QueryDocumentSnapshot qds : snapshots) {
                    enProceso = (T) qds.toObject(tipoObjeto);
                    lista.add(enProceso);
                }
                miAdapter.notifyDataSetChanged();

                Log.d(TAG, "Datos evento_eve recibidos!");
//                Toast.makeText(getActivity(), "Datos evento_eve recibidos!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public <S> void tabla2ChangeListener(Query query, List<S> lista, Class<S> tipoObjeto, RecyclerView.Adapter miAdapter) {

    }

    @Override
    public <R> void tabla3ChangeListener(Query query, List<R> lista, Class<R> tipoObjeto, RecyclerView.Adapter miAdapter) {

    }
}
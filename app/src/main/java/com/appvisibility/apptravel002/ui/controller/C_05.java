package com.appvisibility.apptravel002.ui.controller;

import static android.content.ContentValues.TAG;

import static com.appvisibility.apptravel002.MainActivity.sesionIniciada;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.entities.Colaborador_col;
import com.appvisibility.apptravel002.ui.entities.Evento_eve;
import com.appvisibility.apptravel002.ui.entities.Valiente_val;
import com.appvisibility.apptravel002.ui.service.v02_00_eve_Adapter;
import com.google.android.gms.maps.GoogleMap;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class C_05 extends Fragment implements IDAO <Valiente_val, Object, Object> {

    //TODO:los campos de xml
    private Button c05_me_interesa, c05_volver;
    private TextView c05_apodo_val;
    private TextView c05_nombre_val;
    private TextView c05_apellido1_val;
    private TextView c05_apellido2_val;
    private TextView c05_fotopropia_val;
    private TextView c05_direccion_col;
    private TextView c05_cpostal_col;
    private TextView c05_localidad_col;
    private TextView c05_movil_val;
    private TextView c05_coche_val;
    private TextView c05_email_val;
    private TextView c05_antiguedadpre_val;
    private TextView c05_cochepre_val;
    private TextView c05_fiabilidadpre_val;
    private TextView c05_valoracionorgpre_val;
    private TextView c05_volumencomprapre_val;
    private TextView c05_nrelacionespre_val;
    private TextView c05_emergencia1relacion_val;
    private TextView c05_emergencia1telefono_val;
    private TextView c05_emergencia2relacion_val;
    private TextView c05_emergencia2telefono_val;
    private TextView c05_fechaalta_val;
    private TextView c05_fechabaja_val;
    private TextView c05_dni_val;
    private TextView c05_condicioneslegales_val;
    private TextView c05_nps01fecha_val;
    private TextView c05_nps01_val;
    private TextView c05_nps02fecha_val;
    private TextView c05_nps02_val;
    private TextView c05_nps03fecha_val;
    private TextView c05_nps03_val;

    //TODO:acceso a datos
    FirebaseFirestore fbf = FirebaseFirestore.getInstance();

    //TODO:entities
    private Valiente_val valiente;
    private Colaborador_col colaborador;

    //service
    private int posicion;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_05_1, container, false);

        posicion = getArguments().getInt("valienteParaC_05");

        Bundle bundle = new Bundle();
        bundle.putInt("eventoParaV_05", posicion);

        this.c05_apodo_val = view.findViewById(R.id.c05_txv_apodo_val);
        this.c05_nombre_val = view.findViewById(R.id.c05_txv_nombre_val);
        this.c05_apellido1_val = view.findViewById(R.id.c05_txv_apellido1_val);
        this.c05_apellido2_val = view.findViewById(R.id.c05_txv_apellido2_val);
        this.c05_fotopropia_val = view.findViewById(R.id.c05_imv_fotopropia_val);
        this.c05_direccion_col = view.findViewById(R.id.c05_txv_direccion_col);
        this.c05_cpostal_col = view.findViewById(R.id.c05_txv_cpostal_col);
        this.c05_localidad_col = view.findViewById(R.id.c05_txv_localidad_col);
        this.c05_movil_val = view.findViewById(R.id.c05_txv_movil_val);
        this.c05_coche_val = view.findViewById(R.id.c05_txv_coche_val);
        this.c05_email_val = view.findViewById(R.id.c05_txv_email_val);
        this.c05_antiguedadpre_val = view.findViewById(R.id.c05_txv_antiguedadpre_val);
        this.c05_cochepre_val = view.findViewById(R.id.c05_txv_cochepre_val);
        this.c05_fiabilidadpre_val = view.findViewById(R.id.c05_txv_fiabilidadpre_val);
        this.c05_valoracionorgpre_val = view.findViewById(R.id.c05_txv_valoracionorgpre_val);
        this.c05_volumencomprapre_val = view.findViewById(R.id.c05_txv_volumencomprapre_val);
        this.c05_nrelacionespre_val = view.findViewById(R.id.c05_txv_nrelacionespre_val);
        this.c05_emergencia1relacion_val = view.findViewById(R.id.c05_txv_emergencia1relacion_val);
        this.c05_emergencia1telefono_val = view.findViewById(R.id.c05_txv_emergencia1telefono_val);
        this.c05_emergencia2relacion_val = view.findViewById(R.id.c05_txv_emergencia2relacion_val);
        this.c05_emergencia2telefono_val = view.findViewById(R.id.c05_txv_emergencia2telefono_val);
        this.c05_fechaalta_val = view.findViewById(R.id.c05_txv_fechaalta_val);
        this.c05_fechabaja_val = view.findViewById(R.id.c05_txv_fechabaja_val);
        this.c05_dni_val = view.findViewById(R.id.c05_txv_dni_val);
        this.c05_condicioneslegales_val = view.findViewById(R.id.c05_txv_condicioneslegales_val);
        this.c05_nps01fecha_val = view.findViewById(R.id.c05_txv_nps01fecha_val);
        this.c05_nps01_val = view.findViewById(R.id.c05_txv_nps01_val);
        this.c05_nps02fecha_val = view.findViewById(R.id.c05_txv_nps02fecha_val);
        this.c05_nps02_val = view.findViewById(R.id.c05_txv_nps02_val);
        this.c05_nps03fecha_val = view.findViewById(R.id.c05_txv_nps03fecha_val);
        this.c05_nps03_val = view.findViewById(R.id.c05_txv_nps03_val);

        // TODO: carga de Evento
        // EOB: Intentar pasar este método a changeNoListener y eliminar las dos líneas siguientes
        List<Evento_eve> eventos = new ArrayList<>();
        v02_00_eve_Adapter v02_adapter_eve = null;

        Query query1 = fbf.collection("evento_eve").whereEqualTo("id_eve", posicion);
        tabla1ChangeListener (query1, eventos, Evento_eve.class, v02_adapter_eve);

//        DocumentReference drf = fbf.collection("evento_eve").document(String.valueOf(posicion));
//        DocumentChangeListener(drf);

        // TODO: los botones
        c05_me_interesa = view.findViewById(R.id.c05_btn_me_interesa);
        c05_me_interesa.setOnClickListener(view12 -> {
            if (!sesionIniciada) {
//                Navigation.findNavController(view12).navigate(R.id.action_nav_v03_to_nav_v04, bundle);
            } else {
//                Navigation.findNavController(view12).navigate(R.id.action_nav_v03_to_nav_v05, bundle);
            }
        });
        c05_volver = view.findViewById(R.id.c05_btn_volver);
        c05_volver.setOnClickListener(view1 -> Navigation.findNavController(view1).navigate(R.id.action_nav_c05_to_nav_v03));
        return view;
    }//fin de constructor

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
                    valiente = (Valiente_val) qds.toObject(tipoObjeto);
//                    lista.add(enProceso);
//                miAdapter.notifyDataSetChanged();

                    c05_apodo_val.setText(valiente.getApodo_val());
                    c05_nombre_val.setText(valiente.getNombre_val());
                    c05_apellido1_val.setText(valiente.getApellido1_val());
                    c05_apellido2_val.setText(valiente.getApellido2_val());
                    c05_fotopropia_val.setText(valiente.getFotopropia_val());
                    c05_direccion_col.setText(colaborador.getDireccion_col());
                    c05_cpostal_col.setText(colaborador.getCpostal_col());
                    c05_localidad_col.setText(colaborador.getLocalidad_col());
                    c05_movil_val.setText(valiente.getMovil_val());
                    c05_coche_val.setText(valiente.getCoche_val());
                    c05_email_val.setText(valiente.getEmail_val());
                    c05_antiguedadpre_val.setText(valiente.getAntiguedadpre_val());
                    c05_cochepre_val.setText(valiente.getCochepre_val());
                    c05_fiabilidadpre_val.setText(valiente.getFiabilidadpre_val());
                    c05_valoracionorgpre_val.setText(valiente.getValoracionorgpre_val());
                    c05_volumencomprapre_val.setText(valiente.getVolumencomprapre_val());
                    c05_nrelacionespre_val.setText(valiente.getNrelacionespre_val());
                    c05_emergencia1relacion_val.setText(valiente.getEmergencia1Relacion_val());
                    c05_emergencia1telefono_val.setText(valiente.getEmergencia1Telefono_val());
                    c05_emergencia2relacion_val.setText(valiente.getEmergencia2Relacion_val());
                    c05_emergencia2telefono_val.setText(valiente.getEmergencia2Telefono_val());
                    c05_fechaalta_val.setText(valiente.getFechaalta_val());
                    c05_fechabaja_val.setText(valiente.getFechabaja_val());
                    c05_dni_val.setText(valiente.getDni_val());
                    c05_condicioneslegales_val.setText(valiente.getCondicioneslegales_val()? "Aceptadas":"Rechazadas");
                    c05_nps01fecha_val.setText(valiente.getNps01Fecha_val());
                    c05_nps01_val.setText(valiente.getNps01_val());
                    c05_nps02fecha_val.setText(valiente.getNps02Fecha_val());
                    c05_nps02_val.setText(valiente.getNps02_val());
                    c05_nps03fecha_val.setText(valiente.getNps03Fecha_val());
                    c05_nps03_val.setText(valiente.getNps03_val());

//                if (pdg.isShowing()){
//                    pdg.dismiss();
//                }

                    Log.d(TAG, "Datos recibidos!");
                    Toast.makeText(getActivity(), "Datos recibidos!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public <S> void tabla2ChangeListener(Query query, List<S> lista, Class<S> tipoObjeto, RecyclerView.Adapter miAdapter) {

    }

    @Override
    public <O> void tabla3ChangeListener(Query query, List<O> lista, Class<O> tipoObjeto, RecyclerView.Adapter miAdapter) {

    }

}
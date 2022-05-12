package com.appvisibility.apptravel002.ui.controller;

import static android.content.ContentValues.TAG;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.entities.Persona_prs;
import com.appvisibility.apptravel002.ui.service.v03_00_prs_Adapter;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class C_05 extends Fragment implements IDAO <Persona_prs, Object, Object> {

    //TODO:los campos de xml
    private Button c05_me_interesa, c05_volver;
    private int id_prs_enProceso;
    private TextView c05_apodo_prs;
    private TextView c05_nombre_prs;
    private TextView c05_apellido1_prs;
    private TextView c05_apellido2_prs;
    private ImageView c05_fotopropia_prs;
    private TextView c05_direccion_prs;
    private TextView c05_cpostal_prs;
    private TextView c05_localidad_prs;
    private TextView c05_movil_prs;
    private TextView c05_coche_prs;
    private TextView c05_email_prs;
    private TextView c05_antiguedadpre_prs;
    private TextView c05_cochepre_prs;
    private TextView c05_fiabilidadpre_prs;
    private TextView c05_valoracionorgpre_prs;
    private TextView c05_volumencomprapre_prs;
    private TextView c05_nrelacionespre_prs;
    private TextView c05_contacto1cargo_prs;
    private TextView c05_contacto1movil_prs;
    private TextView c05_contacto2cargo_prs;
    private TextView c05_contacto2movil_prs;
    private TextView c05_fechaalta_prs;
    private TextView c05_fechabaja_prs;
    private TextView c05_dni_prs;
    private TextView c05_condicioneslegales_prs;
    private TextView c05_nps01fecha_prs;
    private TextView c05_nps01_prs;
    private TextView c05_nps02fecha_prs;
    private TextView c05_nps02_prs;
    private TextView c05_nps03fecha_prs;
    private TextView c05_nps03_prs;

    //TODO:acceso a datos
    FirebaseFirestore fbf = FirebaseFirestore.getInstance();

    //TODO:entities
    private Persona_prs persona;

    //service
    private int id_prs_bundle;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_c_05, container, false);

        Bundle bundle = this.getArguments();

        this.id_prs_enProceso = bundle.getInt("personaParaC_05", id_prs_bundle);
//        this.id_prs_enProceso = bundle.getInt("id_prs", posicion + 1);
        this.c05_apodo_prs = view.findViewById(R.id.c05_txv_apodo_prs);
        this.c05_nombre_prs = view.findViewById(R.id.c05_txv_nombre_prs);
        this.c05_apellido1_prs = view.findViewById(R.id.c05_txv_apellido1_prs);
        this.c05_apellido2_prs = view.findViewById(R.id.c05_txv_apellido2_prs);
        this.c05_fotopropia_prs = view.findViewById(R.id.c05_imv_fotopropia_prs);
        this.c05_direccion_prs = view.findViewById(R.id.c05_txv_direccion_prs);
        this.c05_cpostal_prs = view.findViewById(R.id.c05_txv_cpostal_prs);
        this.c05_localidad_prs = view.findViewById(R.id.c05_txv_localidad_prs);
        this.c05_movil_prs = view.findViewById(R.id.c05_txv_movil_prs);
        this.c05_coche_prs = view.findViewById(R.id.c05_txv_coche_prs);
        this.c05_email_prs = view.findViewById(R.id.c05_txv_email_prs);
        this.c05_antiguedadpre_prs = view.findViewById(R.id.c05_txv_antiguedadpre_prs);
        this.c05_cochepre_prs = view.findViewById(R.id.c05_txv_cochepre_prs);
        this.c05_fiabilidadpre_prs = view.findViewById(R.id.c05_txv_fiabilidadpre_prs);
        this.c05_valoracionorgpre_prs = view.findViewById(R.id.c05_txv_valoracionorgpre_prs);
        this.c05_volumencomprapre_prs = view.findViewById(R.id.c05_txv_volumencomprapre_prs);
        this.c05_nrelacionespre_prs = view.findViewById(R.id.c05_txv_nrelacionespre_prs);
        this.c05_contacto1cargo_prs = view.findViewById(R.id.contacto1cargo_prs);
        this.c05_contacto1movil_prs = view.findViewById(R.id.contacto1movil_prs);
        this.c05_contacto2cargo_prs = view.findViewById(R.id.contacto2cargo_prs);
        this.c05_contacto2movil_prs = view.findViewById(R.id.contacto2movil_prs);
        this.c05_fechaalta_prs = view.findViewById(R.id.c05_txv_fechaalta_prs);
        this.c05_fechabaja_prs = view.findViewById(R.id.c05_txv_fechabaja_prs);
        this.c05_dni_prs = view.findViewById(R.id.c05_txv_dni_prs);
        this.c05_condicioneslegales_prs = view.findViewById(R.id.c05_txv_condicioneslegales_prs);
        this.c05_nps01fecha_prs = view.findViewById(R.id.c05_txv_nps01fecha_prs);
        this.c05_nps01_prs = view.findViewById(R.id.c05_txv_nps01_prs);
        this.c05_nps02fecha_prs = view.findViewById(R.id.c05_txv_nps02fecha_prs);
        this.c05_nps02_prs = view.findViewById(R.id.c05_txv_nps02_prs);
        this.c05_nps03fecha_prs = view.findViewById(R.id.c05_txv_nps03fecha_prs);
        this.c05_nps03_prs = view.findViewById(R.id.c05_txv_nps03_prs);

        // TODO: carga de Evento
        // EOB: Intentar pasar este método a changeNoListener y eliminar las dos líneas siguientes
        List<Persona_prs> personas = new ArrayList<>();
        v03_00_prs_Adapter v03_adapter_prs = null;

        Query query1 = fbf.collection("persona_prs").whereEqualTo("id_prs", id_prs_enProceso);
        tabla1ChangeListener (query1, personas, Persona_prs.class, v03_adapter_prs);

//        DocumentReference drf = fbf.collection("evento_eve").document(String.valueOf(id_eve));
//        DocumentChangeListener(drf);
/*
        // TODO: los botones
        c05_me_interesa = view.findViewById(R.id.c05_btn_me_interesa);
        c05_me_interesa.setOnClickListener(view12 -> {
            if (!sesionIniciada) {
                Navigation.findNavController(view12).navigate(R.id.action_nav_c05_to_nav_v03, bundle);
            } else {
                Navigation.findNavController(view12).navigate(R.id.action_nav_c05_to_nav_v03, bundle);
            }
        });
*/
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
                    persona = (Persona_prs) qds.toObject(tipoObjeto);
//                    lista.add(enProceso);
//                miAdapter.notifyDataSetChanged();

                    c05_apodo_prs.setText(persona.getApodo_prs());
                    c05_nombre_prs.setText(persona.getNombre_prs());
                    c05_apellido1_prs.setText(persona.getApellido1_prs());
                    c05_apellido2_prs.setText(persona.getApellido2_prs());

                    FirebaseStorage fbs = FirebaseStorage.getInstance();
                    StorageReference str = fbs.getReference();
                    str.child("Personas/"+persona.getFotopropia_prs()).getDownloadUrl().addOnSuccessListener(uri ->
                            Picasso.get().load(uri).into(c05_fotopropia_prs)).addOnFailureListener(exception ->
                            Toast.makeText(getActivity(), "Error de cargar la imagen", Toast.LENGTH_LONG).show());
/*
                    c05_direccion_prs.setText(persona.getDireccion_prs());
                    c05_cpostal_prs.setText(persona.getCpostal_prs());
                    c05_localidad_prs.setText(persona.getLocalidad_prs());
*/
                    c05_movil_prs.setText(persona.getMovil_prs());
                    c05_coche_prs.setText(persona.getCoche_prs());
                    c05_email_prs.setText(persona.getEmail_prs());

                    c05_antiguedadpre_prs.setText("Antigüedad: " + persona.getAntiguedadpre_prs());
                    c05_cochepre_prs.setText("Ofrece coche: " + persona.getCochepre_prs());
                    c05_fiabilidadpre_prs.setText("Fiabilidad: " + persona.getFiabilidadpre_prs());
                    c05_valoracionorgpre_prs.setText("Colaboración: " + persona.getValoracionorgpre_prs());
                    c05_volumencomprapre_prs.setText("Volumen: " + persona.getVolumencomprapre_prs());
                    c05_nrelacionespre_prs.setText("Contactos: " + persona.getNrelacionespre_prs());
                    c05_contacto1cargo_prs.setText(persona.getContacto1Cargo_prs());
                    c05_contacto1movil_prs.setText(persona.getContacto1Movil_prs());
                    c05_contacto2cargo_prs.setText(persona.getContacto2Cargo_prs());
                    c05_contacto2movil_prs.setText(persona.getContacto2Movil_prs());
                    c05_fechaalta_prs.setText(persona.getFechaalta_prs());
                    c05_fechabaja_prs.setText(persona.getFechabaja_prs());
                    c05_dni_prs.setText("DNI: " + persona.getDni_prs());
                    c05_condicioneslegales_prs.setText("Condiciones " + (persona.getCondicioneslegales_prs()? "Aceptadas":"Rechazadas"));
                    c05_nps01fecha_prs.setText(persona.getNps01Fecha_prs());
                    c05_nps01_prs.setText("NPS1:  " + persona.getNps01_prs());
                    c05_nps02fecha_prs.setText(persona.getNps02Fecha_prs());
                    c05_nps02_prs.setText("NPS2:  " + persona.getNps02_prs());
                    c05_nps03fecha_prs.setText(persona.getNps03Fecha_prs());
                    c05_nps03_prs.setText("NPS3:  " + persona.getNps03_prs());

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
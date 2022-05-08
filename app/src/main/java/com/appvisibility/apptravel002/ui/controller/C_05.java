package com.appvisibility.apptravel002.ui.controller;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.appvisibility.apptravel002.ui.entities.Persona_per_test;
import com.appvisibility.apptravel002.ui.service.v03_00_val_Adapter;
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

public class C_05 extends Fragment implements IDAO<Persona_per_test, Object, Object> {

    //TODO:los campos de xml
    private Button c05_me_interesa, c05_volver;
    private int id_val_enProceso;
    private TextView c05_apodo_val;
    private ImageView c05_fotopropia_val;
    private TextView c05_direccion_col;
    private TextView c05_cpostal_col;
    private TextView c05_localidad_col;
    private TextView c05_nombre_apellido1_apellido2_val;
    private TextView c05_movil_val;
    private TextView c05_movil_whatsapp_val;
    private TextView c05_coche_val;
    private TextView c05_email_val;
    private TextView c05_antiguedadpre_val;
    private TextView c05_cochepre_val;
    private TextView c05_fiabilidadpre_val;
    private TextView c05_valoracionorgpre_val;
    private TextView c05_volumencomprapre_val;
    private TextView c05_nrelacionespre_val;
    private TextView c05_emergencia1relacion_emergencia1telefono_val;
    private TextView c05_emergencia2relacion_emergencia2telefono_val;
    private TextView c05_fechaalta_fechabaja_val;
    private TextView c05_dni_val;
    private TextView c05_condicioneslegales_val;
    private TextView c05_nps01fecha_nps01_val;
    private TextView c05_nps02fecha_nps02_val;
    private TextView c05_nps03fecha_nps03_val;

    //TODO:acceso a datos
    FirebaseFirestore fbf = FirebaseFirestore.getInstance();

    //TODO:entities
    //private Valiente_val valiente;
    //private Colaborador_col colaborador;
    private Persona_per_test valiente;

    //service
    private int id_val_bundle;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_c_05, container, false);

        Bundle bundle = this.getArguments();

        this.id_val_enProceso = bundle.getInt("valienteParaC_05", id_val_bundle);
//        this.id_val_enProceso = bundle.getInt("id_val", posicion + 1);
        this.c05_apodo_val = view.findViewById(R.id.c05_txv_apodo_val);
        this.c05_nombre_apellido1_apellido2_val = view.findViewById(R.id.c05_txt_nombre_apellido1_apellido2_val);
        this.c05_fotopropia_val = view.findViewById(R.id.c05_imv_fotopropia_val);
        this.c05_direccion_col = view.findViewById(R.id.c05_txv_direccion_col);
        this.c05_cpostal_col = view.findViewById(R.id.c05_txv_cpostal_col);
        this.c05_localidad_col = view.findViewById(R.id.c05_txv_localidad_col);
        this.c05_movil_val = view.findViewById(R.id.c05_txv_movil_val);
        this.c05_movil_whatsapp_val = view.findViewById(R.id.c05_txv_movil_whatsapp_val);
        this.c05_coche_val = view.findViewById(R.id.c05_txv_coche_val);
        this.c05_email_val = view.findViewById(R.id.c05_txv_email_val);
        this.c05_antiguedadpre_val = view.findViewById(R.id.c05_txv_antiguedadpre_val);
        this.c05_cochepre_val = view.findViewById(R.id.c05_txv_cochepre_val);
        this.c05_fiabilidadpre_val = view.findViewById(R.id.c05_txv_fiabilidadpre_val);
        this.c05_valoracionorgpre_val = view.findViewById(R.id.c05_txv_valoracionorgpre_val);
        this.c05_volumencomprapre_val = view.findViewById(R.id.c05_txv_volumencomprapre_val);
        this.c05_nrelacionespre_val = view.findViewById(R.id.c05_txv_nrelacionespre_val);
        this.c05_emergencia1relacion_emergencia1telefono_val = view.findViewById(R.id.c05_txt_emergencia1relacion_emergencia1telefono_val);
        this.c05_emergencia2relacion_emergencia2telefono_val = view.findViewById(R.id.c05_txt_emergencia2relacion_emergencia2telefono_val);
        this.c05_fechaalta_fechabaja_val = view.findViewById(R.id.c05_txv_fechaalta_fechabaja_val);
        this.c05_dni_val = view.findViewById(R.id.c05_txv_dni_val);
        this.c05_condicioneslegales_val = view.findViewById(R.id.c05_txv_condicioneslegales_val);
        this.c05_nps01fecha_nps01_val = view.findViewById(R.id.c05_txv_nps01fecha_nps01_val);
        this.c05_nps02fecha_nps02_val = view.findViewById(R.id.c05_txv_nps02fecha_nps02_val);
        this.c05_nps03fecha_nps03_val = view.findViewById(R.id.c05_txv_nps03fecha_nps03_val);

        // TODO: carga de Evento
        // EOB: Intentar pasar este método a changeNoListener y eliminar las dos líneas siguientes
        // List<Valiente_val> valientes = new ArrayList<>();
        List<Persona_per_test> persona_per_test = new ArrayList<>();
        v03_00_val_Adapter v03_adapter_val = null;

        Query query1 = fbf.collection("persona_per_test").whereEqualTo("id_per", id_val_enProceso);
        tabla1ChangeListener(query1, persona_per_test, Persona_per_test.class, v03_adapter_val);

//        DocumentReference drf = fbf.collection("evento_eve").document(String.valueOf(id_eve));
//        DocumentChangeListener(drf);

        // TODO: llamada por el numero
        c05_movil_val.setOnClickListener(view13 -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", valiente.getMovil_per(), null));
            startActivity(intent);
        });

        // TODO: llamada por el whatsapp
        c05_movil_whatsapp_val.setOnClickListener(view13 -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            String texto = "Hola "
                    + valiente.getNombre_per() + " " + valiente.getApellido1_per() + ",\n Soy "
                    /* + colaborador.getNombre_col() + colaborador.getApellido1_col() + " me gustaria "*/;
            String uri = "whatsapp://send?phone=" + "codigo pais + tu numero ej: +34622666299" + "&text=" + texto;
            intent.setData(Uri.parse(uri));
            startActivity(intent);
        });

        // TODO: envio del correo
        c05_email_val.setOnClickListener(view12 -> {
            Intent Email = new Intent(Intent.ACTION_SEND);
            Email.setType("text/email");

            Email.putExtra(Intent.EXTRA_EMAIL, new String[]{valiente.getEmail_per()});
            Email.putExtra(Intent.EXTRA_CC, "hola@gmail.com");
            Email.putExtra(Intent.EXTRA_SUBJECT, "Amigos de la montaña");
            Email.putExtra(Intent.EXTRA_TEXT, "Hola "
                            + valiente.getNombre_per() + " " + valiente.getApellido1_per() + ",\n Soy "
                    /* + colaborador.getNombre_col() + colaborador.getApellido1_col() + " me gustaria "*/);
            startActivity(Intent.createChooser(Email, "Send Feedback:"));
        });
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
                    valiente = (Persona_per_test) qds.toObject(tipoObjeto);
//                    lista.add(enProceso);
//                miAdapter.notifyDataSetChanged();

                    c05_apodo_val.setText(valiente.getApodo_per());
                    c05_nombre_apellido1_apellido2_val.setText(valiente.getNombre_per() + " " + valiente.getApellido1_per() + " " + valiente.getApellido2_per());

                    FirebaseStorage fbs = FirebaseStorage.getInstance();
                    StorageReference str = fbs.getReference();
                    str.child("Valientes/" + valiente.getFotopropia_per()).getDownloadUrl().addOnSuccessListener(uri ->
                            Picasso.get().load(uri).into(c05_fotopropia_val)).addOnFailureListener(exception ->
                            Toast.makeText(getActivity(), "Error de cargar la imagen", Toast.LENGTH_LONG).show());

                    //todo: hay que recuperar los datos
                    c05_direccion_col.setText("Direccion: "/*colaborador.getDireccion_col()*/);
                    c05_cpostal_col.setText("Codigo postal: "/*colaborador.getCpostal_col()*/);
                    c05_localidad_col.setText("Localidad: "/*colaborador.getLocalidad_col()*/);

                    c05_movil_val.setText("Tel: " + valiente.getMovil_per());
                    c05_movil_whatsapp_val.setText("Whatsapp: " + valiente.getMovil_per());
                    c05_coche_val.setText("Matricula: " + valiente.getCoche_per());
                    c05_email_val.setText("Email: " + valiente.getEmail_per());
                    c05_antiguedadpre_val.setText("Antigüedad: " + valiente.getAntiguedadpre_per());
                    c05_cochepre_val.setText("Ofrece coche: " + valiente.getCochepre_per());
                    c05_fiabilidadpre_val.setText("Fiabilidad: " + valiente.getFiabilidadpre_per());
                    c05_valoracionorgpre_val.setText("Colaboración: " + valiente.getValoracionorgpre_per());
                    c05_volumencomprapre_val.setText("Volumen: " + valiente.getVolumencomprapre_per());
                    c05_nrelacionespre_val.setText("Contactos: " + valiente.getNrelacionespre_per());
                    c05_emergencia1relacion_emergencia1telefono_val.setText(valiente.getEmergencia1relacion_per() + ": " + valiente.getEmergencia1telefono_per()); // new
                    c05_emergencia2relacion_emergencia2telefono_val.setText(valiente.getEmergencia2relacion_per() + ": " + valiente.getEmergencia2telefono_per()); // new
                    c05_fechaalta_fechabaja_val.setText(valiente.getFechabaja_per() + " " + valiente.getFechabaja_per());
                    c05_dni_val.setText("DNI: " + valiente.getDni_per());
                    c05_condicioneslegales_val.setText("Condiciones " + (valiente.isCondicioneslegales_per() ? "Aceptadas" : "Rechazadas"));
                    c05_nps01fecha_nps01_val.setText(valiente.getNps01fecha_per() + " NPS3:  " + valiente.getNps01_per());
                    c05_nps02fecha_nps02_val.setText(valiente.getNps02fecha_per() + " NPS3:  " + valiente.getNps02_per());
                    c05_nps03fecha_nps03_val.setText(valiente.getNps03fecha_per() + " NPS3:  " + valiente.getNps03_per());

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
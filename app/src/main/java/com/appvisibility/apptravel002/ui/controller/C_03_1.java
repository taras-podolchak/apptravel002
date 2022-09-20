package com.appvisibility.apptravel002.ui.controller;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.entities.Persona_prs;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class C_03_1 extends Fragment {

    // Campos de xml
    private int id_prs_enProceso;
    private TextView c03_1_apodo_prs;
    private TextView c03_1_nombre_prs;
    private ImageView c03_1_fotopropia_prs;
    private TextView c03_1_direccion_prs;
    private TextView c03_1_localidad_prs;
    private TextView c03_1_movil_prs;
    private TextView c03_1_coche_prs;
    private TextView c03_1_email_prs;
    private TextView c03_1_antiguedadpre_prs;
    private TextView c03_1_cochepre_prs;
    private TextView c03_1_fiabilidadpre_prs;
    private TextView c03_1_valoracionorgpre_prs;
    private TextView c03_1_volumencomprapre_prs;
    private TextView c03_1_nrelacionespre_prs;
    private TextView c03_1_contacto1cargo_prs;
    private TextView c03_1_contacto2cargo_prs;
    private TextView c03_1_fechaalta_prs;
    private TextView c03_1_dni_prs;
    private TextView c03_1_condicioneslegales_prs;
    private TextView c03_1_nps01fecha_prs;
    private TextView c03_1_nps02fecha_prs;
    private TextView c03_1_nps03fecha_prs;

    // Acceso a datos
    FirebaseFirestore fbf = FirebaseFirestore.getInstance();

    // Entities
    private Persona_prs personaEnProceso;
    private List<Persona_prs> personas = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_c_03_1, container, false);

        Bundle bundlePersona = getArguments();
        //Recuperamos la Persona
        personaEnProceso = (Persona_prs) bundlePersona.getSerializable("personaParaC_05");

        this.c03_1_apodo_prs = view.findViewById(R.id.c03_1_txv_apodo_prs);
        this.c03_1_nombre_prs = view.findViewById(R.id.c03_1_txv_nombre_prs_apellido1_prs_apellido2_prs);
        this.c03_1_fotopropia_prs = view.findViewById(R.id.c03_1_imv_fotopropia_prs);
        this.c03_1_direccion_prs = view.findViewById(R.id.c03_1_txv_direccion_prs);
        this.c03_1_localidad_prs = view.findViewById(R.id.c03_1_txv_localidad_prs_cpostal_prs);
        this.c03_1_movil_prs = view.findViewById(R.id.c03_1_txv_movil_prs);
        this.c03_1_coche_prs = view.findViewById(R.id.c03_1_txv_coche_prs);
        this.c03_1_email_prs = view.findViewById(R.id.c03_1_txv_email_prs);
        this.c03_1_antiguedadpre_prs = view.findViewById(R.id.c03_1_txv_antiguedadpre_prs);
        this.c03_1_cochepre_prs = view.findViewById(R.id.c03_1_txv_cochepre_prs);
        this.c03_1_fiabilidadpre_prs = view.findViewById(R.id.c03_1_txv_fiabilidadpre_prs);
        this.c03_1_valoracionorgpre_prs = view.findViewById(R.id.c03_1_txv_valoracionorgpre_prs);
        this.c03_1_volumencomprapre_prs = view.findViewById(R.id.c03_1_txv_volumencomprapre_prs);
        this.c03_1_nrelacionespre_prs = view.findViewById(R.id.c03_1_txv_nrelacionespre_prs);
        this.c03_1_contacto1cargo_prs = view.findViewById(R.id.c03_1_txt_contacto1cargo_prs_contacto1movil_prs);
        this.c03_1_contacto2cargo_prs = view.findViewById(R.id.c03_1_txt_contacto2cargo_prs_contacto2movil_prs);
        this.c03_1_fechaalta_prs = view.findViewById(R.id.c03_1_txv_fechaalta_prs_fechabaja_prs);
        this.c03_1_dni_prs = view.findViewById(R.id.c03_1_txv_dni_prs);
        this.c03_1_condicioneslegales_prs = view.findViewById(R.id.c03_1_txv_condicioneslegales_prs);
        this.c03_1_nps01fecha_prs = view.findViewById(R.id.c03_1_txv_nps01_prs_nps01_prs);
        this.c03_1_nps02fecha_prs = view.findViewById(R.id.c03_1_txv_nps02_prs_nps02_prs);
        this.c03_1_nps03fecha_prs = view.findViewById(R.id.c03_1_txv_nps03_prs_nps03_prs);

        //Cargamos la Persona
        c03_1_apodo_prs.setText(personaEnProceso.getApodo_prs());
        c03_1_nombre_prs.setText(personaEnProceso.getNombre_prs() + " " + personaEnProceso.getApellido1_prs() + " " + personaEnProceso.getApellido2_prs());
        FirebaseStorage fbs = FirebaseStorage.getInstance();
        StorageReference str = fbs.getReference();
        str.child("Valientes/" + personaEnProceso.getFotopropia_prs()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(c03_1_fotopropia_prs);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(getActivity(), "Error de cargar la imagen", Toast.LENGTH_SHORT).show();
            }
        });
        c03_1_direccion_prs.setText(personaEnProceso.getDireccion_prs());
        c03_1_localidad_prs.setText(personaEnProceso.getLocalidad_prs() + " " + personaEnProceso.getCpostal_prs());
        c03_1_movil_prs.setText(personaEnProceso.getMovil_prs());
        c03_1_coche_prs.setText(personaEnProceso.getCoche_prs());
        c03_1_email_prs.setText(personaEnProceso.getEmail_prs());
        c03_1_antiguedadpre_prs.setText("Antigüedad: " + personaEnProceso.getAntiguedadpre_prs());
        c03_1_cochepre_prs.setText("Ofrece coche: " + personaEnProceso.getCochepre_prs());
        c03_1_fiabilidadpre_prs.setText("Fiabilidad: " + personaEnProceso.getFiabilidadpre_prs());
        c03_1_valoracionorgpre_prs.setText("Colaboración: " + personaEnProceso.getValoracionorgpre_prs());
        c03_1_volumencomprapre_prs.setText("Volumen: " + personaEnProceso.getVolumencomprapre_prs());
        c03_1_nrelacionespre_prs.setText("Contactos: " + personaEnProceso.getNrelacionespre_prs());
        c03_1_contacto1cargo_prs.setText(personaEnProceso.getContacto1Cargo_prs() + " " + personaEnProceso.getContacto1Movil_prs());
        c03_1_contacto2cargo_prs.setText(personaEnProceso.getContacto2Cargo_prs() + " " + personaEnProceso.getContacto2Movil_prs());
        c03_1_fechaalta_prs.setText("Alta: " + personaEnProceso.getFechaalta_prs() + " / Baja: " + personaEnProceso.getFechabaja_prs());
        c03_1_dni_prs.setText("DNI: " + personaEnProceso.getDni_prs());
        c03_1_condicioneslegales_prs.setText("Condiciones " + (personaEnProceso.isCondicioneslegales_prs() ? "Aceptadas" : "Rechazadas"));
        c03_1_nps01fecha_prs.setText(personaEnProceso.getNps01Fecha_prs() + " - NPS1: " + personaEnProceso.getNps01_prs());
        c03_1_nps02fecha_prs.setText(personaEnProceso.getNps02Fecha_prs() + " - NPS2: " + personaEnProceso.getNps02_prs());
        c03_1_nps03fecha_prs.setText(personaEnProceso.getNps03Fecha_prs() + " - NPS3: " + personaEnProceso.getNps03_prs());

        // Botones
        // Envio de whatsapp
        c03_1_movil_prs.setOnClickListener(new View.OnClickListener() {
            String mensaje = "Hola, necesito más información sobre ...";
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=+34" + personaEnProceso.getMovil_prs() + "&text=" + mensaje));
                startActivity(intent);
            }
        });

        // Envio de eMail
        c03_1_email_prs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/email")
                    .putExtra(Intent.EXTRA_EMAIL, new String[]{personaEnProceso.getEmail_prs()})
                    .putExtra(Intent.EXTRA_CC, "hola@gmail.com")
                    .putExtra(Intent.EXTRA_SUBJECT, "Amigos de la Montaña")
                    .putExtra(Intent.EXTRA_TEXT, "Hola " + personaEnProceso.getNombre_prs() + ",\n\n Soy ");
                startActivity(Intent.createChooser(intent, "Send Feedback:"));
            }
        });

        // Llamar por telefono a contacto1
//https://stackoverflow.com/questions/40125931/how-to-ask-permission-to-make-phone-call-from-android-from-android-version-marsh
//https://es.stackoverflow.com/questions/105776/android-action-dial-error-no-activity-found-to-handle-intent-act-android-inte
        c03_1_contacto1cargo_prs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + personaEnProceso.getContacto1Movil_prs()));
                startActivity(intent);
            }
        });

        // Llamar por telefono a contacto2
        c03_1_contacto2cargo_prs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + personaEnProceso.getContacto2Movil_prs().toString()));
                startActivity(intent);
            }
        });

        return view;
    }//Fin de constructor

}
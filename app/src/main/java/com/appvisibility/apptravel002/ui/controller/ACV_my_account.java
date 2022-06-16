package com.appvisibility.apptravel002.ui.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.entities.Persona_prs;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ACV_my_account#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ACV_my_account extends Fragment {

    // Campos de xml
    private ImageView acv_my_account_otopropia_prs;
    private EditText acv_my_account_nombre_prs;
    private EditText acv_my_account_apellido1_prs;
    private EditText acv_my_account_apellido2_prs;
    private EditText acv_my_account_apodo_prs;
    private EditText acv_my_account_email_prs;
    private EditText acv_my_account_contrasenna1_prs;
    private EditText acv_my_account_contrasenna2_prs;
    private EditText acv_my_account_dni_prs;
    private EditText acv_my_account_movil_prs;
    private EditText acv_my_account_contacto1movil_prs;
    private EditText acv_my_account_pais_prs;
    private EditText acv_my_account_localidad_prs;
    private EditText acv_my_account_cpostal_prs;
    private EditText acv_my_account_direccion_prs;
    private Switch acv_my_account_seguro_prs;
    private EditText acv_my_account_seguropoliza_prs;
    private EditText acv_my_account_fechacaducidadseguro_prs;
    private Button acv_my_account_volver;
    private Button acv_my_account_guardar;

    // Acceso a datos
    private FirebaseFirestore fbf = FirebaseFirestore.getInstance();
    private FirebaseStorage fbs = FirebaseStorage.getInstance();
    private StorageReference str = fbs.getReference();


    // Entities
    private String idPersona;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ACV_my_account() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ACV_my_account.
     */
    // TODO: Rename and change types and number of parameters
    public static ACV_my_account newInstance(String param1, String param2) {
        ACV_my_account fragment = new ACV_my_account();
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

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a_c_v_my_account, container, false);

        idPersona = getArguments().getString("idPersona");

        acv_my_account_otopropia_prs = view.findViewById(R.id.acv_my_account_imv_otopropia_prs);
        acv_my_account_nombre_prs = view.findViewById(R.id.acv_my_account_etx_nombre_prs);
        acv_my_account_apellido1_prs = view.findViewById(R.id.acv_my_account_etx_apellido1_prs);
        acv_my_account_apellido2_prs = view.findViewById(R.id.acv_my_account_etx_apellido2_prs);
        acv_my_account_apodo_prs = view.findViewById(R.id.acv_my_account_etx_apodo_prs);
        acv_my_account_email_prs = view.findViewById(R.id.acv_my_account_etx_email_prs);
        acv_my_account_contrasenna1_prs = view.findViewById(R.id.acv_my_account_etx_contrasenna1_prs);
        acv_my_account_contrasenna2_prs = view.findViewById(R.id.acv_my_account_etx_contrasenna2_prs);
        acv_my_account_dni_prs = view.findViewById(R.id.acv_my_account_etx_dni_prs);
        acv_my_account_movil_prs = view.findViewById(R.id.acv_my_account_etx_movil_prs);
        acv_my_account_contacto1movil_prs = view.findViewById(R.id.acv_my_account_etx_contacto1movil_prs);
        acv_my_account_pais_prs = view.findViewById(R.id.acv_my_account_etx_pais_prs);
        acv_my_account_localidad_prs = view.findViewById(R.id.acv_my_account_etx_localidad_prs);
        acv_my_account_cpostal_prs = view.findViewById(R.id.acv_my_account_etx_cpostal_prs);
        acv_my_account_direccion_prs = view.findViewById(R.id.acv_my_account_etx_direccion_prs);
        acv_my_account_seguro_prs = view.findViewById(R.id.acv_my_account_swc_seguro_prs);
        acv_my_account_seguropoliza_prs = view.findViewById(R.id.acv_my_account_etx_seguropoliza_prs);
        acv_my_account_fechacaducidadseguro_prs = view.findViewById(R.id.acv_my_account_etx_fechacaducidadseguro_prs);
        acv_my_account_volver = view.findViewById(R.id.acv_my_account_btn_volver);
        acv_my_account_guardar = view.findViewById(R.id.acv_my_account_btn_guardar);


        fbf.collection("persona_prs").document(idPersona)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                rellenarCamposDePersona(document.toObject(Persona_prs.class));
                                Toast.makeText(getContext(), "DocumentSnapshot data:", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "No such document", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "get failed with", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return view;
    }

    private void rellenarCamposDePersona(Persona_prs personaPrs) {
        str.child("Valientes/" + personaPrs.getFotopropia_prs()).getDownloadUrl().addOnSuccessListener(uri ->
                Picasso.get().load(uri).into(acv_my_account_otopropia_prs)).addOnFailureListener(exception ->
            Toast.makeText(getActivity(), "GET IMAGE FAILED", Toast.LENGTH_SHORT).show());
        acv_my_account_nombre_prs.setText(personaPrs.getNombre_prs());
        acv_my_account_apellido1_prs.setText(personaPrs.getApellido1_prs());
        acv_my_account_apellido2_prs.setText(personaPrs.getApellido2_prs());
        acv_my_account_apodo_prs.setText(personaPrs.getApodo_prs());
        acv_my_account_email_prs.setText(personaPrs.getEmail_prs());
        // acv_my_account_contrasenna1_prs.setText(personaPrs.contr
        //    acv_my_account_contrasenna2_prs.setText(personaPrs.
        acv_my_account_dni_prs.setText(personaPrs.getDni_prs());
        acv_my_account_movil_prs.setText(personaPrs.getMovil_prs());
        acv_my_account_contacto1movil_prs.setText(personaPrs.getContacto1Movil_prs());
        acv_my_account_pais_prs.setText(personaPrs.getPais_prs());
        acv_my_account_localidad_prs.setText(personaPrs.getLocalidad_prs());
        acv_my_account_cpostal_prs.setText(personaPrs.getCpostal_prs());
        acv_my_account_direccion_prs.setText(personaPrs.getDireccion_prs());
        //acv_my_account_seguro_prs.setText(personaPrs.getSeguro_prs());
        acv_my_account_seguropoliza_prs.setText(personaPrs.getSeguropoliza_prs());
        acv_my_account_fechacaducidadseguro_prs.setText(personaPrs.getFechacaducidadseguro_prs());

    }
}
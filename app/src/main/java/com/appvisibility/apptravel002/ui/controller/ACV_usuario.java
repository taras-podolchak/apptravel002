package com.appvisibility.apptravel002.ui.controller;

import static com.appvisibility.apptravel002.MainActivity_val.sesionIniciada;
import static com.appvisibility.apptravel002.ui.service.Persona_prsService.personaUserU;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.appvisibility.apptravel002.MainActivity_val;
import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.entities.Persona_prs;
import com.appvisibility.apptravel002.ui.service.Alimentacion_aliService;
import com.appvisibility.apptravel002.ui.service.Identidad_idtService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ACV_usuario#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ACV_usuario extends Fragment {

    // Campos de xml
    private Button acv_adelante, acv_atras;
    private ImageView acv_usuario_fotopropia_prs;
    public static LinearLayout acv_usuario_identidad1;
    public static LinearLayout acv_usuario_identidad2;
    public static TextView acv_usuario_apodo_prs;
    public static TextView acv_usuario_nombre_prs;
    public static TextView acv_usuario_apellido1_prs;
    public static TextView acv_usuario_apellido2_prs;
//    public static TextView acv_usuario_contrasenna1_prs;
//    private TextView acv_usuario_contrasenna2_prs;
    public Spinner acv_usuario_spn_actividadtipo_prs;
    public Spinner acv_usuario_spn_documentotipo_prs;
    public TextView acv_usuario_actividadtipo_prs;
    public TextView acv_usuario_documentotipo_prs;
    public static TextView acv_usuario_dni_prs;
    public static TextView acv_usuario_razonsocial_prs;
    public static TextView acv_usuario_numerocta_prs;
    public static TextView acv_usuario_direccion_prs;
    public static TextView acv_usuario_localidad_prs;
    public static TextView acv_usuario_cpostal_prs;
    public static TextView acv_usuario_pais_prs;
    public static TextView acv_usuario_movil_prs;
    public static TextView acv_usuario_email_prs;
    public static TextView acv_usuario_telefono_prs;
    public static TextView acv_usuario_web_prs;
    public static TextView acv_usuario_alimentacion_prs;
    public Switch acv_usuario_federado_prs;
    public Switch acv_usuario_seguro_prs;
    public static TextView acv_usuario_segurocompannia_prs;
    public static TextView acv_usuario_seguropoliza_prs;
    public static TextView acv_usuario_fechacaducidadseguro_prs;
    public static TextView acv_usuario_fechaalta_prs;
    public static TextView acv_usuario_antiguedad_prs;
    public static TextView acv_usuario_fechabaja_prs;
    public Switch acv_usuario_solicitabaja_prs;
    public static TextView acv_usuario_preferencia_prs;
    public static TextView acv_usuario_fiabilidadpre_prs;
    public static TextView acv_usuario_valoracionorgpre_prs;
    public static TextView acv_usuario_antiguedadpre_prs;
    public static TextView acv_usuario_volumencomprapre_prs;
    public static TextView acv_usuario_cochepre_prs;
    public static TextView acv_usuario_nrelacionespre_prs;
    public static TextView acv_usuario_nps01_prs;
    public static TextView acv_usuario_nps01fecha_prs;
    public static TextView acv_usuario_nps02_prs;
    public static TextView acv_usuario_nps02fecha_prs;
    public static TextView acv_usuario_nps03_prs;
    public static TextView acv_usuario_nps03fecha_prs;
    public Switch acv_usuario_condicioneslegales_prs;

    // Acceso a datos
    private FirebaseFirestore fbf = FirebaseFirestore.getInstance();
    private FirebaseStorage fbs = FirebaseStorage.getInstance();
    private StorageReference str = fbs.getReference();

    // Entities
    private String idPersona;
    private Persona_prs personaUser;
    private ArrayAdapter<String> arrayAdapter_act;
    private ArrayAdapter<String> arrayAdapter_idt;
    private Boolean datosActualizados;
    private Identidad_idtService identidadService = new Identidad_idtService();
    private Alimentacion_aliService alimentacion = new Alimentacion_aliService();

    // Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Rename and change types of parameters
    private String mParam1;
    private Bundle mBundlePersonaUser;

    private Context mContext;

    public ACV_usuario() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param bundlePersonaUser Parameter 2.
     * @return A new instance of fragment ACV_my_account.
     */
    // Rename and change types and number of parameters
    public static ACV_usuario newInstance(String param1, Bundle bundlePersonaUser) {
        ACV_usuario fragment = new ACV_usuario();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putBundle(ARG_PARAM2, bundlePersonaUser);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mBundlePersonaUser = getArguments().getBundle(ARG_PARAM2);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_acv_usuario, container, false);

//https://stackoverflow.com/questions/12739909/send-data-from-activity-to-fragment-in-android
/* So, to pass data from the MotherActivity to such a Fragment you will need to create private Strings/Bundles above the onCreate of your Mother activity - which you can fill with the data you want to pass to the fragments, and pass them on via a method created after the onCreate (here called getMyData()).*/
//Recuperamos los datos del Usuario activo
/*
        MainActivity_val activity = (MainActivity_val) getActivity();
        Bundle bundlePersonaUser = activity.getUser();
        personaUser = (Persona_prs) bundlePersonaUser.getSerializable("User");
*/
        MainActivity_val activity = (MainActivity_val) getActivity();
        mBundlePersonaUser = activity.getUser();
        personaUser = (Persona_prs) mBundlePersonaUser.getSerializable("User");

        idPersona = getArguments().getString("idPersona");

        String [] actividadtipos_prs = view.getResources().getStringArray(R.array.actividadTipos_prs);

        arrayAdapter_act = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, actividadtipos_prs);
        arrayAdapter_act.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        String [] documentotipos_prs = view.getResources().getStringArray(R.array.documentoTipos_prs);

        arrayAdapter_idt = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, documentotipos_prs);
        arrayAdapter_idt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        acv_usuario_fotopropia_prs = view.findViewById(R.id.acv_usuario_imv_fotopropia_prs);

        acv_usuario_identidad1 = view.findViewById(R.id.acv_usuario_lly_identidad1);
        acv_usuario_apodo_prs = view.findViewById(R.id.acv_usuario_txv_apodo_prs);
        acv_usuario_nombre_prs = view.findViewById(R.id.acv_usuario_txv_nombre_prs);
        acv_usuario_apellido1_prs = view.findViewById(R.id.acv_usuario_txv_apellido1_prs);
        acv_usuario_apellido2_prs = view.findViewById(R.id.acv_usuario_txv_apellido2_prs);
//        acv_usuario_contrasenna1_prs = view.findViewById(R.id.acv_usuario_txv_contrasenna1_prs);
//        acv_usuario_contrasenna2_prs = view.findViewById(R.id.acv_usuario_txv_contrasenna2_prs);

        acv_usuario_identidad2 = view.findViewById(R.id.acv_usuario_lly_identidad2);
        acv_usuario_actividadtipo_prs = view.findViewById(R.id.acv_usuario_txv_actividadtipo_prs);
        acv_usuario_documentotipo_prs = view.findViewById(R.id.acv_usuario_txv_documentotipo_prs);
        acv_usuario_dni_prs = view.findViewById(R.id.acv_usuario_txv_dni_prs);
        acv_usuario_razonsocial_prs = view.findViewById(R.id.acv_usuario_txv_razonsocial_prs);
        acv_usuario_numerocta_prs = view.findViewById(R.id.acv_usuario_txv_numerocta_prs);

        acv_usuario_direccion_prs = view.findViewById(R.id.acv_usuario_txv_direccion_prs);
        acv_usuario_localidad_prs = view.findViewById(R.id.acv_usuario_txv_localidad_prs);
        acv_usuario_cpostal_prs = view.findViewById(R.id.acv_usuario_txv_cpostal_prs);
        acv_usuario_pais_prs = view.findViewById(R.id.acv_usuario_txv_pais_prs);
        acv_usuario_movil_prs = view.findViewById(R.id.acv_usuario_txv_movil_prs);
        acv_usuario_email_prs = view.findViewById(R.id.acv_usuario_txv_email_prs);
        acv_usuario_telefono_prs = view.findViewById(R.id.acv_usuario_txv_telefono_prs);
        acv_usuario_web_prs = view.findViewById(R.id.acv_usuario_txv_web_prs);
        acv_usuario_alimentacion_prs = view.findViewById(R.id.acv_usuario_txv_alimentacion_prs);
        acv_usuario_federado_prs = view.findViewById(R.id.acv_usuario_swc_federado_prs);
        acv_usuario_seguro_prs = view.findViewById(R.id.acv_usuario_swc_seguro_prs);
        acv_usuario_segurocompannia_prs = view.findViewById(R.id.acv_usuario_txv_segurocompannia_prs);
        acv_usuario_seguropoliza_prs = view.findViewById(R.id.acv_usuario_txv_seguropoliza_prs);
        acv_usuario_fechacaducidadseguro_prs = view.findViewById(R.id.acv_usuario_txv_fechacaducidadseguro_prs);
        acv_usuario_fechaalta_prs = view.findViewById(R.id.acv_usuario_txv_fechaalta_prs);
        acv_usuario_antiguedad_prs = view.findViewById(R.id.acv_usuario_txv_antiguedad_prs);
        acv_usuario_fechabaja_prs = view.findViewById(R.id.acv_usuario_txv_fechabaja_prs);
        acv_usuario_solicitabaja_prs = view.findViewById(R.id.acv_usuario_swc_solicitabaja_prs);
        acv_usuario_preferencia_prs = view.findViewById(R.id.acv_usuario_txv_preferencia_prs);
        acv_usuario_fiabilidadpre_prs = view.findViewById(R.id.acv_usuario_txv_fiabilidadpre_prs);
        acv_usuario_valoracionorgpre_prs = view.findViewById(R.id.acv_usuario_txv_valoracionorgpre_prs);
        acv_usuario_antiguedadpre_prs = view.findViewById(R.id.acv_usuario_txv_antiguedadpre_prs);
        acv_usuario_volumencomprapre_prs = view.findViewById(R.id.acv_usuario_txv_volumencomprapre_prs);
        acv_usuario_cochepre_prs = view.findViewById(R.id.acv_usuario_txv_cochepre_prs);
        acv_usuario_nrelacionespre_prs = view.findViewById(R.id.acv_usuario_txv_nrelacionespre_prs);
        acv_usuario_nps01_prs = view.findViewById(R.id.acv_usuario_txv_nps01_prs);
        acv_usuario_nps01fecha_prs = view.findViewById(R.id.acv_usuario_txv_nps01fecha_prs);
        acv_usuario_nps02_prs = view.findViewById(R.id.acv_usuario_txv_nps02_prs);
        acv_usuario_nps02fecha_prs = view.findViewById(R.id.acv_usuario_txv_nps02fecha_prs);
        acv_usuario_nps03_prs = view.findViewById(R.id.acv_usuario_txv_nps03_prs);
        acv_usuario_nps03fecha_prs = view.findViewById(R.id.acv_usuario_txv_nps03fecha_prs);
        acv_usuario_condicioneslegales_prs = view.findViewById(R.id.acv_usuario_swc_condicioneslegales_prs);

        if (sesionIniciada == view.getResources().getInteger(R.integer.rol_transportecolectivo)) {
// https://stackoverflow.com/questions/4622517/hide-a-edittext-make-it-visible-by-clicking-a-menu
            acv_usuario_razonsocial_prs.setVisibility(View.GONE);
            acv_usuario_numerocta_prs.setVisibility(View.GONE);
            acv_usuario_direccion_prs.setVisibility(View.GONE);
            acv_usuario_localidad_prs.setVisibility(View.GONE);
            acv_usuario_cpostal_prs.setVisibility(View.GONE);
            acv_usuario_pais_prs.setVisibility(View.GONE);
            acv_usuario_web_prs.setVisibility(View.GONE);
            acv_usuario_razonsocial_prs.setVisibility(View.GONE);
        } else if (sesionIniciada == view.getResources().getInteger(R.integer.rol_valiente)
                || sesionIniciada == view.getResources().getInteger(R.integer.rol_alojamiento)
                || sesionIniciada == view.getResources().getInteger(R.integer.rol_empresas_trekking)) {
//            V_05.v05_recomendacionContacto.setVisibility(View.GONE);
        }


        fbf.collection("persona_prs").document(idPersona)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                mostrarUsuarioEnProceso(document.toObject(Persona_prs.class));
                                Toast.makeText(getContext(), "Datos del usuario recibidos:", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "Datos del usuario no encontrados", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "get failed with", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        acv_usuario_identidad1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Identidad_idtService.newInstance(null, null);
                identidadService.onCreateView(inflater, container, savedInstanceState);
            }
        });

        acv_usuario_identidad2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Identidad_idtService.newInstance(null, null);
                identidadService.onCreateView(inflater, container, savedInstanceState);
            }
        });

        Alimentacion_aliService.newInstance(null, mBundlePersonaUser);
        alimentacion.mostrarAlimentacionEnProceso();
        acv_usuario_alimentacion_prs.setText(Alimentacion_aliService.alimentacionSpanned);
        acv_usuario_alimentacion_prs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alimentacion.indicarRestriccionesAlimentarias(view);
            }
        });

        // Botones
        acv_adelante = view.findViewById(R.id.acv_usuario_btn_adelante);
        acv_adelante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datosActualizados = personaUserU(datosActualizados, personaUser);
                if (datosActualizados) {
                    Toast.makeText(getActivity(), "Se han actualizado tus datos", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "No se han podido actualizar tus datos", Toast.LENGTH_LONG).show();
                }
            }
        });

        acv_atras = view.findViewById(R.id.acv_usuario_btn_atras);
        acv_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "No se han actualizado tus datos", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }//Fin de constructor

    private void mostrarUsuarioEnProceso(Persona_prs usuarioEnProceso) {
        str.child("Valientes/" + usuarioEnProceso.getFotopropia_prs()).getDownloadUrl().addOnSuccessListener(uri ->
                Picasso.get().load(uri).into(acv_usuario_fotopropia_prs)).addOnFailureListener(exception ->
            Toast.makeText(getActivity(), "GET IMAGE FAILED", Toast.LENGTH_SHORT).show());
        acv_usuario_apodo_prs.setText(usuarioEnProceso.getApodo_prs());
        // acv_usuario_contrasenna1_prs.setText(usuarioEnProceso.contr
        // acv_usuario_contrasenna2_prs.setText(usuarioEnProceso.
        acv_usuario_nombre_prs.setText(usuarioEnProceso.getNombre_prs());
        acv_usuario_apellido1_prs.setText(usuarioEnProceso.getApellido1_prs());
        acv_usuario_apellido2_prs.setText(usuarioEnProceso.getApellido2_prs());

        acv_usuario_actividadtipo_prs.setText(personaUser.getActividadtipo_prs());
        acv_usuario_documentotipo_prs.setText(personaUser.getDocumentotipo_prs());
/*
        int indexOfPreviousSelection;
        acv_usuario_spn_actividadtipo_prs.setAdapter(arrayAdapter_act);
// https://stackoverflow.com/questions/37481951/how-to-get-and-set-selected-item-from-spinner-using-sharedpreferences
// Recupera spinner anterior
        acv_usuario_spn_actividadtipo_prs.getSelectedItemPosition();
        indexOfPreviousSelection = arrayAdapter_act.getPosition(personaUser.getActividadtipo_prs());
        acv_usuario_spn_actividadtipo_prs.setSelection(indexOfPreviousSelection);
        acv_usuario_actividadtipo_prs.setText(acv_usuario_spn_actividadtipo_prs.getSelectedItem().toString());

 */
/*
        acv_usuario_spn_documentotipo_prs.setAdapter(arrayAdapter_idt);
// https://stackoverflow.com/questions/37481951/how-to-get-and-set-selected-item-from-spinner-using-sharedpreferences
// Recupera spinner anterior
        acv_usuario_spn_documentotipo_prs.getSelectedItemPosition();
        indexOfPreviousSelection = arrayAdapter_idt.getPosition(personaUser.getActividadtipo_prs());
        acv_usuario_spn_documentotipo_prs.setSelection(acv_usuario_spn_documentotipo_prs.getSelectedItemPosition());
        acv_usuario_documentotipo_prs.setText(acv_usuario_spn_documentotipo_prs.getSelectedItem().toString());
*/


//        acv_usuario_actividadtipo_prs.setText(usuarioEnProceso.getActividadtipo_prs());
//        acv_usuario_documentotipo_prs.setText(usuarioEnProceso.getDocumentotipo_prs());
        acv_usuario_dni_prs.setText(usuarioEnProceso.getDni_prs());
        acv_usuario_razonsocial_prs.setText(usuarioEnProceso.getRazonsocial_prs());
        acv_usuario_numerocta_prs.setText(usuarioEnProceso.getNumerocta_prs());

        acv_usuario_direccion_prs.setText(usuarioEnProceso.getDireccion_prs());
        acv_usuario_localidad_prs.setText(usuarioEnProceso.getLocalidad_prs());
        acv_usuario_cpostal_prs.setText(usuarioEnProceso.getCpostal_prs());
        acv_usuario_pais_prs.setText(usuarioEnProceso.getPais_prs());
        acv_usuario_movil_prs.setText(usuarioEnProceso.getMovil_prs());
        acv_usuario_email_prs.setText(usuarioEnProceso.getEmail_prs());
        acv_usuario_telefono_prs.setText(usuarioEnProceso.getTelefono_prs());
        acv_usuario_web_prs.setText(usuarioEnProceso.getWeb_prs());
        acv_usuario_alimentacion_prs.setText(usuarioEnProceso.getAlimentacion_prs());
        //acv_usuario_federado_prs.setText(usuarioEnProceso.getFederado_prs());
        //acv_usuario_seguro_prs.setText(usuarioEnProceso.getSeguro_prs());
        acv_usuario_segurocompannia_prs.setText(usuarioEnProceso.getSegurocompannia_prs());
        acv_usuario_seguropoliza_prs.setText(usuarioEnProceso.getSeguropoliza_prs());
        acv_usuario_fechacaducidadseguro_prs.setText(usuarioEnProceso.getFechacaducidadseguro_prs());
        acv_usuario_fechaalta_prs.setText(usuarioEnProceso.getFechaalta_prs());
        acv_usuario_antiguedad_prs.setText(usuarioEnProceso.getAntiguedad_prs());
        acv_usuario_fechabaja_prs.setText(usuarioEnProceso.getFechabaja_prs());
        //acv_usuario_solicitabaja_prs.setText(usuarioEnProceso.getSolicitabaja_prs());
        acv_usuario_preferencia_prs.setText(String.valueOf(usuarioEnProceso.getPreferencia_prs()));
        acv_usuario_fiabilidadpre_prs.setText(String.valueOf(usuarioEnProceso.getFiabilidadpre_prs()));
        acv_usuario_valoracionorgpre_prs.setText(String.valueOf(usuarioEnProceso.getValoracionorgpre_prs()));
        acv_usuario_antiguedadpre_prs.setText(String.valueOf(usuarioEnProceso.getAntiguedadpre_prs()));
        acv_usuario_volumencomprapre_prs.setText(String.valueOf(usuarioEnProceso.getVolumencomprapre_prs()));
        acv_usuario_cochepre_prs.setText(String.valueOf(usuarioEnProceso.getCochepre_prs()));
        acv_usuario_nrelacionespre_prs.setText(String.valueOf(usuarioEnProceso.getNrelacionespre_prs()));
        acv_usuario_nps01_prs.setText(String.valueOf(usuarioEnProceso.getNps01_prs()));
        acv_usuario_nps01fecha_prs.setText(usuarioEnProceso.getNps01Fecha_prs());
        acv_usuario_nps02_prs.setText(String.valueOf(usuarioEnProceso.getNps02_prs()));
        acv_usuario_nps02fecha_prs.setText(usuarioEnProceso.getNps02Fecha_prs());
        acv_usuario_nps03_prs.setText(String.valueOf(usuarioEnProceso.getNps03_prs()));
        acv_usuario_nps03fecha_prs.setText(usuarioEnProceso.getNps03Fecha_prs());
        //acv_usuario_condicioneslegales_prs.setText(usuarioEnProceso.getCondicioneslegales_prs());
    }
}
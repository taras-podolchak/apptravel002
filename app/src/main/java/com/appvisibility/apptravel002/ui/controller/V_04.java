package com.appvisibility.apptravel002.ui.controller;

import static android.content.ContentValues.TAG;
import static com.appvisibility.apptravel002.MainActivity_val.sesionIniciada;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appvisibility.apptravel002.MainActivity_adm;
import com.appvisibility.apptravel002.MainActivity_col;
import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.entities.Evento_eve;
import com.appvisibility.apptravel002.ui.entities.Persona_prs;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link V_04#newInstance} factory method to
 * create an instance of this fragment.
 */
public class V_04 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Campos de xml
    private Button v04_iniciar_sesion, v04_registrarse;
    private EditText v04_email_prs;
    private EditText v04_contrasenna_prs;

    // Acceso a datos
    private FirebaseAuth fba = FirebaseAuth.getInstance();
    private FirebaseFirestore fbf = FirebaseFirestore.getInstance();

    // Entities
    private Context mContext;
    private Bundle id_eve_bundle_put;
    private Persona_prs persona;
    int acceso = 0;
    private Evento_eve eventoEnProceso;
    private Bundle bundleEvento;

    // Service
    private ProgressDialog pdg;

    public V_04() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment V_04.
     */
    // TODO: Rename and change types and number of parameters
    public static V_04 newInstance(String param1, String param2) {
        V_04 fragment = new V_04();
        Bundle bundel = new Bundle();
        bundel.putString(ARG_PARAM1, param1);
        bundel.putString(ARG_PARAM2, param2);
        fragment.setArguments(bundel);
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
        View view = inflater.inflate(R.layout.fragment_v_04, container, false);

        bundleEvento = getArguments();
        acceso = bundleEvento.getInt("accesoParaV_04");

        eventoEnProceso = (Evento_eve) bundleEvento.getSerializable("eventoParaV_04");

        bundleEvento.putSerializable("eventoParaV_04_1", eventoEnProceso);
        bundleEvento.putSerializable("eventoParaV_05", eventoEnProceso);

        //Referenciamos los views
        v04_email_prs = view.findViewById(R.id.v04_etx_email_prs);
        v04_contrasenna_prs = view.findViewById(R.id.v04_etx_contrasenna_prs);
        v04_iniciar_sesion = view.findViewById(R.id.v04_btn_iniciar_sesion);
        v04_registrarse = view.findViewById(R.id.v04_btn_registrarse);

        pdg = new ProgressDialog(mContext);

        // Botones
        v04_iniciar_sesion.setOnClickListener(view1 -> iniciarSesion(view1));
        v04_registrarse.setOnClickListener(view12 -> Navigation.findNavController(view12).navigate(R.id.action_nav_v04_to_nav_v04_1, bundleEvento));
        return view;
    }

    private void iniciarSesion(View view) {
        //Obtenemos el email_prs y la contraseña desde las cajas de texto
        String email_prs = v04_email_prs.getText().toString().trim();
        String contrasenna_prs = v04_contrasenna_prs.getText().toString().trim();

        //Verificamos que las cajas de texto no estén vacías
        if (TextUtils.isEmpty(email_prs)) {
            Toast.makeText(getActivity(), "Se debe ingresar un email_prs", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(contrasenna_prs)) {
            Toast.makeText(getActivity(), "Falta ingresar la contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        fba.signInWithEmailAndPassword(email_prs, contrasenna_prs)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        //obtenemos la Uid del registro de la bbdd FirebaseAuth
                        FirebaseUser user = fba.getCurrentUser();
                        String uid = user.getUid();

                        //buscamos en FirebaseFirestore la persona con esa Uid
                        DocumentReference docRef = fbf.collection("persona_prs").document(uid);
                        docRef.get().addOnCompleteListener(task1 -> {
                            if (task1.isSuccessful()) {
                                DocumentSnapshot document = task1.getResult();
                                if (document.exists()) {
                                    //recuperamos la persona
                                    persona = document.toObject(Persona_prs.class);
                                    Toast.makeText(getActivity(), "Bien venido " + persona.getNombre_prs(), Toast.LENGTH_SHORT).show();

                                    //si es valiente
                                    if (persona.getUsuariotipo_prs() == getResources().getInteger(R.integer.rol_valiente)) {
                                        sesionIniciada = persona.getUsuariotipo_prs();
                                        Navigation.findNavController(view).navigate(R.id.action_nav_v04_to_nav_v05, bundleEvento);
                                    }
                                    //si es colaborador
                                    if (persona.getUsuariotipo_prs() == getResources().getInteger(R.integer.rol_colaborador)) {
                                        sesionIniciada = persona.getUsuariotipo_prs();

                                        // si colaborador entra por el menu (sin elegir el evento) navegamos a 01
                                        if (eventoEnProceso == null) {
                                            startActivity(new Intent(getActivity(), MainActivity_col.class).putExtra("abrirEnMainActivity_col", getResources().getInteger(R.integer.accion_a_v01)));
                                        }

                                        //si colaborador entra con el bundle desde 03, tiene que ir a 05 con bundle
                                        else {
                                            startActivity(new Intent(getActivity(), MainActivity_col.class).putExtra("abrirEnMainActivity_col", eventoEnProceso.getId_eve()).putExtras(bundleEvento));
                                        }
                                    }

                                    //si es administrador
                                    if (persona.getUsuariotipo_prs() == getResources().getInteger(R.integer.rol_administrador)) {
                                        sesionIniciada = persona.getUsuariotipo_prs();

                                        // si administrador entra por el menu (sin elegir el evento) navegamos a 01
                                        if (eventoEnProceso == null) {
                                            startActivity(new Intent(getActivity(), MainActivity_adm.class).putExtra("adm", getResources().getInteger(R.integer.accion_a_v01)));
                                        }

                                        //si administrador entra con el bundle desde 03, tiene que ir a 05 con bundle
                                        else {
                                            startActivity(new Intent(getActivity(), MainActivity_adm.class).putExtra("abrirEnMainActivity_adm", eventoEnProceso.getId_eve()).putExtras(bundleEvento));
                                        }
                                    }
                                } else {
                                    Log.d(TAG, "No such document");
                                }
                            } else {
                                Log.d(TAG, "get failed with ", task1.getException());
                            }
                        });
                    } else {
                        Toast.makeText(getActivity(), "El email_prs o la contraseña es incorrecta", Toast.LENGTH_SHORT).show();
                    }
                    pdg.dismiss();
                });
        pdg.setMessage("Realizando registro en linea...");
        pdg.show();
    }
}

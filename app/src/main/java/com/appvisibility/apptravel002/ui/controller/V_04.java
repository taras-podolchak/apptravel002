package com.appvisibility.apptravel002.ui.controller;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

import static com.appvisibility.apptravel002.MainActivity.sesionIniciada;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCaller;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appvisibility.apptravel002.MainActivity;
import com.appvisibility.apptravel002.MainActivity_col;
import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.entities.Persona_per_test;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.installations.Utils;

import java.util.zip.Inflater;

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

    //TODO:los campos de xml
    private Button v04_iniciar_sesion, v04_registrarse;
    private EditText v04_email_val;
    private EditText v04_contrasenna_val;

    //TODO:acceso a datos
    private FirebaseAuth fba = FirebaseAuth.getInstance();
    private FirebaseFirestore fbf = FirebaseFirestore.getInstance();

    //TODO:entities
    private Context mContext;
    private Bundle result;
    private Persona_per_test persona;
    int id_eve_bundle = 0;

    //TODO:servise
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


       // id_eve_bundle = getArguments().getInt("eventoParaV_04", 0);

        result = new Bundle();
        result.putInt("eventoParaV_04_1", id_eve_bundle);
        result.putInt("eventoParaV_05", id_eve_bundle);

        //Referenciamos los views
        v04_email_val = view.findViewById(R.id.v04_etx_email_val);
        v04_contrasenna_val = view.findViewById(R.id.v04_etx_contrasenna_val);
        v04_iniciar_sesion = view.findViewById(R.id.v04_btn_iniciar_sesion);
        v04_registrarse = view.findViewById(R.id.v04_btn_registrarse);

        pdg = new ProgressDialog(mContext);

        //TODO: los botones
        v04_iniciar_sesion.setOnClickListener(view1 -> iniciarSesion(view1));
        v04_registrarse.setOnClickListener(view12 -> Navigation.findNavController(view12).navigate(R.id.action_nav_v04_to_nav_v04_1, result));
        return view;
    }

    private void iniciarSesion(View view) {
        //Obtenemos el email_val y la contraseña desde las cajas de texto
        String email_val = v04_email_val.getText().toString().trim();
        String contrasenna_val = v04_contrasenna_val.getText().toString().trim();

        //Verificamos que las cajas de texto no estén vacías
        if (TextUtils.isEmpty(email_val)) {
            Toast.makeText(getActivity(), "Se debe ingresar un email_val", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(contrasenna_val)) {
            Toast.makeText(getActivity(), "Falta ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        fba.signInWithEmailAndPassword(email_val, contrasenna_val)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        //obtenemos la Uid del registro de la bbdd FirebaseAuth
                        FirebaseUser user = fba.getCurrentUser();
                        String uid = user.getUid();

                        //buscamos en FirebaseFirestore el documento con esa Uid
                        DocumentReference docRef = fbf.collection("persona_per_test").document(uid);
                        docRef.get().addOnCompleteListener(task1 -> {
                            if (task1.isSuccessful()) {
                                DocumentSnapshot document = task1.getResult();
                                if (document.exists()) {
                                    //recuperamos la persona
                                    persona = (Persona_per_test) document.toObject(Persona_per_test.class);
                                    Toast.makeText(getActivity(), "Bien venido " + persona.getNombre_per(), Toast.LENGTH_LONG).show();
                                    //si es valiente
                                    if (persona.getId_val_col_per() == 1) {
                                        sesionIniciada = persona.getId_val_col_per();
                                        Navigation.findNavController(view).navigate(R.id.action_nav_v04_to_nav_v05, result);
                                    }
                                    //si es colaborador
                                    if (persona.getId_val_col_per() == 2) {
                                        sesionIniciada = persona.getId_val_col_per();

                                        Intent intent = new Intent(getActivity(), MainActivity_col.class);
                                        intent.putExtra("abrirEnMainActivity_col", 1);
                                        startActivity(intent);
                                    }
                                } else {
                                    Log.d(TAG, "No such document");
                                }
                            } else {
                                Log.d(TAG, "get failed with ", task1.getException());
                            }
                        });
                    } else {
                        Toast.makeText(getActivity(), "El email_val o la contraseña es incorrecta", Toast.LENGTH_LONG).show();
                    }
                    pdg.dismiss();
                });
        pdg.setMessage("Realizando registro en linea...");
        pdg.show();
    }
}

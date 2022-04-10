package com.appvisibility.apptravel002.ui.controller;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appvisibility.apptravel002.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


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
    private EditText v04_1_email_val;
    private EditText v04_1_contrasenna_val;

    //TODO:acceso a datos
    private FirebaseAuth firebaseAuth;

    //TODO:entities
    private Context mContext;

    //TODO:servise
    private ProgressDialog progressDialog;

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
        View view = inflater.inflate(R.layout.fragment_v_04,
                container, false);

        //inicializamos el objeto firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        //Referenciamos los views
        v04_1_email_val = view.findViewById(R.id.v04_etx_email_val);
        v04_1_contrasenna_val = view.findViewById(R.id.v04_etx_contrasenna_val);
        v04_iniciar_sesion = view.findViewById(R.id.v04_btn_iniciar_sesion);
        v04_registrarse = view.findViewById(R.id.v04_btn_registrarse);

        progressDialog = new ProgressDialog(mContext);

        v04_iniciar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarUsuario(view);
            }
        });
        v04_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_v04_to_nav_v04_1);
            }
        });
        return view;
    }

    private void registrarUsuario(View view) {

        //Obtenemos el email_val y la contraseña desde las cajas de texto
        String email_val = v04_1_email_val.getText().toString().trim();
        String contrasenna_val = v04_1_contrasenna_val.getText().toString().trim();

        //Verificamos que las cajas de texto no estén vacías
        if (TextUtils.isEmpty(email_val)) {
            Toast.makeText(getActivity(), "Se debe ingresar un email_val", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(contrasenna_val)) {
            Toast.makeText(getActivity(), "Falta ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(email_val, contrasenna_val).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Navigation.findNavController(view).navigate(R.id.action_nav_v04_to_nav_v05);
                } else {
                    Toast.makeText(getActivity(), "El email_val o la contraseña es incorrecta", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });

        progressDialog.setMessage("Realizando registro en linea...");
        progressDialog.show();


    }
}

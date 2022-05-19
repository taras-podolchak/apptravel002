package com.appvisibility.apptravel002.ui.controller;

import static com.appvisibility.apptravel002.MainActivity.sesionIniciada;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.appvisibility.apptravel002.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link V_04_1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class V_04_1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Campos de xml
    private Button v04_1_aceptar, v04_1_volver;
    private EditText v04_1_nombre_prs;
    private EditText v04_1_apellido1_prs;
    private EditText v04_1_apellido2_prs;
    private EditText v04_1_email_prs;
    private EditText v04_1_contrasenna1_prs;
    private EditText v04_1_contrasenna2_prs;
    private EditText v04_1_dni_prs;
    private EditText v04_1_movil_prs;
    private CheckBox v04_1_condicioneslegales_prs;

    // Acceso a datos
    private FirebaseAuth fba = FirebaseAuth.getInstance();
    private FirebaseFirestore fbf = FirebaseFirestore.getInstance();

    // Entities
    private Context mContext;
    private Bundle result;

    // Service
    private ProgressDialog pdg;
    private int usuariotipo = 1;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment V_04_1.
     */
    // TODO: Rename and change types and number of parameters
    public static V_04_1 newInstance(String param1, String param2) {
        V_04_1 fragment = new V_04_1();
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
        View view = inflater.inflate(R.layout.fragment_v_04_1, container, false);

        int id_eve_bundle = getArguments().getInt("eventoParaV_04_1");

        result = new Bundle();
        result.putInt("eventoParaV_05", id_eve_bundle);

        v04_1_nombre_prs = view.findViewById(R.id.v04_1_etx_nombre_prs);
        v04_1_apellido1_prs = view.findViewById(R.id.v04_1_etx_apellido1_prs);
        v04_1_apellido2_prs = view.findViewById(R.id.v04_1_etx_apellido2_prs);
        v04_1_email_prs = view.findViewById(R.id.v04_1_etx_email_prs);
        v04_1_contrasenna1_prs = view.findViewById(R.id.v04_1_etx_contrasenna1_prs);
        v04_1_contrasenna2_prs = view.findViewById(R.id.v04_1_etx_contrasenna2_prs);
        v04_1_dni_prs = view.findViewById(R.id.v04_1_etx_dni_prs);
        v04_1_movil_prs = view.findViewById(R.id.v04_1_etx_movil_prs);
        v04_1_condicioneslegales_prs = view.findViewById(R.id.v04_1_ckb_condicioneslegales_prs);
        v04_1_aceptar = view.findViewById(R.id.v04_1_btn_aceptar);
        v04_1_volver = view.findViewById(R.id.v04_1_btn_volver);
        RadioGroup radioGroup = view.findViewById(R.id.v04_1_RadioGroup);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.v04_1_rbt_soy_persona:
                    Toast.makeText(getActivity(), "Me registro como valiente", Toast.LENGTH_SHORT).show();
                    usuariotipo = 1;
                    break;
                case R.id.v04_1_rbt_soy_colaborador:
                    Toast.makeText(getActivity(), "Me registro como colaborador", Toast.LENGTH_SHORT).show();
                    usuariotipo = 2;
                    break;
            }
        });

        pdg = new ProgressDialog(mContext);
        v04_1_aceptar.setOnClickListener(view12 -> {
            //la navegacion se hace en registrarUsuario despues de que todos datos tienen exito
            registrarUsuario(view12);
        });

        v04_1_volver.setOnClickListener(view1 -> Navigation.findNavController(view1).navigate(R.id.action_nav_v04_1_to_nav_v04));
        return view;
    }

    //2º MÉTODOS REGISTROFB
    private void registrarUsuario(View view) {
        if (!v04_1_condicioneslegales_prs.isChecked() /*|| !cbTerminos.isChecked()*/) {
            Toast.makeText(getActivity(), "Debes aceptar los términos y condiciones", Toast.LENGTH_LONG).show();
            return;
        }

        //Obtenemos el email_prs y la contraseña desde las cajas de texto
        String nombre_prs = v04_1_nombre_prs.getText().toString().trim();
        String apellido1_prs = v04_1_apellido1_prs.getText().toString().trim();
        String apellido2_prs = v04_1_apellido2_prs.getText().toString().trim();
        String email_prs = v04_1_email_prs.getText().toString().trim();
        String contrasenna1_prs = v04_1_contrasenna1_prs.getText().toString().trim();
        String contrasenna2_prs = v04_1_contrasenna2_prs.getText().toString().trim();
        String contrasenna_prs = null;
        String dni_prs = v04_1_dni_prs.getText().toString().trim();
        String movil_prs = v04_1_movil_prs.getText().toString().trim();

        //Verificamos que las cajas de texto no estén vacías
        if (TextUtils.isEmpty(nombre_prs)) {
            Toast.makeText(getActivity(), "Se debe ingresar el nombre_prs", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(apellido1_prs)) {
            Toast.makeText(getActivity(), "Se debe ingresar el apellido1_prs", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(email_prs)) {
            Toast.makeText(getActivity(), "Se debe ingresar el email_prs", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(contrasenna1_prs)) {
            Toast.makeText(getActivity(), "Se debe ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(contrasenna2_prs)) {
            Toast.makeText(getActivity(), "Se debe confirmar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(dni_prs)) {
            Toast.makeText(getActivity(), "Se debe ingresar el DNI", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(movil_prs)) {
            Toast.makeText(getActivity(), "Se debe ingresar un movil_prs", Toast.LENGTH_LONG).show();
            return;
        }

        //Verificamos que las contraseñas coinciden
        if (contrasenna1_prs.equals(contrasenna2_prs)) {
            contrasenna_prs = contrasenna1_prs;
        } else {
            Toast.makeText(getActivity(), "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
            return;
        }

        //creamos el objeto del usuario
        Map<String, Object> user = new HashMap<>();
        user.put("nombre_prs", nombre_prs);
        user.put("apellido1_prs", apellido1_prs);
        user.put("apellido2_prs", apellido2_prs);
        user.put("email_prs", email_prs);
        user.put("dni_prs", dni_prs);
        user.put("movil_prs", movil_prs);
        user.put("id_val_col_prs", usuariotipo);

        fba.createUserWithEmailAndPassword(email_prs, contrasenna_prs)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //comprobando el éxito
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Se ha registrado el usuario: " + nombre_prs + " " + apellido1_prs, Toast.LENGTH_LONG).show();

                            //añadomos el usuario a FirebaseFirestore
                            fbf.collection("persona_prs").document(fba.getUid()).set(user);
                            sesionIniciada = usuariotipo;
                            Navigation.findNavController(view).navigate(R.id.action_nav_v04_1_to_nav_v05, result);
                        } else {
                            Toast.makeText(getActivity(), "Ya existe el usuario con el mismo mail", Toast.LENGTH_LONG).show();
                        }
                        pdg.dismiss();
                    }
                });
        pdg.setMessage("Realizando registro en linea...");
        pdg.show();
    }
}
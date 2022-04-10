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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
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

    //TODO:los campos de xml
    private Button v04_1_aceptar, v04_1_volver;
    private EditText v04_1_nombre_val;
    private EditText v04_1_apellido1_val;
    private EditText v04_1_apellido2_val;
    private EditText v04_1_email_val;
    private EditText v04_1_contrasenna1_val;
    private EditText v04_1_contrasenna2_val;
    private EditText v04_1_dni_val;
    private EditText v04_1_movil_val;
    private Spinner v04_1_spinner_valiente_colaborador;
    private CheckBox v04_1_condicioneslegales_val;

    //TODO:acceso a datos
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    // private DatabaseReference mDatabase; //no necesitamos esa bbdd

    //TODO:entities
    private Context mContext;

    //TODO:servise
    private ProgressDialog progressDialog;

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
        View view = inflater.inflate(R.layout.fragment_v_04_1,
                container, false);

        //2ºREGISTROFB
        //inicializamos el objeto firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        //mDatabase = FirebaseDatabase.getInstance().getReference();  //no necesitamos  esa bbdd
        //Referenciamos los views
        v04_1_nombre_val = view.findViewById(R.id.v04_1_etx_nombre_val);
        v04_1_apellido1_val = view.findViewById(R.id.v04_1_etx_apellido1_val);
        v04_1_apellido2_val = view.findViewById(R.id.v04_1_etx_apellido2_val);
        v04_1_email_val = view.findViewById(R.id.v04_1_etx_email_val);
        v04_1_contrasenna1_val = view.findViewById(R.id.v04_1_etx_contrasenna1_val);
        v04_1_contrasenna2_val = view.findViewById(R.id.v04_1_etx_contrasenna2_val);
        v04_1_dni_val = view.findViewById(R.id.v04_1_etx_dni_val);
        v04_1_movil_val = view.findViewById(R.id.v04_1_etx_movil_val);
        //v04_1_spinner_valiente_colaborador = view.findViewById(R.id.v04_1_spinner_valiente_colaborador);
        v04_1_condicioneslegales_val = view.findViewById(R.id.v04_1_ckb_condicioneslegales_val);
        v04_1_aceptar = view.findViewById(R.id.v04_1_btn_aceptar);
        v04_1_volver = view.findViewById(R.id.v04_1_btn_volver);

        //el spinner para el futuro
        /*String[] arraySpinner = new String[]{
                "Valiente", "Colaborador"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        v04_1_spinner_valiente_colaborador.setAdapter(adapter);
        v04_1_spinner_valiente_colaborador.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
                 v04_1_resultado_spinner_valiente_colaborador = v04_1_spinner_valiente_colaborador.getSelectedItem().toString();
                //Toast.makeText(getActivity(), resultado, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });*/

        progressDialog = new ProgressDialog(mContext);
        v04_1_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //la navegacion se hace en registrarUsuario despues de que todos datos tienen exito
                registrarUsuario(view);
            }
        });

        v04_1_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_v04_1_to_nav_v04);
            }
        });
        return view;
    }


    //2º MÉTODOS REGISTROFB
    private void registrarUsuario(View view) {

        if (!v04_1_condicioneslegales_val.isChecked() /*|| !cbTerminos.isChecked()*/) {
            Toast.makeText(getActivity(), "Debes aceptar los términos y condiciones", Toast.LENGTH_LONG).show();
            return;
        }

        //Obtenemos el email_val y la contraseña desde las cajas de texto
        String nombre_val = v04_1_nombre_val.getText().toString().trim();
        String apellido1_val = v04_1_apellido1_val.getText().toString().trim();
        String apellido2_val = v04_1_apellido2_val.getText().toString().trim();
        String email_val = v04_1_email_val.getText().toString().trim();
        String contrasenna1_val = v04_1_contrasenna1_val.getText().toString().trim();
        String contrasenna2_val = v04_1_contrasenna2_val.getText().toString().trim();
        String contrasenna_val = null;
        String dni_val = v04_1_dni_val.getText().toString().trim();
        String movil_val = v04_1_movil_val.getText().toString().trim();

        //Verificamos que las cajas de texto no estén vacías
        if (TextUtils.isEmpty(nombre_val)) {
            Toast.makeText(getActivity(), "Se debe ingresar el nombre_val", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(apellido1_val)) {
            Toast.makeText(getActivity(), "Se debe ingresar el apellido1_val", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(email_val)) {
            Toast.makeText(getActivity(), "Se debe ingresar el email_val", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(contrasenna1_val)) {
            Toast.makeText(getActivity(), "Se debe ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(contrasenna2_val)) {
            Toast.makeText(getActivity(), "Se debe confirmar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(dni_val)) {
            Toast.makeText(getActivity(), "Se debe ingresar el DNI", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(movil_val)) {
            Toast.makeText(getActivity(), "Se debe ingresar un movil_val", Toast.LENGTH_LONG).show();
            return;
        }

        //Verificamos que las contraseñas coinciden
        if (contrasenna1_val.equals(contrasenna2_val)) {
            contrasenna_val = contrasenna1_val;
        } else {
            Toast.makeText(getActivity(), "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
            return;
        }

        //creamos el objeto del usuario
        Map<String, Object> user = new HashMap<>();
        user.put("nombre_val", nombre_val);
        user.put("apellido1_val", apellido1_val);
        user.put("apellido2_val", apellido2_val);
        user.put("email_val", email_val);
        user.put("dni_val", dni_val);
        user.put("movil_val", movil_val);
        user.put("usuariotipo_val","Valiente");

        firebaseAuth.createUserWithEmailAndPassword(email_val, contrasenna_val)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //comprobando el éxito
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Se ha registrado el usuario: " + nombre_val + " " + apellido1_val, Toast.LENGTH_LONG).show();
                            //añadomos el usuario a FirebaseFirestore
                            firebaseFirestore.collection("valiente_val").document(firebaseAuth.getUid()).set(user);
                            Navigation.findNavController(view).navigate(R.id.action_nav_v04_1_to_nav_v05);
                        } else {

                            Toast.makeText(getActivity(), "Ya existe el usuario con el mismo mail", Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
        progressDialog.setMessage("Realizando registro en linea...");
        progressDialog.show();

        //es un ejemplo como trabajar con DatabaseReference //no necesitamos  esa bbdd
        /*mDatabase.child("16").child("data").child("30").child("nombre_val").setValue(nombre_val);
        mDatabase.child("16").child("data").child("30").child("apellido1_va").setValue(apellido1_val);
        mDatabase.child("16").child("data").child("30").child("email_val").setValue(email_val);
        mDatabase.child("16").child("data").child("30").child("dni_val").setValue(dni_val);
       // mDatabase.child("16").child("data").add/*child("30").child("movil_val").setValue(movil_val)*/
        ;

    }


}
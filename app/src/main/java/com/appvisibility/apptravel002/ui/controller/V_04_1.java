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
    private Button v04_1_boton_aceptar, v04_1_boton_volver;
    //2ºREGISTROFB defining view objects
    private EditText v04_1_editTextTextPersonName_nombre;
    private EditText v04_1_editTextTextPersonName_apellido;
    private EditText v04_1_editTextTextPersonName_apellido2;
    private EditText v04_1_editTextTextPersonName_email;
    private EditText v04_1_editTextTextPersonName_password1;
    private EditText v04_1_editTextTextPersonName_password2;
    private EditText v04_1_editTextTextPersonName_dni;
    private EditText v04_1_editTextTextPersonName_telefono;
    private Spinner v04_1_spinner_valiente_colaborador;
    private CheckBox v04_1_checkBox_aceptacion_condiciones;
    private ProgressDialog progressDialog;
    private Context mContext;
    //Declaramos de los bbdd
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    // private DatabaseReference mDatabase; //no necesitamos esa bbdd


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
        v04_1_editTextTextPersonName_nombre = view.findViewById(R.id.v04_1_editTextTextPersonName_nombre);
        v04_1_editTextTextPersonName_apellido = view.findViewById(R.id.v04_1_editTextTextPersonName_apellido);
        v04_1_editTextTextPersonName_apellido2 = view.findViewById(R.id.v04_1_editTextTextPersonName_apellido2);
        v04_1_editTextTextPersonName_email = view.findViewById(R.id.v04_1_editTextTextPersonName_email);
        v04_1_editTextTextPersonName_password1 = view.findViewById(R.id.v04_1_editTextTextPersonName_password1);
        v04_1_editTextTextPersonName_password2 = view.findViewById(R.id.v04_1_editTextTextPersonName_password2);
        v04_1_editTextTextPersonName_dni = view.findViewById(R.id.v04_1_editTextTextPersonName_dni);
        v04_1_editTextTextPersonName_telefono = view.findViewById(R.id.v04_1_editTextTextPersonName_telefono);
        //v04_1_spinner_valiente_colaborador = view.findViewById(R.id.v04_1_spinner_valiente_colaborador);
        v04_1_checkBox_aceptacion_condiciones = view.findViewById(R.id.v04_1_checkBox_aceptacion_condiciones);
        v04_1_boton_aceptar = view.findViewById(R.id.v04_1_boton_aceptar);
        v04_1_boton_volver = view.findViewById(R.id.v04_1_boton_volver);

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
        v04_1_boton_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //la navegacion se hace en registrarUsuario despues de que todos datos tienen exito
                registrarUsuario(view);
            }
        });

        v04_1_boton_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_v04_1_to_nav_v04);
            }
        });
        return view;
    }


    //2º MÉTODOS REGISTROFB
    private void registrarUsuario(View view) {

        if (!v04_1_checkBox_aceptacion_condiciones.isChecked() /*|| !cbTerminos.isChecked()*/) {
            Toast.makeText(getActivity(), "Debes aceptar los términos y condiciones", Toast.LENGTH_LONG).show();
            return;
        }

        //Obtenemos el email y la contraseña desde las cajas de texto
        String nombre = v04_1_editTextTextPersonName_nombre.getText().toString().trim();
        String apeido = v04_1_editTextTextPersonName_apellido.getText().toString().trim();
        String apeido2 = v04_1_editTextTextPersonName_apellido2.getText().toString().trim();
        String email = v04_1_editTextTextPersonName_email.getText().toString().trim();
        String password1 = v04_1_editTextTextPersonName_password1.getText().toString().trim();
        String password2 = v04_1_editTextTextPersonName_password2.getText().toString().trim();
        String passwordTrue = null;
        String dni = v04_1_editTextTextPersonName_dni.getText().toString().trim();
        String telefono = v04_1_editTextTextPersonName_telefono.getText().toString().trim();

        //Verificamos que las cajas de texto no estén vacías
        if (TextUtils.isEmpty(nombre)) {
            Toast.makeText(getActivity(), "Se debe ingresar el nombre", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(apeido)) {
            Toast.makeText(getActivity(), "Se debe ingresar el apeido", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getActivity(), "Se debe ingresar el email", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password1)) {
            Toast.makeText(getActivity(), "Se debe ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password2)) {
            Toast.makeText(getActivity(), "Se debe confirmar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(dni)) {
            Toast.makeText(getActivity(), "Se debe ingresar el DNI", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(telefono)) {
            Toast.makeText(getActivity(), "Se debe ingresar un telefono", Toast.LENGTH_LONG).show();
            return;
        }

        //Verificamos que las contraseñas coinciden
        if (password1.equals(password2)) {
            passwordTrue = password1;
        } else {
            Toast.makeText(getActivity(), "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
            return;
        }

        //creamos el objeto del usuario
        Map<String, Object> user = new HashMap<>();
        user.put("nombre_val", nombre);
        user.put("apellido1_va", apeido);
        user.put("apellido2_va", apeido2);
        user.put("email_val", email);
        user.put("dni_val", dni);
        user.put("movil_val", telefono);
        user.put("usuariotipo_val","Valiente");

        firebaseAuth.createUserWithEmailAndPassword(email, passwordTrue)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //comprobando el éxito
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Se ha registrado el usuario: " + nombre + " " + apeido, Toast.LENGTH_LONG).show();
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
        /*mDatabase.child("16").child("data").child("30").child("nombre_val").setValue(nombre);
        mDatabase.child("16").child("data").child("30").child("apellido1_va").setValue(apeido);
        mDatabase.child("16").child("data").child("30").child("email_val").setValue(email);
        mDatabase.child("16").child("data").child("30").child("dni_val").setValue(dni);
       // mDatabase.child("16").child("data").add/*child("30").child("movil_val").setValue(telefono)*/
        ;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
}
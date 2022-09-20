package com.appvisibility.apptravel002.ui.controller;

import static com.appvisibility.apptravel002.MainActivity_val.sesionIniciada;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.appvisibility.apptravel002.MainActivity_adm;
import com.appvisibility.apptravel002.MainActivity_col;
import com.appvisibility.apptravel002.MainActivity_val;
import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.entities.Evento_eve;
import com.appvisibility.apptravel002.ui.entities.Persona_prs;
import com.appvisibility.apptravel002.ui.service.Identidad_idtService;
import com.appvisibility.apptravel002.ui.service.Persona_prsService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link V_04#newInstance} factory method to
 * create an instance of this fragment.
 */
public class V_04 extends Fragment {

    // Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Campos de xml
    private Button v04_iniciar_sesion, v04_registrarse;
    private EditText v04_email_prs;
    private EditText v04_contrasenna_prs;
    private EditText v04_contrasennaConfirmar_prs;
    public Switch v04_recordarcontrasenna_prs;
    public TextView v04_olvidaste_contrasenna;
    private CheckBox v04_condicioneslegales_prs;

    // Acceso a datos
    FirebaseAuth fba = FirebaseAuth.getInstance();
    FirebaseFirestore fbf = FirebaseFirestore.getInstance();
    private String uid;

    // Entities
    private Persona_prs personaUser = new Persona_prs();
    private List<Persona_prs> personas = new ArrayList<>();
    private String contrasenna_prs;
    private String contrasennaConfirmar_prs;
    private boolean recordarcontrasenna_prs;
//    private int id_prs = 50;
    private int id_prs = -1;
    private String email_prs;
    private boolean	condicioneslegales_prs;
    private int acceso = 0;
    private Evento_eve eventoEnProceso;
    private Bundle bundleEvento;
    private Bundle bundlePersonaUser;
    private boolean validacion;
    private boolean datosActualizados;
    private boolean inicioActivo;
    private Identidad_idtService identidadService = new Identidad_idtService();
    private Context mContext;

    // Service
    private ProgressDialog pdg;
    private int usuariotipo_prs;

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
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
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

//        bundleEvento.putSerializable("eventoParaIdentidad", eventoEnProceso);
        bundleEvento.putSerializable("eventoParaV_05", eventoEnProceso);

        //Referenciamos los views
        v04_email_prs = view.findViewById(R.id.v04_etx_email_prs);
        v04_contrasenna_prs = view.findViewById(R.id.v04_etx_contrasenna_prs);
        v04_contrasennaConfirmar_prs = view.findViewById(R.id.v04_etx_contrasennaConfirmar_prs);
        v04_recordarcontrasenna_prs = view.findViewById(R.id.v04_swc_recordarcontrasenna_prs);
        v04_olvidaste_contrasenna = view.findViewById(R.id.v04_txv_olvidaste_contrasenna);
        v04_condicioneslegales_prs = view.findViewById(R.id.v04_ckb_condicioneslegales_prs);
        v04_registrarse = view.findViewById(R.id.v04_btn_registrarse);

        v04_contrasennaConfirmar_prs.setVisibility(View.GONE);
        v04_registrarse.setVisibility(View.GONE);
        v04_olvidaste_contrasenna.setVisibility(View.GONE);
        v04_condicioneslegales_prs.setVisibility(View.GONE);

        pdg = new ProgressDialog(mContext);

//        recuperarAuthenticationActual(view);

// Se presenta la pantalla de Iniciar Sesión limpia
        mostrarAuthenticationEnProceso(view);
        prepararValidacion(view);

        // Botones
        v04_iniciar_sesion = view.findViewById(R.id.v04_btn_iniciar_sesion);
        v04_iniciar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prepararValidacion(view);
                autentificar(view);
            }
        });

        v04_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prepararValidacion(view);
                secuenciaDeValidacion(view);
                if (!validacion){
                    Toast.makeText(view.getContext(), "No se ha podido completar el registro, datos incorrectos", Toast.LENGTH_LONG).show();
                } else {
                    validacion = false;
                    volcarNuevoUsuario(view);
                    autentificar(view);
                }
            }
        });

        // Acceso a datos
//        FirebaseFirestore fbf = FirebaseFirestore.getInstance();
/*
        List<Persona_prs> lista = new ArrayList<>();
        Task<QuerySnapshot> task1 = fbf.collection("persona_prs").orderBy("id_prs", Query.Direction.DESCENDING).get();
        final int[] numero = new int[1];
        task1.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Persona_prs enProceso;
                lista.clear();
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot qds : task.getResult()) {
                        Log.d(TAG, qds.getId() + " => " + qds.getData());
                        enProceso = (Persona_prs) qds.toObject(Persona_prs.class);
                        lista.add(enProceso);
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
                numero[0] = lista.get(0).getId_prs()+1;
            }
        });
        id_prs = numero[0];
*/
//        id_prs = Persona_prsService.personaUserR2();
        return view;
    }//Fin de constructor

    private Boolean autentificar(View view) {
//        inicioActivo = false;
        validacion = false;
        //Verificamos que las cajas de texto no estén vacías
        if (TextUtils.isEmpty(email_prs) && TextUtils.isEmpty(contrasenna_prs)) {
            opcionesRegistro();
            validacion = false;
        } else {
            v04_email_prs.setBackgroundResource(0);
            validacion = true;
        }
        if (TextUtils.isEmpty(email_prs)) {
            v04_email_prs.setBackgroundResource(R.drawable.etx_alerta_validacion);
            validacion = false;
            Toast.makeText(getActivity(), "Se debe ingresar un eMail", Toast.LENGTH_SHORT).show();
        } else {
            v04_email_prs.setBackgroundResource(0);
            validacion = true;
        }
        if (TextUtils.isEmpty(contrasenna_prs)) {
            v04_contrasenna_prs.setBackgroundResource(R.drawable.etx_alerta_validacion);
            validacion = false;
            Toast.makeText(getContext(), "La contraseña debe contener al menos 8 caracteres incluyendo números, mayúsculas, minúsculas y caracteres especiales", Toast.LENGTH_LONG).show();
        } else {
            v04_contrasenna_prs.setBackgroundResource(0);
            validacion = !validacion ? false : true;
        }

        if (!validacion){
            inicioActivo = false;
        } else {
            validacion = false;
            Task task1 = fba.signInWithEmailAndPassword(email_prs, contrasenna_prs);
            task1.addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task1) {
                    if (task1.isSuccessful()) {
                        //obtenemos la Uid del registro de la bbdd FirebaseAuth
                        FirebaseUser user = fba.getCurrentUser();
                        uid = user.getUid();

                        recuperarAuthenticationActual(view);
                    } else {
                        opcionesRegistro();
                    }
                    pdg.dismiss();
                }
            });
            pdg.setMessage("Identificación en linea...");
            pdg.show();
        }
        return inicioActivo;
    }

    public void recuperarAuthenticationActual(View view) {
        //buscamos en FirebaseFirestore el documento con esa Uid
        Task task = fbf.collection("persona_prs").document(uid).get();
        task.addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = (DocumentSnapshot) task.getResult();
                    if (document.exists()) {
                        //recuperamos los datos del usuario
                        personaUser = document.toObject(Persona_prs.class);
                        personaUser.setRecordarcontrasenna_prs((recordarcontrasenna_prs)? true : false);
                        Persona_prsService.personaUserU(datosActualizados, personaUser);
                        id_prs = personaUser.getId_prs();
                        Toast.makeText(getContext(), "Bienvenido", Toast.LENGTH_LONG).show();
                        inicioActivo = true;
                        bundlePersonaUser = new Bundle();
                        bundlePersonaUser.putSerializable("User", personaUser);

                        if (id_prs == -1) {
                            Identidad_idtService.newInstance(null, bundlePersonaUser);
                        } else {
                            V_05.newInstance(null, bundlePersonaUser);
                        }

                        iniciarSesion(view);

                    } else {
                        Toast.makeText(getContext(), "No se ha podido iniciar la sesión", Toast.LENGTH_LONG).show();
                        inicioActivo = false;
                    }
                } else {
                    Toast.makeText(mContext, "Get failed with", Toast.LENGTH_SHORT).show();
                    inicioActivo = false;
                }
            }
        });
    }
/*
    public void volcarAuthentication(View view) {
//        personaUser.setId_prs(id_prs);
        personaUser.setRecordarcontrasenna_prs((recordarcontrasenna_prs)? true : false);
//        personaUser.setEmail_prs(email_prs);
//        personaUser.setCondicioneslegales_prs((condicioneslegales_prs)? true : false);
    }
*/

    public void volcarNuevoUsuario(View view) {
        //creamos el objeto del usuario
        Map<String, Object> user = new HashMap<>();
        user.put("id_prs", id_prs);
        user.put("recordarcontrasenna_prs", recordarcontrasenna_prs);
        user.put("email_prs", email_prs);
        user.put("usuariotipo_prs", usuariotipo_prs);
        user.put("condicioneslegales_prs", condicioneslegales_prs);

        Task task = fba.createUserWithEmailAndPassword(email_prs, contrasenna_prs);
        task.addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    //comprobando el éxito
                    if (task.isSuccessful()) {
                        Toast.makeText(getActivity(), "Se ha registrado el usuario", Toast.LENGTH_SHORT).show();

                        //añadimos el usuario a FirebaseFirestore
                        fbf.collection("persona_prs").document(fba.getUid()).set(user);
                        sesionIniciada = usuariotipo_prs;
                    } else {
                        Toast.makeText(getActivity(), "El usuario no se ha podido registrar", Toast.LENGTH_SHORT).show();
                        mostrarAuthenticationEnProceso(view);
                        prepararValidacion(view);
                        v04_condicioneslegales_prs.setChecked(false);
                    }
                    pdg.dismiss();
                }
            });
        pdg.setMessage("Registro en linea...");
        pdg.show();
    }

    public void iniciarSesion(View view) {
        //si es valiente
        if (personaUser.getUsuariotipo_prs() == getResources().getInteger(R.integer.rol_valiente)) {
            sesionIniciada = personaUser.getUsuariotipo_prs();
            // si valiente entra por el menu (sin elegir el evento) navegamos a 01
            if (eventoEnProceso == null) {
                startActivity(new Intent(getActivity(), MainActivity_val.class).putExtra("accion_val", getResources().getInteger(R.integer.accion_a_v01)));
                //si valiente entra con el bundle desde 03, tiene que ir a 05 con bundle
            } else {

                Toast.makeText(getContext(), "Iniciada Sesión Valiente", Toast.LENGTH_LONG).show();
//                startActivity(new Intent(getActivity(), MainActivity_val.class).putExtra("accion_val", eventoEnProceso.getId_eve()).putExtras(bundleEvento));
                startActivity(new Intent(getActivity(), MainActivity_val.class)
                    .putExtra("accion_val", eventoEnProceso.getId_eve())
                    .putExtra("id_prs", personaUser.getId_prs())
                    .putExtras(bundleEvento));
            }
        }

        //si es colaborador
        if (personaUser.getUsuariotipo_prs() == getResources().getInteger(R.integer.rol_colaborador)) {
            sesionIniciada = personaUser.getUsuariotipo_prs();
            // si colaborador entra por el menu (sin elegir el evento) navegamos a 01
            if (eventoEnProceso == null) {
                startActivity(new Intent(getActivity(), MainActivity_col.class).putExtra("accion_col", getResources().getInteger(R.integer.accion_a_v01)));
                //si colaborador entra con el bundle desde 03, tiene que ir a 05 con bundle
            } else {
                Toast.makeText(getContext(), "Iniciada Sesión Colaborador", Toast.LENGTH_LONG).show();
//                startActivity(new Intent(getActivity(), MainActivity_col.class).putExtra("accion_col", eventoEnProceso.getId_eve()).putExtras(bundleEvento));
                startActivity(new Intent(getActivity(), MainActivity_col.class)
                    .putExtra("accion_col", eventoEnProceso.getId_eve())
                    .putExtra("id_prs", personaUser.getId_prs())
                    .putExtras(bundleEvento));
            }
        }

        //si es administrador
        if (personaUser.getUsuariotipo_prs() == getResources().getInteger(R.integer.rol_administrador)) {
            sesionIniciada = personaUser.getUsuariotipo_prs();
            // si administrador entra por el menu (sin elegir el evento) navegamos a 01
            if (eventoEnProceso == null) {
                startActivity(new Intent(getActivity(), MainActivity_adm.class).putExtra("adm", getResources().getInteger(R.integer.accion_a_v01)));
                //si administrador entra con el bundle desde 03, tiene que ir a 05 con bundle
            } else {
                Toast.makeText(getContext(), "Iniciada Sesión Administrador", Toast.LENGTH_LONG).show();
//                startActivity(new Intent(getActivity(), MainActivity_adm.class).putExtra("accion_adm", eventoEnProceso.getId_eve()).putExtras(bundleEvento));
                startActivity(new Intent(getActivity(), MainActivity_adm.class)
                    .putExtra("accion_adm", eventoEnProceso.getId_eve())
                    .putExtra("id_prs", personaUser.getId_prs())
                    .putExtras(bundleEvento));
            }
        }
    }

    // Obtenemos los datos desde las cajas de texto
    public void prepararValidacion(View view) {
        contrasenna_prs = v04_contrasenna_prs.getText().toString().trim();
        recordarcontrasenna_prs = (v04_recordarcontrasenna_prs.isChecked())? true : false;
        email_prs = v04_email_prs.getText().toString().trim();
        usuariotipo_prs = 1;
        condicioneslegales_prs = (v04_condicioneslegales_prs.isChecked())? true : false;
    }

    public void mostrarAuthenticationEnProceso (View view){
        v04_contrasenna_prs.setText("");
        v04_contrasennaConfirmar_prs.setText("");
        v04_recordarcontrasenna_prs.setChecked((recordarcontrasenna_prs)? true : false);
        v04_email_prs.setText("");
        v04_condicioneslegales_prs.setChecked((condicioneslegales_prs)? true : false);
    }

    public void secuenciaDeValidacion (View view) {
// https://www.android--code.com/2015/08/android-edittext-border-color_20.html
// https://stackoverflow.com/questions/34075131/how-to-set-a-button-border-color-programmatically-in-android
// You can create a layout for this. in your code
        if (!validarContrasenna()) {
            v04_contrasenna_prs.setBackgroundResource(R.drawable.etx_alerta_validacion);
            validacion = false;
            Toast.makeText(view.getContext(), "La contraseña debe contener al menos 8 caracteres incluyendo números, mayúsculas, minúsculas y caracteres especiales", Toast.LENGTH_LONG).show();
        } else {
            v04_contrasenna_prs.setBackgroundResource(0);
            validacion = true;
        }
        contrasennaConfirmar_prs = (String.valueOf(v04_contrasennaConfirmar_prs.getText()) == null)? "" : String.valueOf(v04_contrasennaConfirmar_prs.getText());
        if (!contrasenna_prs.equals(contrasennaConfirmar_prs) ||
                !validarContrasenna()) {
            v04_contrasenna_prs.setBackgroundResource(R.drawable.etx_alerta_validacion);
            v04_contrasennaConfirmar_prs.setBackgroundResource(R.drawable.etx_alerta_validacion);
            Toast.makeText(view.getContext(), "La contraseña no está bien confirmada", Toast.LENGTH_LONG).show();
            validacion = false;
        } else {
            v04_contrasenna_prs.setBackgroundResource(0);
            v04_contrasennaConfirmar_prs.setBackgroundResource(0);
            validacion = !validacion? false: true;
        }
        if (!validarEmail()) {
            v04_email_prs.setBackgroundResource(R.drawable.etx_alerta_validacion);
            validacion = false;
        } else {
            v04_email_prs.setBackgroundResource(0);
            validacion = !validacion? false: true;
        }
        if (!v04_condicioneslegales_prs.isChecked()) {
            v04_condicioneslegales_prs.setBackgroundResource(R.drawable.etx_alerta_validacion);
            validacion = false;
        } else {
            validacion = !validacion? false: true;
        }
    }

    public Boolean validarContrasenna() {
        if (TextUtils.isEmpty(contrasenna_prs) ||
                contrasenna_prs == null ||
                !contrasenna_prs.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$")) {
            return false;
        } else {
            return true;
        }
    }

//  http://w3.unpocodetodo.info/utiles/regex-ejemplos.php?type=email
    public Boolean validarEmail() {
        if (TextUtils.isEmpty(email_prs) ||
            email_prs == null ||
            !email_prs.matches("^[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$")) {
            return false;
        } else {
            return true;
        }
    }

    public void opcionesRegistro() {
        v04_email_prs.setBackgroundResource(R.drawable.etx_alerta_validacion);
        v04_contrasenna_prs.setBackgroundResource(R.drawable.etx_alerta_validacion);
        v04_contrasennaConfirmar_prs.setVisibility(View.VISIBLE);
        v04_contrasennaConfirmar_prs.setBackgroundResource(R.drawable.etx_alerta_validacion);
        v04_recordarcontrasenna_prs.setChecked((recordarcontrasenna_prs)? true : false);
        v04_olvidaste_contrasenna.setVisibility(View.GONE);
        v04_registrarse.setVisibility(View.VISIBLE);
        v04_condicioneslegales_prs.setVisibility(View.VISIBLE);
        v04_iniciar_sesion.setVisibility(View.GONE);
    }
}

package com.appvisibility.apptravel002.ui.controller;

import static com.appvisibility.apptravel002.MainActivity_val.sesionIniciada;
import static com.appvisibility.apptravel002.ui.service.Inscribir_eveprsService.*;
import static com.appvisibility.apptravel002.ui.service.Inscribir_eveprsService.plazaLibreRenunciarRU;
import static com.appvisibility.apptravel002.ui.service.Persona_prsService.*;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.appvisibility.apptravel002.MainActivity_val;
import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.controller.modal.V_03_2_modal;
import com.appvisibility.apptravel002.ui.entities.Evento_eve;
import com.appvisibility.apptravel002.ui.entities.Inscribir_eveprs;
import com.appvisibility.apptravel002.ui.entities.Persona_prs;
import com.appvisibility.apptravel002.ui.service.Alimentacion_aliService;
import com.appvisibility.apptravel002.ui.service.Contacto_cntService;
import com.appvisibility.apptravel002.ui.service.v03_00_act_Adapter;
import com.appvisibility.apptravel002.ui.service.v03_00_prs_Adapter;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link V_05#newInstance} factory method to
 * create an instance of this fragment.
 */
public class V_05 extends Fragment {

    // Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Rename and change types of parameters
    private String mParam1;
    private Bundle mBundlePersonaUser;

    // Campos de xml
    private Button v05_adelante, v05_atras;
    private TextView v05_titulo_eve;
    private Switch v05_cocheSiNo;
    private Button v05_ofrecePlazaLibre;
    private Spinner v05_ofertaPlazaLibre;
    private Button v05_solicitaPlazaLibre;
    private TextView v05_asignadaPlazaLibre;
    public static TextView v05_alimentacion_prs;
    private Button v05_indicaContacto1;
    public static Button v05_indicaContacto2;
    public static Button v05_indicaContacto3;
    public static TextView v05_2_muestraContacto1Elegido;
    public static TextView v05_2_muestraContacto2Elegido;
    public static TextView v05_2_muestraContacto3Elegido;
    public static TextView v05_recomendacionContacto;

    // Acceso a datos
    FirebaseFirestore fbf = FirebaseFirestore.getInstance();

    // Entities
    private Evento_eve eventoEnProceso;
    private List<Persona_prs> personaOferentes = new ArrayList<>();
    private List<Inscribir_eveprs> inscritos = V_03.inscritos;
    private List<Inscribir_eveprs> inscritoOferentes = new ArrayList<>();
    public static Map<Integer, Persona_prs> map_IdIns_Prs = v03_00_prs_Adapter.map_IdIns_Prs;
    private Inscribir_eveprs inscritoOferente;
    private Inscribir_eveprs inscritoOferenteActual;
    private Inscribir_eveprs inscritoEnProceso;
    private int plazasOcupadas = -1;
    private Persona_prs personaUser;
    private int id_prs_enProceso;
    private Boolean solicitudPlazaLibreRealizada = V_03_2_modal.solicitudRealizada;
    private int plazaslibres_eveprs;
    private Alimentacion_aliService alimentacion = new Alimentacion_aliService();

    private List alimentaciones_prs = new ArrayList();
    private String alimentacion_prsTitulo;
    private String alimentacion_prsActual;
    private String alimentacionEnProceso = "";

//    private Contacto_cnt contacto = new Contacto_cnt();
    private Contacto_cntService contactoService = new Contacto_cntService();
    private String nombre_cnt;
    private String apellido1_cnt;
    private Boolean datosActualizados;

    private Context mContext;

    // Service
    private v03_00_act_Adapter v03_adapter_act;
    private v03_00_prs_Adapter v03_adapter_prs;

    public V_05() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param bundlePersonaUser Parameter 2.
     * @return A new instance of fragment V_05.
     */
    // Rename and change types and number of parameters
    public static V_05 newInstance(String param1, Bundle bundlePersonaUser) {
        V_05 fragment = new V_05();
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_05, container, false);

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

        //Recuperamos el Evento
        Bundle bundleEvento = getArguments();
        eventoEnProceso = (Evento_eve) bundleEvento.getSerializable("eventoParaV_05");

        bundleEvento.putSerializable("eventoParaV_03", eventoEnProceso);
        bundleEvento.putSerializable("eventoParaV_02", eventoEnProceso);

        v05_titulo_eve = view.findViewById(R.id.v05_txv_titulo_eve);
        v05_cocheSiNo = view.findViewById(R.id.v05_swc_cocheSiNo);
        v05_ofrecePlazaLibre = view.findViewById(R.id.v05_btn_ofrecePlazaLibre);
        v05_ofertaPlazaLibre = view.findViewById(R.id.v05_spn_ofertaPlazaLibre);
        v05_solicitaPlazaLibre = view.findViewById(R.id.v05_btn_solicitaPlazaLibre);
        v05_asignadaPlazaLibre = view.findViewById(R.id.v05_txv_asignadaPlazaLibre);
        v05_alimentacion_prs = view.findViewById(R.id.v05_txv_alimentacion_prs);
        v05_indicaContacto1 = view.findViewById(R.id.v05_btn_indicaContacto1);
        v05_indicaContacto2 = view.findViewById(R.id.v05_btn_indicaContacto2);
        v05_indicaContacto3 = view.findViewById(R.id.v05_btn_indicaContacto3);
        v05_2_muestraContacto1Elegido = view.findViewById(R.id.v05_txv_muestraContacto1Elegido);
        v05_2_muestraContacto2Elegido = view.findViewById(R.id.v05_txv_muestraContacto2Elegido);
        v05_2_muestraContacto3Elegido = view.findViewById(R.id.v05_txv_muestraContacto3Elegido);
        v05_recomendacionContacto = view.findViewById(R.id.v05_txv_recomendacionContacto);

        v05_titulo_eve.setText(eventoEnProceso.getTitulo_eve());

        inscritoEnProceso();
        if (inscritoEnProceso.getPlazaslibres_eveprs() < 0) {
// Hace visible las opciones NO llevo coche
            v05_cocheSiNo.setChecked(false);
            v05_ofrecePlazaLibre.setVisibility(View.GONE);
            v05_ofertaPlazaLibre.setVisibility(View.GONE);
            v05_solicitaPlazaLibre.setVisibility(View.VISIBLE);
            v05_asignadaPlazaLibre.setVisibility(View.VISIBLE);
        } else if (inscritoEnProceso.getPlazaslibres_eveprs() == 0 &&
          inscritoEnProceso.getId_tpr() != inscritoEnProceso.getId_eveprs()){
// Hace visible las opciones SI llevo coche y NO ofrezco Plazas Libres
            v05_cocheSiNo.setChecked(true);
            v05_ofrecePlazaLibre.setVisibility(View.GONE);
            v05_ofertaPlazaLibre.setVisibility(View.GONE);
            v05_solicitaPlazaLibre.setVisibility(View.VISIBLE);
            v05_asignadaPlazaLibre.setVisibility(View.VISIBLE);
        } else {
// Hace visible las opciones SI llevo coche y SI ofrezco Plazas Libres
            v05_cocheSiNo.setChecked(true);
            v05_ofrecePlazaLibre.setVisibility(View.VISIBLE);
            v05_ofertaPlazaLibre.setVisibility(View.VISIBLE);
            v05_solicitaPlazaLibre.setVisibility(View.GONE);
            v05_asignadaPlazaLibre.setVisibility(View.GONE);
            ofrecerPlazasLibres();
        }

        v05_indicaContacto2.setVisibility(View.GONE);
        v05_2_muestraContacto2Elegido.setVisibility(View.GONE);
        v05_indicaContacto3.setVisibility(View.GONE);
        v05_2_muestraContacto3Elegido.setVisibility(View.GONE);

// https://stackoverflow.com/questions/45712826/how-to-draw-a-horizontal-line-between-two-linearlayouts-in-android
// Linea de separación
// Switch para mostrar opción Ofrezco Plazas Libres / Necesito Plazas Libres
        v05_cocheSiNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (solicitudPlazaLibreRealizada) {
                        solicitudPlazaLibreRealizada = plazaLibreRenunciarRU(solicitudPlazaLibreRealizada, personaUser, inscritoOferenteActual);
                        v05_asignadaPlazaLibre.setText("");
                    } else {
                        plazaslibres_eveprs = 0;
                    }
                    v05_ofrecePlazaLibre.setVisibility(View.VISIBLE);
                    v05_ofertaPlazaLibre.setVisibility(View.VISIBLE);
                    v05_solicitaPlazaLibre.setVisibility(View.GONE);
                    v05_asignadaPlazaLibre.setVisibility(View.GONE);
                } else {
                    if (inscritoEnProceso().getPlazaslibres_eveprs() >= 0){
                        plazasOcupadas();
                        if (plazasOcupadas > 0){
                            Toast.makeText(getActivity(), "Hay personas con plaza asignada en tu coche", Toast.LENGTH_LONG).show();
                        } else {
                            plazaslibres_eveprs = -1;
                            solicitudPlazaLibreRealizada = plazaLibreAnularRU(solicitudPlazaLibreRealizada, personaUser, plazaslibres_eveprs);
                        }
                    }
                    v05_ofrecePlazaLibre.setVisibility(View.GONE);
                    v05_ofertaPlazaLibre.setVisibility(View.GONE);
                    v05_solicitaPlazaLibre.setVisibility(View.VISIBLE);
                    v05_asignadaPlazaLibre.setVisibility(View.VISIBLE);
                }
            }
        });

// Spinner para indicar el número de Plazas Libres que se ofrecen
        v05_ofrecePlazaLibre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (solicitudPlazaLibreRealizada) {
                    solicitudPlazaLibreRealizada = plazaLibreRenunciarRU(solicitudPlazaLibreRealizada, personaUser, inscritoOferenteActual);
                }
            ofrecerPlazasLibres();
            }
        });

// Si hay una Plaza Libre solicitada se muestra
        for (Inscribir_eveprs ins: inscritos){
            if(personaUser.getId_prs() == ins.getId_prs()
                && ins.getId_tpr() != ins.getId_eveprs()){
                int id_tpr = ins.getId_tpr();
                for (Inscribir_eveprs ins2: inscritos){
                    if(id_tpr == ins2.getId_eveprs()){
                        v05_asignadaPlazaLibre.setText(map_IdIns_Prs.get(ins2.getId_prs()).getApodo_prs());
                        solicitudPlazaLibreRealizada = true;
                        inscritoOferenteActual = ins2;
                    }
                }
            }
        }

/*
        v05_foto_eve = view.findViewById(R.id.v05_imv_foto_eve);
        v05_foto_eve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_v05_to_nav_v05_1, bundleEvento);
            }
        });
*/

        v05_solicitaPlazaLibre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (plazasOcupadas > 0){
                    Toast.makeText(getActivity(), "No puedes solicitar plaza al mismo tiempo que tienes personas con plaza asignada en tu coche", Toast.LENGTH_LONG).show();
                } else {
                    solicitarPlazaLibre(view);
                }
            }
        });

        Alimentacion_aliService.newInstance(null, mBundlePersonaUser);
        alimentacion.alimentacionR();
        v05_alimentacion_prs.setText(Alimentacion_aliService.alimentacionSpanned);
        v05_alimentacion_prs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alimentacion.indicarRestriccionesAlimentarias(view);
            }
        });

//        alimentacionR();

        v05_indicaContacto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permitirAcceso(view);
                Contacto_cntService.newInstance("Contacto1", null);
                contactoService.onCreateView(inflater, container, savedInstanceState);
//                mostrarBotonContacto();
           }
        });

        v05_2_muestraContacto1Elegido.setText(personaUser.getContacto1Nombre_prs() + " " + personaUser.getContacto1Apellido1_prs());

        v05_indicaContacto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permitirAcceso(view);
                Contacto_cntService.newInstance("Contacto2", null);
                contactoService.onCreateView(inflater, container, savedInstanceState);
//                mostrarBotonContacto();
            }
        });

        v05_2_muestraContacto1Elegido.setText(personaUser.getContacto1Nombre_prs() + " " + personaUser.getContacto1Apellido1_prs());

        v05_indicaContacto3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permitirAcceso(view);
                Contacto_cntService.newInstance("Contacto3", null);
                contactoService.onCreateView(inflater, container, savedInstanceState);
            }
        });


        mostrarBotonContacto();
        v05_2_muestraContacto1Elegido.setText(personaUser.getContacto1Nombre_prs() + " " + personaUser.getContacto1Apellido1_prs());
        nombre_cnt = (personaUser.getContacto2Nombre_prs() == null)? "" : personaUser.getContacto2Nombre_prs();
        apellido1_cnt = (personaUser.getContacto2Apellido1_prs() == null)? "" : personaUser.getContacto2Apellido1_prs();
        v05_2_muestraContacto2Elegido.setText(nombre_cnt + " " + apellido1_cnt);
        nombre_cnt = (personaUser.getContacto3Nombre_prs() == null)? "" : personaUser.getContacto3Nombre_prs();
        apellido1_cnt = (personaUser.getContacto3Apellido1_prs() == null)? "" : personaUser.getContacto3Apellido1_prs();
        v05_2_muestraContacto3Elegido.setText(nombre_cnt + " " + apellido1_cnt);

        // Botones
        v05_adelante = view.findViewById(R.id.v05_btn_adelante);
        v05_adelante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datosActualizados = personaUserU(datosActualizados, personaUser);
                if (datosActualizados) {
                    Toast.makeText(getActivity(), "Se han actualizado tus datos", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "No se han podido actualizar tus datos", Toast.LENGTH_LONG).show();
                }
                Navigation.findNavController(view).navigate(R.id.action_nav_v05_to_nav_v06);
            }
        });

        v05_atras = view.findViewById(R.id.v05_btn_atras);
        v05_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "No se han actualizado tus datos", Toast.LENGTH_LONG).show();
                Navigation.findNavController(view).navigate(R.id.action_nav_v05_to_nav_v03, bundleEvento);
            }
        });
        return view;
    }//Fin de constructor

    //Identificamos al inscrito en proceso de Plazas Libres en coche
    private Inscribir_eveprs inscritoEnProceso (){
        for (Inscribir_eveprs ins : inscritos) {
            if (ins.getId_prs() == personaUser.getId_prs()){
                inscritoEnProceso = ins;
            }
        }
        return inscritoEnProceso;
    }

    private void plazasOcupadas (){
        plazasOcupadas = -1;
        // Contamos cuantas plazas ocupadas tiene el inscritoOferente
        if (inscritoEnProceso.getId_tpr() == inscritoEnProceso.getId_eveprs()){
            for (Inscribir_eveprs ins: inscritos){
                if (ins.getId_tpr() == inscritoEnProceso.getId_tpr()){
                    plazasOcupadas++;
                }
            }
        } else {
            plazasOcupadas = 0;
        }
    }

    private void ofrecerPlazasLibres() {
        inscritoEnProceso = inscritoEnProceso();
        plazasOcupadas();

        String [] ofertaPlazasLibres = getResources().getStringArray(R.array.ofertaPlazasLibres);
/*
        ArrayList<String> ofertaPlazasLibres = new ArrayList<>();
        ofertaPlazasLibres.add("VIAJO SOLO");
        ofertaPlazasLibres.add("1 Plaza");
        ofertaPlazasLibres.add("2 Plazas");
        ofertaPlazasLibres.add("3 Plazas");
        ofertaPlazasLibres.add("4 Plazas");
        ofertaPlazasLibres.add("5 Plazas");
        ofertaPlazasLibres.add("6 Plazas");
*/
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, ofertaPlazasLibres);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        v05_ofertaPlazaLibre.setAdapter(arrayAdapter);
// https://stackoverflow.com/questions/37481951/how-to-get-and-set-selected-item-from-spinner-using-sharedpreferences
// Recupera spinner anterior
//        v05_ofertaPlazaLibre.getSelectedItemPosition();
        int indexOfPreviousSelection = inscritoEnProceso.getPlazaslibres_eveprs();
        v05_ofertaPlazaLibre.setSelection(indexOfPreviousSelection);
// https://stackoverflow.com/questions/10331854/how-to-get-spinner-selected-item-value-to-string
        final String[] dato = new String[1];
        dato[0] = ofertaPlazasLibres[plazasOcupadas];
//        dato[0] = ofertaPlazasLibres.get(plazasOcupadas);
        v05_ofertaPlazaLibre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                plazasOcupadas();

                if (position < plazasOcupadas){
                    position = plazasOcupadas;
                }
                v05_ofertaPlazaLibre.setSelection(position);
                Object item = parent.getItemAtPosition(position);
                if (item.toString().equals("VIAJO SOLO")){
                    dato[0] = "0";
                } else {
                    dato[0] = item.toString().substring(0, item.toString().indexOf(" "));
                }
                if (Integer.parseInt(dato[0]) > plazasOcupadas){
                    plazaLibreOfrecerRU(solicitudPlazaLibreRealizada, personaUser, (Integer.parseInt(dato[0])-plazasOcupadas));
                } else {
                    plazaLibreOfrecerRU(solicitudPlazaLibreRealizada, personaUser, 0);
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void solicitarPlazaLibre(View view) {
        String [] descripcionPlazasLibres;

        //Identificamos a los inscritos que ofrecen Plazas Libres en el coche
        inscritoOferentes = inscritos.stream()
            .filter(p->p.getPlazaslibres_eveprs() > 0)
            .collect(Collectors.toList());

        //Identificamos al inscritoSolicitante de Plazas Libres en coche
        inscritoEnProceso = inscritoEnProceso();
        //Recuperamos los datos de las personas que ofrecen Plazas Libres en el coche
        for(Inscribir_eveprs ins: inscritoOferentes){
            if (ins.getPlazaslibres_eveprs() > 0
                    && ins.getId_prs() != personaUser.getId_prs()) {
                id_prs_enProceso = ins.getId_prs();
                personaOferentes.add(map_IdIns_Prs.get(id_prs_enProceso));
            }
            if (ins.getId_prs() == personaUser.getId_prs()){
                v05_asignadaPlazaLibre.setText(map_IdIns_Prs.get(ins.getId_prs()).getApodo_prs());
            }
        }

        //Organizamos el contenido del AlertDialog modal Selección Simple
        descripcionPlazasLibres = new String[personaOferentes.size()];
        for (int i=0; i<personaOferentes.size(); i++){
            for (int j=0; j<inscritoOferentes.size(); j++){
                if (inscritoOferentes.get(j).getId_prs() == personaOferentes.get(i).getId_prs()) {
                    descripcionPlazasLibres[i] = personaOferentes.get(i).getApodo_prs()
                        + " ("
                        + inscritoOferentes.get(j).getPlazaslibres_eveprs()
                        + ") ";
                }
            }
        }

        if (sesionIniciada < view.getResources().getInteger(R.integer.rol_transportecolectivo)) {
// https://stackoverflow.com/questions/42436012/how-to-put-the-arraylist-into-bundle
// Valiente: Enviamos a la ventana modal el listado de personasEnCoche correspondiente al id_prs (Persona) seleccionado
//            Bundle bundleInscritoOferentes = new Bundle();
//            bundleInscritoOferentes.putParcelableArrayList("inscritoParaV_05_2", (ArrayList<? extends Parcelable>) inscritoOferentes);
            AlertDialog.Builder modalSimplePlazasLibres = new AlertDialog.Builder(mContext);
            modalSimplePlazasLibres
                .setIcon(R.drawable.ico_coche_azul)
                .setTitle("COCHES CON PLAZAS LIBRES")
                .setItems(descripcionPlazasLibres, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        String datoseleccionado = descripcionPlazasLibres[item];
//                        ((AppCompatActivity) mContext).findViewById(R.id.v05_txv_asignadaPlazaLibre);
                        inscritoOferente = inscritoOferentes.get(item);
                        if (inscritoEnProceso.getId_tpr() == inscritoOferente.getId_tpr()){
                            Toast.makeText(getActivity(), "Ya tienes una plaza solicitada", Toast.LENGTH_LONG).show();
                        } else {
                            if (solicitudPlazaLibreRealizada){
                                solicitudPlazaLibreRealizada = plazaLibreRenunciarRU(solicitudPlazaLibreRealizada, personaUser, inscritoOferenteActual);
                                // Debido al uso de onComplete tenemos que alterar algunos valores de los arrayList con el fin de que puedan entrar en los métodos
                                inscritoEnProceso.setPlazaslibres_eveprs(inscritoEnProceso.getPlazaslibres_eveprs() - 1);
                                inscritoEnProceso.setId_tpr(inscritoEnProceso.getId_eveprs());
                                solicitudPlazaLibreRealizada = plazaLibreSolicitarRU(solicitudPlazaLibreRealizada, personaUser, inscritoOferente);
                                if (solicitudPlazaLibreRealizada){
                                    inscritoOferenteActual = inscritoOferente;
                                    v05_asignadaPlazaLibre.setText(datoseleccionado.substring(0, datoseleccionado.indexOf("(")));
                                }
                            } else {
                                solicitudPlazaLibreRealizada = plazaLibreSolicitarRU(solicitudPlazaLibreRealizada, personaUser, inscritoOferente);
                                if (solicitudPlazaLibreRealizada){
                                    inscritoOferenteActual = inscritoOferente;
                                    v05_asignadaPlazaLibre.setText(datoseleccionado.substring(0, datoseleccionado.indexOf("(")));
                                }
                            }
                        }
                    }
                })
                .create()
                .show();
        }
        personaOferentes.clear();
    }

// Solicita autorización de acceso a la agenda de contactos del móvil
    private void permitirAcceso (View view){
        if (ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) mContext, Manifest.permission.READ_CONTACTS)) {
            } else {
                ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.READ_CONTACTS}, 1);}}
    }

    public void mostrarBotonContacto() {
        if (!personaUser.getContacto1Cargo_prs().equalsIgnoreCase("") &&
                !personaUser.getContacto1Cargo_prs().equalsIgnoreCase("")) {
            v05_indicaContacto2.setVisibility(View.VISIBLE);
            v05_2_muestraContacto2Elegido.setVisibility(View.VISIBLE);
        }

        if (!personaUser.getContacto2Cargo_prs().equalsIgnoreCase("") &&
                !personaUser.getContacto2Cargo_prs().equalsIgnoreCase("")) {
            v05_indicaContacto3.setVisibility(View.VISIBLE);
            v05_2_muestraContacto3Elegido.setVisibility(View.VISIBLE);
        }
    }

}
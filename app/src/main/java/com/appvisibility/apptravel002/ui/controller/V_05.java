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
import android.text.Html;
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
import com.appvisibility.apptravel002.ui.entities.Contacto_cnt;
import com.appvisibility.apptravel002.ui.entities.Evento_eve;
import com.appvisibility.apptravel002.ui.entities.Inscribir_eveprs;
import com.appvisibility.apptravel002.ui.entities.Persona_prs;
import com.appvisibility.apptravel002.ui.service.Contacto_cntService;
import com.appvisibility.apptravel002.ui.service.v03_00_act_Adapter;
import com.appvisibility.apptravel002.ui.service.v03_00_prs_Adapter;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
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
    private String mParam2;

    // Campos de xml
    private Button v05_adelante, v05_atras;
    private TextView v05_titulo_eve;
    private Switch v05_cocheSiNo;
    private Button v05_ofrecePlazaLibre;
    private Spinner v05_ofertaPlazaLibre;
    private Button v05_solicitaPlazaLibre;
    private TextView v05_asignadaPlazaLibre;
    private TextView v05_alimentacion_prs;
    private Button v05_indicaContacto1;
    public static TextView v05_2_muestraContactoElegido;

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
    private List alimentaciones_prs = new ArrayList();
    private String alimentacion_prsTitulo;
    private String alimentacion_prsActual;
    private String alimentacion_prsNueva = "";
//    private Contacto_cnt contacto = new Contacto_cnt();
    private Contacto_cntService contactoService = new Contacto_cntService();
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
     * @param param2 Parameter 2.
     * @return A new instance of fragment V_05.
     */
    // Rename and change types and number of parameters
    public static V_05 newInstance(String param1, String param2) {
        V_05 fragment = new V_05();
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_05, container, false);

        //https://stackoverflow.com/questions/12739909/send-data-from-activity-to-fragment-in-android
        /* So, to pass data from the MotherActivity to such a Fragment you will need to create private Strings/Bundles above the onCreate of your Mother activity - which you can fill with the data you want to pass to the fragments, and pass them on via a method created after the onCreate (here called getMyData()).*/
        //Recuperamos los datos del Usuario activo
        MainActivity_val activity = (MainActivity_val) getActivity();
        Bundle bundlePersonaUser = activity.getUser();
        personaUser = (Persona_prs) bundlePersonaUser.getSerializable("User");

        //Cargamos el Evento
        Bundle bundleEvento = getArguments();
        eventoEnProceso = (Evento_eve) bundleEvento.getSerializable("eventoParaV_05");

        bundleEvento.putSerializable("eventoParaV_05_1", eventoEnProceso);

        v05_titulo_eve = view.findViewById(R.id.v05_txv_titulo_eve);
        v05_cocheSiNo = view.findViewById(R.id.v05_swc_cocheSiNo);
        v05_cocheSiNo.setChecked(true);
        v05_ofrecePlazaLibre = view.findViewById(R.id.v05_btn_ofrecePlazaLibre);
        v05_ofertaPlazaLibre = view.findViewById(R.id.v05_spn_ofertaPlazaLibre);
        v05_solicitaPlazaLibre = view.findViewById(R.id.v05_btn_solicitaPlazaLibre);
        v05_solicitaPlazaLibre.setVisibility(View.GONE);
        v05_asignadaPlazaLibre = view.findViewById(R.id.v05_txv_asignadaPlazaLibre);
        v05_asignadaPlazaLibre.setVisibility(View.GONE);
        v05_alimentacion_prs = view.findViewById(R.id.v05_txv_alimentacion_prs);
        v05_indicaContacto1 = view.findViewById(R.id.v05_btn_indicaContacto1);
        v05_2_muestraContactoElegido = view.findViewById(R.id.v05_txv_muestraContactoElegido);

        v05_titulo_eve.setText(eventoEnProceso.getTitulo_eve());

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
                    v05_ofertaPlazaLibre.setSelection(0);
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

        v05_alimentacion_prs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indicarRestriccionesAlimentarias(view);
            }
        });

        alimentacionR();

        v05_indicaContacto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permitirAcceso(view);
                contactoService.onCreateView(inflater, container, savedInstanceState);
           }
        });

        // Botones
        v05_adelante = view.findViewById(R.id.v05_btn_confirmar);
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
                Navigation.findNavController(view).navigate(R.id.action_nav_v05_to_nav_v02);
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

        if (sesionIniciada >= view.getResources().getInteger(R.integer.rol_transportecolectivo)) {
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

// Convierte un String separado por guiones en un ArrayList
    private String alimentacionR(){
        alimentacion_prsTitulo = "Restricciones Alimentarias: ";
        alimentacion_prsNueva = "";
// Si el usuario tiene restricciones Alimentarias se muestran
        alimentacion_prsActual = personaUser.getAlimentacion_prs();
// https://stackoverflow.com/questions/7488643/how-to-convert-comma-separated-string-to-list
        alimentaciones_prs = Arrays.asList(alimentacion_prsActual.split("\\s*\\s*-\\s*"));
        for (Object ali: alimentaciones_prs){
            alimentacion_prsNueva += ali.toString() + " - ";
            v05_alimentacion_prs.setText(Html.fromHtml("<u>"+ alimentacion_prsTitulo + "</u><br>" + alimentacion_prsNueva),
                    TextView.BufferType.SPANNABLE);
        }
        return alimentacion_prsNueva;
    }

    private void indicarRestriccionesAlimentarias(View view) {
        String [] restriccionesAlimentarias = getResources().getStringArray(R.array.restriccionesAlimentarias);
        boolean[] opcionesRestriccionesAlimentarias =new boolean[restriccionesAlimentarias.length];

        //Organizamos el contenido del AlertDialog modal Selección Múltiple
        alimentacion_prsNueva = alimentacionR();
        alimentacion_prsNueva = "";
        for (int i=0; i < restriccionesAlimentarias.length; i++){
            restriccionesAlimentarias[i] = restriccionesAlimentarias[i];
            if (alimentaciones_prs.contains(restriccionesAlimentarias[i])){
                opcionesRestriccionesAlimentarias[i] = true;
                alimentacion_prsNueva += restriccionesAlimentarias[i] + " - ";
                v05_alimentacion_prs.setText(Html.fromHtml("<u>"+ alimentacion_prsTitulo + "</u><br>" + alimentacion_prsNueva),
                        TextView.BufferType.SPANNABLE);
            } else {
                opcionesRestriccionesAlimentarias[i] = false;
            };
        }

        if (sesionIniciada >= view.getResources().getInteger(R.integer.rol_valiente)) {
            AlertDialog.Builder modalMultipleRestriccionesAlimentarias = new AlertDialog.Builder(mContext);
            modalMultipleRestriccionesAlimentarias
                .setIcon(R.drawable.ico_foodmealplaterestaurant_azul)
                .setTitle("RESTRICCIONES ALIMENTARIAS")
                .setMultiChoiceItems(restriccionesAlimentarias, opcionesRestriccionesAlimentarias, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item, boolean isChecked) {
                        alimentacion_prsNueva  = "";
                        alimentacion_prsActual = "";
                        for (int i = 0; i < opcionesRestriccionesAlimentarias.length; i++) {
                            if (opcionesRestriccionesAlimentarias[i] == true) {
                                alimentacion_prsNueva += restriccionesAlimentarias[i] + " - ";
                                alimentacion_prsActual += restriccionesAlimentarias[i] + " - ";
                            }
                        }
                    }
                })
                .setPositiveButton("Confirmar", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        personaUser.setAlimentacion_prs(alimentacion_prsActual);
                        v05_alimentacion_prs.setText(Html.fromHtml("<u>"+ alimentacion_prsTitulo + "</u><br>" + alimentacion_prsNueva),
                                TextView.BufferType.SPANNABLE);
//                        ((AppCompatActivity) mContext).findViewById(R.id.v05_txv_muestraRestriccionesAlimentarias);
                    }
                })
                .setNegativeButton("Volver", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
        }
    }

    private void permitirAcceso (View view){
        if (ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) mContext, Manifest.permission.READ_CONTACTS)) {
            } else {
                ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.READ_CONTACTS}, 1);}}
    }

}
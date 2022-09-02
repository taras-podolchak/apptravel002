package com.appvisibility.apptravel002.ui.controller.modal;

import static com.appvisibility.apptravel002.ui.service.Inscribir_eveprsService.plazaLibreRenunciarRU;
import static com.appvisibility.apptravel002.ui.service.Inscribir_eveprsService.plazaLibreSolicitarRU;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.controller.V_03;
import com.appvisibility.apptravel002.ui.entities.Evento_eve;
import com.appvisibility.apptravel002.ui.entities.Inscribir_eveprs;
import com.appvisibility.apptravel002.ui.entities.Persona_prs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link V_03_2_modal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class V_03_2_modal extends DialogFragment {

    // Campos de xml
    private Button v03_2_adelante, v03_2_atras;
    private TextView v03_2_titulo;
    private TextView v03_2_listaPlazas;

    // Entities
    private Evento_eve eventoEnProceso;
    private List<Persona_prs> personasEnCoche = new ArrayList<>();
    private Inscribir_eveprs inscritoOferente;
    private String informeCoche = "";
    private List<Inscribir_eveprs> inscritos = V_03.inscritos;
    private List<Inscribir_eveprs> inscritosEnCoche  = new ArrayList<>();
    private Persona_prs personaUser;
    public static Persona_prs personaOferente;
    public static Boolean solicitudRealizada = false;

    // Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Rename and change types of parameters
    private String mParam1;
    private Bundle mBundlePersonaUser;

    public V_03_2_modal() {
        // Required empty public constructor    
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param bundlePersonaUser Parameter 2.
     * @return A new instance of fragment V_03_2_modal.
     */
    // Rename and change types and number of parameters
    public static V_03_2_modal newInstance(String param1, Bundle bundlePersonaUser) {
        V_03_2_modal fragment = new V_03_2_modal();
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_03_2_modal, container, false);

//https://stackoverflow.com/questions/12739909/send-data-from-activity-to-fragment-in-android
/* So, to pass data from the MotherActivity to such a Fragment you will need to create private Strings/Bundles above the onCreate of your Mother activity - which you can fill with the data you want to pass to the fragments, and pass them on via a method created after the onCreate (here called getMyData()).*/
//Recuperamos los datos del Usuario activo
/*
        MainActivity_val activity = (MainActivity_val) getActivity();
        Bundle bundlePersonaUser = activity.getUser();
        personaUser = (Persona_prs) bundlePersonaUser.getSerializable("User");
*/
        personaUser = (Persona_prs) mBundlePersonaUser.getSerializable("User");

        Bundle bundlePersonasEnCoche = getArguments();
        //Recuperamos las Plazas del Coche
        personasEnCoche = bundlePersonasEnCoche.getParcelableArrayList("personaParaV_03_2");

// Listamos el subconjunto de Inscritos que viajan en el id_tpr (coche) en proceso
// https://stackoverflow.com/questions/36246998/stream-filter-of-1-list-based-on-another-list
        inscritosEnCoche.clear();
        inscritosEnCoche = inscritos.stream()
            .filter(prs -> personasEnCoche.stream()
            .map(Persona_prs::getId_prs)
            .anyMatch(ins -> ins.equals(prs.getId_prs())))
            .collect(Collectors.toList());

        //Identificamos al inscrito que ofrecen Plazas Libres en el coche
        inscritoOferente = inscritosEnCoche.stream()
            .filter(p->p.getId_eveprs() == (p.getId_tpr()))
            .max((p1,p2) -> p1.getPlazaslibres_eveprs() > p2.getPlazaslibres_eveprs()?1:-1)
            .orElse(null);

        //Recuperamos los datos del inscrito que ofrecen Plazas Libres en el coche
        for(Persona_prs prs: personasEnCoche) {
            if (prs.getId_prs() == inscritoOferente.getId_prs()
                && inscritoOferente.getId_tpr() == inscritoOferente.getId_eveprs()) {
                personaOferente = prs;
            }
        }

        //Recuperamos los inscritos en coche
        inscritosEnCoche = inscritos.stream()
            .filter(ins2 -> ins2.getId_tpr() == inscritoOferente.getId_tpr())
            .sorted((e1,e2) -> e2.getPlazaslibres_eveprs() - e1.getPlazaslibres_eveprs())
            .collect(Collectors.toList());

        Bundle bundleEvento = getArguments();
        //Recuperamos el Evento
//        eventoEnProceso = (Evento_eve) bundleEvento.getSerializable("eventoParaV_03_2");
        eventoEnProceso = V_03.eventoEnProceso;
        bundleEvento.putSerializable("eventoParaV_03", eventoEnProceso);

        v03_2_titulo = view.findViewById(R.id.v03_2_txv_titulo);
        v03_2_titulo.setText("COCHE DE " + personaOferente.getApodo_prs().toUpperCase());

        v03_2_listaPlazas = view.findViewById(R.id.v05_2_txv_apodo_prs);

        for (Persona_prs prs: personasEnCoche){
            for (Inscribir_eveprs ins: inscritosEnCoche){
                if (prs.getId_prs() == ins.getId_prs() && ins.getPlazaslibres_eveprs() > 0){
                    informeCoche += prs.getApodo_prs() + "\n";
                }
            }
        }
        for (Persona_prs prs: personasEnCoche){
            for (Inscribir_eveprs ins: inscritosEnCoche){
                if (prs.getId_prs() == ins.getId_prs() && ins.getPlazaslibres_eveprs() <= 0){
                    informeCoche += prs.getApodo_prs() + "\n";
                }
            }
        }
        for (int i=0; i < inscritoOferente.getPlazaslibres_eveprs(); i++){
            informeCoche += "-----" + "\n";
        }

        v03_2_listaPlazas.setText(informeCoche);

        // Botones
        v03_2_adelante = view.findViewById(R.id.v03_2_btn_adelante);
        v03_2_adelante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (solicitudRealizada){
                    Toast.makeText(getActivity(), "La solicitud no reune los requisitos", Toast.LENGTH_LONG).show();
                } else {
                    solicitudRealizada = plazaLibreSolicitarRU(solicitudRealizada, personaUser, inscritoOferente);
                    if (solicitudRealizada) {
                        Toast.makeText(getActivity(), "Solicitud de plaza realizada", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getActivity(), "La solicitud no reune los requisitos", Toast.LENGTH_LONG).show();
                    }
                };
                getDialog().cancel();
            }
        });

        v03_2_atras = view.findViewById(R.id.v03_2_btn_atras);
        v03_2_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solicitudRealizada = true;
                solicitudRealizada = plazaLibreRenunciarRU(solicitudRealizada, personaUser, inscritoOferente);
                if (!solicitudRealizada) {
                    Toast.makeText(getActivity(), "Renuncia de plaza realizada", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "La renuncia no reune los requisitos", Toast.LENGTH_LONG).show();
                };
                getDialog().cancel();
            }
        });
        personasEnCoche.clear();
        return view;
    }//Fin de constructor

}
package com.appvisibility.apptravel002.ui.controller;

import static android.content.ContentValues.TAG;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.appvisibility.apptravel002.MainActivity_val;
import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.entities.Evento_eve;
import com.appvisibility.apptravel002.ui.entities.Inscribir_eveprs;
import com.appvisibility.apptravel002.ui.entities.Persona_prs;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
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
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link V_05_2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class V_05_2 extends DialogFragment implements IDAO<Object, Inscribir_eveprs, Object> {

    // Campos de xml
    private Button v05_2_adelante, v05_2_atras;
    private TextView v05_2_titulo;
    private TextView v05_2_listaPlazas;

    // Acceso a datos
    FirebaseFirestore fbf = FirebaseFirestore.getInstance();

    // Entities
    private Evento_eve eventoEnProceso;
    private List<Persona_prs> personasEnCoche = new ArrayList<>();
    private Inscribir_eveprs inscritoOferente;
    private String informeCoche = "";
    private List<Inscribir_eveprs> inscritos = V_03.inscritos;
    private List<Inscribir_eveprs> inscritosEnCoche  = new ArrayList<>();
    private Map<Integer, Persona_prs> map_IdIns_Prs = new HashMap<>();
    private Persona_prs personaUser;
    public static Persona_prs personaOferente;
    public static Boolean solicitudRealizada = true;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public V_05_2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment V_05_2.
     */
    // TODO: Rename and change types and number of parameters
    public static V_05_2 newInstance(String param1, String param2) {
        V_05_2 fragment = new V_05_2();
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_05_2, container, false);

        //https://stackoverflow.com/questions/12739909/send-data-from-activity-to-fragment-in-android
        /* So, to pass data from the MotherActivity to such a Fragment you will need to create private Strings/Bundles above the onCreate of your Mother activity - which you can fill with the data you want to pass to the fragments, and pass them on via a method created after the onCreate (here called getMyData()).*/
        //Recuperamos los datos del Usuario activo
        MainActivity_val activity = (MainActivity_val) getActivity();
        Bundle bundlePersonaUser = activity.getUser();
        personaUser = (Persona_prs) bundlePersonaUser.getSerializable("User");

        Bundle bundlePersonasEnCoche = getArguments();
        //Recuperamos las Plazas del Coche
        personasEnCoche = bundlePersonasEnCoche.getParcelableArrayList("personaParaV_05_2");

//        https://stackoverflow.com/questions/22694884/filter-java-stream-to-1-and-only-1-element
        //Recuperamos los datos de la persona que ofrece el coche
        List<Persona_prs> personasEnCocheFiltrado = personasEnCoche.stream()
                .filter(ins -> inscritos.stream()
                        .map(Inscribir_eveprs::getId_prs)
                        .anyMatch(prs -> prs.equals(ins.getId_prs())))
                .collect(Collectors.toList());
        personaOferente = personasEnCocheFiltrado.get(0);

        //Recuperamos los datos del inscrito que ofrece el coche
        for(Inscribir_eveprs ins: inscritos) {
            if (ins.getId_prs() == personaOferente.getId_prs()) {
                inscritoOferente = ins;
            }
        }

        //Recuperamos los inscritos en coche
        inscritosEnCoche = inscritos.stream()
                .filter(ins2->ins2.getId_tpr()==inscritoOferente.getId_tpr())
                .sorted((e1,e2)->e1.getId_eveprs()-e2.getId_eveprs())
                .collect(Collectors.toList());

        Bundle bundleEvento = getArguments();
        //Recuperamos el Evento
//        eventoEnProceso = (Evento_eve) bundleEvento.getSerializable("eventoParaV_05_2");
        eventoEnProceso = V_03.eventoEnProceso;
        bundleEvento.putSerializable("eventoParaV_03", eventoEnProceso);

        v05_2_titulo = view.findViewById(R.id.v05_2_txv_titulo);
        v05_2_titulo.setText("COCHE DE " + personaOferente.getApodo_prs().toUpperCase());

        v05_2_listaPlazas = view.findViewById(R.id.v05_2_txv_listaPlazas);

        for (Persona_prs prs: personasEnCoche){
            informeCoche += prs.getApodo_prs() + "\n";
        }
        for (int i=0; i < inscritoOferente.getPlazaslibres_eveprs(); i++){
            informeCoche += "-----" + "\n";
        }

        v05_2_listaPlazas.setText(informeCoche);

        // Botones
        v05_2_adelante = view.findViewById(R.id.v05_2_btn_confirmar);
        v05_2_adelante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(Inscribir_eveprs inscritoSolicitante: inscritos) {
//                    if (inscritoSolicitante.getId_prs() == 10 && inscritoSolicitante.getPlazaslibres_eveprs() < 0) {
                    if (inscritoSolicitante.getId_prs() == personaUser.getId_prs() && personaUser.getUsuariotipo_prs() < 1 && personaUser.getUsuariotipo_prs() >= 3 && inscritoSolicitante.getPlazaslibres_eveprs() < 0) {
//https://firebase.google.com/docs/firestore/query-data/queries#java_6
//https://firebase.google.com/docs/firestore/manage-data/add-data
//https://stackoverflow.com/questions/68922621/how-to-update-field-in-the-firebase-firestore-document-using-the-collections
                        /*You cannot query the database and update the documents in a single go. You need to query the collection, get the documents, and right after that perform the update.*/
                        Task<QuerySnapshot> task1 = fbf.collection("inscribir_eveprs").whereEqualTo("id_eveprs", inscritoSolicitante.getId_eveprs()).get();
                        task1.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot qds : task.getResult()) {
                                        Log.d(TAG, qds.getId() + " => " + qds.getData());
                                        DocumentReference docRef = qds.getReference();
                                        docRef.update("plazaslibres_eveprs", inscritoSolicitante.getPlazaslibres_eveprs() + 1);
                                        docRef.update("id_tpr", inscritoOferente.getId_tpr())
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                                                    }
                                                });
                                    }
                                }
                            }
                        });
                    }
                    if (inscritoOferente.getId_prs() != personaUser.getId_prs() && personaUser.getUsuariotipo_prs() < 1 && personaUser.getUsuariotipo_prs() >= 3 && inscritoOferente.getPlazaslibres_eveprs() >= 1) {
//                    if (inscritoOferente.getId_prs() == personaUser.getId_prs() && ins.getPlazaslibres_eveprs() < 0) {
                        Task<QuerySnapshot> task1 = fbf.collection("inscribir_eveprs").whereEqualTo("id_eveprs", inscritoOferente.getId_eveprs()).get();
                        task1.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot qds : task.getResult()) {
                                        Log.d(TAG, qds.getId() + " => " + qds.getData());
                                        DocumentReference docRef = qds.getReference();
                                        docRef.update("plazaslibres_eveprs", inscritoOferente.getPlazaslibres_eveprs() - 1)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                                                        solicitudRealizada = true;
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.w(TAG, "Error writing document", e);
                                                        solicitudRealizada = false;
                                                    }
                                                });
                                    }
                                    if (solicitudRealizada){
                                        Toast.makeText(getActivity(), "Solicitud de plaza realizada", Toast.LENGTH_LONG).show();
//                                        Toast.makeText(getActivity(), "No se ha podido solicitar la plaza", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        });
                    }
                }
                if (!solicitudRealizada){
                    Toast.makeText(getActivity(), "La solicitud no reune los requisitos", Toast.LENGTH_LONG).show();
                }
                informeCoche = "";
                Navigation.findNavController(view).navigate(R.id.action_nav_v05_2_to_nav_v03, bundleEvento);
            }
        });

        v05_2_atras = view.findViewById(R.id.v05_2_btn_volver);
        v05_2_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                informeCoche = "";
                Navigation.findNavController(view).navigate(R.id.action_nav_v05_2_to_nav_v03, bundleEvento);
            }
        });

        return view;
    }//Fin de constructor

    @Override
    public <T> void tabla1ChangeListener(Query query, List<T> lista, Class<T> tipoObjeto, RecyclerView.Adapter miAdapter) {
    }

    @Override
    public <S> void tabla2ChangeListener(Query query, List<S> lista, Class<S> tipoObjeto, RecyclerView.Adapter miAdapter) {
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException error) {
                S enProceso;
                if (error != null) {
                    Log.e("Error en Firestore", error.getMessage());
                    return;
                }
                lista.clear();
                for (QueryDocumentSnapshot qds : snapshots) {
                    enProceso = (S) qds.toObject(tipoObjeto);
                    lista.add(enProceso);
                }
                miAdapter.notifyDataSetChanged();

                Log.d(TAG, "Datos recibidos!");
                Toast.makeText(getActivity(), "Datos recibidos!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public <R> void tabla3ChangeListener(Query query, List<R> lista, Class<R> tipoObjeto, RecyclerView.Adapter miAdapter) {
    }
}
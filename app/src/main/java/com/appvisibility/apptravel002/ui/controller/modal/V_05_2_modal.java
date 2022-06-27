package com.appvisibility.apptravel002.ui.controller.modal;

import static android.content.ContentValues.TAG;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.appvisibility.apptravel002.MainActivity_val;
import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.controller.Interfaces.IDAO;
import com.appvisibility.apptravel002.ui.controller.V_03;
import com.appvisibility.apptravel002.ui.entities.Evento_eve;
import com.appvisibility.apptravel002.ui.entities.Inscribir_eveprs;
import com.appvisibility.apptravel002.ui.entities.Persona_prs;
import com.appvisibility.apptravel002.ui.service.v03_00_prs_Adapter;
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
 * Use the {@link V_05_2_modal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class V_05_2_modal extends DialogFragment implements IDAO<Object, Inscribir_eveprs, Object> {

    // Campos de xml
    private Button v05_2_adelante, v05_2_atras;
    private TextView v05_2_titulo;
    private TextView v05_2_inscritoOferentes;

    // Acceso a datos
    FirebaseFirestore fbf = FirebaseFirestore.getInstance();

    // Entities
    private Evento_eve eventoEnProceso;
    private List<Persona_prs> personaOferentes = new ArrayList<>();
    private Inscribir_eveprs inscritoOferente;
//    private String informeCoche = "";
    private List<Inscribir_eveprs> inscritos = V_03.inscritos;
    private List<Inscribir_eveprs> inscritoOferentes = new ArrayList<>();
    public static Map<Integer, Persona_prs> map_IdIns_Prs = v03_00_prs_Adapter.map_IdIns_Prs;
    private Persona_prs personaUser;
    private Persona_prs personaOferente;
    private int id_prs_enProceso;
    public Boolean solicitudRealizada = false;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public V_05_2_modal() {
        // Required empty public constructor    
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment V_05_2_modal.
     */
    // TODO: Rename and change types and number of parameters
    public static V_05_2_modal newInstance(String param1, String param2) {
        V_05_2_modal fragment = new V_05_2_modal();
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
        View view = inflater.inflate(R.layout.fragment_v_05_2_modal, container, false);

        //https://stackoverflow.com/questions/12739909/send-data-from-activity-to-fragment-in-android
        /* So, to pass data from the MotherActivity to such a Fragment you will need to create private Strings/Bundles above the onCreate of your Mother activity - which you can fill with the data you want to pass to the fragments, and pass them on via a method created after the onCreate (here called getMyData()).*/
        //Recuperamos los datos del Usuario activo
        MainActivity_val activity = (MainActivity_val) getActivity();
        Bundle bundlePersonaUser = activity.getUser();
        personaUser = (Persona_prs) bundlePersonaUser.getSerializable("User");

        Bundle bundleInscritoOferentes = getArguments();
        //Recuperamos las Plazas del Coche
        inscritoOferentes = bundleInscritoOferentes.getParcelableArrayList("inscritoParaV_05_2");

        //Recuperamos los datos de las personas que ofrecen Plazas Libres en el coche
        for(Inscribir_eveprs ins: inscritoOferentes){
            if (ins.getPlazaslibres_eveprs() > 0
                && ins.getId_prs() != personaUser.getId_prs()) {
                id_prs_enProceso = ins.getId_prs();
                personaOferentes.add(map_IdIns_Prs.get(id_prs_enProceso));
            }
        }

        Bundle bundleEvento = getArguments();
        //Recuperamos el Evento
//        eventoEnProceso = (Evento_eve) bundleEvento.getSerializable("eventoParaV_03_2");
        eventoEnProceso = V_03.eventoEnProceso;
        bundleEvento.putSerializable("eventoParaV_03", eventoEnProceso);

        v05_2_titulo = view.findViewById(R.id.v03_2_txv_titulo);
        v05_2_titulo.setText("LISTADO DE COCHES CON PLAZAS LIBRES");

        v05_2_inscritoOferentes = view.findViewById(R.id.v05_2_txv_apodo_prs);
        v05_2_inscritoOferentes.setText(personaOferente.getApodo_prs().toUpperCase());

        // Botones
        v05_2_adelante = view.findViewById(R.id.v03_2_btn_adelante);
        v05_2_adelante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Inscribir_eveprs inscritoSolicitante : inscritos) {
                    if (!solicitudRealizada
                            && (inscritoSolicitante.getId_prs() == personaUser.getId_prs()
                            && personaUser.getUsuariotipo_prs() > 0
                            && personaUser.getUsuariotipo_prs() <= 3
                            && inscritoSolicitante.getPlazaslibres_eveprs() < 0
                            && inscritoOferente.getId_prs() != personaUser.getId_prs()
                            && inscritoOferente.getPlazaslibres_eveprs() >= 1)) {
                        if (!solicitudRealizada) {
//https://firebase.google.com/docs/firestore/query-data/queries#java_6
//https://firebase.google.com/docs/firestore/manage-data/add-data
//https://stackoverflow.com/questions/68922621/how-to-update-field-in-the-firebase-firestore-document-using-the-collections
                            /*You cannot query the database and update the documents in a single go. You need to query the collection, get the documents, and right after that perform the update.*/
                            Task<QuerySnapshot> task1 = fbf.collection("inscribir_eveprs").whereEqualTo("id_eveprs", inscritoSolicitante.getId_eveprs()).get();
                            Task<QuerySnapshot> task2 = fbf.collection("inscribir_eveprs").whereEqualTo("id_eveprs", inscritoOferente.getId_eveprs()).get();
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
                            task2.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Log.w(TAG, "Error writing document", e);
                                                        }
                                                    });
                                        }
                                    }
                                }
                            });
                       }
                        solicitudRealizada = true;
                    }
                }
                if (solicitudRealizada){
                    Toast.makeText(getActivity(), "Solicitud de plaza realizada", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "La solicitud no reune los requisitos", Toast.LENGTH_LONG).show();
                }                                           ;
                solicitudRealizada = false;
                getDialog().cancel();
            }
        });

        v05_2_atras = view.findViewById(R.id.v03_2_btn_atras);
        v05_2_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Inscribir_eveprs inscritoSolicitante : inscritos) {
                    if (!solicitudRealizada
                            && (inscritoSolicitante.getId_prs() == personaUser.getId_prs()
                            && personaUser.getUsuariotipo_prs() > 0
                            && personaUser.getUsuariotipo_prs() <= 3
                            && inscritoSolicitante.getPlazaslibres_eveprs() == 0
                            && inscritoSolicitante.getId_tpr() == inscritoOferente.getId_tpr()
                            && inscritoOferente.getId_prs() != personaUser.getId_prs()
                            && inscritoOferente.getPlazaslibres_eveprs() >= 0)) {
                        if (!solicitudRealizada
                                && inscritoOferente.getId_tpr() == inscritoOferente.getId_eveprs()) {
//https://firebase.google.com/docs/firestore/query-data/queries#java_6
//https://firebase.google.com/docs/firestore/manage-data/add-data
//https://stackoverflow.com/questions/68922621/how-to-update-field-in-the-firebase-firestore-document-using-the-collections
                            /*You cannot query the database and update the documents in a single go. You need to query the collection, get the documents, and right after that perform the update.*/
                            Task<QuerySnapshot> task1 = fbf.collection("inscribir_eveprs").whereEqualTo("id_eveprs", inscritoSolicitante.getId_eveprs()).get();
                            Task<QuerySnapshot> task2 = fbf.collection("inscribir_eveprs").whereEqualTo("id_eveprs", inscritoOferente.getId_eveprs()).get();
                            task1.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot qds : task.getResult()) {
                                            Log.d(TAG, qds.getId() + " => " + qds.getData());
                                            DocumentReference docRef = qds.getReference();
                                            docRef.update("plazaslibres_eveprs", inscritoSolicitante.getPlazaslibres_eveprs() - 1);
                                            docRef.update("id_tpr", inscritoSolicitante.getId_eveprs())
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
                            task2.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot qds : task.getResult()) {
                                            Log.d(TAG, qds.getId() + " => " + qds.getData());
                                            DocumentReference docRef = qds.getReference();
                                            docRef.update("plazaslibres_eveprs", inscritoOferente.getPlazaslibres_eveprs() + 1)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {
                                                            Log.d(TAG, "DocumentSnapshot successfully updated!");
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Log.w(TAG, "Error writing document", e);
                                                        }
                                                    });
                                        }
                                    }
                                }
                            });
                        }
                        solicitudRealizada = true;
                    }
                }
                if (solicitudRealizada){
                    Toast.makeText(getActivity(), "Renuncia de plaza realizada", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "La renuncia no reune los requisitos", Toast.LENGTH_LONG).show();
                }                                           ;
                solicitudRealizada = false;
                getDialog().cancel();
            }
        });
        personaOferentes.clear();
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
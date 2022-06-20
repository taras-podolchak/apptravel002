package com.appvisibility.apptravel002.ui.controller.modal;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.appvisibility.apptravel002.MainActivity_val;
import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.controller.Interfaces.IDAO;
import com.appvisibility.apptravel002.ui.controller.V_03;
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

// https://stackoverflow.com/questions/7977392/android-dialogfragment-vs-dialog
// https://developer.android.com/reference/android/app/DialogFragment.html
public class InformePlazas extends DialogFragment implements IDAO<Object, Inscribir_eveprs, Object> {

    // Campos de xml
//    private Button v05_2_adelante, v05_2_atras;
    private String v05_2_titulo;

    // Acceso a datos
    FirebaseFirestore fbf = FirebaseFirestore.getInstance();

    // Entities
    private Evento_eve eventoEnProceso;
    private List<Persona_prs> personasEnCoche = new ArrayList<>();
    private Inscribir_eveprs inscritoOferente;
    private String informeCoche = "";
    private List<Inscribir_eveprs> inscritos = V_03.inscritos;
    private List<Inscribir_eveprs> inscritosEnCoche  = new ArrayList<>();
//    private Map<Integer, Persona_prs> map_IdIns_Prs = new HashMap<>();
    private Persona_prs personaUser;
    public static Persona_prs personaOferente;
    public static Boolean solicitudRealizada = true;

    public static final String ARG_TITLE = "InformePlazas.Title";
    public static final String ARG_MESSAGE = "InformePlazas.Message";
    public static final String ARG_BOTON_ATRAS = "InformePlazas.Message";
    public static final String ARG_BOTON_ADELANTE = "InformePlazas.Message";

    public InformePlazas() {
    }

    public static DialogFragment newInstance(String title, String message, String botonAtras, String botonAdelante) {
        InformePlazas fragment = new InformePlazas();
        Bundle bundle = new Bundle();
        bundle.putString(InformePlazas.ARG_TITLE, title);
        bundle.putString(InformePlazas.ARG_MESSAGE, message);
        bundle.putString(InformePlazas.ARG_BOTON_ATRAS, botonAtras);
        bundle.putString(InformePlazas.ARG_BOTON_ADELANTE, botonAdelante);
        fragment.setArguments(bundle);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = getArguments();
        String title = args.getString(ARG_TITLE);
        String message = args.getString(ARG_MESSAGE);
        String botonAtras = args.getString(ARG_BOTON_ATRAS);
        String botonAdelante = args.getString(ARG_BOTON_ADELANTE);

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

        v05_2_titulo = "COCHE DE " + personaOferente.getApodo_prs().toUpperCase();

        for (Persona_prs prs: personasEnCoche){
            informeCoche += prs.getApodo_prs() + "\n";
        }
        for (int i=0; i < inscritoOferente.getPlazaslibres_eveprs(); i++){
            informeCoche += "-----" + "\n";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title)
            .setIcon(R.drawable.ico_coche_naranja)
            .setMessage(message)
            .setNegativeButton("Volver", null)
            .setPositiveButton("Solicitar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
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
                }
            });
        return builder.create();
    }// Fin de constructor

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

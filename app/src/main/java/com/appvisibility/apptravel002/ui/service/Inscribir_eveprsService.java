package com.appvisibility.apptravel002.ui.service;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.appvisibility.apptravel002.ui.controller.V_03;
import com.appvisibility.apptravel002.ui.entities.Inscribir_eveprs;
import com.appvisibility.apptravel002.ui.entities.Persona_prs;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class Inscribir_eveprsService {
    private static List<Inscribir_eveprs> inscritos = V_03.inscritos;

    public static Boolean plazaLibreRenunciarRU(Boolean solicitudRealizada, Persona_prs personaUser, Inscribir_eveprs inscritoOferente) {
        // Acceso a datos
        FirebaseFirestore fbf = FirebaseFirestore.getInstance();
        for (Inscribir_eveprs inscritoSolicitante : inscritos) {
            if (solicitudRealizada
                    && (inscritoSolicitante.getId_prs() == personaUser.getId_prs()
                    && personaUser.getUsuariotipo_prs() > 0
                    && personaUser.getUsuariotipo_prs() <= 3
                    && inscritoSolicitante.getPlazaslibres_eveprs() == 0
                    && inscritoSolicitante.getId_tpr() == inscritoOferente.getId_tpr()
                    && inscritoOferente.getId_prs() != personaUser.getId_prs()
                    && inscritoOferente.getPlazaslibres_eveprs() >= 0)) {
                if (solicitudRealizada
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
                                    // La siguiente linea renueva los datos del inscritoOferente para que el método pueda ser utilizado por varias clases
                                    Inscribir_eveprs inscritoSolicitante = qds.toObject(Inscribir_eveprs.class);
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
                                    // La siguiente linea renueva los datos del inscritoOferente para que el método pueda ser utilizado por varias clases
                                    Inscribir_eveprs inscritoOferente = qds.toObject(Inscribir_eveprs.class);
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
                solicitudRealizada = false;
            }
        }
        return solicitudRealizada;
    }

    public static Boolean plazaLibreSolicitarRU(Boolean solicitudRealizada, Persona_prs personaUser, Inscribir_eveprs inscritoOferente) {
        // Acceso a datos
        FirebaseFirestore fbf = FirebaseFirestore.getInstance();
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
                                    // La siguiente linea renueva los datos del inscritoOferente para que el método pueda ser utilizado por varias clases
//                                    Inscribir_eveprs inscritoSolicitante = qds.toObject(Inscribir_eveprs.class);
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
                                    // La siguiente linea renueva los datos del inscritoOferente para que el método pueda ser utilizado por varias clases
                                    Inscribir_eveprs inscritoOferente = qds.toObject(Inscribir_eveprs.class);
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
        return solicitudRealizada;
    }

    public static Boolean plazaLibreOfrecerRU(Boolean solicitudRealizada, Persona_prs personaUser, int plazaslibres_eveprs) {
        // Acceso a datos
        FirebaseFirestore fbf = FirebaseFirestore.getInstance();
        for (Inscribir_eveprs ins : inscritos) {
            if (!solicitudRealizada
                    && (ins.getId_prs() == personaUser.getId_prs()
                    && personaUser.getUsuariotipo_prs() > 0
                    && personaUser.getUsuariotipo_prs() <= 3
                    && plazaslibres_eveprs >= 0)) {
                if (!solicitudRealizada) {
//https://firebase.google.com/docs/firestore/query-data/queries#java_6
//https://firebase.google.com/docs/firestore/manage-data/add-data
//https://stackoverflow.com/questions/68922621/how-to-update-field-in-the-firebase-firestore-document-using-the-collections
                    /*You cannot query the database and update the documents in a single go. You need to query the collection, get the documents, and right after that perform the update.*/
                    Task<QuerySnapshot> task1 = fbf.collection("inscribir_eveprs").whereEqualTo("id_eveprs", ins.getId_eveprs()).get();
                    task1.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot qds : task.getResult()) {
                                    Log.d(TAG, qds.getId() + " => " + qds.getData());
                                    DocumentReference docRef = qds.getReference();
                                    // La siguiente linea renueva los datos del inscritoOferente para que el método pueda ser utilizado por varias clases
                                    Inscribir_eveprs ins = qds.toObject(Inscribir_eveprs.class);
//                                    int plazaslibres_eveprs = ins.getPlazaslibres_eveprs();
                                    docRef.update("plazaslibres_eveprs", plazaslibres_eveprs);
                                    docRef.update("id_tpr", ins.getId_eveprs())
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
                solicitudRealizada = false;
            }
        }
        return solicitudRealizada;
    }

    public static Boolean plazaLibreAnularRU(Boolean solicitudRealizada, Persona_prs personaUser, int plazaslibres_eveprs) {
        // Acceso a datos
        FirebaseFirestore fbf = FirebaseFirestore.getInstance();
        for (Inscribir_eveprs ins : inscritos) {
            if (!solicitudRealizada
                    && (ins.getId_prs() == personaUser.getId_prs()
                    && personaUser.getUsuariotipo_prs() > 0
                    && personaUser.getUsuariotipo_prs() <= 3
                    && plazaslibres_eveprs < 0)) {
                if (!solicitudRealizada) {
//https://firebase.google.com/docs/firestore/query-data/queries#java_6
//https://firebase.google.com/docs/firestore/manage-data/add-data
//https://stackoverflow.com/questions/68922621/how-to-update-field-in-the-firebase-firestore-document-using-the-collections
                    /*You cannot query the database and update the documents in a single go. You need to query the collection, get the documents, and right after that perform the update.*/
                    Task<QuerySnapshot> task1 = fbf.collection("inscribir_eveprs").whereEqualTo("id_eveprs", ins.getId_eveprs()).get();
                    task1.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot qds : task.getResult()) {
                                    Log.d(TAG, qds.getId() + " => " + qds.getData());
                                    DocumentReference docRef = qds.getReference();
                                    // La siguiente linea renueva los datos del inscritoOferente para que el método pueda ser utilizado por varias clases
                                    Inscribir_eveprs ins = qds.toObject(Inscribir_eveprs.class);
//                                    int plazaslibres_eveprs = ins.getPlazaslibres_eveprs();
                                    docRef.update("plazaslibres_eveprs", plazaslibres_eveprs);
                                    docRef.update("id_tpr", ins.getId_eveprs())
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
                solicitudRealizada = false;
            }
        }
        return solicitudRealizada;
    }

}

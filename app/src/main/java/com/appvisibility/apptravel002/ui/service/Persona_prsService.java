package com.appvisibility.apptravel002.ui.service;

import static android.content.ContentValues.TAG;

import android.util.Log;

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

public class Persona_prsService {
    private static List<Persona_prs> personas = V_03.personas;

// TODO: BORRAR ESTE ES INNECESARIO AL TENER UN BUNDLE personaUser en V_05
// ***************************
    public static Persona_prs personaUserR(Persona_prs personaUser) {
        // Acceso a datos
        FirebaseFirestore fbf = FirebaseFirestore.getInstance();
        final Persona_prs[] persona = new Persona_prs[1];
//https://firebase.google.com/docs/firestore/query-data/queries#java_6
//https://firebase.google.com/docs/firestore/manage-data/add-data
//https://stackoverflow.com/questions/68922621/how-to-update-field-in-the-firebase-firestore-document-using-the-collections
        /*You cannot query the database and update the documents in a single go. You need to query the collection, get the documents, and right after that perform the update.*/
        Task<QuerySnapshot> task1 = fbf.collection("persona_prs").whereEqualTo("id_prs", personaUser.getId_prs()).get();
        task1.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot qds : task.getResult()) {
                        Log.d(TAG, qds.getId() + " => " + qds.getData());
                        persona[0] = qds.toObject(Persona_prs.class);
                    }
                }
            }
        });
        return persona[0];
    }
// TODO: BORRAR ESTE METODO ES INNECESARIO AL TENER UN BUNDLE personaUser en V_05
// ***************************


    public static Boolean personaUserU(Boolean datosActualizados, Persona_prs personaUser) {
        // Acceso a datos
        FirebaseFirestore fbf = FirebaseFirestore.getInstance();
//https://firebase.google.com/docs/firestore/query-data/queries#java_6
//https://firebase.google.com/docs/firestore/manage-data/add-data
//https://stackoverflow.com/questions/68922621/how-to-update-field-in-the-firebase-firestore-document-using-the-collections
        /*You cannot query the database and update the documents in a single go. You need to query the collection, get the documents, and right after that perform the update.*/
        Task<QuerySnapshot> task1 = fbf.collection("persona_prs").whereEqualTo("id_prs", personaUser.getId_prs()).get();
        task1.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot qds : task.getResult()) {
                        Log.d(TAG, qds.getId() + " => " + qds.getData());
                        DocumentReference docRef = qds.getReference();
                        docRef.update("alimentacion_prs", personaUser.getAlimentacion_prs())
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
        datosActualizados = true;
        return datosActualizados;
    }
}
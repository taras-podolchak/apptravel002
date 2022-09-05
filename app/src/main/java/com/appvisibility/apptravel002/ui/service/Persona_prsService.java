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
                        docRef.update("apodo_prs", personaUser.getApodo_prs());
                        docRef.update("nombre_prs", personaUser.getNombre_prs());
                        docRef.update("apellido1_prs", personaUser.getApellido1_prs());
                        docRef.update("apellido2_prs", personaUser.getApellido2_prs());
                        docRef.update("contrasenna_prs", personaUser.getContrasenna_prs());
//                        docRef.update("recordarcontrasenna_prs", personaUser.isRecordarcontrasenna_prs());
                        docRef.update("actividadtipo_prs", personaUser.getActividadtipo_prs());
                        docRef.update("documentaciontipo_prs", personaUser.getDocumentotipo_prs());
                        docRef.update("dni_prs", personaUser.getDni_prs());
                        docRef.update("nacionalidad_prs", personaUser.getNacionalidad_prs());
                        docRef.update("razonsocial_prs", personaUser.getRazonsocial_prs());
                        docRef.update("numerocta_prs", personaUser.getNumerocta_prs());
                        docRef.update("localidad_prs", personaUser.getLocalidad_prs());
                        docRef.update("alimentacion_prs", personaUser.getAlimentacion_prs());
                        docRef.update("contacto1cargo_prs", personaUser.getContacto1Cargo_prs());
                        docRef.update("contacto1nombre_prs", personaUser.getContacto1Nombre_prs());
                        docRef.update("contacto1apellido1_prs", personaUser.getContacto1Apellido1_prs());
                        docRef.update("contacto1apellido2_prs", personaUser.getContacto1Apellido2_prs());
                        docRef.update("contacto1movil_prs", personaUser.getContacto1Movil_prs());
                        docRef.update("contacto1telefono_prs", personaUser.getContacto1Telefono_prs());
                        docRef.update("contacto1email_prs", personaUser.getContacto1Email_prs());
                        docRef.update("contacto2cargo_prs", personaUser.getContacto2Cargo_prs());
                        docRef.update("contacto2nombre_prs", personaUser.getContacto2Nombre_prs());
                        docRef.update("contacto2apellido1_prs", personaUser.getContacto2Apellido1_prs());
                        docRef.update("contacto2apellido2_prs", personaUser.getContacto2Apellido2_prs());
                        docRef.update("contacto2movil_prs", personaUser.getContacto2Movil_prs());
                        docRef.update("contacto2telefono_prs", personaUser.getContacto2Telefono_prs());
                        docRef.update("contacto2email_prs", personaUser.getContacto2Email_prs());
                        docRef.update("contacto3cargo_prs", personaUser.getContacto3Cargo_prs());
                        docRef.update("contacto3nombre_prs", personaUser.getContacto3Nombre_prs());
                        docRef.update("contacto3apellido1_prs", personaUser.getContacto3Apellido1_prs());
                        docRef.update("contacto3apellido2_prs", personaUser.getContacto3Apellido2_prs());
                        docRef.update("contacto3movil_prs", personaUser.getContacto3Movil_prs());
                        docRef.update("contacto3telefono_prs", personaUser.getContacto3Telefono_prs());
                        docRef.update("contacto3email_prs", personaUser.getContacto3Email_prs())
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

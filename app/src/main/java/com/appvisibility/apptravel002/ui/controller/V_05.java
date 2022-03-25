package com.appvisibility.apptravel002.ui.controller;

import static android.content.ContentValues.TAG;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.appvisibility.apptravel002.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link V_05#newInstance} factory method to
 * create an instance of this fragment.
 */
public class V_05 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button v05_boton_confirmar, v05_boton_volver;
    private FirebaseFirestore firebaseFirestore;

    private TextView v05_titulo_eve;
    private ImageView v05_imageView;
    private TextView v05_textView_info_completa;
    private Spinner v05_spinner_lista_personas;
    private Switch v05_swich_llevas_el_coche;
    private Spinner v05_spinner_nesesito_coche;
    private Spinner v05_spinner_ofresco_coche;
    private Spinner v05_spinner_tipo_alojamiento;
    private Spinner v05_spinner_restricciones_allimentarias;
    private Spinner v05_spinner_estado_de_pago;


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
    // TODO: Rename and change types and number of parameters
    public static V_05 newInstance(String param1, String param2) {
        V_05 fragment = new V_05();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_05,
                container, false);

        v05_imageView = view.findViewById(R.id.v05_imageView);

        firebaseFirestore = FirebaseFirestore.getInstance();

        v05_titulo_eve = view.findViewById(R.id.v05_titulo_eve);
        v05_textView_info_completa = view.findViewById(R.id.v05_textView_info_completa);
        v05_imageView = view.findViewById(R.id.v05_imageView);

        DocumentReference docRef = firebaseFirestore.collection("evento_eve").document("2");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        //Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        //cargamos el titulo
                        v05_titulo_eve.setText(document.getString("titulo_eve"));
                        //cargamos la imagen
                        FirebaseStorage storage = FirebaseStorage.getInstance();
                        StorageReference storageRef = storage.getReference();
                        storageRef.child("Eventos/1.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Picasso.get().load(uri).into(v05_imageView);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), "GET IMAGE FAILED", Toast.LENGTH_LONG).show();
                                // Handle any errors
                            }
                        });
                        //cargamos la info completa
                        v05_textView_info_completa.setText(
                                "Nivel de dificultad: " + document.getString("nivel_eve") + "\n"
                                        + "Distancia ida: " + document.getString("distanciaidatru_eve") + "\n"
                                        + "Distancia vuelta: " + document.getString("distanciavueltatru_eve") + "\n"
                                        + "Fecha ida: " + document.getString("fechaidatru_eve") + "\n"
                                        + "Fecha vuelta: " + document.getString("fechavueltatru_eve") + "\n"
                                        + "Coordenadas de salida: " + document.getString("salidacoordenadastru_eve") + "\n"
                                        + "Coordenadas de llegada: " + document.getString("llegadacoordenadastru_eve") + "\n"
                                        + "Precio: " + document.getString("precio_eve") + "€\n"
                                        + document.getString("descgeneral_eve"));

                        //aqui se cargara mas cosas
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });


        v05_boton_confirmar = view.findViewById(R.id.v05_boton_confirmar);    //button v01_boton_buscaar_actividades
        v05_boton_confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_v05_to_nav_v06);
            }
        });
        v05_boton_volver = view.findViewById(R.id.v05_boton_volver);
        v05_boton_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_v05_to_nav_v02);
            }
        });
        return view;
    }//fin de constructor

}
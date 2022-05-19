package com.appvisibility.apptravel002.ui.controller;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.entities.Evento_eve;
import com.appvisibility.apptravel002.ui.service.v02_00_eve_Adapter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class V_05_1 extends Fragment implements OnMapReadyCallback {

    // Campos de xml
    private TextView v05_1_titulo_eve;
    private TextView v05_1_fechapagosennal_eve;
    private TextView v05_1_fechapagototal_eve;
    private LinearLayout v05_1_salidacoordenadastru_eve;
    private String salidacoordenadastru_eve;
    private TextView v05_1_salidaidatru_eve;
    private String llegadacoordenadastru_eve;
    private LinearLayout v05_1_llegadacoordenadastru_eve;
    private TextView v05_1_llegadaidatru_eve;
    private TextView v05_1_descgeneral_eve;
    private Button v05_1_recomendaciones;
    private GoogleMap Mapa;

    // Acceso a datos
    FirebaseFirestore fbf = FirebaseFirestore.getInstance();

    // Entities
    private Evento_eve eventoEnProceso;

    // Service
    private int id_eve_bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_05_1, container, false);

//        id_eve_bundle = getArguments().getInt("eventoParaV_05_1");

//        Bundle bundleEvento = new Bundle();
        Bundle bundleEvento = getArguments();
        //Cargamos el Evento
        eventoEnProceso = (Evento_eve) bundleEvento.getSerializable("eventoParaV_05_1");
        int id_eve_bundle = eventoEnProceso.getId_eve();
//        bundleEvento.putInt("eventoParaV_05", id_eve_bundle);
        bundleEvento.putSerializable("eventoParaV_05", eventoEnProceso);

        this.v05_1_titulo_eve = view.findViewById(R.id.v05_1_txv_titulo_eve);
        this.v05_1_fechapagosennal_eve = view.findViewById(R.id.v05_1_txv_fechapagosennal_eve);
        this.v05_1_fechapagototal_eve = view.findViewById(R.id.v05_1_txv_fechapagototal_eve);
        this.v05_1_salidacoordenadastru_eve = view.findViewById(R.id.v05_1_lly_salidacoordenadastru_eve);
        this.v05_1_salidaidatru_eve = view.findViewById(R.id.v05_1_txv_salidaidatru_eve);
        this.v05_1_llegadacoordenadastru_eve = view.findViewById(R.id.v05_1_lly_llegadacoordenadastru_eve);
        this.v05_1_llegadaidatru_eve = view.findViewById(R.id.v05_1_txv_llegadaidatru_eve);
        this.v05_1_descgeneral_eve = view.findViewById(R.id.v05_1_txv_descgeneral_eve);
        this.v05_1_recomendaciones = view.findViewById(R.id.v05_1_btn_descrecomendaciones_eve);

        //Cargamos el Evento
        // EOB: Intentar pasar este método a changeNoListener y eliminar las dos líneas siguientes
        List<Evento_eve> eventos = new ArrayList<>();
        v02_00_eve_Adapter v02_adapter_eve = null;
/*
        Query query1 = fbf.collection("evento_eve").whereEqualTo("id_eve", id_eve_bundle);
        tabla1ChangeListener (query1, eventos, Evento_eve.class, v02_adapter_eve);
*/
        v05_1_titulo_eve.setText(eventoEnProceso.getTitulo_eve());
        v05_1_fechapagosennal_eve.setText(eventoEnProceso.getFechapagosennal_eve());
        v05_1_fechapagototal_eve.setText(eventoEnProceso.getFechapagototal_eve());
        salidacoordenadastru_eve = eventoEnProceso.getSalidacoordenadastru_eve();
        v05_1_salidaidatru_eve.setText("Salida: " + eventoEnProceso.getSalidaidatru_eve());
        llegadacoordenadastru_eve = eventoEnProceso.getLlegadacoordenadastru_eve();
        v05_1_llegadaidatru_eve.setText("Destino: " + eventoEnProceso.getLlegadaidatru_eve());
        v05_1_descgeneral_eve.setText(eventoEnProceso.getDescgeneral_eve());

//        DocumentReference drf = fbf.collection("evento_eve").document(String.valueOf(id_eve_bundle));
//        DocumentChangeListener(drf);

        // Botones
// https://stackoverflow.com/questions/2662531/launching-google-maps-directions-via-an-intent-on-android/2663565#2663565
        v05_1_salidacoordenadastru_eve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/place/" + salidacoordenadastru_eve));
//                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=" + salidacoordenadastru_eve));
                startActivity(intent);
            }
        });

        v05_1_llegadacoordenadastru_eve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/dir/" + salidacoordenadastru_eve + "/" + llegadacoordenadastru_eve));
                startActivity(intent);
            }
        });

        v05_1_recomendaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://amigosmontanawo.eboe62.com/preguntas-frecuentes-faq/"));
               startActivity(intent);
//                webView.loadUrl("https://amigosmontanawo.eboe62.com/preguntas-frecuentes-faq/");
            }
        });

        v05_1_descgeneral_eve = view.findViewById(R.id.v05_1_txv_descgeneral_eve);
        v05_1_descgeneral_eve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_v05_1_to_nav_v05, bundleEvento);
            }
        });

        return view;
    }//Fin de constructor
/*
    @Override
    public <T> void tabla1ChangeListener(Query query, List<T> lista, Class<T> tipoObjeto, RecyclerView.Adapter miAdapter) {
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException error) {
                T enProceso;
                if (error != null) {
                    Log.e("Error en Firestore", error.getMessage());
                    return;
                }
                lista.clear();
                for (QueryDocumentSnapshot qds : snapshots) {
                    eventoEnProceso = (Evento_eve) qds.toObject(tipoObjeto);
//                    lista.add(enProceso);
//                miAdapter.notifyDataSetChanged();

                    v05_1_titulo_eve.setText(eventoEnProceso.getTitulo_eve());
                    v05_1_fechapagosennal_eve.setText(eventoEnProceso.getFechapagosennal_eve());
                    v05_1_fechapagototal_eve.setText(eventoEnProceso.getFechapagototal_eve());
                    salidacoordenadastru_eve = eventoEnProceso.getSalidacoordenadastru_eve();
                    v05_1_salidaidatru_eve.setText("Salida: " + eventoEnProceso.getSalidaidatru_eve());
                    llegadacoordenadastru_eve = eventoEnProceso.getLlegadacoordenadastru_eve();
                    v05_1_llegadaidatru_eve.setText("Destino: " + eventoEnProceso.getLlegadaidatru_eve());
                    v05_1_descgeneral_eve.setText(eventoEnProceso.getDescgeneral_eve());

//                if (pdg.isShowing()){
//                    pdg.dismiss();
//                }

                    Log.d(TAG, "Datos recibidos!");
                    Toast.makeText(getActivity(), "Datos recibidos!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
*/
/*
    private void DocumentChangeListener(DocumentReference drf) {
        drf.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        evento = (Evento_eve) document.toObject(Evento_eve.class);
                        v05_1_descgeneral_eve.setText(evento.getDescgeneral_eve());
                        v05_1_descgeneral_eve.setText(document.getString("descgeneral_eve"));
                    } else {
                        Log.d(TAG, "Error en document");
                    }
                } else {
                    Log.d(TAG, "Error en Firestore ", task.getException());
                }
            }
        });
    }
*/

// https://stackoverflow.com/questions/27425547/cannot-resolve-method-getsupportfragmentmanager-inside-fragment
    public void cargarMapa() {
        // Buscamos el Fragment
        SupportMapFragment mapaFrag = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragmentMap);
        // Le decimos que el mapa se cargue con esta actividad
        mapaFrag.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        Mapa = googleMap;
        personalizarPropiedades(); //Modificamos alguna propiedad del mapa
    }

    public void personalizarPropiedades() {
        // Mostramos controles zoom sobre el mapa
        Mapa.getUiSettings().setZoomControlsEnabled(true);

    }
}

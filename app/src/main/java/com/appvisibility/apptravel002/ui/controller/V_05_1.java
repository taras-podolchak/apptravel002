package com.appvisibility.apptravel002.ui.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.controller.Interfaces.meteorologia.Root;
import com.appvisibility.apptravel002.ui.controller.Interfaces.meteorologia.WeatherService;
import com.appvisibility.apptravel002.ui.entities.Evento_eve;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.firebase.firestore.FirebaseFirestore;

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
    private TextView lblCurrent;
    private TextView lblfeels_like;
    private TextView lblMin;
    private TextView lblMax;
    private TextView lbl_humedad;
    private TextView lbl_presión;
    private TextView lbl_velocidad_viento;
    private TextView lbl_dirección_viento;
    private TextView lbl_nubes;
    private TextView lbl_visibilidad;

    private WeatherService service = new WeatherService("a9cf0f7a3cc84a884d84d4df48f057c2");

    // Acceso a datos
    FirebaseFirestore fbf = FirebaseFirestore.getInstance();

    // Entities
    private Evento_eve eventoEnProceso;

    // Service
    private int id_eve_bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_05_1, container, false);

        Bundle bundleEvento = getArguments();
        //Cargamos el Evento
        eventoEnProceso = (Evento_eve) bundleEvento.getSerializable("eventoParaV_05_1");
        int id_eve_bundle = eventoEnProceso.getId_eve();
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
        lblCurrent = view.findViewById(R.id.lblCurrent);
        lblfeels_like = view.findViewById(R.id.lblfeels_like);
        lblMin = view.findViewById(R.id.lblMin);
        lblMax = view.findViewById(R.id.lblMax);
        lbl_humedad = view.findViewById(R.id.lbl_humedad);
        lbl_presión = view.findViewById(R.id.lbl_presión);
        lbl_velocidad_viento = view.findViewById(R.id.lbl_velocidad_viento);
        lbl_dirección_viento = view.findViewById(R.id.lbl_direccion_viento);
        lbl_nubes = view.findViewById(R.id.lbl_nubes);
        lbl_visibilidad = view.findViewById(R.id.lbl_visibilidad);

        //Cargamos el Evento
        // EOB: Intentar pasar este método a changeNoListener y eliminar las dos líneas siguientes
//        List<Evento_eve> eventos = new ArrayList<>();
//        v02_00_eve_Adapter v02_adapter_eve = null;

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

        //recuperacion de temperatura
        //https://www.youtube.com/watch?v=5sIrkG_PLYs&ab_channel=OSWALDOFABIANPALACIOSMU%C3%91OZ
        service.requestWeatherData("Madrid", "ES", (isNetworkError, statusCode, root) -> {
            if (!isNetworkError) {
                if (statusCode == 200) {
                    showWeatherInfo(root);
                } else {
                    Log.d("Weather", "Service error");
                }
            } else {
                Log.d("Weather", "Network error");
            }
        });


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

        v05_1_descgeneral_eve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_v05_1_to_nav_v05, bundleEvento);
            }
        });

        return view;
    }//Fin de constructor

    // https://stackoverflow.com/questions/27425547/cannot-resolve-method-getsupportfragmentmanager-inside-fragment
// Inside a Fragment subclass you have to use getFragmentManager in place of getSupportFragmentManager.
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

    @SuppressLint("SetTextI18n")
    public void showWeatherInfo(Root root) {
        String temp = String.valueOf(root.getMain().getTemp());
        String feels_like = String.valueOf(root.getMain().getFeelsLike());
        String tempMin = String.valueOf(root.getMain().getTempMin());
        String tempMax = String.valueOf(root.getMain().getTempMax());
        String humedad = String.valueOf(root.getMain().getHumidity());
        String presion = String.valueOf(root.getMain().getPressure());
        String velocidad_viento = String.valueOf(root.getWind().getSpeed());
        String dirección_viento = String.valueOf(root.getWind().getDeg());
        String nubes = String.valueOf(root.getClouds().getAll());
        String visibilidad = String.valueOf(root.getVisibility());



        lblCurrent.setText(getString(R.string.current) + " " + temp + " °C");
        lblfeels_like.setText(getString(R.string.se_siente_como) + " " + feels_like + " °C");
        lblMin.setText(getString(R.string.minimum) + " " + tempMin + " °C");
        lblMax.setText(getString(R.string.maximum) + " " + tempMax + " °C");
        lbl_humedad.setText(getString(R.string.humedad) + " " + humedad + " %");
        lbl_presión.setText(getString(R.string.presion) + " " + presion + " hPa");
        lbl_velocidad_viento.setText(getString(R.string.velocidad) + " " + velocidad_viento + " km/h");
        lbl_dirección_viento.setText(getString(R.string.dirección) + " " + dirección_viento + "°");
        lbl_nubes.setText(getString(R.string.nubes) + " " + nubes + " %");
        lbl_visibilidad.setText(getString(R.string.visibilidad) + " " + visibilidad + " %");
    }
}

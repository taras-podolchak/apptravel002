package com.appvisibility.apptravel002.ui.service;

import static com.appvisibility.apptravel002.MainActivity_val.sesionIniciada;
import static com.appvisibility.apptravel002.ui.service.Persona_prsService.personaUserU;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.controller.ACV_usuario;
import com.appvisibility.apptravel002.ui.controller.V_05;
import com.appvisibility.apptravel002.ui.entities.Persona_prs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Alimentacion_aliService#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Alimentacion_aliService extends Fragment {

    // Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Rename and change types of parameters
    private String mParam1;
    private Bundle mBundlePersonaUser;

    // Campos de xml

    // Entities
    private static Persona_prs personaUser;
    private List alimentaciones_prs = new ArrayList();
    private String alimentacion_prsTitulo;
    private String alimentacion_prsActual;
    private String alimentacionEnProceso = "";
    private Boolean datosActualizados;
    public static Spanned alimentacionSpanned;

    private Context mContext;

    public Alimentacion_aliService() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param bundlePersonaUser Parameter 2.
     * @return A new instance of fragment Contacto_cnt.
     */
    // Rename and change types and number of parameters
    public static Alimentacion_aliService newInstance(String param1, Bundle bundlePersonaUser) {
        Alimentacion_aliService fragment = new Alimentacion_aliService();

        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putBundle(ARG_PARAM2, bundlePersonaUser);
        fragment.setArguments(args);

//https://stackoverflow.com/questions/12739909/send-data-from-activity-to-fragment-in-android
/* So, to pass data from the MotherActivity to such a Fragment you will need to create private Strings/Bundles above the onCreate of your Mother activity - which you can fill with the data you want to pass to the fragments, and pass them on via a method created after the onCreate (here called getMyData()).*/
//Recuperamos los datos del Usuario activo
/*
        MainActivity_val activity = (MainActivity_val) view.getContext();
        Bundle bundlePersonaUser = activity.getUser();
        personaUser = (Persona_prs) bundlePersonaUser.getSerializable("User");
*/
        personaUser = (Persona_prs) bundlePersonaUser.getSerializable("User");
        return fragment;
    }//Fin de constructor

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mBundlePersonaUser = getArguments().getBundle(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        return view;
    }

// Convierte un String separado por guiones en un ArrayList
    public Spanned mostrarAlimentacionEnProceso(){
//        alimentacion_prsTitulo = "Restricciones Alimentarias: ";
        alimentacion_prsTitulo = "";
        alimentacionEnProceso = "";
// Si el usuario tiene restricciones Alimentarias se recupera
        recuperarAlimentacionActual();
// https://stackoverflow.com/questions/7488643/how-to-convert-comma-separated-string-to-list
// https://regexr.com/346hf
// Separar los saltos de línea no funciona
//        alimentaciones_prs = Arrays.asList(alimentacion_prsActual.split("^[\\n]*$"));
        alimentaciones_prs = Arrays.asList(alimentacion_prsActual.split("\\s*\\s*-\\s*"));
        ACV_usuario.newInstance(null, null);
        V_05.newInstance(null, null);
        for (Object ali: alimentaciones_prs){
            alimentacionEnProceso += ali.toString() + " - ";
// https://stackoverflow.com/questions/7806709/remove-trailing-comma-from-comma-separated-string
//            v05_alimentacion_prs.setText(Html.fromHtml(alimentacionEnProceso+"<br>"), TextView.BufferType.SPANNABLE);
            alimentacionSpanned = Html.fromHtml("<u>"+ alimentacion_prsTitulo + "</u><br>" + alimentacionEnProceso.replaceAll(" - $", ""));
        };
//        alimentacionEnProceso.replaceAll("- $", "");
        return alimentacionSpanned;
    }

    public void indicarRestriccionesAlimentarias(View view) {
        String [] restriccionesAlimentarias = view.getResources().getStringArray(R.array.restriccionesAlimentarias);
        boolean[] opcionesRestriccionesAlimentarias =new boolean[restriccionesAlimentarias.length];

        //Organizamos el contenido del AlertDialog modal Selección Múltiple
        alimentacionEnProceso = "";
        for (int i=0; i < restriccionesAlimentarias.length; i++){
            if (alimentaciones_prs.contains(restriccionesAlimentarias[i])){
                opcionesRestriccionesAlimentarias[i] = true;
                alimentacionEnProceso += restriccionesAlimentarias[i] + " - ";
            } else {
                opcionesRestriccionesAlimentarias[i] = false;
            };
        }

        if (sesionIniciada >= view.getResources().getInteger(R.integer.rol_valiente)
            || sesionIniciada == view.getResources().getInteger(R.integer.rol_empresas_trekking)) {
            AlertDialog.Builder modalMultipleRestriccionesAlimentarias = new AlertDialog.Builder(view.getContext());
            modalMultipleRestriccionesAlimentarias
                .setIcon(R.drawable.ico_foodmealplaterestaurant_azul)
                .setTitle("RESTRICCIONES ALIMENTARIAS")
                .setMultiChoiceItems(restriccionesAlimentarias, opcionesRestriccionesAlimentarias, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item, boolean isChecked) {
                        alimentacionEnProceso  = "";
                        for (int i = 0; i < opcionesRestriccionesAlimentarias.length; i++) {
                            if (opcionesRestriccionesAlimentarias[i] == true) {
                                alimentacionEnProceso += restriccionesAlimentarias[i] + " - ";
                            }
                        }
                    }
                })
                .setPositiveButton("Confirmar", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        volcarAlimentacion();

                        if (ACV_usuario.acv_usuario_alimentacion_prs != null){
                            ACV_usuario.acv_usuario_alimentacion_prs.setText(Html.fromHtml("<u>"+ alimentacion_prsTitulo + "</u><br>" + alimentacionEnProceso.replaceAll(" - $", "")),
                                    TextView.BufferType.SPANNABLE);
                        }

                        if (V_05.v05_alimentacion_prs != null){
                            V_05.v05_alimentacion_prs.setText(Html.fromHtml("<u>"+ alimentacion_prsTitulo + "</u><br>" + alimentacionEnProceso.replaceAll(" - $", "")),
                                    TextView.BufferType.SPANNABLE);
                        }
                        datosActualizados = personaUserU(datosActualizados, personaUser);
                        if (datosActualizados) {
                            Toast.makeText(view.getContext(), "Las Restricciones Alimentarias se han guardado satisfactoriamente", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(view.getContext(), "Las Restricciones Alimentarias no se han podido guardar", Toast.LENGTH_LONG).show();
                        }
//                        ((AppCompatActivity) mContext).findViewById(R.id.v05_txv_muestraRestriccionesAlimentarias);
                    }
                })
                .setNegativeButton("Volver", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
        }
    }

    public void volcarAlimentacion() {
        personaUser.setAlimentacion_prs(alimentacionEnProceso.replaceAll(" - $", ""));
    }

    public void recuperarAlimentacionActual() {
        alimentacion_prsActual = (personaUser.getAlimentacion_prs() == null)? "" : personaUser.getAlimentacion_prs();
    }
}
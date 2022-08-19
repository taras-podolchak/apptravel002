package com.appvisibility.apptravel002.ui.service;

import static com.appvisibility.apptravel002.MainActivity_val.sesionIniciada;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.appvisibility.apptravel002.MainActivity_val;
import com.appvisibility.apptravel002.R;
import com.appvisibility.apptravel002.ui.controller.V_05;
import com.appvisibility.apptravel002.ui.entities.Contacto_cnt;
import com.appvisibility.apptravel002.ui.entities.Persona_prs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Contacto_cntService#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Contacto_cntService extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static String contactoNumero;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Campos de xml
    private AutoCompleteTextView v05_2_contactoElegido;
    private Spinner v05_2_cargo_cnt;
    private EditText v05_2_nombre_cnt;
    private EditText v05_2_apellido1_cnt;
    private EditText v05_2_apellido2_cnt;
    private EditText v05_2_movil_cnt;
    private EditText v05_2_telefono_cnt;
    private EditText v05_2_email_cnt;

    // Entities
    private Persona_prs personaUser;
    private Contacto_cnt contactoEnProceso;
    private Map<Integer, Contacto_cnt> contactos = new HashMap<>();
    private String contactoElegido = "";
    public String contacto1ElegidoResultado;
    public String contacto2ElegidoResultado;
    public String contacto3ElegidoResultado;
    private String id_cnt = "";
    private String idNueva_cnt;
    private Boolean nuevoContacto = false;
    private String cargo_cnt = "";
    private String nombre_cnt;
    private String apellido1_cnt;
    private String apellido2_cnt;
    private String movil_cnt;
    private String telefono_cnt;
    private String email_cnt;

    private Context mContext;

    public Contacto_cntService() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Contacto_cnt.
     */
    // TODO: Rename and change types and number of parameters
    public static Contacto_cntService newInstance(String param1, String param2) {
        Contacto_cntService fragment = new Contacto_cntService();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        contactoNumero = param1;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_05_2_modal_cnt, container, false);
        View view2 = inflater.inflate(R.layout.fragment_v_05, container, false);

        //https://stackoverflow.com/questions/12739909/send-data-from-activity-to-fragment-in-android
        /* So, to pass data from the MotherActivity to such a Fragment you will need to create private Strings/Bundles above the onCreate of your Mother activity - which you can fill with the data you want to pass to the fragments, and pass them on via a method created after the onCreate (here called getMyData()).*/
        //Recuperamos los datos del Usuario activo

        MainActivity_val activity = (MainActivity_val) view.getContext();
        Bundle bundlePersonaUser = activity.getUser();
        personaUser = (Persona_prs) bundlePersonaUser.getSerializable("User");
//        contactoEnProceso = (Contacto_cnt) bundleContacto.getSerializable("contactoParaV_05");

        // https://www.youtube.com/watch?v=nlqtyfshUkc&ab_channel=CodingDemos
            String [] contactocargo_prs = view.getResources().getStringArray(R.array.contactoCargo_prs);
/*
        ArrayList<String> contactocargo_prs = new ArrayList<>();
        contactocargo_prs.add("RELACION:");
        contactocargo_prs.add("Pareja");
        contactocargo_prs.add("Amistad");
        contactocargo_prs.add("Familiar");
        contactocargo_prs.add("Trabajo");
*/
            ArrayAdapter<String> arrayAdapter_prs = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, contactocargo_prs);
            arrayAdapter_prs.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            String [] contactocargo_sum = view.getResources().getStringArray(R.array.contactoCargo_sum);
/*
        ArrayList<String> contactocargo_sum = new ArrayList<>();
        contactocargo_sum.add("CARGO:");
        contactocargo_sum.add("Gerencia");
        contactocargo_sum.add("Reservas");
        contactocargo_sum.add("Facturación");
*/
            ArrayAdapter<String> arrayAdapter_sum = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, contactocargo_sum);
            arrayAdapter_sum.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

//            permitirAcceso(view);

            Uri bbdd = ContactsContract.Contacts.CONTENT_URI;
            String projection[] = new String[] {
                    ContactsContract.Contacts._ID,
                    ContactsContract.Contacts.DISPLAY_NAME,
            };
            String selectionClause = null;
            String selectionArgs[] = null;
            String sortOrder = ContactsContract.Contacts.DISPLAY_NAME;
//        String sortOrder = null;
            Cursor cursor = crearCursor(view, bbdd, projection, selectionClause, selectionArgs, sortOrder);

// https://stackoverflow.com/questions/4301064/how-to-get-the-first-name-and-last-name-from-android-contacts
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                id_cnt = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                nombre_cnt = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                contactoEnProceso = new Contacto_cnt (Integer.parseInt(id_cnt), cargo_cnt, nombre_cnt, apellido1_cnt, apellido2_cnt, movil_cnt, telefono_cnt, email_cnt);
                contactos.put(Integer.parseInt(id_cnt), contactoEnProceso);
                cursor.moveToNext();
            }
            cursor.close();

            List<String> contactosDescripcion = new ArrayList<>();

            Iterator<Integer> it1 = contactos.keySet().iterator();
            while (it1.hasNext()) {
                Integer key = it1.next();
                contactosDescripcion.add(
                    contactos.get(key).getId_cnt() + " - " +
                    contactos.get(key).getNombre_cnt()
                );
            }

            ArrayAdapter<String> arrayAdapter_contacto = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, contactosDescripcion);
            arrayAdapter_contacto.setDropDownViewResource(android.R.layout.simple_list_item_1);

            AlertDialog.Builder modalFormContacto = new AlertDialog.Builder(view.getContext());
            v05_2_contactoElegido = view.findViewById(R.id.v05_2_act_contactoElegido);
//            v05_2_contactoElegidoResultado = view.findViewById(R.id.v05_txv_contactoElegidoResultado);
            v05_2_cargo_cnt = view.findViewById(R.id.v05_2_spn_ContactoCargo);
            v05_2_nombre_cnt = view.findViewById(R.id.v05_2_etx_ContactoNombre);
            v05_2_apellido1_cnt = view.findViewById(R.id.v05_2_etx_ContactoApellido1);
            v05_2_apellido2_cnt = view.findViewById(R.id.v05_2_etx_ContactoApellido2);
            v05_2_movil_cnt = view.findViewById(R.id.v05_2_etx_ContactoMovil);
            v05_2_telefono_cnt = view.findViewById(R.id.v05_2_etx_ContactoTelefono);
            v05_2_email_cnt = view.findViewById(R.id.v05_2_etx_ContactoEmail);
/*
            cargo_cnt = personaUser.getContacto1Cargo_prs();
            nombre_cnt = personaUser.getContacto1Nombre_prs();
            apellido1_cnt = personaUser.getContacto1Apellido1_prs();
            apellido2_cnt = personaUser.getContacto1Apellido2_prs();
            movil_cnt = personaUser.getContacto1Movil_prs();
            telefono_cnt = personaUser.getContacto1Telefono_prs();
            email_cnt = personaUser.getContacto1Email_prs();
            contactoEnProceso = new Contacto_cnt(Integer.parseInt(id_cnt), cargo_cnt, nombre_cnt, apellido1_cnt, apellido2_cnt, movil_cnt, telefono_cnt, email_cnt);
*/
        switch (contactoNumero){
            case ("Contacto1"): {
                recuperarContacto1Actual(view);
                break;
            }
            case ("Contacto2"): {
                recuperarContacto2Actual(view);
                break;
            }
            case ("Contacto3"): {
                recuperarContacto3Actual(view);
                break;
            }
        }

//            recuperarContacto1Actual(view);
            mostrarContactoEnProceso(view);

            v05_2_contactoElegido.setAdapter(arrayAdapter_contacto);

// https://stackoverflow.com/questions/4819813/how-to-get-text-from-autocompletetextview
            v05_2_contactoElegido.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                    v05_2_contactoElegido.setSelection(position);
                    Object item = parent.getItemAtPosition(position);
                    contactoElegido = (String) item;
                    List<String> contactoNuevaArray = Arrays.asList(contactoElegido.split("\\s*\\s*-\\s*"));
                    for (int i=0; i <= contactoNuevaArray.size(); i++){
                        switch (i) {
                            case 0: {
                                idNueva_cnt = contactoNuevaArray.get(i);
                                if (!idNueva_cnt.equalsIgnoreCase(id_cnt)){
                                    nuevoContacto = true;
                                    limpiarContacto(view);
                                }
                                break;}
                            case 1: {
                                v05_2_nombre_cnt.setText(contactoNuevaArray.get(i)); break;}
                            default: {
                                contactoElegido = " ";}
                        }
                    }

//                    Toast.makeText(view.getContext(), id_cnt +"-->"+ idNueva_cnt, Toast.LENGTH_LONG).show();
                    id_cnt = idNueva_cnt;

                    Uri bbdd = ContactsContract.Data.CONTENT_URI;
                    String projection[] = new String[] {
                            ContactsContract.Data.CONTACT_ID,
                            ContactsContract.Data.MIMETYPE,
                            ContactsContract.Data.DISPLAY_NAME_ALTERNATIVE,
                            ContactsContract.Data.DATA1,
                    };
                    String selectionClause = ContactsContract.Data.CONTACT_ID+ " = ?";
                    String selectionArgs[] = new String[]{id_cnt};
                    String sortOrder = null;
                    Cursor cursor1 = crearCursor(view, bbdd, projection, selectionClause, selectionArgs, sortOrder);

                    if (cursor1.moveToFirst()) {
                        nombre_cnt = cursor1.getString(cursor1.getColumnIndexOrThrow(ContactsContract.Data.DISPLAY_NAME_ALTERNATIVE));
                        if (nombre_cnt.contains(",")){
                            contactoNuevaArray = Arrays.asList(nombre_cnt.split("\\s*,\\s*"));
                            for (int i=0; i <= contactoNuevaArray.size(); i++){
                                switch (i) {
                                    case 0: {
                                        apellido1_cnt = contactoNuevaArray.get(i); break;}
                                    case 1: {
                                        nombre_cnt = contactoNuevaArray.get(i); break;}
                                    default: {
                                        contactoElegido = "Error: Contacto sin identificar";}
                                }
                            }
                        } else {
                            apellido1_cnt = "";
                        }

// https://stackoverflow.com/questions/8805937/retrieving-phone-number-from-contact-id-android
// Permite ver en el log los datos que estamos capturado
/*
                        for(int i=0; i< cursor1.getColumnCount(); i++) {
                            Log.i("CONTACTSTAG21", cursor1.getColumnName(i) + ": " + cursor1.getString(i));
                        }
 */
                        cursor1.moveToNext();
                    }
                    cursor1.close();

                    Uri bbdd2 = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                    String projection2[] = new String[] {
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                            ContactsContract.CommonDataKinds.Phone.DATA,
                    };
                    String selectionClause2 = ContactsContract.CommonDataKinds.Phone.CONTACT_ID+ " = ?";
                    String selectionArgs2[] = new String[]{id_cnt};
                    String sortOrder2 = null;
                    Cursor cursor2 = crearCursor(view, bbdd2, projection2, selectionClause2, selectionArgs2, sortOrder2);

                    if (!cursor2.moveToFirst()) {
                        movil_cnt = "";
                        telefono_cnt = "";
                    }
                    cursor2.moveToFirst();
                    List<String> telefonos = new ArrayList<>();
                    while (!cursor2.isAfterLast()) {
                        movil_cnt = contactoEnProceso.getMovil_cnt();
                        if (nuevoContacto && (movil_cnt == null || movil_cnt.equalsIgnoreCase(""))) {
                            movil_cnt = cursor2.getString(cursor2.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DATA1)).replaceAll(" ", "");
                            if (!telefonos.contains(movil_cnt)){
                                telefonos.add(movil_cnt);
                            }
                            int i = telefonos.size();
                            switch (i) {
                                case 0: {
                                    movil_cnt = "";
                                    telefono_cnt = "";
                                    break;}
                                case 1: {
// https://regexr.com/346hf
// Diferenciar si el número es un movil
                                    if (telefonos.get(0).matches("^[+]*(\\d{2})*[6]\\d{8,10}$")){
                                        movil_cnt = telefonos.get(0);
                                        telefono_cnt = "";
                                    } else {
                                        movil_cnt = "";
                                        telefono_cnt = telefonos.get(0);
                                    }
                                    break;}
                                default: {
                                    if (telefonos.get(0).matches("^[+]*(\\d{2})*[6]\\d{8,10}$")) {
                                        movil_cnt = telefonos.get(0);
                                        telefono_cnt = telefonos.get(1);
                                    } else {
                                        movil_cnt = telefonos.get(1);
                                        telefono_cnt = telefonos.get(0);
                                    }
                                }
                            }
                        }
                        cursor2.moveToNext();
                    }
                    cursor2.close();

                    Uri bbdd3 = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
                    String projection3[] = new String[] {
                            ContactsContract.CommonDataKinds.Email.CONTACT_ID,
                            ContactsContract.CommonDataKinds.Email.DATA,
                    };
                    String selectionClause3 = ContactsContract.CommonDataKinds.Email.CONTACT_ID+ " = ?";
                    String selectionArgs3[] = new String[]{id_cnt};
                    String sortOrder3 = null;
                    Cursor cursor3 = crearCursor(view, bbdd3, projection3, selectionClause3, selectionArgs3, sortOrder3);

                    if (!cursor3.moveToFirst()) {
                        email_cnt = "";
                    }
                    cursor3.moveToFirst();
                    while (!cursor3.isAfterLast()) {
                        email_cnt = contactoEnProceso.getEmail_cnt();
                        if (nuevoContacto && (email_cnt == null || email_cnt.equalsIgnoreCase(""))) {
                            email_cnt = cursor3.getString(cursor3.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.DATA1)).replaceAll(" ", "");
                            if (email_cnt == null || email_cnt.equalsIgnoreCase("")) {
                                email_cnt = "";
                            }
                        }
/*
                        for(int i=0; i< cursor3.getColumnCount(); i++){
                            Log.i("CONTACTSTAG21", cursor3.getColumnName(i) + ": " + cursor3.getString(i));
                        }
 */
                        cursor3.moveToNext();
                    }
                    cursor3.close();

                    contactoEnProceso = new Contacto_cnt(Integer.parseInt(id_cnt), cargo_cnt, nombre_cnt, apellido1_cnt, apellido2_cnt, movil_cnt, telefono_cnt, email_cnt);
                    mostrarContactoEnProceso(view);

                    nuevoContacto = false;
                    if (nombre_cnt != ""){
                        v05_2_contactoElegido.setVisibility(View.GONE);
                    }
                }
            });

            modalFormContacto.setIcon(R.drawable.ico_contact_book);
            modalFormContacto.setTitle("PERSONA DE CONTACTO");

            if (sesionIniciada == view.getResources().getInteger(R.integer.rol_transportecolectivo)) {
                v05_2_cargo_cnt.setAdapter(arrayAdapter_prs);
// https://stackoverflow.com/questions/37481951/how-to-get-and-set-selected-item-from-spinner-using-sharedpreferences
// Recupera spinner anterior
                v05_2_cargo_cnt.getSelectedItemPosition();
                int indexOfPreviousSelection = arrayAdapter_sum.getPosition(contactoEnProceso.getCargo_cnt());
                v05_2_cargo_cnt.setSelection(indexOfPreviousSelection);
// https://stackoverflow.com/questions/4622517/hide-a-edittext-make-it-visible-by-clicking-a-menu
                v05_2_apellido2_cnt.setVisibility(View.GONE);
                v05_2_telefono_cnt.setVisibility(View.GONE);
                v05_2_email_cnt.setVisibility(View.GONE);

                modalFormContacto.setPositiveButton("Confirmar", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cargo_cnt = v05_2_cargo_cnt.getSelectedItem().toString();
                        contactoEnProceso.setCargo_cnt(cargo_cnt);
// https://www.youtube.com/watch?v=JB3ETK5mh3c&ab_channel=CodinginFlow
// autocomplete form with contact android youtube
                        if (!cargo_cnt.equalsIgnoreCase("RELACION:")) {
                            contactoEnProceso = new Contacto_cnt(Integer.parseInt(id_cnt), cargo_cnt, nombre_cnt, apellido1_cnt, apellido2_cnt, movil_cnt, telefono_cnt, email_cnt);
                            switch (contactoNumero){
                                case ("Contacto1"): {
                                    volcarContacto1(view);
                                    break;
                                }
                                case ("Contacto2"): {
                                    volcarContacto2(view);
                                    break;
                                }
                                case ("Contacto3"): {
                                    volcarContacto3(view);
                                    break;
                                }
                            }

//                            volcarContacto(view);
                        } else {
                            Toast.makeText(view.getContext(), "El contacto no se guarda por falta de datos", Toast.LENGTH_LONG).show();
                        }
// https://stackoverflow.com/questions/42397915/how-to-pass-string-from-one-fragment-to-another-in-android
// Permite mostrar en la pantalla padre el resultado de la selección de un contacto de la agenda

                    switch (contactoNumero){
                        case ("Contacto1"): {
                            V_05.newInstance(contacto1ElegidoResultado, null);
                            V_05.v05_2_muestraContacto1Elegido.setText(contacto1ElegidoResultado);
                            break;
                        }
                        case ("Contacto2"): {
                            V_05.newInstance(contacto2ElegidoResultado, null);
                            V_05.v05_2_muestraContacto2Elegido.setText(contacto2ElegidoResultado);
                            break;
                        }
                        case ("Contacto3"): {
                            V_05.newInstance(contacto3ElegidoResultado, null);
                            V_05.v05_2_muestraContacto3Elegido.setText(contacto3ElegidoResultado);
                            break;
                        }
                    }

//                        V_05.newInstance(contactoElegidoResultado, null);
//                        V_05.v05_2_muestraContacto1Elegido.setText(contactoElegidoResultado);
                    }
                });
            } else if (sesionIniciada == view.getResources().getInteger(R.integer.rol_valiente)
                    || sesionIniciada == view.getResources().getInteger(R.integer.rol_alojamiento)
                    || sesionIniciada == view.getResources().getInteger(R.integer.rol_empresas_trekking)){
                v05_2_cargo_cnt.setAdapter(arrayAdapter_sum);
// https://stackoverflow.com/questions/37481951/how-to-get-and-set-selected-item-from-spinner-using-sharedpreferences
// Recupera spinner anterior
                v05_2_cargo_cnt.getSelectedItemPosition();
                int indexOfPreviousSelection = arrayAdapter_sum.getPosition(contactoEnProceso.getCargo_cnt());
                v05_2_cargo_cnt.setSelection(indexOfPreviousSelection);
                modalFormContacto.setPositiveButton("Confirmar", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cargo_cnt = v05_2_cargo_cnt.getSelectedItem().toString();
                        contactoEnProceso.setCargo_cnt(cargo_cnt);
                        if (!cargo_cnt.equalsIgnoreCase("CARGO:")) {
                            switch (contactoNumero){
                                case ("Contacto1"): {
                                    volcarContacto1(view);
                                    break;
                                }
                                case ("Contacto2"): {
                                    volcarContacto2(view);
                                    break;
                                }
                                case ("Contacto3"): {
                                    volcarContacto3(view);
                                    break;
                                }
                            }

//                            volcarContacto(view);
                        } else {
                            Toast.makeText(view.getContext(), "El contacto no se guarda por falta de datos", Toast.LENGTH_LONG).show();
                        }

                        switch (contactoNumero){
                            case ("Contacto1"): {
                                V_05.newInstance(contacto1ElegidoResultado, null);
                                V_05.v05_2_muestraContacto1Elegido.setText(contacto1ElegidoResultado);
                                break;
                            }
                            case ("Contacto2"): {
                                V_05.newInstance(contacto2ElegidoResultado, null);
                                V_05.v05_2_muestraContacto2Elegido.setText(contacto2ElegidoResultado);
                                break;
                            }
                            case ("Contacto3"): {
                                V_05.newInstance(contacto3ElegidoResultado, null);
                                V_05.v05_2_muestraContacto3Elegido.setText(contacto3ElegidoResultado);
                                break;
                            }
                        }

//                        V_05.newInstance(contactoElegidoResultado, null);
//                        V_05.v05_2_muestraContacto1Elegido.setText(contactoElegidoResultado);
                    }
                });
            }

            modalFormContacto.setNegativeButton("Volver", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setView(view)
                .create()
                .show();
        return view;
    }//Fin de constructor

    private Cursor crearCursor(View view, Uri bbdd, String projection[], String selectionClause, String selectionArgs[], String sortOrder) {
// https://stackoverflow.com/questions/24108998/getcontentresolver-is-not-working
        Cursor cursor = view.getContext().getContentResolver().query(
                bbdd,
                projection,
                selectionClause,
                selectionArgs,
                sortOrder,
                null
        );
        return cursor;
    }

    public void volcarContacto1(View view){
        personaUser.setContacto1Cargo_prs(contactoEnProceso.getCargo_cnt());
        personaUser.setContacto1Nombre_prs(contactoEnProceso.getNombre_cnt());
        personaUser.setContacto1Apellido1_prs(contactoEnProceso.getApellido1_cnt());
        personaUser.setContacto1Apellido2_prs(contactoEnProceso.getApellido2_cnt());
        personaUser.setContacto1Movil_prs(contactoEnProceso.getMovil_cnt());
        personaUser.setContacto1Telefono_prs(contactoEnProceso.getTelefono_cnt());
        personaUser.setContacto1Email_prs(contactoEnProceso.getEmail_cnt());
        contacto1ElegidoResultado = contactoEnProceso.getNombre_cnt()+" "+contactoEnProceso.getApellido1_cnt();
    }

    public void volcarContacto2(View view){
        personaUser.setContacto2Cargo_prs(contactoEnProceso.getCargo_cnt());
        personaUser.setContacto2Nombre_prs(contactoEnProceso.getNombre_cnt());
        personaUser.setContacto2Apellido1_prs(contactoEnProceso.getApellido1_cnt());
        personaUser.setContacto2Apellido2_prs(contactoEnProceso.getApellido2_cnt());
        personaUser.setContacto2Movil_prs(contactoEnProceso.getMovil_cnt());
        personaUser.setContacto2Telefono_prs(contactoEnProceso.getTelefono_cnt());
        personaUser.setContacto2Email_prs(contactoEnProceso.getEmail_cnt());
        contacto2ElegidoResultado = contactoEnProceso.getNombre_cnt()+" "+contactoEnProceso.getApellido1_cnt();
    }

    public void volcarContacto3(View view){
        personaUser.setContacto3Cargo_prs(contactoEnProceso.getCargo_cnt());
        personaUser.setContacto3Nombre_prs(contactoEnProceso.getNombre_cnt());
        personaUser.setContacto3Apellido1_prs(contactoEnProceso.getApellido1_cnt());
        personaUser.setContacto3Apellido2_prs(contactoEnProceso.getApellido2_cnt());
        personaUser.setContacto3Movil_prs(contactoEnProceso.getMovil_cnt());
        personaUser.setContacto3Telefono_prs(contactoEnProceso.getTelefono_cnt());
        personaUser.setContacto3Email_prs(contactoEnProceso.getEmail_cnt());
        contacto3ElegidoResultado = contactoEnProceso.getNombre_cnt()+" "+contactoEnProceso.getApellido1_cnt();
    }

    public void limpiarContacto(View view){
        contactoEnProceso.setId_cnt(-1);
        contactoEnProceso.setCargo_cnt("");
        contactoEnProceso.setNombre_cnt("");
        contactoEnProceso.setApellido1_cnt("");
        contactoEnProceso.setApellido2_cnt("");
        contactoEnProceso.setMovil_cnt("");
        contactoEnProceso.setTelefono_cnt("");
        contactoEnProceso.setEmail_cnt("");
    }

    public void recuperarContacto1Actual(View view) {
        contactoEnProceso.setId_cnt(-1);
        contactoEnProceso.setCargo_cnt(personaUser.getContacto1Cargo_prs());
        contactoEnProceso.setNombre_cnt(personaUser.getContacto1Nombre_prs());
        contactoEnProceso.setApellido1_cnt(personaUser.getContacto1Apellido1_prs());
        contactoEnProceso.setApellido2_cnt(personaUser.getContacto1Apellido2_prs());
        contactoEnProceso.setMovil_cnt(personaUser.getContacto1Movil_prs());
        contactoEnProceso.setTelefono_cnt(personaUser.getContacto1Telefono_prs());
        contactoEnProceso.setEmail_cnt(personaUser.getContacto1Email_prs());
    }

    public void recuperarContacto2Actual(View view) {
        contactoEnProceso.setId_cnt(-1);
        contactoEnProceso.setCargo_cnt(personaUser.getContacto2Cargo_prs());
        contactoEnProceso.setNombre_cnt(personaUser.getContacto2Nombre_prs());
        contactoEnProceso.setApellido1_cnt(personaUser.getContacto2Apellido1_prs());
        contactoEnProceso.setApellido2_cnt(personaUser.getContacto2Apellido2_prs());
        contactoEnProceso.setMovil_cnt(personaUser.getContacto2Movil_prs());
        contactoEnProceso.setTelefono_cnt(personaUser.getContacto2Telefono_prs());
        contactoEnProceso.setEmail_cnt(personaUser.getContacto2Email_prs());
    }

    public void recuperarContacto3Actual(View view) {
        contactoEnProceso.setId_cnt(-1);
        contactoEnProceso.setCargo_cnt(personaUser.getContacto3Cargo_prs());
        contactoEnProceso.setNombre_cnt(personaUser.getContacto3Nombre_prs());
        contactoEnProceso.setApellido1_cnt(personaUser.getContacto3Apellido1_prs());
        contactoEnProceso.setApellido2_cnt(personaUser.getContacto3Apellido2_prs());
        contactoEnProceso.setMovil_cnt(personaUser.getContacto3Movil_prs());
        contactoEnProceso.setTelefono_cnt(personaUser.getContacto3Telefono_prs());
        contactoEnProceso.setEmail_cnt(personaUser.getContacto3Email_prs());
    }

    public void mostrarContactoEnProceso (View view) {
        v05_2_nombre_cnt.setText(contactoEnProceso.getNombre_cnt());
        v05_2_apellido1_cnt.setText(contactoEnProceso.getApellido1_cnt());
        v05_2_apellido2_cnt.setText(contactoEnProceso.getApellido2_cnt());
        v05_2_movil_cnt.setText(contactoEnProceso.getMovil_cnt());
        v05_2_telefono_cnt.setText(contactoEnProceso.getTelefono_cnt());
        v05_2_email_cnt.setText(contactoEnProceso.getEmail_cnt());
    }

}
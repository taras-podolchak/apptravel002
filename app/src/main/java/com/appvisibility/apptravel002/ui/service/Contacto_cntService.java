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
import android.util.Log;
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
import com.appvisibility.apptravel002.ui.entities.Evento_eve;
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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Campos de xml
    private AutoCompleteTextView v05_2_contactoElegido;
    private TextView v05_2_contactoElegidoResultado;
    private TextView v05_2_muestraContactoElegido;
    private Spinner v05_2_contactocargo_cnt;
    private EditText v05_2_contactonombre_cnt;
    private EditText v05_2_contactoapellido1_cnt;
    private EditText v05_2_contactoapellido2_cnt;
    private EditText v05_2_contactomovil_cnt;
    private EditText v05_2_contactotelefono_cnt;
    private EditText v05_2_contactoemail_cnt;

    // Entities
    private Persona_prs personaUser;
    private Contacto_cnt contactoEnProceso;
    private Map<Integer, Contacto_cnt> contactos = new HashMap<>();
    private String contactoElegido = "";
    public String contactoElegidoResultado;
    private String contactoid_cnt;
    private String contactocargo_cnt = "";
    private String contactonombre_cnt;
    private String contactoapellido1_cnt;
    private String contactoapellido2_cnt;
    private String contactomovil_cnt;
    private String contactotelefono_cnt;
    private String contactoemail_cnt;

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
                String contactoid_cnt = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                String contactonombre_cnt = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                contactoEnProceso = new Contacto_cnt (Integer.parseInt(contactoid_cnt), contactocargo_cnt, contactonombre_cnt, contactoapellido1_cnt, contactoapellido2_cnt, contactomovil_cnt, contactotelefono_cnt, contactoemail_cnt);
                contactos.put(Integer.parseInt(contactoid_cnt), contactoEnProceso);
                cursor.moveToNext();
            }
            cursor.close();

            List<String> contactosDescripcion = new ArrayList<>();

            Iterator<Integer> it1 = contactos.keySet().iterator();
            while (it1.hasNext()) {
                Integer key = it1.next();
                contactosDescripcion.add(
                    contactos.get(key).getId_cnt() + " - " +
                    contactos.get(key).getContactoNombre_cnt()
                );
            }

            ArrayAdapter<String> arrayAdapter_contacto = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, contactosDescripcion);
            arrayAdapter_contacto.setDropDownViewResource(android.R.layout.simple_list_item_1);

            AlertDialog.Builder modalFormContacto = new AlertDialog.Builder(view.getContext());
            v05_2_contactoElegido = view.findViewById(R.id.v05_2_act_contactoElegido);
            v05_2_contactoElegidoResultado = view.findViewById(R.id.v05_txv_contactoElegidoResultado);
            v05_2_contactocargo_cnt = view.findViewById(R.id.v05_2_spn_ContactoCargo);
            v05_2_contactonombre_cnt = view.findViewById(R.id.v05_2_etx_ContactoNombre);
            v05_2_contactoapellido1_cnt = view.findViewById(R.id.v05_2_etx_ContactoApellido1);
            v05_2_contactoapellido2_cnt = view.findViewById(R.id.v05_2_etx_ContactoApellido2);
            v05_2_contactomovil_cnt = view.findViewById(R.id.v05_2_etx_ContactoMovil);
            v05_2_contactotelefono_cnt = view.findViewById(R.id.v05_2_etx_ContactoTelefono);
            v05_2_contactoemail_cnt = view.findViewById(R.id.v05_2_etx_ContactoEmail);
            v05_2_muestraContactoElegido = view2.findViewById(R.id.v05_txv_muestraContactoElegido);

            v05_2_contactoElegido.setAdapter(arrayAdapter_contacto);

// https://stackoverflow.com/questions/4819813/how-to-get-text-from-autocompletetextview
            v05_2_contactoElegido.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                    v05_2_contactoElegido.setSelection(position);
                    Object item = parent.getItemAtPosition(position);
                    contactoElegido = (String) item;
//                    Toast.makeText(view.getContext(), contactoElegido, Toast.LENGTH_LONG).show();
                    List<String> contactoNuevaArray = Arrays.asList(contactoElegido.split("\\s*\\s*-\\s*"));
                    for (int i=0; i <= contactoNuevaArray.size(); i++){
                        switch (i) {
                            case 0: {
                                contactoid_cnt = contactoNuevaArray.get(i); break;}
                            case 1: {
                                v05_2_contactonombre_cnt.setText(contactoNuevaArray.get(i)); break;}
                            default: {
                                contactoElegido = " ";}
                        }
                    }

                    Uri bbdd = ContactsContract.Data.CONTENT_URI;
                    String projection[] = new String[] {
                            ContactsContract.Data.CONTACT_ID,
                            ContactsContract.Data.MIMETYPE,
                            ContactsContract.Data.DISPLAY_NAME_ALTERNATIVE,
                            ContactsContract.Data.DATA1,
                    };
                    String selectionClause = ContactsContract.Data.CONTACT_ID+ " = ?";
                    String selectionArgs[] = new String[]{contactoid_cnt};
                    String sortOrder = null;
                    Cursor cursor = crearCursor(view, bbdd, projection, selectionClause, selectionArgs, sortOrder);

                    if (cursor.moveToFirst()) {
                        contactonombre_cnt = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Data.DISPLAY_NAME_ALTERNATIVE));
                        if (contactonombre_cnt.contains(",")){
                            contactoNuevaArray = Arrays.asList(contactonombre_cnt.split("\\s*,\\s*"));
                            for (int i=0; i <= contactoNuevaArray.size(); i++){
                                switch (i) {
                                    case 0: {
                                        contactoapellido1_cnt = contactoNuevaArray.get(i); break;}
                                    case 1: {
                                        contactonombre_cnt = contactoNuevaArray.get(i); break;}
                                    default: {
                                        contactoElegido = "Error: Contacto sin identificar";}
                                }
                            }
                        } else {
                            contactoapellido1_cnt = "";
                        }
                        contactos.get(Integer.parseInt(contactoid_cnt)).setContactoNombre_cnt(contactonombre_cnt);
                        v05_2_contactonombre_cnt.setText(contactos.get(Integer.parseInt(contactoid_cnt)).getContactoNombre_cnt());
                        contactos.get(Integer.parseInt(contactoid_cnt)).setContactoApellido1_cnt(contactoapellido1_cnt);
                        v05_2_contactoapellido1_cnt.setText(contactos.get(Integer.parseInt(contactoid_cnt)).getContactoApellido1_cnt());
// https://stackoverflow.com/questions/8805937/retrieving-phone-number-from-contact-id-android
// Permite ver en el log los datos que estamos capturado
/*
                        for(int i=0; i< cursor.getColumnCount(); i++) {
                            Log.i("CONTACTSTAG21", cursor.getColumnName(i) + ": " + cursor.getString(i));
                        }
 */
                    }
                    cursor.close();

                    Uri bbdd2 = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                    String projection2[] = new String[] {
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                            ContactsContract.CommonDataKinds.Phone.DATA,
                    };
                    String selectionClause2 = ContactsContract.CommonDataKinds.Phone.CONTACT_ID+ " = ?";
                    String selectionArgs2[] = new String[]{contactoid_cnt};
                    String sortOrder2 = null;
                    Cursor cursor2 = crearCursor(view, bbdd2, projection2, selectionClause2, selectionArgs2, sortOrder2);

                    cursor2.moveToFirst();
                    while (!cursor2.isAfterLast()) {
//                        String PHONE_REGEX = "\\d{8,10}";
                        contactomovil_cnt = contactos.get(Integer.parseInt(contactoid_cnt)).getContactoMovil_cnt();
                        contactotelefono_cnt = contactos.get(Integer.parseInt(contactoid_cnt)).getContactoTelefono_cnt();
                        if (contactomovil_cnt == null || contactomovil_cnt.equalsIgnoreCase("")) {
                            contactomovil_cnt = cursor2.getString(cursor2.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DATA1)).replaceAll(" ", "");
                            contactos.get(Integer.parseInt(contactoid_cnt)).setContactoMovil_cnt(contactomovil_cnt);
// https://regexr.com/346hf
// https://regex101.com/
/*
                            if (contactomovil_cnt.matches("^+?(\\s)?\\d{2,3}(\\s)?\\d{3}(\\s|-)?\\d{3}(\\s|-)?\\d{3}(\\s|-)?\\d{3}$")) {
                                contactos.get(Integer.parseInt(contactoid_cnt)).setContactoMovil_cnt(contactomovil_cnt);
                            } else {
                                contactomovil_cnt = null;
                            }
 */
                            v05_2_contactomovil_cnt.setText(contactos.get(Integer.parseInt(contactoid_cnt)).getContactoMovil_cnt());
                        } else {
                            if (!(cursor2.getString(cursor2.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DATA1)).replaceAll(" ", "")).equalsIgnoreCase(contactomovil_cnt.replaceAll(" ", "")));{
                                if (contactotelefono_cnt == null || contactotelefono_cnt.equalsIgnoreCase("")) {
                                    contactotelefono_cnt = cursor2.getString(cursor2.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DATA1)).replaceAll(" ", "");
                                    if (!contactotelefono_cnt.equalsIgnoreCase(contactomovil_cnt)) {
                                        contactos.get(Integer.parseInt(contactoid_cnt)).setContactoTelefono_cnt(contactotelefono_cnt);
                                        v05_2_contactotelefono_cnt.setText(contactos.get(Integer.parseInt(contactoid_cnt)).getContactoTelefono_cnt());
                                    }
                                }
                            }
                        }
/*
                        for(int i=0; i< cursor2.getColumnCount(); i++){
                            Log.i("CONTACTSTAG21", cursor2.getColumnName(i) + ": " + cursor2.getString(i));
                        }
 */
                        cursor2.moveToNext();
                    }
                    cursor2.close();

                    Uri bbdd3 = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
                    String projection3[] = new String[] {
                            ContactsContract.CommonDataKinds.Email.CONTACT_ID,
                            ContactsContract.CommonDataKinds.Email.DATA,
                    };
                    String selectionClause3 = ContactsContract.CommonDataKinds.Email.CONTACT_ID+ " = ?";
                    String selectionArgs3[] = new String[]{contactoid_cnt};
                    String sortOrder3 = null;
                    Cursor cursor3 = crearCursor(view, bbdd3, projection3, selectionClause3, selectionArgs3, sortOrder3);

                    cursor3.moveToFirst();
                    while (!cursor3.isAfterLast()) {
                        contactoemail_cnt = contactos.get(Integer.parseInt(contactoid_cnt)).getContactoEmail_cnt();
                        if (contactoemail_cnt == null) {
                            contactoemail_cnt = cursor3.getString(cursor3.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.DATA1)).replaceAll(" ", "");
                            contactos.get(Integer.parseInt(contactoid_cnt)).setContactoEmail_cnt(contactoemail_cnt);
                            v05_2_contactoemail_cnt.setText(contactos.get(Integer.parseInt(contactoid_cnt)).getContactoEmail_cnt());
                        }
/*
                        for(int i=0; i< cursor3.getColumnCount(); i++){
                            Log.i("CONTACTSTAG21", cursor3.getColumnName(i) + ": " + cursor3.getString(i));
                        }
 */
                        cursor3.moveToNext();
                    }
                    cursor3.close();

                    if (contactonombre_cnt != ""){
                        v05_2_contactoElegido.setVisibility(View.GONE);
                    }
                }
            });

            modalFormContacto.setIcon(R.drawable.ico_contact_book);
            modalFormContacto.setTitle("PERSONA DE CONTACTO");

            if (sesionIniciada == view.getResources().getInteger(R.integer.rol_transportecolectivo)) {
                v05_2_contactocargo_cnt.setAdapter(arrayAdapter_prs);
// https://stackoverflow.com/questions/4622517/hide-a-edittext-make-it-visible-by-clicking-a-menu
                v05_2_contactoapellido2_cnt.setVisibility(View.GONE);
                v05_2_contactotelefono_cnt.setVisibility(View.GONE);
                v05_2_contactoemail_cnt.setVisibility(View.GONE);

                modalFormContacto.setPositiveButton("Confirmar", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        contactocargo_cnt = v05_2_contactocargo_cnt.getSelectedItem().toString();
                        if (!contactocargo_cnt.equalsIgnoreCase("RELACION:")) {
                            contactoEnProceso = new Contacto_cnt(Integer.parseInt(contactoid_cnt), contactocargo_cnt, contactonombre_cnt, contactoapellido1_cnt, contactoapellido2_cnt, contactomovil_cnt, contactotelefono_cnt, contactoemail_cnt);
// https://www.youtube.com/watch?v=JB3ETK5mh3c&ab_channel=CodinginFlow
// autocomplete form with contact android youtube
                        } else {
                            contactoEnProceso = new Contacto_cnt(-1, "", "", "", "", "", "", "");
                            Toast.makeText(view.getContext(), "El contacto no se guarda por falta de datos", Toast.LENGTH_LONG).show();
                        }
                    volcarContacto(view);
// https://stackoverflow.com/questions/42397915/how-to-pass-string-from-one-fragment-to-another-in-android
// Permite mostrar en la pantalla padre el resultado de la selección de un contacto de la agenda
                    V_05.newInstance(contactoElegidoResultado, null);
                    V_05.v05_2_muestraContactoElegido.setText(contactoElegidoResultado);
                    }
                });
            } else if (sesionIniciada == view.getResources().getInteger(R.integer.rol_valiente)
                    || sesionIniciada == view.getResources().getInteger(R.integer.rol_alojamiento)
                    || sesionIniciada == view.getResources().getInteger(R.integer.rol_empresas_trekking)){
                v05_2_contactocargo_cnt.setAdapter(arrayAdapter_sum);
                modalFormContacto.setPositiveButton("Confirmar", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        contactocargo_cnt = v05_2_contactocargo_cnt.getSelectedItem().toString();
                        if (!contactocargo_cnt.equalsIgnoreCase("CARGO:")) {
                            contactoEnProceso = new Contacto_cnt(Integer.parseInt(contactoid_cnt), contactocargo_cnt, contactonombre_cnt, contactoapellido1_cnt, contactoapellido2_cnt, contactomovil_cnt, contactotelefono_cnt, contactoemail_cnt);
                        } else {
                            contactoEnProceso = new Contacto_cnt(-1, "", "", "", "", "", "", "");
                            Toast.makeText(view.getContext(), "El contacto no se guarda por falta de datos", Toast.LENGTH_LONG).show();
                        }
                        volcarContacto(view);
                        V_05.newInstance(contactoElegidoResultado, null);
                        V_05.v05_2_muestraContactoElegido.setText(contactoElegidoResultado);
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

    public void volcarContacto(View view){
        personaUser.setContacto1Cargo_prs(contactoEnProceso.getContactoCargo_cnt());
        personaUser.setContacto1Nombre_prs(contactoEnProceso.getContactoNombre_cnt());
        personaUser.setContacto1Apellido1_prs(contactoEnProceso.getContactoApellido1_cnt());
        personaUser.setContacto1Apellido2_prs(contactoEnProceso.getContactoApellido2_cnt());
        personaUser.setContacto1Movil_prs(contactoEnProceso.getContactoMovil_cnt());
        personaUser.setContacto1Telefono_prs(contactoEnProceso.getContactoTelefono_cnt());
        personaUser.setContacto1Email_prs(contactoEnProceso.getContactoEmail_cnt());
        contactoElegidoResultado = contactoEnProceso.getContactoNombre_cnt()+" "+contactoEnProceso.getContactoApellido1_cnt();
//        Toast.makeText(view.getContext(), contactoElegidoResultado, Toast.LENGTH_LONG).show();
    }
}
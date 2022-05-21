package com.appvisibility.apptravel002.ui.controller;

        import android.app.AlertDialog;
        import android.app.Dialog;
        import android.app.DialogFragment;
        import android.content.DialogInterface;
        import android.os.Bundle;

        import com.appvisibility.apptravel002.MainActivity;
        import com.appvisibility.apptravel002.R;

public class V_05_2 extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
        .setIcon(R.drawable.ico_coche_naranja)
        .setTitle("¿Quieres solicitar una plaza en este coche?")
        .setPositiveButton("Aceptar",
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
//                    (V_05_2.getActivity()).accionAceptar();
                }
            }
        )
        //PARA CREAR UN MENSAJE DE CONFIRMACIÓN
        //ES TAN SENCILLO COMO IMPLEMENTAR EL BOTÓN
        //DE NEGACIÓN Y CAPTURAR SU RESPUESTA EN LA ACTIVIDAD.
        .setNegativeButton("Cancelar",
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
//                    ((MainActivity) getActivity()).accionCancelar();
                }
            }
        )
        //POR ÚLTIMO, CREAMOS EL CUADRO
        .create();
    }
}

package com.appvisibility.apptravel002;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.appvisibility.apptravel002.databinding.ActivityMainValBinding;
import com.appvisibility.apptravel002.ui.entities.Persona_prs;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity_val extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainValBinding binding;
    private FirebaseAuth fba = FirebaseAuth.getInstance();
    private FirebaseFirestore fbf = FirebaseFirestore.getInstance();
    private Persona_prs personaTipo;
    public static int sesionIniciada = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(R.integer.rol_no_iniciada);

        binding = ActivityMainValBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMainActivityVal.toolbar);
        binding.appBarMainActivityVal.fab.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://amigosmontanawo.eboe62.com/preguntas-frecuentes-faq/"));
            startActivity(intent);
//                webView.loadUrl("https://amigosmontanawo.eboe62.com/preguntas-frecuentes-faq/");
        });

        DrawerLayout drawer = binding.drawerLayoutVal.findViewById(R.id.drawer_layout_val);
        NavigationView navigationView = binding.navViewVal.findViewById(R.id.nav_view_val);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_v01, R.id.nav_v02, R.id.nav_v03, R.id.nav_v04, R.id.nav_v05, R.id.nav_v05_1, R.id.nav_v06)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_activity_val);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        NavigationView mNavigationView = findViewById(R.id.nav_view_val);
        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(this);
        }
    }//Fin de constructor

    //Generamos un bundle con los datos del Usuario activo
    public Bundle getUser() {
        Bundle bundelPersonaUser = new Bundle();
        bundelPersonaUser.putSerializable("User", personaTipo);
        return bundelPersonaUser;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_val, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_activity_val);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //TODO para tener la cesion abierta
    @Override
    protected void onStart() {
        super.onStart();
        if (fba.getCurrentUser() != null) {

            //obtenemos la Uid del registro de la bbdd FirebaseAuth
            FirebaseUser user = fba.getCurrentUser();
            String uid = user.getUid();

            //buscamos en FirebaseFirestore el documento con esa Uid
            DocumentReference docRef = fbf.collection("persona_prs").document(uid);
            docRef.get().addOnCompleteListener(task1 -> {
                if (task1.isSuccessful()) {
                    DocumentSnapshot document = task1.getResult();
                    if (document.exists()) {

                        //recuperamos la persona
                        personaTipo = document.toObject(Persona_prs.class);

                        //si es valiente
                        if (personaTipo.getUsuariotipo_prs() == getResources().getInteger(R.integer.rol_valiente)) {
                            sesionIniciada = personaTipo.getUsuariotipo_prs();
                            setTitle(personaTipo.getEmail_prs());
                        }
                        //si es colaborador
                        else if (personaTipo.getUsuariotipo_prs() == getResources().getInteger(R.integer.rol_colaborador)) {
                            sesionIniciada = personaTipo.getUsuariotipo_prs();
                            startActivity(new Intent(this, MainActivity_col.class).putExtra("abrirEnMainActivity_col", 0));
                        }
                        //si es administrador
                        else if (personaTipo.getUsuariotipo_prs() == getResources().getInteger(R.integer.rol_administrador)) {
                            sesionIniciada = personaTipo.getUsuariotipo_prs();
                            startActivity(new Intent(this, MainActivity_adm.class).putExtra("abrirEnMainActivity_adm", 0));
                        }
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task1.getException());
                }
            });
        }
    }

    //TODO el menuImems onClicListener
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.dudas_sobre_logistica) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://amigosmontanawo.eboe62.com/dudas-sobre-la-logistica/"));
            startActivity(intent);
        }
        if (id == R.id.dudas_sobre_montannismo) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://amigosmontanawo.eboe62.com/dudas-sobre-montanismo/"));
            startActivity(intent);
        }
        if (id == R.id.dudas_sobre_club) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://amigosmontanawo.eboe62.com/dudas-sobre-el-club/"));
            startActivity(intent);
        }
        if (id == R.id.licencia_federativa) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://amigosmontanawo.eboe62.com/2020/10/01/licencia-federativa/"));
            startActivity(intent);
        }
        if (id == R.id.equipamiento) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://amigosmontanawo.eboe62.com/2020/10/05/496/"));
            startActivity(intent);
        }
        if (id == R.id.pagina_web) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://amigosmontanawo.eboe62.com/"));
            startActivity(intent);
        }
        if (id == R.id.colaborador) {
            if (sesionIniciada == getResources().getInteger(R.integer.rol_no_iniciada)) {
                startActivity(new Intent(this, MainActivity_col.class).putExtra("abrirEnMainActivity_col", getResources().getInteger(R.integer.accion_a_v04)));
                Toast.makeText(this, "Inicie la sesion por favor", Toast.LENGTH_SHORT).show();
            } else if (sesionIniciada == getResources().getInteger(R.integer.rol_colaborador)) {
                startActivity(new Intent(this, MainActivity_col.class).putExtra("abrirEnMainActivity_col", getResources().getInteger(R.integer.accion_a_v01)));
            } else {
                Toast.makeText(this, "No tienes permisos", Toast.LENGTH_SHORT).show();
            }
        }
        if (id == R.id.administrador) {
            if (sesionIniciada == getResources().getInteger(R.integer.rol_no_iniciada)) {
                startActivity(new Intent(this, MainActivity_adm.class).putExtra("abrirEnMainActivity_adm", getResources().getInteger(R.integer.accion_a_v04)));
                Toast.makeText(this, "Inicie la sesion por favor", Toast.LENGTH_SHORT).show();
            } else if (sesionIniciada == getResources().getInteger(R.integer.rol_administrador)) {
                startActivity(new Intent(this, MainActivity_adm.class).putExtra("abrirEnMainActivity_adm", getResources().getInteger(R.integer.accion_a_v01)));
            } else {
                Toast.makeText(this, "No tienes permisos", Toast.LENGTH_SHORT).show();
            }
        }
        if (id == R.id.cerrar_session) {
            Toast.makeText(getApplicationContext(), "Cerramos la sesion", Toast.LENGTH_SHORT).show();
            fba.signOut();
            sesionIniciada = getResources().getInteger(R.integer.rol_no_iniciada);
            startActivity(new Intent(this, MainActivity_val.class));
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout_val);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

// https://stackoverflow.com/questions/5448653/how-to-implement-onbackpressed-in-fragments
// https://stackoverflow.com/questions/35708453/how-to-automatically-click-a-button-in-android-after-a-5-second-delay
// https://stackoverflow.com/questions/41981546/cant-resolve-method-showandroid-support-v4-app-fragmentmanager-java-lang-stri
// Cuando queremos mostrar en una activity: getActivity().getFragmentManager()
// Cuando queremos mostrar en un fragmento: getFragmentManager()
    /*
    public void onBackPressed() {
        V_03.v03_atras.performClick();
        if (V_03_2_modal.solicitudRealizada) {
            DialogFragment dialogFragment = OnBackPressed.newInstance("Has solicitado plaza en el coche de: ", V_03_2_modal.personaOferente.getApodo_prs().toUpperCase() + "\n");
            dialogFragment.show(getFragmentManager(), "tag");
//            dialogFragment.show(getActivity().getFragmentManager(),"tag");
            super.onBackPressed();
        }
    }*/

}
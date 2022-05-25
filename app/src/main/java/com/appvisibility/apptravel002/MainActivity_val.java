package com.appvisibility.apptravel002;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.appvisibility.apptravel002.databinding.ActivityMainValBinding;
import com.appvisibility.apptravel002.ui.entities.Persona_prs;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity_val extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainValBinding binding;
    private FirebaseAuth fba = FirebaseAuth.getInstance();
    private FirebaseFirestore fbf = FirebaseFirestore.getInstance();
    private Persona_prs persona;
    public static int sesionIniciada = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                R.id.nav_v01, R.id.nav_v02, R.id.nav_v03, R.id.nav_v04, R.id.nav_v05, R.id.nav_v06)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_activity_val);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        NavigationView mNavigationView = findViewById(R.id.nav_view_val);
        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(this);
        }

    }   //fin de constructor

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
                        persona = document.toObject(Persona_prs.class);

                        //si es valiente
                        if (persona.getUsuariotipo_prs() == 1) {
                            sesionIniciada = persona.getUsuariotipo_prs();
                        }
                        //si es colaborador
                        else if (persona.getUsuariotipo_prs() == 2) {
                            sesionIniciada = persona.getUsuariotipo_prs();
                            startActivity(new Intent(this, MainActivity_col.class).putExtra("abrirEnMainActivity_col", 0));
                        }
                        //si es administrador
                        else if (persona.getUsuariotipo_prs() == 3) {
                            sesionIniciada = persona.getUsuariotipo_prs();
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
            if (sesionIniciada == 0) {
                startActivity(new Intent(this, MainActivity_col.class).putExtra("abrirEnMainActivity_col", -1));
                Toast.makeText(this, "Inicie la sesion por favor", Toast.LENGTH_LONG).show();
            } else if (sesionIniciada == 2) {
                startActivity(new Intent(this, MainActivity_col.class).putExtra("abrirEnMainActivity_col", 0));
            } else {
                Toast.makeText(this, "No tienes permisos", Toast.LENGTH_LONG).show();
            }
        }
        if (id == R.id.administrador) {
            if (sesionIniciada == 0) {
                startActivity(new Intent(this, MainActivity_adm.class).putExtra("abrirEnMainActivity_adm", -1));
                Toast.makeText(this, "Inicie la sesion por favor", Toast.LENGTH_LONG).show();
            } else if (sesionIniciada == 2) {
                startActivity(new Intent(this, MainActivity_adm.class).putExtra("abrirEnMainActivity_adm", 0));
            } else {
                Toast.makeText(this, "No tienes permisos", Toast.LENGTH_LONG).show();
            }
        }
        if (id == R.id.cerrar_session) {
            Toast.makeText(getApplicationContext(), "Cerramos la sesion", Toast.LENGTH_SHORT).show();
            fba.signOut();
            sesionIniciada = 0;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout_val);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
package com.appvisibility.apptravel002;

import static com.appvisibility.apptravel002.MainActivity_val.sesionIniciada;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

import com.appvisibility.apptravel002.databinding.ActivityMainColBinding;
import com.appvisibility.apptravel002.ui.entities.Evento_eve;
import com.appvisibility.apptravel002.ui.entities.Persona_prs;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity_col extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainColBinding binding;
    private FirebaseAuth fba = FirebaseAuth.getInstance();
    private NavController navController;

    // Entities
    public static Evento_eve eventoEnProceso;
    private Persona_prs personaUser = new Persona_prs();
    private Bundle id_eve_bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainColBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMainActivityCol.toolbar);
        binding.appBarMainActivityCol.fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show());

        DrawerLayout drawer = binding.drawerLayoutCol.findViewById(R.id.drawer_layout_col);
        NavigationView navigationView = binding.navViewCol.findViewById(R.id.nav_view_col);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_v01, R.id.nav_v02, R.id.nav_v03, R.id.nav_v04, R.id.nav_v05, R.id.nav_v06)
                .setOpenableLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_activity_col);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        NavigationView mNavigationView = findViewById(R.id.nav_view_col);
        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(this);
        }//el codigo intocable

        Intent intent = getIntent();
        //Recuperamos el Evento
        int accion = intent.getIntExtra("accion_col", 0);
        eventoEnProceso = (Evento_eve) intent.getSerializableExtra("eventoParaV_05");
        //Recuperamos el Usuario
        personaUser = (Persona_prs) intent.getSerializableExtra("User");

        id_eve_bundle = new Bundle();
        id_eve_bundle.putInt("accesoParaV_04", accion);
        id_eve_bundle.putSerializable("eventoParaV_05", eventoEnProceso);

        int id_prs = (personaUser == null)? 0 : personaUser.getId_prs();

        if (accion == -1) {
            navController.navigate(R.id.nav_v04, id_eve_bundle);
        } else if (accion > 0 && id_prs == -1) {
            navController.navigate(R.id.nav_identidad, id_eve_bundle);
        } else if (accion > 0 && id_prs > 0) {
            navController.navigate(R.id.nav_v05, id_eve_bundle);
        } else {
            navController.navigate(R.id.nav_v01);
        }
    }//Fin de constructor

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activiry_col, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_activity_col);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //TODO el menuImems onClicListener
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mi_cuenta) {
            if (sesionIniciada == getResources().getInteger(R.integer.rol_no_iniciada)) {
                Bundle id_eve_bundle_put = new Bundle();
                id_eve_bundle_put.putInt("accesoParaV_04", 0);
                navController.navigate(R.id.nav_v04, id_eve_bundle_put);
            } else {
                FirebaseUser user = fba.getCurrentUser();
                String uid = user.getUid();
                Bundle bundle = new Bundle();
                bundle.putString("idPersona", uid);
                navController.navigate(R.id.ACV_my_account, bundle);
            }
        }
        if (id == R.id.dudas_sobre_logistica)
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://amigosmontanawo.eboe62.com/dudas-sobre-la-logistica/")));

        if (id == R.id.dudas_sobre_montannismo)
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://amigosmontanawo.eboe62.com/dudas-sobre-montanismo/")));

        if (id == R.id.dudas_sobre_club)
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://amigosmontanawo.eboe62.com/dudas-sobre-el-club/")));

        if (id == R.id.licencia_federativa)
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://amigosmontanawo.eboe62.com/2020/10/01/licencia-federativa/")));

        if (id == R.id.equipamiento)
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://amigosmontanawo.eboe62.com/2020/10/05/496/")));

        if (id == R.id.pagina_web)
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://amigosmontanawo.eboe62.com/")));
        if (id == R.id.colaborador) {
            if (sesionIniciada == getResources().getInteger(R.integer.rol_no_iniciada)) {
                startActivity(new Intent(this, MainActivity_col.class).putExtra("accion_col", getResources().getInteger(R.integer.accion_a_v04)));
                Toast.makeText(this, "Inicie la sesion por favor", Toast.LENGTH_SHORT).show();
            } else if (sesionIniciada == getResources().getInteger(R.integer.rol_colaborador)) {
                startActivity(new Intent(this, MainActivity_col.class).putExtra("accion_col", getResources().getInteger(R.integer.accion_a_v01)));
            } else {
                Toast.makeText(this, "No tienes permisos", Toast.LENGTH_SHORT).show();
            }
        }
        if (id == R.id.administrador) {
            if (sesionIniciada == getResources().getInteger(R.integer.rol_no_iniciada)) {
                startActivity(new Intent(this, MainActivity_adm.class).putExtra("accion_adm", getResources().getInteger(R.integer.accion_a_v04)));
                Toast.makeText(this, "Inicie la sesion por favor", Toast.LENGTH_SHORT).show();
            } else if (sesionIniciada == getResources().getInteger(R.integer.rol_administrador)) {
                startActivity(new Intent(this, MainActivity_adm.class).putExtra("accion_adm", getResources().getInteger(R.integer.accion_a_v01)));
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
        DrawerLayout drawer = findViewById(R.id.drawer_layout_col);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
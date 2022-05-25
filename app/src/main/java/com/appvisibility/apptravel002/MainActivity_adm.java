package com.appvisibility.apptravel002;

import static com.appvisibility.apptravel002.MainActivity_val.sesionIniciada;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.appvisibility.apptravel002.databinding.ActivityMainAdmBinding;
import com.appvisibility.apptravel002.ui.controller.V_01;
import com.appvisibility.apptravel002.ui.controller.V_04;
import com.appvisibility.apptravel002.ui.controller.V_05;
import com.appvisibility.apptravel002.ui.entities.Evento_eve;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity_adm extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainAdmBinding binding;
    private FirebaseAuth fba = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainAdmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMainActivityAdm.toolbar);
        binding.appBarMainActivityAdm.fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        DrawerLayout drawer = binding.drawerLayoutAdm.findViewById(R.id.drawer_layout_adm);
        NavigationView navigationView = binding.navViewAdm.findViewById(R.id.nav_view_adm);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_v01, R.id.nav_v02, R.id.nav_v03, R.id.nav_v04, R.id.nav_v05, R.id.nav_v06)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_activity_adm);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        NavigationView mNavigationView = findViewById(R.id.nav_view_adm);
        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(this);
        }//el codigo intocable

        //recuperamos el bundle
        Intent intent = getIntent();
        int acceso = intent.getIntExtra("abrirEnMainActivity_adm", 0);
        Evento_eve evento_eve_get = (Evento_eve) intent.getSerializableExtra("eventoParaV_05");

        Bundle id_eve_bundle_put = new Bundle();
        id_eve_bundle_put.putInt("accesoParaV_04", acceso);
        id_eve_bundle_put.putSerializable("eventoParaV_05",evento_eve_get);

        if (acceso == -1) {
            navController.navigate(R.id.nav_v04, id_eve_bundle_put);
        } else if (acceso == 0) {
            navController.navigate(R.id.nav_v01);
        } else {
            navController.navigate(R.id.nav_v05, id_eve_bundle_put);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_adm, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_activity_adm);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
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
                Intent intent = new Intent(this, MainActivity_col.class);
                intent.putExtra("abrirEnMainActivity_col", -1);
                startActivity(intent);
                Toast.makeText(this, "Inicie la sesion por favor", Toast.LENGTH_LONG).show();
            } else if (sesionIniciada == 2) {
                Intent intent = new Intent(this, MainActivity_col.class);
                intent.putExtra("abrirEnMainActivity_col", 0);
                startActivity(intent);
            } else {
                Toast.makeText(this, "No tienes permisos", Toast.LENGTH_LONG).show();
            }
        }
        if (id == R.id.administrador) {
            if (sesionIniciada == 0) {
                Intent intent = new Intent(this, MainActivity_adm.class);
                intent.putExtra("abrirEnMainActivity_adm", -1);
                startActivity(intent);
                Toast.makeText(this, "Inicie la sesion por favor", Toast.LENGTH_LONG).show();
            } else if (sesionIniciada == 3) {
                Intent intent = new Intent(this, MainActivity_adm.class);
                intent.putExtra("abrirEnMainActivity_adm", 0);
                startActivity(intent);
            } else {
                Toast.makeText(this, "No tienes permisos", Toast.LENGTH_LONG).show();
            }
        }
        if (id == R.id.cerrar_session) {
            Toast.makeText(getApplicationContext(), "Cerramos la sesion", Toast.LENGTH_SHORT).show();
            fba.signOut();
            sesionIniciada = 0;
            Intent intent = new Intent(this, MainActivity_val.class);
            startActivity(intent);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout_adm);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
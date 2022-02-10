package com.appvisibility.apptravel002;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.RadioButton;
import android.widget.Toast;
import com.appvisibility.apptravel002.databinding.ActivityMainBinding;
import com.appvisibility.apptravel002.ui.valiente.v_01.V_01;
import com.google.android.material.navigation.NavigationView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Email = new Intent(Intent.ACTION_SEND);
                Email.setType("text/email");
                Email.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{"developeremail@gmail.com"});  //developer 's email
                Email.putExtra(Intent.EXTRA_SUBJECT,
                        "Add your Subject"); // Email 's Subject
                Email.putExtra(Intent.EXTRA_TEXT, "Dear Developer Name," + "");  //Email 's Greeting text
                startActivity(Intent.createChooser(Email, "Send Feedback:"));
            }

        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_inicio_v01, R.id.nav_v02,R.id.nav_v03,R.id.nav_v04,R.id.nav_v05,R.id.nav_v06)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);



    }   //fin de constructor

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //V_01

    public void onRadioButtonClicked(View view) {   // metodo para v_01 chequear el contenedor
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.v01_radioButton_exurciones_de_un_dia:
                if (checked)
                    Toast.makeText(getApplicationContext(), "v01_radioButton_exurciones_de_un_dia", Toast.LENGTH_SHORT).show();
                V_01.setResultado(1);
                break;
            case R.id.v01_radioButton_fin_de_semana:
                if (checked)
                    Toast.makeText(getApplicationContext(), "v01_radioButton_fin_de_semana", Toast.LENGTH_SHORT).show();
                V_01.setResultado(2);
                break;
            case R.id.v01_radioButton_aventuras_mas_largas:
                if (checked)
                    Toast.makeText(getApplicationContext(), "v01_radioButton_aventuras_mas_largas", Toast.LENGTH_SHORT).show();
                V_01.setResultado(3);
                break;
        }
    }

}
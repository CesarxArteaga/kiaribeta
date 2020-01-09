package com.moviles.kiari;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;



import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;


public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Toolbar navbar_home = findViewById(R.id.nav_home);
        setSupportActionBar(navbar_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menu_options = getMenuInflater();
        menu_options.inflate(R.menu.menu_options,menu);
        return true;
    }
}

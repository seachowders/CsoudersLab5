package com.souders.christian.csouderslab5;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MyMainFragment.UpdateListener {

    String time = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MyMainFragment mainFrag = new MyMainFragment();
        if(!getResources().getBoolean(R.bool.dual_pane))
           {

            getSupportFragmentManager().beginTransaction().add(R.id.PortHolder, mainFrag).commit();
            }
        else
        {
            getSupportFragmentManager().popBackStack(0,getFragmentManager().POP_BACK_STACK_INCLUSIVE) ;
            if(savedInstanceState != null)
            {
                ((DetailFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.DetailHolder)).setText(savedInstanceState.getString("time"));
            }
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(), "Lab 5, Winter 2016, Christian Souders", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(String string) {
        time = string;
        if(getResources().getBoolean(R.bool.dual_pane))
            ((DetailFragment)getSupportFragmentManager().findFragmentById(R.id.DetailHolder)).setText(string);
        else
        {
            DetailFragment frag = (DetailFragment)getSupportFragmentManager().findFragmentByTag("DETAIL_FRAGMENT");
            if(frag == null)
            {
                Log.i("MainActivity", "Fragment was null.");
                frag = new DetailFragment();
            }
            else
            {
                Log.i("MainActivity", "Fragment was not null.");
            }
            android.support.v4.app.FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putString("detail", string);
            frag.setArguments(bundle);
            trans.replace(R.id.PortHolder, frag, "DETAIL_FRAGMENT");
            trans.addToBackStack(null);
            trans.commit();


        }

    }

    @Override
    public void onBackPressed()
    {
        if(getSupportFragmentManager().getBackStackEntryCount()>0)
        {
            getSupportFragmentManager().popBackStack();
        }
        else
            super.onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        outState.putString("time", time);
        super.onSaveInstanceState(outState);
    }
}

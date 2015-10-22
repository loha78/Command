package com.projectapp.command;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    static FragmentManager manager;
    MenuCuisineFragment menuCuisineFragment = new MenuCuisineFragment();
    MenuCommandeFragment menuCommandeFragment = new MenuCommandeFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Drawable mainBg = findViewById(R.id.main_layout).getBackground();
        mainBg.setAlpha(100);
        manager = getFragmentManager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart(){
        super.onStart();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.mainFrameLayout, menuCommandeFragment);

        transaction.commit();


    }

    public void alternerFragmentCuisineCommande(View view){
        if(view.getId()== R.id.buttonCuisine) {
            manageFragChanges(menuCuisineFragment, R.id.mainFrameLayout);
        }
        else {
            manageFragChanges(menuCommandeFragment, R.id.mainFrameLayout);
        }
    }


    public void manageFragChanges(Fragment frag, int layout){
        FragmentTransaction transaction = manager.beginTransaction();
        if(frag.isAdded()){
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            transaction.remove(frag);
        }
        else{
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.add(layout, frag);
        }
        transaction.commit();
    }

}

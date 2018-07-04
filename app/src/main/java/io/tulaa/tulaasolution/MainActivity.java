package io.tulaa.tulaasolution;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private android.support.v4.app.Fragment pascalsFragment;
    private android.support.v4.app.Fragment tripletFragment;
    private android.support.v4.app.Fragment countTripletSmallerSumFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_count:
                     showFragment(countTripletSmallerSumFragment);
                     hideFragment(tripletFragment);
                     hideFragment(pascalsFragment);
                    return true;
                case R.id.navigation_triplets:
                    showFragment(tripletFragment);
                     hideFragment(pascalsFragment);
                     hideFragment(countTripletSmallerSumFragment);
                    return true;
                case R.id.navigation_pascal:
                    showFragment(pascalsFragment);
                     hideFragment(tripletFragment);
                     hideFragment(countTripletSmallerSumFragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pascalsFragment=(android.support.v4.app.Fragment) getSupportFragmentManager().findFragmentById(R.id.pascalsfrag);
        tripletFragment=(android.support.v4.app.Fragment) getSupportFragmentManager().findFragmentById(R.id.tripletfrag);
        countTripletSmallerSumFragment=(android.support.v4.app.Fragment) getSupportFragmentManager().findFragmentById(R.id.tripletsmallerfrag);

        hideFragment(tripletFragment);
        hideFragment(pascalsFragment);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

     public void showFragment(android.support.v4.app.Fragment fragment){
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .show(fragment)
                .commit();
    }

    public void hideFragment(android.support.v4.app.Fragment fragment){
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .hide(fragment)
                .commit();
    }

}

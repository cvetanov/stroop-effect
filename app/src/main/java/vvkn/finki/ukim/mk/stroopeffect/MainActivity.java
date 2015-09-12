package vvkn.finki.ukim.mk.stroopeffect;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import vvkn.finki.ukim.mk.stroopeffect.fragments.HomeFragment;
import vvkn.finki.ukim.mk.stroopeffect.fragments.SimulationFragment;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "SE:MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initHomeFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void initHomeFragment()
    {
        Fragment home = new HomeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container, home);
        transaction.commit();
    }
    // Method for button onClick
    public void initSimulationFragment(View v)
    {
        Log.d(TAG, "Initializing stroop effect simulation fragment.");
        Fragment test = new SimulationFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(SimulationFragment.SIMULATION_TYPE, 1); // put type instead of 1
        test.setArguments(arguments);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.container, test);
        //transaction.addToBackStack(null);     - back button returns previous fragment
        transaction.commit();
    }
}

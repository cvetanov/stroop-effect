package mk.ukim.finki.vvkn.stroopeffect;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mk.ukim.finki.vvkn.stroopeffect.db.ResultsDao;
import mk.ukim.finki.vvkn.stroopeffect.fragments.HomeFragment;
import mk.ukim.finki.vvkn.stroopeffect.fragments.ResultsFragment;
import mk.ukim.finki.vvkn.stroopeffect.fragments.SimulationFragment;
import mk.ukim.finki.vvkn.stroopeffect.models.Result;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer player;
    private ResultsDao dao;
    private List<Result> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = MediaPlayer.create(getApplicationContext(), R.raw.background_music);
        player.setLooping(true);
        dao = new ResultsDao(getApplicationContext());
        results = new ArrayList<>();
        initHomeFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        player.start();
        try {
            dao.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.pause();
        dao.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.stop();
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

    public void startHomeFragment()
    {
        Fragment home = new HomeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, home);
        transaction.commit();
    }

    public void startSimulationFragment(String gender)
    {
        Fragment simulateTest = new SimulationFragment();

        Bundle arguments = new Bundle();
        arguments.putString(SimulationFragment.TESTER_GENDER, gender);
        simulateTest.setArguments(arguments);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, simulateTest);
        transaction.commit();
    }

    public void startResultsFragment()
    {
        readResultsFromDatabase();

        Fragment resultsFragment = new ResultsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.container, resultsFragment);
        transaction.commit();
    }

    private void readResultsFromDatabase()
    {
        results = dao.getAllResults();
    }

    public List<Result> getResults()
    {
        return results;
    }

    public void insertResultIntoDatabase(Result result)
    {
        dao.insert(result);
    }
}

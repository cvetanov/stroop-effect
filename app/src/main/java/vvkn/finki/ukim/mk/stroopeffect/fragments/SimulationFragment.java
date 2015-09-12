package vvkn.finki.ukim.mk.stroopeffect.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vvkn.finki.ukim.mk.stroopeffect.R;

/**
 * Created by gocelinho on 9/12/15.
 */
public class SimulationFragment extends Fragment {
    public static final String TAG = "SE:TestFragment";
    public static final String SIMULATION_TYPE = "congruent; incongruent; under-pressure";
    public static final int STROOP_EFFECT_CONGRUENT = 0;
    public static final int STROOP_EFFECT_INCONGRUENT = 2;
    public static final int STROOP_EFFECT_INCOGRUENT_TIMER = 2;
    private int mSimulationType;

    public SimulationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mSimulationType = getArguments().getInt(SIMULATION_TYPE);
        Log.d(TAG, "type: " + mSimulationType);
        return inflater.inflate(R.layout.fragment_simulation, container, false);
    }
}

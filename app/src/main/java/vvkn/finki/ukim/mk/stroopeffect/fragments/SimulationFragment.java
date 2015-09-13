package vvkn.finki.ukim.mk.stroopeffect.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vvkn.finki.ukim.mk.stroopeffect.R;
import vvkn.finki.ukim.mk.stroopeffect.models.Result;

public class SimulationFragment extends Fragment {
    public static final String TAG = "SE:TestFragment";
    public static final String SIMULATION_TYPE = "congruent; incongruent; under-pressure";
    public static final String TESTER_GENDER = "male or female";
    public static final int STROOP_EFFECT_CONGRUENT = 0;
    public static final int STROOP_EFFECT_INCONGRUENT = 1;
    public static final int STROOP_EFFECT_INCOGRUENT_TIMER = 2;
    private int mSimulationType;
    private Result currentResult;

    public SimulationFragment() {
        currentResult = new Result();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflate depending on type\
        mSimulationType = getArguments().getInt(SIMULATION_TYPE);

        View view = inflater.inflate(R.layout.fragment_simulation, container, false);

        Log.d(TAG, "type: " + mSimulationType);
        Log.d(TAG, "gender: " + getArguments().getString(TESTER_GENDER));

        // write result to db
        //((MainActivity)getActivity()).getDao().insert(currentResult);

        return view;
    }
}

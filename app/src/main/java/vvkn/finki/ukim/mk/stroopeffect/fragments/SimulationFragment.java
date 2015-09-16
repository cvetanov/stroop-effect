package vvkn.finki.ukim.mk.stroopeffect.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import vvkn.finki.ukim.mk.stroopeffect.MainActivity;
import vvkn.finki.ukim.mk.stroopeffect.R;
import vvkn.finki.ukim.mk.stroopeffect.models.Result;

public class SimulationFragment extends Fragment {
    public static final String TAG = "SE:TestFragment";
    public static final String SIMULATION_TYPE = "congruent; incongruent; under-pressure";
    public static final String TESTER_GENDER = "male or female";
    public static final int STROOP_EFFECT_CONGRUENT = 0;
    public static final int STROOP_EFFECT_INCONGRUENT = 1;
    public static final int STROOP_EFFECT_INCONGRUENT_TIMER = 2;
    public static final int MAX_SIMULATIONS = 5;

    private final Random random;
    private static final int [] COLOR_FIELDS = { R.color.color_option_1, R.color.color_option_2,
                                                R.color.color_option_3, R.color.color_option_4,
                                                R.color.color_option_5, R.color.color_option_6,
                                                R.color.color_option_7 };
    private static final String [] COLOR_NAMES = { "Black", "Red", "Green", "Blue", "Yellow", "Pink", "Cyan" };

    private int mSimulationType;
    private int mCurrentSimulationNumber;
    private int mTotalTries;

    private Result currentResult;

    private ImageView imgViewMain;
    private ImageView imgViewOption1;
    private ImageView imgViewOption2;
    private ImageView imgViewOption3;
    private ImageView imgViewOption4;
    private TextView txtViewOption1;
    private TextView txtViewOption2;
    private TextView txtViewOption3;
    private TextView txtViewOption4;
    private int mCorrectAnswer;

    public SimulationFragment() {
        currentResult = new Result();
        random = new Random();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflate depending on type\
        mSimulationType = getArguments().getInt(SIMULATION_TYPE);
        mCurrentSimulationNumber = 0;
        mTotalTries = 0;
        mCorrectAnswer = -1;

        Log.d(TAG, "type: " + mSimulationType);
        Log.d(TAG, "gender: " + getArguments().getString(TESTER_GENDER));
        currentResult.setGender(getArguments().getString(TESTER_GENDER));

        View view = inflater.inflate(R.layout.fragment_simulation, container, false);

        imgViewMain = (ImageView)view.findViewById(R.id.simulation_fragment_image_view_main);
        imgViewOption1 = (ImageView)view.findViewById(R.id.simulation_fragment_image_view_option1);
        imgViewOption2 = (ImageView)view.findViewById(R.id.simulation_fragment_image_view_option2);
        imgViewOption3 = (ImageView)view.findViewById(R.id.simulation_fragment_image_view_option3);
        imgViewOption4 = (ImageView)view.findViewById(R.id.simulation_fragment_image_view_option4);
        txtViewOption1 = (TextView)view.findViewById(R.id.simulation_fragment_text_view_option1);
        txtViewOption2 = (TextView)view.findViewById(R.id.simulation_fragment_text_view_option2);
        txtViewOption3 = (TextView)view.findViewById(R.id.simulation_fragment_text_view_option3);
        txtViewOption4 = (TextView)view.findViewById(R.id.simulation_fragment_text_view_option4);

        // start stopwatch

        simulate();

        imgViewOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processClick(1);
            }
        });

        imgViewOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processClick(2);
            }
        });

        imgViewOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processClick(3);
            }
        });

        imgViewOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processClick(4);
            }
        });

        // TODO: write result to db
        //((MainActivity)getActivity()).getDao().insert(currentResult);

        return view;
    }

    private void processClick(int option)
    {
        mTotalTries++;
        Log.d(TAG, "totalTries incremented: " + mTotalTries);
        if (mCorrectAnswer == option) {
            mCurrentSimulationNumber++;
            Log.d(TAG, "currentSimulation incremented: " + mCurrentSimulationNumber);
            if (mCurrentSimulationNumber == MAX_SIMULATIONS)
            {
                // TODO:
                //currentResult.setElapsedTime(mSimulationType, elapsedFromStopWatch);
                // restart stopwatch
                currentResult.setErrorPercentage(mSimulationType, 1.0 * MAX_SIMULATIONS / mTotalTries);
                if (mSimulationType == STROOP_EFFECT_INCONGRUENT_TIMER) {
                    Log.d(TAG, "Simulation finished");
                    Toast.makeText(getActivity().getApplicationContext(), "Thanks for participating", Toast.LENGTH_SHORT).show();
                    ((MainActivity)getActivity()).startHomeFragment();
                }
                mSimulationType++;
                Log.d(TAG, "simulationType incremented: " + mSimulationType);
                mCurrentSimulationNumber = 0;
                mTotalTries = 0;
            }
            // TODO: different simulations depending on simulation type
            simulate();
        }
        else
        {
            Toast.makeText(getActivity().getApplicationContext(), "Wrong answer", Toast.LENGTH_SHORT).show();
        }
    }

    private void simulate()
    {
        int correctColorId = random.nextInt(COLOR_FIELDS.length);
        int correctImageView = random.nextInt(4) + 1;
        mCorrectAnswer = correctImageView;

        setImageViewsBackgroundColor(generateOtherColors(correctColorId), correctImageView, correctColorId);
    }

    private int[] generateOtherColors(int correctColorId)
    {
        int [] colorIds = new int[3];
        for (int i = 0; i < colorIds.length; ++i)
        {
            colorIds[i] = -1;
        }

        while (colorIds[0] == -1 || colorIds[0] == correctColorId)
        {
            colorIds[0] = random.nextInt(COLOR_FIELDS.length);
        }

        while (colorIds[1] == -1 || colorIds[1] == colorIds[0] || colorIds[1] == correctColorId)
        {
            colorIds[1] = random.nextInt(COLOR_FIELDS.length);
        }

        while (colorIds[2] == -1 || colorIds[2] == colorIds[0] || colorIds[2] == colorIds[1] || colorIds[2] == correctColorId)
        {
            colorIds[2] = random.nextInt(COLOR_FIELDS.length);
        }
        return colorIds;
    }

    private void setImageViewsBackgroundColor(int[] colorIds, int correctImageView, int correctColorId)
    {
        imgViewMain.setBackgroundColor(getResources().getColor(COLOR_FIELDS[correctColorId]));
        if (correctImageView == 1)
        {
            imgViewOption1.setBackgroundColor(getResources().getColor(COLOR_FIELDS[correctColorId]));
            imgViewOption2.setBackgroundColor(getResources().getColor(COLOR_FIELDS[colorIds[0]]));
            imgViewOption3.setBackgroundColor(getResources().getColor(COLOR_FIELDS[colorIds[1]]));
            imgViewOption4.setBackgroundColor(getResources().getColor(COLOR_FIELDS[colorIds[2]]));
        }
        else if (correctImageView == 2)
        {
            imgViewOption2.setBackgroundColor(getResources().getColor(COLOR_FIELDS[correctColorId]));
            imgViewOption1.setBackgroundColor(getResources().getColor(COLOR_FIELDS[colorIds[0]]));
            imgViewOption3.setBackgroundColor(getResources().getColor(COLOR_FIELDS[colorIds[1]]));
            imgViewOption4.setBackgroundColor(getResources().getColor(COLOR_FIELDS[colorIds[2]]));
        }
        else if (correctImageView == 3)
        {
            imgViewOption3.setBackgroundColor(getResources().getColor(COLOR_FIELDS[correctColorId]));
            imgViewOption1.setBackgroundColor(getResources().getColor(COLOR_FIELDS[colorIds[0]]));
            imgViewOption2.setBackgroundColor(getResources().getColor(COLOR_FIELDS[colorIds[1]]));
            imgViewOption4.setBackgroundColor(getResources().getColor(COLOR_FIELDS[colorIds[2]]));
        }
        else
        {
            imgViewOption4.setBackgroundColor(getResources().getColor(COLOR_FIELDS[correctColorId]));
            imgViewOption1.setBackgroundColor(getResources().getColor(COLOR_FIELDS[colorIds[0]]));
            imgViewOption2.setBackgroundColor(getResources().getColor(COLOR_FIELDS[colorIds[1]]));
            imgViewOption3.setBackgroundColor(getResources().getColor(COLOR_FIELDS[colorIds[2]]));
        }
    }

    // TODO: text for imageviews
    private void setTextViews(int [] textIds)
    {

    }

}

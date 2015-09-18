package vvkn.finki.ukim.mk.stroopeffect.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
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
import vvkn.finki.ukim.mk.stroopeffect.utilities.StopWatch;

public class SimulationFragment extends Fragment {
    public static final String TAG = "SE:TestFragment";
    public static final String TESTER_GENDER = "male or female";
    public static final int STROOP_EFFECT_CONGRUENT = 0;
    public static final int STROOP_EFFECT_INCONGRUENT = 1;
    public static final int MAX_SIMULATIONS = 1;
    public static final int TOAST_DURATION = 500;

    private static final int [] COLOR_FIELDS = { R.color.color_option_1, R.color.color_option_2,
                                                R.color.color_option_3, R.color.color_option_4,
                                                R.color.color_option_5, R.color.color_option_6,
                                                R.color.color_option_7 };
    private static final String [] COLOR_NAMES = { "Black", "Red", "Green", "Blue", "Yellow", "Pink", "Cyan" };

    private int mSimulationType;
    private int mCurrentSimulationNumber;
    private int mTotalTries;

    private final Result currentResult;
    private final StopWatch stopWatch;
    private final Random random;

    private ImageView imgViewMain;
    private ImageView[] imgViewsArray;
    private TextView[] txtViewsArray;

    private Toast errorToast;

    private int mCorrectAnswer;

    public SimulationFragment() {
        imgViewsArray = new ImageView[4];
        txtViewsArray = new TextView[4];

        currentResult = new Result();
        stopWatch = new StopWatch();
        random = new Random();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simulation, container, false);
        initializeWidgets(view);

        mSimulationType = STROOP_EFFECT_CONGRUENT;
        mCurrentSimulationNumber = 0;
        mTotalTries = 0;

        currentResult.setGender(getArguments().getString(TESTER_GENDER));

        stopWatch.start();

        simulate(mSimulationType);

        for (int i = 0; i < imgViewsArray.length; ++i)
        {
            final int index = i;
            imgViewsArray[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    processClick(index);
                }
            });
        }

        return view;
    }

    private void initializeWidgets(View view)
    {
        imgViewMain = (ImageView)view.findViewById(R.id.simulation_fragment_image_view_main);

        imgViewsArray[0] = (ImageView)view.findViewById(R.id.simulation_fragment_image_view_option1);
        imgViewsArray[1] = (ImageView)view.findViewById(R.id.simulation_fragment_image_view_option2);
        imgViewsArray[2] = (ImageView)view.findViewById(R.id.simulation_fragment_image_view_option3);
        imgViewsArray[3] = (ImageView)view.findViewById(R.id.simulation_fragment_image_view_option4);

        txtViewsArray[0] = (TextView)view.findViewById(R.id.simulation_fragment_text_view_option1);
        txtViewsArray[1] = (TextView)view.findViewById(R.id.simulation_fragment_text_view_option2);
        txtViewsArray[2] = (TextView)view.findViewById(R.id.simulation_fragment_text_view_option3);
        txtViewsArray[3] = (TextView)view.findViewById(R.id.simulation_fragment_text_view_option4);

        View toastView = getActivity().getLayoutInflater().inflate(R.layout.toast_error, null);
        errorToast = new Toast(getActivity().getApplicationContext());
        errorToast.setGravity(Gravity.BOTTOM, 0, 100);
        errorToast.setView(toastView);
    }

    private void processClick(int optionClicked)
    {
        mTotalTries++;

        if (mCorrectAnswer == optionClicked) {
            mCurrentSimulationNumber++;

            if (mCurrentSimulationNumber == MAX_SIMULATIONS)
            {
                long elapsedTime = stopWatch.getElapsedMilliseconds();
                currentResult.setElapsedTime(mSimulationType, elapsedTime);
                currentResult.setErrorPercentage(mSimulationType, 1 - 1.0 * MAX_SIMULATIONS / mTotalTries);
                stopWatch.restart();

                if (mSimulationType == STROOP_EFFECT_INCONGRUENT) {
                    String message = "Thanks for participating. " + currentResult.toString();
                    Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    Log.d(TAG, "" + ((MainActivity)getActivity()).getDao().insert(currentResult));
                    ((MainActivity)getActivity()).startHomeFragment();
                }

                mSimulationType++;

                mCurrentSimulationNumber = 0;
                mTotalTries = 0;
            }
            simulate(mSimulationType);
        }
        else
        {
            errorToast.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    errorToast.cancel();
                }
            }, TOAST_DURATION);
        }
    }

    private void simulate(int type)
    {
        int correctColorId = random.nextInt(COLOR_FIELDS.length);
        int [] otherColorsIds = generateOtherRandomNumbers(correctColorId);
        int correctAnswerIndex = random.nextInt(imgViewsArray.length);
        mCorrectAnswer = correctAnswerIndex;

        if (type == STROOP_EFFECT_CONGRUENT) {
            setWidgetsCongruent(otherColorsIds, correctAnswerIndex, correctColorId);
        }
        else if (type == STROOP_EFFECT_INCONGRUENT)
        {
            setWidgetsIncongruent(otherColorsIds, correctAnswerIndex, correctColorId);
        }
    }

    private void setWidgetsCongruent(int[] otherColorsIds, int correctAnswerIndex, int correctColorId)
    {
        imgViewMain.setBackgroundColor(getResources().getColor(COLOR_FIELDS[correctColorId]));

        for (int i = 0, j = 0; i < imgViewsArray.length; ++i)
        {
            if (i == correctAnswerIndex)
            {
                imgViewsArray[i].setBackgroundColor(getResources().getColor(COLOR_FIELDS[correctColorId]));
                txtViewsArray[i].setText(COLOR_NAMES[correctColorId]);
            }
            else
            {
                imgViewsArray[i].setBackgroundColor(getResources().getColor(COLOR_FIELDS[otherColorsIds[j]]));
                txtViewsArray[i].setText(COLOR_NAMES[otherColorsIds[j++]]);
            }
        }
    }

    private void setWidgetsIncongruent(int[] otherColorIds, int correctImageViewIndex, int correctColorId)
    {
        imgViewMain.setBackgroundColor(getResources().getColor(COLOR_FIELDS[correctColorId]));

        int imgViewIndexForCorrectColor = random.nextInt(imgViewsArray.length);
        while (imgViewIndexForCorrectColor == correctImageViewIndex)
        {
            imgViewIndexForCorrectColor = random.nextInt(imgViewsArray.length);
        }

        int[] txtIds = randomizeValues(otherColorIds);

        for (int i = 0, txtIndex = 0, imageIndex = 0; i < imgViewsArray.length; ++i)
        {
            if (i == correctImageViewIndex)
            {
                imgViewsArray[i].setBackgroundColor(getResources().getColor(COLOR_FIELDS[otherColorIds[imageIndex++]]));
                txtViewsArray[i].setText(COLOR_NAMES[correctColorId]);
            }
            else
            {
                if (i == imgViewIndexForCorrectColor)
                {
                    imgViewsArray[i].setBackgroundColor(getResources().getColor(COLOR_FIELDS[correctColorId]));
                }
                else
                {
                    imgViewsArray[i].setBackgroundColor(getResources().getColor(COLOR_FIELDS[otherColorIds[imageIndex++]]));
                }
                txtViewsArray[i].setText(COLOR_NAMES[txtIds[txtIndex++]]);
            }
        }
    }

    // TODO: Refactor random generators (make them more reusable)
    private int[] generateOtherRandomNumbers(int correctColorId)
    {
        int [] colorIds = new int[imgViewsArray.length - 1];
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

    private int[] randomizeValues(int [] otherValues)
    {
        int[] newValues = new int[otherValues.length];

        int firstIndex, secondIndex = -1, thirdIndex = -1;
        firstIndex = random.nextInt(otherValues.length);
        while (secondIndex == -1 || secondIndex == firstIndex)
        {
            secondIndex = random.nextInt(otherValues.length);
        }
        while (thirdIndex == -1 || thirdIndex == secondIndex || thirdIndex == firstIndex)
        {
            thirdIndex = random.nextInt(otherValues.length);
        }

        Log.d(TAG, "randomized random values: " + firstIndex + ' ' + secondIndex + ' ' + thirdIndex);

        newValues[0] = otherValues[firstIndex];
        newValues[1] = otherValues[secondIndex];
        newValues[2] = otherValues[thirdIndex];

        return newValues;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "final result: \n" + currentResult.toString());
    }
}

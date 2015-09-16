package vvkn.finki.ukim.mk.stroopeffect.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import vvkn.finki.ukim.mk.stroopeffect.MainActivity;
import vvkn.finki.ukim.mk.stroopeffect.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment {
    public static final String TAG = "SE:MainFragment";

    private RadioGroup radioGroup;
    private Button btnStart;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        radioGroup = (RadioGroup)(view.findViewById(R.id.home_fragment_radio_buttons_group));
        btnStart = (Button)(view.findViewById(R.id.home_fragment_button_start_test));

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (!btnStart.isEnabled()) btnStart.setEnabled(true);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gender = (String)(view.findViewById(radioGroup.getCheckedRadioButtonId()).getTag());
                if (gender == null || gender.isEmpty()) gender = "m";

                ((MainActivity)getActivity()).startSimulationFragment(gender);
            }
        });

        return view;

    }
}

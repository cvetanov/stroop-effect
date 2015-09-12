package vvkn.finki.ukim.mk.stroopeffect.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vvkn.finki.ukim.mk.stroopeffect.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment {
    public static final String TAG = "SE:MainFragment";

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}

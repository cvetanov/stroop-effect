package vvkn.finki.ukim.mk.stroopeffect.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import vvkn.finki.ukim.mk.stroopeffect.R;
import vvkn.finki.ukim.mk.stroopeffect.models.Result;
import vvkn.finki.ukim.mk.stroopeffect.utilities.ResultsAdapter;

public class ResultsFragment extends Fragment {
    public static final String TAG = "SE:ResultsFragment";

    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_results, container, false);

        List<Result> results = new ArrayList<>();
        Result res = new Result();
        res.setGender("f");
        res.setErrorPercentageCongruent(0);
        res.setElapsedTimeCongruent(3000);
        res.setErrorPercentageIncongruent(0.1);
        res.setElapsedTimeIncongruent(5000);
        results.add(res);

        listView = (ListView) view.findViewById(R.id.results_fragment_listview);
        ListAdapter adapter = new ResultsAdapter(getActivity().getApplicationContext(), R.layout.listview_item_results, results);
        listView.setAdapter(adapter);

        return view;
    }
}

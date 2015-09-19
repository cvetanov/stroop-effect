package mk.ukim.finki.vvkn.stroopeffect.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import mk.ukim.finki.vvkn.stroopeffect.MainActivity;
import mk.ukim.finki.vvkn.stroopeffect.R;
import mk.ukim.finki.vvkn.stroopeffect.utilities.ResultsAdapter;

public class ResultsFragment extends Fragment {

    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_results, container, false);
        MainActivity activity = (MainActivity)getActivity();

        listView = (ListView) view.findViewById(R.id.results_fragment_listview);
        ListAdapter adapter = new ResultsAdapter(activity.getApplicationContext(), R.layout.listview_item_results, activity.getResults());
        listView.setAdapter(adapter);

        return view;
    }
}

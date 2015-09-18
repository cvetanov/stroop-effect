package vvkn.finki.ukim.mk.stroopeffect.utilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import vvkn.finki.ukim.mk.stroopeffect.R;
import vvkn.finki.ukim.mk.stroopeffect.models.Result;

public class ResultsAdapter extends ArrayAdapter<Result> {
    public static final String TAG = "SE:ResultsAdapter";

    private List<Result> results;
    private int resource;

    public ResultsAdapter(Context context, int resource) {
        super(context, resource);
    }

    public ResultsAdapter(Context context, int resource, List<Result> objects) {
        super(context, resource, objects);
        results = objects;
        this.resource = resource;
    }

    @Override
    public Result getItem(int position) {
        return results.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.listview_item_results, null);
        }

        Result result = results.get(position);

        TextView txtViewCongruentError = (TextView) v.findViewById(R.id.results_fragment_list_text_error_congruent);
        TextView txtViewCongruentTime = (TextView) v.findViewById(R.id.results_fragment_list_text_time_congruent);
        TextView txtViewIncongruentError = (TextView) v.findViewById(R.id.results_fragment_list_text_error_incongruent);
        TextView txtViewIncongruentTime = (TextView) v.findViewById(R.id.results_fragment_list_text_time_incongruent);
        txtViewCongruentError.setText("" + result.getErrorPercentageCongruent());
        txtViewCongruentTime.setText("" + result.getElapsedTimeCongruent());
        txtViewIncongruentError.setText("" + result.getErrorPercentageIncongruent());
        txtViewIncongruentTime.setText("" + result.getElapsedTimeIncongruent());

        return v;
    }

    public List<Result> getResults() {
        return results;
    }
}

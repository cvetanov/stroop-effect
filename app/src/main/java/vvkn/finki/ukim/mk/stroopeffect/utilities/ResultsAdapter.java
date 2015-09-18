package vvkn.finki.ukim.mk.stroopeffect.utilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import vvkn.finki.ukim.mk.stroopeffect.R;
import vvkn.finki.ukim.mk.stroopeffect.models.Result;

public class ResultsAdapter extends ArrayAdapter<Result> {
    public static final String TAG = "SE:ResultsAdapter";

    private List<Result> results;

    public ResultsAdapter(Context context, int resource, List<Result> objects) {
        super(context, resource, objects);
        results = objects;
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
        txtViewCongruentError.setText("Error% " + String.format("%.2f", result.getErrorPercentageCongruent()));
        txtViewCongruentTime.setText("Response time (ms) " + result.getElapsedTimeCongruent());
        txtViewIncongruentError.setText("Error% " + String.format("%.2f", result.getErrorPercentageIncongruent()));
        txtViewIncongruentTime.setText("Response time (ms) " + result.getElapsedTimeIncongruent());

        if (position % 2 == 1)
        {
            LinearLayout containerLayout = (LinearLayout)v.findViewById(R.id.results_fragment_layout_container);
            containerLayout.setBackgroundColor(getContext().getResources().getColor(R.color.row_stripping));
        }

        return v;
    }
}

package mk.ukim.finki.vvkn.stroopeffect.utilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import mk.ukim.finki.vvkn.stroopeffect.R;
import mk.ukim.finki.vvkn.stroopeffect.models.Result;

public class ResultsAdapter extends ArrayAdapter<Result> {
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

        LinearLayout containerLayout = (LinearLayout)v.findViewById(R.id.results_fragment_layout_container);
        if (position % 2 == 1)
        {
            containerLayout.setBackgroundColor(getContext().getResources().getColor(R.color.row_striping_odd));
        }
        else
        {
            containerLayout.setBackgroundColor(getContext().getResources().getColor(R.color.row_striping_even));
        }

        return v;
    }
}

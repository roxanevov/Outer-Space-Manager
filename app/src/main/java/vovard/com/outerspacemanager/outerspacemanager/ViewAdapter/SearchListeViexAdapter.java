package vovard.com.outerspacemanager.outerspacemanager.ViewAdapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import vovard.com.outerspacemanager.outerspacemanager.Entity.Search;
import vovard.com.outerspacemanager.outerspacemanager.R;

public class SearchListeViexAdapter extends ArrayAdapter<Search> {
    private final Context context;
    private final List<Search> values;

    public SearchListeViexAdapter(Context context, List<Search> search) {
        super(context, R.layout.row_fleet, search);
        this.context = context;
        this.values = search;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_search, parent, false);
        TextView textViewname = (TextView) rowView.findViewById(R.id.textViewname);
        TextView textViewAmoutEffectByLevel = (TextView) rowView.findViewById(R.id.textViewAmoutEffectByLevel);
        TextView textViewAmountEffectLevel = (TextView) rowView.findViewById(R.id.textViewAmountEffectLevel);
        TextView textViewBuilding = (TextView) rowView.findViewById(R.id.textViewBuilding);
        TextView textViewEffect = (TextView) rowView.findViewById(R.id.textViewEffect);
        TextView textViewTime = (TextView) rowView.findViewById(R.id.textViewTime);

        Search search = values.get(position);

        textViewname.setText(search.getName());
        textViewAmoutEffectByLevel.setText(String.valueOf(search.getAmountOfEffectByLevel()));
        textViewAmountEffectLevel.setText(String.valueOf(search.getAmountOfEffectLevel0()));
        textViewBuilding.setText(String.valueOf(search.getBuilding()));
        textViewEffect.setText(String.valueOf(search.getEffect()));
        textViewTime.setText(String.valueOf(search.getTimeToBuildByLevel()));
        return rowView;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public long getItemId( int position){
        Search search = getItem(position);
        return search.getSearchId();
    }
}

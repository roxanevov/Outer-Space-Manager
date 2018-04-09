package vovard.com.outerspacemanager.outerspacemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class FleetListeViewAdapter  extends ArrayAdapter<Ship>{

    private final Context context;
        private final List<Ship> values;

    public FleetListeViewAdapter(Context context, List<Ship> fleet) {
        super(context, R.layout.row_fleet, fleet);
        this.context = context;
        this.values = fleet;
    }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.row_fleet, parent, false);
            TextView textViewName = (TextView) rowView.findViewById(R.id.textViewName);
            TextView textViewAmount = (TextView) rowView.findViewById(R.id.textViewAmount);

            Ship ship = values.get(position);

            textViewName.setText(ship.getName());
            textViewAmount.setText(String.valueOf(ship.getAmount()));

            return rowView;
        }

        @Override
        public int getCount() {
        return super.getCount();
    }

        @Override
        public long getItemId( int position){
        Ship ship = getItem(position);
        return ship.getShipId();
    }
}

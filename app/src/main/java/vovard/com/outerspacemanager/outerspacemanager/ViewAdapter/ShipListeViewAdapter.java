package vovard.com.outerspacemanager.outerspacemanager.ViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import vovard.com.outerspacemanager.outerspacemanager.Entity.Ship;
import vovard.com.outerspacemanager.outerspacemanager.R;

public class ShipListeViewAdapter  extends ArrayAdapter<Ship> {
    private final Context context;
    private final List<Ship> values;

    public ShipListeViewAdapter(Context context, List<Ship> ships) {
        super(context, R.layout.row_ship, ships);
        this.context = context;
        this.values = ships;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_ship, parent, false);
        TextView textViewName = (TextView) rowView.findViewById(R.id.textViewName);
        TextView textViewGasCost = (TextView) rowView.findViewById(R.id.textViewGasCost);
        TextView textViewMineralCost = (TextView) rowView.findViewById(R.id.textViewMineralCost);
        TextView textViewMaxAttack = (TextView) rowView.findViewById(R.id.textViewMaxAttack);
        TextView textViewMinAttack = (TextView) rowView.findViewById(R.id.textViewMinAttack);

        textViewName.setText(values.get(position).getName());
        textViewGasCost.setText("" + values.get(position).getGasCost());
        textViewMineralCost.setText("" + values.get(position).getMineralCost());
        textViewMaxAttack.setText("" + values.get(position).getMaxAttack());
        textViewMinAttack.setText("" + values.get(position).getMinAttack());

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

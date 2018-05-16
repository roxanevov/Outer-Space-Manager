package vovard.com.outerspacemanager.outerspacemanager.viewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import vovard.com.outerspacemanager.outerspacemanager.entity.Building;
import vovard.com.outerspacemanager.outerspacemanager.R;


public class BuildingListeViewAdapter extends ArrayAdapter<Building> {

    private final Context context;
    private final List<Building> values;

    public BuildingListeViewAdapter(Context context, List<Building> building) {
        super(context, R.layout.row_building, building);
        this.context = context;
        this.values = building;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_building, parent, false);

        ImageView imageViewBuilding = (ImageView) rowView.findViewById(R.id.imageViewBuilding);

        Glide.with(context)
                .load(values.get(position).getImageUrl())
                .into(imageViewBuilding);
        return rowView;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public long getItemId( int position){
        Building building = getItem(position);
        return Long.parseLong(building.getBuildingId());
    }
}

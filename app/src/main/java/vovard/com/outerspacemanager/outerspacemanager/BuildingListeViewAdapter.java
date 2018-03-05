package vovard.com.outerspacemanager.outerspacemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


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
        TextView textView = (TextView) rowView.findViewById(R.id.BuildingNameTextView);
        TextView BuildingEffectTextView = (TextView) rowView.findViewById(R.id.BuildingEffectTextView);
        TextView BuildingBuildingTextView = (TextView) rowView.findViewById(R.id.BuildingBuildingTextView);
        ImageView imageViewBuilding = (ImageView) rowView.findViewById(R.id.imageViewBuilding);
        ImageView BuildingLevelTextViewtextView3 = (TextView) rowView.findViewById(R.id.BuildingLevelTextViewtextView3);

        Glide.with(context)
                .load(values.get(position).getImageUrl())
                .into(imageViewBuilding);

        textView.setText(values.get(position).getName());
        BuildingEffectTextView.setText(values.get(position).getEffect());
        BuildingBuildingTextView.setText(values.get(position).getBuilding());
        BuildingLevelTextViewtextView3.setText(values.get(position).getLevel()+"");

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

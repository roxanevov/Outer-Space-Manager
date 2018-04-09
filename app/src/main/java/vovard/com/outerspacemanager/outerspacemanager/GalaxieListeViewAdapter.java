package vovard.com.outerspacemanager.outerspacemanager;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GalaxieListeViewAdapter extends ArrayAdapter<UserClass> {

    private final Context context;
    private final List<UserClass> values;

    public GalaxieListeViewAdapter(Context context, List<UserClass> user) {
        super(context, R.layout.row_fleet, user);
        this.context = context;
        this.values = user;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_galaxie, parent, false);
        TextView textViewName = (TextView) rowView.findViewById(R.id.textViewName);
        TextView textViewPoints = (TextView) rowView.findViewById(R.id.textViewPoints);
        ImageView imageViewAvatar = (ImageView) rowView.findViewById(R.id.imageViewAvatar);

        UserClass user = values.get(position);

        textViewName.setText(user.getUsername());
        textViewPoints.setText(user.getPoints());
        imageViewAvatar.setImageURI(user.getImageUrl());
        return rowView;
    }

    @Override
    public long getItemId( int position){
        UserClass user = getItem(position);
        return Long.parseLong(user.getUsername());
    }
}

package vovard.com.outerspacemanager.outerspacemanager.ViewAdapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vovard.com.outerspacemanager.outerspacemanager.APIResponse.CreateShipResponce;
import vovard.com.outerspacemanager.outerspacemanager.Constant;
import vovard.com.outerspacemanager.outerspacemanager.Entity.Ship;
import vovard.com.outerspacemanager.outerspacemanager.R;
import vovard.com.outerspacemanager.outerspacemanager.Service.outerSpeceManagerService;

public class ShipListeViewAdapter  extends RecyclerView.Adapter<ShipListeViewAdapter.ViewHolder> {
    private final Context context;
    private final List<Ship> values;

    public static String TOKEN = "";
    public static String token;


    public ShipListeViewAdapter(Context context, List<Ship> ships) {
        //super(context, R.layout.row_ship, ships);

        this.context = context;
        this.values = ships;

        SharedPreferences isToken = context.getSharedPreferences(TOKEN, 0);
        token = isToken.getString("token", null);
    }
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mView;
        public ViewHolder(View v) {
            super(v);
            mView = v;

            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(Constant.URL_BDD)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    outerSpeceManagerService service = retrofit.create(outerSpeceManagerService.class);

                    JSONObject jsonParams = new JSONObject();
                    try {
                        jsonParams.put("amount", 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),jsonParams.toString());
                    Call<CreateShipResponce> request = service.createShip(token, getAdapterPosition() ,body);

                    request.enqueue(new Callback<CreateShipResponce>() {
                        @Override
                        public void onResponse(Call<CreateShipResponce> call, Response<CreateShipResponce> response) {
                            if (response.code() != 200) {
                                try {
                                    Toast.makeText(mView.getContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                                    Log.i("erreur", response.errorBody().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                CreateShipResponce rss = response.body();
                                Toast.makeText(mView.getContext(), "En construction", Toast.LENGTH_SHORT).show();

                            }
                        }
                        @Override
                        public void onFailure(Call<CreateShipResponce> call, Throwable t) {
                        }
                    });

                    //Toast.makeText(mView.getContext(),"J'ai sélectionné l'item "+id,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ShipListeViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_ship, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder (ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.mView.setText(this.values[position]);
        ImageView imageViewBuilding = (ImageView)  holder.mView.findViewById(R.id.imageViewShip);
        Glide.with(context)
                .load(getImage("ship_"+values.get(position).getShipId(), context))
                .into(imageViewBuilding);

        TextView textViewName = (TextView) holder.mView.findViewById(R.id.textViewName);
        TextView textViewGasCost = (TextView) holder.mView.findViewById(R.id.textViewGasCost);
        TextView textViewMineralCost = (TextView)holder.mView.findViewById(R.id.textViewMineralCost);
        TextView textViewMaxAttack = (TextView) holder.mView.findViewById(R.id.textViewMaxAttack);
        TextView textViewMinAttack = (TextView) holder.mView.findViewById(R.id.textViewMinAttack);

        textViewName.setText(values.get(position).getName());
        textViewGasCost.setText("" + values.get(position).getGasCost());
        textViewMineralCost.setText("" + values.get(position).getMineralCost());
        textViewMaxAttack.setText("" + values.get(position).getMaxAttack());
        textViewMinAttack.setText("" + values.get(position).getMinAttack());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

    public int getImage(String imageName, Context context) {

        int drawableResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        return drawableResourceId;
    }

}

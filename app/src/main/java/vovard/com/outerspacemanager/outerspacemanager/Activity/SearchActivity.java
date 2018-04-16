package vovard.com.outerspacemanager.outerspacemanager.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vovard.com.outerspacemanager.outerspacemanager.APIResponse.SearchResponce;
import vovard.com.outerspacemanager.outerspacemanager.Constant;
import vovard.com.outerspacemanager.outerspacemanager.Entity.Search;
import vovard.com.outerspacemanager.outerspacemanager.R;
import vovard.com.outerspacemanager.outerspacemanager.ViewAdapter.SearchListeViexAdapter;
import vovard.com.outerspacemanager.outerspacemanager.Service.outerSpeceManagerService;

public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    public static String TOKEN = "";
    private List<Search> searches;
    public String token;
    private ListView ListViewSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);
        ListViewSearch = (ListView)findViewById(R.id.ListViewSearch);
        ListViewSearch.setOnItemClickListener(this);

        SharedPreferences isToken = getSharedPreferences(TOKEN, 0);
        token = isToken.getString("token", null);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_BDD)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        outerSpeceManagerService service = retrofit.create(outerSpeceManagerService.class);
        Call<SearchResponce> request = service.listeSearches(token);

        request.enqueue(new Callback<SearchResponce>() {
            @Override
            public void onResponse(Call<SearchResponce> call, Response<SearchResponce> response) {
                if (response.code() != 200) {
                    try {
                        //Toast.makeText(getApplicationContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                        Log.i("erreur", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    SearchResponce rss = response.body();
                    searches = rss.getSearches();
                    ListViewSearch.setAdapter(new SearchListeViexAdapter(SearchActivity.this,searches));
                }

            }

            @Override
            public void onFailure(Call<SearchResponce> call, Throwable t) {

            }
        });
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }
}

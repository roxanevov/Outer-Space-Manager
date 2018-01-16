package vovard.com.outerspacemanager.outerspacemanager;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by rvovard on 16/01/2018.
 */

public interface outerSpeceManagerService {
    @POST("auth/create")
    Call<AuthentificationResponce> user(@Body UserClass user);

}

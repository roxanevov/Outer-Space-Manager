package vovard.com.outerspacemanager.outerspacemanager;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by rvovard on 16/01/2018.
 */

public interface outerSpeceManagerService {
    @POST("auth/create")
    Call<AuthentificationResponce> user(@Body UserClass user);

    @GET("users/get")
    Call<CurrentUserResponce> getCurrentuser(@Header("x-access-token") String token);

    @GET("buildings/list")
    Call<BuildingResponce> getBuilding(@Header("x-access-token") String token);

    @POST("buildings/create/{buildingId}")
    Call<CreateBuildingResponce> createBuilding(@Header("x-access-token") String token, @Path("buildingId") long id);

}

package remer.weather;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService
{
    @GET("data/2.5/weather")
    Single<WeatherResponse> weatherNow(
            @Query("q") String query,
            @Query("appid") String apiKey,
            @Query("units") String units
    );

}

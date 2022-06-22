package ua.icm.medassistant.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ua.icm.medassistant.datamodel.CityGroup;
import ua.icm.medassistant.datamodel.WeatherGroup;


public interface IpmaApiEndpoints {

    @GET("open-data/distrits-islands.json")
    Call<CityGroup> getCityParent();

    @GET("open-data/forecast/meteorology/cities/daily/{localId}.json")
    Call<WeatherGroup> getWeatherParent(@Path("localId") int localId);

}

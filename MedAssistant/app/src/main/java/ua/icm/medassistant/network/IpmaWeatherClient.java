package ua.icm.medassistant.network;


import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import ua.icm.medassistant.datamodel.City;
import ua.icm.medassistant.datamodel.CityGroup;
import ua.icm.medassistant.datamodel.Weather;
import ua.icm.medassistant.datamodel.WeatherGroup;

public class IpmaWeatherClient {

    private IpmaApiEndpoints apiService;


    public IpmaWeatherClient() {
        Retrofit retrofitInstance = RetrofitInstance.getRetrofitInstance();
        this.apiService = retrofitInstance.create(IpmaApiEndpoints.class);
    }

    /**
     * get a list of cities (districts)
     * @param listener a listener object to callback with the results
     */
    public void getCitiesList(CityListResultObserver listener) {
        HashMap<String, City> cities = new HashMap<>();

        Call<CityGroup> call = apiService.getCityParent();
        call.enqueue(new Callback<CityGroup>() {
            @Override
            public void onResponse(Call<CityGroup> call, Response<CityGroup> response) {
                int statusCode = response.code();
                CityGroup citiesGroup = response.body();
                for (City city : citiesGroup.getCities()) {
                    cities.put(city.getLocal(), city);
                }
                listener.receiveCitiesList( cities);
            }

            @Override
            public void onFailure(Call<CityGroup> call, Throwable t) {
                Log.e("main", "errog calling remote api: " + t.getLocalizedMessage());
                listener.onFailure( t);
            }
        });
    }

    /**
     * get the dictionary for the weather states
     * @param listener a listener object to callback with the results
     */
  /*  public void retrieveWeatherConditionsDescriptions(WeatherTypesResultsObserver listener) {
        HashMap<Integer, WeatherType> weatherDescriptions = new HashMap<>();

        Call<WeatherTypeGroup> call = apiService.getWeatherTypes();
        call.enqueue(new Callback<WeatherTypeGroup>() {
            @Override
            public void onResponse(Call<WeatherTypeGroup> call, Response<WeatherTypeGroup> response) {
                int statusCode = response.code();
                WeatherTypeGroup weatherTypesGroup = response.body();
                for ( WeatherType weather: weatherTypesGroup.getTypes() ) {
                    weatherDescriptions.put(weather.getIdWeatherType(), weather);
                }
                listener.receiveWeatherTypesList(weatherDescriptions);
            }

            @Override
            public void onFailure(Call<WeatherTypeGroup> call, Throwable t) {
                Log.e( "main", "errog calling remote api: " + t.getLocalizedMessage());
                listener.onFailure( t);
            }
        });
    }
*/
    /**
     * get the 5-day forecast for a city
     * @param  localId the global identifier of the location
     * @param listener a listener object to callback with the results
     */
    public void getForecastForCity(int localId, ForecastForACityResultsObserver listener) {
        List<Weather> forecast = new ArrayList<>();

        Call<WeatherGroup> call = apiService.getWeatherParent(localId);
        call.enqueue(new Callback<WeatherGroup>() {
            @Override
            public void onResponse(Call<WeatherGroup> call, Response<WeatherGroup> response) {
                int statusCode = response.code();
                WeatherGroup weatherTypesGroup = response.body();
                forecast.addAll(weatherTypesGroup.getForecasts() );
                listener.receiveForecastList(forecast);
            }

            @Override
            public void onFailure(Call<WeatherGroup> call, Throwable t) {
                Log.e( "main", "errog calling remote api: " + t.getLocalizedMessage());
                listener.onFailure( t);
            }
        });

    }


}

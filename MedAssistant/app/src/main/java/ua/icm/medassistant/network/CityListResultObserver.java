package ua.icm.medassistant.network;


import java.util.HashMap;

import ua.icm.medassistant.datamodel.City;

public interface CityListResultObserver {
    public void receiveCitiesList(HashMap<String, City> citiesMap);
    public void onFailure(Throwable cause);

}

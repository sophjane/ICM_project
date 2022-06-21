package ua.icm.medassistant.network;

import java.util.List;

import ua.icm.medassistant.datamodel.Weather;

public interface ForecastForACityResultsObserver {
    public void receiveForecastList(List<Weather> forecast);
    public void onFailure(Throwable cause);
}

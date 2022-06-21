package ua.icm.medassistant.content;

import java.util.ArrayList;
import java.util.List;

public class CityUtils {

    public static final List<City> CITY_ITEMS = new ArrayList<>();

    public static final String CITY_ID_KEY = "item_id";

    // The number of songs.
    //private static final int COUNT = 7;


    public static class City {
        public final String name;
        public final String weather;

        private City(String name, String weather) {
            this.name = name;
            this.weather = weather;
        }
    }

    private static void addItem(City item) {
        CITY_ITEMS.add(item);
    }


}

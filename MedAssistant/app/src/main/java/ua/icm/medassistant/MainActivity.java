package ua.icm.medassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime());
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println("Day: " + day);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println("Hour: " + hour);
        int minute = calendar.get(Calendar.MINUTE);
        System.out.println("Minute: " + minute);

        //Log.d(LOG_TAG, String.valueOf(calendar.getTime()));

    }
}
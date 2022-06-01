package ua.icm.medassistant;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;


        System.out.println(height);
        System.out.println(width);

        Calendar calendar = Calendar.getInstance();

        int dayNum = calendar.get(Calendar.DAY_OF_WEEK);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int monthNum = calendar.get(Calendar.MONTH);
        int dayOfTheMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);

        String dayOfTheWeek;
        String monthName;

        switch (dayNum) {
            case 1:
                dayOfTheWeek = "Sunday";
                break;
            case 2:
                dayOfTheWeek = "Monday";
                break;
            case 3:
                dayOfTheWeek = "Tuesday";
                break;
            case 4:
                dayOfTheWeek = "Wednesday";
                break;
            case 5:
                dayOfTheWeek = "Thursday";
                break;
            case 6:
                dayOfTheWeek = "Friday";
                break;
            case 7:
                dayOfTheWeek = "Saturday";
                break;
            default:
                dayOfTheWeek = "undefined";
                break;
        }

        switch (monthNum) {
            case 0:
                monthName = "January";
                break;
            case 1:
                monthName = "February";
                break;
            case 2:
                monthName = "March";
                break;
            case 3:
                monthName = "April";
                break;
            case 4:
                monthName = "May";
                break;
            case 5:
                monthName = "June";
                break;
            case 6:
                monthName = "July";
                break;
            case 7:
                monthName = "August";
                break;
            case 8:
                monthName = "September";
                break;
            case 9:
                monthName = "October";
                break;
            case 10:
                monthName = "November";
                break;
            case 11:
                monthName = "December";
                break;
            default:
                monthName = "undefined";
                break;
        }

        String time = hour + ":" + minute;
        Log.d(LOG_TAG, time);

        String fullDay = String.format("%s, %s %d %d", dayOfTheWeek, monthName, dayOfTheMonth, year);
        Log.d(LOG_TAG, fullDay);


        TextView timeTextView = findViewById(R.id.time);
        timeTextView.setText(time);


        TextView dayTextView = findViewById(R.id.day);
        dayTextView.setText(fullDay);
    }
}
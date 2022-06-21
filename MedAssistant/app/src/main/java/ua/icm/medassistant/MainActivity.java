package ua.icm.medassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final int TEXT_REQUEST = 1;

    protected void handleClick() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getApplication().setTheme(Theme.Material);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               handleClick();
            }

        });

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

        String fullDay = String.format("%s, %s %d %d", dayOfTheWeek, monthName, dayOfTheMonth, year);

        TextView timeTextView = findViewById(R.id.time);
        timeTextView.setText(time);


        TextView dayTextView = findViewById(R.id.day);
        dayTextView.setText(fullDay);
    }
}
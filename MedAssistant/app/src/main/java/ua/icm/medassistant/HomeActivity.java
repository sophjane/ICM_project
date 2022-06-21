package ua.icm.medassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity {

    private static final int TEXT_REQUEST = 1;
    private static int TIME_OUT =  10000;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button addMed = (Button) findViewById(R.id.addMed);
        addMed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handleAddMedClick();
            }
        });

        Button checkMed = (Button) findViewById(R.id.checkMed);
        checkMed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handleCheckMedClick();
            }
        });

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, TIME_OUT);
    }

    private void handleAddMedClick() {
        Intent intent = new Intent(this, AddMedicationActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    private void handleCheckMedClick() {
        Intent intent = new Intent(this, CheckMedicationsActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }
}
package ua.icm.medassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class HomeActivity extends AppCompatActivity {

    private static final int TEXT_REQUEST = 1;

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
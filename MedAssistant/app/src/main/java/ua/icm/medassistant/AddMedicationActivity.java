package ua.icm.medassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class AddMedicationActivity extends AppCompatActivity {

    ImageView selectedImage;
    Button addPhotoBtn;
    EditText nameInput;

    DatePickerDialog datePickerDialog;
    Button startDateBtn;

    Calendar calendar;
    int currentYear;
    int currentMonth;
    int currentDay;


    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static int TIME_OUT =  10000;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medication);

        calendar = Calendar.getInstance(TimeZone.getDefault());

        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH) + 1;
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        selectedImage = findViewById(R.id.displayImageView);
        addPhotoBtn = findViewById(R.id.addPhoto);
        nameInput = findViewById(R.id.nameInput);
        startDateBtn = findViewById(R.id.startDate);

        addPhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });

        nameInput.setVisibility(View.VISIBLE);
        startDateBtn.setVisibility(View.VISIBLE);
        startDateBtn.setText("Set Start Date");
        startDateBtn.setTextSize(15);
        startDateBtn.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                openDatePicker();
                                            }
                                        }
        );

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(AddMedicationActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, TIME_OUT);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            addPhotoBtn.setVisibility(View.INVISIBLE);
            selectedImage.setVisibility(View.VISIBLE);
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            selectedImage.setImageBitmap(imageBitmap);
        }

    }

    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

        } catch (ActivityNotFoundException e) {
            // display error state to the user
        }
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                String date = String.format("%d / %d / %d", day, month, year);
                startDateBtn.setText(date);
            }
        };

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, currentYear, currentMonth, currentDay);
    }


    public void openDatePicker() {
        initDatePicker();
        datePickerDialog.show();
    }
}
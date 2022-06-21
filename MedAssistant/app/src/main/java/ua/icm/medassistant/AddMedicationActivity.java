package ua.icm.medassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/*import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;*/

public class AddMedicationActivity extends AppCompatActivity {

    ImageView selectedImage;
    Button addPhotoBtn;
    EditText nameInput;

    DatePickerDialog datePickerDialog;
    Button startDateBtn, endDateBtn;

    Calendar calendar;
    int currentYear;
    int currentMonth;
    int currentDay;
    Switch notifications;
    boolean notificationsOn;

    Button gotoScanner;


    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int TEXT_REQUEST = 1;

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
        endDateBtn = findViewById(R.id.endDate);
        notifications = findViewById(R.id.notificationSwitch);
        gotoScanner = findViewById(R.id.scanner);

        notifications.setTextOn("On");
        notifications.setTextOff("Off");
        notifications.setChecked(true);

        addPhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });

        startDateBtn.setVisibility(View.VISIBLE);
        //startDateBtn.setText("Set Start Date");
        startDateBtn.setTextSize(15);
        startDateBtn.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                openDatePicker(startDateBtn);
                                            }
                                        });

        nameInput.setVisibility(View.VISIBLE);
        endDateBtn.setVisibility(View.VISIBLE);
        //endDateBtn.setText("Set End Date");
        endDateBtn.setTextSize(15);
        endDateBtn.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                openDatePicker(endDateBtn);
                                            }
                                        }
        );

        notifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String input;
                if (isChecked){
                    input = notifications.getTextOn().toString();
                }else{
                    input = notifications.getTextOff().toString();

                }
                notificationsOn = isChecked;
                Toast.makeText(getApplicationContext(), "Notifications are "+input,Toast.LENGTH_SHORT).show();
            }
        });

        gotoScanner.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handleGoToScanner();
            }
        });

    }

    private void handleGoToScanner() {
        Intent intent = new Intent(this, CodeScannerActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);
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

    private void initDatePicker(Button datePickerBtn) {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                String date = String.format("%d / %d / %d", day, month, year);
                datePickerBtn.setText(date);
            }
        };

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, currentYear, currentMonth, currentDay);
    }


    public void openDatePicker(Button datePicker) {
        initDatePicker(datePicker);
        datePickerDialog.show();
    }
}
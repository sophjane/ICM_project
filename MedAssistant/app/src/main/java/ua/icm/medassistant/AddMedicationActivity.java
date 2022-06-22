package ua.icm.medassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AddMedicationActivity extends AppCompatActivity {

    ImageView selectedImage;
    Button addPhotoBtn, startDateBtn, endDateBtn, gotoScanner, submit;
    EditText nameInput;

    DatePickerDialog datePickerDialog;

    Calendar calendar;
    int currentYear;
    int currentMonth;
    int currentDay;
    Switch notifications;
    boolean notificationsOn;

    Spinner frequencyDropdown;

    Bitmap medicationPhoto;


    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int TEXT_REQUEST = 1;
    private static final String STATE_PHOTO = "photo";
    private static final String STATE_BARCODE = "barcode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medication);

        /*if (savedInstanceState!=null){
            byte[] image = savedInstanceState.getByteArray(STATE_PHOTO);
            Toast.makeText(AddMedicationActivity.this, "An attemp was made", Toast.LENGTH_LONG).show();
            medicationPhoto = convertCompressedByteArrayToBitmap(image);
            selectedImage.setImageBitmap(medicationPhoto);
        }*/

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
        frequencyDropdown = findViewById(R.id.spinner_frequency);
        submit =findViewById(R.id.submit);

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

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.frequency, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        frequencyDropdown.setAdapter(adapter);

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handleSubmit();
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
            //addPhotoBtn.setVisibility(View.INVISIBLE);
            selectedImage.setVisibility(View.VISIBLE);
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            medicationPhoto = imageBitmap;
            selectedImage.setImageBitmap(imageBitmap);
        }
        if (resultCode==RESULT_OK && data!=null) {
            retrieveBarcode(data);
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



    private void retrieveBarcode(Intent data){
        String barcode = data.getStringExtra("barcode");
        gotoScanner.setText(barcode);
    }

    private void handleSubmit(){
        String name = nameInput.getText().toString();
        String startDate = startDateBtn.getText().toString();
        String endDate = endDateBtn.getText().toString();
        String frequency = frequencyDropdown.getSelectedItem().toString();
        String barcode = gotoScanner.getText().toString();
        if (medicationPhoto!=null || (name!=null && name!="") || !startDate.contains("/") || frequency!=null || !barcode.matches(".*\\d.*")){
            //Toast.makeText(AddMedicationActivity.this, medicationPhoto.toString(), Toast.LENGTH_LONG).show();
            Toast.makeText(AddMedicationActivity.this, "Please fill in the fields, end date is not a required field", Toast.LENGTH_LONG).show();
            return;
        }
        Map<String, Object> userInputs = new HashMap<>();
        userInputs.put("photo", medicationPhoto);
        userInputs.put("name", name);
        userInputs.put("startDate", startDate);
        if (!startDate.contains("/")){
            userInputs.put("endDate", endDate);
        }
        userInputs.put("frequency", Integer.parseInt(frequency));
        userInputs.put("barcode", barcode);
        userInputs.put("notification", notificationsOn);

        System.out.println(userInputs);
        Toast.makeText(AddMedicationActivity.this, userInputs.toString(), Toast.LENGTH_LONG).show();

    }

    /*@Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        if (medicationPhoto!=null) {
            byte[] image = convertBitmapToByteArray(medicationPhoto);
            savedInstanceState.putByteArray(STATE_PHOTO, image);
            Toast.makeText(AddMedicationActivity.this, medicationPhoto.toString(), Toast.LENGTH_LONG).show();

        }
        savedInstanceState.putString(STATE_BARCODE, gotoScanner.getText().toString());
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }*/

    private byte[] convertBitmapToByteArray(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
    private Bitmap convertCompressedByteArrayToBitmap(byte[] src){
        return BitmapFactory.decodeByteArray(src, 0, src.length);
    }
}
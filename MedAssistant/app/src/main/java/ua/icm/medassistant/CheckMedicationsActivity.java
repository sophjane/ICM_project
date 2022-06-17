package ua.icm.medassistant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.TimeZone;

public class CheckMedicationsActivity extends AppCompatActivity {

    private final LinkedList<String> medications = new LinkedList<>();

    private RecyclerView recyclerView;
    private MedicationsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_medications);
        for (int i = 0; i < 20; i++) {
            medications.addLast("Med " + i);
        }

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MedicationsAdapter(this, medications);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
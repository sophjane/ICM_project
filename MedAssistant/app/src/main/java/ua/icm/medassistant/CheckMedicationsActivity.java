package ua.icm.medassistant;

import androidx.annotation.NonNull;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.TimeZone;

public class CheckMedicationsActivity extends AppCompatActivity {

    private final LinkedList<String> medications = new LinkedList<>();

    private RecyclerView recyclerView;
    private MedicationsAdapter adapter;
    private boolean twoPane = false;

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

        if(findViewById(R.id.medication_info_container) != null) {
            twoPane = true;
        }
    }

    public class MedicationsAdapter extends
            RecyclerView.Adapter<MedicationsAdapter.MedicationViewHolder> {

        private final LinkedList<String> medications;
        private LayoutInflater mInflater;

        public MedicationsAdapter(Context context, LinkedList<String> medications) {
            mInflater = LayoutInflater.from(context);
            this.medications = medications;
        }


        @NonNull
        @Override
        public MedicationsAdapter.MedicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.medications_item, parent, false);
            return new MedicationViewHolder(itemView, this);
        }

        @Override
        public void onBindViewHolder(@NonNull MedicationsAdapter.MedicationViewHolder holder, int position) {
            String current = medications.get(position);
            holder.medicationItemView.setText(current);
            holder.medicationItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (twoPane) {
                       // int selectedSong = holder.getAdapterPosition();
                        MedicationInformationFragment fragment = MedicationInformationFragment.newInstance();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.medication_info_container, fragment)
                                .addToBackStack(null)
                                .commit();
                    } else {
                        Context context = view.getContext();
                        Intent intent = new Intent(context, MedicationInformationActivity.class);
                        context.startActivity(intent);
                    }

                }
                                                         }

            );
        }

        @Override
        public int getItemCount() {
            return medications.size();
        }

        class MedicationViewHolder extends RecyclerView.ViewHolder {

            public final TextView medicationItemView;
            final MedicationsAdapter medicationsAdapter;
            public MedicationViewHolder(@NonNull View itemView, MedicationsAdapter medicationsAdapter) {
                super(itemView);
                medicationItemView = itemView.findViewById(R.id.medication);
                this.medicationsAdapter = medicationsAdapter;
            }


        }
    }
}
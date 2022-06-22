package ua.icm.medassistant;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.icm.medassistant.datamodel.City;
import ua.icm.medassistant.datamodel.CityGroup;
import ua.icm.medassistant.datamodel.Medication;
import ua.icm.medassistant.network.IpmaApiEndpoints;
import ua.icm.medassistant.network.RetrofitInstance;

public class CheckMedicationsActivity extends AppCompatActivity {

    private boolean mTwoPane = false;
    private RecyclerView recyclerView;
    private static int TIME_OUT =  50000;
    Timer timer;


    private static final int TEXT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        toolbar.setTitleTextColor(Color.WHITE);

        generateDataList();



        //Create handle for the RetrofitInstance interface
        /*IpmaApiEndpoints service = RetrofitInstance.getRetrofitInstance().create(IpmaApiEndpoints.class);

        Call<CityGroup> call = service.getCityParent();

        call.enqueue(new Callback<CityGroup>() {

            @Override
            public void onResponse(Call<CityGroup> call, Response<CityGroup> response) {
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<CityGroup> call, Throwable t) {
                Toast.makeText(CheckMedicationsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

       /* timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(CheckMedicationsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, TIME_OUT);*/
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.medication_list);
        List<Medication> meds = new ArrayList<>();
        meds.add(new Medication("Brufen", 0));
        recyclerView.setAdapter(new CityListViewAdapter(meds));
        // The city_weather_container only shows up when the screen's width is 600dp or larger
        if (findViewById(R.id.medication_info_container) != null) {
            mTwoPane = true;
        }
    }

    class CityListViewAdapter extends RecyclerView.Adapter<CityListViewAdapter.ViewHolder> {

        private final List<Medication> mValues;

        CityListViewAdapter(List<Medication> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.medication_list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mContentView.setText(mValues.get(position).getName());
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        int selectedCity = holder.mItem.getMed_id();
                        MedicationInformationFragment fragment = MedicationInformationFragment.newInstance(selectedCity);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.medication_info_container, fragment)
                                .addToBackStack(null)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, MedicationInformationActivity.class);
                        intent.putExtra("cityGlobalLocal", holder.mItem.getMed_id());
                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final View mView;
            final TextView mContentView;
            Medication mItem;

            ViewHolder(View view) {
                super(view);
                mView = view;
                mContentView = (TextView) view.findViewById(R.id.content);
            }
        }
    }


}
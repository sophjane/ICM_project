package ua.icm.medassistant;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.icm.medassistant.datamodel.Weather;
import ua.icm.medassistant.datamodel.WeatherGroup;
import ua.icm.medassistant.network.IpmaApiEndpoints;
import ua.icm.medassistant.network.RetrofitInstance;


public class MedicationInformationFragment extends Fragment {

    Button button;
    int globalIdLocal;
    IpmaApiEndpoints service = RetrofitInstance.getRetrofitInstance().create(IpmaApiEndpoints.class);
    private static final String LOG_TAG = MedicationInformationFragment.class.getSimpleName();
    private static final int TEXT_REQUEST = 1;
    private final String code_test = "5550587";

    Button scanner;

    public MedicationInformationFragment() {}

    public static MedicationInformationFragment newInstance(int selectedCity) {
        MedicationInformationFragment fragment = new MedicationInformationFragment();
        Bundle args = new Bundle();
        args.putInt("cityGlobalLocal", selectedCity);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey("cityGlobalLocal")) {
            globalIdLocal = getArguments().getInt("cityGlobalLocal");
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_medication_info, container, false);

        scanner = rootView.findViewById(R.id.scanner);

        scanner.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handleGoToScanner();
            }
        });

        return rootView;
    }

    private void handleGoToScanner() {
        Intent intent = new Intent(getActivity(), CodeScannerActivity.class);
        intent.putExtra("dummy", code_test);
        startActivityForResult(intent, TEXT_REQUEST);


      /*  timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(AddMedicationActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, TIME_OUT);*/

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        scanner.setText("Checked");
        scanner.setBackgroundColor(0xFF93C572);
        scanner.setClickable(false);

    }

}
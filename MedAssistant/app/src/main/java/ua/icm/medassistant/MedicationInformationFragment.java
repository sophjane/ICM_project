package ua.icm.medassistant;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MedicationInformationFragment extends Fragment {

    public MedicationInformationFragment() {
        // Required empty public constructor
    }

    public static MedicationInformationFragment newInstance() {
        MedicationInformationFragment fragment = new MedicationInformationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_medication_information, container, false);
        return rootView;
    }

}
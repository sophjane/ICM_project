package ua.icm.medassistant.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ua.icm.medassistant.entity.Medication;
import ua.icm.medassistant.repository.MedicationRepository;

public class MedicationViewModel extends AndroidViewModel {

    private MedicationRepository repository;

    private LiveData<List<Medication>> allMedications;

    public MedicationViewModel (Application application) {
        super(application);
        repository = new MedicationRepository(application);
        allMedications = repository.getAllMedications();
    }

    LiveData<List<Medication>> getAllMedications() { return allMedications; }

    public void insert(Medication medication) { repository.insert(medication); }
}
package ua.icm.medassistant.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import ua.icm.medassistant.dao.MedicationDao;
import ua.icm.medassistant.database.MedicationRoomDatabase;
import ua.icm.medassistant.entity.Medication;

public class MedicationRepository {

    private MedicationDao medicationDao;
    private LiveData<List<Medication>> allMedications;

    public MedicationRepository(Application application) {
        MedicationRoomDatabase db = MedicationRoomDatabase.getDatabase(application);
        medicationDao = db.medicationDao();
        allMedications = (LiveData<List<Medication>>) medicationDao.getAllMedications();
    }

    public LiveData<List<Medication>> getAllMedications() {
        return allMedications;
    }

    public void insert (Medication medication) {
        new insertAsyncTask(medicationDao).execute(medication);
    }

    private static class insertAsyncTask extends AsyncTask<Medication, Void, Void> {

        private MedicationDao mAsyncTaskDao;

        insertAsyncTask(MedicationDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Medication... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
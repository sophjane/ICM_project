package ua.icm.medassistant.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ua.icm.medassistant.dao.MedicationDao;
import ua.icm.medassistant.entity.Medication;

@Database(entities = {Medication.class}, version = 1)
public abstract class MedicationRoomDatabase extends RoomDatabase {

    public abstract MedicationDao medicationDao();

    private static MedicationRoomDatabase INSTANCE;

    public static MedicationRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MedicationRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    MedicationRoomDatabase.class, "medication_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
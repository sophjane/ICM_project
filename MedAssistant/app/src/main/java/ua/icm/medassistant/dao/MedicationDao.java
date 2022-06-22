package ua.icm.medassistant.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ua.icm.medassistant.entity.Medication;

@Dao
public interface MedicationDao {

    // The conflict strategy defines what happens,
    // if there is an existing entry.
    // The default action is ABORT.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Medication medication);

    // Simple query that does not take parameters and returns nothing.
    @Query("DELETE FROM medication_table")
    void deleteAll();

    // Simple query without parameters that returns values.
    @Query("SELECT * from medication_table")
    List<Medication> getAllMedications();

    // Query with parameter that returns a specific word or words.
    // @Query("SELECT * FROM medication_table WHERE name LIKE :word ")
    // public Medication findWord(String word);
}

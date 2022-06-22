package ua.icm.medassistant.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "medication_table")
public class Medication {

    @PrimaryKey(autoGenerate=true)
    @NonNull
    @ColumnInfo(name = "uid")
    private int uid;

/*    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "start_date")
    private Date startDate;

    @ColumnInfo(name = "end_date")
    private Date endDate;

    @ColumnInfo(name = "frequency")
    private int frequency;

    @ColumnInfo(name = "barcode")
    private String barcode;

    @ColumnInfo(name = "notification")
    private boolean notification;

    public Medication(String name, Date startDate, Date endDate, int frequency, String barcode, boolean notification) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.frequency = frequency;
        this.barcode = barcode;
        this.notification = notification;
    }*/

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    /*
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }*/
}
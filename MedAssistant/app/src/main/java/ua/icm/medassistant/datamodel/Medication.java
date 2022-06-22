package ua.icm.medassistant.datamodel;

public class Medication {
    private String name;
    private int med_id;

    public Medication(String name, int med_id){
        this.name= name;
        this.med_id = med_id;
    }

    public String getName() {
        return name;
    }

    public int getMed_id() {
        return med_id;
    }
}

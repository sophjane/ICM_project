package ua.icm.medassistant;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class MedicationsAdapter extends
        RecyclerView.Adapter<MedicationsAdapter.MedicationViewHolder> {

    private final LinkedList<String> medications;
    private LayoutInflater mInflater;
    private static final String LOG_TAG = MedicationsAdapter.class.getSimpleName();

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
    }

    @Override
    public int getItemCount() {
        return medications.size();
    }

    class MedicationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView medicationItemView;
        final MedicationsAdapter medicationsAdapter;
        public MedicationViewHolder(@NonNull View itemView, MedicationsAdapter medicationsAdapter) {
            super(itemView);
            medicationItemView = itemView.findViewById(R.id.medication);
            this.medicationsAdapter = medicationsAdapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            Intent intent = new Intent(context, MedicationInformationActivity.class);
            context.startActivity(intent);
        }
    }
}

package com.kivucapacity.workouttracker.myadapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kivucapacity.workouttracker.R;
import com.kivucapacity.workouttracker.SubRecordsActivity;
import com.kivucapacity.workouttracker.databinding.RecordItemLayoutBinding;
import com.kivucapacity.workouttracker.mymodels.RecordItemsModel;

import java.util.ArrayList;

public class RecordsMainAdapter extends RecyclerView.Adapter<RecordsMainAdapter.ViewHolder> {

    ArrayList<RecordItemsModel> mainRecordsItemsList;

    public RecordsMainAdapter(ArrayList<RecordItemsModel> mainRecordsItemsList) {
        this.mainRecordsItemsList = mainRecordsItemsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_item_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecordItemsModel item = mainRecordsItemsList.get(position);
        holder.binding.image.setImageResource(item.getImage());
        holder.binding.count.setText(String.valueOf(item.getCount()));
        holder.binding.fullCard.setOnClickListener(view -> {
            Intent iSubRecord = new Intent(view.getContext(), SubRecordsActivity.class);
            iSubRecord.putExtra("Title", item.getTitle());
            view.getContext().startActivity(iSubRecord);
        });
    }

    @Override
    public int getItemCount() {
        return mainRecordsItemsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        RecordItemLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RecordItemLayoutBinding.bind(itemView);
        }
    }

}

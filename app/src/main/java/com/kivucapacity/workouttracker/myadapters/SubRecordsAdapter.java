package com.kivucapacity.workouttracker.myadapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kivucapacity.workouttracker.R;
import com.kivucapacity.workouttracker.databinding.SubRecordsItemLayoutBinding;
import com.kivucapacity.workouttracker.mymodels.RecordItemsModel;

import java.util.ArrayList;

public class SubRecordsAdapter extends RecyclerView.Adapter<SubRecordsAdapter.ViewHolder> {

    ArrayList<RecordItemsModel> subRecordsItemsList;

    public SubRecordsAdapter(ArrayList<RecordItemsModel> subRecordsItemsList) {
        this.subRecordsItemsList = subRecordsItemsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_records_item_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecordItemsModel item = subRecordsItemsList.get(position);
        holder.binding.image.setImageResource(item.getImage());
        holder.binding.count.setText(String.valueOf(item.getCount()));
    }

    @Override
    public int getItemCount() {
        return subRecordsItemsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        SubRecordsItemLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = SubRecordsItemLayoutBinding.bind(itemView);
        }
    }

}

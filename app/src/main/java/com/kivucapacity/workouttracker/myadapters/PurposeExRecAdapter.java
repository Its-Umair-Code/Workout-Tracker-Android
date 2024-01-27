package com.kivucapacity.workouttracker.myadapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kivucapacity.workouttracker.R;
import com.kivucapacity.workouttracker.databinding.MainRecItemLayoutBinding;
import com.kivucapacity.workouttracker.mymodels.MainItemsModel;

import java.util.ArrayList;

public class PurposeExRecAdapter extends RecyclerView.Adapter<PurposeExRecAdapter.ViewHolder> {

    ArrayList<MainItemsModel> purposeExItemsList;

    public PurposeExRecAdapter(ArrayList<MainItemsModel> purposeExItemsList) {
        this.purposeExItemsList = purposeExItemsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_rec_item_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MainItemsModel item = purposeExItemsList.get(position);
        holder.binding.image.setImageResource(item.getImage());
        holder.binding.title.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return purposeExItemsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        MainRecItemLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = MainRecItemLayoutBinding.bind(itemView);
        }
    }
}

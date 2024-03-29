package com.kivucapacity.workouttracker.myadapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kivucapacity.workouttracker.PurposeExercises;
import com.kivucapacity.workouttracker.R;
import com.kivucapacity.workouttracker.databinding.MainRecItemLayoutBinding;
import com.kivucapacity.workouttracker.mymodels.MainItemsModel;

import java.util.ArrayList;

public class ShowExRecAdapter extends RecyclerView.Adapter<ShowExRecAdapter.ViewHolder> {

    ArrayList<MainItemsModel> showExItemsList;

    public ShowExRecAdapter(ArrayList<MainItemsModel> showExItemsList) {
        this.showExItemsList = showExItemsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_rec_item_layout, parent, false);
        return new ShowExRecAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MainItemsModel item = showExItemsList.get(position);
        holder.binding.image.setImageResource(item.getImage());
        holder.binding.title.setText(item.getTitle());
        holder.binding.fullCard.setOnClickListener(view -> {
            Intent iPurposeEx = new Intent(view.getContext(), PurposeExercises.class);
            iPurposeEx.putExtra("PTitle", item.getTitle());
            view.getContext().startActivity(iPurposeEx);
        });
    }

    @Override
    public int getItemCount() {
        return showExItemsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        MainRecItemLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = MainRecItemLayoutBinding.bind(itemView);
        }
    }

}

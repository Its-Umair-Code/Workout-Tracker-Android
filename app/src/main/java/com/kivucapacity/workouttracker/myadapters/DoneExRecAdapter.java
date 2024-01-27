package com.kivucapacity.workouttracker.myadapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kivucapacity.workouttracker.R;
import com.kivucapacity.workouttracker.databinding.DoneExRecItemLayoutBinding;
import com.kivucapacity.workouttracker.mymodels.MainItemsModel;

import java.util.ArrayList;

public class DoneExRecAdapter extends RecyclerView.Adapter<DoneExRecAdapter.ViewHolder> {

    ArrayList<MainItemsModel> doneExItemsList;
    private boolean[] mChecked;

    public DoneExRecAdapter(ArrayList<MainItemsModel> doneExItemsList) {
        this.doneExItemsList = doneExItemsList;
        this.mChecked = new boolean[doneExItemsList.size()];
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.done_ex_rec_item_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MainItemsModel item = doneExItemsList.get(position);
        holder.binding.imgEx.setImageResource(item.getImage());
        holder.binding.txtExName.setText(item.getTitle());
        holder.binding.checkBoxStartEx.setChecked(mChecked[position]);
        holder.binding.checkBoxStartEx.setOnClickListener(view -> mChecked[holder.getAdapterPosition()] = holder.binding.checkBoxStartEx.isChecked());
    }

    @Override
    public int getItemCount() {
        return doneExItemsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        DoneExRecItemLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DoneExRecItemLayoutBinding.bind(itemView);
        }
    }

    public boolean[] getDoneExercises() {
        return mChecked;
    }

}

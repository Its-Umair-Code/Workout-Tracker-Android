package com.kivucapacity.workouttracker.myadapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kivucapacity.workouttracker.R;
import com.kivucapacity.workouttracker.RecordsActivity;
import com.kivucapacity.workouttracker.ShowExercisesActivity;
import com.kivucapacity.workouttracker.databinding.MainRecItemLayoutBinding;
import com.kivucapacity.workouttracker.mymodels.MainItemsModel;

import java.util.ArrayList;

public class MainRecAdapter extends RecyclerView.Adapter<MainRecAdapter.ViewHolder> {

    ArrayList<MainItemsModel> mainItemsList;

    public MainRecAdapter(ArrayList<MainItemsModel> mainItemsList) {
        this.mainItemsList = mainItemsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_rec_item_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MainItemsModel item = mainItemsList.get(position);
        holder.binding.image.setImageResource(item.getImage());
        holder.binding.title.setText(item.getTitle());
        holder.binding.fullCard.setOnClickListener(view -> {
            if (item.getTitle().equals(view.getContext().getString(R.string.show_exercises))) {
                Intent iShowEx = new Intent(view.getContext(), ShowExercisesActivity.class);
                iShowEx.putExtra("Title", item.getTitle());
                iShowEx.putExtra("Type", 1);
                view.getContext().startActivity(iShowEx);
            } else if (item.getTitle().equals(view.getContext().getString(R.string.start_exercise))) {
                Intent iStartEx = new Intent(view.getContext(), ShowExercisesActivity.class);
                iStartEx.putExtra("Title", item.getTitle());
                iStartEx.putExtra("Type", 2);
                view.getContext().startActivity(iStartEx);
            }else{
                Intent iRecord = new Intent(view.getContext(), RecordsActivity.class);
                iRecord.putExtra("Title",item.getTitle());
                view.getContext().startActivity(iRecord);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mainItemsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        MainRecItemLayoutBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = MainRecItemLayoutBinding.bind(itemView);
        }
    }

}

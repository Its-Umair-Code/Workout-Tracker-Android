package com.kivucapacity.workouttracker;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.kivucapacity.workouttracker.databinding.ActivityShowExercisesBinding;
import com.kivucapacity.workouttracker.myadapters.ShowExRecAdapter;
import com.kivucapacity.workouttracker.myadapters.StartExRecAdapter;
import com.kivucapacity.workouttracker.mymodels.MainItemsModel;

import java.util.ArrayList;

public class ShowExercisesActivity extends AppCompatActivity {

    private ActivityShowExercisesBinding binding;
    private ArrayList<MainItemsModel> showExItemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowExercisesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.title.setText(getIntent().getStringExtra("Title"));
        int type = getIntent().getIntExtra("Type", 1);

        fillShowExItemsList();
        binding.showExRec.setLayoutManager(new LinearLayoutManager(this));

        if (type == 1) {
            ShowExRecAdapter adapter1 = new ShowExRecAdapter(showExItemsList);
            binding.showExRec.setAdapter(adapter1);
        }
        if (type == 2) {
            StartExRecAdapter adapter2 = new StartExRecAdapter(showExItemsList);
            binding.showExRec.setAdapter(adapter2);
        }

        binding.backImage.setOnClickListener(view -> getOnBackPressedDispatcher().onBackPressed());

    }

    private void fillShowExItemsList() {
        showExItemsList = new ArrayList<>();
        showExItemsList.add(new MainItemsModel(R.drawable.chest, getString(R.string.chest_exercises)));
        showExItemsList.add(new MainItemsModel(R.drawable.glute, getString(R.string.glute_exercises)));
        showExItemsList.add(new MainItemsModel(R.drawable.flat_stomach, getString(R.string.flat_stomach_exercises)));
    }
}
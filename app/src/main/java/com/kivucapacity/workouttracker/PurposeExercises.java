package com.kivucapacity.workouttracker;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.kivucapacity.workouttracker.databinding.ActivityPurposeExercisesBinding;
import com.kivucapacity.workouttracker.myadapters.PurposeExRecAdapter;
import com.kivucapacity.workouttracker.mymodels.MainItemsModel;

import java.util.ArrayList;

public class PurposeExercises extends AppCompatActivity {

    private ActivityPurposeExercisesBinding binding;
    private ArrayList<MainItemsModel> chestExItemsList;
    private ArrayList<MainItemsModel> gluteExItemsList;
    private ArrayList<MainItemsModel> stomachExItemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPurposeExercisesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String title = getIntent().getStringExtra("PTitle");
        binding.title.setText(title);

        fillPurposeExItemsList();
        binding.purposeExRec.setLayoutManager(new LinearLayoutManager(this));
        PurposeExRecAdapter adapter;
        if (title.equals(getString(R.string.chest_exercises))) {
            adapter = new PurposeExRecAdapter(chestExItemsList);
        } else if (title.equals(getString(R.string.glute_exercises))) {
            adapter = new PurposeExRecAdapter(gluteExItemsList);
        } else {
            adapter = new PurposeExRecAdapter(stomachExItemsList);
        }
        binding.purposeExRec.setAdapter(adapter);

        binding.backImage.setOnClickListener(view -> getOnBackPressedDispatcher().onBackPressed());

    }

    private void fillPurposeExItemsList() {
        chestExItemsList = new ArrayList<>();
        chestExItemsList.add(new MainItemsModel(R.drawable.barbell_bench_press, getString(R.string.barbell_bench_press)));
        chestExItemsList.add(new MainItemsModel(R.drawable.chest_dip, getString(R.string.chest_dip)));
        chestExItemsList.add(new MainItemsModel(R.drawable.dumbbell_bench_press, getString(R.string.dumbbell_bench_press)));
        chestExItemsList.add(new MainItemsModel(R.drawable.push_ups, getString(R.string.push_ups)));
        chestExItemsList.add(new MainItemsModel(R.drawable.standing_plank, getString(R.string.standing_plank)));

        gluteExItemsList = new ArrayList<>();
        gluteExItemsList.add(new MainItemsModel(R.drawable.back_squat, getString(R.string.back_squat)));
        gluteExItemsList.add(new MainItemsModel(R.drawable.conventional_deadlift, getString(R.string.conventional_deadlift)));
        gluteExItemsList.add(new MainItemsModel(R.drawable.donkey_kick, getString(R.string.donkey_kick)));
        gluteExItemsList.add(new MainItemsModel(R.drawable.hip_thrust, getString(R.string.hip_thrust)));
        gluteExItemsList.add(new MainItemsModel(R.drawable.walking_lunge, getString(R.string.walking_lunge)));

        stomachExItemsList = new ArrayList<>();
        stomachExItemsList.add(new MainItemsModel(R.drawable.bicycle_crunches, getString(R.string.bicycle_crunches)));
        stomachExItemsList.add(new MainItemsModel(R.drawable.boat_pose, getString(R.string.boat_pose)));
        stomachExItemsList.add(new MainItemsModel(R.drawable.leg_raises, getString(R.string.leg_raises)));
        stomachExItemsList.add(new MainItemsModel(R.drawable.side_planks, getString(R.string.side_planks)));
        stomachExItemsList.add(new MainItemsModel(R.drawable.situps, getString(R.string.situps)));
        stomachExItemsList.add(new MainItemsModel(R.drawable.standing_plank, getString(R.string.standing_plank)));
        stomachExItemsList.add(new MainItemsModel(R.drawable.toe_reaches, getString(R.string.toe_reaches)));

    }

}
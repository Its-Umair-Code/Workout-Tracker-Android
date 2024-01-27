package com.kivucapacity.workouttracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.kivucapacity.workouttracker.databinding.ActivityDoneExercisesBinding;
import com.kivucapacity.workouttracker.myadapters.DoneExRecAdapter;
import com.kivucapacity.workouttracker.mymodels.MainItemsModel;

import java.util.ArrayList;

public class DoneExercisesActivity extends AppCompatActivity {

    private ActivityDoneExercisesBinding binding;

    private ArrayList<MainItemsModel> chestExItemsList;
    private ArrayList<MainItemsModel> gluteExItemsList;
    private ArrayList<MainItemsModel> stomachExItemsList;
    String title;
    private ArrayList<MainItemsModel> currentExItemsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDoneExercisesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        title = getIntent().getStringExtra("DTitle");
        binding.title.setText(title);

        MobileAds.initialize(this, initializationStatus -> {
        });
        loadAdmobAd();

        fillDoneExList();
        binding.doneExRec.setLayoutManager(new LinearLayoutManager(this));
        DoneExRecAdapter adapter;
        if (title.equals(getString(R.string.chest_exercises))) {
            currentExItemsList = chestExItemsList;
        } else if (title.equals(getString(R.string.glute_exercises))) {
            currentExItemsList = gluteExItemsList;
        } else {
            currentExItemsList = stomachExItemsList;
        }
        adapter = new DoneExRecAdapter(currentExItemsList);
        binding.doneExRec.setAdapter(adapter);

        binding.btnDoneEx.setOnClickListener(view -> performDoneExActions());

        binding.backImage.setOnClickListener(view -> getOnBackPressedDispatcher().onBackPressed());

    }

    private void loadAdmobAd() {
        binding.doneBanner.setVisibility(View.VISIBLE);
        AdRequest adRequest = new AdRequest.Builder().build();
        binding.doneBanner.loadAd(adRequest);
        binding.doneBanner.setAdListener(new com.google.android.gms.ads.AdListener() {
            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
                super.onAdClicked();
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
                binding.doneBanner.loadAd(adRequest);
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                // Code to be executed when an ad request fails.
                binding.doneBanner.loadAd(adRequest);
            }

            @Override
            public void onAdImpression() {
                // Code to be executed when an impression is recorded
                // for an ad.
                super.onAdImpression();
            }

            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                super.onAdLoaded();
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                super.onAdOpened();
            }
        });
    }

    private void performDoneExActions() {

        DoneExRecAdapter adapter = (DoneExRecAdapter) binding.doneExRec.getAdapter();
        if (adapter != null) {
            boolean[] doneEx = adapter.getDoneExercises();
            int check = 0;
            for (boolean ex : doneEx) {
                if (ex) {
                    check++;
                }
            }
            if (check > 0) {
                SharedPreferences exercisesP = getSharedPreferences("Exercises", Context.MODE_PRIVATE);
                SharedPreferences.Editor exEditor = exercisesP.edit();
                int exCount = exercisesP.getInt(title, 0);
                exCount++;
                exEditor.putInt(title, exCount);
                exEditor.apply();
                SharedPreferences subExercisesP = getSharedPreferences("Sub Exercises", Context.MODE_PRIVATE);
                SharedPreferences.Editor subExEditor = subExercisesP.edit();
                for (int i = 0; i < doneEx.length; i++) {
                    if (doneEx[i]) {
                        int currentExCount = subExercisesP.getInt(currentExItemsList.get(i).getTitle(), 0);
                        currentExCount++;
                        subExEditor.putInt(currentExItemsList.get(i).getTitle(), currentExCount);
                    }
                }
                subExEditor.apply();
                Toast.makeText(this, "Exercise Done!", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Please do some exercise!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void fillDoneExList() {

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
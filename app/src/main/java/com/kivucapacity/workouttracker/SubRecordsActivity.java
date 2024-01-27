package com.kivucapacity.workouttracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.kivucapacity.workouttracker.databinding.ActivitySubRecordsBinding;
import com.kivucapacity.workouttracker.myadapters.SubRecordsAdapter;
import com.kivucapacity.workouttracker.mymodels.RecordItemsModel;

import java.util.ArrayList;

public class SubRecordsActivity extends AppCompatActivity {

    private ActivitySubRecordsBinding binding;

    private ArrayList<RecordItemsModel> chestExItemsList;
    private ArrayList<RecordItemsModel> gluteExItemsList;
    private ArrayList<RecordItemsModel> stomachExItemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubRecordsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String title = getIntent().getStringExtra("Title");

        binding.title.setText(title);

        MobileAds.initialize(this, initializationStatus -> {
        });
        loadAdmobAd();

        fillSubRecordsItemsList();
        binding.subRecordsExRec.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
        if (title.equals(getString(R.string.chest_exercises))) {
            binding.subRecordsExRec.setAdapter(new SubRecordsAdapter(chestExItemsList));
        } else if (title.equals(getString(R.string.glute_exercises))) {
            binding.subRecordsExRec.setAdapter(new SubRecordsAdapter(gluteExItemsList));
        } else {
            binding.subRecordsExRec.setAdapter(new SubRecordsAdapter(stomachExItemsList));
        }

        binding.backImage.setOnClickListener(view -> getOnBackPressedDispatcher().onBackPressed());

    }

    private void loadAdmobAd() {
        binding.subRecordBanner.setVisibility(View.VISIBLE);
        AdRequest adRequest = new AdRequest.Builder().build();
        binding.subRecordBanner.loadAd(adRequest);
        binding.subRecordBanner.setAdListener(new com.google.android.gms.ads.AdListener() {
            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
                super.onAdClicked();
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
                binding.subRecordBanner.loadAd(adRequest);
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                // Code to be executed when an ad request fails.
                binding.subRecordBanner.loadAd(adRequest);
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

    private void fillSubRecordsItemsList() {
        SharedPreferences subExercisesP = getSharedPreferences("Sub Exercises", Context.MODE_PRIVATE);
        chestExItemsList = new ArrayList<>();
        chestExItemsList.add(new RecordItemsModel(R.drawable.barbell_bench_press, getString(R.string.barbell_bench_press), subExercisesP.getInt(getString(R.string.barbell_bench_press), 0)));
        chestExItemsList.add(new RecordItemsModel(R.drawable.chest_dip, getString(R.string.chest_dip), subExercisesP.getInt(getString(R.string.chest_dip), 0)));
        chestExItemsList.add(new RecordItemsModel(R.drawable.dumbbell_bench_press, getString(R.string.dumbbell_bench_press), subExercisesP.getInt(getString(R.string.dumbbell_bench_press), 0)));
        chestExItemsList.add(new RecordItemsModel(R.drawable.push_ups, getString(R.string.push_ups), subExercisesP.getInt(getString(R.string.push_ups), 0)));
        chestExItemsList.add(new RecordItemsModel(R.drawable.standing_plank, getString(R.string.standing_plank), subExercisesP.getInt(getString(R.string.standing_plank), 0)));

        gluteExItemsList = new ArrayList<>();
        gluteExItemsList.add(new RecordItemsModel(R.drawable.back_squat, getString(R.string.back_squat), subExercisesP.getInt(getString(R.string.back_squat), 0)));
        gluteExItemsList.add(new RecordItemsModel(R.drawable.conventional_deadlift, getString(R.string.conventional_deadlift), subExercisesP.getInt(getString(R.string.conventional_deadlift), 0)));
        gluteExItemsList.add(new RecordItemsModel(R.drawable.donkey_kick, getString(R.string.donkey_kick), subExercisesP.getInt(getString(R.string.donkey_kick), 0)));
        gluteExItemsList.add(new RecordItemsModel(R.drawable.hip_thrust, getString(R.string.hip_thrust), subExercisesP.getInt(getString(R.string.hip_thrust), 0)));
        gluteExItemsList.add(new RecordItemsModel(R.drawable.walking_lunge, getString(R.string.walking_lunge), subExercisesP.getInt(getString(R.string.walking_lunge), 0)));

        stomachExItemsList = new ArrayList<>();
        stomachExItemsList.add(new RecordItemsModel(R.drawable.bicycle_crunches, getString(R.string.bicycle_crunches), subExercisesP.getInt(getString(R.string.bicycle_crunches), 0)));
        stomachExItemsList.add(new RecordItemsModel(R.drawable.boat_pose, getString(R.string.boat_pose), subExercisesP.getInt(getString(R.string.boat_pose), 0)));
        stomachExItemsList.add(new RecordItemsModel(R.drawable.leg_raises, getString(R.string.leg_raises), subExercisesP.getInt(getString(R.string.leg_raises), 0)));
        stomachExItemsList.add(new RecordItemsModel(R.drawable.side_planks, getString(R.string.side_planks), subExercisesP.getInt(getString(R.string.side_planks), 0)));
        stomachExItemsList.add(new RecordItemsModel(R.drawable.situps, getString(R.string.situps), subExercisesP.getInt(getString(R.string.situps), 0)));
        stomachExItemsList.add(new RecordItemsModel(R.drawable.standing_plank, getString(R.string.standing_plank), subExercisesP.getInt(getString(R.string.standing_plank), 0)));
        stomachExItemsList.add(new RecordItemsModel(R.drawable.toe_reaches, getString(R.string.toe_reaches), subExercisesP.getInt(getString(R.string.toe_reaches), 0)));
    }
}
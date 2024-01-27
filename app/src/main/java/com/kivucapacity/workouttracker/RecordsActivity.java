package com.kivucapacity.workouttracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.kivucapacity.workouttracker.databinding.ActivityRecordsBinding;
import com.kivucapacity.workouttracker.myadapters.RecordsMainAdapter;
import com.kivucapacity.workouttracker.mymodels.RecordItemsModel;

import java.util.ArrayList;

public class RecordsActivity extends AppCompatActivity {

    private ActivityRecordsBinding binding;
    private ArrayList<RecordItemsModel> mainRecordsItemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecordsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.title.setText(getIntent().getStringExtra("Title"));

        fillMainRecordsItemsList();
        binding.mainRecordsExRec.setLayoutManager(new LinearLayoutManager(this));
        RecordsMainAdapter adapter = new RecordsMainAdapter(mainRecordsItemsList);
        binding.mainRecordsExRec.setAdapter(adapter);

        binding.backImage.setOnClickListener(view -> getOnBackPressedDispatcher().onBackPressed());


    }

    private void fillMainRecordsItemsList() {
        SharedPreferences exercisesP = getSharedPreferences("Exercises", Context.MODE_PRIVATE);
        mainRecordsItemsList = new ArrayList<>();
        mainRecordsItemsList.add(new RecordItemsModel(R.drawable.chest, getString(R.string.chest_exercises), exercisesP.getInt(getString(R.string.chest_exercises), 0)));
        mainRecordsItemsList.add(new RecordItemsModel(R.drawable.glute, getString(R.string.glute_exercises), exercisesP.getInt(getString(R.string.glute_exercises), 0)));
        mainRecordsItemsList.add(new RecordItemsModel(R.drawable.flat_stomach, getString(R.string.flat_stomach_exercises), exercisesP.getInt(getString(R.string.flat_stomach_exercises), 0)));
    }

}
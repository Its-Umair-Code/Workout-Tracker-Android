package com.kivucapacity.workouttracker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.kivucapacity.workouttracker.databinding.ActivityMainBinding;
import com.kivucapacity.workouttracker.myadapters.MainRecAdapter;
import com.kivucapacity.workouttracker.mymodels.MainItemsModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private ArrayList<MainItemsModel> mainItemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MobileAds.initialize(this, initializationStatus -> {
        });
        loadAdmobAd();

        binding.myNavigationView.setItemIconTintList(null);
        binding.content.barImage.setOnClickListener(v -> binding.myDrawerLayout.openDrawer(GravityCompat.START));
        binding.myNavigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.menu_shareApp) {

                try {
                    String appUrl = "https://play.google.com/store/apps/details?id=" + getPackageName();
                    Intent iShare = new Intent(Intent.ACTION_SEND);
                    iShare.setType("text/plain"); // Plain text share karwany ka code ha
                    iShare.putExtra(Intent.EXTRA_TEXT, "\"Embark on a holistic fitness journey with the Workout Tracker app, your comprehensive companion for achieving your fitness goals. Tailored to empower users in their quest for a healthier lifestyle, this application focuses on three major purpose exercises: Chest, Glute, and Flat Stomach workouts.\n\n" + appUrl);
                    // Jb hamain user sy choose karwana ho ky us ny konsy app ky through send karna ha :
                    startActivity(Intent.createChooser(iShare, "Share via"));
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Oops! Something went wrong.", Toast.LENGTH_SHORT).show();
                }

            } else if (id == R.id.menu_rateApp) {

                try {
                    String appUrl = "https://play.google.com/store/apps/details?id=" + getPackageName();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(appUrl));
                    startActivity(i);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Oops! Something went wrong.\nMake sure your internet connection.", Toast.LENGTH_SHORT).show();
                }

            } else if (id == R.id.menu_contactUs) {

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"kivucapacity2023@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Workout Tracker - Feedback");
                try {
                    startActivity(Intent.createChooser(intent, "Send Email"));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity.this, "No email app found!", Toast.LENGTH_SHORT).show();
                }

            } else {

                try {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("https://kivucapacity.blogspot.com/2023/11/workout-tracker-privacy-policy.html"));
                    startActivity(i);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Oops! Something went wrong.\\nMake sure your internet connection.", Toast.LENGTH_SHORT).show();
                }

            }
            binding.myDrawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        fillMainItemsList();
        binding.content.mainRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        MainRecAdapter adapter = new MainRecAdapter(mainItemsList);
        binding.content.mainRecycler.setAdapter(adapter);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (binding.myDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    binding.myDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    finish();
                }
            }
        };

        getOnBackPressedDispatcher().addCallback(this, callback);

    }

    private void loadAdmobAd() {
        binding.content.mainBanner.setVisibility(View.VISIBLE);
        AdRequest adRequest = new AdRequest.Builder().build();
        binding.content.mainBanner.loadAd(adRequest);
        binding.content.mainBanner.setAdListener(new com.google.android.gms.ads.AdListener() {
            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
                super.onAdClicked();
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
                binding.content.mainBanner.loadAd(adRequest);
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                // Code to be executed when an ad request fails.
                binding.content.mainBanner.loadAd(adRequest);
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

    private void fillMainItemsList() {
        mainItemsList = new ArrayList<>();
        mainItemsList.add(new MainItemsModel(R.drawable.start_exercise, getString(R.string.start_exercise)));
        mainItemsList.add(new MainItemsModel(R.drawable.show_exercises, getString(R.string.show_exercises)));
        mainItemsList.add(new MainItemsModel(R.drawable.my_record, getString(R.string.my_record)));
    }

}
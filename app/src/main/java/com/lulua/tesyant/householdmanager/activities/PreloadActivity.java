package com.lulua.tesyant.householdmanager.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.lulua.tesyant.householdmanager.R;
import com.lulua.tesyant.householdmanager.db.FixedNeedsHelper;
import com.lulua.tesyant.householdmanager.db.UnfixedNeedsHelper;
import com.lulua.tesyant.householdmanager.models.FixedNeeds;
import com.lulua.tesyant.householdmanager.models.UnfixedNeeds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PreloadActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preload);

        new LoadData().execute();
    }

    private class LoadData extends AsyncTask<Void, Integer, Void>{

        FixedNeedsHelper fixedNeedsHelper;
        UnfixedNeedsHelper unfixedNeedsHelper;
        AppPreference appPreference;
        double progress;
        double maxProgress = 100;

        @Override
        public void onPreExecute() {
            fixedNeedsHelper = new FixedNeedsHelper(PreloadActivity.this);
            unfixedNeedsHelper = new UnfixedNeedsHelper(PreloadActivity.this);
            appPreference = new AppPreference(PreloadActivity.this);
        }

        @Override
        protected Void doInBackground(Void... params) {
            boolean firstRun = appPreference.getFirstRun();
            Log.d("First Run ", ": " + firstRun);

            if (firstRun) {
                ArrayList<FixedNeeds> fixedNeeds = preLoadRaw();
                ArrayList<UnfixedNeeds> unfixedNeeds = preLoadInRaw();
                Log.d("size", " : " + fixedNeeds.size());
                progress = 30;
                publishProgress((int)progress);
                fixedNeedsHelper.open();
                unfixedNeedsHelper.open();

                Double progressMaxInsert = 80.0;
                Double progressFixedDiff = (progressMaxInsert - progress) / fixedNeeds.size();
                Double progressUnfixedDiff = (progressFixedDiff - progress) / unfixedNeeds.size();

                fixedNeedsHelper.insertTransaction(fixedNeeds);
                unfixedNeedsHelper.insertTransaction(unfixedNeeds);

                fixedNeedsHelper.close();
                unfixedNeedsHelper.close();
                appPreference.setFirstRun(false);

                publishProgress((int)maxProgress);
            }

            else {
                try {
                    synchronized (this) {
                        this.wait(2000);

                        publishProgress(50);
                        this.wait(2000);
                        publishProgress((int)maxProgress);
                    }
                }

                catch (Exception e) {

                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected void onPostExecute(Void result) {
            Toast.makeText(PreloadActivity.this, "Data have been saved", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(PreloadActivity.this, NavDrawerActivity.class));
        }
    }

    private ArrayList<FixedNeeds> preLoadRaw() {
        ArrayList<FixedNeeds> fixedNeeds = new ArrayList<>();
        String line = null;
        BufferedReader reader;
        try {
            Resources resources = getResources();
            InputStream raw_keb = resources.openRawResource(R.raw.kebtetap);
            reader = new BufferedReader(new InputStreamReader(raw_keb));
            int count = 0;
            do {
                line = reader.readLine();
                String[] splitstr = line.split("\t");
                FixedNeeds fixedNeeds1;
                fixedNeeds1 = new FixedNeeds();
                fixedNeeds.add(fixedNeeds1);
                count++;
            }
            while (line != null);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return fixedNeeds;
    }

    private ArrayList<UnfixedNeeds> preLoadInRaw() {
        ArrayList<UnfixedNeeds> unfixedNeeds = new ArrayList<>();
        String line = null;
        BufferedReader reader;
        try {
            Resources resources = getResources();
            InputStream raw_keb = resources.openRawResource(R.raw.kebtidaktetap);
            reader = new BufferedReader(new InputStreamReader(raw_keb));
            int count = 0;
            do {
                line = reader.readLine();
                String[] splitstr = line.split("\t");
                UnfixedNeeds unfixedNeeds1;
                unfixedNeeds1 = new UnfixedNeeds();
                unfixedNeeds.add(unfixedNeeds1);
                count++;
            }
            while (line != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return unfixedNeeds;
    }

}

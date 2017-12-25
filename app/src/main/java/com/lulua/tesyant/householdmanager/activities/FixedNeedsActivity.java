package com.lulua.tesyant.householdmanager.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lulua.tesyant.householdmanager.R;
import com.lulua.tesyant.householdmanager.db.FixedNeedsHelper;
import com.lulua.tesyant.householdmanager.models.FixedNeeds;

import java.util.ArrayList;

public class FixedNeedsActivity extends AppCompatActivity {

    private FixedNeedsHelper fixedNeedsHelper;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_needs);

        recyclerView = findViewById(R.id.btn_recyclerview);

        fixedNeedsHelper = new FixedNeedsHelper(this);
        fixedNeedsHelper.open();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<FixedNeeds> result = new ArrayList<>();
        result = fixedNeedsHelper.getAllData();

        final String[] results = new String[result.size()];

        if (result.size() > 0) {
            for (int i=0; i<result.size(); i++) {
                results[i] = String.valueOf(result.get(i));
            }
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

    }
}

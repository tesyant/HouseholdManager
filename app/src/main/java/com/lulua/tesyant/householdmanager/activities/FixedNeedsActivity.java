package com.lulua.tesyant.householdmanager.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.lulua.tesyant.householdmanager.R;
import com.lulua.tesyant.householdmanager.adapter.FixedNeedsAdapter;
import com.lulua.tesyant.householdmanager.db.FixedNeedsHelper;
import com.lulua.tesyant.householdmanager.models.FixedNeeds;

import java.util.ArrayList;

public class FixedNeedsActivity extends AppCompatActivity {

    FixedNeedsHelper fixedNeedsHelper;
    FixedNeedsAdapter fixedNeedsAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_needs);

        recyclerView = findViewById(R.id.btn_recyclerview);

        fixedNeedsHelper = new FixedNeedsHelper(this);
        fixedNeedsAdapter = new FixedNeedsAdapter(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(fixedNeedsAdapter);

        fixedNeedsHelper.open();
        ArrayList<FixedNeeds> fixedNeeds = fixedNeedsHelper.getAllData();
        Log.e("test", "ok");

        fixedNeedsHelper.close();
        fixedNeedsAdapter.addItem(fixedNeeds);
    }
}

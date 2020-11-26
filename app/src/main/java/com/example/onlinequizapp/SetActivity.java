package com.example.onlinequizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;

public class SetActivity extends AppCompatActivity {

    private GridView sets_grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        Toolbar toolbar=findViewById(R.id.settoolbar);
        setSupportActionBar(toolbar);

        String Title=getIntent().getStringExtra("CATEGORY");
        getSupportActionBar().setTitle(Title);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sets_grid=findViewById(R.id.setgrid);

        SetAdapter adapter=new SetAdapter(6);
        sets_grid.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home)
        {
            SetActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);

    }
}

package com.nykj.simplemultiadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_list = findViewById(R.id.lv_list);
        MyAdapter adapter = new MyAdapter();
        lv_list.setAdapter(adapter);

        List<Entity> entities = new ArrayList<>();
        entities.add(new Entity(1));
        entities.add(new Entity(1));
        entities.add(new Entity(1));
        entities.add(new Entity(1));
        entities.add(new Entity(1));

        entities.add(new Entity(2));
        entities.add(new Entity(2));
        entities.add(new Entity(1));
        entities.add(new Entity(2));
        entities.add(new Entity(2));

        entities.add(new Entity(1));
        entities.add(new Entity(2));
        entities.add(new Entity(1));
        entities.add(new Entity(2));
        entities.add(new Entity(1));

        entities.add(new Entity(3));
        entities.add(new Entity(2));
        entities.add(new Entity(2));
        entities.add(new Entity(2));
        entities.add(new Entity(2));

        adapter.update(entities);
    }
}
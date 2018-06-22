package com.chen.arxiv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chen.arxiv.adapter.PaperAdapter;
import com.chen.arxiv.model.Feed;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class SearchResultActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getSupportActionBar().hide();
        setContentView(R.layout.activity_search_result);
        recyclerView = findViewById(R.id.recyclerView);

        Serializer serializer = new Persister();

        Intent intent = getIntent();

        String queryResult = intent.getStringExtra("queryResult");

        try {
            final Feed feed = serializer.read(Feed.class, queryResult);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);

            PaperAdapter adapter = new PaperAdapter(feed, this);
            recyclerView.setAdapter(adapter);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

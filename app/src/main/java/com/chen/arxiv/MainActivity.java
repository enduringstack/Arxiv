package com.chen.arxiv;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.chen.arxiv.adapter.FragmentsAdapter;
import com.chen.arxiv.adapter.PaperAdapter;
import com.chen.arxiv.model.Feed;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TabLayout tab;
    private ViewPager pager;
    private List<String> list;
    private FloatingSearchView searchView;
    private OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initData();
        pager.setAdapter(new FragmentsAdapter(getSupportFragmentManager(), list));
        tab.setupWithViewPager(pager);

        searchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
            }

            @Override
            public void onSearchAction(String currentQuery) {
                initSearchViewData(currentQuery);
            }
        });

        searchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {

            }
        });
    }

    private void initSearchViewData(String newQuery) {
        String query = null;
        try {
            String search = "\"" + newQuery + "\"";

            query = URLEncoder.encode(search, "utf-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String queryUrl = "http://export.arxiv.org/api/query?search_query=all:" + query +
                "&start=0&max_results=30&sortBy=lastUpdatedDate&sortOrder=descending";

        Request.Builder builder = new Request.
                Builder()
                .url(queryUrl);
        builder.method("GET", null);
        Request request = builder.build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String paperLists = response.body().string();

                Log.e(TAG, "onResponse: " + paperLists);

                Intent intent = new Intent(MainActivity.this, SearchResultActivity.class);
                intent.putExtra("queryResult", paperLists);
                startActivity(intent);

            }
        });

    }

    private void initData() {
        list = new ArrayList<>();
        list.add("CV and Pattern Recognition");
        list.add("Hardware Architecture");
        list.add("Computation and Language");
        list.add("Artificial Intelligence");
        list.add("Graphics");
        list.add("Signal Processing");
        list.add("Maths");
        list.add("Physics");
    }

    private void initViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.pager = findViewById(R.id.viewPager);
        this.tab = findViewById(R.id.tabLayout);
        this.searchView = findViewById(R.id.floating_search_view);
    }


}

package com.chen.arxiv.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chen.arxiv.R;
import com.chen.arxiv.adapter.PaperAdapter;
import com.chen.arxiv.model.Entry;
import com.chen.arxiv.model.Feed;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ComputerVisionFragment extends Fragment {

    private RecyclerView recyclerView;

    private OkHttpClient client = new OkHttpClient();

    public ComputerVisionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_computer_vision, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        initData();

        return view;
    }

    private void initData() {
        Request.Builder builder = new Request.
                Builder()
                .url("http://export.arxiv.org/api/query?search_query=cat:cs.CV&start=0&max_results=30&sortBy=lastUpdatedDate&sortOrder=descending");
        builder.method("GET", null);
        Request request = builder.build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String paperLists = response.body().string();

                Serializer serializer = new Persister();

                try {
                    final Feed feed = serializer.read(Feed.class, paperLists);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                            recyclerView.setLayoutManager(linearLayoutManager);

                            PaperAdapter adapter = new PaperAdapter(feed, getActivity());
                            recyclerView.setAdapter(adapter);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

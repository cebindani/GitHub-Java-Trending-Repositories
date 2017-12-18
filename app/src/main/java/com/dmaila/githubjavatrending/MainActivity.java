package com.dmaila.githubjavatrending;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.dmaila.githubjavatrending.adapters.RepositoryAdapter;
import com.dmaila.githubjavatrending.data.Repositories;
import com.dmaila.githubjavatrending.data.Repository;
import com.dmaila.githubjavatrending.utils.ApiUtils;
import com.dmaila.githubjavatrending.utils.EndlessRecyclerViewScrollListener;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private final int DEFAULT_PAGE = 1;
    private List<Repository> repositories = new ArrayList<>();
    private EndlessRecyclerViewScrollListener scrollListener;
    private int page = DEFAULT_PAGE;
    private RepositoryAdapter repositoryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getRepositories(page);

    }

    private void initViews() {
        RecyclerView recyclerView = findViewById(R.id.root_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        repositoryAdapter = new RepositoryAdapter();
        recyclerView.setAdapter(repositoryAdapter);


        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {

                getRepositoriesNextPage();
            }
        };
        recyclerView.addOnScrollListener(scrollListener);
    }


    private void getRepositoriesNextPage() {

        page++;
        Toast.makeText(MainActivity.this, "loading more...\npage " + page, Toast.LENGTH_SHORT).show();

        getRepositories(page);
    }

    private void getRepositories(int page) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(ApiUtils.buildSearchJavaRepositoriesURL(page))
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //                    Intent intent = new Intent(getApplicationContext(), ErrorActivity.class);
//                startActivity(intent);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);

                }
                repositories = getRepositoriesModel(response.body().string());

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateViews((ArrayList<Repository>) repositories);

                    }
                });
            }
        });

    }


    private List<Repository> getRepositoriesModel(String responseJson) throws IOException {

        final Moshi moshi = new Moshi.Builder().build();
        final JsonAdapter<Repositories> jsonAdapter = moshi.adapter(Repositories.class);

        Repositories repositories = jsonAdapter.fromJson(responseJson);
        return repositories.getRepositories();

    }

    private void updateViews(ArrayList<Repository> repositories) {
        repositoryAdapter.setAdapterList(repositories, this);
        this.repositories.addAll(repositories);
    }
}

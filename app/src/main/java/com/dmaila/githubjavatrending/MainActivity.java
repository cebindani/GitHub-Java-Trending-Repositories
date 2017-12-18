package com.dmaila.githubjavatrending;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.dmaila.githubjavatrending.adapters.RepositoryAdapter;
import com.dmaila.githubjavatrending.data.Repository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AsyncResponse {

    private final int defaultPage = 1;
    RecyclerView recyclerView;
    List<Repository> repositories = new ArrayList<>();
    //    private EndlessRecyclerViewScrollListener scrollListener;
    private int page = defaultPage;
    private RepositoryAdapter repositoryAdapter;
    private LinearLayoutManager layoutManager;
    private boolean isNewRequest = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
//            @Override
//            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
//                getRepositoriesNextPage(page + 1);
//            }
//        };
//        recyclerView.addOnScrollListener(scrollListener);

        getRepositories(page);

    }

    private void initViews() {
        recyclerView = findViewById(R.id.root_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        repositoryAdapter = new RepositoryAdapter();
        recyclerView.setAdapter(repositoryAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int adapterPosition = layoutManager.findLastVisibleItemPosition();

                if (adapterPosition == repositoryAdapter.getItemCount() - 1) {
                    onLoadMore(false);
                }
            }

        });
    }


    private void onLoadMore(boolean isNewRequest) {
        if (!isNewRequest) {
            page++;
            Toast.makeText(MainActivity.this, "loading more...", Toast.LENGTH_SHORT).show();
            getRepositoriesNextPage(page);

        } else {
            repositories.clear();
            getRepositories(page);
        }

    }

    private void getRepositoriesNextPage(int page) {

        Toast.makeText(this, "page = " + page, Toast.LENGTH_SHORT).show();
        getRepositories(page);
    }

    private void getRepositories(int page) {
        new Backend(this).execute(page);
    }

    @Override
    public void onSucess(ArrayList<Repository> repositories) {

        initViews();
        if (repositoryAdapter != null) {
            repositoryAdapter.setAdapterList(repositories, this);
        }
        repositories.addAll(repositories);


    }

    @Override
    public void onFailure() {
        Intent intent = new Intent(this, ErrorActivity.class);
        startActivity(intent);

    }
}

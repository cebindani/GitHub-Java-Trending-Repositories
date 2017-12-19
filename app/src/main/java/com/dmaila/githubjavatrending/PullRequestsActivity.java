package com.dmaila.githubjavatrending;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.dmaila.githubjavatrending.adapters.PullRequestAdapter;
import com.dmaila.githubjavatrending.data.PullRequest;
import com.dmaila.githubjavatrending.data.Repository;
import com.dmaila.githubjavatrending.utils.ApiUtils;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PullRequestsActivity extends AppCompatActivity {

    public static final String REPOSITORY_EXTRA = "REPOSITORY_EXTRA";
    private static final String PR_OPEN = "open";
    private Repository repository;
    private List<PullRequest> pullRequests = new ArrayList<>();
    private TextView statusCount;
    private int openPullRequestsCounter = 0;
    private int closedPullRequestsCounter = 0;
    private PullRequestAdapter pullRequestAdapter;
    private LinearLayoutManager layoutManager;
    private Parcelable layoutManagerState;


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("pullRequests", (ArrayList<? extends Parcelable>) pullRequests);
        layoutManagerState = layoutManager.onSaveInstanceState();
        outState.putParcelable("layoutManager", layoutManagerState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            pullRequests = savedInstanceState.getParcelableArrayList("pullRequests");
            layoutManagerState = savedInstanceState.getParcelable("layoutManager");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (layoutManager != null && pullRequestAdapter != null) {
            layoutManager.onRestoreInstanceState(layoutManagerState);
            pullRequestAdapter.setAdapterList(pullRequests, this);
        } else
            initData();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initViews();

    }

    private void initData() {
        if (getIntent() != null && getIntent().hasExtra(REPOSITORY_EXTRA)) {
            repository = getIntent().getParcelableExtra(REPOSITORY_EXTRA);
            getPullRequests(repository.getOwnerLogin(), repository.getName());
        }
    }

    private void initViews() {
        setContentView(R.layout.activity_pull_requests);
        Toolbar toolbar = findViewById(R.id.pull_request_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setTitle(repository.getName());
        getSupportActionBar().setTitle(repository.getName());


        statusCount = findViewById(R.id.pull_requests_status);
        RecyclerView recyclerView = findViewById(R.id.pullRequestRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.ItemDecoration itemDecoration
                = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        pullRequestAdapter = new PullRequestAdapter();
        recyclerView.setAdapter(pullRequestAdapter);
    }

    private void getPullRequests(String ownerLogin, String repoFullName) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(ApiUtils.buildPullRequestsURL(ownerLogin, repoFullName))
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, IOException e) {
                if (isOnline())
                    startActivity(new Intent(getApplicationContext(), ErrorActivity.class));


            }

            private Boolean isOnline() {
                ConnectivityManager connectivityManager =
                        (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
                return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
            }


            @Override
            public void onResponse(@NonNull Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
                pullRequests = getPullRequestsModel(response.body().string());

                PullRequestsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateViews(pullRequests);

                    }
                });
            }
        });
    }

    private void updateViews(List<PullRequest> pullRequests) {
        initViews();
        countPullRequestStatus(pullRequests);

        String formattedString = getString(R.string.opened_closed_status, openPullRequestsCounter, closedPullRequestsCounter);
        statusCount.setText(formattedString);

        if (pullRequestAdapter != null) {
            pullRequestAdapter.setAdapterList(pullRequests, this);
        }
    }

    private void countPullRequestStatus(List<PullRequest> pullRequests) {
        for (PullRequest pullRequest :
                pullRequests) {
            if (pullRequest.getState().equals(PR_OPEN))
                openPullRequestsCounter++;
            else
                closedPullRequestsCounter++;

        }

    }

    private List<PullRequest> getPullRequestsModel(String responseJson) throws IOException {

        final Moshi moshi = new Moshi.Builder().build();
        Type listMyData = Types.newParameterizedType(List.class, PullRequest.class);
        JsonAdapter<List<PullRequest>> adapter = moshi.adapter(listMyData);

        return adapter.fromJson(responseJson);

    }


}

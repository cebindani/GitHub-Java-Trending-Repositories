package com.dmaila.githubjavatrending;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.dmaila.githubjavatrending.adapters.PullRequestAdapter;
import com.dmaila.githubjavatrending.data.PullRequest;
import com.dmaila.githubjavatrending.data.Repository;
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
    Repository repository;
    List<PullRequest> pullRequests = new ArrayList<>();
    TextView statusCount;
    RecyclerView recyclerView;
    private int openPullRequestsCounter = 0;
    private int closedPullRequestsCounter = 0;
    private PullRequestAdapter pullRequestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        initData();
    }

    private void initData() {
        if (getIntent() != null && getIntent().hasExtra(REPOSITORY_EXTRA)) {
            repository = getIntent().getParcelableExtra(REPOSITORY_EXTRA);
            getPullRequests(repository.getOwnerLogin(), repository.getName());
        }
    }

    private void initViews() {
        setContentView(R.layout.activity_pull_requests);

        statusCount = findViewById(R.id.pull_requests_status);
        recyclerView = findViewById(R.id.pullRequestRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.ItemDecoration itemDecoration
                = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        pullRequestAdapter = new PullRequestAdapter();
        recyclerView.setAdapter(pullRequestAdapter);
    }

    private void getPullRequests(String ownerLogin, String repoFullName) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BackendUtils.buildPullRequestsURL(ownerLogin, repoFullName))
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
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

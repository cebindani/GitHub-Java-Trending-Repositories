package com.dmaila.githubjavatrending;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.dmaila.githubjavatrending.adapters.PullRequestAdapter;
import com.dmaila.githubjavatrending.data.PullRequest;
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

    private static final String PR_OPEN = "open";

    private int openPullRequestsCounter = 0;
    private int closedPullRequestsCounter = 0;

    String ownerLogin;
    String repoFullName;
    List<PullRequest> pullRequests = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_requests);

        repoFullName = getIntent().getStringExtra("REPO_FULL_NAME");

        getPullRequests("ReactiveX", "RxJava");
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
                        updateView(pullRequests);

                    }
                });


            }
        });
    }

    private void updateView(List<PullRequest> pullRequests) {
        countPullRequestStatus(pullRequests);

        TextView statusCount = (TextView) findViewById(R.id.pull_requests_status);
        String formatedString = getString(R.string.opened_closed_status, openPullRequestsCounter, closedPullRequestsCounter);
        statusCount.setText(formatedString);

        RecyclerView recyclerView = findViewById(R.id.pullRequestRecyclerView);
        PullRequestAdapter pullRequestAdapter
                = new PullRequestAdapter(new ArrayList<PullRequest>(pullRequests), this);
        recyclerView.setAdapter(pullRequestAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.ItemDecoration itemDecoration
                = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

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

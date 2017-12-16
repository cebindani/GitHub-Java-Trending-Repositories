package com.dmaila.githubjavatrending;

import android.os.AsyncTask;
import android.util.Log;

import com.dmaila.githubjavatrending.data.Repositories;
import com.dmaila.githubjavatrending.data.Repository;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Backend extends AsyncTask<Void, Void, List<Repository>> {

    public static final String GITHUB_BASE_URL = "https://api.github.com";
    public static final String SEARCH_JAVA_REPOSITORIES_URL
            = "/search/repositories?q=language:Java&sort=stars&page=1";

    private String responseJson = null;
    private AsyncResponse mAsyncResponse;

    public Backend(AsyncResponse asyncResponse) {
        this.mAsyncResponse = asyncResponse;
    }

    @Override
    protected List<Repository> doInBackground(Void... voids) {

        return getRepositoriesFromAPI();
    }

    @Override
    protected void onPostExecute(List<Repository> repositories) {
        super.onPostExecute(repositories);

        if (repositories != null && !repositories.isEmpty()) {
            mAsyncResponse.onSucess(repositories);
        } else {
            mAsyncResponse.onFailure();
        }

    }

    List<Repository> getRepositoriesFromAPI() {
        try {
            callGitHubApi(SEARCH_JAVA_REPOSITORIES_URL);
            if (responseJson != null) {
                return getRepositoriesFromJson(responseJson);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }
        return null;
    }

    private void callGitHubApi(String callUrl) {
        OkHttpClient client = new OkHttpClient();

        String httpUrl = GITHUB_BASE_URL.concat(callUrl);

        Request request = new Request.Builder()
                .url(httpUrl)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);

            }
            responseJson = response.body().string();
            Log.d("", "callGitHubApi: " + responseJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Repository> getRepositoriesFromJson(String responseJson) throws IOException {

        final Moshi moshi = new Moshi.Builder().build();
        final JsonAdapter<Repositories> jsonAdapter = moshi.adapter(Repositories.class);

        Repositories repositories = jsonAdapter.fromJson(responseJson);
        return repositories.getRepositories();

    }
}

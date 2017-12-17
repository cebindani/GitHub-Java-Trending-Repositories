package com.dmaila.githubjavatrending;

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


public class BackendAPI extends Backend {
    private static String responseJson = null;

    public BackendAPI(AsyncResponse asyncResponse) {
        super(asyncResponse);
    }

    private String callGitHubApi(String callUrl) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(callUrl)
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
        return String.valueOf(responseJson);
    }

    public List<Repository> getRepositories(int page) {
        responseJson = callGitHubApi(BackendUtils.buildSearchJavaRepositoriesURL(page));
        if (responseJson != null) {
            try {
                return getRepositoriesModel(responseJson);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    List<Repository> getRepositoriesModel(String responseJson) throws IOException {

        final Moshi moshi = new Moshi.Builder().build();
        final JsonAdapter<Repositories> jsonAdapter = moshi.adapter(Repositories.class);

        Repositories repositories = jsonAdapter.fromJson(responseJson);
        return repositories.getRepositories();

    }
}

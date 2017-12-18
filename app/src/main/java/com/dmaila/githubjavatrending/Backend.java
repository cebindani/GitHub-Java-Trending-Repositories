package com.dmaila.githubjavatrending;

import android.os.AsyncTask;

import com.dmaila.githubjavatrending.data.Repository;

import java.util.ArrayList;
import java.util.List;


public class Backend extends AsyncTask<Integer, Void, List<Repository>> {

    private String responseJson = null;
    private AsyncResponse mAsyncResponse;

    public Backend(AsyncResponse asyncResponse) {
        this.mAsyncResponse = asyncResponse;
    }


    @Override
    protected List<Repository> doInBackground(Integer... params) {
        int page = params[0];
        return getRepositories(page);
    }

    @Override
    protected void onPostExecute(List<Repository> repositories) {
        super.onPostExecute(repositories);

        if (repositories != null && !repositories.isEmpty()) {
            mAsyncResponse.onSucess((ArrayList<Repository>) repositories);
        } else {
            mAsyncResponse.onFailure();
        }

    }

    List<Repository> getRepositories(int page) {
        BackendAPI api = new BackendAPI(mAsyncResponse);

        if (api.getRepositories(page) != null) {
            return api.getRepositories(page);
        }
        return null;
    }
}

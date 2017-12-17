package com.dmaila.githubjavatrending;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dmaila.githubjavatrending.adapters.RepositoryAdapter;
import com.dmaila.githubjavatrending.data.Repository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AsyncResponse {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getRepositories();
        /*
        1. chama API: recebe URL e responde Response JSON
        2. processa JSON: recebe String json e retorna Objeto do model
         */


    }

    private void getRepositories() {
        new Backend(this).execute();
    }


    @Override
    public void onSucess(List<Repository> repositoryList) {

        RecyclerView recyclerView = findViewById(R.id.root_view);
        RepositoryAdapter repositoryAdapter = new RepositoryAdapter(new ArrayList<>(repositoryList), MainActivity.this);
        recyclerView.setAdapter(repositoryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
    }

    @Override
    public void onFailure() {

    }
}

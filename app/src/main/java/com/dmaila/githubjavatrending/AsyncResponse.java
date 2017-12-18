package com.dmaila.githubjavatrending;


import com.dmaila.githubjavatrending.data.Repository;

import java.util.ArrayList;

interface AsyncResponse {

    void onSucess(ArrayList<Repository> list);

    void onFailure();
}

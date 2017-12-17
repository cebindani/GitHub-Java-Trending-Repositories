package com.dmaila.githubjavatrending;


import com.dmaila.githubjavatrending.data.Repository;

import java.util.List;

interface AsyncResponse {

    void onSucess(List<Repository> list);

    void onFailure();
}

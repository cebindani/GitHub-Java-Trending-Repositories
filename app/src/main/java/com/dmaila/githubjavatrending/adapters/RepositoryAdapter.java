package com.dmaila.githubjavatrending.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dmaila.githubjavatrending.PullRequestsActivity;
import com.dmaila.githubjavatrending.R;
import com.dmaila.githubjavatrending.data.Repository;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.dmaila.githubjavatrending.PullRequestsActivity.REPOSITORY_EXTRA;


public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder> {

    private ArrayList<Repository> mRepositories;
    private Context context;

    public RepositoryAdapter(ArrayList<Repository> mRepositories, Context context) {
        this.mRepositories = mRepositories;
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View repositoryView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_repo_item, parent, false);
        return new ViewHolder(repositoryView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Repository repository = mRepositories.get(position);

        holder.setAvatarImage(repository.getOwnerAvatarUrl());
        holder.mRepositoryName.setText(repository.getName());
        holder.mDescription.setText(repository.getDescription());
        holder.mOwnerLogin.setText(repository.getOwnerLogin());
        holder.mStargazersCount.setText(String.valueOf(repository.getStargazersCount()));
        holder.mForksCount.setText(String.valueOf(repository.getForksCount()));


    }

    @Override
    public int getItemCount() {
        return this.mRepositories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mRepositoryName;
        TextView mDescription;
        TextView mOwnerLogin;
        CircleImageView mOwnerAvatar;
        TextView mForksCount;
        TextView mStargazersCount;

        public ViewHolder(View itemView) {
            super(itemView);

            mRepositoryName = itemView.findViewById(R.id.repositoryName);
            mDescription = itemView.findViewById(R.id.repositoryDescription);
            mOwnerLogin = itemView.findViewById(R.id.ownerLogin);
            mOwnerAvatar = itemView.findViewById(R.id.ownerAvatar);
            mStargazersCount = itemView.findViewById(R.id.starsCount);
            mForksCount = itemView.findViewById(R.id.forksCount);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();

            Repository repository = mRepositories.get(position);

            Intent intent = new Intent(context, PullRequestsActivity.class);
            intent.putExtra(REPOSITORY_EXTRA, repository);
            context.startActivity(intent);

        }

        public void setAvatarImage(String ownerAvatarUrl) {
            Picasso.with(context).load(ownerAvatarUrl)
                    .into(mOwnerAvatar);
        }
    }
}

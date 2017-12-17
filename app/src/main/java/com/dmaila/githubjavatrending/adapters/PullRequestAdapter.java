package com.dmaila.githubjavatrending.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dmaila.githubjavatrending.R;
import com.dmaila.githubjavatrending.data.PullRequest;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class PullRequestAdapter extends RecyclerView.Adapter<PullRequestAdapter.ViewHolder> {

    private static final String TIMESTAMP_MASK = "yyyy/MM/dd";
    private List<PullRequest> pullRequests = new ArrayList<>();
    private Context context;

    public PullRequestAdapter(ArrayList<PullRequest> pullRequests, Context context) {
        this.pullRequests = pullRequests;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View repositoryView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pull_request_item, parent, false);
        return new ViewHolder(repositoryView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (!pullRequests.isEmpty()) {
            PullRequest pullRequest = pullRequests.get(position);

            holder.pullRequestTitle.setText(pullRequest.getTitle());
            holder.pullRequestBody.setText(pullRequest.getBody());
            holder.pullRequestOwnerLogin.setText(pullRequest.getPullRequestOwnerLogin());
            holder.pullRequestOwnerFullName.setText(pullRequest.getRepositoryFullName());

            //fixme: date
            String createdDate = pullRequest.getCreatedAt(); //new SimpleDateFormat(TIMESTAMP_MASK, Locale.getDefault()).format(pullRequest.getCreatedAt());
            String updatedDate = pullRequest.getUpdatedAt(); //new SimpleDateFormat(TIMESTAMP_MASK, Locale.getDefault()).format(pullRequest.getUpdatedAt());
            String createdDateFormated = context.getString(R.string.created, createdDate);
            String updatedDateFormated = context.getString(R.string.updated, updatedDate);

            holder.setAvatarImage(pullRequest.getPullRequestOwnerAvatar());
            holder.createDateView.setText(createdDateFormated);
            holder.updateDateView.setText(updatedDateFormated);


        }
    }

    @Override
    public int getItemCount() {
        return this.pullRequests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView pullRequestTitle;
        TextView pullRequestBody;
        TextView pullRequestOwnerLogin;
        TextView pullRequestOwnerFullName;
        TextView createDateView;
        TextView updateDateView;
        ImageView pullRequestOwnerAvatar;
        String createdString;
        String updatedString;


        public ViewHolder(View itemView) {
            super(itemView);

            pullRequestTitle = (TextView) itemView.findViewById(R.id.pullRequestTitle);
            pullRequestBody = (TextView) itemView.findViewById(R.id.pullRequestBody);
            pullRequestOwnerLogin = (TextView) itemView.findViewById(R.id.pullRequestOwnerLogin);
            pullRequestOwnerFullName = (TextView) itemView.findViewById(R.id.pullRequestOwnerName);

            createDateView = (TextView) itemView.findViewById(R.id.pullRequestCreated);
            updateDateView = (TextView) itemView.findViewById(R.id.pullRequestUpdated);

            pullRequestOwnerAvatar = (ImageView) itemView.findViewById(R.id.pullRequestOwnerAvatar);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Toast.makeText(context, pullRequestTitle.getText().toString(), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent();
            //create new intent
            //send owner and repositoryName

            //on new intent, call {base_url} + /repos/{owner}/{repo}/pulls

        }

        public void setAvatarImage(String imageUrl) {
            Picasso.with(context)
                    .load(imageUrl)
                    .into(pullRequestOwnerAvatar);
        }
    }
}

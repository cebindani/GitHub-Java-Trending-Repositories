package com.dmaila.githubjavatrending.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmaila.githubjavatrending.R;
import com.dmaila.githubjavatrending.data.PullRequest;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


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
            if (pullRequest.getRepositoryFullName() != null) {
                holder.pullRequestRepoFullName.setText(pullRequest.getRepositoryFullName());
            } else {
                holder.pullRequestRepoFullName.setText(R.string.unknown_repository);
//                holder.pullRequestRepoFullName.setVisibility(View.GONE);
            }

            String createdDate = formattedDate(pullRequest.getCreatedAt());
            String updatedDate = formattedDate(pullRequest.getUpdatedAt());


            String createdDateFormatted = context.getString(R.string.created, createdDate);
            String updatedDateFormatted = context.getString(R.string.updated, updatedDate);

            holder.setAvatarImage(pullRequest.getPullRequestOwnerAvatar());
            holder.createDateView.setText(createdDateFormatted);
            holder.updateDateView.setText(updatedDateFormatted);

            if (pullRequest.getState().equals("open")) {
                holder.pullRequestStatusView.setBackgroundColor(context.getResources().getColor(R.color.stateOpen));
            } else if (!pullRequest.getState().equals("open")  && pullRequest.getMergedAt() != null) {
                holder.pullRequestStatusView.setBackgroundColor(context.getResources().getColor(R.color.stateMerged));
            } else {
                holder.pullRequestStatusView.setBackgroundColor(context.getResources().getColor(R.color.stateClosed));
            }



        }
    }

    private String formattedDate(String dateString) {
        SimpleDateFormat outputDateFormat = new SimpleDateFormat(TIMESTAMP_MASK, Locale.getDefault());
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(dateString);
            return outputDateFormat.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public int getItemCount() {
        return this.pullRequests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView pullRequestTitle;
        TextView pullRequestBody;
        TextView pullRequestOwnerLogin;
        TextView pullRequestRepoFullName;
        TextView createDateView;
        TextView updateDateView;
        ImageView pullRequestOwnerAvatar;
        View pullRequestStatusView;


        public ViewHolder(View itemView) {
            super(itemView);

            pullRequestTitle = itemView.findViewById(R.id.pullRequestTitle);
            pullRequestBody = itemView.findViewById(R.id.pullRequestBody);
            pullRequestOwnerLogin = itemView.findViewById(R.id.pullRequestOwnerLogin);
            pullRequestRepoFullName = itemView.findViewById(R.id.pullRequestOwnerName);

            createDateView = itemView.findViewById(R.id.pullRequestCreated);
            updateDateView = itemView.findViewById(R.id.pullRequestUpdated);

            pullRequestOwnerAvatar = itemView.findViewById(R.id.pullRequestOwnerAvatar);
            pullRequestStatusView = itemView.findViewById(R.id.pullRequestStatusSemaphore);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();

            String pullRequestUrl = pullRequests.get(position).getHtmlUrl();
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pullRequestUrl));
            context.startActivity(webIntent);

        }

        public void setAvatarImage(String imageUrl) {
            Picasso.with(context)
                    .load(imageUrl)
                    .into(pullRequestOwnerAvatar);
        }
    }
}

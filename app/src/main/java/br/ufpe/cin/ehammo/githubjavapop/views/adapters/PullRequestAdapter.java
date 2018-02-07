package br.ufpe.cin.ehammo.githubjavapop.views.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import br.ufpe.cin.ehammo.githubjavapop.R;
import br.ufpe.cin.ehammo.githubjavapop.model.PullRequest;

/**
 * Created by eduardo on 07/02/2018.
 */

public class PullRequestAdapter extends RecyclerView.Adapter<PullRequestAdapter.ViewHolder> {


    private ArrayList<PullRequest> pullRequests;
    private Activity activity;

    public PullRequestAdapter(ArrayList<PullRequest> pullRequests, Activity activity) {
        this.pullRequests = pullRequests;
        this.activity = activity;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final PullRequest pullRequest = pullRequests.get(i);
        viewHolder.setInfo(pullRequest);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item_pullrequest, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public int getItemCount() {
        if (pullRequests != null) {
            return pullRequests.size();
        } else {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView name;
        TextView desc;
        TextView forks;
        TextView stars;
        ImageView avatar;
        TextView username;
        TextView fullusername;
        RelativeLayout layout;

        ViewHolder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.externalLayout);
            name = itemView.findViewById(R.id.tvName);
            desc = itemView.findViewById(R.id.tvDesc);
            forks = itemView.findViewById(R.id.tvForks);
            stars = itemView.findViewById(R.id.tvStars);
            avatar = itemView.findViewById(R.id.ivAvatar);
            username = itemView.findViewById(R.id.tvUsername);
            fullusername = itemView.findViewById(R.id.tvFullUserName);
        }

        public void setInfo(PullRequest pullrequest) {
//            name.setText(pullrequest.getName());
//            desc.setText(pullrequest.getDescription());
//            forks.setText(pullrequest.getForksCount().toString());
//            stars.setText(pullrequest.getStargazersCount().toString());
//            username.setText(pullrequest.getOwner().getLogin());
//            fullusername.setText(pullrequest.getOwner().getName());
//            avatar
        }
    }

}

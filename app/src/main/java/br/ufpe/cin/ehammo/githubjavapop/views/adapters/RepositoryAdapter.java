package br.ufpe.cin.ehammo.githubjavapop.views.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import br.ufpe.cin.ehammo.githubjavapop.R;
import br.ufpe.cin.ehammo.githubjavapop.model.Repository;
import br.ufpe.cin.ehammo.githubjavapop.views.activities.PullRequestActivity;

/**
 * Created by eduardo on 07/02/2018.
 */

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder> {


    private ArrayList<Repository> repositories;
    private Activity activity;

    public RepositoryAdapter(ArrayList<Repository> repositories, Activity activity) {
        this.repositories = repositories;
        this.activity = activity;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final Repository repository = repositories.get(i);
        viewHolder.setInfo(repository);
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, PullRequestActivity.class);
                intent.putExtra("login", repository.getOwner().getLogin());
                intent.putExtra("repository", repository.getName());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item_repository, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public int getItemCount() {
        if (repositories != null) {
            return repositories.size();
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

        public void setInfo(Repository repository) {
            name.setText(repository.getName());
            desc.setText(repository.getDescription());
            forks.setText(repository.getForksCount().toString());
            stars.setText(repository.getStargazersCount().toString());
            username.setText(repository.getOwner().getLogin());
            fullusername.setText(repository.getOwner().getName());
//            avatar
        }
    }

}

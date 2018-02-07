package br.ufpe.cin.ehammo.githubjavapop.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.ufpe.cin.ehammo.githubjavapop.R;
import br.ufpe.cin.ehammo.githubjavapop.model.Repository;

/**
 * Created by eduardo on 07/02/2018.
 */

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder> {


    private ArrayList<Repository> repositories;

    public RepositoryAdapter(ArrayList<Repository> repositories) {
        this.repositories = repositories;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Repository repository = repositories.get(i);
        viewHolder.setInfo(repository);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item_repository, viewGroup, false);
        return new ViewHolder(v);
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

        ViewHolder(View itemView) {
            super(itemView);
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

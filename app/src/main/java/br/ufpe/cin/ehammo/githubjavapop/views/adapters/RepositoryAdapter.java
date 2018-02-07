package br.ufpe.cin.ehammo.githubjavapop.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        Log.d("oi", repositories.get(i).getName());
        viewHolder.name.setText(repositories.get(i).getName() + " " + i);
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

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
        }
    }

}

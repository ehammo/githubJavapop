package br.ufpe.cin.ehammo.githubjavapop.views.adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

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
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = pullRequest.getHtmlUrl();
                if (!url.startsWith("http://") && !url.startsWith("https://"))
                    url = "http://" + url;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView body;
        ImageView avatar;
        TextView username;
        TextView fullusername;
        LinearLayout layout;

        ViewHolder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.externalLayout);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
            avatar = itemView.findViewById(R.id.ivAvatar);
            username = itemView.findViewById(R.id.tvUsername);
            fullusername = itemView.findViewById(R.id.tvFullUserName);
        }

        public void setInfo(PullRequest pullrequest) {
            title.setText(pullrequest.getTitle());
            body.setText(pullrequest.getBody());
            username.setText(pullrequest.getOwner().getLogin());
            fullusername.setText(pullrequest.getOwner().getName());
            Glide.with(activity).load(pullrequest.getOwner().getAvatarUrl()).asBitmap()
                    .into(new SimpleTarget<Bitmap>(320, 240) {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            Drawable drawable = new BitmapDrawable(activity.getResources(), resource);
                            avatar.setImageDrawable(drawable);
                        }
                    });
        }
    }

}

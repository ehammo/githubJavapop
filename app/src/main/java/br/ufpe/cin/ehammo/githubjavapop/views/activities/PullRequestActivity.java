package br.ufpe.cin.ehammo.githubjavapop.views.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.ufpe.cin.ehammo.githubjavapop.R;
import br.ufpe.cin.ehammo.githubjavapop.controller.Facade;
import br.ufpe.cin.ehammo.githubjavapop.model.PullRequest;
import br.ufpe.cin.ehammo.githubjavapop.views.adapters.PullRequestAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PullRequestActivity extends AppCompatActivity {

    private String TAG;
    private String login;
    private String rep;
    private Context mContext;
    private RecyclerView recyclerView;
    private RelativeLayout progressBar;
    private TextView tv_open;
    private TextView tv_close;


    private ArrayList<PullRequest> pullRequests;
    private PullRequestAdapter pullRequestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pullrequest);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            login = (String) bundle.get("login");
            rep = (String) bundle.get("repository");
        }
        TAG = this.getLocalClassName();
        mContext = this;
        tv_close = findViewById(R.id.close);
        tv_open = findViewById(R.id.open);
        pullRequests = new ArrayList<>();
        progressBar = findViewById(R.id.progress);

        recyclerView = findViewById(R.id.rvPullRequests);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        pullRequestAdapter = new PullRequestAdapter(pullRequests, this);

        recyclerView.setAdapter(pullRequestAdapter);

        Facade.getInstance().getPullRequests(login, rep, new Callback<List<PullRequest>>() {
            @Override
            public void onResponse(Call<List<PullRequest>> call, Response<List<PullRequest>> response) {
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    pullRequests.clear();
                    pullRequests.addAll(response.body());
                    int open = 0;
                    int close = 0;
                    for (PullRequest pr : pullRequests) {
                        if (pr.getState().equals("opened")) {
                            open++;
                        } else {
                            close++;
                        }
                    }
                    tv_open.setText(open + " open ");
                    tv_close.setText("/ " + close + " closed ");
                    pullRequestAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<PullRequest>> call, Throwable t) {

            }
        });
    }

}

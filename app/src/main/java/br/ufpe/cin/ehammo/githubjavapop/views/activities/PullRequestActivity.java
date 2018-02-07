package br.ufpe.cin.ehammo.githubjavapop.views.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

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

    private ArrayList<PullRequest> pullRequests;
    private PullRequestAdapter pullRequestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            login = (String) bundle.get("login");
            rep = (String) bundle.get("repository");
        }

        TAG = this.getLocalClassName();
        mContext = this;
        pullRequests = new ArrayList<>();
        progressBar = findViewById(R.id.progress);

        recyclerView = findViewById(R.id.rvRepositories);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        pullRequestAdapter = new PullRequestAdapter(pullRequests, this);

        recyclerView.setAdapter(pullRequestAdapter);

        Facade.getInstance().getPullRequests(login, rep, new Callback<List<PullRequest>>() {
            @Override
            public void onResponse(Call<List<PullRequest>> call, Response<List<PullRequest>> response) {
                if (response.isSuccessful()) {
                    pullRequests.addAll(response.body());
                    pullRequestAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<PullRequest>> call, Throwable t) {

            }
        });
    }

}

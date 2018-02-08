package br.ufpe.cin.ehammo.githubjavapop.views.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    private RelativeLayout noPullRequests;
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
        noPullRequests = findViewById(R.id.noPullRequests);
        tv_close = findViewById(R.id.close);
        tv_open = findViewById(R.id.open);
        pullRequests = new ArrayList<>();
        progressBar = findViewById(R.id.progress);

        recyclerView = findViewById(R.id.rvPullRequests);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        pullRequestAdapter = new PullRequestAdapter(pullRequests, this);

        recyclerView.setAdapter(pullRequestAdapter);
        recyclerView.setVisibility(View.VISIBLE);
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
                        if (pr.getState().equals("open")) {
                            open++;
                        } else {
                            close++;
                        }
                    }
                    tv_open.setText(String.format("%s %s ", String.valueOf(open), getString(R.string.open)));
                    tv_close.setText(String.format("/ %s %s", String.valueOf(close), getString(R.string.close)));
                    pullRequestAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<PullRequest>> call, Throwable t) {
                Log.e(TAG, "fail");
                if (!call.isCanceled()) {
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                    noPullRequests.setVisibility(View.VISIBLE);
                }
                if (t != null) {
                    Log.e(TAG, t.getMessage());
                    Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}

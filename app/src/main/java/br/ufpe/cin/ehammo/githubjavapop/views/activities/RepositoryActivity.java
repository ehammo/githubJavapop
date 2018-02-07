package br.ufpe.cin.ehammo.githubjavapop.views.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;

import java.util.ArrayList;

import br.ufpe.cin.ehammo.githubjavapop.R;
import br.ufpe.cin.ehammo.githubjavapop.controller.Facade;
import br.ufpe.cin.ehammo.githubjavapop.model.APIResponse;
import br.ufpe.cin.ehammo.githubjavapop.model.Repository;
import br.ufpe.cin.ehammo.githubjavapop.views.EndlessRecyclerViewScrollListener;
import br.ufpe.cin.ehammo.githubjavapop.views.adapters.RepositoryAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryActivity extends AppCompatActivity {

    private String TAG;
    private Context mContext;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private EndlessRecyclerViewScrollListener scrollListener;

    private ArrayList<Repository> repositories;
    private RepositoryAdapter repositoryAdapter;


    private int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TAG = this.getLocalClassName();
        mContext = this;
        repositories = new ArrayList<>();
        progressBar = findViewById(R.id.progress);

        recyclerView = findViewById(R.id.rvRepositories);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        repositoryAdapter = new RepositoryAdapter(repositories);

        recyclerView.setAdapter(repositoryAdapter);


        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {

            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                currentPage += 1;
                loadNextPage();
            }
        };

        recyclerView.addOnScrollListener(scrollListener);
        loadNextPage();
    }

    private void loadNextPage() {
        Log.d(TAG, "loadPage: " + currentPage);

        Facade.getInstance().getRepositories(currentPage, new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                Log.d(TAG, "onresponse");
                if (response.isSuccessful()) {
                    APIResponse apiResponse = response.body();
                    int pageSize = apiResponse.getItems().size();
                    repositories.addAll(apiResponse.getItems());
                    Log.d(TAG, "repSize: " + repositoryAdapter.getItemCount());
                    repositoryAdapter.notifyDataSetChanged();

                } else {
                    Log.e(TAG, "response not successful");
                    Log.e(TAG, response.errorBody().toString());
                    Log.e(TAG, response.message().toString());
//                            Toast.makeText(mContext, response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Log.e(TAG, "fail");
                if (t != null) {
                    Log.e(TAG, t.getMessage());
                }
            }
        });
    }
}

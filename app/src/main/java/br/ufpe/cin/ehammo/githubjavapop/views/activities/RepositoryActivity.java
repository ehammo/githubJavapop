package br.ufpe.cin.ehammo.githubjavapop.views.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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
    private RelativeLayout progressBar;
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

        repositoryAdapter = new RepositoryAdapter(repositories, this);

        recyclerView.setAdapter(repositoryAdapter);

        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {

            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                currentPage += 1;
                //todo:fix smoothing
                loadPage();
            }
        };

        recyclerView.addOnScrollListener(scrollListener);
        loadPage();
    }

    private void loadPage() {
        progressBar.setVisibility(View.VISIBLE);
        Facade.getInstance().getRepositories(currentPage, new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    APIResponse apiResponse = response.body();
                    if (apiResponse != null) {
                        List<Repository> repositoryList = apiResponse.getItems();
                        Log.d(TAG, repositoryList.size() + " itens");
                        if (repositoryList != null) {
                            repositories.addAll(repositoryList);
                            repositoryAdapter.notifyDataSetChanged();
                            //todo:maybe fix fullname by authenticating and then getting user?
                        }
                    }


                } else {
                    Log.e(TAG, "response not successful");
                    Log.e(TAG, response.errorBody().toString());
                    Log.e(TAG, response.message());
                    Toast.makeText(mContext, response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Log.e(TAG, "fail");
                if (!call.isCanceled()) {
                    progressBar.setVisibility(View.GONE);
                }
                if (t != null) {
                    Log.e(TAG, t.getMessage());
                    Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

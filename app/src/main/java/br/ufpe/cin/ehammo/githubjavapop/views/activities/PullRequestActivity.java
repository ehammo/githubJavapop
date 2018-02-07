package br.ufpe.cin.ehammo.githubjavapop.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import br.ufpe.cin.ehammo.githubjavapop.R;

public class PullRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_request);
        TextView textView = findViewById(R.id.login);
        TextView textView2 = findViewById(R.id.rep);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String login = (String) bundle.get("login");
            String rep = (String) bundle.get("repository");
            textView.setText(login);
            textView2.setText(rep);
        }
    }
}

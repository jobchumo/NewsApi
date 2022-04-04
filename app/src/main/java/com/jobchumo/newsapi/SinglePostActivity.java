package com.jobchumo.newsapi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebView;

public class SinglePostActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_post);

        loadNewsArticle();
    }

    private void loadNewsArticle() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        final String url = getIntent().getStringExtra("url");
        WebView newsView = findViewById(R.id.single_post);
        newsView.loadUrl(url);

        progressDialog.hide();
    }
}
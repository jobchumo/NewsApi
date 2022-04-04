package com.jobchumo.newsapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.jobchumo.newsapi.adapter.ArticleAdapter;
import com.jobchumo.newsapi.apis.APIInterface;
import com.jobchumo.newsapi.apis.ApiClient;
import com.jobchumo.newsapi.model.Article;
import com.jobchumo.newsapi.model.ResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements com.jobchumo.newsapi.utils.OnRecyclerViewItemClickListener {

    private static final String API_KEY = "1eeddc329ed341d3b429fe2a693d6e42";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadResults();

    }
    private void loadResults() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading top headlines in the United States");
        progressDialog.show();

        final RecyclerView newsRecycler = findViewById(R.id.newsRecyclerView);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        newsRecycler.setLayoutManager(linearLayoutManager);

        final APIInterface apiService = ApiClient.getClient().create(APIInterface.class);
        Call<ResponseModel> call = apiService.getLatestNews("us",API_KEY);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel>call, Response<ResponseModel> response) {
                if(response.body().getStatus().equals("ok")) {
                    List<Article> articleList = response.body().getArticles();
                    if(articleList.size()>0) {
                        Log.d("Hello there", "does this thing work");
                        final ArticleAdapter mainArticleAdapter = new ArticleAdapter(articleList);
                        mainArticleAdapter.setOnRecyclerViewItemClickListener(MainActivity.this);
                        newsRecycler.setAdapter(mainArticleAdapter);
                        progressDialog.hide();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseModel>call, Throwable t) {
                Log.d("failure", t.toString());
            }
        });
    }

    @Override
    public void onItemClick(int position, View view) {
        switch (view.getId()) {
            case R.id.article_parent:
                Article article = (Article) view.getTag();
                if(!TextUtils.isEmpty(article.getUrl())) {
                    Log.d("post url", article.getUrl());
                    Intent singlePost = new Intent(this, SinglePostActivity.class);
                    singlePost.putExtra("url",article.getUrl());
                    startActivity(singlePost);
                }
                break;
        }
    }
}
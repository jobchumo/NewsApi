package com.jobchumo.newsapi.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jobchumo.newsapi.MainActivity;
import com.jobchumo.newsapi.R;
import com.jobchumo.newsapi.model.Article;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private List<Article> articleArrayList;
    private Context context;
    private MainActivity onRecyclerViewItemClickListener;

    public ArticleAdapter(List<Article> articleArrayList) {
        this.articleArrayList = articleArrayList;
    }

    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.article_adapter, viewGroup, false);
        return new ArticleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleAdapter.ViewHolder viewHolder, int position) {
        final Article articleModel = articleArrayList.get(position);
        if (!TextUtils.isEmpty(articleModel.getTitle())) {
            viewHolder.articleTitle.setText(articleModel.getTitle());
        }
        if (!TextUtils.isEmpty(articleModel.getDescription())) {
            viewHolder.articleDesc.setText(articleModel.getDescription());
        }
        viewHolder.articleParent.setTag(articleModel);
    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView articleTitle;
        private TextView articleDesc;
        private LinearLayout articleParent;

        ViewHolder(View view) {
            super(view);
            articleTitle = view.findViewById(R.id.article_title);
            articleDesc = view.findViewById(R.id.article_desc);
            articleParent = view.findViewById(R.id.article_parent);
            articleParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onRecyclerViewItemClickListener != null) {
                        onRecyclerViewItemClickListener.onItemClick(getAdapterPosition(), view);
                    }
                }
            });
        }
    }

    public void setOnRecyclerViewItemClickListener(MainActivity onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }
}
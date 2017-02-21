package com.example.user.retrofit.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.retrofit.R;
import com.example.user.retrofit.models.User;

import java.util.List;

/**
 * Created by user on 2/20/2017.
 */

public class GitHubProfileAdapter extends RecyclerView.Adapter<GitHubProfileAdapter.GithubView> {

    private LayoutInflater in;
    private List<User.Items> items;
    private Context context;

    public GitHubProfileAdapter(Context context, List<User.Items> items) {
        this.items = items;
        this.context = context;
        in = LayoutInflater.from(context);
    }

    public GitHubProfileAdapter.GithubView onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = in.inflate(R.layout.github_profile_item, parent, false);
        GithubView holder = new GithubView(v);

        return holder;
    }

    public void onBindViewHolder(GitHubProfileAdapter.GithubView holder, int position) {
        User.Items userItems = items.get(position);

        holder.username.setText(userItems.getLogin());
        holder.id.setText(String.valueOf(userItems.getId()));
        holder.score.setText(String.valueOf(userItems.getScore()));
    }

    public class GithubView extends RecyclerView.ViewHolder {
        TextView username;
        TextView id;
        TextView score;

        public GithubView(final View item) {
            super(item);
            username = (TextView) item.findViewById(R.id.login);
            id = (TextView) item.findViewById(R.id.id);
            score = (TextView) item.findViewById(R.id.score);
        }
    }

    public int getItemCount() {
        return items.size();
    }

}
package com.example.favoritesapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class GithubUserAdapter extends RecyclerView.Adapter<GithubUserAdapter.ListViewHolder> {

    private Context context;
    private Cursor cursor;

    public GithubUserAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    private GithubUser getFavoriteItemAdapter(int Position){
        if (!cursor.moveToPosition(Position)){
            throw new IllegalStateException("Error In Data");
        }
        return new GithubUser(cursor);
    }

    @NonNull
    @Override
    public GithubUserAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_githubuser, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GithubUserAdapter.ListViewHolder holder, int position) {
        GithubUser githubUser = getFavoriteItemAdapter(position);
        holder.bind(githubUser);
    }

    @Override
    public int getItemCount() {
        if (cursor == null) return 0;
        return cursor.getCount();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView txtName, txtCompany;
        ListViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.name);
            txtCompany = itemView.findViewById(R.id.company);
            imgPhoto = itemView.findViewById(R.id.photo);
        }

        void bind(GithubUser githubUser) {
            Glide.with(itemView.getContext())
                    .load(githubUser.getAvatar())
                    .into(imgPhoto);
            txtName.setText(githubUser.getName());
            txtCompany.setText(githubUser.getCompany());
        }
    }
}

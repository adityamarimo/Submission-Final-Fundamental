package com.example.myappgithubuser3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myappgithubuser3.db.FavoriteHelper;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {
    private RecyclerView rvFavorite;
    private FavoriteHelper favoriteHelper;
    private GithubUserAdapter adapter;

    private ArrayList<GithubUser> listFavoriteUser = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        favoriteHelper = FavoriteHelper.getInstance(FavoriteActivity.this);
        favoriteHelper.open();

        rvFavorite = findViewById(R.id.rv_listFavorite);
        rvFavorite.setHasFixedSize(true);

        showRecyclerList();
    }

    private void showRecyclerList() {
        rvFavorite.setLayoutManager(new LinearLayoutManager(this));
        listFavoriteUser = favoriteHelper.getAllFavorite();
        adapter = new GithubUserAdapter(listFavoriteUser);
        rvFavorite.setAdapter(adapter);

        adapter.setOnItemClickCallback(new GithubUserAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(GithubUser data) {
                showselectedList(data);
            }
        });
    }

    private void showselectedList(GithubUser data) {
        Intent moveToDetailActivity = new Intent(FavoriteActivity.this, DetailActivity.class);
        moveToDetailActivity.putExtra(DetailActivity.EXTRA_GITHUB, data);
        startActivity(moveToDetailActivity);
    }

}
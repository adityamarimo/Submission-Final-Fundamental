package com.example.favoritesapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import static com.example.favoritesapp.db.DatabaseContract.FavColoumns.CONTENT_URI;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvFavorite;
    private GithubUserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvFavorite = findViewById(R.id.rv_listFavorite);
        rvFavorite.setHasFixedSize(true);

        new FavoriteGithubUsers().execute();
    }

    private class FavoriteGithubUsers extends AsyncTask<Void,Void, Cursor> {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected Cursor doInBackground(Void... voids) {
            return getApplicationContext().getContentResolver().query(CONTENT_URI,null,null,null,null);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            showRecyclerList(cursor);
        }
    }

    private void showRecyclerList(Cursor cursor){
        rvFavorite.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GithubUserAdapter(getApplicationContext(), cursor);
        rvFavorite.setAdapter(adapter);
    }
}

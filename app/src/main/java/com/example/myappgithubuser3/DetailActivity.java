package com.example.myappgithubuser3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myappgithubuser3.db.FavoriteHelper;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.google.android.material.tabs.TabLayout;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_GITHUB = "extra_github";
    private MaterialFavoriteButton materialFavoriteButton;
    private FavoriteHelper favoriteHelper;
    private GithubUser githubUser;

    TextView tvName, tvUsername, tvRepository, tvFollower, tvFollowing, tvCompany, tvLocation;
    ImageView imgPhoto;
    String username, name, avatar, company, followers, following, location, repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setTitle("Detail User");

        favoriteHelper = FavoriteHelper.getInstance(getApplicationContext());
        favoriteHelper.open();

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        getSupportActionBar().setElevation(0);

        setComponent();
        setData();
        checkFavorite();
    }

    private void checkFavorite() {

        boolean existFavorite = favoriteHelper.checkForTableExists(username);
        if (existFavorite) {
            materialFavoriteButton.setFavorite(true);
        }

        materialFavoriteButton.setOnFavoriteChangeListener(
                new MaterialFavoriteButton.OnFavoriteChangeListener() {
                    @Override
                    public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                        if (favorite) {
                            saveFavorite();
                            Toast.makeText(DetailActivity.this, "Added to Favorite", Toast.LENGTH_SHORT).show();
                        } else {
                            deleteFavorite(username);
                            Toast.makeText(DetailActivity.this, "Removed from Favorite", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    private void saveFavorite() {
        githubUser.setLogin(username);
        githubUser.setName(name);
        githubUser.setAvatar(avatar);
        githubUser.setCompany(company);
        githubUser.setFollowers(followers);
        githubUser.setFollowing(following);
        githubUser.setLocation(location);
        githubUser.setRepository(repository);
        favoriteHelper.addFavorite(githubUser);
    }

    private void deleteFavorite(String username) {
        favoriteHelper.deleteFavorite(username);
    }

    private void setData() {
        githubUser = getIntent().getParcelableExtra(EXTRA_GITHUB);
        tvName.setText(githubUser.getName());
        tvUsername.setText(githubUser.getLogin());
        tvRepository.setText(githubUser.getRepository());
        tvFollower.setText(githubUser.getFollowers());
        tvFollowing.setText(githubUser.getFollowing());
        tvCompany.setText("Company : " + githubUser.getCompany());
        tvLocation.setText("Location : " + githubUser.getLocation());
        username = githubUser.getLogin();
        name = githubUser.getName();
        avatar = githubUser.getAvatar();
        company = githubUser.getCompany();
        followers = githubUser.getFollowers();
        following = githubUser.getFollowing();
        location = githubUser.getLocation();
        repository = githubUser.getRepository();
        Glide.with(this).load(githubUser.getAvatar()).into(imgPhoto);
    }

    private void setComponent() {
        tvName = findViewById(R.id.txt_nameavatar);
        tvUsername = findViewById(R.id.txt_username);
        tvRepository = findViewById(R.id.txt_repo);
        tvFollower = findViewById(R.id.txt_follower);
        tvFollowing = findViewById(R.id.txt_following);
        tvCompany = findViewById(R.id.txt_company);
        tvLocation = findViewById(R.id.txt_location);
        imgPhoto = findViewById(R.id.img_avatar);
        materialFavoriteButton = findViewById(R.id.btn_fav);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        } else if (item.getItemId() == R.id.action_favorite_setting) {
            Intent intent = new Intent(this, FavoriteActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.action_reminder_setting) {
            Intent intent = new Intent(this, ReminderActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
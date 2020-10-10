package com.example.favoritesapp;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import static com.example.favoritesapp.db.DatabaseContract.FavColoumns.COMPANY;
import static com.example.favoritesapp.db.DatabaseContract.FavColoumns.FOLLOWERS;
import static com.example.favoritesapp.db.DatabaseContract.FavColoumns.FOLLOWING;
import static com.example.favoritesapp.db.DatabaseContract.FavColoumns.LOCATION;
import static com.example.favoritesapp.db.DatabaseContract.FavColoumns.REPOSITORY;
import static com.example.favoritesapp.db.DatabaseContract.getFavoriteItem;
import static com.example.favoritesapp.db.DatabaseContract.FavColoumns.AVATAR;
import static com.example.favoritesapp.db.DatabaseContract.FavColoumns.NAME;
import static com.example.favoritesapp.db.DatabaseContract.FavColoumns.USERNAME;

public class GithubUser implements Parcelable {
    String avatar;
    String login;

    protected GithubUser(Parcel in) {
        avatar = in.readString();
        login = in.readString();
        name = in.readString();
        location = in.readString();
        repository = in.readString();
        company = in.readString();
        followers = in.readString();
        following = in.readString();
    }

    public static final Creator<GithubUser> CREATOR = new Creator<GithubUser>() {
        @Override
        public GithubUser createFromParcel(Parcel in) {
            return new GithubUser(in);
        }

        @Override
        public GithubUser[] newArray(int size) {
            return new GithubUser[size];
        }
    };

    public GithubUser() {

    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    String name;
    String location;
    String repository;
    String company;
    String followers;
    String following;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(avatar);
        dest.writeString(login);
        dest.writeString(name);
        dest.writeString(location);
        dest.writeString(repository);
        dest.writeString(company);
        dest.writeString(followers);
        dest.writeString(following);
    }

    public GithubUser(Cursor cursor) {
        this.login = getFavoriteItem(cursor, USERNAME);
        this.name = getFavoriteItem(cursor, NAME);
        this.avatar = getFavoriteItem(cursor, AVATAR);
        this.company = getFavoriteItem(cursor, COMPANY);
        this.followers = getFavoriteItem(cursor, FOLLOWERS);
        this.following = getFavoriteItem(cursor, FOLLOWING);
        this.location = getFavoriteItem(cursor, LOCATION);
        this.repository = getFavoriteItem(cursor, REPOSITORY);
    }
}

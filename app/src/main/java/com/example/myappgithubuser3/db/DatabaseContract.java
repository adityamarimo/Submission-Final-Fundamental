package com.example.myappgithubuser3.db;

import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {

    public static final String AUTHORITY = "com.example.myappgithubuser3";
    private static final String SCHEME = "content";

    public static String TABLE_NAME = "favorite";

    public static final class FavColoumns implements BaseColumns {
        static String USERNAME = "username";
        static String NAME = "name";
        static String AVATAR = "avatar";
        static String COMPANY = "company";
        static String FOLLOWERS = "followers";
        static String FOLLOWING = "following";
        static String LOCATION = "location";
        static String REPOSITORY = "repository";
        static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build();
    }
}

package com.example.favoritesapp.db;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {

    public static final String AUTHORITY = "com.example.myappgithubuser3";
    private static final String SCHEME = "content";

    public static String TABLE_NAME = "favorite";

    public static final class FavColoumns implements BaseColumns {
        public static String USERNAME = "username";
        public static String NAME = "name";
        public static String AVATAR = "avatar";
        public static String COMPANY = "company";
        public static String FOLLOWERS = "followers";
        public static String FOLLOWING = "following";
        public static String LOCATION = "location";
        public static String REPOSITORY = "location";
        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build();
    }

    public static String getFavoriteItem(Cursor cursor, String column){
        return cursor.getString(cursor.getColumnIndex(column));
    }
}

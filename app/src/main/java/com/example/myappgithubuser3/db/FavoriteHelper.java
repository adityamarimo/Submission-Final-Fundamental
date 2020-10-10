package com.example.myappgithubuser3.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myappgithubuser3.GithubUser;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.myappgithubuser3.db.DatabaseContract.FavColoumns.AVATAR;
import static com.example.myappgithubuser3.db.DatabaseContract.FavColoumns.COMPANY;
import static com.example.myappgithubuser3.db.DatabaseContract.FavColoumns.FOLLOWERS;
import static com.example.myappgithubuser3.db.DatabaseContract.FavColoumns.FOLLOWING;
import static com.example.myappgithubuser3.db.DatabaseContract.FavColoumns.LOCATION;
import static com.example.myappgithubuser3.db.DatabaseContract.FavColoumns.NAME;
import static com.example.myappgithubuser3.db.DatabaseContract.FavColoumns.REPOSITORY;
import static com.example.myappgithubuser3.db.DatabaseContract.FavColoumns.USERNAME;
import static com.example.myappgithubuser3.db.DatabaseContract.TABLE_NAME;

public class FavoriteHelper {
    private static final String DATABASE_TABLE = TABLE_NAME;
    private static DatabaseHelper dataBaseHelper;
    private static FavoriteHelper INSTANCE;
    private static SQLiteDatabase database;

    public FavoriteHelper(Context context) {
        dataBaseHelper = new DatabaseHelper(context);
    }

    public static FavoriteHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FavoriteHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void addFavorite(GithubUser githubUser) {
        ContentValues values = new ContentValues();
        values.put(USERNAME, githubUser.getLogin());
        values.put(NAME, githubUser.getName());
        values.put(AVATAR, githubUser.getAvatar());
        values.put(COMPANY, githubUser.getCompany());
        values.put(FOLLOWERS, githubUser.getFollowers());
        values.put(FOLLOWING, githubUser.getFollowing());
        values.put(LOCATION, githubUser.getLocation());
        values.put(REPOSITORY, githubUser.getRepository());

        database.insert(DATABASE_TABLE, null, values);
    }

    public int deleteFavorite(String username) {
        return database.delete(TABLE_NAME, USERNAME + " = '" + username + "'", null);
    }

    public boolean checkForTableExists(String username) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE username='" + username + "'";
        Cursor mCursor = database.rawQuery(sql, null);
        if (mCursor.getCount() > 0) {
            return true;
        }
        mCursor.close();
        return false;
    }

    public void open() throws SQLException {
        database = dataBaseHelper.getWritableDatabase();
    }

    public void close() {
        dataBaseHelper.close();
        if (database.isOpen())
            database.close();
    }

    public ArrayList<GithubUser> getAllFavorite() {
        ArrayList<GithubUser> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        GithubUser githubUser;
        if (cursor.getCount() > 0) {
            do {
                githubUser = new GithubUser();
                githubUser.setLogin(cursor.getString(cursor.getColumnIndexOrThrow(USERNAME)));
                githubUser.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)));
                githubUser.setAvatar(cursor.getString(cursor.getColumnIndexOrThrow(AVATAR)));
                githubUser.setCompany(cursor.getString(cursor.getColumnIndexOrThrow(COMPANY)));
                githubUser.setFollowers(cursor.getString(cursor.getColumnIndexOrThrow(FOLLOWERS)));
                githubUser.setFollowing(cursor.getString(cursor.getColumnIndexOrThrow(FOLLOWING)));
                githubUser.setLocation(cursor.getString(cursor.getColumnIndexOrThrow(LOCATION)));
                githubUser.setRepository(cursor.getString(cursor.getColumnIndexOrThrow(REPOSITORY)));
                arrayList.add(githubUser);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public Cursor queryAll() {
        return database.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                _ID + " ASC");
    }
}

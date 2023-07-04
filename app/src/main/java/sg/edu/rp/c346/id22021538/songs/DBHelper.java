package sg.edu.rp.c346.id22021538.songs;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

// Song class
class Song {
    int _id;
    String title;
    String singers;
    int year;
    int stars;

    Song(String title, String singers, int year, int stars) {
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }
}

// DBHelper class
class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "songs.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE songs ( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, " +
                "singers TEXT, " +
                "year INTEGER, " +
                "stars INTEGER " +
                ");";
        db.execSQL(createTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS songs");
        onCreate(db);
    }

    // Create a song record
    public void createSong(Song song) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", song.title);
        values.put("singers", song.singers);
        values.put("year", song.year);
        values.put("stars", song.stars);
        db.insert("songs", null, values);
        db.close();
    }

    // Retrieve all songs
    public List<Song> getAllSongs() {
        List<Song> songs = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectSql = "SELECT * FROM songs";
        Cursor cursor = db.rawQuery(selectSql, null);
        while (cursor.moveToNext()) {
            Song song = new Song();
            song._id = cursor.getInt(0);
            song.title = cursor.getString(1);
            song.singers = cursor.getString(2);
            song.year = cursor.getInt(3);
            song.stars = cursor.getInt(4);
            songs.add(song);
        }
        cursor.close();
        db.close();
        return songs;
    }
}
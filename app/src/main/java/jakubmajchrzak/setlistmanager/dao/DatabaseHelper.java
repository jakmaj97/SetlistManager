package jakubmajchrzak.setlistmanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import jakubmajchrzak.setlistmanager.otherInterfaces.DatabaseInfoInterface;
import jakubmajchrzak.setlistmanager.tablesInterfaces.BandSongTableInterface;
import jakubmajchrzak.setlistmanager.tablesInterfaces.BandTableInterface;
import jakubmajchrzak.setlistmanager.tablesInterfaces.SetlistSongTableInterface;
import jakubmajchrzak.setlistmanager.tablesInterfaces.SetlistTableInterface;
import jakubmajchrzak.setlistmanager.tablesInterfaces.SongTableInterface;

public class DatabaseHelper extends SQLiteOpenHelper implements BandTableInterface, SongTableInterface, BandSongTableInterface, DatabaseInfoInterface, SetlistTableInterface, SetlistSongTableInterface {

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_TABLE_BANDS);
        db.execSQL(SQL_CREATE_TABLE_SONGS);
        db.execSQL(SQL_CREATE_TABLE_BANDS_SONGS);
        db.execSQL(SQL_CREATE_TABLE_SETLISTS);
        db.execSQL(SQL_CREATE_TABLE_SETLISTS_SONGS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(TAG, "Upgrading the database from version " + oldVersion + " to " + newVersion);

        //clear all data
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BANDS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONGS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BANDS_SONGS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETLISTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETLISTS_SONGS);

        //Recreate tables
        onCreate(db);
    }

    public boolean insertData(String bandName) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_BAND_BANDNAME, bandName);
        long result = db.insert(TABLE_BANDS, null, contentValues);
        if(result == -1)
            return false;
        return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_BANDS + ";", null);
        return result;
    }

    public boolean updateData(String bandId, String bandName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_BAND_BANDID, bandId);
        contentValues.put(COLUMN_BAND_BANDNAME, bandName);

        db.update(TABLE_BANDS, contentValues, "bandId = ?", new String[] { bandId });
        return true;
    }

    public Integer deleteData(String bandId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_BANDS, "bandId = ?", new String[] { bandId });
    }
}

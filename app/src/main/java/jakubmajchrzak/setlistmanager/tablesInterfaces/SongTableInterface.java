package jakubmajchrzak.setlistmanager.tablesInterfaces;

public interface SongTableInterface {

    //Columns of the songs table
    String TABLE_SONGS = "songs";
    String COLUMN_SONG_SONGID = "songId";
    String COLUMN_SONG_SONGTITLE = "title";
    String COLUMN_SONG_SONGAUTHOR = "author";
    String COLUMN_SONG_SONGDURATIONTIME= "durationTime";

    //SQL statement of the songs table creation
    String SQL_CREATE_TABLE_SONGS = "CREATE TABLE " + TABLE_SONGS + "("
            + COLUMN_SONG_SONGID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_SONG_SONGTITLE + " TEXT NOT NULL, "
            + COLUMN_SONG_SONGAUTHOR + " TEXT NOT NULL, "
            + COLUMN_SONG_SONGDURATIONTIME + " TEXT NOT NULL "
            + ");";
}

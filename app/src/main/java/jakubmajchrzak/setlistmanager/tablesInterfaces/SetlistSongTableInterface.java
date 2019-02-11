package jakubmajchrzak.setlistmanager.tablesInterfaces;

public interface SetlistSongTableInterface {

    //Columns of the setlists_songs table
    String TABLE_SETLISTS_SONGS = "setlists_songs";
    String COLUMN_SETLISTS_SONGS_SETLISTID = "setlistsSetlistId";
    String COLUMN_SETLISTS_SONGS_SONGID = "songsSongId";

    //SQL statement of the setlists_songs table creation
    String SQL_CREATE_TABLE_SETLISTS_SONGS = "CREATE TABLE " + TABLE_SETLISTS_SONGS + "("
            + COLUMN_SETLISTS_SONGS_SETLISTID + " INTEGER, "
            + COLUMN_SETLISTS_SONGS_SONGID + " INTEGER, "
            + "FOREIGN KEY (" + COLUMN_SETLISTS_SONGS_SETLISTID + ") REFERENCES setlists(setlistId), "
            + "FOREIGN KEY (" + COLUMN_SETLISTS_SONGS_SONGID + ") REFERENCES songs(songId));";
}

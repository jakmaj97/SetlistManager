package jakubmajchrzak.setlistmanager.tablesInterfaces;

public interface BandSongTableInterface {

    //Columns of the bands_songs table
    String TABLE_BANDS_SONGS = "bands_songs";
    String COLUMN_BANDS_SONGS_BANDID = "bandsBandId";
    String COLUMN_BANDS_SONGS_SONGID = "songsSongId";

    //SQL statement of the bands_songs table creation
    String SQL_CREATE_TABLE_BANDS_SONGS = "CREATE TABLE " + TABLE_BANDS_SONGS + "("
            + COLUMN_BANDS_SONGS_BANDID + " INTEGER, "
            + COLUMN_BANDS_SONGS_SONGID + " INTEGER, "
            + "FOREIGN KEY (" + COLUMN_BANDS_SONGS_BANDID + ") REFERENCES bands(bandId), "
            + "FOREIGN KEY (" + COLUMN_BANDS_SONGS_SONGID + ") REFERENCES songs(songId));";
}

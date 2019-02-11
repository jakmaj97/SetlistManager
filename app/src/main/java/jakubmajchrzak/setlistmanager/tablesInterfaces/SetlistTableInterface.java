package jakubmajchrzak.setlistmanager.tablesInterfaces;

public interface SetlistTableInterface {

    //Columns of the setlists table
    String TABLE_SETLISTS = "setlists";
    String COLUMN_SETLIST_SETLISTID = "setlistId";
    String COLUMN_SETLIST_SETLISTNAME = "setlistName";
    String COLUMN_SETLIST_SETLISTBANDID = "bandsSetlistBandId";

    //SQL statement of the setlists table creation
    String SQL_CREATE_TABLE_SETLISTS = "CREATE TABLE " + TABLE_SETLISTS + "("
            + COLUMN_SETLIST_SETLISTID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_SETLIST_SETLISTNAME + " TEXT NOT NULL, "
            + COLUMN_SETLIST_SETLISTBANDID + " INTEGER, "
            + "FOREIGN KEY (" + COLUMN_SETLIST_SETLISTBANDID + ") REFERENCES bands(bandId);";
}

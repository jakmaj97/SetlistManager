package jakubmajchrzak.setlistmanager.tablesInterfaces;

public interface BandTableInterface {

    //Columns of the bands table
    String TABLE_BANDS = "bands";
    String COLUMN_BAND_BANDID = "bandId";
    String COLUMN_BAND_BANDNAME = "bandName";

    //SQL statement of the bands table creation
    String SQL_CREATE_TABLE_BANDS = "CREATE TABLE " + TABLE_BANDS + "("
            + COLUMN_BAND_BANDID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_BAND_BANDNAME + " TEXT NOT NULL "
            + ");";
}

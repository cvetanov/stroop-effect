package mk.ukim.finki.vvkn.stroopeffect.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ResultsDbOpenHelper extends SQLiteOpenHelper {
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_ERROR_CONGRUENT = "errorCongruent";
    public static final String COLUMN_ERROR_INCONGRUENT = "errorIncongruent";
    public static final String COLUMN_ELAPSED_TIME_CONGRUENT = "elapsedTimeCongruent";
    public static final String COLUMN_ELAPSED_TIME_INCONGRUENT = "elapsedTimeIncongruent";
    public static final String TABLE_NAME = "Results";

    private static final String DATABASE_NAME = "StroopEffectResults.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = String.format("create table %s (" +
        "%s integer primary key autoincrement, %s text not null, " +
        "%s real, %s real, %s integer, %s integer);",
            TABLE_NAME, COLUMN_ID, COLUMN_GENDER, COLUMN_ERROR_CONGRUENT, COLUMN_ERROR_INCONGRUENT,
            COLUMN_ELAPSED_TIME_CONGRUENT, COLUMN_ELAPSED_TIME_INCONGRUENT);


    public ResultsDbOpenHelper(Context c)
    {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLE_NAME));
        onCreate(db);
    }
}

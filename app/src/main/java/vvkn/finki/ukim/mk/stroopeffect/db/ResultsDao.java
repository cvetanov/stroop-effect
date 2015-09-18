package vvkn.finki.ukim.mk.stroopeffect.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vvkn.finki.ukim.mk.stroopeffect.models.Result;

public class ResultsDao  {
    private SQLiteDatabase database;
    private ResultsDbOpenHelper dbHelper;
    private String [] allColumns = { ResultsDbOpenHelper.COLUMN_ID, ResultsDbOpenHelper.COLUMN_GENDER,
            ResultsDbOpenHelper.COLUMN_ERROR_CONGRUENT, ResultsDbOpenHelper.COLUMN_ERROR_INCONGRUENT,
            ResultsDbOpenHelper.COLUMN_ELAPSED_TIME_CONGRUENT, ResultsDbOpenHelper.COLUMN_ELAPSED_TIME_INCONGRUENT };

    public ResultsDao(Context c)
    {
        dbHelper = new ResultsDbOpenHelper(c);
    }

    public void open() throws SQLException
    {
        database = dbHelper.getWritableDatabase();
    }

    public void close()
    {
        database.close();
        dbHelper.close();
    }

    public boolean insert(Result r)
    {
        if (r.getId() != null)
        {
            return false;
        }

        long insertedId = database.insert(ResultsDbOpenHelper.TABLE_NAME, null, resultToContentValues(r));
        if (insertedId > 0)
        {
            r.setId(insertedId);
            return true;
        }
        return false;
    }

    public List<Result> getAllResults() {
        List<Result> items = new ArrayList<>();

        Cursor cursor = database.query(ResultsDbOpenHelper.TABLE_NAME, allColumns,
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                items.add(cursorToResult(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return items;
    }

    protected Result cursorToResult(Cursor cursor) {
        Result result = new Result();
        result.setGender(cursor.getString(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_GENDER)));
        result.setElapsedTimeCongruent(cursor.getLong(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_ELAPSED_TIME_CONGRUENT)));
        result.setErrorPercentageCongruent(cursor.getDouble(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_ERROR_CONGRUENT)));
        result.setElapsedTimeIncongruent(cursor.getLong(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_ELAPSED_TIME_INCONGRUENT)));
        result.setErrorPercentageIncongruent(cursor.getDouble(cursor.getColumnIndex(ResultsDbOpenHelper.COLUMN_ERROR_INCONGRUENT)));
        return result;
    }

    protected ContentValues resultToContentValues(Result r)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ResultsDbOpenHelper.COLUMN_GENDER, r.getGender());
        contentValues.put(ResultsDbOpenHelper.COLUMN_ERROR_CONGRUENT, r.getErrorPercentageCongruent());
        contentValues.put(ResultsDbOpenHelper.COLUMN_ERROR_INCONGRUENT, r.getErrorPercentageIncongruent());
        contentValues.put(ResultsDbOpenHelper.COLUMN_ELAPSED_TIME_CONGRUENT, r.getElapsedTimeCongruent());
        contentValues.put(ResultsDbOpenHelper.COLUMN_ELAPSED_TIME_INCONGRUENT, r.getElapsedTimeIncongruent());
        return contentValues;
    }

}

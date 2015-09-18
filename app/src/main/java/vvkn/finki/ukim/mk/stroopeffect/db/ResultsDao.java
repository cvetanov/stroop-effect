package vvkn.finki.ukim.mk.stroopeffect.db;

import android.content.ContentValues;
import android.content.Context;
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

    // TODO: read all results from database
    public List<Result> readResults()
    {
        List<Result> results = new ArrayList<>();
        Result r = new Result();
        r.setGender("f");
        results.add(r);
        return results;
    }

    protected Result contentValuesToResult(ContentValues values)
    {
        Result result = new Result();
        result.setGender(values.getAsString(ResultsDbOpenHelper.COLUMN_GENDER));
        result.setElapsedTimeCongruent(values.getAsLong(ResultsDbOpenHelper.COLUMN_ELAPSED_TIME_CONGRUENT));
        result.setErrorPercentageCongruent(values.getAsDouble(ResultsDbOpenHelper.COLUMN_ERROR_CONGRUENT));
        result.setElapsedTimeIncongruent(values.getAsLong(ResultsDbOpenHelper.COLUMN_ELAPSED_TIME_INCONGRUENT));
        result.setErrorPercentageIncongruent(values.getAsDouble(ResultsDbOpenHelper.COLUMN_ERROR_INCONGRUENT));
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

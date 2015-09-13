package vvkn.finki.ukim.mk.stroopeffect.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import vvkn.finki.ukim.mk.stroopeffect.db.ResultsDbOpenHelper;

public class ResultsContentProvider extends ContentProvider {

    private ResultsDbOpenHelper helper;
    private static final String AUTHORITY = "vvkn.finki.ukim.mk.stroopeffect.contentprovider";
    private static final String BASE_PATH = "results";



    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}

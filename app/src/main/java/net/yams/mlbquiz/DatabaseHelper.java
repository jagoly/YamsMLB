package net.yams.mlbquiz;

import android.content.Context;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

class DatabaseHelper extends SQLiteAssetHelper
{
    public DatabaseHelper(Context context)
    {
        super(context, "mlb.db", null, 1);
    }
}

package applications.listadapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "coupons.db";
    public static final String TABLE_NAME = "coupon_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "STORENAME";
    public static final String COL3 = "EXPIRATIONDATE";
    public static final String COL4 = "COUPONNUMBER";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " STORENAME TEXT, EXPIRATIONDATE TEXT, COUPONNUMBER TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String storeName, String expDate, String coupon) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, storeName);
        contentValues.put(COL3, expDate);
        contentValues.put(COL4, coupon);

        long result = db.insert(TABLE_NAME, null, contentValues);

        // If it's not inserted correctly, it'll return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //Query
    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }
}

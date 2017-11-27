package applications.editablelistview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "couponer-x.db";
    public static final String TABLE_NAME = "coupon_data";
    public static final String ID = "ID";
    public static final String STORENAME = "STORE_NAME";
    public static final String EXP = "EXPIRATION_DATE";
    public static final String COUP = "COUPON_NUMBER";
    public static boolean store_flag = false;
    public static String storeN = STORENAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " STORE_NAME TEXT, EXPIRATION_DATE TEXT, COUPON_NUMBER TEXT, STORE_FLAG BOOLEAN, STOREN TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String storeName, String expDate, String cNum) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(STORENAME, storeName);
        contentValues.put(EXP, expDate);
        contentValues.put(COUP, cNum);

        //if data is inserted incorrectly it will return -1
        if (db.insert(TABLE_NAME, null, contentValues) == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data;
        if (store_flag == false){
            data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        }
        else{
            data = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + STORENAME  + " = '" + storeN + "'", null);
            store_flag  =  false;
        }
        return data;
    }

    public boolean deleteStore(String storeName)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(TABLE_NAME, STORENAME + "=" + storeName, null) > 0;
    }
}
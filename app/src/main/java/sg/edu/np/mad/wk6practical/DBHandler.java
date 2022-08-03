package sg.edu.np.mad.wk6practical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "Wk6PracticalDB.db";
    public static String TABLE_NAME = "Users";
    public static String COLUMN_USERNAME = "name";
    public static String COLUMN_DESCRIPTION = "description";
    public static String COLUMN_ID = "id";
    public static String COLUMN_FOLLOWED = "followed";
    public static int DATABASE_VERSION = 1;

    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_DATABASE = "CREATE TABLE " + TABLE_NAME +
                "(" + COLUMN_USERNAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_FOLLOWED + " TEXT" + ")";
        db.execSQL(CREATE_DATABASE);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addUser(String name, String desc, int id, boolean follow) {

        // Create database to write into
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, name);
        contentValues.put(COLUMN_DESCRIPTION, desc);
        contentValues.put(COLUMN_ID, id);
        contentValues.put(COLUMN_FOLLOWED, follow);

        // Insert new User data into table
        db.insert(TABLE_NAME, null, contentValues);

        // Close database
        db.close();
    }

    public ArrayList<User> getUsers(){

        // Create database to read from
        SQLiteDatabase db = this.getReadableDatabase();

        // Create Cursor object to select data
        Cursor cursor = db.rawQuery("Select * from Users", null);

        // Create arraylist
        ArrayList<User> uList = new ArrayList<>();

        // Select recipes
        if (cursor.moveToFirst()) {
            do {
                // Add recipes
                uList.add(
                        new User(
                                cursor.getString(0),
                                cursor.getString(1),
                                cursor.getInt(2),
                                Boolean.parseBoolean(cursor.getString(3))
                        )
                );
            }
            while (cursor.moveToNext());
        }

        // Close cursor and return list
        cursor.close();
        return uList;
    }

    public User findUser(String name) {

        // Create query
        String query = "SELECT * FROM " + TABLE_NAME +
                " WHERE " + COLUMN_USERNAME + "=\"" + name + "\"";

        // SELECT * FROM table WHERE username = "???"
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User queryData = new User();

        if (cursor.moveToFirst()){
            queryData.setName(cursor.getString(0));
            queryData.setDescription(cursor.getString(1));
            queryData.setId(cursor.getInt(2));
            queryData.setFollowed(Boolean.parseBoolean(cursor.getString(3)));
            cursor.close();
        }
        else{ queryData = null; }
        db.close();
        return queryData;
    }

    public void updateUser(String name, Boolean newFollowed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_USERNAME, name);
        contentValues.put(COLUMN_FOLLOWED, newFollowed);

        db.update(TABLE_NAME, contentValues, "name=?", new String[]{String.valueOf(name)});
        db.close();
    }
}

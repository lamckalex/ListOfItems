package io.github.lamckalex.listofitems;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 1/9/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "NotesDatabase";
    private static final String TABLE_NOTES = "notes";
    private static final String KEY_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_CONTENT = "content";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NOTES + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + COLUMN_TITLE + " TEXT,"
            + COLUMN_CONTENT + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        // Create tables again
        onCreate(db);
    }

    //CRUD

    //Adding a new note
    void addNote(CustomDataObject cdo)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, cdo.getNoteTitle());
        values.put(COLUMN_CONTENT, cdo.getNoteContent());

        db.insert(TABLE_NOTES,null,values);
        db.close();
    }

    //Getting a note
    CustomDataObject getNote(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NOTES, new String[] {KEY_ID, COLUMN_TITLE, COLUMN_CONTENT}, KEY_ID + "=?",
                new String[] {String.valueOf(id) }, null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();

        CustomDataObject cdo = new CustomDataObject(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));

        return cdo;
    }

    //Get all notes
    public List<CustomDataObject> getAllNotes()
    {
        List<CustomDataObject> cdoList = new ArrayList<CustomDataObject>();
        String selectQuery = "SELECT  * FROM " + TABLE_NOTES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst())
        {
            do{
                CustomDataObject cdo = new CustomDataObject();
                cdo.setID(Integer.parseInt(cursor.getString(0)));
                cdo.setNoteTitle((cursor.getString(1)));
                cdo.setNoteContent(cursor.getString(2));
                cdoList.add(cdo);
            }while (cursor.moveToNext());
        }
        return cdoList;
    }

    //Updating a note
    public int updateNote(CustomDataObject cdo)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, cdo.getNoteTitle());
        values.put(COLUMN_CONTENT, cdo.getNoteContent());

        return db.update(TABLE_NOTES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(cdo.getID()) });
    }

    //Deleting a note
    public void deleteNote(CustomDataObject cdo)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NOTES, KEY_ID + " = ?",
                new String[] { String.valueOf(cdo.getID())});
        db.close();
    }

    //Getting the count of notes
    public int getNotesCount()
    {
        String countQuery = "SELECT  * FROM " + TABLE_NOTES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }
}

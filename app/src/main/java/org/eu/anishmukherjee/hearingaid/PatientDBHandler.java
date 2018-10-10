package org.eu.anishmukherjee.hearingaid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class PatientDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "patientDB.db";
    public static final String TABLE_NAME = "Patient";
    public static final String COLUMN_ID = "PatientID";
    public static final String COLUMN_NAME = "PatientName";
    public static final String COLUMN_PASSWORD = "PatientPassword";
    public static final String COLUMN_AUDIOLOGIST = "PatientAudiologist";
    public static final String COLUMN_PROGRESS = "PatientProgress";

    public PatientDBHandler(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " TEXT PRIMARY KEY," +
                COLUMN_NAME + " TEXT," +
                COLUMN_PASSWORD + " TEXT," +
                COLUMN_AUDIOLOGIST + " TEXT," +
                COLUMN_PROGRESS + " INT)";
        db.execSQL(CREATE_TABLE);
    }

    public String loadHandler() {
        String result = "";
        String query = "Select*FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String result_0 = cursor.getString(0);
            String result_1 = cursor.getString(1);
            String result_2 = cursor.getString(2);
            String result_3 = cursor.getString(3);
            int result_4 = cursor.getInt(4);
            result += result_0 + " " + result_1 + " " + result_2 + " " + result_3 + " " + result_4 +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }

    public void addHandler(Patient patient) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, patient.id);
        values.put(COLUMN_NAME, patient.name);
        values.put(COLUMN_PASSWORD, patient.password);
        values.put(COLUMN_AUDIOLOGIST, patient.audiologistName);
        values.put(COLUMN_PROGRESS, patient.progress);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Patient findHandler(String patientId) {
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + "'" + patientId + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Patient patient = new Patient();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            patient.id = cursor.getString(0);
            patient.name = cursor.getString(1);
            patient.password = cursor.getString(2);
            patient.audiologistName = cursor.getString(3);
            patient.progress = cursor.getInt(4);
            cursor.close();
        } else {
            patient = null;
        }
        db.close();
        return patient;
    }

    public ArrayList<Patient> findPatientWithAudiologist(String audiologistId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor  cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        ArrayList<Patient> listPatient = new ArrayList<Patient>();
        int flag = 0;
        if (cursor.moveToFirst()) {
            while(!cursor.isAfterLast()) {
                Patient patient = new Patient();
                patient.id = cursor.getString(0);
                patient.name = cursor.getString(1);
                patient.password = cursor.getString(2);
                patient.audiologistName = cursor.getString(3);
                patient.progress = cursor.getInt(4);
                if (patient.audiologistName.equals(audiologistId)) {
                    flag = 1;
                    listPatient.add(patient);
                }
                cursor.moveToNext();
            }
        }
        if (flag == 0) {
            return null;
        }
        return listPatient;
    }

    /*
    // Delete a record by condition
    public boolean deleteHandler(int ID) {
        booleanresult = false;
        Stringquery = "Select*FROM" + TABLE_NAME + "WHERE" + COLUMN_ID + "= '" + String.valueOf(ID) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Student student = new Student();
        if (cursor.moveToFirst()) {
            student.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_NAME, COLUMN_ID + "=?",
                    newString[] {
                String.valueOf(student.getID())
            });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
    */
    // Update information of a record
    public boolean updateHandler(Patient patient) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, patient.id);
        args.put(COLUMN_NAME, patient.name);
        args.put(COLUMN_PASSWORD, patient.password);
        args.put(COLUMN_AUDIOLOGIST, patient.audiologistName);
        args.put(COLUMN_PROGRESS, patient.progress);
        return db.update(TABLE_NAME, args, COLUMN_ID + " = ?", new String[] {patient.id}) > 0;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //
    }
}

package com.example.yashodhara;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import kotlin.jvm.internal.PropertyReference0Impl;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "yashodhara.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "child_details";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_DOB = "dob";
    private static final String COLUMN_AGE = "age";

    // child table ---------------------------------------------------------------------------------
    private static final String T_CHILD = "child";
    private static final String CHILD_ID = "child_id";
    private static ArrayList<String> CHILD_ATTR;
    static{
        ArrayList<String> child_attr = new ArrayList<String>();
        child_attr.add("Gzjb3fp9FSe");
        child_attr.add("K7Fxa2wv2Rx");
        child_attr.add("kYfIkz2M6En");
        child_attr.add("cpcMXDhQouL");
        child_attr.add("LYRf4eIUVuN");
        child_attr.add("LpvdWM4YuRq");
        child_attr.add("ghN8XfnlU5V");
        child_attr.add("Srxv0vniOnf");
        child_attr.add("b9CoAneYYys");
        child_attr.add("igjlkmMF81X");
        child_attr.add("upQGjAHBjzu");
        child_attr.add("zh4hiarsSD5");
        child_attr.add("Gy4bCBxNuo4");
        child_attr.add("GMNSaaq4xST");
        child_attr.add("lmtzQrlHMYF");
        child_attr.add("h2ATdtJguMq");
        child_attr.add("D9aC5K6C6ne");
        child_attr.add("Fs89NLB2FrA");
        child_attr.add("qNH202ChkV3");
        CHILD_ATTR = child_attr;
    }

    //program tables common columns ----------------------------------------------------------------
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_ENROL_DATE = "doe";

    // anthropometry table -------------------------------------------------------------------------
    private static final String T_ANTHROPOMETRY = "anthropometry";
    private static final String ANTHROPOMETRY_ID = "anthropometry_id";
    private static ArrayList<String> ANTHROPOMETRY_ATTR;
    static{
        ArrayList<String> ant_attr = new ArrayList<String>();
        ant_attr.add("YB21tVtxZ0z");
        ant_attr.add("rBRI27lvfY5");
        ant_attr.add("cDXlUgg1WiZ");
        ant_attr.add("b4Gpl5ayBe3");
        ant_attr.add("bJHCnjX02PN");
        ant_attr.add("SOAtQfInRoy");
        ant_attr.add("jnzg5BvOj5T");
        ANTHROPOMETRY_ATTR = ant_attr;
    }
    // other table -------------------------------------------------------------------------
    private static final String T_OTHER = "other";
    private static final String OTHER_ID = "other_id";
    private static ArrayList<String> OTHER_ATTR;
    static{
        ArrayList<String> ant_attr = new ArrayList<String>();
        ant_attr.add("YB21tVtxZ0z");
        ant_attr.add("rBRI27lvfY5");
        ant_attr.add("cDXlUgg1WiZ");
        ant_attr.add("b4Gpl5ayBe3");
        ant_attr.add("bJHCnjX02PN");
        ant_attr.add("SOAtQfInRoy");
        ant_attr.add("jnzg5BvOj5T");
        OTHER_ATTR = ant_attr;
    }
    // stunt table -------------------------------------------------------------------------
    private static final String T_STUNT = "stunt";
    private static final String STUNT_ID = "stunt_id";
    private static ArrayList<String> STUNT_ATTR;
    static{
        ArrayList<String> ant_attr = new ArrayList<String>();
        ant_attr.add("YB21tVtxZ0z");
        ant_attr.add("rBRI27lvfY5");
        ant_attr.add("cDXlUgg1WiZ");
        ant_attr.add("b4Gpl5ayBe3");
        ant_attr.add("bJHCnjX02PN");
        ant_attr.add("SOAtQfInRoy");
        ant_attr.add("jnzg5BvOj5T");
        STUNT_ATTR = ant_attr;
    }
    // supplementary table -------------------------------------------------------------------------
    private static final String T_SUPPLEMENTARY = "supplementary";
    private static final String SUPPLEMENTARY_ID = "supplementary_id";
    private static ArrayList<String> SUPPLEMENTARY_ATTR;
    static{
        ArrayList<String> ant_attr = new ArrayList<String>();
        ant_attr.add("YB21tVtxZ0z");
        ant_attr.add("rBRI27lvfY5");
        ant_attr.add("cDXlUgg1WiZ");
        ant_attr.add("b4Gpl5ayBe3");
        ant_attr.add("bJHCnjX02PN");
        ant_attr.add("SOAtQfInRoy");
        ant_attr.add("jnzg5BvOj5T");
        SUPPLEMENTARY_ATTR = ant_attr;
    }
    // overweight table -------------------------------------------------------------------------
    private static final String T_OVERWEIGHT = "overweight";
    private static final String OVERWEIGHT_ID = "overweight_id";
    private static ArrayList<String> OVERWEIGHT_ATTR;
    static{
        ArrayList<String> ant_attr = new ArrayList<String>();
        ant_attr.add("YB21tVtxZ0z");
        ant_attr.add("rBRI27lvfY5");
        ant_attr.add("cDXlUgg1WiZ");
        ant_attr.add("b4Gpl5ayBe3");
        ant_attr.add("bJHCnjX02PN");
        ant_attr.add("SOAtQfInRoy");
        ant_attr.add("jnzg5BvOj5T");
        OVERWEIGHT_ATTR = ant_attr;
    }
    // therapeutic table -------------------------------------------------------------------------
    private static final String T_THERAPEUTIC = "therapeutic";
    private static final String THERAPEUTIC_ID = "therapeutic_id";
    private static ArrayList<String> THERAPEUTIC_ATTR;
    static{
        ArrayList<String> ant_attr = new ArrayList<String>();
        ant_attr.add("YB21tVtxZ0z");
        ant_attr.add("rBRI27lvfY5");
        ant_attr.add("cDXlUgg1WiZ");
        ant_attr.add("b4Gpl5ayBe3");
        ant_attr.add("bJHCnjX02PN");
        ant_attr.add("SOAtQfInRoy");
        ant_attr.add("jnzg5BvOj5T");
        THERAPEUTIC_ATTR = ant_attr;
    }
    //----------------------------------------------------------------------------------------------
    private static Context context;

    public Database(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " ("+
                COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, "+
                COLUMN_GENDER + " TEXT, "+
                COLUMN_AGE + " INT, "+
                COLUMN_DOB+" TEXT ) ;";
        db.execSQL(createTableQuery);

        // create child table ----------------------------------------------------------------------
        String createChildTableQuery = "CREATE TABLE " + T_CHILD + " ("+
                COLUMN_ENROL_DATE+ " VARCHAR, "+
                CHILD_ID+ " VARCHAR PRIMARY KEY, ";
        int i=1;
        for (String attr:
             CHILD_ATTR) {
            createChildTableQuery += attr + " TEXT";
            if (i<CHILD_ATTR.size()){
                createChildTableQuery+=", ";
            }else{
                createChildTableQuery+=") ;";
            }
        }
        db.execSQL(createChildTableQuery);

        // create anthropometry program table ------------------------------------------------------
        String createAnthropometryProgramTableQuery = "CREATE TABLE "+T_ANTHROPOMETRY+ " ("+
                COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ENROL_DATE+ " VARCHAR, "+
                COLUMN_DATE+ " VARCHAR, "+
                CHILD_ID+ " VARCHAR PRIMARY KEY, ";
        i=1;
        for (String attr: ANTHROPOMETRY_ATTR) {
            createAnthropometryProgramTableQuery += attr + " TEXT";
            if (i<ANTHROPOMETRY_ATTR.size()){
                createAnthropometryProgramTableQuery+=", ";
            }else{
                createAnthropometryProgramTableQuery+=") ;";
            }
        }
        db.execSQL(createAnthropometryProgramTableQuery);


    // create overweight program table ------------------------------------------------------
    String createOverweightProgramTableQuery = "CREATE TABLE "+T_OVERWEIGHT+ " ("+
            COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_ENROL_DATE+ " VARCHAR, "+

            COLUMN_DATE+ " VARCHAR, "+
            CHILD_ID+ " VARCHAR PRIMARY KEY, ";
    i=1;
        for (String attr: OVERWEIGHT_ATTR) {
        createOverweightProgramTableQuery += attr + " TEXT";
        if (i<OVERWEIGHT_ATTR.size()){
            createOverweightProgramTableQuery+=", ";
        }else{
            createOverweightProgramTableQuery+=") ;";
        }
    }
        db.execSQL(createOverweightProgramTableQuery);

        // create supplementary program table ------------------------------------------------------
        String createSupplementaryProgramTableQuery = "CREATE TABLE "+T_SUPPLEMENTARY+ " ("+
                COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ENROL_DATE+ " VARCHAR, "+
                COLUMN_DATE+ " VARCHAR, "+
                CHILD_ID+ " VARCHAR PRIMARY KEY, ";
        i=1;
        for (String attr: SUPPLEMENTARY_ATTR) {
            createSupplementaryProgramTableQuery += attr + " TEXT";
            if (i<SUPPLEMENTARY_ATTR.size()){
                createSupplementaryProgramTableQuery+=", ";
            }else{
                createSupplementaryProgramTableQuery+=") ;";
            }
        }
        db.execSQL(createSupplementaryProgramTableQuery);

        // create stunt program table ------------------------------------------------------
        String createStuntProgramTableQuery = "CREATE TABLE "+T_STUNT+ " ("+
                COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ENROL_DATE+ " VARCHAR, "+
                COLUMN_DATE+ " VARCHAR, "+
                CHILD_ID+ " VARCHAR PRIMARY KEY, ";
        i=1;
        for (String attr: STUNT_ATTR) {
            createStuntProgramTableQuery += attr + " TEXT";
            if (i<STUNT_ATTR.size()){
                createStuntProgramTableQuery+=", ";
            }else{
                createStuntProgramTableQuery+=") ;";
            }
        }
        db.execSQL(createStuntProgramTableQuery);

        // create other program table ------------------------------------------------------
        String createOtherProgramTableQuery = "CREATE TABLE "+T_OTHER+ " ("+
                COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ENROL_DATE+ " VARCHAR, "+
                COLUMN_DATE+ " VARCHAR, "+
                CHILD_ID+ " VARCHAR PRIMARY KEY, ";
        i=1;
        for (String attr: OTHER_ATTR) {
            createOtherProgramTableQuery += attr + " TEXT";
            if (i<OTHER_ATTR.size()){
                createOtherProgramTableQuery+=", ";
            }else{
                createOtherProgramTableQuery+=") ;";
            }
        }
        db.execSQL(createOtherProgramTableQuery);

        // create therapeutic program table ------------------------------------------------------
        String createTherapeuticProgramTableQuery = "CREATE TABLE "+T_THERAPEUTIC+ " ("+
                COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ENROL_DATE+ " VARCHAR, "+
                COLUMN_DATE+ " VARCHAR, "+
                CHILD_ID+ " VARCHAR PRIMARY KEY, ";
        i=1;
        for (String attr: THERAPEUTIC_ATTR) {
            createTherapeuticProgramTableQuery += attr + " TEXT";
            if (i<THERAPEUTIC_ATTR.size()){
                createTherapeuticProgramTableQuery+=", ";
            }else{
                createTherapeuticProgramTableQuery+=") ;";
            }
        }
        db.execSQL(createTherapeuticProgramTableQuery);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addChild(String name, String gender, String dob, double age){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_AGE,age);
        cv.put(COLUMN_DOB,dob);
        cv.put(COLUMN_GENDER,gender);

        long result = db.insert(TABLE_NAME,null,cv);
        if (result==-1){
            Toast.makeText(context, "Failed to save data", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully saved data", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllChildData(){
        String getChildDataQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db!=null){
            cursor = db.rawQuery(getChildDataQuery,null);
        }
        return cursor;
    }

    // add new entry to child table
    public boolean enterChildToChild(HashMap<String,String> data,String childId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        Iterator entryIterator = data.entrySet().iterator();
        cv.put(CHILD_ID,childId);
        for (Map.Entry entry : data.entrySet()) {
            cv.put(entry.getKey().toString(), entry.getValue().toString());
        }
        long result = db.insert(T_CHILD,null,cv);
        if (result==-1){
            return false;
        }
        return true;
    }

    //add child to program
    public boolean insertChildProgramInfo(HashMap<String,String> data,String program, String childId) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(CHILD_ID, childId);
        Iterator entryIterator = data.entrySet().iterator();
        for (Map.Entry entry : data.entrySet()) {
            cv.put(entry.getKey().toString(), entry.getValue().toString());
        }
        long result = 0;

        if (program.equals(T_ANTHROPOMETRY)) {
            result = db.insert(T_ANTHROPOMETRY, null, cv);
        }
        if (program.equals(T_OTHER)) {
            result = db.insert(T_OTHER, null, cv);
        }
        if (program.equals(T_SUPPLEMENTARY) ){
            result = db.insert(T_SUPPLEMENTARY, null, cv);
        }
        if (program.equals(T_STUNT)) {
            result = db.insert(T_STUNT, null, cv);
        }
        if (program.equals(T_OVERWEIGHT)) {
            result = db.insert(T_OVERWEIGHT, null, cv);
        }
        if (result == -1) {
            return false;
        }

            return true;
        }

        //update program info
        //add child to program
        public boolean enterChildToProgram(HashMap<String,String> data,String program, String childId) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues cv = new ContentValues();
            cv.put(CHILD_ID, childId);

            Iterator entryIterator = data.entrySet().iterator();
            for (Map.Entry entry : data.entrySet()) {
                cv.put(entry.getKey().toString(), entry.getValue().toString());
            }
            long result = 0;

            if (program.equals(T_ANTHROPOMETRY)) {
                result = db.insert(T_ANTHROPOMETRY, null, cv);
            }
            if (program.equals(T_OTHER)) {
                result = db.insert(T_OTHER, null, cv);
            }
            if (program.equals(T_SUPPLEMENTARY) ){
                result = db.insert(T_SUPPLEMENTARY, null, cv);
            }
            if (program.equals(T_STUNT)) {
                result = db.insert(T_STUNT, null, cv);
            }
            if (program.equals(T_OVERWEIGHT)) {
                result = db.insert(T_OVERWEIGHT, null, cv);
            }
            if (result == -1) {
                return false;
            }

            return true;
        }


}

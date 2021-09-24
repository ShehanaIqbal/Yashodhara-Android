package com.example.yashodhara;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "yashodhara.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SYNC = "sync";

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
    private static final String COLUMN_ID = "id";

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

        // create child table ----------------------------------------------------------------------
        String createChildTableQuery = "CREATE TABLE " + T_CHILD + " ("+
                COLUMN_ENROL_DATE+ " VARCHAR, "+
                SYNC+ " boolean, "+
                CHILD_ID+ " VARCHAR PRIMARY KEY, ";
        int i=1;
        for (String attr: CHILD_ATTR) {
            createChildTableQuery += attr + " TEXT";
            if (i<CHILD_ATTR.size()){
                createChildTableQuery+=", ";
            }else{
                createChildTableQuery+=") ;";
            }
            i++;
        }
        Log.i("YYY","childquery: " + createChildTableQuery);
        db.execSQL(createChildTableQuery);

        // create anthropometry program table ------------------------------------------------------
        String createAnthropometryProgramTableQuery = "CREATE TABLE "+T_ANTHROPOMETRY+ " ("+
                COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ENROL_DATE+ " VARCHAR, "+
                SYNC+ " boolean, "+
                COLUMN_DATE+ " VARCHAR, "+
                CHILD_ID+ " VARCHAR, ";
        i=1;
        for (String attr: ANTHROPOMETRY_ATTR) {
            createAnthropometryProgramTableQuery += attr + " TEXT";
            if (i<ANTHROPOMETRY_ATTR.size()){
                createAnthropometryProgramTableQuery+=", ";
            }else{
                createAnthropometryProgramTableQuery+=") ;";
            }
            i++;
        }
        db.execSQL(createAnthropometryProgramTableQuery);


    // create overweight program table ------------------------------------------------------
    String createOverweightProgramTableQuery = "CREATE TABLE "+T_OVERWEIGHT+ " ("+
            COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_ENROL_DATE+ " VARCHAR, "+
            SYNC+ " boolean, "+
            COLUMN_DATE+ " VARCHAR, "+
            CHILD_ID+ " VARCHAR, ";
    i=1;
        for (String attr: OVERWEIGHT_ATTR) {
        createOverweightProgramTableQuery += attr + " TEXT";
        if (i<OVERWEIGHT_ATTR.size()){
            createOverweightProgramTableQuery+=", ";
        }else{
            createOverweightProgramTableQuery+=") ;";
        }
        i++;
    }
        db.execSQL(createOverweightProgramTableQuery);

        // create supplementary program table ------------------------------------------------------
        String createSupplementaryProgramTableQuery = "CREATE TABLE "+T_SUPPLEMENTARY+ " ("+
                COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ENROL_DATE+ " VARCHAR, "+
                SYNC+ " boolean, "+
                COLUMN_DATE+ " VARCHAR, "+
                CHILD_ID+ " VARCHAR, ";
        i=1;
        for (String attr: SUPPLEMENTARY_ATTR) {
            createSupplementaryProgramTableQuery += attr + " TEXT";
            if (i<SUPPLEMENTARY_ATTR.size()){
                createSupplementaryProgramTableQuery+=", ";
            }else{
                createSupplementaryProgramTableQuery+=") ;";
            }
            i++;
        }
        db.execSQL(createSupplementaryProgramTableQuery);

        // create stunt program table ------------------------------------------------------
        String createStuntProgramTableQuery = "CREATE TABLE "+T_STUNT+ " ("+
                COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ENROL_DATE+ " VARCHAR, "+
                SYNC+ " boolean, "+
                COLUMN_DATE+ " VARCHAR, "+
                CHILD_ID+ " VARCHAR, ";
        i=1;
        for (String attr: STUNT_ATTR) {
            createStuntProgramTableQuery += attr + " TEXT";
            if (i<STUNT_ATTR.size()){
                createStuntProgramTableQuery+=", ";
            }else{
                createStuntProgramTableQuery+=") ;";
            }
            i++;
        }
        db.execSQL(createStuntProgramTableQuery);

        // create other program table ------------------------------------------------------
        String createOtherProgramTableQuery = "CREATE TABLE "+T_OTHER+ " ("+
                COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ENROL_DATE+ " VARCHAR, "+
                SYNC+ " boolean, "+
                COLUMN_DATE+ " VARCHAR, "+
                CHILD_ID+ " VARCHAR, ";
        i=1;
        for (String attr: OTHER_ATTR) {
            createOtherProgramTableQuery += attr + " TEXT";
            if (i<OTHER_ATTR.size()){
                createOtherProgramTableQuery+=", ";
            }else{
                createOtherProgramTableQuery+=") ;";
            }
            i++;
        }
        db.execSQL(createOtherProgramTableQuery);

        // create therapeutic program table ------------------------------------------------------
        String createTherapeuticProgramTableQuery = "CREATE TABLE "+T_THERAPEUTIC+ " ("+
                COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ENROL_DATE+ " VARCHAR, "+
                SYNC+ " boolean, "+
                COLUMN_DATE+ " VARCHAR, "+
                CHILD_ID+ " VARCHAR , ";
        i=1;
        for (String attr: THERAPEUTIC_ATTR) {
            createTherapeuticProgramTableQuery += attr + " TEXT";
            if (i<THERAPEUTIC_ATTR.size()){
                createTherapeuticProgramTableQuery+=", ";
            }else{
                createTherapeuticProgramTableQuery+=") ;";
            }
            i++;
        }
        db.execSQL(createTherapeuticProgramTableQuery);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int k) {
        db.execSQL("DROP TABLE IF EXISTS " + "child");
        onCreate(db);
    }

    // add new entry to child table
    public boolean enterChildToChild(HashMap<String,String> data,String childId,boolean sync, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        Iterator entryIterator = data.entrySet().iterator();
        cv.put(CHILD_ID,childId);
        cv.put(COLUMN_ENROL_DATE, date);
        cv.put(SYNC,sync);
        for (Map.Entry entry : data.entrySet()) {
            cv.put(entry.getKey().toString(), entry.getValue().toString());
        }
        long result = db.insert(T_CHILD,null,cv);
        if (result==-1){
            return false;
        }
        return true;
    }

    //read single child details - any table
    public HashMap<String,String> readSingleChildData (String childId,String tableName, ArrayList<String> attrs){
        SQLiteDatabase db= this.getReadableDatabase();
        String query = "SELECT * FROM "+tableName+ " WHERE "+CHILD_ID+"="+childId;
        Log.i("YYY",childId+ ": childSelectQuery :"+query);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                // move the cursor to next row if there is any to read it's data
                if (tableName.equals(T_CHILD)){
                    HashMap<String, String> child = getSingleChildFromChild(cursor, attrs);
                    return child;
                }else {
                    HashMap<String, String> child = getSingleChildFromChildFromProgram(cursor, attrs);
                    return child;
                }
            }
        }
        return null;
    }

    //read all children details - any table
    public ArrayList<HashMap<String,String>> readAllChildrenData (String tableName, ArrayList<String> attrs){
        SQLiteDatabase db= this.getReadableDatabase();
        String query = "SELECT * FROM "+ T_CHILD;
        Log.i("YYY",tableName+ ": childSelectQuery :"+query);
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<HashMap<String,String>> allChildList = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                // move the cursor to next row if there is any to read it's data
                if (tableName.equals(T_CHILD)){
                    HashMap<String, String> child = getSingleChildFromChild(cursor, attrs);
                    allChildList.add(child);
                }else {
                    HashMap<String, String> child = getSingleChildFromChildFromProgram(cursor, attrs);
                    allChildList.add(child);
                }
            }
            return allChildList;
        }
        return null;
    }


    private HashMap<String,String> getSingleChildFromChild(Cursor cursor, ArrayList<String> attrList) {
        HashMap<String,String> child=new HashMap<>();

        //get basic attributes
        int doe = cursor.getColumnIndex(COLUMN_ENROL_DATE);
        String doe_value = cursor.getString(doe);
        child.put(COLUMN_ENROL_DATE,doe_value);
        int sync = cursor.getColumnIndex(SYNC);
        String sync_value = cursor.getString(sync);
        child.put(SYNC,sync_value);
        int c_id = cursor.getColumnIndex(CHILD_ID);
        String cid_value = cursor.getString(c_id);
        child.put(CHILD_ID,cid_value);

        //get other attributes
        for (String attr:attrList) {
            int col_id = cursor.getColumnIndex(attr);
            String value = cursor.getString(col_id);
            child.put(attr,value);
        }
        return child;
    }

    private HashMap<String,String> getSingleChildFromChildFromProgram(Cursor cursor,ArrayList<String> attrList) {
        HashMap<String,String> child=new HashMap<>();

        //get basic attributes
        int id = cursor.getColumnIndex(COLUMN_ID);
        String id_val = cursor.getString(id);
        child.put(COLUMN_ID,id_val);
        int doe = cursor.getColumnIndex(COLUMN_ENROL_DATE);
        String doe_value = cursor.getString(doe);
        child.put(COLUMN_ENROL_DATE,doe_value);
        int sync = cursor.getColumnIndex(SYNC);
        String sync_value = cursor.getString(sync);
        child.put(SYNC,sync_value);
        int date = cursor.getColumnIndex(COLUMN_DATE);
        String date_value = cursor.getString(date);
        child.put(COLUMN_DATE,date_value);
        int c_id = cursor.getColumnIndex(CHILD_ID);
        String cid_value = cursor.getString(c_id);
        child.put(CHILD_ID,cid_value);

        //get other attributes
        for (String attr:attrList) {
            int col_id = cursor.getColumnIndex(attr);
            String value = cursor.getString(col_id);
            child.put(attr,value);
        }
        return child;
    }

        //update program info
        //add child to program
        public boolean enterChildToProgram(HashMap<String,String> data,String program, String childId,boolean sync, String date, String updatedDate) {
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

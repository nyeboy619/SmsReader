package rt.com.readme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import java.sql.*;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Student.db";
    private static final int DATABASE_VERSION = 1;

    private Context context;

    public Database(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;

    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE MESSAGES (ID INTEGER PRIMARY KEY,NUMBER INTEGER,BODY TEXT )");
//




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS MESSAGES");
    }
		SQLiteDatabase db;

    public void insertMessages(String number,String body,String date){
				long result = -1;
         db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NUMBER",number);
        cv.put("BODY",body);
				cv.put("DATE",date.toString());
				
        result = db.insert("MESSAGES",null,cv);
				
        db.close();

        if(result==-1){
            Toast.makeText(this.context,"Data insert Failed!",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this.context,"Data added successfully!",Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor view(){

        Toast.makeText(context, "opening db", Toast.LENGTH_SHORT).show();
         db = getWritableDatabase();
        Cursor res = db.rawQuery(" SELECT * FROM MESSAGES ",null);

        return res;


    }




}

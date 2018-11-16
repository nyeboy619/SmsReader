package rt.com.readme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "sms";
    private static final int DATABASE_VERSION = 1;

    private Context context;

    public Database(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE MESSAGES (NUMBER INTEGER PRIMARY KEY,BODY TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insertMessages(String number,String body){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NUMBER",number);
        cv.put("BODY",body);
        long result = db.insert("MESSAGES",null,cv);
        db.close();

        if(result==-1){
            Toast.makeText(this.context,"Data insert Failed!",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this.context,"Data added successfully!",Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor view(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from CONTACTS",null);
        return res;
    }


}

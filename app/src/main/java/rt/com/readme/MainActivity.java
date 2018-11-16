package rt.com.readme;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity {

    ListView lv;
    private ArrayList<String> smsList;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new Database(this);

        setContentView(R.layout.main_layout);
        lv = findViewById(R.id.lv);
        Button b = findViewById(R.id.b);
        b.setText("Read database content");
        Button b2 = findViewById(R.id.b2);
        b2.setText("Copy Sms content to database");










    }
    public  void copy(View v){
        insertToDatabase();
    }

    public  void read(View v){
        readDatabase();
    }
    private void insertToDatabase() {

        Uri inboxUri = Uri.parse("content://sms/inbox");

        ContentResolver cr = getContentResolver();

        Cursor c = cr.query(inboxUri,null,null,null,null);
        while(c.moveToNext()){
            String number = c.getString(c.getColumnIndexOrThrow("address"));
            String body = c.getString(c.getColumnIndexOrThrow("body"));

            db.insertMessages(number,body);
            Toast.makeText(this,"Inserting Data . . .", Toast.LENGTH_SHORT).show();
        }

        c.close();



    }

    private void readDatabase() {

        Cursor c = db.view();

        smsList = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        if(c!=null && c.getCount()>0){
            while (c.moveToNext()){
                sb.append("Number : "+c.getString(1)+"\n");
                sb.append("Message : "+c.getString(2)+"\n");

            }
            smsList.add(sb.toString());



            ArrayAdapter<String> la = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, smsList);
            lv.setAdapter(la);
        }

    }


}

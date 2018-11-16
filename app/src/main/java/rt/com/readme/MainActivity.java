package rt.com.readme;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
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

        ProgressBar pb = new ProgressBar(this);




    }

    private void insertToDatabase() {

        Uri inboxUri = Uri.parse("content://sms/inbox");
        smsList = new ArrayList<>();
        ContentResolver cr = getContentResolver();

        Cursor c = cr.query(inboxUri,null,null,null,null);
        while(c.moveToNext()){
            String number = c.getString(c.getColumnIndexOrThrow("address"));
            String body = c.getString(c.getColumnIndexOrThrow("body"));

            db.insertMessages(number,body);






            Toast.makeText(this,"Inserting Data . . .", Toast.LENGTH_SHORT).show();
        }
        c.close();
        ArrayAdapter<String> la = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, smsList);
        lv.setAdapter(la);

    }
}

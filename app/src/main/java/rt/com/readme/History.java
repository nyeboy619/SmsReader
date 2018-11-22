package rt.com.readme;
import android.app.*;
import android.content.*;
import android.database.*;
import android.graphics.*;
import android.net.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.util.*;

public class History extends Activity
{
		ListView lv;
    private ArrayList<String> smsList;
    Database db;

		

		@Override
		protected void onCreate(Bundle savedInstanceState)
		{
				// TODO: Implement this method
				super.onCreate(savedInstanceState);
				setContentView(R.layout.main_list);
				
				
				lv = findViewById(R.id.lv);
				db = new Database(this);
				
				//insertToDatabase();
				
				
				
				//readDatabase();
				
				
				
		}
		public void copy(View v){
				insertToDatabase();
		}
		public void read(View v){
				readDatabase();
		}
		
		

   
    private void insertToDatabase() {

        Uri inboxUri = Uri.parse("content://sms/inbox");

        ContentResolver cr = getContentResolver();

        Cursor c = cr.query(inboxUri,null,null,null,null);
        while(c.moveToNext()){
            String number = c.getString(c.getColumnIndexOrThrow("address"));
            String body = c.getString(c.getColumnIndexOrThrow("body"));
						String date = c.getString(c.getColumnIndexOrThrow("date"));
						switch(number){
								case "09125683179":
								case "+639125683179":
								case "Mommy tnt":
										db.insertMessages(number,body,date);
										Toast.makeText(this,"Inserting Data . . .", Toast.LENGTH_SHORT).show();

						}


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
								//smsList.add(sb.toString());

            }
            smsList.add(sb.toString());



            ArrayAdapter<String> la = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, smsList);
            lv.setAdapter(la);
        }

    }

		public void draw(Canvas c){
				c.drawColor(Color.BLACK);
		}

		
}

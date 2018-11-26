package rt.com.readme;
import android.app.*;
import android.content.*;
import android.database.*;
import android.net.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.util.*;

public class History extends Activity
{
		ListView lv;
    private ArrayList<String> smsList;
		ArrayAdapter<String> la;
    Database db;

		private String url;

		

		@Override
		protected void onCreate(Bundle savedInstanceState)
		{
				// TODO: Implement this method
				super.onCreate(savedInstanceState);
				setContentView(R.layout.main_list);
				getActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP);
				
				
				
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
					
						switch(number){
								case "09481769397":
								case "+639481769397":
								
										db.insertMessages(number,body);
										Toast.makeText(this,"Inserting Data . . .", Toast.LENGTH_SHORT).show();

						}


        }

        c.close();



    }

    private void readDatabase() {

				Cursor c=null;
				try{
         c = db.view();

			  }catch(Exception w){
						e();
				}
        smsList = new ArrayList<String>();
        StringBuffer sb = new StringBuffer();
        if(c!=null && c.getCount()>0){
            while (c.moveToNext()){
                
                sb.append("Message : "+c.getString(2)+"\n");
								url = c.getString(1);
								smsList.add(url);

            }
            //smsList.add(sb.toString());



						c.close();
             la = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, smsList);
            lv.setAdapter(la);
        }
				

    }
		
		
		
		void e(){
				Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
		}

		
}

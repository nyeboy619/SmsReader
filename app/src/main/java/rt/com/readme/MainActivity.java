package rt.com.readme;

import android.app.*;
import android.content.*;
import android.database.*;
import android.graphics.*;
import android.net.*;
import android.os.*;
import android.view.*;
import android.webkit.*;
import android.widget.*;
import java.util.*;
import android.widget.AdapterView.*;



public class MainActivity extends Activity {

    
		ActionBar ab;
		
		WebView wv;
		MainThread thread;

		private ArrayList smsList;
		ListView lv;
		Database db;
		

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
				
				thread = new MainThread(this);

      
				getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME);
				requestWindowFeature(Window.FEATURE_ACTION_BAR);
				
				setContentView(R.layout.main);
				
				wv = findViewById(R.id.wv);
				wv.setWebViewClient(new WebViewClient());
				wv.loadUrl("http://www.free.facebook.com");
				
				
				
				
				//thread.start();

        
        

    }

		@Override
		public boolean onCreateOptionsMenu(Menu menu)
		{
				getMenuInflater().inflate(R.menu.item,menu);
				return true;
		}

		@Override
		public boolean onOptionsItemSelected(MenuItem item)
		{
				// TODO: Implement this method
				
				switch(item.getItemId()){
						case R.id.history:
								Intent i = new Intent(this,History.class);
								startActivity(i);
				}
				
				
				return true;
		}
		
		
		
		
		
		
		
		
		public class MainThread extends Thread{
				
				MainActivity activity;
				public MainThread(MainActivity activity){
						
						this.activity= activity;
				}

				@Override
				public void run()
				{
						super.run();
								Toast.makeText(activity.getApplicationContext(),"updating",Toast.LENGTH_SHORT).show();
								
						
						
				}
				
				
		}
		
		private String readDatabase() {
				String url= null;

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
				return url;

    }


}

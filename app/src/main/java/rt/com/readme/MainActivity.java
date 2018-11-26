package rt.com.readme;

import android.app.*;
import android.content.*;
import android.database.*;
import android.os.*;
import android.view.*;
import android.webkit.*;
import android.widget.*;



public class MainActivity extends Activity {

    
		ActionBar ab;
		String url;
		WebView wv;
		Database db;
		MenuItem mi;
		

		
		
	
		
		

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
				
				db = new Database(this);
		

      
				getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME);
				requestWindowFeature(Window.FEATURE_ACTION_BAR);
				
		//		mi = findViewById(R.id.update);
			//	mi.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
				
				setContentView(R.layout.main);
				
				
				wv = findViewById(R.id.wv);
				wv.setWebViewClient(new WebViewClient());
				
				
				wv.loadUrl("http://www.google.com");
			
			
				
			
				
				
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
								break;
						case R.id.update:
								wv.loadUrl("http://www.hd"+readDatabase()+"kd.com");
								break;
				}
				
				
				return true;
		}
		
		private String readDatabase(){
				Cursor a;
						a = db.view();
						
					
						
						try{
								
						
						if(a.moveToNext()){
						url = a.getString(1);
						}
						}catch(Exception e){
								Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
						}

        
						a.close();
					

			
					
				
				return url;
    }


}

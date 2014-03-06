package fr.iutlpirm3.kdo;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	private ExpandableListView maliste;
	private ImageButton bouton_photo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bouton_photo = (ImageButton) findViewById(R.id.add_kdo);
		bouton_photo.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        	Intent myIntent = new Intent(v.getContext(), PhotoActivity.class);
                startActivityForResult(myIntent, 0);
	        }
	    });
		writeJSON();
		JSONtoView();
		 SharedPreferences sharedPref = getSharedPreferences("json_data", 0);
	       String strJson = sharedPref.getString("infos_users",null);
	       Log.v("testJSON",strJson);
		


	}

		
	public void writeJSON() {
		  JSONObject infos_users = new JSONObject();
		  try {
			  // rempli l'object JSON avec des infos fictives
			  infos_users.put("name", "Torvald");
			  infos_users.put("firstname", "Linus");
			  infos_users.put("age", "44 ans");
			  infos_users.put("occupation", "Student");
			  infos_users.put("birthdate", "28/12/1969");
			  infos_users.put("mail", "linustorvald@fake.com");
			  // mets l'object json dans les sharedpref en format String
			  SharedPreferences json_infos_users = getSharedPreferences("json_data", 0);
		      SharedPreferences.Editor editor = json_infos_users.edit();
		      editor.putString("infos_users", infos_users.toString());
		      editor.commit();

		  } catch (JSONException e) {
		    e.printStackTrace();
		  }
		  Log.v("object JSON : ",infos_users.toString());
		}
	
	public void JSONtoView(){
		try {
			 SharedPreferences sharedPref = getSharedPreferences("json_data", 0);
		       String strJson = sharedPref.getString("infos_users",null);
		       if(strJson != null){
		    	   JSONObject jsonData = new JSONObject(strJson);
					String infos_users_name = (String) jsonData.get("name");
					String infos_users_firstname = (String) jsonData.get("firstname");
					String infos_users_age = (String) jsonData.get("age");
					String infos_users_occupation = (String) jsonData.get("occupation");
					String infos_users_birthdate = (String) jsonData.get("birthdate");
					String infos_users_mail = (String) jsonData.get("mail");
					TextView label_name = (TextView) findViewById(R.id.nom_label);
					label_name.setText(infos_users_name);
					TextView label_firstname = (TextView) findViewById(R.id.prenom_label);
					label_firstname.setText(infos_users_firstname);
					TextView label_age = (TextView) findViewById(R.id.age_label);
					label_age.setText(infos_users_age);
					TextView label_situation = (TextView) findViewById(R.id.situation_label);
					label_situation.setText(infos_users_occupation);
					TextView datenaissance_label = (TextView) findViewById(R.id.datenaissance_label);
					datenaissance_label.setText(infos_users_birthdate);
					TextView email_label = (TextView) findViewById(R.id.email_label);
					email_label.setText(infos_users_mail);
		       }
		       

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.action_exit:
	        	moveTaskToBack(true);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
}

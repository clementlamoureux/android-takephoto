package fr.iutlpirm3.kdo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;

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



	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

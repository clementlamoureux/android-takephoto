package fr.iutlpirm3.kdo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsKdoActivity extends ActionBarActivity{
	private int i;
	private String mCurrentPhotoPath;
	private ImageView mImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		mImageView = (ImageView) findViewById(R.id.imageView1);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    i = extras.getInt("id_kdo");
		    display_kdo();
		}
		
		
	}
	
	public void display_kdo(){
		SharedPreferences sharedPref = getSharedPreferences("json_data", 0);
		String temp_sp = sharedPref.getString("liste_kdo", null);
		JSONObject temp;
		try {
			temp = new JSONObject(temp_sp);
			JSONArray list_kdo_array = temp.getJSONArray("liste");
		    JSONObject temp1 = list_kdo_array.getJSONObject(i);
		    TextView temp2 = (TextView) findViewById(R.id.dest_nom);
		    TextView temp3 = (TextView) findViewById(R.id.dest_prix);
		    TextView temp4 = (TextView) findViewById(R.id.dest_lieu);
		    TextView temp5 = (TextView) findViewById(R.id.label_lieuachat);
		    temp2.setText(temp1.getString("nom"));
		    temp3.setText(temp1.getString("prix") + "€");
		    if (temp1.getString("lieu")!=null){
			    temp4.setText(temp1.getString("lieu"));
			    temp5.setVisibility(View.VISIBLE);
		    }
		    mCurrentPhotoPath = temp1.getString("image");
		    setPic();
		    
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void setPic() {

		/* There isn't enough memory to open up more than a couple camera photos */
		/* So pre-scale the target bitmap into which the file is decoded */

		/* Get the size of the ImageView */
		int targetW = mImageView .getWidth();
		int targetH = mImageView.getHeight();

		/* Get the size of the image */
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
		int photoW = bmOptions.outWidth;
		int photoH = bmOptions.outHeight;
		
		/* Figure out which way needs to be reduced less */
		int scaleFactor = 1;
		if ((targetW > 0) || (targetH > 0)) {
			scaleFactor = Math.min(photoW/targetW, photoH/targetH);	
		}

		/* Set bitmap options to scale the image decode target */
		bmOptions.inJustDecodeBounds = false;
		bmOptions.inSampleSize = scaleFactor;
		bmOptions.inPurgeable = true;

		/* Decode the JPEG file into a Bitmap */
		Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
		
		/* Associate the Bitmap to the ImageView */
		mImageView.setImageBitmap(bitmap);
		mImageView.setVisibility(View.VISIBLE);
	}
}

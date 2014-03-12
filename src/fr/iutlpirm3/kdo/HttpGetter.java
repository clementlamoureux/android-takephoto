package fr.iutlpirm3.kdo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public class HttpGetter extends AsyncTask<URL, Void, Void> {

    protected Void doInBackground(URI... urls) {
            // TODO Auto-generated method stub
            StringBuilder builder = new StringBuilder();
            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(urls[0]);
            
            try {
                    HttpResponse response = client.execute(httpGet);
                    StatusLine statusLine = response.getStatusLine();
                    int statusCode = statusLine.getStatusCode();
                    if (statusCode == 200) {
                            HttpEntity entity = response.getEntity();
                            InputStream content = entity.getContent();
                            BufferedReader reader = new BufferedReader(
                                            new InputStreamReader(content));
                            String line;
                            while ((line = reader.readLine()) != null) {
                                    builder.append(line);
                            }
                            Log.v("Getter", "Your data: " + builder.toString()); //response data
                            
                    } else {
                            Log.e("Getter", "Failed to download file");
                    }
            } catch (ClientProtocolException e) {
                    e.printStackTrace();
            } catch (IOException e) {
                    e.printStackTrace();
            }
            
            return null;
    }

	protected Void doInBackground(URL... arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}

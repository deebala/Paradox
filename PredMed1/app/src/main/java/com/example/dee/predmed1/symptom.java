package com.example.dee.predmed1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class symptom extends ActionBarActivity {


    Bundle b_glob=new Bundle();
    Intent i_glob=new Intent(this.getApplicationContext(),diagnosis.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom);




        Spinner symptom_spinner = (Spinner) findViewById(R.id.symptoms_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> symptom_adapter = ArrayAdapter.createFromResource(this,R.array.symptoms_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        symptom_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        symptom_spinner.setAdapter(symptom_adapter);

        Spinner intensity_spinner = (Spinner) findViewById(R.id.intensity_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> intensity_adapter = ArrayAdapter.createFromResource(this,R.array.intensity_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        intensity_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        intensity_spinner.setAdapter(intensity_adapter);

        Spinner duration_spinner = (Spinner) findViewById(R.id.duration_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> duration_adapter = ArrayAdapter.createFromResource(this,R.array.duration_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        duration_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        duration_spinner.setAdapter(duration_adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_symptom, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void storesym_callback(View view) {

        //EditText name;
        //name=(EditText)findViewById(R.id.editText3);


        Spinner spinner = (Spinner) findViewById(R.id.symptoms_spinner);
        String symptom = spinner.getSelectedItem().toString(); // Fever, Cough
        //and to get the values:

        int spinner_pos = spinner.getSelectedItemPosition();
        //String[] size_values = getResources().getStringArray(R.array.size_values);
        //int size1 = Integer.valueOf(size_values[spinner_pos]); // 1,2,,3,4

        //Bundle bundle = getIntent().getExtras();
        //int count=bundle.getInt("count");
        //count++;


        Toast.makeText(this.getApplicationContext(), "Symptoms saved. Enter next one. Pos=" + spinner_pos, Toast.LENGTH_LONG).show();
        Intent i = new Intent(this.getApplicationContext(),symptom.class);
        //bundle.putInt("count",count);
        //i.putExtras(bundle);
        startActivity(i);
        //overridePendingTransition(R.anim.animation1,R.anim.animation2);
    }

    public void diagnose(View view) {

        /*List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("key","value"));
        InputStream is=new InputStream();

        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = null;
            httppost = new HttpPost("www.myURL.com");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection" + e.toString());
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
            sb = new StringBuilder();
            sb.append(reader.readLine() + "\n");
            String line = "0";
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            sb.deleteCharAt(sb.length() - 1);
            is.close();
            result = sb.toString();
        } catch (Exception e) {
            Log.e("log_tag", "Error converting result " + e.toString());
        }*/

     /*   Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //code to do the HTTP request
                HttpClient httpclient = new DefaultHttpClient();

                HttpPost httppost = new HttpPost("http://192.168.43.89:3333/post_to_java.php");
                try {

// Add your data

                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);

                    nameValuePairs.add(new BasicNameValuePair("action", "blah"));
                    try {
                        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

// Execute HTTP Post Request

                        ResponseHandler<String> responseHandler = new BasicResponseHandler();
                        String response = httpclient.execute(httppost, responseHandler);

//This is the response from a php application
                        String reverseString = response;

                        Toast.makeText(this.getApplicationContext(), "response" + reverseString, Toast.LENGTH_LONG).show();
                    } catch (ClientProtocolException e) {
                        Toast.makeText(, "CPE response " + e.toString(), Toast.LENGTH_LONG).show();
                    }
            /*catch (IOException ioe)
            {
                Toast.makeText(this, "CPE response " + ioe.toString(), Toast.LENGTH_LONG).show();

            }

                } catch (IOException e) {
                    Toast.makeText(this, "IOE response " + e.toString(), Toast.LENGTH_LONG).show();

            }
        });

            thread.start();
        }*/



//This is the data to send

        //String url = 'https://www.youtube.com/watch?v=sEhy-RXkNo0'; //any data to send


        /*

        ExecutorService threadpool = Executors.newFixedThreadPool(2);
        Async async = Async.newInstance().use(threadpool);
        final DownloadManager.Request request = DownloadManager.Request.Get(requestURL);

        Future<Content> future = async.execute(request, new FutureCallback<Content>() {
            public void failed (final Exception e) {
                System.out.println(e.getMessage() +": "+ request);
            }
            public void completed (final Content content) {
                System.out.println("Request completed: "+ request);
                System.out.println("Response:\n"+ content.asString());
            }

            public void cancelled () {}
        });

       */





        Toast.makeText(this.getApplicationContext(), "Ongoing diagnosis...", Toast.LENGTH_LONG).show();
        Intent i = new Intent(this.getApplicationContext(), suggestions.class);
        startActivity(i);
        //overridePendingTransition(R.anim.animation1,R.anim.animation2);
    }

    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {



        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            // An item was selected. You can retrieve the selected item using
            Object item = parent.getItemAtPosition(pos);
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    }
}

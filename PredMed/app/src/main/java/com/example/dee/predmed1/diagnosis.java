package com.example.dee.predmed1;

import android.app.Activity;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;



public class diagnosis extends Activity {

    TextView v0,v1;
    public SQLiteDatabase db;
    String sympt[] = new String[100];
    Integer[] set = new Integer[100];
    int k=0;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);

        v0 = (TextView) findViewById(R.id.textView12);
        //v1 = (TextView) findViewById(R.id.textView13);
        db = openOrCreateDatabase("symptomDB", Context.MODE_APPEND,null);



        Cursor c = db.rawQuery("select * from symnums",null);
        while(c.moveToNext()) {
            try {

                sympt[k++]=c.getString(0).toString();
                //Toast.makeText(this.getApplicationContext(), c.getString(0).toString(), Toast.LENGTH_SHORT).show();
            }
            catch(Exception e){
                System.out.println("Error in retrieve!"+ e.toString());
            }

        }


        try {

            db.execSQL("drop table symnums");
        }
        catch(Exception e){
            System.out.println("Error in drop!"+ e.toString());
        }
        db.close();



        new httpclient_diag().execute();

    }



    class httpclient_diag extends AsyncTask<Void,Void, String> {

        public String[] text = new String[10];
        public double[] value = new double[10];
        public String resp;


        protected String doInBackground(Void... voids) {

            connect();

            return "All done";
        }

        public void connect() {

          /*  int k=0;


            Cursor c = db.rawQuery("select * from symnums",null);
            while(c.moveToNext()) {
                try {

                    sympt[k++]=c.getString(0).toString();
                    //Toast.makeText(this.getApplicationContext(), c.getString(0).toString(), Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                    System.out.println("Error in retrieve!"+ e.toString());
                }

            }*/
            String[] suggestions;
            String[] sug_str = new String[10];
            Resources res = getResources();
            suggestions = res.getStringArray(R.array.symptoms_array);
            //final String[] sug_str = new String[10];
            //final RadioButton r1, r2, r3, r4;
            for (int i = 0; i < k; i++) {

                set[i]=Integer.parseInt(sympt[i]);

                sug_str[i] = suggestions[set[i]];


                //Toast.makeText(this.getApplicationContext(), "Suggestion: "+suggestion, Toast.LENGTH_LONG).show();
            }




            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
            nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("i1", "Item1"));
            nameValuePairs.add(new BasicNameValuePair("i2", "Item2"));
            nameValuePairs.add(new BasicNameValuePair("i3", "Item3"));

            nameValuePairs.add(new BasicNameValuePair("question", "I have"+sug_str[0]+" "+sug_str[1]+" "+sug_str[2]+"."+" What do I have?"));//generate question from query here

            System.out.println("Hiiiiiiiiiiiiiiiiiiiii");
            try {


                System.out.println("Hiiiiiiiiiiiiiiiiiiiii");
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://watson-qa-univalve-homework.mybluemix.net/ask.php");
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost);


                HttpEntity entity = response.getEntity();
                long len = entity.getContentLength();
                InputStreamReader inputStreamReader = null;
                try {
                    inputStreamReader = new InputStreamReader(entity.getContent());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                JsonParser p = new JsonParser();

                JsonElement j = p.parse(inputStreamReader);

                JsonObject temp_init = j.getAsJsonArray().get(0).getAsJsonObject();
                JsonObject temp = temp_init.getAsJsonObject().getAsJsonObject("question");

                //System.out.println(temp);
                JsonArray temp1 = temp.getAsJsonArray("evidencelist");
                for (int i = 0; i < temp1.size(); i++) {

                    System.out.println(temp1.get(i).toString());

                }
                for (int i = 0; i < temp1.size(); i++) {
                    JsonElement val = temp1.get(i);
                    System.out.println("----------------------------------------------------");
                    //System.out.println(val);
                    JsonObject tmp = val.getAsJsonObject();
                    String yo = tmp.get("value").getAsString();
                    value[i] = Double.parseDouble(yo);
                    text[i] = tmp.get("text").getAsString().toString();
                    System.out.printf("Confidence value = %.2f", value[i] * 100);
                    System.out.println("%");
                    System.out.println("Description : " + text[i]);
                    System.out.println("----------------------------------------------------");
                }



                System.out.println("Hiiiiiiiiiiiiiiiiiiiii");




            } catch (Exception e) {
                System.out.println(e.toString());
                //Toast.makeText(this, "HTTP response " + e.toString(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override


        public void onPostExecute(String complete) {
            super.onPostExecute(complete);

            System.out.println(complete);
            v0.setText("1. Confidence: "+String.valueOf(value[0]*100)+"%\n\n"+text[0]+"\n\n\n"+"2. Confidence: "+String.valueOf(value[1]*100)+"%\n\n"+text[1]+"\n\n\n"+"3. Confidence: "+String.valueOf(value[2]*100)+"%\n\n"+text[2]);


        }
    }



}


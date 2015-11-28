package com.example.dee.predmed1;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class suggestions extends ActionBarActivity {

    public SQLiteDatabase db;

    //private Button btnDiagnose;
    String sympt[] = new String[100];
    private RadioGroup radioGroup;
    int k = 0, l = 0,position;
    String[] suggestions;
    String[] sug_str = new String[10];
    RadioButton r1, r2, r3, r4;
    Integer[] set = new Integer[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestions);

        db = openOrCreateDatabase("symptomDB", Context.MODE_APPEND,null);

        //btnDiagnose = (Button) findViewById(R.id.button7);

        Integer[] q_set = new Integer[5];
        Integer[][] arr = new Integer[3][5];



        int k=0;
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

        /*try {

            db.execSQL("drop table symnums");
        }
        catch(Exception e){
            System.out.println("Error in drop!"+ e.toString());
        }*/
        for(int i=0;i<k;i++)
            System.out.println(sympt[i]);
        new postToDB().execute();




    }
    class postToDB extends AsyncTask<Void,Void,String> {

        String rsp;

        protected String doInBackground(Void... voids)  {

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
            nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("i1", sympt[0]));
            nameValuePairs.add(new BasicNameValuePair("i2", sympt[1]));
            nameValuePairs.add(new BasicNameValuePair("i3", sympt[2]));


            //nameValuePairs.add(new BasicNameValuePair("question", "I have high fever, cold and chest pain. What do I have?"));//generate question from query here


            try {



                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://192.168.1.106:3333/blahblah.php");
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost);


                HttpEntity entity = response.getEntity();
                rsp = EntityUtils.toString(entity);



            } catch (Exception e) {
                System.out.println(e.toString());
            }
            return "All done";
        }

        public void onPostExecute(String s) {
            super.onPostExecute(s);

            System.out.println("Server response :" + rsp);
            if (rsp!=null&&(rsp.contains("1")||rsp.contains("2")||rsp.contains("3")||rsp.contains("4")||rsp.contains("5")||rsp.contains("6")||rsp.contains("7")||rsp.contains("8")||rsp.contains("9")) ) {
                String arr[] = rsp.split(" ");
                set[0] = Integer.parseInt(arr[0]);
                set[1] = Integer.parseInt(arr[1]);
                Resources res = getResources();
                suggestions = res.getStringArray(R.array.symptoms_array);
                //final String[] sug_str = new String[10];
                //final RadioButton r1, r2, r3, r4;
                for (int i = 0; i < 2; i++) {

                    sug_str[i] = suggestions[set[i]];


                    //Toast.makeText(this.getApplicationContext(), "Suggestion: "+suggestion, Toast.LENGTH_LONG).show();
                }


                r1 = (RadioButton) findViewById(R.id.radioButton);
                r2 = (RadioButton) findViewById(R.id.radioButton2);


                r1.setText(sug_str[0]);
                r2.setText(sug_str[1]);


                radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
                radioGroup.clearCheck();

        /* Attach CheckedChangeListener to radio group */

                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton rb = (RadioButton) group.findViewById(checkedId);
                        if (null != rb && checkedId > -1) {
                            //Toast.makeText(suggestions.this, rb.getText(), Toast.LENGTH_SHORT).show();
                            //for (int i = 0; i < l; i++) {

                            //   if(sug_str[i].equals(rb.getText().toString())) {
                            //     position = i;
                            //System.out.println(rb.getText());
                            if (rb == r1)
                                position = set[0] - 1;
                            else if (rb == r2)
                                position = set[1] - 1;
                            else if (rb == r3)
                                position = set[2] - 1;
                            else if (rb == r4)
                                position = set[3] - 1;
                            else
                                System.out.println("Error");
                            //   break;

                            //}


                            //Toast.makeText(this.getApplicationContext(), "Suggestion: "+suggestion, Toast.LENGTH_LONG).show();


                        }

                    }
                });

            }
        }
    }

    public void onClear(View v) {
        /* Clears all selected radio buttons to default */
        radioGroup.clearCheck();
    }

    public void onSubmit(View v) {
        //RadioButton rb = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
        /*if(rb==r1)
            position=set[0]-1;
        else if(rb==r2)
            position=set[1]-1;
        else if(rb==r3)
            position=set[2]-1;
        else if(rb==r4)
            position=set[3]-1;
        else
            System.out.println("Error");
        //   break;*/
        System.out.println(position);
        //
        //
        // Toast.makeText(suggestions.this,"Value= "+position , Toast.LENGTH_SHORT).show();

        try {

            db.execSQL("insert into symnums values(" + position + ")");
        }
        catch(Exception e){
            System.out.println("Error in insert!"+ e.toString());
        }
        db.close();

        Intent i = new Intent(this.getApplicationContext(), diagnosis.class);
        startActivity(i);
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_suggestions, menu);
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
}

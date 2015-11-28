package com.example.dee.predmed1;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Arrays;


public class sugg1 extends ActionBarActivity {

    public SQLiteDatabase db;

    //private Button btnDiagnose;
    private RadioGroup radioGroup;
    int k = 0, l = 0,position;
    String[] suggestions;
    String[] sug_str = new String[10];
    RadioButton r1, r2, r3, r4;
    Integer[] set = new Integer[10];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugg1);

        db = openOrCreateDatabase("symptomDB", Context.MODE_APPEND,null);

        //btnDiagnose = (Button) findViewById(R.id.button7);



        Resources res = getResources();
        suggestions = res.getStringArray(R.array.symptoms_array);
        //final String[] sug_str = new String[10];
        //final RadioButton r1, r2, r3, r4;
        for (int i = 0; i < l; i++) {

            sug_str[i] = suggestions[set[i] - 1];


            //Toast.makeText(this.getApplicationContext(), "Suggestion: "+suggestion, Toast.LENGTH_LONG).show();
        }


        r1 = (RadioButton) findViewById(R.id.radioButton);
        r2 = (RadioButton) findViewById(R.id.radioButton2);
        r3 = (RadioButton) findViewById(R.id.radioButton3);
        r4 = (RadioButton) findViewById(R.id.radioButton4);

        r1.setText(sug_str[0]);
        r2.setText(sug_str[1]);
        r3.setText(sug_str[2]);
        r4.setText(sug_str[3]);

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
                    if(rb==r1)
                        position=set[0]-1;
                    else if(rb==r2)
                        position=set[1]-1;
                    else if(rb==r3)
                        position=set[2]-1;
                    else if(rb==r4)
                        position=set[3]-1;
                    else
                        System.out.println("Error");
                    //   break;

                    //}
                    //Toast.makeText(this.getApplicationContext(), "Suggestion: "+suggestion, Toast.LENGTH_LONG).show();

                }

            }
        });

    }

    public void onClear(View v) {
        /* Clears all selected radio buttons to default */
        radioGroup.clearCheck();
    }

    public void onSubmit(View v) {
        RadioButton rb = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
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
        //Toast.makeText(sugg1.this, "Value= " + position, Toast.LENGTH_SHORT).show();

        try {

            db.execSQL("insert into symnums values(" + position + ")");
        }
        catch(Exception e){
            System.out.println("Error in insert!"+ e.toString());
        }
//PASS TO ACHAL'S TABLE HERE
     /*   try {

            db.execSQL("drop table symnums");
        }
        catch(Exception e){
            System.out.println("Error in drop!"+ e.toString());
        }*/

        Toast.makeText(this.getApplicationContext(), "Ongoing diagnosis", Toast.LENGTH_LONG).show();
        Intent i = new Intent(this.getApplicationContext(), diagnosis.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sugg1, menu);
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

package com.example.dee.predmed1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class symptom4 extends ActionBarActivity {

    public SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom4);

        db = openOrCreateDatabase("symptomDB", Context.MODE_APPEND,null);

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
        getMenuInflater().inflate(R.menu.menu_symptom4, menu);
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

    public void storesym4_nxtsym(View view) {

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


        try {

            db.execSQL("insert into symnums values(" + spinner_pos + ")");
        }
        catch(Exception e){
            System.out.println("Error in insert!"+ e.toString());
        }


        Cursor c = db.rawQuery("select * from symnums",null);
        while(c.moveToNext()) {
            try {
               // Toast.makeText(this.getApplicationContext(), c.getString(0).toString(), Toast.LENGTH_SHORT).show();
            }
            catch(Exception e){
                System.out.println("Error in retrieve!"+ e.toString());
            }
        }

        //Toast.makeText(this.getApplicationContext(), "Symptoms saved. Enter next one. Pos=" + spinner_pos, Toast.LENGTH_LONG).show();
        db.close();
        Intent i = new Intent(this.getApplicationContext(),symptom5.class);
        //bundle.putInt("count",count);
        //i.putExtras(bundle);
        startActivity(i);
        //overridePendingTransition(R.anim.animation1,R.anim.animation2);
    }

    public void diagnose(View view) {

        db.close();
        Toast.makeText(this.getApplicationContext(), "Ongoing diagnosis...", Toast.LENGTH_LONG).show();
        Intent i = new Intent(this.getApplicationContext(), sugg1.class);
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

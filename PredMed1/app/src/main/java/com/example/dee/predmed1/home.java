package com.example.dee.predmed1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class home extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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

    public void takesymp(View view){

        Bundle b_glob=new Bundle();
        //Intent i_glob=new Intent(this.getApplicationContext(),symptom.class);
        //b_glob.putInt("count",0);
        //i_glob.putExtras(b_glob);
        Toast.makeText(this.getApplicationContext(), "Enter symptoms", Toast.LENGTH_LONG).show();
        Intent i = new Intent(this.getApplicationContext(),symptom.class);
        startActivity(i);

    }
}

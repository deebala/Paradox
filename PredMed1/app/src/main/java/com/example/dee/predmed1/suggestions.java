package com.example.dee.predmed1;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

import java.util.Arrays;


public class suggestions extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestions);

        Integer[] q_set = new Integer[5];
        Integer[][] arr = new Integer[3][5];
        Integer[] set = new Integer[10];
        int k=0,l=0;
        q_set[0]=1;
        q_set[1]=2;
        q_set[2]=3;
        Integer[][] freq_sets = new Integer[3][5];
        freq_sets[0][0]=1;
        freq_sets[0][1]=2;
        freq_sets[0][2]=3;
        freq_sets[0][3]=4;
        freq_sets[0][4]=5;
        freq_sets[1][0]=1;
        freq_sets[1][1]=2;
        freq_sets[1][2]=3;
        freq_sets[1][3]=6;
        freq_sets[1][4]=7;
        freq_sets[2][0]=4;
        freq_sets[2][1]=5;
        freq_sets[2][2]=6;
        freq_sets[2][3]=8;
        freq_sets[2][4]=9;
        int count=0;
        for(int i=0;i<3;i++) {
            count = 0;
            for (int j = 0; j < 3; j++) {
                if (Arrays.asList(freq_sets[i]).contains(new Integer(q_set[j]))) {
                    count++;
                }

            }
            if (count == 3)//complete match
            {
                arr[k++] = freq_sets[i];//store relevant freq set
            }
        }

        for(int i=0;i<k;i++)
        {
            for(int j=0;j<5;j++)
            {
                if(arr[i][j]==q_set[j])
                    continue;
                set[l++]=arr[i][j];
            }

        }

        Resources res = getResources();
        String[] suggestions = res.getStringArray(R.array.symptoms_array);
        String[] sug_str=new String[10];
        RadioButton r1,r2,r3,r4;
        for(int i=0;i<l;i++){

            sug_str[i] = suggestions[set[i]-1];


            //Toast.makeText(this.getApplicationContext(), "Suggestion: "+suggestion, Toast.LENGTH_LONG).show();
        }


        r1=(RadioButton)findViewById(R.id.radioButton);
        r2=(RadioButton)findViewById(R.id.radioButton2);
        r3=(RadioButton)findViewById(R.id.radioButton3);
        r4=(RadioButton)findViewById(R.id.radioButton4);

        r1.setText(sug_str[0]);
        r2.setText(sug_str[1]);
        r3.setText(sug_str[2]);
        r4.setText(sug_str[3]);





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

    public void watson(View view){
        Intent i = new Intent(this.getApplicationContext(), diagnosis.class);
        startActivity(i);
    }
}

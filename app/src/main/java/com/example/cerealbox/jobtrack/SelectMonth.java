package com.example.cerealbox.jobtrack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class SelectMonth extends AppCompatActivity {

    private ApplicationsDataSource ds;
    private ListView monthsList;

    private String[] monthsArray = {"January", "February", "March", "April", "May", "June",
                                "July", "August", "Septmeber", "October", "November", "December"};


    public final static String SELECTED_MONTH = "com.mycompany.myfirstapp.SELECTED_MONTH";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_month);

        monthsList = (ListView) findViewById(R.id.monthsListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, monthsArray);
        monthsList.setAdapter(adapter);


        monthsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SelectMonth.this, CheckAllApplications.class);
                intent.putExtra(SELECTED_MONTH, position+1);
                startActivity(intent);
            }
        });

        /*ds = new ApplicationsDataSource(this);
        ds.open();
        */


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_month, menu);
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

package com.example.cerealbox.jobtrack;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class SearchResultsActivity extends AppCompatActivity {

    private ApplicationsDataSource ds;
    private ListView applicationsList;

    private ArrayAdapter<Applications> adapter;


    public final static String SELECTED_COMPANY = "com.mycompany.myfirstapp.SELECTED_COMPANY";
    public final static String SELECTED_POSITION = "com.mycompany.myfirstapp.SELECTED_POSITION";
    public final static String SELECTED_DATE = "com.mycompany.myfirstapp.SELECTED_DATE";
    public final static String SELECTED_INTERVIEW_STATUS = "com.mycompany.myfirstapp.SELECTED_INTERVIEW_STATUS";
    public final static String SELECTED_OFFER_STATUS = "com.mycompany.myfirstapp.SELECTED_OFFER_STATUS";
    public final static String SAVED_AID = "com.mycompany.myfirstapp.SAVED_AID";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        Log.w("query", "going into activity");


        ds = new ApplicationsDataSource(this);
        ds.open();

        String query = handleIntent(getIntent());

        /*Gather all applications given the search term*/
        final List<Applications> allApplications = ds.displayApplicationsForSearch(query);

        applicationsList = (ListView) findViewById(R.id.searchListView);

                /*Create adapter for ListView and set it to our applications list*/
        adapter = new ArrayAdapter<Applications>(this,
                android.R.layout.simple_list_item_1, allApplications);
        applicationsList.setAdapter(adapter);

        /*Set the OnItemClick Listener for our ListView cells*/
        applicationsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*on click, we send the following information to the EditApplication Activity*/
                Intent intent = new Intent(SearchResultsActivity.this, EditApplication.class);
                intent.putExtra(SELECTED_COMPANY, allApplications.get(position).getCompany());
                intent.putExtra(SELECTED_POSITION, allApplications.get(position).getPosition());
                intent.putExtra(SELECTED_DATE, allApplications.get(position).getDateAsString());
                intent.putExtra(SELECTED_INTERVIEW_STATUS, allApplications.get(position).getInterviewStatus());
                intent.putExtra(SELECTED_OFFER_STATUS, allApplications.get(position).getOfferStatus());
                intent.putExtra(SAVED_AID, allApplications.get(position).getAid());


                startActivity(intent);
            }
        });

    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_results, menu);
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

    private String handleIntent(Intent intent) {

        String query = "";
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
            Log.w("query", query);
        }
        return query;
    }




}

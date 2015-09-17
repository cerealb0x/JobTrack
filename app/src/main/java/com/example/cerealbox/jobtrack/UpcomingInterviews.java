package com.example.cerealbox.jobtrack;

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
import android.widget.TextView;

import java.util.List;

public class UpcomingInterviews extends AppCompatActivity {

    private ApplicationsDataSource ds;
    private ListView interviewsList;
    private TextView noInterviewsText;
    private ArrayAdapter<Applications> adapter;

    public final static String SELECTED_COMPANY = "com.mycompany.myfirstapp.SELECTED_COMPANY";
    public final static String SELECTED_POSITION = "com.mycompany.myfirstapp.SELECTED_POSITION";
    public final static String SELECTED_DATE = "com.mycompany.myfirstapp.SELECTED_DATE";
    public final static String SELECTED_INTERVIEW_STATUS = "com.mycompany.myfirstapp.SELECTED_INTERVIEW_STATUS";
    public final static String SELECTED_OFFER_STATUS = "com.mycompany.myfirstapp.SELECTED_OFFER_STATUS";
    public final static String SELECTED_INTERVIEW_DATE = "com.mycompany.myfirstapp.SELECTED_INTERVIEW_DATE";
    public final static String SAVED_AID = "com.mycompany.myfirstapp.SAVED_AID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_interviews);

        Intent intent = getIntent();

        ds = new ApplicationsDataSource(this);
        ds.open();

        /*Gather all applications for the selected month*/
        final List<Applications> allApplications = ds.displayApplicationsWithUpcomingInterviews();//display applications with upcoming interviews


        interviewsList = (ListView) findViewById(R.id.interviewsListView);
        noInterviewsText = (TextView) findViewById(R.id.noInterviewsText);

        /*Create adapter for ListView and set it to our applications list*/
        adapter = new ArrayAdapter<Applications>(this,
                android.R.layout.simple_list_item_1, allApplications);
        interviewsList.setAdapter(adapter);

        /*Set the OnItemClick Listener for our ListView cells*/
        interviewsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*on click, we send the following information to the EditApplication Activity*/

                Log.w("interview date", "intent is created");


                Intent intent = new Intent(UpcomingInterviews.this, EditApplication.class);
                intent.putExtra(SELECTED_COMPANY, allApplications.get(position).getCompany());
                intent.putExtra(SELECTED_POSITION, allApplications.get(position).getPosition());
                intent.putExtra(SELECTED_DATE, allApplications.get(position).getDateAsString());
                intent.putExtra(SELECTED_INTERVIEW_STATUS, allApplications.get(position).getInterviewStatus());
                intent.putExtra(SELECTED_OFFER_STATUS, allApplications.get(position).getOfferStatus());
                intent.putExtra(SELECTED_INTERVIEW_DATE, allApplications.get(position).getInterviewDateAsString());
                intent.putExtra(SAVED_AID, allApplications.get(position).getAid());


                startActivity(intent);
            }
        });



        /*Set our empty application list text to invisible when we have applications to show*/
        if(!allApplications.isEmpty()) {
            noInterviewsText.setVisibility(View.GONE);

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_upcoming_interviews, menu);
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


    @Override
    protected void onResume() {
        super.onResume();
                /*Gather all applications for the selected month*/
        final List<Applications> allApplications = ds.displayApplicationsWithUpcomingInterviews();//display applications with upcoming interviews


        interviewsList = (ListView) findViewById(R.id.interviewsListView);
        noInterviewsText = (TextView) findViewById(R.id.noInterviewsText);

        /*Create adapter for ListView and set it to our applications list*/
        adapter = new ArrayAdapter<Applications>(this,
                android.R.layout.simple_list_item_1, allApplications);
        interviewsList.setAdapter(adapter);

        /*Set the OnItemClick Listener for our ListView cells*/
        interviewsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*on click, we send the following information to the EditApplication Activity*/

                Log.w("interview date", "intent is created");


                Intent intent = new Intent(UpcomingInterviews.this, EditApplication.class);
                intent.putExtra(SELECTED_COMPANY, allApplications.get(position).getCompany());
                intent.putExtra(SELECTED_POSITION, allApplications.get(position).getPosition());
                intent.putExtra(SELECTED_DATE, allApplications.get(position).getDateAsString());
                intent.putExtra(SELECTED_INTERVIEW_STATUS, allApplications.get(position).getInterviewStatus());
                intent.putExtra(SELECTED_OFFER_STATUS, allApplications.get(position).getOfferStatus());
                intent.putExtra(SELECTED_INTERVIEW_DATE, allApplications.get(position).getInterviewDateAsString());
                intent.putExtra(SAVED_AID, allApplications.get(position).getAid());


                startActivity(intent);
            }
        });



        /*Set our empty application list text to invisible when we have applications to show*/
        if(!allApplications.isEmpty()) {
            noInterviewsText.setVisibility(View.GONE);

        }else{
            noInterviewsText.setVisibility(View.VISIBLE);
        }

    }
}

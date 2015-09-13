package com.example.cerealbox.jobtrack;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class EditApplication extends AppCompatActivity {

    private ApplicationsDataSource ds;
    private EditText companyField;
    private EditText positionField;
    private EditText dateField;
    private CheckBox receivedInterviewBox;
    private CheckBox receivedOfferBox;
    private String savedAID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_application);
        Intent intent = getIntent();


        companyField = (EditText) findViewById(R.id.edit_company_field);
        positionField = (EditText) findViewById(R.id.edit_position_field);
        dateField = (EditText) findViewById(R.id.edit_dateField);
        receivedInterviewBox = (CheckBox) findViewById(R.id.receivedInterviewCheckBox);
        receivedOfferBox = (CheckBox) findViewById(R.id.receivedOfferCheckBox);

        String savedCompany = intent.getStringExtra(CheckAllApplications.SELECTED_COMPANY);
        String savedPosition = intent.getStringExtra(CheckAllApplications.SELECTED_POSITION);
        String savedDate = intent.getStringExtra(CheckAllApplications.SELECTED_DATE);
        boolean savedInterviewStatus = intent.getIntExtra(CheckAllApplications.SELECTED_INTERVIEW_STATUS, 0) == 1 ? true:false;
        boolean savedOfferStatus = intent.getIntExtra(CheckAllApplications.SELECTED_OFFER_STATUS, 0) == 1 ? true:false;
        savedAID = intent.getStringExtra(CheckAllApplications.SAVED_AID);

        companyField.setText(savedCompany);
        positionField.setText(savedPosition);
        dateField.setText(savedDate);
        receivedInterviewBox.setChecked(savedInterviewStatus);
        receivedOfferBox.setChecked(savedOfferStatus);


        dateField.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(EditApplication.
                        this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                    /*      Your code   to get date and time    */

                        String date = String.valueOf(selectedmonth + 1) + "/" + String.valueOf(selectedday) + "/" + String.valueOf(selectedyear);

                        dateField.setText(date);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date of application");
                mDatePicker.show();
            }
        });






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_application, menu);
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

    public void editApplication(View view){
        if(companyField.getText().toString().isEmpty() || dateField.getText().toString().isEmpty()){
            alertUserOfNullFields();
            return;
        }

        ds = new ApplicationsDataSource(this);
        ds.open();
         Log.w("edit", companyField.getText().toString());
         Log.w("edit", positionField.getText().toString());
         Log.w("edit", dateField.getText().toString());


        ds.editCompany(companyField.getText().toString(), positionField.getText().toString(), dateField.getText().toString(), receivedInterviewBox.isChecked(), receivedOfferBox.isChecked(), savedAID);

        companyField.clearFocus();
        positionField.clearFocus();


        this.finish();

    }

    public void deleteApplication(View view){

        ds = new ApplicationsDataSource(this);
        ds.open();

        ds.deleteApplication(savedAID);

        this.finish();

    }



    public void alertUserOfNullFields(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Error Saving - Please make sure that the company and date fields aren't empty");
        builder.setTitle("A Required Field is Empty");


        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                return;
            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();


    }

}



package com.example.cerealbox.jobtrack;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class TrackNewApplication extends AppCompatActivity {

    private ApplicationsDataSource ds;
    private EditText dateField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_new_application);

        dateField = (EditText)findViewById(R.id.dateField);

        dateField.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog( TrackNewApplication.
                this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                    /*      Your code   to get date and time    */

                        String date = String.valueOf(selectedmonth+1)+"/"+String.valueOf(selectedday)+"/"+String.valueOf(selectedyear);

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
        getMenuInflater().inflate(R.menu.menu_track_new_application, menu);
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


    public void setDate(View view){


    }


    public void saveApplication(View view){
        EditText company = (EditText)findViewById(R.id.company_field);
        EditText position = (EditText)findViewById(R.id.position_field);
        EditText date = (EditText)findViewById(R.id.dateField);

        if(company.getText().toString().isEmpty() || date.getText().toString().isEmpty()){
            alertUserOfNullFields();
            return;
        }

        ds = new ApplicationsDataSource(this);
        ds.open();
       // Log.w("onClick", "calling saveApplication");
       // Log.w("onClick", "company field says "+company.getText().toString());
       // Log.w("onClick", "position field says "+position.getText().toString());


        ds.insertCompany(company.getText().toString(), position.getText().toString(), date.getText().toString());

        company.clearFocus();
        position.clearFocus();


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

package com.app.toast.publictransportation;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText edtxt_runm,edtxt_rname,edtxt_rage,edtxt_rpsw,edtxt_rnum;
    Spinner spn1;
    RadioGroup rg;
    RadioButton radio_male,radio_female;
    Button btn_submit;
    AlertDialog ad;
    String male,female,gen;
    String tb,spn;
    SQLiteDatabase database;
    String username,name,password,gender,city,mstatus,seek1,age,hobby1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        edtxt_runm=(EditText)findViewById(R.id.edtxt_runm);
        edtxt_rpsw=(EditText)findViewById(R.id.edtxt_rpsw);
        edtxt_rname=(EditText)findViewById(R.id.edtxt_rname);
        edtxt_rage=(EditText)findViewById(R.id.edtxt_rage);
        edtxt_rnum=(EditText)findViewById(R.id.edtxt_rnum);
        spn1=(Spinner)findViewById(R.id.spn1);
        rg=(RadioGroup)findViewById(R.id.rg);
        radio_female=(RadioButton)findViewById(R.id.radio_female);
        radio_male=(RadioButton)findViewById(R.id.radio_male);
        btn_submit=(Button)findViewById(R.id.btn_submit);
        spn=String.valueOf(spn1.getItemAtPosition(0));


            database=openOrCreateDatabase("mydb.db",MODE_PRIVATE,null);
            String registration="CREATE TABLE IF NOT EXISTS register(username TEXT,password TEXT," +
                    "name TEXT,city TEXT,mno INTEGER,gender TEXT,age INTEGER);";
            database.execSQL(registration);
            database.close();







        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder build=new AlertDialog.Builder(RegisterActivity.this);
                build.setTitle("Alert Dialog");
                build.setMessage("Are you sure to register? ");
                build.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(i);

                        database=openOrCreateDatabase("mydb.db",MODE_PRIVATE,null);
                        ContentValues value=new ContentValues();
                        value.put("username",edtxt_runm.getText().toString());
                        value.put("password",edtxt_rpsw.getText().toString());
                        value.put("name",edtxt_rname.getText().toString());
                        value.put("city",spn1.getSelectedItem().toString());
                        value.put("mno",edtxt_rnum.getText().toString());
                        value.put("gender",gen);
                        value.put("age",edtxt_rage.getText().toString());


                            //value.put("Password",edtxt_rpsw.getText().toString());
                            long rel=database.insert("register",null,value);
                            if(rel<0)
                            {
                                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();

                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Data Inserted successfully",Toast.LENGTH_LONG).show();
                            }
                            database.close();





                    }
                });
                build.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"No",Toast.LENGTH_LONG).show();
                    }
                });
                ad=build.create();
                ad.show();




            }
        });
    radio_male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if(radio_male.isChecked())
            {
                gen=radio_male.getText().toString();
            }
        }
    });

        radio_female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(radio_female.isChecked())
                {
                    gen=radio_female.getText().toString();
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

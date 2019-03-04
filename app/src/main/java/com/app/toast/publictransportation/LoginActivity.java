package com.app.toast.publictransportation;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button btn_login,btn_register;
    EditText edtxt_unm,edtxt_psw;
    String username,password;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        edtxt_unm=(EditText)findViewById(R.id.edtxt_unm);
        edtxt_psw=(EditText)findViewById(R.id.edtxt_psw);
        btn_login=(Button)findViewById(R.id.btn_login);
        btn_register=(Button)findViewById(R.id.btn_register);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database = openOrCreateDatabase("mydb.db", MODE_PRIVATE, null);
                username=edtxt_unm.getText().toString();
                password=edtxt_psw.getText().toString();
                String []param=new String[]{username};
                Cursor c=database.rawQuery("select username,password from register where username=?",new String[]{username});

                if(username.equals("") && password.equals(""))
                {

                    Toast.makeText(getApplicationContext(),"Please Fill The data",Toast.LENGTH_SHORT).show();
                }
                else if(c.moveToFirst() && c!=null)
                {
                    String uname=c.getString(c.getColumnIndex("username"));
                    String psw=c.getString(c.getColumnIndex("password"));

                    if(uname.equals(username) && psw.equals(password))
                    {
                        Intent i = new Intent(getApplicationContext(), WelcomeActivity.class);
                        //i.putExtra("username",edtxt_unm.getText().toString());
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Incurrect",Toast.LENGTH_SHORT).show();
                        uname="";
                        psw="";
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Register First",Toast.LENGTH_SHORT).show();
                }
                c.close();
                database.close();



            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(i);

            }
        });



    }
}

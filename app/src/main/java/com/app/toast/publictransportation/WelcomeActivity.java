package com.app.toast.publictransportation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {
    Button btn_train,btn_bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();

        btn_train=(Button)findViewById(R.id.btn_train);
        btn_bus=(Button)findViewById(R.id.btn_bus);

        btn_train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),TrainActivity.class);
                startActivity(i);

            }
        });

        btn_bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(),BusSchedule.class);
                startActivity(i1);

            }
        });

    }
}

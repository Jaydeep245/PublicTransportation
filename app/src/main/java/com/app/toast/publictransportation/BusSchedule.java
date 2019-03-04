package com.app.toast.publictransportation;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class BusSchedule extends AppCompatActivity {

    private String[] TRAINS= new String[] {"Bandra Bus Station","Bandra Police Station","Elco Market","Sent Peters's Church","Holi Family Hospitle","St Andrews Church","Mehboob Studio","Galexy Apartment","Prarthanalaya","Sister's Bungalow","Band Stand"};
    private ListView list1_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_schedule);
        getSupportActionBar().hide();
        listView();
    }
    public void listView() {
        list1_view = (ListView) findViewById(R.id.lv1_layout);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.bus_list,TRAINS);
        list1_view.setAdapter(adapter);
        list1_view.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String tName = (String) list1_view.getItemAtPosition(position);
                        String tTimings = "Timings :\n";
                        int p,tT,tH,tM;
                        p=0;
                        for (int i=0;i<11;i++) {
                            if (TRAINS[i].equals(tName))
                                p=i;
                        }

                        for (int j=0;j<10;j++) {
                            tH = 10 + (j * 1);
                            tM = 10+(4*p);
                            tTimings = tTimings + tH + ":" + tM + "\n";
                        }


                        View alertV = LayoutInflater.from(BusSchedule.this).inflate(R.layout.timings_bus,null);
                        TextView alertText = (TextView) alertV.findViewById(R.id.timings_label1);
                        alertText.setText(tTimings);
                        AlertDialog.Builder alertB = new AlertDialog.Builder(BusSchedule.this);
                        alertB.setMessage(tName +" to FR. Agnel Ashram")
                                .setView(alertV)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        return;
                                    }
                                })
                                .setCancelable(false);
                        AlertDialog alert = alertB.create();
                        alert.show();
                    }
                }
        );
    }
}

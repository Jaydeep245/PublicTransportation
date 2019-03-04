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

public class TrainActivity extends AppCompatActivity {

    private String[] TRAINS= new String[] {"Virar","Vasai Road","Bhayander","Dahisar","Borivali","Kandivali","Malad","Goregoan","Andheri","Vile Parle","Santa Cruz","Bandra","Mahim","Dadar","Lower Parel","Mumbai Central","Grant Road","Marine Lines"};
    private ListView list_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);
        getSupportActionBar().hide();
        listView();
    }
    public void listView() {
        list_view = (ListView) findViewById(R.id.lv_layout);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.train_list,TRAINS);
        list_view.setAdapter(adapter);
        list_view.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String tName = (String) list_view.getItemAtPosition(position);
                        String tTimings = "Timings :\n";
                        int p,tT,tH,tM;
                        p=0;
                        for (int i=0;i<18;i++) {
                            if (TRAINS[i].equals(tName))
                                p=i;
                        }

                        for (int j=0;j<10;j++) {
                            tH = 10 + (j * 1);
                            tM = 10+(2*p);
                            tTimings = tTimings + tH + ":" + tM + "\n";
                        }


                        View alertV = LayoutInflater.from(TrainActivity.this).inflate(R.layout.timings_layout,null);
                        TextView alertText = (TextView) alertV.findViewById(R.id.timings_label);
                        alertText.setText(tTimings);
                        AlertDialog.Builder alertB = new AlertDialog.Builder(TrainActivity.this);
                        alertB.setMessage(tName +" to Churchgate")
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

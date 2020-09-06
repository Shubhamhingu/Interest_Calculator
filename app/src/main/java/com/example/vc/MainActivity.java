package com.example.vc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText principal,rate,months;
    Button enter;
    String Rate,prin,nom;
    Float r,ra;
    int p,n,inc,stam,i,min,sum=0,dep;
    int[] a = new int[100];
    String start;
    Boolean q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        principal=findViewById(R.id.etPrin);
        rate=findViewById(R.id.etrate);
        months=findViewById(R.id.etmonths);
        enter=findViewById(R.id.btnEnter);
        display();


    }
    public void display(){
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timepass();
            }
        });
    }
    public void timepass(){
        Rate =rate.getText().toString();
        prin=principal.getText().toString();
        nom=months.getText().toString();
        q=converter(prin,nom,Rate);
        if(q){
            calculate();
            Intent intent =new Intent(MainActivity.this,SecondActivity.class);
            intent.putExtra("Start",start);
            startActivity(intent);
        }
        else {
            Toast.makeText(this,"Please Fill in all The fields..",Toast.LENGTH_SHORT).show();
        }

    }
    public Boolean converter(String prin, String nom, String Rate){
        if(prin.isEmpty() && nom.isEmpty() && Rate.isEmpty()){
            Toast.makeText(this,"Please Fill in all The fields..",Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            p = Integer.parseInt(prin);
            r = Float.parseFloat(Rate);
            n = Integer.parseInt(nom);
            return true;
        }
    }
    public void calculate(){
        ra=r/100;
        inc= (int) ((ra*p)/n);
        stam=(p/n)+inc;
        start="Increment in Every Installment is:"+inc+"\n";
        dep=stam-inc;
        start=start+"Starting Deposit is:"+dep+"\n";
        min=inc*n;
        a[0]=stam-min;
        for(i=0;i<n-2;i++){a[i+1]=a[i]+inc;}
        for(i=0;i<n-1;i++) {start=start+(i+1+" Installment is:"+a[i]+"\n");}
        for(i=0;i<=n;i++) { sum=sum+a[i]; }
        start=start+("THE TOTAL AMOUNT PAID IS:"+(sum+stam-inc));

    }
}

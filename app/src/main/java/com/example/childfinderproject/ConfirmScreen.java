package com.example.childfinderproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ConfirmScreen extends AppCompatActivity {

    TextView tv_top2,con_tv_user;
    ImageView con_img_parent,con_img_user,logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_screen);

        tv_top2=findViewById(R.id.tv_top2);
        con_tv_user=findViewById(R.id.con_tv_user);
        con_img_parent=findViewById(R.id.con_img_parent);
        con_img_user=findViewById(R.id.con_img_user);
        logout=findViewById(R.id.logout);


        con_img_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent parentIntent = new Intent(ConfirmScreen.this,ParentMenuScreen.class);
                startActivity(parentIntent);
            }
        });


        con_img_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent parentIntent = new Intent(ConfirmScreen.this,FinderMenuScreen.class);
                startActivity(parentIntent);
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(ConfirmScreen.this);
                builder.setTitle("Are you sure...");
                builder.setMessage("please confirm Yes/No.!");
                builder.setCancelable(false);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent logIntent = new Intent(ConfirmScreen.this,LoginScreen.class);
                        startActivity(logIntent);
                        finish();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog myalter=builder.create();
                myalter.show();

            }
        });


    }
}
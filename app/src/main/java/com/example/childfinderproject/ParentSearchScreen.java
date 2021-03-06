package com.example.childfinderproject;

import androidx.annotation.NonNull;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.childfinderproject.Model.FinderUploadDataClass;
import com.example.childfinderproject.Model.ParentUploadDataClass;
import com.example.childfinderproject.Model.UserAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;
import com.google.mlkit.vision.common.InputImage;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.support.common.TensorOperator;
import org.tensorflow.lite.support.common.ops.NormalizeOp;
import org.tensorflow.lite.support.image.ImageProcessor;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.image.ops.ResizeOp;
import org.tensorflow.lite.support.image.ops.ResizeWithCropOrPadOp;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;

public class ParentSearchScreen extends AppCompatActivity {


    private RecyclerView recyclerView;
    private UserAdapter mUserAdapter;
    private List<FinderUploadDataClass> mDatalist;

    FirebaseDatabase database;
    DatabaseReference myRef;

    ImageView img_parent_search_back;

    private ChildEventListener MyChildEventListener;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myRef.removeEventListener(MyChildEventListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_search_screen);

        img_parent_search_back=findViewById(R.id.img_parent_search_back);


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("FinderUploadData");



        img_parent_search_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent backIntent= new Intent(ParentSearchScreen.this,ParentMenuScreen.class);
                startActivity(backIntent);
                finish();


            }
        });

        mDatalist=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUserAdapter=new UserAdapter(this,mDatalist);
        recyclerView.setAdapter(mUserAdapter);

        MyChildEventListener=new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @com.google.firebase.database.annotations.Nullable String previousChildName) {

               if (snapshot.exists())
               {
                   FinderUploadDataClass user=snapshot.getValue(FinderUploadDataClass.class);
            /*Log.d(TAG,"User Name :" + user.getUserName());
            Log.d(TAG,"User Name :" + user.getUserPassword());*/
                   mDatalist.add(user);
                   mUserAdapter.notifyDataSetChanged();
               }
               else
               {
                   Toast.makeText(ParentSearchScreen.this, "No Image Found", Toast.LENGTH_SHORT).show();
               }


            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @com.google.firebase.database.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        };
        myRef.addChildEventListener(MyChildEventListener);


    }
}